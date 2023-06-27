package com.bubble.bubbleapi.web.gql.errors;

import java.io.Serial;

public class DeviceAlreadyUsedException extends BadRequestAlertException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DeviceAlreadyUsedException() {
        super(ErrorConstants.DEVICE_ALREADY_USED_TYPE, "Device is already in use!", "userManagement", "deviceexists");
    }
}
