package controller;


import com.aldebaran.qi.AnyObject;
import com.aldebaran.qi.Application;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;
import com.aldebaran.qi.helper.proxies.ALAudioDevice;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;

public class Connection{


    private static Application app;
    private Session session;
    private Future<Void> fut;
    private AnyObject ttsSay = null;
    private ALAnimatedSpeech animatedSpeech;
    private ALRobotPosture robotPosture;
    private ALAudioDevice audioDevice;
    private static boolean b = false;



    public Connection(String IP_ADRESS, String[] args) throws Exception{
            if(!b) {
                app = new Application(args);
                b = true;
            }
            session = new Session();
            fut = session.connect("tcp://" + IP_ADRESS + ":9559");
            fut.get();

            try {
                animatedSpeech = new ALAnimatedSpeech(session);
            } catch (Exception ex){}

            try {
                robotPosture = new ALRobotPosture(session);
            }catch (Exception ex){}

            try {
                audioDevice = new ALAudioDevice(session);
             }catch (Exception ex){}

            ttsSay = session.service("ALTextToSpeech");

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

    public void ping()throws Exception {
        boolean ping = ttsSay.<Boolean>call("ping").get();
        if (!ping) {
            System.out.println("Could not ping TTS");
        } else {
            System.out.println("Ping ok");
        }
    }

}
