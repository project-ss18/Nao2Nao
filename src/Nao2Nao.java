import Controller.InterviewPlayer;
import Robot.Robot;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import Interview.*;

public class Nao2Nao {

    // In Config auslagern
    private static String InterviewPath = "Interviews\\";
    // In Config auslagern

    public static List<Robot> robotList;
    public static List<InterviewPlayer> interviewPlayerList;
    public static InterviewPlayer currentInterviewPlayer;
    public static Interview interview;

    public static void main(String[] args) {
/*
        System.out.println("Bitte wählen Sie eine InterviewPlayer-ID zum abspielen aus:");
        interviewPlayerList = InterviewPlayer.GetAllInterviews();

        for(InterviewPlayer IteratedInterview: interviewPlayerList)
        {
            IteratedInterview.GetInterviewOverview();
        }

        int input_ID = Integer.parseInt(System.console().readLine());
        currentInterviewPlayer = InterviewPlayer.FindInterview(input_ID, interviewPlayerList);

        /*for(int i = 1; i != currentInterviewPlayer.InterviewBusinessObject.get_MemberCount(); ++i)
        {
            System.out.print("IP-Adresse für Roboter " + String.valueOf(i) + " : ");
            String input_IP = System.console().readLine();
            robotList.add(new Robot(input_IP));
        }*/
/*
        System.out.println("Das InterviewPlayer ist bereit! Enter drücken um zu starten!");
        System.console().readLine();

        currentInterviewPlayer.StartInterview();

        /*
        resources-Teil von Manu
         */

        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur resources Datei
            FileReader reader = new FileReader("/home/e/IdeaProjects/Nao2Nao/src/resources/interview.xml");
            InputSource inputSource = new InputSource(reader);

            // DTD kann optional übergeben werden
            // inputSource.setSystemId("X:\\personen.dtd");

            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new ContentHandler());

            // Parsen wird gestartet
            xmlReader.parse(inputSource);


           System.out.println(ContentHandler.getInterview().getBlock(1).getQuestion(1).getAnswer(1).getPhrase());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}


