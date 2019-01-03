/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fizzbuzz.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 *
 * @author hugo
 */
public class FileManager {

    public static final String PROPERTIES_FILENAME = "/conf/fizzbuzz.properties";
    public static final File PROPERTIES_FILE = new File(PROPERTIES_FILENAME);

    public static final String RESULTS_FILENAME = "results.txt";
    public static final File RESULTS_FILE = new File(RESULTS_FILENAME);

    /**
     * Loads a property from properties file
     *
     * @param property
     * @return the String value of the property loaded
     */
    public static String loadProperty(String property) {
        String value = null;
        try {
            final FileInputStream fi = new FileInputStream(PROPERTIES_FILE);
            final Properties prop = new Properties();
            prop.load(fi);
            value = prop.getProperty(property);
            fi.close();
        } catch (IOException ex) {
            System.err.println("ERROR: " + ex);
        }
        return value;
    }

    public static void writeToFile(String resultLine) {

        FileWriter fileWriter = null;
        PrintWriter pw = null;
        try {
            fileWriter = new FileWriter(RESULTS_FILENAME, true);
            pw = new PrintWriter(fileWriter);

            for (int i = 0; i < 10; i++) {
                pw.println(resultLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

}
