package controller;

import model.interview.*;
import model.robot.Robot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.lang.Runnable;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer implements Runnable{
    //----Attribute---\\
    public Interview interview;
    private List<Robot> robots;

    //--------Befehl-Tags-------\\
    private final String start = "^start(animations/Stand/Gestures/";
    private final char endTag = ')';
    private String wait = "^wait(animations/Stand/Gestures/";

    private static int counterBlock = 1;

    //----Thread----\\
    private Thread currentInterview;
    private boolean threadStarted = false;
    private boolean pauseInterview = false;

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

    public InterviewPlayer(Interview interview){
        this.interview = interview;
    }

    private Robot getRobot(int RobotID) {
        for(Robot CurrentRobot: robots) {
            if(CurrentRobot.get_ID() == RobotID) {
                return CurrentRobot;
            }
            }
        return null;
    }

    private Answer answershuffler(Block selectedBlock,int QuestionID, int RobotID) {
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

    private ArrayList<Integer> getRobotSpeakAnswerOrder(Question selectedQuestion) {
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
            robots = new ArrayList<Robot>();
            robots.addAll(_Roboter);

            currentInterview = new Thread(this);
            currentInterview.start();
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

    // ---------- New Thread -----------
    // Playback Funktionen
    @Override public void run(){
        for(Block currentBlock : interview.getBlockList()) {
            try {
                // Frage auslesen und abspielen
                    getRobot(currentBlock.getQuestion(1).getId()).setVolume(currentBlock.getQuestion(1).getVolume());
                    getRobot(currentBlock.getQuestion(1).getId()).animatedSay(start + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase()  + wait + endTag);
                // Frage auslesen und abspielen

                // Antwort auswählen und abspielen
                // Suchen, wer als erstes antwortet
                    ArrayList<Integer> AnswerOrder = getRobotSpeakAnswerOrder(currentBlock.getQuestion(1));
                // Suchen, wer als erstes antwortet
                // Roboter, die Antworten durchlaufen
                    for(int RobotID: AnswerOrder) {
                        Answer selectedAnswer = answershuffler(currentBlock,1,RobotID);
                        getRobot(RobotID).animatedSay(start + selectedAnswer.getGesture() + endTag + selectedAnswer.getPhrase() + wait + endTag);
                    }
                // Roboter, die Antworten durchlaufen
                // Antwort auswählen und abspielen



                while(pauseInterview == true) {
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
            for (Robot CurrentRobot: robots)
            CurrentRobot.goToPosture("Sit");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // ---------- New Thread -----------
}
