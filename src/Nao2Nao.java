import Controller.Interview;
import Robot.Robot;

import java.util.List;

public class Nao2Nao {

    // In Config auslagern
    private static String InterviewPath = "Interviews\\";
    // In Config auslagern

    public static List<Robot> robotList;
    public static List<Interview> interviewList;
    public static Interview currentInterview;

    public static void main(String[] args) {

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

        System.out.println("Das Interview ist bereit! Enter drücken um zu starten!");
        System.console().readLine();

        currentInterview.StartInterview();
    }


}
