package controller;

import interview.ContentHandler;
import interview.Interview;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import userInterface.Robot;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InterviewPlayer {

    // Instance Vars
    public Interview InterviewBusinessObject;
    public File XMLFile;
    private final static String PATH = "./res/";
    // Instance Vars

    // Functions

    public InterviewPlayer(String FileName)
    {
        XMLFile = new File(FileName);
        Initialize(FileName);
    }

    private void Initialize(String FileName)
    {
        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur resources Datei
            FileReader reader = new FileReader(FileName);
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
    public void StartInterview(ArrayList<Robot> AlleRoboter)
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
        System.out.println("Interview: '" + XMLFile.getName() + "'");
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
            if(CurrentInterview.isFile() && CurrentInterview.getName().endsWith(".xml"))
            {
               InterviewObjects.add(new InterviewPlayer(PATH + CurrentInterview.getName()));
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
