package com.bubble.bubbleapi.domains;

import lombok.Data;

@Data
public class VmqResponse {
    private final Object result;

    public VmqResponse() {
        this.result = "ok";
    }

    public VmqResponse(Object result) {
        this.result = result;
    }
}
