package controller;

import interview.ContentHandler;
import interview.Interview;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class InterviewPlayer {

    // Instance Vars
    public Interview InterviewBusinessObject;
    private final static String PATH = "./src/resources/";
    // Instance Vars

    // Functions

    public InterviewPlayer(String FileName)
    {
        Initialize(FileName);
    }

    private void Initialize(String FileName)
    {
        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur resources Datei
            FileReader reader = new FileReader(PATH + FileName);
            InputSource inputSource = new InputSource(reader);


            // DTD kann optional übergeben werden
            // inputSource.setSystemId("X:\\personen.dtd");

            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new ContentHandler());

            // Parsen wird gestartet
            xmlReader.parse(inputSource);

          InterviewBusinessObject = ContentHandler.getInterview();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    // Playback Funktionen
    public void StartInterview()
    {

    }
    public void PauseInterview()
    {

    }
    // Playback Funktionen

    // Print Funktionen

    // Functions


    // Static Functions
    public static List<InterviewPlayer> GetAllInterviews()
    {
        return null;
    }

    public static InterviewPlayer FindInterview(int ID, List<InterviewPlayer> alleInterviewPlayers)
    {
        return new InterviewPlayer("");
    }
    public static InterviewPlayer FindInterview(String Name, List<InterviewPlayer> alleInterviewPlayers)
    {
        return new InterviewPlayer("");
    }
    // Static Functions

}
