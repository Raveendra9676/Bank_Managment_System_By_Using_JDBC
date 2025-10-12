package org.bank.exception;

public class CustomerInvalidDataException extends RuntimeException {

	private String message;

	public CustomerInvalidDataException() {
	}

	public CustomerInvalidDataException(String message) {

		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomerInvalidDataException message=:" + message + "";
	}

}
