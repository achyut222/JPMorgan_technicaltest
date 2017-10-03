package com.jpmorgan.technicaltest.validation;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.jpmorgan.technicaltest.bo.EntityDetails;
import com.jpmorgan.technicaltest.constants.ReportConstants;
import com.jpmorgan.technicaltest.exception.ReportValidationException;

public class ReportValidation {
	
	/**
	 * This method used for set the settelemet date and amount
	 * @param entityTran
	 * @return
	 */
	public static EntityDetails validateAndSetEntity(EntityDetails entityTran)
	{
		entityTran = setSettlementDate(entityTran);
		entityTran = setTradeAmount(entityTran);
		return entityTran;
	}
	
	/**
	 * This function calculates the trade amount
	 * @param entityTran
	 * @return
	 */
	private static EntityDetails setTradeAmount(EntityDetails entityTran)
	{
		entityTran.setTradeAmount(entityTran.getUnits()*entityTran.getPricePerUnit()*entityTran.getAgreedFix());
		return entityTran;
	}
	
	/**
	 * This function set the settlement date as per the instruction date varying with currency.
	 * @param entityTran
	 * @return
	 */
	private static EntityDetails setSettlementDate(EntityDetails entityTran)
	{
		if(entityTran.getIstructionDate()==null)
		{
			entityTran.setIstructionDate(new Date());
		}
		Calendar cal = Calendar.getInstance();
        cal.setTime(entityTran.getIstructionDate());
     	
		if(ReportConstants.CURRENCY_AED.equals(entityTran.getCurrency().trim())||ReportConstants.CURRENCY_SAR.equals(entityTran.getCurrency().trim()))
		{
				if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.FRIDAY)
		        {
		        	cal.add(Calendar.DATE, 2);
		        }
				else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
				{
					cal.add(Calendar.DATE, 1);
				}
		}
		else
		{
			if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY)
	        {
	        	cal.add(Calendar.DATE, 2);
	        }
			else if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			{
				cal.add(Calendar.DATE, 1);
			}
		}
		
		entityTran.setSettlementDate(cal.getTime());
		return entityTran;
	}	

	/**
	 * Do the repective validattion as required
	 * @param details
	 * @throws ReportValidationException
	 */
	private static void validateReport(EntityDetails details) throws ReportValidationException
	{
		if(details.getBuyOrSell() != ReportConstants.BUY && details.getBuyOrSell() != ReportConstants.SELL)
		{
			throw new ReportValidationException("The Bur or sell option is invalid for the entity"+ details.getEntityName());
		}
		
		
	}
	
	/**
	 * This method used to validate the entity details attribute and set the entity.
	 * @param details
	 */
	public static void validate(EntityDetails details)
	{
		try
		{
		validateReport(details);
		}
		catch(ReportValidationException reportException)
		{
			System.out.println(reportException.getMessage());
		}
		validateAndSetEntity(details);
	}
	
	/*
	 * This function convert from date to String
	 */
	public static Date covertStingToDate(String strDate)
	{
		Date date = null;
		try {
			date = ReportConstants.INPUT_DATE_FORMAT.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return date;
	}

}
