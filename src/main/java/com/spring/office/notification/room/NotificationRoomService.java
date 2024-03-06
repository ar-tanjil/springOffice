package com.spring.office.notification.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationRoomService {

    private final NotificationRoomRepository notificationRepository;


    public Optional<String> getNotificationId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        return notificationRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(NotificationRoom::getChatId)
                .or(() -> {
                    if(createNewRoomIfNotExists) {
                        var chatId = createChatId(senderId, recipientId);
                        return Optional.of(chatId);
                    }

                    return  Optional.empty();
                });
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        NotificationRoom senderRecipient = NotificationRoom
                .builder()
                .chatId(chatId)
                .senderId(senderId)
                .recipientId(recipientId)
                .build();

        NotificationRoom recipientSender = NotificationRoom
                .builder()
                .chatId(chatId)
                .senderId(recipientId)
                .recipientId(senderId)
                .build();

        notificationRepository.save(senderRecipient);
        notificationRepository.save(recipientSender);

        return chatId;
    }




}
