package com.fizzbuzz.logic;

import com.fizzbuzz.exception.CustomException;
import com.fizzbuzz.file.FileManager;
import org.apache.log4j.Logger;

/**
 *
 * @author hugo
 */
public class FizzBuzzLogic {
    
    private static final Logger LOGGER = Logger.getLogger(FizzBuzzLogic.class);
    
    private static final long serialVersionUID = 7718828513143293558L;
    
    private static final int FIZZ_MOD = 3;
    private static final int BUZZ_MOD = 5;
        
    private static final String FIZZ_STRING = "fizz";
    private static final String BUZZ_STRING = "buzz";
    
    /*
    private final int limit = Integer.parseInt(FileManager.loadProperty("limit"));
    private final boolean allowNegativeNumbers = Boolean.parseBoolean(FileManager.loadProperty("allowNegativeNumbers"));
    */
    private final int limit = 37;
    private final boolean allowNegativeNumbers = true;

    /**
     * Main method
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            FizzBuzzLogic fuzz = new FizzBuzzLogic();   
            System.out.println(fuzz.getNumList("5"));
        } catch (CustomException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /*** 
     * Constructor
     */
    public FizzBuzzLogic(){
        
    }
    
    /**
     * Returns the list of the correct Fizz Buzz serie
     * @param str
     * @return the String list of the correct Fizz Buzz serie
     * @throws CustomException 
     */
    public String getNumList(String str) throws CustomException {
        
        LOGGER.info("Starts fuzz method");
                
        final StringBuffer sb = new StringBuffer();
        
        try {
            if (str == null) {
                throw new CustomException("Number is null");
            }
            int num = Integer.valueOf(str);
            if (num > limit) {
                throw new CustomException("Number bigger than limit");
            } else if (num < 0 && !allowNegativeNumbers) {
                throw new CustomException("Number must be bigger than zero");
            } else {
                for (int i = num;i<limit; i++) {
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
            }
        } catch (NumberFormatException ex) {
            throw new CustomException("Not a number", ex);
        }
        
        LOGGER.info("Ends fuzz method");
                
        FileManager.writeToFile(sb.toString());
        
        return sb.toString();
    }
           
    /**
     * Returns true if current number is divisible by three
     * @param num
     * @return true if current number is divisible by three
     */
    public boolean isFizz(int num) {
        LOGGER.info("isFizz method");
        return (num % FIZZ_MOD) == 0;
    }
    
    /**
     * Returns true if current number is divisible by five
     * @param num
     * @return true if current number is divisible by five
     */
    public boolean isBuzz(int num) {
        LOGGER.info("isBuzz method");
        return (num % BUZZ_MOD) == 0;
    }
    
}
