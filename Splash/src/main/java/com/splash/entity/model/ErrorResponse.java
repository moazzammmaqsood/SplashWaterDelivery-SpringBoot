package com.splash.entity.model;

public class ErrorResponse {

	int Responsecode;
	String Error;
	String ErrorDescriprtion;

	public int getResponsecode() {
		return Responsecode;
	}

	public void setResponsecode(int responsecode) {
		Responsecode = responsecode;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
	}

	public String getErrorDescriprtion() {
		return ErrorDescriprtion;
	}

	public void setErrorDescriprtion(String errorDescriprtion) {
		ErrorDescriprtion = errorDescriprtion;
	}

	@Override
	public String toString() {
		return "ErrorResponse [Responsecode=" + Responsecode + ", Error=" + Error + ", ErrorDescriprtion="
				+ ErrorDescriprtion + "]";
	}

	public ErrorResponse(int responsecode, String error, String errorDescriprtion) {
		super();
		Responsecode = responsecode;
		Error = error;
		ErrorDescriprtion = errorDescriprtion;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
