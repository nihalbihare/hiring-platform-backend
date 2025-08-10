package com.hiring_platform.Hiring.Platform.service;

import com.hiring_platform.Hiring.Platform.dto.NotificationDTO;
import com.hiring_platform.Hiring.Platform.entity.Notification;
import com.hiring_platform.Hiring.Platform.exception.JobPortalException;

import java.util.List;

public interface NotificationService {
    public void  sendNotifcation(NotificationDTO notificationDTO) throws JobPortalException;
    public List<Notification> getUnreadNotification(Long userId);
    public void readNotification(Long id) throws JobPortalException;
}
