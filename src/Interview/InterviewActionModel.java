package Interview;

public class InterviewActionModel {
    // Objects
    public InterviewBlockModel _InterviewBlock;
    // Objects

    // Attribute
    private int RobotID;
    private int ActionOrderID;
    private String ActionText;
    private Object ActionGesture;
    private ActionType TypeofAction;
    // Attribute

    // Getter / Setter
    public int getRobotID() {
        return RobotID;
    }

    public void setRobotID(int robotID) {
        RobotID = robotID;
    }

    public int getActionOrderID() {
        return ActionOrderID;
    }

    public void setActionOrderID(int actionOrderID) {
        ActionOrderID = actionOrderID;
    }

    public String getActionText() {
        return ActionText;
    }

    public void setActionText(String actionText) {
        ActionText = actionText;
    }

    public Object getActionGesture() {
        return ActionGesture;
    }

    public void setActionGesture(Object actionGesture) {
        ActionGesture = actionGesture;
    }

    public ActionType getTypeofAction() {
        return TypeofAction;
    }

    public void setTypeofAction(ActionType typeofAction) {
        TypeofAction = typeofAction;
    }
    // Getter / Setter

    public InterviewActionModel(int _roboterID, int _actionOrderID, String _actionText, String _actionGesture, ActionType _typeOfAction)
    {
        Initialize(_roboterID,_actionOrderID,_actionText,_actionGesture,_typeOfAction);
    }
    private void Initialize(int _roboterID, int _actionOrderID, String _actionText, String _actionGesture, ActionType _typeOfAction)
    {
    setRobotID(_roboterID);
    setActionOrderID(_actionOrderID);
    setActionText(_actionText);
    setActionGesture(_actionGesture);
    setTypeofAction(_typeOfAction);
    }

}
