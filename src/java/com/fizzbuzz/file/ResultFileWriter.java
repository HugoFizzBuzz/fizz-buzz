package com.fizzbuzz.file;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 *
 * @author hugo
 */
public class ResultFileWriter implements Runnable {
    
    private static final Logger LOGGER = Logger.getLogger(ResultFileWriter.class);
    
    private static final String RESULTS_FILENAME = "/home/hugo/results.txt";
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private final String resultLine;

    /**
     * Constructor
     * @param resultLine 
     */
    public ResultFileWriter(String resultLine) {
        this.resultLine = resultLine;
    }
    
    /**
     * Run method
     */
    @Override
    public void run() {
        
        LOGGER.info("Begins ResultFileWriter run method");
        
        FileWriter fileWriter = null;
        PrintWriter pw;
        
        try {
            fileWriter = new FileWriter(RESULTS_FILENAME, true);
            pw = new PrintWriter(fileWriter);

            pw.println(DATE_FORMAT.format(new Date())+"\t"+resultLine);

        } catch (IOException e) {
            LOGGER.error(e);
        } finally {
            try {
                if (null != fileWriter) {
                    fileWriter.close();
                }
            } catch (IOException e2) {
                LOGGER.error(e2);
            }
        }
        
        LOGGER.info("Ends ResultFileWriter run method");
    }
}
