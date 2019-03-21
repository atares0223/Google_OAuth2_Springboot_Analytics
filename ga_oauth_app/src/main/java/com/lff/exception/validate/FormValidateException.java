package com.lff.exception.validate;

@SuppressWarnings("serial")
public class FormValidateException extends Exception{
	public FormValidateException(String field , String reason){
		super(field + " is " + reason);
	}
}
