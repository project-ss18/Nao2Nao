package model.interview;

import java.util.ArrayList;

public class Question extends Action {
    //------------------------Attribute------------------------\\\
    public ArrayList<Answer> answerList = new ArrayList<Answer>();

    //-----------------------Konstruktor-----------------------\\
    public Question(int id, Block v, String posture, String gesture, String role, int volume, int speechSpeed, float voicePitch){
        this.id = id;
        this.block=v;
        this.posture = posture;
        this.gesture = gesture;
        this.role = role;
        this.volume = volume;
        this.speechSpeed = speechSpeed;
        this.voicePitch = voicePitch;
        v.addQuestion(this);
    }
    //----------------------Getter/Setter----------------------\\
    public void addAnswer(Answer a){
        answerList.add(a);
    }
    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }

}
