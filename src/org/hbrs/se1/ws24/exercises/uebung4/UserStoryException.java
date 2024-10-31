package org.hbrs.se1.ws24.exercises.uebung4;

public class UserStoryException extends Exception {
	public enum ExceptionType {
		WrongPriorityValue
	}

	private ExceptionType type;

	public UserStoryException(ExceptionType type, String msg) {
		super(msg);
		this.type = type;
	}

	public ExceptionType getExceptionType() {
		return type;
	}
}
