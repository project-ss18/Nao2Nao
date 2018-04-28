package Controller;

import Model.InterviewModel;

import java.util.List;

public class Interview {

    // Instance Vars
    public InterviewModel InterviewBusinessObject;
    // Instance Vars

    // Functions

    public Interview(String Path)
    {
        Initialize(Path);
    }

    private void Initialize(String Path)
    {
        //InterviewBusinessObject = new InterviewModel();
    }

    // Playback Funktionen
    public void StartInterview()
    {

    }
    public void PauseInterview()
    {

    }
    // Playback Funktionen

    // Print Funktionen
    public String GetInterviewText()
    {
        return InterviewBusinessObject.GetInterviewText();
    }
    public String GetInterviewOverview()
    {
        return "";
    }
    // Print Funktionen

    // Functions


    // Static Functions
    public static List<Interview> GetAllInterviews()
    {
        return null;
    }

    public static Controller.Interview FindInterview(int ID, List<Interview> AlleInterviews)
    {
        return new Controller.Interview("");
    }
    public static Controller.Interview FindInterview(String Name, List<Interview> AlleInterviews)
    {
        return new Controller.Interview("");
    }
    // Static Functions

}
