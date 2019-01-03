package com.fizzbuzz.logic;

import com.fizzbuzz.exception.FizzBuzzException;
import com.fizzbuzz.file.PropertiesLoader;
import com.fizzbuzz.file.ResultFileWriter;
import org.apache.log4j.Logger;

/**
 *
 * @author hugo
 */
public class FizzBuzzLogic {
    
    private static final Logger LOGGER = Logger.getLogger(FizzBuzzLogic.class);
    
    private static final int FIZZ_MOD = 3;
    private static final int BUZZ_MOD = 5;
        
    private static final String FIZZ_STRING = "fizz";
    private static final String BUZZ_STRING = "buzz";
    
    private final int limit = Integer.parseInt(PropertiesLoader.loadProperty("limit"));
    private final boolean allowNegativeNumbers = Boolean.parseBoolean(PropertiesLoader.loadProperty("allowNegativeNumbers"));
    
    /*** 
     * Constructor
     */
    public FizzBuzzLogic(){
        
    }
    
    /**
     * Returns the fizz-buzz serie
     * @param str
     * @return the String Fizz Buzz serie
     * @throws FizzBuzzException 
     */
    public String getFizzBuzzList(String str) throws FizzBuzzException {
        
        LOGGER.info("Begins FizzBuzzLogic getFizzBuzzList method");
        
        String fizzBuzzList = "";
        
        try {
            if (str == null) {
                throw new FizzBuzzException("Number is null");
            }
            final int initNum = Integer.valueOf(str);
            if (initNum > limit) {
                LOGGER.error("Initial number is bigger than limit ("+limit+")");
                throw new FizzBuzzException("Initial number is bigger than limit ("+limit+")");
            } else if (initNum < 0 && !allowNegativeNumbers) {
                LOGGER.error("Initial number must be bigger than zero");
                throw new FizzBuzzException("Initial number must be bigger than zero");
            } else {
                fizzBuzzList = buildFizzBuzzList(initNum);
                saveListToResultsFile(fizzBuzzList);
            }
        } catch (NumberFormatException ex) {
            LOGGER.error("Not a number");
            throw new FizzBuzzException("Not a number", ex);
        }
        
        LOGGER.info("Ends FizzBuzzLogic getFizzBuzzList method");
        
        return fizzBuzzList;
    }
    
    /**
     * Builds the fizz-buzz list
     * @param initNum
     * @return 
     */
    private String buildFizzBuzzList(int initNum) {
        LOGGER.info("Begins FizzBuzzLogic buildFizzBuzzList method");
                
        final StringBuffer sb = new StringBuffer();
        
        for (int i = initNum;i<limit; i++) {
            if (isFizz(i) && isBuzz(i)) {
                sb.append(FIZZ_STRING+BUZZ_STRING);
            } else if (isFizz(i)) {
                sb.append(FIZZ_STRING);
            } else if (isBuzz(i)) {
                sb.append(BUZZ_STRING);
            } else {
                sb.append(i);
            }
            if (i < limit-1) {
                sb.append(",");
            }
        }
        
        LOGGER.info("Ends FizzBuzzLogic buildFizzBuzzList method");
        
        return sb.toString();
    }
    
    /**
     * Saves the fizzbuzz list to the results file
     * @param fizzBuzzList 
     */
    private void saveListToResultsFile(String fizzBuzzList) {    
        
        LOGGER.info("Begins FizzBuzzLogic saveListToResultsFile method");    
        
        ResultFileWriter rfw = new ResultFileWriter(fizzBuzzList);
        rfw.run();
        
        LOGGER.info("Ends FizzBuzzLogic saveListToResultsFile method");
    }
           
    /**
     * Returns true if current number is divisible by three
     * @param num
     * @return true if current number is divisible by three
     */
    public boolean isFizz(int num) {
        LOGGER.info("FizzBuzzLogic isFizz method");
        return (num % FIZZ_MOD) == 0;
    }
    
    /**
     * Returns true if current number is divisible by five
     * @param num
     * @return true if current number is divisible by five
     */
    public boolean isBuzz(int num) {
        LOGGER.info("FizzBuzzLogic isBuzz method");
        return (num % BUZZ_MOD) == 0;
    }
    
}
