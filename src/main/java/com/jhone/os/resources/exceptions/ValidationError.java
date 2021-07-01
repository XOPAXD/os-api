package com.jhone.os.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FiledMessage> erros = new ArrayList<>();

	public ValidationError() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
		// TODO Auto-generated constructor stub
	}

	public List<FiledMessage> getErros() {
		return erros;
	}

	public void addErros(String fieldName,String message) {
		this.erros.add(new FiledMessage(fieldName,message));
	}
	
	
}
