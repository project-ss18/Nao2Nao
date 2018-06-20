package model.robot;

import controller.Connection;

import java.util.ArrayList;

public class Robot {

    private static ArrayList<Robot> robotList = new ArrayList<Robot>();

    private static int id_Counter = 0;
    private String[] args = {};

    private final String IP_ADDRESS;
    private final int ID;
    private String name;
    private String role;

    private Connection CONNECTION;
    private final String PORT = "9559";

    public Robot(String address, String name) throws Exception {
       id_Counter++;
       this.name=name;
       IP_ADDRESS = address;
       ID = id_Counter;

       CONNECTION = new Connection(this.IP_ADDRESS, args);
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

    public void setVolume(int args)throws Exception{
        CONNECTION.setVolume(args);
    }

    public static ArrayList<Robot> getRobotList() {
        return robotList;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getVolume()throws Exception{
        return CONNECTION.getVolume();
    }
    public void setSpeechSpeed(float args)throws Exception{
        CONNECTION.setSpeechSpeed(args);
    }
    public String []toStringArray(){
        String[] temp = new String[2];
        temp = new String[]{Integer.toString(this.ID), this.name, this.IP_ADDRESS};
        return temp;
    }

    public float getSpeechSpeed()throws Exception{
        return CONNECTION.getSpeechSpeed();
    }

    public void setVoicePitch(float args)throws Exception{
        CONNECTION.setVoicePitch(args);
    }

    public float getVoicePitch()throws Exception{
        return CONNECTION.getVoicePitch();
    }
    public String toString(){
        return ( this.getName());
    }

    public void reset() throws Exception {
        CONNECTION.setSpeechSpeed(100);
        CONNECTION.setVoicePitch(0);
        CONNECTION.setVolume(70);
        CONNECTION.posture("SitRelax");
    }

}
