package com.splash.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public static Date DatetoString(String lastdelivery) throws ParseException {
		Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(lastdelivery);
        return date1;
	}
}
