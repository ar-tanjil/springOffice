package com.spring.office.notification.room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class NotificationRoom {

    @Id
    @GeneratedValue
    private Long id;
    private String chatId;
    private String senderId;
    private String recipientId;

}
