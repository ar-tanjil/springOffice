package com.spring.office.notification.notificaion;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private String senderId;
    private String recipientId;

    @Enumerated(EnumType.STRING)
    private Type type;
    private String content;
}
