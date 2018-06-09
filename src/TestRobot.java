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
        Robot r = new Robot("141.28.75.148", 1, args);
        //r.goToPosture("SitRelax");
        //r.setVolume(80);

        r.setSpeechSpeed(50);

        r.say("Hallo, was geht ab, Bitch!");



        /*
        r.setVolume(50);
        r.say("Hallo, jetzt müsste ich leise sein");
        */


     //   System.out.println("SpeedSpeech: " + r.getSpeedSpeech());
    }
}
