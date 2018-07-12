package controller;

import com.aldebaran.qi.*;
import com.aldebaran.qi.helper.proxies.*;

public class Connection{

    //-----------------------Attribute-----------------------\\
    private static Application app;
    private Session session;
    private Future<Void> fut;
    private AnyObject ttsSay = null;
    private ALTextToSpeech textToSpeech;
    private ALAnimatedSpeech animatedSpeech;
    private ALRobotPosture robotPosture;
    private ALAudioDevice audioDevice;
    private ALMotion motion;
    private static boolean applicationAvaliable = false;

    //-----------------------Konstruktor-----------------------\\
    public Connection(String IP_ADRESS, String[] args) throws Exception{
        //Es darf nur ein Objekt der Applications-Klasse der NaoQi-API existieren
        if(!applicationAvaliable) {
            app = new Application(args);
            applicationAvaliable = true;
        }

        session = new Session();
        //session mit der Roboter IP-Adresse erstellen. Verbindung aufbauen.
        fut = session.connect("tcp://" + IP_ADRESS + ":" + AppProperties.getRobotTCPPort());
        fut.get();
        //Initialisieren der API Objekte für verschiedene Aktionen mit dem Roboter
        animatedSpeech = new ALAnimatedSpeech(session);
        robotPosture = new ALRobotPosture(session);
        audioDevice = new ALAudioDevice(session);
        textToSpeech = new ALTextToSpeech(session);
        motion = new ALMotion(session);
        ttsSay = session.service("ALTextToSpeech");

    }

    //-------------------------Methoden-------------------------\\

    //Gelenke-Steifheit auf die übergebene Fließkommazahl setzen
    public void setStiffnes(double stiffnes) throws InterruptedException, CallError {
        motion.setStiffnesses("Joints",stiffnes);
    }
    //Sprachausgabe ohne Gestik
    public void say(String args) throws Exception{
        ttsSay.call("say", args);
    }
    //Sprachausgabe mit Gestik
    public void gesture(String args)throws Exception{
        animatedSpeech.say(args);
    }
    //Rooter nimmt übergebene Haltung ein
    public void posture(String args)throws Exception{
        //robotPosture.applyPosture(args, (float) 1.0);
        robotPosture.goToPosture(args, (float) 1.0);
    }
    //Test ob die Verbindung noch steht, Roboter zwinkert wenn Verbindung vorhanden
    public void ping()throws Exception {
        boolean ping = ttsSay.<Boolean>call("ping").get();
        if (!ping) {
            System.out.println("Could not ping TTS");
        } else {
            System.out.println("Ping ok");
            ALLeds l = new ALLeds(session);
            l.off( 	"RightFaceLeds");
            Thread.sleep(1000);
            l.on( 	"RightFaceLeds");
        }
    }

    //-----------------------Getter/Setter-----------------------\\
    public void setVolume(int args)throws Exception{
        audioDevice.setOutputVolume(args);
    }

    public int getVolume()throws Exception{
        return audioDevice.getOutputVolume();
    }

    public void setSpeechSpeed(float args)throws Exception{
        textToSpeech.setParameter("speed", args);
    }

    public float getSpeechSpeed()throws Exception{
        return textToSpeech.getParameter("speed");
    }

    public void setVoicePitch(float args)throws Exception{
        textToSpeech.setParameter("pitchShift", args);
    }

    public float getVoicePitch()throws Exception{
        return textToSpeech.getParameter("pitchShift");
    }

}
