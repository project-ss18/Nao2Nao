package interview;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;


public class ContentHandler implements org.xml.sax.ContentHandler {

    private static Interview interview;
    private static ArrayList<Block> blockList = new ArrayList<Block>();
    private static ArrayList<Question> questionList = new ArrayList<Question>();
    private static ArrayList<Answer> answerList = new ArrayList<Answer>();

    private String currentValue;
    static int blockCounter=0;
    static int questCounter=0;
    static int answerCounter=0;

    private static boolean type = false;//false == question


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


    // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (localName.equals("interview")) {
            interview = new Interview(Integer.parseInt(atts.getValue("iid")));
        }

        if (localName.equals("block")) {
            blockCounter++;
            blockList.add (new Block(Integer.parseInt(atts.getValue("bid")), interview));
        }

        if (localName.equals("question")) {
            type = false;
            questCounter++;
            questionList.add(new Question(Integer.parseInt(atts.getValue("qid")),blockList.get(blockCounter-1)));
        }

        if (localName.equals("answer")) {
            type=true;
            answerCounter++;
            answerList.add(new Answer(Integer.parseInt(atts.getValue("aid")),questionList.get(questCounter-1)));
        }
    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (localName.equals("description")) {
            interview.setDescription(currentValue);
        }

        if (localName.equals("posture")) {
            interview.setPosture(currentValue);
        }

        if (localName.equals("phrase")) {
            if(type){
                answerList.get(answerCounter-1).setPhrase(currentValue);
            }else if(type==false){
                questionList.get(questCounter-1).setPhrase(currentValue);
            }
        }

        if (localName.equals("gesture")) {
            if(type){
                answerList.get(answerCounter-1).setGesture(currentValue);
            }else if(type==false){
                questionList.get(questCounter-1).setGesture(currentValue);
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