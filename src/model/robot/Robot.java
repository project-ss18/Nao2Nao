package model.robot;

import com.aldebaran.qi.CallError;
import controller.AppProperties;
import controller.Connection;

import java.util.ArrayList;

public class Robot {
    //------------------------Attribute------------------------\\
    private String name;
    private final String IP_ADDRESS;

    //------------------------Variablen------------------------\\
    private static ArrayList<Robot> robotList = new ArrayList<Robot>();
    private static int id_Counter = 0;
    private String[] args = {};
    private final int ID;
    private String role;
    private Connection CONNECTION;

    //-----------------------Konstruktor-----------------------\\
    public Robot(String address, String name) throws Exception {
       id_Counter++;
       this.name=name;
       IP_ADDRESS = address;
       ID = id_Counter;
       if(!AppProperties.getApplicationMode().equals("Test")){
           CONNECTION = new Connection(this.IP_ADDRESS, args);
       }

    }

    //-------------------------Methoden-------------------------\\

    //Lediglich Sprachausgabe
    public void say(String args)throws Exception{
        CONNECTION.say(args);
    }

    //Sprachausgabe mit zusätzlicher Gestik
    public void animatedSay(String args)throws Exception{
        CONNECTION.gesture(args);
    }

    //Ausgangsposition
    public void goToPosture(String args)throws Exception{
        CONNECTION.posture(args);
    }

    //Lautstärke der Sprachausgabe
    public void setVolume(int args)throws Exception{
        CONNECTION.setVolume(args);
    }

    //Verbindungstest
    public void ping() throws Exception{
        CONNECTION.ping();
    }

    //Erzeugt ein String Array für die Ansicht der Roboterliste in der GUI
    public String []toStringArray(){
        String[] temp = new String[2];
        temp = new String[]{Integer.toString(this.ID), this.name, this.IP_ADDRESS};
        return temp;
    }

    public String toString(){
        return ( this.getName());
    }

    public void reset(Robot r){
        Thread temp = new Thread(new ResetRunnable(r));
        temp.start();
    }

    //Versetzt den Roboter in die "Relax" Position und lockert die Gelenke, sodass die Motoren nicht heiß werden.
    //Zudem werden alle manipulierbaren Attribute der Sprachausgabe auf die Standardwerte zurückgesetzt.
    public void reset() throws Exception {
        CONNECTION.setSpeechSpeed(100);
        CONNECTION.setVoicePitch(0);
        CONNECTION.setVolume(70);
        CONNECTION.posture("SitRelax");
        CONNECTION.setStiffnes(0.0);
    }

    //----------------------Getter//Setter----------------------\\

    public String getName() {
        return name;
    }

    public String getIPAdress() {
        return IP_ADDRESS;
    }

    public int get_ID(){
        return ID;
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

    public float getSpeechSpeed()throws Exception{
        return CONNECTION.getSpeechSpeed();
    }

    public void setVoicePitch(float args)throws Exception{
        CONNECTION.setVoicePitch(args);
    }

    public float getVoicePitch()throws Exception{
        return CONNECTION.getVoicePitch();
    }

    private class ResetRunnable implements Runnable{

        Robot robot;

        ResetRunnable(Robot robot){
            this.robot=robot;
        }
        @Override
        public void run() {
            try {
                robot.reset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
