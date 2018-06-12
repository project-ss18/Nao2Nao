package model.interview;

public abstract class Action {
    //------------------------Attribute------------------------\\\
    protected int id;
    protected String role;
    protected String phrase;
    protected String gesture;
    protected Block block;
    protected int volume;
    protected int speechSpeed;
    protected float voicePitch;

    public Block getBlock() {
        return block;
    }

}