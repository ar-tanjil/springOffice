package com.spring.office.notification.notificaion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
    List<NotificationEntity> findByChatId(String chatId);

    List<NotificationEntity> findByRecipientId(String admin);
}
