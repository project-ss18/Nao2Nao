package controller;

import model.interview.Answer;
import model.interview.Block;
import model.interview.Interview;
import model.interview.Question;
import model.robot.Robot;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer implements Runnable {

    //------------------------Attribute------------------------\\
    private Interview interview;
    private view.InterviewPlayer interviewPlayer;
    private List<Robot> robots;
    private Thread interviewThread;
    private boolean pauseInterview;
    private boolean isRunning;
    private boolean goTo;
    private int goToBid;
    private int gotToQid;

    //-----------------------Befehl-Tags-----------------------\\
    private final String start = "^start(";
    private final char endTag = ')';
    private String wait = "^wait(";

    //-----------------------Konstruktor-----------------------\\
    public InterviewPlayer(Interview interview, ArrayList<Robot> robots,view.InterviewPlayer interviewPlayer) throws Exception {
        this.interview = interview;
        this.interviewPlayer = interviewPlayer;
        interviewPlayer.initProgressBar(interview.getBlockList().size());
        this.robots = robots;
        if(!(interview.getAnzahlTeilnehmer() == robots.size())) throw new Exception("Es wurden zu viele, oder zu wenige Roboter für das angegebene Interview definiert.");
        if(!checkRolesDefined(robots)) throw new Exception("Es wurden nicht für alle Roboter Rollen definiert.");
        pauseInterview=false;
        isRunning=false;
        goTo=false;
    }

    //-------------------------Methoden-------------------------\\

    //Stopppt das Interview
    public void stopInterview(){
        if(isRunning) {
            interviewThread.stop();
        }
        isRunning = false;
    }

    //Startet das Interview
    public void startInterview(){
        if(!isRunning) {
            interviewThread = new Thread(this);
            isRunning=true;
            interviewThread.start();
        } else{
            pauseInterview=false;
        }
    }

    //Springt zur ausgewählten Frage
    public void goToQuestion(int qID, int bID){
        goTo = true;
        goToBid=bID;
        gotToQid=qID;
        stopInterview();
        startInterview();
    }

    //Überprüft ob Rollen zugewiesen wurden
    private boolean checkRolesDefined(ArrayList<Robot> robots) {
        // ----- Testen der Rollen der Roboter -----
        for(Robot CurrentRobot: robots) {
            if(CurrentRobot.getRole() == "" || CurrentRobot.getRole() == null) return false;
        }
        return true;
        // ----- Testen der Rollen der Roboter -----
    }

    //Führt Frage aus
    private void doQuestion(Question question) {
        try {
            getRobot(question.getRole()).goToPosture(question.getPosture());
            getRobot(question.getRole()).setVolume(question.getVolume());
            getRobot(question.getRole()).setSpeechSpeed(question.getSpeechSpeed());
            getRobot(question.getRole()).setVoicePitch(question.getVoicePitch());
            getRobot(question.getRole()).animatedSay(start + AppProperties.getRobotActionPath() + question.getGesture() + endTag + question.getPhrase()  + wait + AppProperties.getRobotActionPath() + endTag);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Führt Antwort aus
    private void doAnswer(Answer selectedAnswer) {
        try {
            getRobot(selectedAnswer.getRole()).goToPosture(selectedAnswer.getPosture());
            getRobot(selectedAnswer.getRole()).setVolume(selectedAnswer.getVolume());
            getRobot(selectedAnswer.getRole()).setSpeechSpeed(selectedAnswer.getSpeechSpeed());
            getRobot(selectedAnswer.getRole()).setVoicePitch(selectedAnswer.getVoicePitch());
            getRobot(selectedAnswer.getRole()).animatedSay(start + selectedAnswer.getGesture() + endTag + selectedAnswer.getPhrase() + wait + endTag);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }
    }

    //
    private Robot getRobot(String robotRole) {
        for(Robot currentRobot: robots) {
            String role = currentRobot.getRole();
            if(robotRole.equals(role)) {
                return currentRobot;
            }
        }
        return null;
    }

    //Pausiert das Interview
    private void pauseHandler() {
        try {
            while(pauseInterview) {
                Thread.sleep(1000);
            }
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void run() {
        try {
            int i = 1;
            for (Block currentBlock : interview.getBlockList()) {
                for (Question question : currentBlock.getQuestionList()) {
                    interviewPlayer.setProgressBar(i);
                    if (goTo){
                        if((currentBlock.getBid() == goToBid) && (question.getId() == gotToQid)){
                            goTo=false;
                        }
                    }
                    if(!goTo) {
                        pauseHandler();
                        doQuestion(question);
                        ArrayList<String> answerOrder = getRobotSpeakAnswerOrder(question);
                        for (String role : answerOrder) {
                            ArrayList<Answer> robotAnswers = getRobotAnswers(role, question);
                            pauseHandler();
                            doAnswer(answershuffler(robotAnswers));
                        }
                    }
                }
                i++;
            }
        }finally {
            isRunning = false;
            for(Robot robot : robots){
                try {
                    robot.reset(robot);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    }

    //Zufällige Auswahl der Antworten
    private Answer answershuffler(ArrayList<Answer> answerList) {
        int RandomeInteger = ThreadLocalRandom.current().nextInt(0, answerList.size());
        return answerList.get(RandomeInteger);
    }

    //
    private ArrayList<String> getRobotSpeakAnswerOrder(Question selectedQuestion) {
        ArrayList<String> order = new ArrayList<String>();
        for(Answer currentAnswer: selectedQuestion.getAnswerList()) {
            if(!order.contains(currentAnswer.getRole())) {
                order.add(currentAnswer.getRole());
            }
        }
        return order;
    }

    //
    private ArrayList<Answer> getRobotAnswers(String role, Question question){
        ArrayList<Answer> temp = new ArrayList<>();
        for(Answer answer : question.getAnswerList()){
            if(answer.getRole().equals(role)){
                temp.add(answer);
            }
        }
        return temp;
    }


    //
    public void pauseInterview(){
        pauseInterview=true;
    }



}
