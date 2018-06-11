package model.interview;

public class Answer extends Action {
    //------------------------Attribute------------------------\\
    private Question question;

    //-----------------------Konstruktor-----------------------\\
    public Answer(int id, Question q, int volume){
        this.volume = volume;
        this.id = id;
        question = q;
        question.addAnswer(this);
    }

    //----------------------Getter//Setter----------------------\\
    public void setPhrase(String phrase) {
        this.phrase=phrase;
    }

    public void setGesture(String gesture) {
        this.gesture=gesture;
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

    public String getRole(){
        return role;
    }

    public void setRole(String tempRole){
        role = tempRole;
    }

}
