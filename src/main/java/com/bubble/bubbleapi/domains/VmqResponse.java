package com.bubble.bubbleapi.domains;

import lombok.Data;

public abstract class VmqResponse {
    protected Object result;

    public VmqResponse(Object result) {
        this.result = result;
    }

    public VmqResponse() {
        this.result = null;
    }
}
