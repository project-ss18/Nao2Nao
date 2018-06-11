import controller.InterviewLoader;
import controller.InterviewPlayer;
import model.interview.ContentHandler;
import model.interview.Interview;
import model.robot.Robot;
import java.util.ArrayList;
import java.util.Scanner;
import model.interview.ContentHandler;


public class Nao2Nao {


    public static ArrayList<Robot> robotList;
    public static InterviewPlayer currentInterviewPlayer;
    public static Interview interview;

    public static void main(String[] args) throws Exception {

        //--------------------------Interview Liste ausgeben--------------------------\\
        Scanner s = new Scanner(System.in);
        System.out.println("Bitte wählen Sie eine InterviewPlayer-ID zum abspielen aus:");
        InterviewLoader.printFileNames();

        //--------------------------Selektiertes Interview eingliedern--------------------------\\
        System.out.print("Bitte Interview-Namen angeben:");
        String InterviewName = s.next();

        interview = InterviewLoader.initializeInterview(InterviewName);
        if(interview == null){
            System.out.println("Interview ist null: CheckSyntax.fail");
            return ;
        }
        currentInterviewPlayer = new InterviewPlayer(interview);

        ArrayList<Robot> teilnehmendeRoboter = new ArrayList<Robot>();
        //--------------------------Roboter 1 erstellen--------------------------\\
        System.out.print("IP-Adresse für Roboter 1: ");
        String ipRobot1 = s.next();
        teilnehmendeRoboter.add(new Robot(ipRobot1,"Rolle1"));

        //--------------------------Roboter 2 erstellen--------------------------\\
        System.out.print("IP-Adresse für Roboter 2: ");
        String ipRobot2 = s.next();
        teilnehmendeRoboter.add(new Robot(ipRobot2, "Rolle2"));

        //--------------------------Interview ablaufen lassen--------------------------\\
        System.out.print("Drücken Sie eine Taste um das Interview zu starten.");
        currentInterviewPlayer.startInterview(teilnehmendeRoboter);

    }
}


