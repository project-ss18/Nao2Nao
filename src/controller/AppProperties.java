package controller;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class AppProperties {

    private static String propertiesFileName = "config.properties";
    private static java.util.Properties prop = new java.util.Properties();

    public static String getInterviewDirectory()
    {
        return getPropertieValue("InterviewDirectory");
    }
    public static String getInterviewValidationFile()
    {
        return getPropertieValue("InterviewValidationFile");
    }
    public static String getRobotActionPath()
    {
        return getPropertieValue("RobotActionPath");
    }
    public static String getRobotTCPPort()
    {
        return getPropertieValue("RobotTCPPort");
    }
    public static ArrayList<String> getPreSettedIPs() {
        try {
            String allIPs = getPropertieValue("PreSettedIPs");
            if(allIPs.equals(""))
            {
                return new ArrayList<String>();
            }
            return new ArrayList<String>(Arrays.asList(allIPs.split(";")));
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Es konnten keine vordefinierten IP-Adressen geladen werden. Fehler in der Configuration Attribut 'PreSettedIPs'. Trennung mehrerer IP Adressen mit einem ';'.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return new ArrayList<String>();
        }
    }
    public static String getApplicationMode()
    {
        return getPropertieValue("AppMode");
    }

    private static String getPropertieValue(String PropertyName) {
        try {
            InputStream inStram = new FileInputStream(propertiesFileName);

            if(inStram != null) {
                prop.load(inStram);
            }
            return prop.getProperty(PropertyName);
        }
        catch(Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage() + " fehlt im Anwendungsverzeichnis!", "Information", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

}
