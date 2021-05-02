package com.splash.utils;

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
}
