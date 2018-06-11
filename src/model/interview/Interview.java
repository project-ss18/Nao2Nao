package model.interview;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Interview {
    public static ArrayList<Interview> getAllInterviews() {
        return allInterviews;
    }

    //------------------------Attribute------------------------\\
    private static ArrayList<Interview> allInterviews = new ArrayList<Interview>();

    private ArrayList<Block> blockList = new ArrayList<Block>();
    private int id;
    private String description;
    private Block interviewBlock;
    private int anzahlTeilnehmer;

    public Interview(int id, int anzahlTeilnehmer) {
        this.id=id;
        this.anzahlTeilnehmer = anzahlTeilnehmer;
    }

    public void addBlock(Block v) {
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

    public int getAnzahlTeilnehmer() {
        return anzahlTeilnehmer;
    }

    public void setAnzahlTeilnehmer(int anzahlTeilnehmer) {
        this.anzahlTeilnehmer = anzahlTeilnehmer;
    }

    public String[] toStringArray(){
        int questCounter=0;
        for(Block b : blockList){
            for(Question q : b.getQuestionList()){
                questCounter++;
            }
        }
        return new String[]{Integer.toString(this.id), this.getDescription(), Integer.toString(questCounter),"2"};
    }
}
