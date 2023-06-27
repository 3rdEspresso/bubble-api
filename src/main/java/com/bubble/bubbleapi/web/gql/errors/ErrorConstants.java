package com.bubble.bubbleapi.web.gql.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://bubble.com/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI USERNAME_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/username-already-used");
    public static final URI DEVICE_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/device-already-used");

    private ErrorConstants() {}
}

