import Interview.Interview;
import Robot.Robot;

import java.util.List;

public class Nao2Nao {

    // In Config auslagern
    private static String InterviewPath = "Interviews\\";
    // In Config auslagern

    public static List<Robot> Robots;
    public static List<Interview> AlleInterviews;
    public static Interview CurrentInterview;

    public static void main(String[] args) {

        System.out.println("Bitte wählen Sie eine Interview-ID zum abspielen aus:");
        AlleInterviews = Interview.GetAllInterviews();

        for(Interview IteratedInterview: AlleInterviews)
        {
            IteratedInterview.GetInterviewOverview();
        }

        int input_ID = Integer.parseInt(System.console().readLine());
        CurrentInterview = Interview.FindInterview(input_ID, AlleInterviews);

        for(int i = 1; i != CurrentInterview.InterviewBusinessObject.get_MemberCount(); ++i)
        {
            System.out.print("IP-Adresse für Roboter " + String.valueOf(i) + " : ");
            String input_IP = System.console().readLine();
            Robots.add(new Robot(input_IP));
        }

        System.out.println("Das Interview ist bereit! Enter drücken um zu starten!");
        System.console().readLine();

        CurrentInterview.StartInterview();
    }


}
