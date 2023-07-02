package com.bubble.bubbleapi.domains;

public record VmqRegisterRequestBody(
        String username,
        String password,
        String peer_addr,
        int peer_port,
        String mountpoint,
        String client_id,
        boolean clean_session
) {}
