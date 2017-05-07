// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.search;

import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import java.util.List;
import android.os.Parcelable;

public interface SocialNotificationsList
{
    Parcelable getParcelable();
    
    List<SocialNotificationSummary> getSocialNotifications();
    
    SocialNotificationsListSummary getSocialNotificationsListSummary();
}
