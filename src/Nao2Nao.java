import Controller.Interview;
import Robot.Robot;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static Controller.Interview.*;

public class Nao2Nao {

    // In Config auslagern
    private static String InterviewPath = "Interviews\\";
    // In Config auslagern

    public static List<Robot> robotList;
    public static List<Interview> interviewList;
    public static Interview currentInterview;

    public static void main(String[] args) {
/*
        System.out.println("Bitte wählen Sie eine Interview-ID zum abspielen aus:");
        interviewList = Interview.GetAllInterviews();

        for(Interview IteratedInterview: interviewList)
        {
            IteratedInterview.GetInterviewOverview();
        }

        int input_ID = Integer.parseInt(System.console().readLine());
        currentInterview = Interview.FindInterview(input_ID, interviewList);

        /*for(int i = 1; i != currentInterview.InterviewBusinessObject.get_MemberCount(); ++i)
        {
            System.out.print("IP-Adresse für Roboter " + String.valueOf(i) + " : ");
            String input_IP = System.console().readLine();
            robotList.add(new Robot(input_IP));
        }*/
/*
        System.out.println("Das Interview ist bereit! Enter drücken um zu starten!");
        System.console().readLine();

        currentInterview.StartInterview();

        /*
        XML-Teil von Manu
         */

        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur XML Datei
            FileReader reader = new FileReader("/src/XML/interview.xml");
            InputSource inputSource = new InputSource(reader);

            // DTD kann optional übergeben werden
            // inputSource.setSystemId("X:\\personen.dtd");

            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new Interview.InterviewContentHandler());

            // Parsen wird gestartet
            xmlReader.parse(inputSource);

            Interview.InterviewModel test = new Interview.InterviewModel();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    }


