package model.robot;

import controller.Connection;

public class Robot {
    private static int id_Counter = 0;
    private String[] args;

    private final String IP_ADDRESS;
    private final int ID;
    private String name;
    private Connection CONNECTION;
    private final String PORT = "9559";

    public Robot(String address, String name) throws Exception {
        id_Counter++;
        this.name=name;
        IP_ADDRESS = address;
        ID = id_Counter;
        try {
           // CONNECTION = new Connection(this.IP_ADDRESS, args);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

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

    public void goToPosture(String args)throws Exception{
        CONNECTION.posture(args);
    }
    public String []toStringArray(){
        String[] temp = new String[2];
        temp = new String[]{Integer.toString(this.ID), this.name, this.IP_ADDRESS};
        return temp;
    }
}
