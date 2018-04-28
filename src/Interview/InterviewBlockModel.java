package Interview;



import java.util.ArrayList;

public class InterviewBlockModel {
    // Objects
    public InterviewModel interview;
    public ArrayList<InterviewActionModel> blockActions;
    // Objects

    // Attribute
    private String bid;
    // Attribute

    // Getter / Setter
    public String getBid() {
        return bid;
    }
    public void setBid(String bid) {
        this.bid = bid;
    }

    public InterviewBlockModel()
    {
        //iaction = new ArrayList<InterviewAction>();
    }

    public InterviewBlockModel(String _blockID)
    {
        blockActions = new ArrayList<InterviewActionModel>();
        Initialize(_blockID);
    }

    // Getter / Setter

    private void Initialize(String _blockID)
    {
        setBid(_blockID);
    }

    public boolean AddInterviewAction(InterviewActionModel interviewAction)
    {
        for(InterviewActionModel CurrentAction : blockActions)
        {
            if(CurrentAction.equals(interviewAction))
            {
                return false;
            }
        }
        blockActions.add(interviewAction);
        return true;
    }

    @Override
    public String toString()
    {
        return "bid: " + this.bid;
    }

}
