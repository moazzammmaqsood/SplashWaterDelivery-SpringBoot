package com.splash.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(lastdelivery);
        return date1;
	}

	public static String Datetostring(Date date) { 
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		String strDate = dateFormat.format(date);  
		return strDate;
	}
}
