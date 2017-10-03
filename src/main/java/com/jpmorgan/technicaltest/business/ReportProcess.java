package com.jpmorgan.technicaltest.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.jpmorgan.technicaltest.bo.EntityDetails;
import com.jpmorgan.technicaltest.constants.ReportConstants;
import com.jpmorgan.technicaltest.interfaces.Settled;

public class ReportProcess {
	
	/**
	 * 
	 * @param entityDetailsList
	 */
	public static void incomingSettled(List<EntityDetails> entityDetailsList)
	{
		Collections.sort(entityDetailsList);
		List<EntityDetails> usdIncommingSet = new ArrayList<EntityDetails>();
		List<EntityDetails> usdOutgingSet = new ArrayList<EntityDetails>();
		
		Settled incoming = (entityList) ->{
			for(EntityDetails entityDetails : entityList)
			{
				if(ReportConstants.BUY == entityDetails.getBuyOrSell() && ReportConstants.CURRENCY_USD.equals(entityDetails.getCurrency()))
				{
					usdIncommingSet.add(entityDetails);
				}
				else if(ReportConstants.SELL == entityDetails.getBuyOrSell() && ReportConstants.CURRENCY_USD.equals(entityDetails.getCurrency()))
				{
					usdOutgingSet.add(entityDetails);
				}
			}
		};
		incoming.settle(entityDetailsList);
		displayIncomingUSDReports(usdIncommingSet);
		displayOutgoingUSDReports(usdOutgingSet);
		displayReports(entityDetailsList);
	}
	
	/**
	 * Display the incoming USD reports.
	 * @param incomingUSDList
	 */
	public static void displayIncomingUSDReports(List<EntityDetails> incomingUSDList)
	{
		System.out.println("-----------Incoming USD Trading---------");
		System.out.println("Entity"+ "||"+"Buy/Sell"+"||"+"Agreed Fix"+"||"+"Currency"+"||"+"Instructon Date"+"||"+"Settlement Date"+"||"+"Units"+"||"+"Price per Amount"+ "||"+"Trade Amount");
		for(EntityDetails details:incomingUSDList)
		{
			System.out.println(formatMessage(details));
		}
	}
	
	/**
	 * Display outgoing USD reports
	 * @param outgoingUSDList
	 */
	public static void displayOutgoingUSDReports(List<EntityDetails> outgoingUSDList)
	{
		System.out.println("-----------Outgoing USD Trading---------");
		System.out.println("Entity"+ "||"+"Buy/Sell"+"||"+"Agreed Fix"+"||"+"Currency"+"||"+"Instructon Date"+"||"+"Settlement Date"+"||"+"Units"+"||"+"Price per Amount"+ "||"+"Trade Amount");
		for(EntityDetails details:outgoingUSDList)
		{
			System.out.println(formatMessage(details));
		}
	}
	
	/**
	 * Display the whole report
	 * @param fullList
	 */
	public static void displayReports(List<EntityDetails> fullList)
	{
		System.out.println("-----------Full Trade reports---------");
		System.out.println("Entity"+ "||"+"Buy/Sell"+"||"+"Agreed Fix"+"||"+"Currency"+"||"+"Instructon Date"+"||"+"Settlement Date"+"||"+"Units"+"||"+"Price per Amount"+ "||"+"Trade Amount");
		for(EntityDetails details:fullList)
		{
			System.out.println(formatMessage(details));

		}
	}
	
	/**
	 * Format the message to display in the console
	 * @param details
	 * @return
	 */
	private static StringBuilder formatMessage(EntityDetails details)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(details.getEntityName()+"   ||  "+details.getBuyOrSell()+"     ||  "+details.getAgreedFix()+"    || ");
		sb.append(details.getCurrency()+"    || "+ReportConstants.DATE_FORMAT.format(details.getIstructionDate())+"   ||"+ReportConstants.DATE_FORMAT.format(details.getSettlementDate())+"    || ");
		sb.append(details.getUnits()+" || "+details.getPricePerUnit()+"         || "+details.getTradeAmount());
		return sb;
	}

}
