package controller;


import com.aldebaran.qi.AnyObject;
import com.aldebaran.qi.Application;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;
import com.aldebaran.qi.helper.proxies.ALAnimatedSpeech;

public class Connection{


    private static Application app;
    private Session session;
    private Future<Void> fut;
    private AnyObject ttsSay = null;
    private AnyObject ttsGesture = null;
    private ALAnimatedSpeech animatedSpeech;
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

            ttsSay = session.service("ALTextToSpeech");
            ttsGesture = session.service("ALAnimatedSpeech");

    }







    public void say(String args) throws Exception{
        ttsSay.call("say", args);
    }

    public void gesture(String args)throws Exception{
        animatedSpeech.say(args);
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
