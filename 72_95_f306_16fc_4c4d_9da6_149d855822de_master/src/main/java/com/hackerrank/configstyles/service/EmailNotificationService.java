package com.hackerrank.configstyles.service;

import org.springframework.stereotype.Service;

@Service("emailNotificationService")
public class EmailNotificationService implements NotificationService {
    private String serviceName="EMAIL_SERVICE";

    public EmailNotificationService(String serviceName) {
        this.serviceName = serviceName;
    }

    public EmailNotificationService() {
	}

	public ServiceResponse sendNotification(String notification) {
        return new ServiceResponse(serviceName, notification);
    }

}
