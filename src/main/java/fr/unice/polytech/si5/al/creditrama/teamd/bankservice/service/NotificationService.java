package fr.unice.polytech.si5.al.creditrama.teamd.bankservice.service;

import fr.unice.polytech.si5.al.creditrama.teamd.bankservice.kafka.NotificationStreams;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
@EnableBinding(NotificationStreams.class)
public class NotificationService {
    private final NotificationStreams notificationStreams;

    public NotificationService(NotificationStreams notificationStreams) {
        this.notificationStreams = notificationStreams;
    }

    public void sendGreeting(String email) {
        MessageChannel messageChannel = notificationStreams.outboundNotification();

        messageChannel.send(MessageBuilder
                .withPayload(email)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());
    }
}