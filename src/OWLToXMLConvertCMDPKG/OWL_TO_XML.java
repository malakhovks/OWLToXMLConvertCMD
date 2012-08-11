/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OWLToXMLConvertCMDPKG;

/**
 *
 * @author MalahovKS
 */
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OWL_TO_XML {

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
            convertation(args[0], args[1]);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        // TODO code application logic here
    }

    public static void convertation(String filename_in, String filename_out) {

        File f_in = new File(filename_in);
        File f_out = new File(filename_out);
        //создаеться временный файл в корневой директории проекта
        File f_temp = new File(System.getProperty("user.dir"), "temp.tmp");
        System.out.println("Конвертируем OWL" + filename_in);
        //создание потока для считывания временного  файла temp
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(f_in));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ошибка - файл не найден: " + ex + "Конвертер");
        }
        BufferedReader br1 = null;
        try {
            br1 = new BufferedReader(new FileReader(f_in));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Ошибка - файл не найден: " + ex + "Конвертер");
        }
        ArrayList myArr = new ArrayList<String>();
        ArrayList myArr1 = new ArrayList<String>();
        ArrayList myArr2 = new ArrayList<String>();
        ArrayList myArr3 = new ArrayList<String>();
        ArrayList myArr4 = new ArrayList<String>();
        //формирование строк файла, запись в динамический массив

        myArr.add("<Graph>");
        myArr.add("<Nodes>");
        String s = null;
        String Space = " ";
        String owl_Class = "<owl:Class rdf:ID=";
        String owl_Class_rdf_about = "<owl:Class rdf:about=";
        String owl_class1 = "<owl:Class";
        String rdfs_SubClass = "<rdfs:subClassOf>";
        String s1;
        int j = 0;
        char ch1 = '"';
        double xPos = 115.5;
        double yPos = 14.95;
        String s2 = null;
        String s3;
        String s4;
        String s5;
        String s6 = null;
        int n_rebra = 0;
        String Name = "<name>";
        String Superclass = "<superclass>";
        String s7;

        // цикл считывания строк исходного файла и формирование структуры <node>
        try {
            while ((s = br.readLine()) != null) {
                //удаление пробелов
                if (s.startsWith(Space)) {
                    while (s.startsWith(Space)) {
                        s = s.substring(j + 1, s.length());
                    }
                    // "|" оператор OR
                    if (s.startsWith(owl_Class) | s.startsWith(owl_Class_rdf_about)) {
                        if (s.startsWith(owl_Class)) {
                            s = s.substring(19, s.length());
                            s = s.substring(0, s.indexOf(ch1));
                            System.out.println(s);
                            s1 = s;
                            myArr2.add(s1);
                            yPos = yPos + 20;
                            xPos = xPos + 20;

                            String s_utf_8_1 = new String(s.getBytes(ENCODING_WIN1251), ENCODING_UTF8);

                            myArr.add("<Node nodeName=" + ch1 + s_utf_8_1 + ch1 + " " + "shape=" + ch1 + "circle" + ch1 + " " + "color=" + ch1 + "16711680" + ch1 + " " + "xPos=" + ch1 + xPos + ch1 + " " + "yPos=" + ch1 + yPos + ch1 + "/>");
                        } else {
                            s = s.substring(22, s.length());
                            s = s.substring(0, s.indexOf(ch1));
                            while ((s.startsWith("#")) != true) {
                                s = s.substring(j + 1, s.length());
                            }
                            s = s.substring(1, s.length());
                            if (myArr2.contains(s)) {
                                System.out.println();
                                System.out.println(s + " ------ Повтор!!! Данный класс уже записан.");
                                System.out.println();
                            } else {
                                System.out.println(s);
                                yPos = yPos + 20;
                                xPos = xPos + 20;

                                String s_utf_8_2 = new String(s.getBytes(ENCODING_WIN1251), ENCODING_UTF8);

                                myArr.add("<Node nodeName=" + ch1 + s_utf_8_2 + ch1 + " " + "shape=" + ch1 + "circle" + ch1 + " " + "color=" + ch1 + "16711680" + ch1 + " " + "xPos=" + ch1 + xPos + ch1 + " " + "yPos=" + ch1 + yPos + ch1 + "/>");
                            }
                        }
                    }
                }
            }
            myArr.add("</Nodes>");
        } catch (IOException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }


        // Цикл формирования списка связей
        try {
            while ((s = br1.readLine()) != null) {
                j = 0;
                if (s.startsWith(Space)) {
                    while (s.startsWith(Space)) {
                        s = s.substring(j + 1, s.length());
                    }

                    if (s.startsWith(owl_class1) | s.startsWith("<rdfs:subClassOf rdf:resource")) {

                        if (s2.startsWith("<owl:someValuesFrom")) {
                        } else {

                            if (s2.startsWith(rdfs_SubClass) | s.startsWith("<rdfs:subClassOf rdf:resource")) {
                                j = 0;
                                while ((s.startsWith(ch1 + "")) != true) {
                                    s = s.substring(j + 1, s.length());
                                }
                                s = s.substring(0, s.length() - 3);

                                if (s.contains("#") & s.contains(ch1 + "")) {
                                    s = s.substring(s.indexOf("#") + 1, s.length());
                                } else {
                                    s = s.substring(s.indexOf(ch1) + 1, s.length());
                                }

                                String s_utf_8_5 = new String(s.getBytes(ENCODING_WIN1251), ENCODING_UTF8);

                                myArr3.add("<superclass>" + s_utf_8_5 + "<superclass>");
                                myArr3.add("\n");
                                myArr3.add("\n");
                                s4 = s;
                            } else {

                                if (s.endsWith(ch1 + "/>")) {
                                } else {
                                    s = s.substring(s.indexOf(ch1) + 1, s.length());
                                    s = s.substring(0, s.length() - 2);

                                    if (s.contains("#")) {
                                        s = s.substring(s.indexOf("#") + 1, s.length());
                                    } else {
                                        s = s.substring(s.indexOf(ch1) + 1, s.length());
                                    }

                                    String s_utf_8_6 = new String(s.getBytes(ENCODING_WIN1251), ENCODING_UTF8);

                                    myArr3.add("<name>" + s_utf_8_6 + "<name>");
                                    myArr3.add("\n");
                                    s5 = s;
                                }
                            }
                        }

                    }
                    s2 = s;
                }
            }

            System.out.println(myArr3);

        } catch (IOException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }

        //                          ЗАПИСЬ ВРЕММЕННОГО ФАЙЛА

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f_temp);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedWriter writer_ = null;
        try {
            writer_ = new BufferedWriter(new OutputStreamWriter(fos, ENCODING_UTF8));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator iterator_ = myArr3.iterator();
        while (iterator_.hasNext()) {
            try {
                writer_.append(iterator_.next().toString());
            } catch (IOException ex) {
                System.out.println("Ошибка ввода-вывода: " + ex + "Конвертер");

                Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            writer_.close();
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }

        //                          ЗАПИСЬ ВРЕММЕННОГО ФАЙЛА



        //                         ЧТЕНИЕ ВРЕМЕННОГО ФАЙЛА И ФОРМИРОВАНИЕ СТРУКТУРЫ СВЯЗЕЙ 
        myArr4.add("<Edges>");

        BufferedReader br2 = null;
        try {
            br2 = new BufferedReader(new FileReader(f_temp));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            while ((s5 = br2.readLine()) != null) {
                if (s5.startsWith(Name)) {
                    s5 = s5.substring(6, s5.length());
                    s5 = s5.substring(0, s5.indexOf("<"));
                    s6 = s5;
                }
                if (s5.startsWith(Superclass)) {
                    s5 = s5.substring(12, s5.length());
                    s5 = s5.substring(0, s5.indexOf("<"));
                    s7 = s5;
                    //                                      счётчик рёбер
                    n_rebra++;

                    String s_utf_8_3 = new String(s7.getBytes(ENCODING_WIN1251), ENCODING_UTF8);
                    String s_utf_8_4 = new String(s6.getBytes(ENCODING_WIN1251), ENCODING_UTF8);

                    myArr4.add("<Edge edgeName=" + ch1 + "ребро" + n_rebra + ch1 + " node1=" + ch1 + s_utf_8_3 + ch1 + " node2=" + ch1 + s_utf_8_4 + ch1 + " " + "color=" + ch1 + "255" + ch1 + " " + "weight=" + ch1 + "1" + ch1 + "/>");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }

        myArr4.add("</Edges>");
        myArr4.add("</Graph>");


        System.out.println(myArr4);


        //                         ЧТЕНИЕ ВРЕМЕННОГО ФАЙЛА И ФОРМИРОВАНИЕ СТРУКТУРЫ СВЯЗЕЙ



        //                         ЗАПИСЬ ИТОГОВОГО ФАЙЛА В НУЖНОМ ФОРМАТЕ

        //подготовка конечного файла для записи
        //в него массивов MyArr & MyArr1
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(f_out);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
//                        запись в файл в кодировке UTF-8
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, ENCODING_UTF8));

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
//                        writer = new BufferedWriter(new FileWriter(f_result));

        //создаем объект iterator класса Iterator
        Iterator iterator = myArr.iterator();
        //цикл вставки строк массива MyArr в конечный файл (f,outfile.xml)
        while (iterator.hasNext()) {
            try {
                writer.append(iterator.next().toString() + '\n');
            } catch (IOException ex) {
                Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        //создаем объект iterator класса Iterator
        Iterator iterator_1 = myArr4.iterator();
        //цикл вставки строк массива MyArr в конечный файл (f,outfile.xml)
        while (iterator_1.hasNext()) {
            try {
                writer.append(iterator_1.next().toString() + '\n');
            } catch (IOException ex) {

                System.out.println("Ошибка ввода-вывода: " + ex + "Конвертер");
                Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//                         ЗАПИСЬ ИТОГОВОГО ФАЙЛА В НУЖНОМ ФОРМАТЕ




//          ЗАКРЫТИЕ ПОТОК И ОТЧИСТКА ПЕРЕМЕННЫХ
        try {
//                                закрытие потоков записи
            writer.close();
            fileOutputStream.close();
            br.close();
            br2.close();
            br1.close();
        } catch (IOException ex) {
            Logger.getLogger(OWL_TO_XML.class.getName()).log(Level.SEVERE, null, ex);
        }
//                            отчистка массивов
        myArr.clear();
        myArr1.clear();
        myArr2.clear();
        myArr3.clear();
        myArr4.clear();

        s = null;
        s4 = null;
        s5 = null;
        s2 = null;
        s3 = null;
        s6 = null;
        s7 = null;


        n_rebra = 0;
        xPos = 115.5;
        yPos = 14.95;

        if (f_temp.exists()) {
            Boolean f_d = f_temp.delete();
            System.out.println();
            // вывод в консоль инфо о удалении файла
            System.out.println("Удалён ли временный файл - " + f_d);
        } else {
            System.out.println();
            System.out.println("Временный файл отсутствует или небыл создан.");
            System.out.println("Удалён ли временный файл - false");
        }

    }

    ;

    protected static void fail(String msg) throws IllegalArgumentException {
        throw new IllegalArgumentException(msg);
    }
}
