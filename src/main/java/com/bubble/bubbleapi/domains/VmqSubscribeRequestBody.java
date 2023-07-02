package com.bubble.bubbleapi.domains;

import java.util.List;

public record VmqSubscribeRequestBody(
        String client_id,
        String mountpoint,
        String username,
        List<Topic> topics
) {}

