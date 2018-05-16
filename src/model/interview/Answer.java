package model.interview;

public class Answer extends Action {

    private Question question;

    public Answer(int id, Question q){
        this.id = id;
        question = q;
        question.addAnswer(this);
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
}
