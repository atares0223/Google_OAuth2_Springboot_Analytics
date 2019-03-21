package com.lff.exception.sql;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends Exception{
	
	public ObjectNotFoundException(String msg){
		super(msg);
	}
	
	public ObjectNotFoundException(Class<?> c){
		super(c.getName() + " Not Found");
	}
}
