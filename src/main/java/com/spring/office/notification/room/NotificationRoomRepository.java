package com.spring.office.notification.room;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRoomRepository extends JpaRepository<NotificationRoom, Long> {
    Optional<NotificationRoom> findBySenderIdAndRecipientId(String senderId, String recipientId);
}
