package it.ubmplatform.eccezioni;

import javax.servlet.ServletException;

public class InsertFailedException extends ServletException {

	public InsertFailedException(){
		super();
	}
	
	public InsertFailedException(String msg){
		super(msg);
	}
	
}
