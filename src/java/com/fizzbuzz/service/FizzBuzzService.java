package com.fizzbuzz.service;

import com.fizzbuzz.exception.FizzBuzzException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import com.fizzbuzz.logic.FizzBuzzLogic;
import org.apache.log4j.Logger;

/**
 * REST Web Service
 *
 * @author hugo
 */
@Path("/fizzbuzz")
public class FizzBuzzService {
    
    private static final Logger LOGGER = Logger.getLogger(FizzBuzzService.class);

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Fizz
     */
    public FizzBuzzService() {
    }

    /**
     * Retrieves representation of an instance of api.Fizz
     * @param initNum
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{initNum}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@PathParam("initNum") String initNum) {
        String result = "";
        try {
            final FizzBuzzLogic fizzBuzz = new FizzBuzzLogic();
            result = fizzBuzz.getFizzBuzzList(initNum);
        } catch (FizzBuzzException ex) {
            result = ex.getMessage();
        }
        return result;
    }
    
}
