package model.interview;

public class Answer extends Action {

    private Question question;

    public Answer(int id, Question q, int volume, int speechSpeed, float voicePitch){
        this.id = id;
        question = q;
        this.volume = volume;
        this.speechSpeed = speechSpeed;
        this.voicePitch = voicePitch;
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

    public int getVolume() {return this.volume;}

    public int getSpeechSpeed(){return this.speechSpeed;}

    public float getVoicePitch() {return this.voicePitch;}
}
