package Model;

import InterviewModel;

import java.util.ArrayList;

public class InterviewBlockModel {
    // Objects
    public InterviewModel interview;
    public ArrayList<InterviewActionModel> blockActions;
    // Objects

    // Attribute
    private int BlockOrderID;
    // Attribute

    // Getter / Setter
    public int getBlockOrderID() {
        return BlockOrderID;
    }
    public void setBlockOrderID(int blockOrderID) {
        BlockOrderID = blockOrderID;
    }
    // Getter / Setter

    public InterviewBlockModel(int _blockID)
    {
        blockActions = new ArrayList<InterviewActionModel>();
        Initialize(_blockID);
    }

    private void Initialize(int _blockID)
    {
        setBlockOrderID(_blockID);
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

}
