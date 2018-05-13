package controller;

import interview.Action;
import interview.Block;
import interview.ContentHandler;
import interview.Interview;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import userInterface.Robot;

import javax.xml.stream.FactoryConfigurationError;
import java.lang.Runnable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer implements Runnable{

    public Interview interview;
    public File XMLFile;
    private final static String PATH = "./res/";
    private String start = "^start(animations/Stand/Gestures/";
    private String endTag = ")";
    private String wait = "^wait(animations/Stand/Gestures/";

    private boolean pauseInterview = false;
    private Robot Roboter1;
    private Robot Roboter2;
    private Thread AktuellesInterview;
    private boolean ThreadStarted = false;

    public InterviewPlayer(String FileName) {
        XMLFile = new File(FileName);
        initialize(FileName);
    }

    private void initialize(String FileName) {
        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur resources Datei
            FileReader reader = new FileReader(FileName);
            InputSource inputSource = new InputSource(reader);


            // DTD kann optional übergeben werden
            // inputSource.setSystemId("X:\\personen.dtd");

            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new ContentHandler());

            // Parsen wird gestartet
            xmlReader.parse(inputSource);

          interview = ContentHandler.getInterview();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    // Playback Funktionen
    @Override public void run(){
        for(Block currentBlock : interview.getBlockList()) {
            try
            {
                // Frage auslesen und abspielen
                Roboter1.animatedSay(start + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase() + wait + endTag);
                int AnswerCount = currentBlock.getQuestion(1).getAnswerCount();
                // Frage auslesen und abspielen

                // Antwort auswählen und abspielen
                int AnswerNumber = ThreadLocalRandom.current().nextInt(1, AnswerCount + 1);
                Roboter2.animatedSay(start + currentBlock.getQuestion(1).getAnswer(AnswerNumber).getGesture() + endTag + currentBlock.getQuestion(1).getAnswer(AnswerNumber).getPhrase() + wait + endTag);
                // Antwort auswählen und abspielen

                while(pauseInterview == true)
                {
                    Thread.sleep(1000);
                }
            }
            catch (Exception ex)
            {

            }


        }
    }


    public void startInterview(Robot _Roboter1, Robot _Roboter2) throws Exception {
        if(ThreadStarted == false)
        {
            Roboter1 = _Roboter1;
            Roboter2 = _Roboter2;

            AktuellesInterview = new Thread(this);
            AktuellesInterview.start();
            ThreadStarted = true;

        }
        else
        {
            pauseInterview = false;
        }

    }
    public void PauseInterview() {
        pauseInterview = true;
    }

    public static void print() {
        for (InterviewPlayer interviewPlayer : getAllInterviews()){
            System.out.println("Interview: '" + interviewPlayer.XMLFile.getName() + "'");
        }
    }
    // print
    // Static Functions
    public static List<InterviewPlayer> getAllInterviews()
    {
        ArrayList<InterviewPlayer> InterviewObjects = new ArrayList<InterviewPlayer>();
        File folder = new File(PATH);
        File[] listofInterviews = folder.listFiles();

        for(File currentInterview : listofInterviews)
        {
            if(currentInterview.isFile() && currentInterview.getName().endsWith(".xml"))
            {
               InterviewObjects.add(new InterviewPlayer(PATH + currentInterview.getName()));
            }
        }
        return InterviewObjects;
    }

    public static InterviewPlayer findInterview(int ID, List<InterviewPlayer> alleInterviewPlayers) {
        return new InterviewPlayer("");
    }
    public static InterviewPlayer findInterview(String Name, List<InterviewPlayer> alleInterviewPlayers) {
        return new InterviewPlayer("");
    }
    // Static Functions

}
