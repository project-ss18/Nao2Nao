package controller;
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
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer implements Runnable{

    public Interview interview;
    public File XMLFile;
    private final static String PATH = "./res/";
    private String start = "^start(animations/Stand/Gestures/";
    private String endTag = ")";
    private String wait = "^wait(animations/Stand/Gestures/";
    private static int counterBlock = 1;
    private boolean pauseInterview = false;
    private ArrayList<Robot> roboter;

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

    private Robot getRobot(int RobotID)
    {
        for(Robot CurrentRobot: roboter)
        {
            if(CurrentRobot.get_ID() == RobotID) {
                return CurrentRobot;
            }
            }
        return null;
    }

    private Answer answershuffler(Block selectedBlock,int QuestionID, int RobotID)
    {
        ArrayList<Answer> PossibleAwnsers = new ArrayList<Answer>();
        for(Answer CurrentAnswer: selectedBlock.getQuestion(QuestionID).answerList)
        {
            if(CurrentAnswer.getId() == RobotID)
            {
                PossibleAwnsers.add(CurrentAnswer);
            }
        }
        return PossibleAwnsers.get(ThreadLocalRandom.current().nextInt(1, PossibleAwnsers.size() + 1));
    }

    private ArrayList<Integer> getRobotSpeakAnswerOrder(Question selectedQuestion)
    {
        ArrayList<Integer> Order = new ArrayList<Integer>();
        for(Answer CurrentAnswer: selectedQuestion.answerList)
        {
            if(!Order.contains(CurrentAnswer.getId())) {
                Order.add(CurrentAnswer.getId());
            }
        }
        return Order;
    }


    public void startInterview(ArrayList<Robot> _Roboter) throws Exception {
        if(threadStarted == false)
        {
            roboter = new ArrayList<Robot>();
            roboter.addAll(_Roboter);

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
        for (String interviewDescription : getAllInterviewNames()){
            System.out.println("Interview: '" + interviewDescription + "'");
        }
    }
    // print
    // Static Functions
    public static List<String> getAllInterviewNames()
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
                    getRobot(currentBlock.getQuestion(1).getId()).setVolume(currentBlock.getQuestion(1).getVolume());
                    getRobot(currentBlock.getQuestion(1).getId()).animatedSay(start + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase()  + wait + endTag);
                // Frage auslesen und abspielen

                // Antwort auswählen und abspielen
                // Suchen, wer als erstes antwortet
                    ArrayList<Integer> AnswerOrder = getRobotSpeakAnswerOrder(currentBlock.getQuestion(1));
                // Suchen, wer als erstes antwortet
                // Roboter, die Antworten durchlaufen
                    for(int RobotID: AnswerOrder)
                    {
                        Answer selectedAnswer = answershuffler(currentBlock,1,RobotID);
                        getRobot(RobotID).animatedSay(start + selectedAnswer.getGesture() + endTag + selectedAnswer.getPhrase() + wait + endTag);
                    }
                // Roboter, die Antworten durchlaufen
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
            //Block Counter für Posture
            counterBlock++;
        }
            //Nur für Vorführung implementiert muss später wieder entfernt und über XML umgesetzt werden!
        try {
            for (Robot CurrentRobot: roboter)
            CurrentRobot.goToPosture("Sit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------- New Thread -----------
}
