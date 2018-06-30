package model.interview;

import java.util.ArrayList;

public class Question extends Action {
    //------------------------Attribute------------------------\\\
    private Block block;
    private ArrayList<Answer> answerList = new ArrayList<Answer>();

    //-----------------------Konstruktor-----------------------\\
    public Question(int id, Block block, String posture, String gesture, String role, int volume, int speechSpeed, float voicePitch){
        this.id = id;
        this.block=block;
        this.posture = posture;
        this.gesture = gesture;
        this.role = role;
        this.volume = volume;
        this.speechSpeed = speechSpeed;
        this.voicePitch = voicePitch;
        block.addQuestion(this);
    }
    //----------------------Getter/Setter----------------------\\
    public void addAnswer(Answer a){
        answerList.add(a);
    }
    public ArrayList<Answer> getAnswerList() {
        return answerList;
    }
    public Block getBlock() {
        return block;
    }

}
