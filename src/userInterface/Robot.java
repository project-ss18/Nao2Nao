package userInterface;

public class Robot {

    // Instance Vars
    private String _IPAdress;
    private int _ID;
    // Instance Vars

    // Getter/ Setter
    public String get_IPAdress()
    {
        return _IPAdress;
    }

    public void set_IPAdress(String _IPAdress)
    {
        this._IPAdress = _IPAdress;
        // Connect with this IP-Adress
    }

    public int get_ID()
    {
        return _ID;
    }

    private void set_ID(int _ID)
    {
        this._ID = _ID;
    }
    // Getter/ Setter

    // Functions
    public Robot(String IPAdress)
    {

    }
    // Functions

}
