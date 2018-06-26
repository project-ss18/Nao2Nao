package model.interview;

public abstract class Action {
    //------------------------Attribute------------------------\\\
    protected int id;
    protected Block block;
    protected String role;
    protected String phrase;
    protected String posture;
    protected String gesture;
    protected int volume;
    protected int speechSpeed;
    protected float voicePitch;

    //------------------------Getter/Setter------------------------\\\

    public int getId(){
        return this.id;
    };
    public Block getBlock() {
        return block;
    }
    public String getRole(){
        return this.role;
    }
    public void setRole(String tempRole){
        role = tempRole;
    }
    public void setPhrase(String phrase) {
        this.phrase=phrase;
    }
    public String getPhrase() {
        return phrase;
    }
    public String getPosture() {
        return posture;
    }
    public String getGesture(){
        return gesture;
    }
    public void setGesture(String gesture) {
        this.gesture=gesture;
    }
    public int getVolume() {return this.volume;}
    public int getSpeechSpeed(){return this.speechSpeed;}
    public float getVoicePitch() {return this.voicePitch;}

}