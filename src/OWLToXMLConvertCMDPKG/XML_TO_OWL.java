/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OWLToXMLConvertCMDPKG;

/**
 *
 * @author кирилл
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XML_TO_OWL {

    /**
     * @param args the command line arguments
     */
    private static final String ENCODING_WIN1251 = "windows-1251";
    private static final String ENCODING_UTF8 = "UTF-8";

    public static void main(String[] args) {
        if (args.length != 2) {    // Проверка правильности ввода параметров
            System.err.println("!!! error");
            System.exit(0);
        }
        // вызов convertation()
        try {
            convertation_to_OWL(args[0], args[1]);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void convertation_to_OWL(String filename_in, String filename_out) {
        //
        File f_in = new File(filename_in);
        File f_out = new File(filename_out);
        System.out.println("Конвертируем XML" + filename_in);
        char ch1 = '"';
        ArrayList myArr = new ArrayList<String>();

        myArr.add("<?xml version=" + ch1 + "1.0" + ch1 + "?>");
        myArr.add();

//    xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
//    xmlns:protege="http://protege.stanford.edu/plugins/owl/protege#"
//    xmlns:xsp="http://www.owl-ontologies.com/2005/08/07/xsp.owl#"
//    xmlns:owl="http://www.w3.org/2002/07/owl#"
//    xmlns:xsd="http://www.w3.org/2001/XMLSchema#"
//    xmlns:swrl="http://www.w3.org/2003/11/swrl#"
//    xmlns:swrlb="http://www.w3.org/2003/11/swrlb#"
//    xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#"


        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(f_in), ENCODING_UTF8));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(XML_TO_OWL.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ошибка - файл не найден: " + ex + "Конвертер");
        }
    }
}
