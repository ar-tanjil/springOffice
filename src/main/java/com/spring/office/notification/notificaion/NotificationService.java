package com.spring.office.notification.notificaion;

import com.spring.office.notification.room.NotificationRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;
    private final NotificationRoomService notificationRoomService;

    public NotificationEntity save(NotificationEntity notifyMessage) {
        var chatId = notificationRoomService
                .getNotificationId(notifyMessage.getSenderId(), notifyMessage.getRecipientId(), true)
                .orElseThrow(); // You can create your own dedicated exception
        notifyMessage.setChatId(chatId);
        repository.save(notifyMessage);
        return notifyMessage;
    }

    public List<NotificationEntity> findChatMessages(String senderId, String recipientId) {
        var chatId = notificationRoomService.getNotificationId(senderId, recipientId, false);
        return chatId.map(repository::findByChatId).orElse(new ArrayList<>());
    }



}
