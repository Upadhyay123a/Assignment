package com.speechify.service;

import com.speechify.model.Client;
import com.speechify.model.User;

public class CreditLimitService {
    public void assignCreditLimit(User user, Client client) {
        switch (client.getName()) {  // FIXED: use getName()
            case "VeryImportantClient":
                user.setCreditLimit(Integer.MAX_VALUE); // FIXED: Integer not Double
                break;
            case "ImportantClient":
                user.setCreditLimit(20000); // doubled default
                break;
            default:
                user.setCreditLimit(10000);
        }
    }
}
