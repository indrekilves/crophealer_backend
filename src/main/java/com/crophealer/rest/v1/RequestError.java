package com.crophealer.rest.v1;

public enum RequestError {
	E0001("User name is missing."),
	E0002("User name exists."),
	E0003("Password is missing."),	
	E0004("Password is too weak."),
	E0005("Email is missing."),
	E0006("Email format is faulty.");
	
    private final String value;

    private RequestError(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
       return getValue();
    }	
}
