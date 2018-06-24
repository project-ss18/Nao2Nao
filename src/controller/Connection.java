package controller;

import com.aldebaran.qi.*;
import com.aldebaran.qi.helper.proxies.*;

public class Connection{

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

    public Connection(String IP_ADRESS, String[] args) throws Exception{

        if(!applicationAvaliable) {
            app = new Application(args);
            applicationAvaliable = true;
        }
        session = new Session();
        fut = session.connect("tcp://" + IP_ADRESS + ":" + AppProperties.getRobotTCPPort());
        fut.get();
        animatedSpeech = new ALAnimatedSpeech(session);
        robotPosture = new ALRobotPosture(session);
        audioDevice = new ALAudioDevice(session);
        textToSpeech = new ALTextToSpeech(session);
        motion = new ALMotion(session);
        ttsSay = session.service("ALTextToSpeech");

    }
    public void setStiffnes(double stiffnes) throws InterruptedException, CallError {
        motion.setStiffnesses("Joints",stiffnes);
    }

    public void say(String args) throws Exception{
        ttsSay.call("say", args);
    }

    public void gesture(String args)throws Exception{
        animatedSpeech.say(args);
    }

    public void posture(String args)throws Exception{
        //robotPosture.applyPosture(args, (float) 1.0);
        robotPosture.goToPosture(args, (float) 1.0);
    }

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
}
