package model.interview;

import java.util.ArrayList;

public class Block {
    //------------------------Attribute------------------------\\
    private int bid;
    private Interview interview;

    //------------------------Variablen------------------------\\
    private ArrayList<Question> questionList = new ArrayList<Question>();

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
        return "BlockID: " + this.bid;
    }

    //----------------------Getter//Setter----------------------\\
    public int getBid()
    {
        return bid;
    }
    public void addQuestion(Question v){
        questionList.add(v);
    }
    public ArrayList<Question> getQuestionList() {
        return questionList;
    }

}
