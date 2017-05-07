// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import java.util.Collections;
import com.netflix.mediaclient.ui.iris.notifications.type.NewSeasonAlert;
import java.util.HashMap;
import com.netflix.mediaclient.ui.iris.notifications.type.BaseNotification;
import com.netflix.model.leafs.social.IrisNotificationSummary$NotificationTypes;
import java.util.Map;

public class NotificationsStaticFactory
{
    private static final Map<IrisNotificationSummary$NotificationTypes, BaseNotification> types;
    
    static {
        final HashMap<IrisNotificationSummary$NotificationTypes, BaseNotification> hashMap = new HashMap<IrisNotificationSummary$NotificationTypes, BaseNotification>();
        hashMap.put(IrisNotificationSummary$NotificationTypes.NEW_SEASON_ALERT, new NewSeasonAlert());
        types = Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public static BaseNotification getNotificationByType(final IrisNotificationSummary$NotificationTypes irisNotificationSummary$NotificationTypes) {
        return NotificationsStaticFactory.types.get(irisNotificationSummary$NotificationTypes);
    }
}
