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
    public void resumeInterview(boolean pauseInterview) {
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

    public void startInterview(ArrayList<Robot> _Roboter) throws Exception {

        // ----- Testen der Rollen der Roboter -----

        for(Robot CurrentRobot: _Roboter)
        {
            if(CurrentRobot.getName() == "" || CurrentRobot.getName() == null)
            {
                throw new Exception("Roboter Rolle wurde nicht definiert!");
            }
        }

        // ----- Testen der Rollen der Roboter -----

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

    // ---------- New Thread -----------
    // Playback Funktionen
    @Override public void run(){
        for(Block currentBlock : interview.getBlockList()) {
            try {
                // Frage auslesen und abspielen
                    getRobot(currentBlock.getQuestion(1).getRole()).setVolume(currentBlock.getQuestion(1).getVolume());
                    getRobot(currentBlock.getQuestion(1).getRole()).setSpeechSpeed(currentBlock.getQuestion(1).getSpeechSpeed());
                    getRobot(currentBlock.getQuestion(1).getRole()).setVoicePitch(currentBlock.getQuestion(1).getVoicePitch());

                    getRobot(currentBlock.getQuestion(1).getRole()).animatedSay(start + currentBlock.getQuestion(1).getGesture() + endTag + currentBlock.getQuestion(1).getPhrase()  + wait + endTag);
                // Frage auslesen und abspielen

                // Antwort auswählen und abspielen
                // Suchen, wer als erstes antwortet
                    ArrayList<String> AnswerOrder = getRobotSpeakAnswerOrder(currentBlock.getQuestion(1));
                // Suchen, wer als erstes antwortet
                // Roboter, die Antworten durchlaufen
                    for(String RobotName: AnswerOrder) {
                        Answer selectedAnswer = answershuffler(currentBlock,1,RobotName);
                        getRobot(RobotName).setVolume(selectedAnswer.getVolume());
                        getRobot(RobotName).setSpeechSpeed(selectedAnswer.getSpeechSpeed());
                        getRobot(RobotName).setVoicePitch(selectedAnswer.getVoicePitch());

                        getRobot(RobotName).animatedSay(start + selectedAnswer.getGesture() + endTag + selectedAnswer.getPhrase() + wait + endTag);
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
    }
    // ---------- New Thread -----------
}
