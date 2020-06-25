package com.example.bedsysteem.data.entity;

public class ResponseMessage {

    public enum StatusTypes{
        SUCCESS,
        FAILED
    }
    private StatusTypes statusType;
    private String message;

    public StatusTypes getStatusType() { return statusType; }
    public void setStatusType(StatusTypes statusType) { this.statusType = statusType; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
