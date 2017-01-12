package com.oneg.whsquared.util;

public enum WHStatus {
SUCCESS("Success"), FAILURE("Failure"); 
	private final String value;
	
	WHStatus(String value){
		this.value=value;
	}
	
	public String value(){
		return this.value;
	}
}
