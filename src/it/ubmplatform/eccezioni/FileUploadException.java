package it.ubmplatform.eccezioni;

import javax.servlet.ServletException;

public class FileUploadException extends ServletException {
	
	public FileUploadException(){
		super();
	}
	
	public FileUploadException(String msg){
		super(msg);
	}
}
