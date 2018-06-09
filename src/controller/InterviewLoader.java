package controller;

import model.interview.ContentHandler;
import model.interview.Interview;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InterviewLoader {
    private final static String PATH = "./res/";
    private final static String xsdFile = "interview_xsd.xsd";
    private static ArrayList<String> InterviewNamen = new ArrayList<String>();

    public static Interview initializeInterview(String FileName) {
        File XMLFile = new File(FileName);
        try {
            // XMLReader erzeugen
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Pfad zur resources Datei
            FileReader reader = new FileReader(PATH + FileName);
            InputSource inputSource = new InputSource(reader);

            // PersonenContentHandler wird übergeben
            xmlReader.setContentHandler(new ContentHandler());

            // Parsen wird gestartet
            xmlReader.parse(inputSource);

            Interview tempInterview = ContentHandler.getInterview();

            if(checkSyntax(FileName)==false){
                return null;
            }
            return tempInterview ;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean checkSyntax(String xmlFile) {
        //XML-Syntax Check mithilfe eines XSD Schema
        File schemaFile = new File(PATH + xsdFile);
        Source xmlFileSource = new StreamSource(new File(PATH + xmlFile));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        try {
            Schema schema = schemaFactory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            validator.validate(xmlFileSource);
            System.out.println("Die XML Datei '" + xmlFile + "' ist valide");
        } catch (SAXException e) {
            System.out.println(xmlFileSource.getSystemId() + " Die XML Datei '"+ xmlFile + "' ist nicht valide!, Error:" + e);
            return false;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static void printFileNames() {
        for (String CurrentInterview : getAllInterviewNames(false)){
            System.out.println("Interview: '" + CurrentInterview + "'");
        }
    }

    public static List<String> getAllInterviewNames(boolean forcereload)
    {
        if(InterviewNamen.size() == 0 || forcereload == true) {
            ArrayList<String> _InterviewNamen = new ArrayList<String>();
            File folder = new File(PATH);
            File[] listofInterviews = folder.listFiles();

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
    public static List<Interview> getAllInterviews(boolean forceReload) {
        if(Interview.allInterviews.size() == 0 || forceReload == true) {

            ArrayList<Interview> InterviewObjects = new ArrayList<Interview>();
            File folder = new File(PATH);
            File[] listofInterviews = folder.listFiles();

            for(File currentInterview : listofInterviews)
            {
                if(currentInterview.isFile() && currentInterview.getName().endsWith(".xml"))
                {
                    InterviewObjects.add(initializeInterview(currentInterview.getPath()));
                    InterviewNamen.add(currentInterview.getName());
                }
            }
            Interview.allInterviews.clear();
            Interview.allInterviews.addAll(InterviewObjects);
        }
        return Interview.allInterviews;
    }
}
