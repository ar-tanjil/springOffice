package com.spring.office.notification;

import com.spring.office.notification.notificaion.NotificationDto;
import com.spring.office.notification.notificaion.NotificationEntity;
import com.spring.office.notification.notificaion.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;
    private final NotificationService notificationService;

    @MessageMapping("/admin")
    public void processMessage(@Payload NotificationEntity notification) {
        NotificationEntity savedMsg = notificationService.save(notification);
        messagingTemplate.convertAndSendToUser(
                notification.getRecipientId(), "/topic",
                new NotificationDto(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getType(),
                        savedMsg.getContent()
                )
        );
    }



    public void sendNotificationToAdmin(NotificationEntity notification){
        NotificationEntity savedMsg = notificationService.save(notification);
        messagingTemplate.convertAndSendToUser(
                notification.getRecipientId(), "/topic",
                new NotificationDto(
                        savedMsg.getId(),
                        savedMsg.getSenderId(),
                        savedMsg.getRecipientId(),
                        savedMsg.getType(),
                        savedMsg.getContent()
                )
        );
    }


@GetMapping("/messages/{senderId}/{recipientId}")
public ResponseEntity<List<NotificationEntity>> findChatMessages(@PathVariable String senderId,
                                                                 @PathVariable String recipientId) {
    return ResponseEntity
            .ok(notificationService.findChatMessages(senderId, recipientId));
}

@GetMapping("/messages/admin")
    public ResponseEntity<List<NotificationEntity>> findAdminMessages(){
        return ResponseEntity.ok(notificationService.findAdminMessage());
}






}
