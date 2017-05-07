// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import java.util.List;
import android.os.Parcelable;

public interface SocialNotificationsList
{
    Parcelable getParcelable();
    
    List<SocialNotificationSummary> getSocialNotifications();
    
    SocialNotificationsListSummary getSocialNotificationsListSummary();
}
