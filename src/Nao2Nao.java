import controller.InterviewPlayer;
import userInterface.Robot;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import interview.*;

public class Nao2Nao {


    public static ArrayList<Robot> robotList;
    public static InterviewPlayer currentInterviewPlayer;
    private final static String PATH = "./src/resources/";


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

        for(int i = 1; i != currentInterviewPlayer.InterviewBusinessObject.get_MemberCount(); ++i)
        {
            System.out.print("IP-Adresse für Roboter " + String.valueOf(i) + " : ");
            String input_IP = System.console().readLine();
            robotList.add(new userInterface(input_IP));
        }

        System.out.println("Das InterviewPlayer ist bereit! Enter drücken um zu starten!");
        System.console().readLine();

        currentInterviewPlayer.StartInterview();
*/
        System.out.println("Bitte wählen Sie eine InterviewPlayer-ID zum abspielen aus:");
        interviewPlayerList = InterviewPlayer.GetAllInterviews();

        for(InterviewPlayer IteratedInterview: interviewPlayerList)
        {
            IteratedInterview.GetInterviewOverview();
        }


    }
}


