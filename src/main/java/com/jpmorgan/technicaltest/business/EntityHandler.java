package com.jpmorgan.technicaltest.business;

import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.technicaltest.bo.EntityDetails;
import com.jpmorgan.technicaltest.exception.EntityDetailsException;
import com.jpmorgan.technicaltest.validation.ReportValidation;

public class EntityHandler {
	
	private EntityDetails entityDeatils;
	private List<EntityDetails> entityList = new ArrayList<EntityDetails>();
	
	/**
	 * This function create entity details object and validate the entity details.
	 * @param entityDetailsLine
	 * @throws EntityDetailsException
	 */
	public void processEntity(String entityDetailsLine) throws EntityDetailsException
	{
		entityDeatils = parseEntityDetails(entityDetailsLine);
		ReportValidation.validate(entityDeatils);
		entityList.add(entityDeatils);
	}
	
	/**
	 * Calls to produce the report
	 */
	public void produceReport()
	{
		ReportProcess.incomingSettled(entityList);
	}
	
	/**
	 * Return the entity details in a list
	 * @return
	 */
	public List<EntityDetails> getEntityList() {
		return entityList;
	}
	
	/**
	 * Process the line received from input file.
	 * @param entityDetailsLine
	 * @return
	 * @throws EntityDetailsException
	 */
	 private EntityDetails parseEntityDetails(String entityDetailsLine) throws EntityDetailsException{
		 	EntityDetails entityDetails = null;
	        if (entityDetailsLine == null || entityDetailsLine.isEmpty()) {
	            throw new EntityDetailsException("The line is not valid");
	        }
	        String[] messageArray = entityDetailsLine.trim().split(",");
	        if(messageArray.length != 7)
	        {
	        	 throw new EntityDetailsException("The line doesn't contain full information");
	        }
	        else
	        {
	        	entityDetails = new EntityDetails();
	        	entityDetails.setEntityName(messageArray[0].trim());
	        	entityDetails.setBuyOrSell(messageArray[1].trim().charAt(0));
	        	entityDetails.setCurrency(messageArray[3].trim());
	        	try
	        	{
	        	entityDetails.setAgreedFix(Double.parseDouble(messageArray[2].trim()));
	        	entityDetails.setUnits(Integer.parseInt(messageArray[4].trim()));
	        	entityDetails.setPricePerUnit(Double.parseDouble(messageArray[5].trim()));
	        	entityDetails.setIstructionDate(ReportValidation.covertStingToDate(messageArray[6].trim()));
	        	}
	        	catch(Exception e)
	        	{
	        		throw new EntityDetailsException("The input parameter is not valid");
	        	}
	        }
	        
	        return entityDetails;
	    }

	
}
