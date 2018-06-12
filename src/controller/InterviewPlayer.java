package controller;

import model.interview.*;
import model.robot.Robot;

import java.util.ArrayList;
import java.util.List;

import java.lang.Runnable;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer implements Runnable{
    //----Attribute---\\
    public Interview interview;
    private List<Robot> robots;
    //----GOTO---\\
    private boolean gotoLocation = false;
    private int gotoBlock;
    private String gotoPhraseType;
    private int gotoPosition;
    //--------Befehl-Tags-------\\
    private final String start = "^start(animations/Stand/Gestures/";
    private final char endTag = ')';
    private String wait = "^wait(animations/Stand/Gestures/";

    private static int counterBlock = 1;

    //----Thread----\\
    private Thread currentInterview;
    private boolean threadStarted = false;
    private boolean pauseInterview = false;

    //------Konstruktor------\\
    public InterviewPlayer(Interview interview){
        this.interview = interview;
    }

    // ---------- Getter and Setter ----------
    public boolean isInterviewPaused() {
        return pauseInterview;
    }

    public void pauseInterview() {
        this.pauseInterview = true;
    }
    public void resumeInterview() {
        this.pauseInterview = false;
    }
    // ---------- Getter and Setter ----------


    private Robot getRobot(String RobotName) {
        for(Robot CurrentRobot: robots) {
            String CurrentRobotName = CurrentRobot.getName().toString();
            if(RobotName.equals(CurrentRobotName)) {
                return CurrentRobot;
            }
            }
        return null;
    }

    private Answer answershuffler(Block selectedBlock,int QuestionID, String RobotName) {
        ArrayList<Answer> PossibleAwnsers = new ArrayList<Answer>();
        for(Answer CurrentAnswer: selectedBlock.getQuestion(QuestionID).answerList)
        {
            if(CurrentAnswer.getRole() == RobotName)
            {
                PossibleAwnsers.add(CurrentAnswer);
            }
        }
        int RandomeInteger = ThreadLocalRandom.current().nextInt(0, PossibleAwnsers.size());
        return PossibleAwnsers.get(RandomeInteger);
    }

    private ArrayList<String> getRobotSpeakAnswerOrder(Question selectedQuestion) {
        ArrayList<String> Order = new ArrayList<String>();
        for(Answer CurrentAnswer: selectedQuestion.answerList)
        {
            if(!Order.contains(CurrentAnswer.getRole())) {
                Order.add(CurrentAnswer.getRole());
            }
        }
        return Order;
    }

    public void setGotoLocation(int GotoBlock, String PhraseType, int Position) {
    gotoBlock = GotoBlock;
    gotoPosition = Position;
    gotoPhraseType = PhraseType;
    gotoLocation = true;
    }

    public void startInterview(ArrayList<Robot> _Roboter) throws Exception {

        if(!testAllRolesAreDefined(_Roboter))
        {
            throw new Exception("Es wurden nicht für alle Roboter Rollen definiert.");
        }
        if(!(interview.getAnzahlTeilnehmer() == robots.size()))
        {
            throw new Exception("Es wurden zu viele, oder zu wenige Roboter für das angegebene Interview definiert.");
        }

        if(threadStarted == false)
        {
            System.out.println("Starting up new Interview-Thread");
            robots = null;
            robots = new ArrayList<Robot>();
            robots.addAll(_Roboter);

            currentInterview = null;
            currentInterview = new Thread(this);
            currentInterview.start();
            threadStarted = true;
        }
        else
        {
            System.out.println("Resume Interview-Thread");
            pauseInterview = false;
        }
    }
    private boolean testAllRolesAreDefined(ArrayList<Robot> _Roboter)
    {
        // ----- Testen der Rollen der Roboter -----
        for(Robot CurrentRobot: _Roboter)
        {
            if(CurrentRobot.getName() == "" || CurrentRobot.getName() == null)
            {
                return false;
            }
        }
        return true;
        // ----- Testen der Rollen der Roboter -----
    }
    // ---------- New Thread -----------
    // Playback Funktionen
    @Override public void run(){
        for(Block currentBlock : interview.getBlockList()) {
            try {
                if(gotoLocation)
                {
                   if(FindPosition(currentBlock))
                   {
                       gotoLocation = false;
                   }
                }
                if(!gotoLocation) {
                    PauseHandler();
                    DoQuestion(currentBlock);
                    PauseHandler();

                    ArrayList<String> AnswerOrder = getRobotSpeakAnswerOrder(currentBlock.getQuestion(1));
                    for (String RobotName : AnswerOrder) {
                        Answer selectedAnswer = answershuffler(currentBlock, 1, RobotName);
                        DoAnswer(RobotName, selectedAnswer);
                        PauseHandler();
                    }
                }
                }
                catch (Exception ex)
                {
                    System.out.println(ex.getMessage());
                    threadStarted = false;
                }
            //Block Counter für Posture
            counterBlock++;
        }
            //Nur für Vorführung implementiert muss später wieder entfernt und über XML umgesetzt werden!
        try {
            for (Robot CurrentRobot: robots)
            CurrentRobot.goToPosture("Sit");
        } catch (Exception e) {
            e.printStackTrace();
            threadStarted = false;
        }
        threadStarted = false;
        System.out.println("Interview-Thread finished");
    }

    private boolean FindPosition(Block currentBlock)
    {
        if(currentBlock.getBid() == gotoBlock)
        {
            if(gotoPhraseType == "Question")
            {
                if(currentBlock.getQuestion(1).getId() == gotoPosition)
                {
                    return true;
                }
            }
            if(gotoPhraseType == "Answer")
            {
                for(Answer CurrentAnswer: currentBlock.getQuestion(1).getAnswerList())
                {
                    if(CurrentAnswer.getId() == gotoPosition)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void DoQuestion(Block currentBlock)
    {
        try {
            getRobot(currentBlock.getQuestion(1).getRole()).setVolume(currentBlock.getQuestion(1).getVolume());
            getRobot(currentBlock.getQuestion(1).getRole()).setSpeechSpeed(currentBlock.getQuestion(1).getSpeechSpeed());
            getRobot(currentBlock.getQuestion(1).getRole()).setVoicePitch(currentBlock.getQuestion(1).getVoicePitch());
            getRobot(currentBlock.getQuestion(1).getRole()).animatedSay(start + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase()  + wait + endTag);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void DoAnswer(String robotName, Answer selectedAnswer)
    {
    try {
        getRobot(robotName).setVolume(selectedAnswer.getVolume());
        getRobot(robotName).setSpeechSpeed(selectedAnswer.getSpeechSpeed());
        getRobot(robotName).setVoicePitch(selectedAnswer.getVoicePitch());
        getRobot(robotName).animatedSay(start + selectedAnswer.getGesture() + endTag + selectedAnswer.getPhrase() + wait + endTag);
    }
    catch (Exception ex) {
        System.out.println(ex.getMessage());
    }
    }
    private void PauseHandler()
    {
        try {
            while(pauseInterview == true) {
                Thread.sleep(1000);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    // ---------- New Thread -----------
}
