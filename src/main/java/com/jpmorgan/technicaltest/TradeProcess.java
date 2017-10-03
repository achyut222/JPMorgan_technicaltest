package com.jpmorgan.technicaltest;

import java.io.BufferedReader;
import java.io.FileReader;

import com.jpmorgan.technicaltest.business.EntityHandler;
import com.jpmorgan.technicaltest.exception.EntityDetailsException;

public class TradeProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EntityHandler entitiyHandler = new EntityHandler();

        // Read inputs from test file
		//		
		/**
		 * The input test file should be in below format separate by commas
		 * 1st parameter- entity name (String), 
		 * 2nd parameter -Buy or Sell (B or S) (Single Character),
		 * 3rd parameter - Agreed Fix(double value), 
		 * 4th parameter - Currency(String)
		 * 5th Parameter - Units(Integer), 
		 * 6th parameter - Price per unit(Integer)
		 * 7th parameter - Instruction Date(String in "DD/MM/YYYY" format) 
		 */
        try {
            String line;
            BufferedReader inputFile = new BufferedReader(new FileReader("testInput/input.txt"));
            while((line = inputFile.readLine()) != null) {
                // process message for each sale notification
            	entitiyHandler.processEntity(line);
            }
            entitiyHandler.produceReport();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        } catch (EntityDetailsException e) {
			System.out.println(e.getMessage());
		}

	}

}
