import controller.InterviewLoader;
import controller.InterviewPlayer;
import model.interview.Interview;
import model.robot.Robot;
import java.util.ArrayList;
import java.util.Scanner;


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


        interview = InterviewLoader.initializeInterview("./res/interview.xml");
        if(interview == null){
            System.out.println("Interview ist null: CheckSyntax.fail");
            return ;
        }

        ArrayList<Robot> teilnehmendeRoboter = new ArrayList<Robot>();
        //--------------------------Roboter 1 erstellen--------------------------\\
        System.out.print("IP-Adresse für Roboter 1: ");

        teilnehmendeRoboter.add(new Robot("141.28.75.162","Rolle1"));

        //--------------------------Roboter 2 erstellen--------------------------\\
        System.out.print("IP-Adresse für Roboter 2: ");

        teilnehmendeRoboter.add(new Robot("141.28.75.148", "Rolle2"));
        teilnehmendeRoboter.get(0).setRole("Rolle1");
        teilnehmendeRoboter.get(1).setRole("Rolle2");
        Thread.sleep(2000);
        currentInterviewPlayer = new InterviewPlayer(interview,teilnehmendeRoboter);



        //--------------------------Interview ablaufen lassen--------------------------\\
        System.out.print("Drücken Sie eine Taste um das Interview zu starten.");
        currentInterviewPlayer.startInterview();

    }
}


