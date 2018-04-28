package Interview;

import java.util.ArrayList;

public class InterviewModel {

    // Objects
    public ArrayList<InterviewBlockModel> interviewBlocks;
    // Objects

    // Attribute
    private int interviewID;
    private String interviewName;
    private String interviewDescription;
    private int interviewMemberCount;
    // Attribute

    // Getter/ Setter
    public int getInterviewID() {
        return interviewID;
    }

    private void setInterviewID(int interviewID) {
        this.interviewID = interviewID;
    }

    public String getInterviewName() {
        return interviewName;
    }

    private void setInterviewName(String interviewName) {
        this.interviewName = interviewName;
    }

    public String getInterviewDescription() {
        return interviewDescription;
    }

    private void setInterviewDescription(String interviewDescription) {
        this.interviewDescription = interviewDescription;

    }

    public int getInterviewMemberCount() {
        return interviewMemberCount;
    }

    private void setInterviewMemberCount(int interviewMemberCount) {
        this.interviewMemberCount = interviewMemberCount;
    }
    // Getter/ Setter


    public InterviewModel(int _interviewID, String _interviewName, String _interviewDescription, int _interviewMemberCount)
    {
        interviewBlocks = new ArrayList<InterviewBlockModel>();
        Initialize(_interviewID, _interviewName, _interviewDescription, _interviewMemberCount);
    }

    private void Initialize(int _interviewID, String _interviewName, String _interviewDescription, int _interviewMemberCount)
    {
        // Objekt initialisieren
        setInterviewID(_interviewID);
        setInterviewName(_interviewName);
        setInterviewDescription(_interviewDescription);
        setInterviewMemberCount(_interviewMemberCount);
        // Objekt initialisieren
    }

    public boolean AddInterviewBlock(InterviewBlockModel _interviewBlock)
    {
        for(InterviewBlockModel CurrentBlock : interviewBlocks)
        {
        if(CurrentBlock.getBlockOrderID() == _interviewBlock.getBlockOrderID())
        {
            return false;
        }
        }

        interviewBlocks.add(_interviewBlock);
        return true;
    }



    // Print Methods
    public String GetInterviewText()
    {
        return "";
    }
    // Print Methods

}
