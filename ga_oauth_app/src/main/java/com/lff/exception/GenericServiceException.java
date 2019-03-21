package com.lff.exception;

@SuppressWarnings("serial")
public class GenericServiceException extends Exception{
	public GenericServiceException(String msg){
		super(msg);
	}
	public GenericServiceException(){
		super("Service Error");
	}
}
