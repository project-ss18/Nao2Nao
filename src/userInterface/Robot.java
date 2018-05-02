package userInterface;

public class Robot {

    private final String IP_ADDRESS;
    private final int ID;

    public Robot(String address, int id) {
        IP_ADDRESS = address;
        ID = id;
    }
    //
    public String getIPAdress() {
        return IP_ADDRESS;
    }

    public int get_ID(){
        return ID;
    }


}
