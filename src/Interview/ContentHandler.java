package Interview;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;



public class ContentHandler implements org.xml.sax.ContentHandler {

    /*private ArrayList<InterviewPlayer> allInterviewModels = new ArrayList<InterviewPlayer>();
    private ArrayList<Block> allBlocks = new ArrayList<Block>();
    private ArrayList<Action> allInterviewActions = new ArrayList<Action>();*/

    private String currentValue;

    private static Interview interview;
    private static ArrayList<Block> blockList = new ArrayList<Block>();
    private static ArrayList<Question> questionList = new ArrayList<Question>();
    private static ArrayList<Answer> answerList = new ArrayList<Answer>();

    static int blockCounter=0;
    static int questCounter=0;
    static int answerCounter=0;
    static boolean type = false;//false == question


    // Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable
    // gespeichert
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        currentValue = new String(ch, start, length);
    }

    public static Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }


    // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt     ==>Objekt
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
    {
        if (localName.equals("interview"))
        {
            interview = new Interview(Integer.parseInt(atts.getValue("iid")));
        }

        if (localName.equals("block"))
        {
            blockCounter++;
            blockList.add (new Block(Integer.parseInt(atts.getValue("bid")), interview));

        }

        if (localName.equals("question"))
        {
            type = false;
            questCounter++;
            questionList.add(new Question(Integer.parseInt(atts.getValue("qid")),blockList.get(blockCounter-1)));
        }

        if (localName.equals("answer"))
        {
            type=true;
            answerCounter++;
            answerList.add(new Answer(Integer.parseInt(atts.getValue("aid")),questionList.get(questCounter-1)));
        }
    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt       ==>Attribute von Objekt
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {

        // Beschreibung setzen
        if (localName.equals("description")) {
            interview.setDescription(currentValue);
        }


        if (localName.equals("text")) {
            if(type){
                answerList.get(answerCounter-1).setPhrase(currentValue);
            }else if(type==false){
                questionList.get(questCounter-1).setPhrase(currentValue);
            }
        }

    }



    public void endDocument() throws SAXException {}
    public void endPrefixMapping(String prefix) throws SAXException {}
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {}
    public void processingInstruction(String target, String data)
            throws SAXException {}
    public void setDocumentLocator(Locator locator) {  }
    public void skippedEntity(String name) throws SAXException {}
    public void startDocument() throws SAXException {}
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {}
}