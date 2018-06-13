package controller;

import model.interview.Answer;
import model.interview.Block;
import model.interview.Interview;
import model.interview.Question;
import model.robot.Robot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InterviewPlayer implements Runnable {

    private Interview interview;
    private List<Robot> robots;

    private Thread interviewThread;
    private boolean pauseInterview;
    private boolean isRunning;

    private boolean goTo;
    private int goToBid;
    private int gotToQid;

    //--------Befehl-Tags-------\\
    private final String start = "^start(animations/Stand/Gestures/";
    private final char endTag = ')';
    private String wait = "^wait(animations/Stand/Gestures/";

    //------Konstruktor------\\
    public InterviewPlayer(Interview interview, ArrayList<Robot> robots) throws Exception {
        this.interview = interview;
        this.robots = robots;
        if(!(interview.getAnzahlTeilnehmer() == robots.size())) throw new Exception("Es wurden zu viele, oder zu wenige Roboter für das angegebene Interview definiert.");
        if(!checkRolesDefined(robots)) throw new Exception("Es wurden nicht für alle Roboter Rollen definiert.");
        pauseInterview=false;
        isRunning=false;
        goTo=false;
    }

    public void stopInterview(){
        if(isRunning) {
            interviewThread.stop();
        }
        isRunning = false;
    }

    public void startInterview(){
        if(!isRunning) {
            interviewThread = new Thread(this);
            isRunning=true;
            interviewThread.start();
        } else{
            pauseInterview=false;
        }
    }

    public void goToQuestion(int qID, int bID){
        goTo = true;
        goToBid=bID;
        gotToQid=qID;
        stopInterview();
        startInterview();
    }


    private boolean checkRolesDefined(ArrayList<Robot> robots) {
        // ----- Testen der Rollen der Roboter -----
        for(Robot CurrentRobot: robots) {
            if(CurrentRobot.getRole() == "" || CurrentRobot.getRole() == null) return false;
        }
        return true;
        // ----- Testen der Rollen der Roboter -----
    }
    private void doQuestion(Question question) {
        try {
            getRobot(question.getRole()).goToPosture(question.getPosture());
            getRobot(question.getRole()).setVolume(question.getVolume());
            getRobot(question.getRole()).setSpeechSpeed(question.getSpeechSpeed());
            getRobot(question.getRole()).setVoicePitch(question.getVoicePitch());
            getRobot(question.getRole()).animatedSay(start + question.getGesture() + endTag + question.getPhrase()  + wait + endTag);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void doAnswer(Answer selectedAnswer) {
        try {
            getRobot(selectedAnswer.getRole()).goToPosture(selectedAnswer.getPosture());
            getRobot(selectedAnswer.getRole()).setVolume(selectedAnswer.getVolume());
            getRobot(selectedAnswer.getRole()).setSpeechSpeed(selectedAnswer.getSpeechSpeed());
            getRobot(selectedAnswer.getRole()).setVoicePitch(selectedAnswer.getVoicePitch());
            getRobot(selectedAnswer.getRole()).animatedSay(start + selectedAnswer.getGesture() + endTag + selectedAnswer.getPhrase() + wait + endTag);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private Robot getRobot(String robotRole) {
        for(Robot currentRobot: robots) {
            String role = currentRobot.getRole();
            if(robotRole.equals(role)) {
                return currentRobot;
            }
        }
        return null;
    }
    private void pauseHandler() {
        try {
            while(pauseInterview == true) {
                Thread.sleep(1000);
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public void run() {
        try {
            for (Block currentBlock : interview.getBlockList()) {
                for (Question question : currentBlock.getQuestionList()) {
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
            }
        }finally {
            isRunning = false;
            for(Robot robot : robots){
                try {
                    robot.reset();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private Answer answershuffler(ArrayList<Answer> answerList) {
        int RandomeInteger = ThreadLocalRandom.current().nextInt(0, answerList.size());
        return answerList.get(RandomeInteger);
    }
    private ArrayList<String> getRobotSpeakAnswerOrder(Question selectedQuestion) {
        ArrayList<String> order = new ArrayList<String>();
        for(Answer currentAnswer: selectedQuestion.answerList) {
            if(!order.contains(currentAnswer.getRole())) {
                order.add(currentAnswer.getRole());
            }
        }
        return order;
    }
    private ArrayList<Answer> getRobotAnswers(String role, Question question){
        ArrayList<Answer> temp = new ArrayList<>();
        for(Answer answer : question.getAnswerList()){
            if(answer.getRole().equals(role)){
                temp.add(answer);
            }
        }
        return temp;
    }

    public void pauseInterview(){
        pauseInterview=true;
    }

}
