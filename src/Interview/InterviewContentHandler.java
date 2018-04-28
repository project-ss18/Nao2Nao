package Interview;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.ArrayList;

public class InterviewContentHandler implements ContentHandler {

    private ArrayList<InterviewModel> allInterviewModels = new ArrayList<InterviewModel>();
    private ArrayList<InterviewBlockModel> allBlocks = new ArrayList<InterviewBlockModel>();
    private ArrayList<InterviewActionModel> allInterviewActions = new ArrayList<InterviewActionModel>();
    private String currentValue;
    private InterviewModel interviewModel;
    private InterviewBlockModel interviewBlock;
    private InterviewActionModel interviewAction;

    // Aktuelle Zeichen die gelesen werden, werden in eine Zwischenvariable
    // gespeichert
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        currentValue = new String(ch, start, length);
    }



    // Methode wird aufgerufen wenn der Parser zu einem Start-Tag kommt     ==>Objekt
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException
    {
        //INTERVIEW
        if (localName.equals("interview"))
        {
            // Neues InterviewModel erzeugen
            interviewModel = new InterviewModel();


            //interviewModel.setId(Integer.parseInt(atts.getValue("id")));
            interviewModel.setId(atts.getValue("iid"));
        }

        //BLOCK
        if (localName.equals("block"))
        {
            // Neuen InterviewBlock erzeugen
            interviewBlock = new InterviewBlockModel();
            interviewBlock.setBid(atts.getValue("bid"));
        }

        if (localName.equals("question"))
        {
            // Neue InterviewAction erzeugen
            interviewAction = new InterviewActionModel();
            interviewAction.setId(atts.getValue("id"));
        }

        if (localName.equals("answer"))
        {
            // Neue InterviewAction erzeugen
            interviewAction = new InterviewActionModel();
            interviewAction.setId(atts.getValue("id"));
        }


    }

    // Methode wird aufgerufen wenn der Parser zu einem End-Tag kommt       ==>Attribute von Objekt
    public void endElement(String uri, String localName, String qName)
            throws SAXException
    {

        // Beschreibung setzen
        if (localName.equals("description")) {
            interviewModel.setDescription(currentValue);
        }

        if (localName.equals("interview")) {
            allInterviewModels.add(interviewModel);
            System.out.println(interviewModel);
        }

        if (localName.equals("block")) {
            allBlocks.add(interviewBlock);
            System.out.println(interviewBlock);
        }

        if (localName.equals("question")) {
            allInterviewActions.add(interviewAction);
            System.out.println(interviewAction);
        }

        if (localName.equals("answer")) {
            allInterviewActions.add(interviewAction);
            System.out.println(interviewAction);
        }

        if (localName.equals("text")) {
            interviewAction.setPhrase(currentValue);
            System.out.println(currentValue);
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