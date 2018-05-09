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
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer {

    public Interview interview;
    public File XMLFile;
    private final static String PATH = "./res/";
    private String standGestures = "^start(animations/Stand/Gestures/";
    private String standBodytalk = "^start(animations/Stand/BodyTalk/";
    private String sitBodytalk = "^start(animations/Sit/BodyTalk/";
    private String endTag = ")";
    private String wait = "^wait(animations/Stand/Gestures/";

    public InterviewPlayer(String FileName) {
        XMLFile = new File(FileName);
        initialize(FileName);
    }

    private void initialize(String FileName) {
        try {

            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            //------------------- Pfad XML-Datei -------------------//
            FileReader reader = new FileReader(FileName);
            InputSource inputSource = new InputSource(reader);

            xmlReader.setContentHandler(new ContentHandler());

            //------------------- Start Parser -------------------//
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
    public void startInterview(Robot Roboter1, Robot Roboter2) throws Exception {
        for(Block currentBlock : interview.getBlockList()) {

            Roboter1.goToPosture(interview.getPosture());
            Roboter2.goToPosture(interview.getPosture());

            Roboter1.animatedSay(standBodytalk + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase() + wait + endTag);
            int AnswerCount = currentBlock.getQuestion(1).getAnswerCount();
            Thread.sleep(2000);

            int AnswerNumber = ThreadLocalRandom.current().nextInt(1, AnswerCount + 1);
            Roboter2.animatedSay(standGestures + currentBlock.getQuestion(1).getAnswer(AnswerNumber).getGesture() + endTag + currentBlock.getQuestion(1).getAnswer(AnswerNumber).getPhrase() + wait + endTag);
            Thread.sleep(2000);

            // Antwort auswählen und abspielen
        }
    }
    public void PauseInterview() {
    }
    public static void print() {
        for (InterviewPlayer interviewPlayer : getAllInterviews()){
            System.out.println("Interview: '" + interviewPlayer.XMLFile.getName() + "'");
        }
    }

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
}
