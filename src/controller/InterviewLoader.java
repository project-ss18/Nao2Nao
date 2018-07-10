package controller;

import model.interview.Interview;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import view.Menu;

import javax.swing.*;
import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterviewLoader {

    //------------------------Attribute------------------------\\
    private static ArrayList<String> InterviewNamen = new ArrayList<String>();

    //-----------------------Konstruktor-----------------------\\
    private InterviewLoader(){ }

    //-------------------------Methoden-------------------------\\

    //Initialisiert das Interview
    public static Interview initializeInterview(String FileName) {
        if(!checkSyntax(FileName)){
            return null;
        }
        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur resources Datei
            FileReader reader = new FileReader(FileName);
            InputSource inputSource = new InputSource(reader);
            inputSource.setEncoding("UTF-8");

            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new ContentHandler());

            // Parsen wird gestartet
            xmlReader.parse(inputSource);

            Interview temp = ContentHandler.getInterview();
            temp.setFileName(FileName);
            return temp;

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    //XML-Syntax Check mithilfe eines XSD Schema
    public static boolean checkSyntax(String xmlFile) {
        File schemaFile = new File(AppProperties.getInterviewValidationFile());
        Source xmlFileSource = new StreamSource(new File(xmlFile));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSource);
            //Falls XML-Datei valide ist, wird ein entsprechendes Label im InterviewLoader angezeigt.
            System.out.println("Die XML Datei '" + xmlFile + "' ist valide");
        } catch (SAXException e) {
            //Dialogfenster mit Fehler wird angezeigt, falls eine XML-Datei nicht valide ist.
            JOptionPane.showMessageDialog(null, "Die XML Datei '"+ xmlFile + "' ist nicht valide! \nError: " + e.getMessage(), "Fehler", JOptionPane.ERROR_MESSAGE);
            new Menu();
            return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    //Liefert alle Bezeichnung der Interviews
    public static List<String> getAllInterviewNames(boolean forcereload) {
        if(InterviewNamen.size() == 0 || forcereload) {
            ArrayList<String> _InterviewNamen = new ArrayList<String>();
            File folder = new File(AppProperties.getInterviewDirectory());
            File[] listofInterviews = folder.listFiles();

            assert listofInterviews != null;
            for (File currentInterview : listofInterviews) {
                if (currentInterview.isFile() && currentInterview.getName().endsWith(".xml")) {
                    _InterviewNamen.add(currentInterview.getName());
                }
            }
            InterviewNamen.clear();
            InterviewNamen.addAll(_InterviewNamen);
        }
        return InterviewNamen;
    }

    //Liefer alle Interviewobjekte in einer Liste
    public static List<Interview> getAllInterviews(boolean forceReload) {
        if(Interview.getAllInterviews().size() == 0 || forceReload) {

            ArrayList<Interview> InterviewObjects = new ArrayList<Interview>();
            File folder = new File(AppProperties.getInterviewDirectory());
            File[] listofInterviews = folder.listFiles();

            assert listofInterviews != null;
            for(File currentInterview : listofInterviews)
            {
                if(currentInterview.isFile() && currentInterview.getName().endsWith(".xml"))
                {
                    InterviewObjects.add(initializeInterview(currentInterview.getPath()));
                    InterviewNamen.add(currentInterview.getName());
                }
            }
            Interview.getAllInterviews().clear();
            Interview.getAllInterviews().addAll(InterviewObjects);
        }

        return Interview.getAllInterviews();
    }
}
