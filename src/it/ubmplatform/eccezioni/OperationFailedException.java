package it.ubmplatform.eccezioni;

import javax.servlet.ServletException;

public class OperationFailedException extends ServletException {

	public OperationFailedException(){
		super();
	}
	
	public OperationFailedException(String msg){
		super(msg);
	}
}
