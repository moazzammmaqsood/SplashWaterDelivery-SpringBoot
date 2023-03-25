package com.splash.utils;

import com.splash.domain.constants.AppConstants;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utils {

	static RestTemplate restTemplate = new RestTemplate();
	
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

	public static HttpEntity<String> sendSmsUtil(String sms, String recieverPhoneNumber){


		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(Constants.VeevoUrl)
				.queryParam("hash", "{hash}")
				.queryParam("receivenum", "{receivenum}")
				.queryParam("sendernum", "{sendernum}")
				.queryParam("textmessage", "{textmessage}")
				.encode()
				.toUriString();

		Map<String, String > params = new HashMap<>();
		params.put("hash", Constants.ApiKey);
		params.put("receivenum", recieverPhoneNumber);
		params.put("sendernum", Constants.Phone);
		params.put("textmessage", sms);

		HttpEntity<String> response = restTemplate.exchange(
				urlTemplate,
				HttpMethod.GET,
				entity,
				String.class,
				params);

		return response;

	}



	public static HttpEntity<String> sendSmsUtilSendPk(String sms, String recieverPhoneNumber){


		HttpHeaders headers = new HttpHeaders();
		headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<?> entity = new HttpEntity<>(headers);

		String urlTemplate = UriComponentsBuilder.fromHttpUrl(Constants.sendpk)
				.queryParam("username", "{username}")
				.queryParam("password", "{password}")
				.queryParam("Masking", "{sender}")
				.queryParam("mobile", "{mobile}")
				.queryParam("message", "{message}")
				.encode()
				.toUriString();

		Map<String, String > params = new HashMap<>();
		params.put("username", "+92324826");
		params.put("password", recieverPhoneNumber);
		params.put("Masking", Constants.Phone);
		params.put("mobile", recieverPhoneNumber);
		params.put("message", sms);

		HttpEntity<String> response = restTemplate.exchange(
				urlTemplate,
				HttpMethod.GET,
				entity,
				String.class,
				params);

		return response;

	}

}
