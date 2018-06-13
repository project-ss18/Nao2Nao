package model.interview;

import java.util.ArrayList;

public class Block {
    //------------------------Attribute------------------------\\
    private Interview interview;
    private ArrayList<Question> questionList = new ArrayList<Question>();
    private int bid;


    //-----------------------Konstruktor-----------------------\\
    public Block(int blockID, Interview interview) {
        bid = blockID;
        this.interview = interview;
        this.interview.addBlock(this);
    }

    //-------------------------Methoden-------------------------\\
    @Override
    public String toString()
    {
        return "bid: " + this.bid;
    }

    public void addQuestion(Question v){
        questionList.add(v);
    }

    //----------------------Getter//Setter----------------------\\
    public Question getQuestion(int id){
        for(Question q: questionList){
            if(q.getId()==id){
                return q;
            }
        }
        return null;
    }

    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

    public int getBid()
    {
        return bid;
    }

    public void setBid(int bid)
    {
        this.bid = bid;
    }


}
