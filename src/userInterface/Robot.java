package userInterface;

import com.aldebaran.qi.helper.ALProxy;
import com.aldebaran.qi.helper.proxies.ALRobotPosture;
import controller.Connection;

public class Robot {

    private final String IP_ADDRESS;
    private final int ID;
    private final Connection CONNECTION;
    private final String PORT = "9559";


    public Robot(String address, int id, String[] args) throws Exception {
        IP_ADDRESS = address;
        ID = id;
        CONNECTION = new Connection(this.IP_ADDRESS, args);

    }
    //
    public String getIPAdress() {
        return IP_ADDRESS;
    }

    public int get_ID(){
        return ID;
    }

    public void ping() throws Exception{
        CONNECTION.ping();
    }

    public void say(String args)throws Exception{
        CONNECTION.say(args);
    }

    public void animatedSay(String args)throws Exception{
        CONNECTION.gesture(args);
    }
}
