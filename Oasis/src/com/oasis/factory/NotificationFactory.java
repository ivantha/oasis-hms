package com.oasis.factory;

import com.oasis.ui.component.Notification;

public class NotificationFactory {
    public static Notification newNotification(String heading, String content, Notification.NotificationType notificationType, long timeout) {
        return new Notification(heading, content, notificationType, timeout);
    }

    public static Notification defaultSuccessfullyAddedNotification(String heading, String content) {
        Notification notification = new Notification(heading, content, Notification.NotificationType.SUCCESSFUL, 10000);
        return notification;
    }

    public static Notification defaultInvalidArguementNotification(String heading, String content) {
        Notification notification = new Notification(heading, content, Notification.NotificationType.ERROR, -1);
        return notification;
    }
}
