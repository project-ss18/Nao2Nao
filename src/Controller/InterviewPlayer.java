package Controller;

import Interview.Interview;

import java.util.List;

public class InterviewPlayer {

    // Instance Vars
    public Interview InterviewBusinessObject;
    // Instance Vars

    // Functions

    public InterviewPlayer(String Path)
    {
        Initialize(Path);
    }

    private void Initialize(String Path)
    {
        //InterviewBusinessObject = new InterviewPlayer();
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
    public static List<InterviewPlayer> GetAllInterviews()
    {
        return null;
    }

    public static InterviewPlayer FindInterview(int ID, List<InterviewPlayer> alleInterviewPlayers)
    {
        return new InterviewPlayer("");
    }
    public static InterviewPlayer FindInterview(String Name, List<InterviewPlayer> alleInterviewPlayers)
    {
        return new InterviewPlayer("");
    }
    // Static Functions

}
