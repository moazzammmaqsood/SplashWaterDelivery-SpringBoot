package com.splash.utils;

import com.splash.domain.constants.AppConstants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

public class Utils {

	
	
	public static String generateRandomnumber(int num) {
		
		
		String randomnum="";
		Random rand=new Random();
		for(int i=0;i<num;i++) {
			randomnum=randomnum + rand.nextInt(10);
			
		}
		
		
		return randomnum;
		
	}

	public static Date StringtoDate(String lastdelivery) throws ParseException {
				
		Date date1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(lastdelivery+ " 10:00:00");
		
        return date1;
	}

	public static String Datetostring(Date date) { 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		return strDate;
	}

	public static String getsmstext(String message, Map<String,String> map){

		message=message.replace(AppConstants.NOOFBOTTLEKEY,map.get(AppConstants.NOOFBOTTLEKEY));
		message=message.replace(AppConstants.VENDORNAMEKEY,map.get(AppConstants.VENDORNAMEKEY));
		message=message.replace(AppConstants.DATEKEY,map.get(AppConstants.DATEKEY));
		message=	message.replace(AppConstants.NAMEKEY,map.get(AppConstants.NAMEKEY));
		message=message.replace(AppConstants.BOTTLESRECIEVEDKEY,map.get(AppConstants.BOTTLESRECIEVEDKEY));
		message=message.replace(AppConstants.PAYMENTRECIEVED,map.get(AppConstants.PAYMENTRECIEVED));
		message=message.replace(AppConstants.REMAININGBALANCEKEY,map.get(AppConstants.REMAININGBALANCEKEY));
		return message;
	}
	public static String getdatetostring(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public static String fetchYearAndMonth(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		String strDate = dateFormat.format(date);
		return strDate;
	}

}
