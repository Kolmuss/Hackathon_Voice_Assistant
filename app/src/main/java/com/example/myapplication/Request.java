package com.example.myapplication;

import java.util.ArrayList;

public class Request {
    private Long requestId;
    private ArrayList<String> keyWords;
    private ArrayList<Long> responseIds;

    public Request(Long requestId, ArrayList<String> keyWords, ArrayList<Long> responseIds) {
        this.requestId = requestId;
        this.keyWords = keyWords;
        this.responseIds = responseIds;
    }

    public Long getRequestId() {
        return requestId;
    }

    public ArrayList<String> getKeyWords() {
        return keyWords;
    }

    public ArrayList<Long> getResponseIds() {
        return responseIds;
    }
}


