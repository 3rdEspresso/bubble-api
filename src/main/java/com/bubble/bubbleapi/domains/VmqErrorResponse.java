package com.bubble.bubbleapi.domains;

import java.util.HashMap;

public class VmqErrorResponse extends VmqResponse {
    public VmqErrorResponse() {
        super(new HashMap<>() {
            {
                put("error", "Couldn't Authenticate");
            }
        });
    }

    public VmqErrorResponse(String reason) {
        super(new HashMap<>() {
            {
                put("error", reason);
            }
        });
    }
}
