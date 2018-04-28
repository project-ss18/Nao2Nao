package Interview;

import java.util.ArrayList;

public class InterviewModel {

    // Objects
    public ArrayList<InterviewBlockModel> interviewBlockModels;

    // Attribute
    private String id;
    private String description;
    private InterviewBlockModel interviewBlock;


    // Getter/ Setter

    public InterviewModel()
    {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public InterviewBlockModel getInterviewBlock() {
        return interviewBlock;
    }

    public void setInterviewBlock(InterviewBlockModel interviewBlock) {
        this.interviewBlock = interviewBlock;
    }

    @Override
    public String toString()
    {
        return "id: " + this.id + " decription: " + this.description;
    }



/*
    public InterviewModel(int interviewID, String interviewName, String interviewDescription, int interviewMemberCount)
    {
        interviewBlockModels = new ArrayList<InterviewBlockModel>();
        Initialize(interviewID, interviewName, interviewDescription, interviewMemberCount);
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

    public boolean AddInterviewBlock(InterviewBlockModel _interviewBlockModel)
    {
        for(InterviewBlockModel CurrentBlock : interviewBlockModels)
        {
        if(CurrentBlock.getBid() == _interviewBlockModel.getBid())
        {
            return false;
        }
        }

        interviewBlockModels.add(_interviewBlockModel);
        return true;
    }

*/

    // Print Methods
    public String GetInterviewText()
    {
        return "";
    }
    // Print Methods

}
