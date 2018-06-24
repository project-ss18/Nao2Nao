package model.interview;

public class Answer extends Action {
    //------------------------Attribute------------------------\\
    private Question question;

    //-----------------------Konstruktor-----------------------\\
    public Answer(int id, Question q, String posture, String gesture, String role, int volume, int speechSpeed, float voicePitch){
        this.id = id;
        question = q;
        block=question.getBlock();
        this.posture = posture;
        this.gesture = gesture;
        this.role = role;
        this.volume = volume;
        this.speechSpeed = speechSpeed;
        this.voicePitch = voicePitch;
        question.addAnswer(this);
    }
}
