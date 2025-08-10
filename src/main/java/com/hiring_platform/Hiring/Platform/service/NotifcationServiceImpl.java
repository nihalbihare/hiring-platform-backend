package com.hiring_platform.Hiring.Platform.service;

import com.hiring_platform.Hiring.Platform.dto.NotificationDTO;
import com.hiring_platform.Hiring.Platform.dto.NotificationStatus;
import com.hiring_platform.Hiring.Platform.entity.Notification;
import com.hiring_platform.Hiring.Platform.entity.Utilities;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;
import com.hiring_platform.Hiring.Platform.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service("notifcationService")
public class NotifcationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public void sendNotifcation(NotificationDTO notificationDTO) throws JobPortalException {
        notificationDTO.setId(Utilities.getNextSequence("notification"));
        notificationDTO.setStatus(NotificationStatus.UNREAD);
        notificationDTO.setTimeStamp(LocalDateTime.now());
        notificationRepository.save(notificationDTO.toEntity());
    }

    @Override
    public List<Notification> getUnreadNotification(Long userId) {
        return notificationRepository.findByUserIdAndStatus(userId , NotificationStatus.UNREAD);
    }
    @Override
    public void readNotification(Long id) throws JobPortalException {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(()->new JobPortalException("No Notification Found"));
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
    }
}
