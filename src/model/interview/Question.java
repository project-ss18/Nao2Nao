package model.interview;

import java.util.ArrayList;

public class Question extends Action {
    //------------------------Attribute------------------------\\\
    public ArrayList<Answer> answerList = new ArrayList<Answer>();


    //-----------------------Konstruktor-----------------------\\
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


    //-------------------------Methoden-------------------------\\
    public void addAnswer(Answer a){
        answerList.add(a);
    }
    //----------------------Getter//Setter----------------------\\
    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

    public int getAnswerCount() {
        return answerList.size();
    }

    public Answer getAnswer(int index){
        return answerList.get(index-1);
    }


    public void setPhrase(String phrase) {
        this.phrase=phrase;
    }

    public String getPhrase(){
        return phrase;
    }

    public void setGesture(String gesture) {
        this.gesture=gesture;
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
    public ArrayList<Answer> getAnswers(){
        return answerList;
    }


    public void setRole(String tempRole){
        role = tempRole;
    }
}
