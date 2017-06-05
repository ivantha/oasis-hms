package com.oasis.validation;

import com.oasis.ui.component.Notification;

public interface Validator {
    boolean isValid();

    void refreshState();

    Notification getInvalidArgumentNotification();
}
