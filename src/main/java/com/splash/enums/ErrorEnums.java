package com.splash.enums;

public enum ErrorEnums {
	//401 user not found
	//402 user disabled
	//500 Server Error	

	USER_NOT_FOUND{
		@Override
		public int getResponseCode() {
		return 401;
		}

		@Override
		public String getError() {
			return "User Not Found";
		}

		@Override
		public String getErrorDescription() {
			return "Unable to find user with the provided credentials";
		}
	},USER_DISABLED{

		@Override
		public int getResponseCode() {
			return 402;
		}

		@Override
		public String getError() {
			return "User Disabled ";
		}

		@Override
		public String getErrorDescription() {
			return "please contact admin to enable user";
					
		}
		
	},SERVER_ERROR{

		@Override
		public int getResponseCode() {
			return 500;
		}

		@Override
		public String getError() {
			return "Server Error ";
		}

		@Override
		public String getErrorDescription() {
			return "Server Error please contact admin";
		}
		
		};
	
	
	 public abstract int getResponseCode(); 
	public abstract String getError();
	public abstract String getErrorDescription();
	
}
