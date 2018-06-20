package model;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AppProperties {

    private static String propertiesFileName = "config.properties";
    private static java.util.Properties prop = new java.util.Properties();

    public String getInterviewDirectory()
    {
        return getPropertieValue("InterviewDirectory");
    }
    public String getInterviewValidationFile()
    {
        return getPropertieValue("InterviewValidationFile");
    }
    public String getRobotActionPath()
    {
        return getPropertieValue("RobotActionPath");
    }
    public String getRobotTCPPort()
    {
        return getPropertieValue("RobotTCPPort");
    }
    public ArrayList<String> getPreSettedIPs()
    {
        try {
            String allIPs = getPropertieValue("PreSettedIPs");
            return new ArrayList<String>(Arrays.asList(allIPs.split(";")));
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Information: Es konnten keine vordefinierten IP-Adressen geladen werden. Fehler in der Configuration Attribut 'PreSettedIPs'. Trennung mehrerer IP Adressen mit einem ';'.", "Information", JOptionPane.INFORMATION_MESSAGE);
            return new ArrayList<String>();
        }
    }


    private String getPropertieValue(String PropertyName)
    {
        try
        {
            InputStream inStram = new FileInputStream(propertiesFileName);

            if(inStram != null)
            {
                prop.load(inStram);
            }
            return prop.getProperty(PropertyName);
        }
        catch(Exception ex)
        {
            ex.getStackTrace();
        }
        return null;
    }

}
