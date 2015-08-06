package com.tubemogul.platform.tubebnb.exceptions;

/**
 * Created by kamel.dabwan on 8/6/15.
 */
public class ErrorDisplay {

    private String errorMessage;
    private int httpCode;

    public ErrorDisplay(String errorMessage, int httpCode) {
        this.errorMessage = errorMessage;
        this.httpCode = httpCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }



}
