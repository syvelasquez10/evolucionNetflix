// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.pushnotification.UserFeedbackOnReceivedPushNotification;
import com.netflix.mediaclient.service.pushnotification.MessageData;

public interface CmpEventLogging
{
    void reportUserFeedbackOnReceivedPushNotification(final MessageData p0, final UserFeedbackOnReceivedPushNotification p1);
}
