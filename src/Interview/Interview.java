package Interview;

import BusinessLogic.Interview.BoInterview;

import java.util.List;

public class Interview {

    // Instance Vars
    public BoInterview InterviewBusinessObject;
    // Instance Vars

    // Functions

    public Interview(String Path)
    {
        Initialize(Path);
    }

    private void Initialize(String Path)
    {
        InterviewBusinessObject = new BoInterview(Path);
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

    public static Interview.Interview FindInterview(int ID, List<Interview> AlleInterviews)
    {
        return new Interview.Interview("");
    }
    public static Interview.Interview FindInterview(String Name, List<Interview> AlleInterviews)
    {
        return new Interview.Interview("");
    }
    // Static Functions

}
