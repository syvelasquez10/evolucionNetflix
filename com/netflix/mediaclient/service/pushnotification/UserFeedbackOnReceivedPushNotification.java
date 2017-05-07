// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

public enum UserFeedbackOnReceivedPushNotification
{
    canceled("canceled"), 
    opened("opened");
    
    private String mValue;
    
    private UserFeedbackOnReceivedPushNotification(final String mValue) {
        this.mValue = mValue;
    }
    
    public String getValue() {
        return this.mValue;
    }
}
