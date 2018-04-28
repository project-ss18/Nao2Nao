package Interview;

import java.util.ArrayList;

public class Question extends Action {

    private ArrayList<Answer> answerList = new ArrayList<Answer>();

    public Question(int id, Block v){
        this.id = id;
        this.block=v;
        v.addQuestion(this);
    }

    public Answer getAnswer(int index){
        return answerList.get(index);
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

    public int getId(){
        return this.id;
    }
}
