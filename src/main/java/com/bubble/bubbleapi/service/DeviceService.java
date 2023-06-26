package com.bubble.bubbleapi.service;

import com.bubble.bubbleapi.entity.Device;
import com.bubble.bubbleapi.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public Optional<Device> findDeviceById(String id) {
        return deviceRepository.findDeviceById(id);
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }
}
