package com.develhope.spring.models;

public class ResponseValid extends Response{
    private Object data;

    public ResponseValid(int status, String message, Object data) {
        super(status, message);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
