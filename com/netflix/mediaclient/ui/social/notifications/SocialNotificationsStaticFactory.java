// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import java.util.Collections;
import com.netflix.mediaclient.ui.social.notifications.types.SocialVideoThankedFeedback;
import com.netflix.mediaclient.ui.social.notifications.types.SocialVideoAddedToPlaylist;
import com.netflix.mediaclient.ui.social.notifications.types.SocialVideoImplicitFeedback;
import com.netflix.mediaclient.ui.social.notifications.types.SocialVideoRecommendation;
import java.util.HashMap;
import com.netflix.mediaclient.ui.social.notifications.types.SocialNotification;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import java.util.Map;

public class SocialNotificationsStaticFactory
{
    private static final Map<SocialNotificationSummary$NotificationTypes, SocialNotification> types;
    
    static {
        final HashMap<SocialNotificationSummary$NotificationTypes, SocialNotification> hashMap = new HashMap<SocialNotificationSummary$NotificationTypes, SocialNotification>();
        hashMap.put(SocialNotificationSummary$NotificationTypes.VIDEO_RECOMMENDATION, new SocialVideoRecommendation());
        hashMap.put(SocialNotificationSummary$NotificationTypes.IMPLICIT_FEEDBACK, new SocialVideoImplicitFeedback());
        hashMap.put(SocialNotificationSummary$NotificationTypes.ADDED_TO_VIDEO_PLAYLIST, new SocialVideoAddedToPlaylist());
        hashMap.put(SocialNotificationSummary$NotificationTypes.THANKS_SENT, new SocialVideoThankedFeedback());
        types = Collections.unmodifiableMap((Map<?, ?>)hashMap);
    }
    
    public static SocialNotification getNotificationByType(final SocialNotificationSummary$NotificationTypes socialNotificationSummary$NotificationTypes) {
        return SocialNotificationsStaticFactory.types.get(socialNotificationSummary$NotificationTypes);
    }
}
