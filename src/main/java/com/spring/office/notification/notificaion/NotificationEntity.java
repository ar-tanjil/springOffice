package com.spring.office.notification.notificaion;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class NotificationEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String content;

    @Enumerated(EnumType.STRING)
    private Type type;
    private Date timestamp;
}
