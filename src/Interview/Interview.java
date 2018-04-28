package Interview;

import java.util.ArrayList;

public class Interview {

    // Objects
    public ArrayList<Block> blockList = new ArrayList<Block>();

    // Attribute
    private int id;
    private String description;
    private Block interviewBlock;


    // Getter/ Setter

    public Interview(int id)
    {
        this.id=id;
    }

    public void addBlock(Block v)
    {
        blockList.add(v);
    }

    public Block getBlock(int id) {
        for (Block v : blockList) {
            if (v.getBid() == id) {
                return v;
            }
        }
        return null;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Block getInterviewBlock() {
        return interviewBlock;
    }

    public void setInterviewBlock(Block interviewBlock) {
        this.interviewBlock = interviewBlock;
    }

    @Override
    public String toString()
    {
        return "id: " + this.id + " decription: " + this.description;
    }



/*
    public InterviewPlayer(int interviewID, String interviewName, String interviewDescription, int interviewMemberCount)
    {
        interviewBlockModels = new ArrayList<Block>();
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

    public boolean AddInterviewBlock(Block _interviewBlockModel)
    {
        for(Block CurrentBlock : interviewBlockModels)
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
