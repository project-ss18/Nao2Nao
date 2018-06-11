package model.interview;

import java.util.ArrayList;

public class Question extends Action {
    //------------------------Attribute------------------------\\\
    public ArrayList<Answer> answerList = new ArrayList<Answer>();


    //-----------------------Konstruktor-----------------------\\
    public Question(int id, Block v, int volume){
        this.id = id;
        this.block=v;
        this.volume = volume;
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

    public ArrayList<Answer> getAnswers(){
        return answerList;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String tempRole){
        role = tempRole;
    }
}
