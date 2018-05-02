package controller;


import com.aldebaran.qi.AnyObject;
import com.aldebaran.qi.Application;
import com.aldebaran.qi.Future;
import com.aldebaran.qi.Session;

public class Connection {

    private Application app;
    private Session session;
    private Future<Void> fut;
    AnyObject tts = null;

    public Connection(String IP_ADRESS, String[] args) throws Exception{
            app = new Application(args);
            session = new Session();
            fut = session.connect("tcp://" + IP_ADRESS + "9559");
            fut.get();

            tts = session.service("ALTextToSpeech");
    }

    public void say(String args) throws Exception{
        tts.call("say", args);
    }


    public void ping()throws Exception {
        boolean ping = tts.<Boolean>call("ping").get();
        if (!ping) {
            System.out.println("Could not ping TTS");
        } else {
            System.out.println("Ping ok");
        }
    }

}
