package com.example.myapplication;

import java.util.ArrayList;

public class Response {
    private Long responseId;
    private Long requestId;
    private String operation;
    private int operationID;

    public Response(Long responseId, Long requestId, String operation, int operationID) {
        this.responseId = responseId;
        this.requestId = requestId;
        this.operation = operation;
        this.operationID = operationID;
    }

    public Long getRequestId() {
        return requestId;
    }

    public String getOperation() {
        return operation;
    }

    public int getOperationID() {
        return operationID;
    }

    public Long getResponseId() {
        return responseId;
    }

    @Override
    public String toString() {
        return operation + "\n" +
                "operation:" + operationID;
    }
}
