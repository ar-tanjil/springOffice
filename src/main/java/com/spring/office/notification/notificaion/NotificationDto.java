package com.spring.office.notification.notificaion;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationDto {
    private Long id;
    private String senderId;
    private String recipientId;
    private String content;
}
