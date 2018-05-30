package controller;
import model.interview.Block;
import model.interview.ContentHandler;
import model.interview.Interview;
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

public class InterviewPlayer implements Runnable{

    public Interview interview;
    public File XMLFile;
    private final static String PATH = "./res/";
    private String start = "^start(animations/Stand/Gestures/";
    private String endTag = ")";
    private String wait = "^wait(animations/Stand/Gestures/";


    private boolean pauseInterview = false;
    private Robot roboter1;
    private Robot roboter2;
    private Thread rurrentInterview;
    private boolean threadStarted = false;

    // ---------- Getter and Setter ----------
    public boolean isInterviewPaused() {
        return pauseInterview;
    }

    public void pauseInterview() {
        this.pauseInterview = true;
    }
    public void resumeInterview(boolean pauseInterview) {
        this.pauseInterview = false;
    }
    // ---------- Getter and Setter ----------

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

    public void startInterview(Robot _Roboter1, Robot _Roboter2) throws Exception {
        if(threadStarted == false)
        {
            roboter1 = _Roboter1;
            roboter2 = _Roboter2;

            rurrentInterview = new Thread(this);
            rurrentInterview.start();
            threadStarted = true;
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
        for (String interviewDescription : getAllInterviewDescriptions()){
            System.out.println("Interview: '" + interviewDescription + "'");
        }
    }
    // print
    // Static Functions
    public static List<String> getAllInterviewDescriptions()
    {
        ArrayList<String> InterviewObjects = new ArrayList<String>();
        File folder = new File(PATH);
        File[] listofInterviews = folder.listFiles();

        for(File currentInterview : listofInterviews)
        {
            if(currentInterview.isFile() && currentInterview.getName().endsWith(".xml"))
            {
                //InterviewObjects.add(new InterviewPlayer(PATH + currentInterview.getName()));
                String fileNameWithOutExtension = currentInterview.getName();
                int index = fileNameWithOutExtension.lastIndexOf('.');
                if (index != -1)
                    fileNameWithOutExtension = fileNameWithOutExtension.substring(0, index);

                InterviewObjects.add(fileNameWithOutExtension);
            }
        }
        return InterviewObjects;
    }
    // Static Functions


    // ---------- New Thread -----------
    // Playback Funktionen
    @Override public void run(){
        for(Block currentBlock : interview.getBlockList()) {
            try
            {
                // Frage auslesen und abspielen
                roboter1.animatedSay(start + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase() + wait + endTag);
                int AnswerCount = currentBlock.getQuestion(1).getAnswerCount();
                // Frage auslesen und abspielen

                // Antwort auswählen und abspielen
                int AnswerNumber = ThreadLocalRandom.current().nextInt(1, AnswerCount + 1);
                roboter2.animatedSay(start + currentBlock.getQuestion(1).getAnswer(AnswerNumber).getGesture() + endTag + currentBlock.getQuestion(1).getAnswer(AnswerNumber).getPhrase() + wait + endTag);
                // Antwort auswählen und abspielen

                while(pauseInterview == true)
                {
                    Thread.sleep(1000);
                }
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    // ---------- New Thread -----------
}
