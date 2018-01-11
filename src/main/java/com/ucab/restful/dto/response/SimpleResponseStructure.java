package com.ucab.restful.dto.response;

public class SimpleResponseStructure<T> extends AbstractBaseResponseStructure{
	
	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
