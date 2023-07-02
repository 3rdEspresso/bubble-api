package com.bubble.bubbleapi.domains;

public record VmqPublishRequestBody(
        String client_id,
        String mountpoint,
        String username,
        String topic,
        String payload,
        int qos,
        boolean retain
) { }
