package model.robot;

import controller.Connection;

import java.util.ArrayList;

public class Robot {

    private static int id_Counter = 0;
    private String[] args;

    private String name;
    private final String IP_ADDRESS;
    private final int ID;

    private  Connection CONNECTION;
    private final String PORT = "9559";


    public Robot(String address, String name) throws Exception {

        this.name=name;
        IP_ADDRESS = address;
        ID = id_Counter++;
        try {
            CONNECTION = new Connection(this.IP_ADDRESS, args);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getIPAdress() {
        return IP_ADDRESS;
    }

    public String []toStringArray(){
        String[] temp = new String[2];
        temp = new String[]{Integer.toString(this.ID), this.name, this.getIP_ADDRESS()};
        return temp;
    }
    public String get_ID(){
        return Integer.toString(ID);
    }

    public void ping() throws Exception{
        CONNECTION.ping();
    }

    public void say(String args)throws Exception{
        CONNECTION.say(args);
    }

    public void gesture(String args)throws Exception{
        CONNECTION.gesture(args);
    }
    public String getName() {
        return name;
    }

    public String getIP_ADDRESS() {
        return IP_ADDRESS;
    }
    public void animatedSay(String args)throws Exception{
        CONNECTION.gesture(args);
    }

    public void goToPosture(String args)throws Exception {
        CONNECTION.posture(args);
    }


        //public void edit(String nam)
}
