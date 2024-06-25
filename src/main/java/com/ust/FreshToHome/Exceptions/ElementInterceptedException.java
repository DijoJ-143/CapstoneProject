package com.ust.FreshToHome.Exceptions;

public class ElementInterceptedException extends Exception {

	@Override
	public String getMessage() {
		return "Element not interactable";

	}

}