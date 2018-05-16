import controller.InterviewPlayer;

import java.util.ArrayList;
import java.util.Scanner;
import model.robot.*;

public class Nao2Nao {


    public static ArrayList<Robot> robotList;
    public static InterviewPlayer currentInterviewPlayer;

    public static void main(String[] args) throws Exception {

        //--------------------------Interview Liste ausgeben--------------------------\\
        Scanner s = new Scanner(System.in);
        System.out.println("Bitte wählen Sie eine InterviewPlayer-ID zum abspielen aus:");
        InterviewPlayer.print();

        //--------------------------Selektiertes Interview eingliedern--------------------------\\
        System.out.print("Bitte Interview-Namen angeben:");
        String InterviewName = s.next();
        currentInterviewPlayer = new InterviewPlayer("./res/" + InterviewName);

        //--------------------------Roboter 1 erstellen--------------------------\\
        System.out.print("IP-Adresse für Roboter 1: ");
        String ipRobot1 = s.next();
        Robot r = new Robot(ipRobot1,"Peter");

        //--------------------------Roboter 2 erstellen--------------------------\\
        System.out.print("IP-Adresse für Roboter 2: ");
        String ipRobot2 = s.next();
        Robot r1=new Robot(ipRobot2,"Pan");

        //--------------------------Interview ablaufen lassen--------------------------\\
        System.out.print("Drücken Sie eine Taste um das Interview zu starten.");
        currentInterviewPlayer.startInterview(r,r1);

    }
}


