package interview;

import java.util.ArrayList;

public class Interview {

    public ArrayList<Block> blockList = new ArrayList<Block>();

    private int id;
    private String description;
    private String posture;
    private Block interviewBlock;


    public Interview(int id)
    {
        this.id=id;
    }

    public void addBlock(Block v)
    {
        blockList.add(v);
    }

    public ArrayList<Block> getBlockList() {
        return blockList;
    }

    public Block getBlock(int id) {
        for (Block v : blockList) {
            if (v.getBid() == id) {
                return v;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Block getInterviewBlock() {
        return interviewBlock;
    }

    public void setInterviewBlock(Block interviewBlock) {
        this.interviewBlock = interviewBlock;
    }

    public String getPosture() {
        return posture;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }

}
