package Interview;

public class InterviewActionModel
{

    private String id;
    private String phrase;
    private String gesture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getGesture() {
        return gesture;
    }

    public void setGesture(String gesture) {
        this.gesture = gesture;
    }


    @Override
    public String toString()
    {
        return "id: " + this.id;
    }
}
