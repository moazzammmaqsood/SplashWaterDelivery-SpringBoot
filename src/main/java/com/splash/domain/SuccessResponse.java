package com.splash.domain;

public class SuccessResponse {

    private String successMessage;

    public String success() {
        return successMessage;
    }

    
    public String getSuccessMessage() {
		return successMessage;
	}


	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}


	public SuccessResponse(String successMessage)
    {
        this.successMessage = successMessage;
    }
}
