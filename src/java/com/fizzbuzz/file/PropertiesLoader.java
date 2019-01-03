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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 *
 * @author hugo
 */
public class PropertiesLoader {

    private static final Logger LOGGER = Logger.getLogger(PropertiesLoader.class);
    
    private static final String PROPERTIES_FILENAME = "/home/hugo/fizzbuzz.properties";
    private static final File PROPERTIES_FILE = new File(PROPERTIES_FILENAME);
    
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
            LOGGER.error(ex);
        }
        return value;
    }

}
