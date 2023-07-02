package com.bubble.bubbleapi.web.rest;

import com.bubble.bubbleapi.domains.*;
import com.bubble.bubbleapi.entity.Participant;
import com.bubble.bubbleapi.service.RoomService;
import com.bubble.bubbleapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/webhook")
public class WebhookResource {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RoomService roomService;

    @PostMapping("/vmq/auth_on_register")
    public VmqResponse authOnRegister(@RequestBody VmqRegisterRequestBody body) {
        Boolean valid = jwtUtil.validateToken(body.username());
        if (valid) {
            return new VmqResponse();
        }
        return new VmqErrorResponse();
    }

    @PostMapping("/vmq/auth_on_subscribe")
    public VmqResponse authOnSubscribe(@RequestBody VmqSubscribeRequestBody body) {
        List<Topic> topicList = body.topics();
        List<Topic> finalTopicList = topicList.stream().filter(topic -> {
            String topicName = topic.topic();
            if (topicName.startsWith("bubble/")) {
                String[] topicNameParts = topicName.split("/");
                if (topicNameParts.length == 2) {
                    UUID roomId = UUID.fromString(topicNameParts[1]);
                    String userToken = body.username();
                    UUID userId = jwtUtil.extractUserId(userToken);
                    Participant participant = roomService.findParticipantByRoomAndParticipant(roomId, userId);
                    return participant != null;
                }
            }
            return false;
        }).toList();

        if (finalTopicList.size() == 0) {
            return new VmqErrorResponse();
        }

        return new VmqTopicsResponse("ok", finalTopicList);
    }

    @PostMapping("/vmq/auth_on_publish")
    public VmqResponse authOnPublish(@RequestBody VmqPublishRequestBody body) {
        String topicName = body.topic();
        if (topicName.startsWith("bubble/")) {
            String[] topicNameParts = topicName.split("/");
            if (topicNameParts.length == 2) {
                UUID roomId = UUID.fromString(topicNameParts[1]);
                String userToken = body.username();
                UUID userId = jwtUtil.extractUserId(userToken);
                Participant participant = roomService.findParticipantByRoomAndParticipant(roomId, userId);
                if (participant != null) {
                    System.out.println("Publishing to topic: " + topicName);
                    return new VmqResponse("ok");
                }
            }
        }

        return new VmqErrorResponse();
    }
}
