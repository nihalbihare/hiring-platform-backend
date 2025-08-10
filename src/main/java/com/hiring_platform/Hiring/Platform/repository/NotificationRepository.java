package com.hiring_platform.Hiring.Platform.repository;

import com.hiring_platform.Hiring.Platform.dto.NotificationStatus;
import com.hiring_platform.Hiring.Platform.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, Long> {//Long is the type of the
    // ID (@Id) in your Notification class
public List<Notification> findByUserIdAndStatus(Long userId , NotificationStatus status);
}
