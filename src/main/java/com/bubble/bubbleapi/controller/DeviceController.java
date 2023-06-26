package com.bubble.bubbleapi.controller;

import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @QueryMapping
    public Device deviceByIMEI(@Argument String IMEI) {
        return deviceService.findDeviceByIMEI(IMEI).orElse(null);
    }
}
