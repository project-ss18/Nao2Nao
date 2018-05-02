import controller.InterviewPlayer;
import org.omg.CORBA.Current;
import userInterface.Robot;
import java.util.ArrayList;
import java.util.Scanner;


public class Nao2Nao {


    public static ArrayList<Robot> robotList;
    public static InterviewPlayer currentInterviewPlayer;

    public static void main(String[] args) throws Exception {
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

        // Interview auswählen
        Scanner s = new Scanner(System.in);

        System.out.println("Bitte wählen Sie eine InterviewPlayer-ID zum abspielen aus:");
        for(InterviewPlayer CurrentInterview: InterviewPlayer.GetAllInterviews())
        {
            CurrentInterview.print();
        }
        System.out.print("Bitte Interview-Namen angeben:");
        String InterviewName = s.next();
        for(InterviewPlayer CurrentInterview: InterviewPlayer.GetAllInterviews())
        {
        if(CurrentInterview.XMLFile.getName() == InterviewName)
        {
            currentInterviewPlayer = CurrentInterview;
        }
        }
        
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
        System.out.print("Drücken Sie eine Taste um das Interview zu starten.");
        currentInterviewPlayer.StartInterview(robotList.get(1), robotList.get(2));
        // Interview abspielen
    }
}


