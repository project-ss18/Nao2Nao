package model.interview;

import java.util.ArrayList;

public class Question extends Action {

    public ArrayList<Answer> answerList = new ArrayList<Answer>();

    public Question(int id, Block v, String gesture, String role, int volume, int speechSpeed, float voicePitch){
        this.id = id;
        this.block=v;
        this.gesture = gesture;
        this.role = role;
        this.volume = volume;
        this.speechSpeed = speechSpeed;
        this.voicePitch = voicePitch;
        v.addQuestion(this);
    }

    public int getAnswerCount() {
        return answerList.size();
    }

    public Answer getAnswer(int index){
        return answerList.get(index-1);
    }

    public void addAnswer(Answer a){
        answerList.add(a);
    }

    public void setPhrase(String phrase) {
        this.phrase=phrase;
    }

    public String getPhrase(){
        return phrase;
    }

    public String getGesture(){
        return gesture;
    }

    public int getId(){
        return this.id;
    }

    public int getVolume() {return this.volume;}

    public int getSpeechSpeed(){return this.speechSpeed;}

    public float getVoicePitch() {return this.voicePitch;}

    public String getRole(){
        return this.role;
    }

}
