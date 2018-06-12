import controller.InterviewLoader;
import model.robot.Robot;
import model.interview.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import model.robot.Robot;
import java.lang.Runnable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestRobot {
    public static void main(String[] args) throws Exception {
       Robot r = new Robot("141.28.75.148", "1");
        //r.goToPosture("SitRelax");
        //r.setVolume(80);
        //r.setSpeechSpeed(200);
        //r.say("Hallo, was geht ab, Bitch!");



        //Interview i1 = InterviewLoader.initializeInterview("interview.xml");
        //System.out.println(i1.getAnzahlTeilnehmer());




        r.setVolume(60);
        r.setSpeechSpeed(100);
        r.setVoicePitch(0);
        r.say("Hallo das ist ein Test");

     //   System.out.println("SpeedSpeech: " + r.getSpeedSpeech());
    }
}
