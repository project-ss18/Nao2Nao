import controller.InterviewPlayer;
import org.omg.CORBA.Current;
import userInterface.Robot;
import java.util.ArrayList;
import java.util.Scanner;


public class Nao2Nao {


    public static ArrayList<Robot> robotList;
    public static InterviewPlayer currentInterviewPlayer;

    public static void main(String[] args) throws Exception {

        // Interview auswählen
        Scanner s = new Scanner(System.in);

        System.out.println("Bitte wählen Sie eine InterviewPlayer-ID zum abspielen aus:");

        for(InterviewPlayer CurrentInterview: InterviewPlayer.GetAllInterviews())
        {
            CurrentInterview.print();
        }
        System.out.print("Bitte Interview-Namen angeben:");
        String InterviewName = s.next();

        currentInterviewPlayer = new InterviewPlayer("./res/" + InterviewName);

        // Interview auswählen

        // Roboter initialisieren

        String[] emptyArray = new String[0];
        System.out.print("IP-Adresse für Roboter 1: ");
        String IPAdresse1 = s.next();
        robotList.add(new Robot(IPAdresse1, 1, emptyArray));


        System.out.print("IP-Adresse für Roboter 2: ");
        String IPAdresse2 = s.next();
        robotList.add(new Robot(IPAdresse2, 2, emptyArray));
        // Roboter initialisieren

        // Interview abspielen
        System.out.print("Drücken Sie ENTER um das Interview zu starten.");
        s.next();
        currentInterviewPlayer.StartInterview(robotList.get(1), robotList.get(2));
        // Interview abspielen
    }
}


