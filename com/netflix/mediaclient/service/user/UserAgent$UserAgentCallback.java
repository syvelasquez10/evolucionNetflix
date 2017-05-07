// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;

public interface UserAgent$UserAgentCallback
{
    void onAutoLoginTokenCreated(final String p0, final Status p1);
    
    void onAvailableAvatarsListFetched(final List<AvatarInfo> p0, final Status p1);
    
    void onIrisNotificationsListFetched(final IrisNotificationsList p0, final Status p1);
    
    void onLoginComplete(final Status p0);
    
    void onLogoutComplete(final Status p0);
    
    void onProfilesListUpdateResult(final Status p0);
    
    void onVerified(final boolean p0, final Status p1);
}
