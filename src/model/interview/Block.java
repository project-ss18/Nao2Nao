package model.interview;

import java.util.ArrayList;

public class Block {

    private Interview interview;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private int bid;
    private String posture;


    public Block(int blockID, Interview interview) {
        bid =blockID;
        this.interview = interview;
        this.interview.addBlock(this);
    }

    public void addQuestion(Question v){
        questionList.add(v);
    }
    public Question getQuestion(int id){
        for(Question q: questionList){
            if(q.getId()==id){
                return q;
            }
        }
        return null;
    }

    public int getBid()
    {
        return bid;
    }

    public void setBid(int bid)
    {
        this.bid = bid;
    }

    @Override
    public String toString()
    {
        return "bid: " + this.bid;
    }


    public String getPosture() {
        return posture;
    }

    public void setPosture(String posture) {
        this.posture = posture;
    }
}
