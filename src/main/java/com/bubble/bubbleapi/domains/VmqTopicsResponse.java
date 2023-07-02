package com.bubble.bubbleapi.domains;

import lombok.Data;

import java.util.List;

public class VmqTopicsResponse extends VmqResponse {
    private final String result;
    private final List<Topic> topics;

    public VmqTopicsResponse() {
        this.result = "ok";
        this.topics = null;
    }

    public VmqTopicsResponse(String result, List<Topic> topics) {
        this.result = result;
        this.topics = topics;
    }
}
