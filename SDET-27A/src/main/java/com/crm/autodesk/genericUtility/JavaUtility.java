package com.crm.autodesk.genericUtility;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author Punith
 *
 */
public class JavaUtility
{
	/**
	 *  It is Used to generate random number
	 * @return int data
	 */
	public int getRandomNumber()
	{
		Random random=new Random();
		int intRandom=random.nextInt(10000);
		return intRandom;
	}
	/**
	 * used to get System date & Time in IST format
	 */
	public String getSystemDateAndTime()
	{
		Date date=new Date();
		return date.toString();
		
	}
	
	/**
	 * Used To get System date in YYYY MM DD Format
	 * @return
	 */
	public String getSystemDateWithFormate()
	{
		Date date=new Date();
		String dateAndTime=date.toString();
		String YYYY=dateAndTime.split(" ")[5];
		String DD=dateAndTime.split(" ")[2];
		int MM=date.getMonth()+1;
		String finalFormate=YYYY+"-"+MM+"-"+DD;
		return finalFormate;
	}
}
