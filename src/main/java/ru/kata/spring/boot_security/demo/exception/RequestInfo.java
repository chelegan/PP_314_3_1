package ru.kata.spring.boot_security.demo.exception;

public class RequestInfo {

    private String information;

    public RequestInfo() {
    }

    public RequestInfo(String information) {
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

}
