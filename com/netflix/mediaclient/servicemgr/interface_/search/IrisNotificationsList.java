// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.search;

import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import java.util.List;
import android.os.Parcelable;

public interface IrisNotificationsList
{
    Parcelable getParcelable();
    
    List<IrisNotificationSummary> getSocialNotifications();
    
    IrisNotificationsListSummary getSocialNotificationsListSummary();
}
