package com.oasis.services;

import com.oasis.common.Session;
import com.oasis.model.Employee;
import com.oasis.ui.UIName;

public class UserServices {
    public static Employee getUser(UIName uiName, String username) {
        return Session.userConnector.getUser(username);
    }
}
