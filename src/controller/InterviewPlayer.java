package controller;

import interview.ContentHandler;
import interview.Interview;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InterviewPlayer {

    // Instance Vars
    public Interview InterviewBusinessObject;
    public String filename;
    private final static String PATH = "./src/resources/";
    // Instance Vars

    // Functions

    public InterviewPlayer(String FileName)
    {
        filename = FileName;
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

    // print
    public void print()
    {
        System.out.println("Interview " + filename);
    }
    // print

    // Static Functions
    public static List<InterviewPlayer> GetAllInterviews()
    {
        ArrayList<InterviewPlayer> InterviewObjects = new ArrayList<InterviewPlayer>();
        File folder = new File(PATH);
        File[] listofInterviews = folder.listFiles();

        for(File CurrentInterview : listofInterviews)
        {
            if(CurrentInterview.isFile())
            {
               InterviewObjects.add(new InterviewPlayer((CurrentInterview.getAbsolutePath())));
            }
        }
        return InterviewObjects;
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
