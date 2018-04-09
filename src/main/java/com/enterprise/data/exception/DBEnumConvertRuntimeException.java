package com.enterprise.data.exception;

/**
 * @author tommy
 */
public class DBEnumConvertRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -6774501922165042769L;

    private String message;

    public DBEnumConvertRuntimeException(String message) {
        super(message);

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
