package com.bubble.bubbleapi.web.gql.errors;

import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

public class UsernameAlreadyUsedException extends BadRequestAlertException {

    @Serial
    private static final long serialVersionUID = 1L;

    public UsernameAlreadyUsedException() {
        super(ErrorConstants.USERNAME_ALREADY_USED_TYPE, "Username is already in use!", "userManagement", "usernameexists");
    }
}
