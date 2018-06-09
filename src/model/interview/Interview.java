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

    public ArrayList<Block> blockList = new ArrayList<Block>();


    private int id;
    private String description;
    private String posture;
    private Block interviewBlock;
    private final String PATH = "./res/";
    private String xsdFile = "interview_xsd.xsd";
    private int qCounter;


    public Interview(int id) {
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

    public void checkSyntax(String xmlFile) {
        //XML-Syntax Check mithilfe eines XSD Schema
        File schemaFile = new File(PATH + xsdFile);
        Source xmlFileSource = new StreamSource(new File(PATH + xmlFile));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSource);
            System.out.println("Die XML Datei '" + xmlFile + "' ist valide");
        } catch (SAXException e) {
            System.out.println(xmlFileSource.getSystemId() + " Die XML Datei '"+ xmlFile + "' ist nicht valide!, Error:" + e);
        } catch (IOException e) {
        }
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

    public int getqCounter() {
        return qCounter;
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
