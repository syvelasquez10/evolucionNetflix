// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

public interface UserAgentWebCallback
{
    void onAccountDataFetched(final AccountData p0, final int p1);
    
    void onConnectWithFacebook(final int p0, final String p1);
    
    void onDisconnectWithFacebook(final int p0, final String p1);
    
    void onDummyWebCallDone(final int p0);
    
    void onPinVerified(final boolean p0, final int p1);
    
    void onProfileDataFetched(final UserProfile p0, final int p1);
    
    void onUserProfileSwitched(final UserBoundCookies p0, final int p1);
}
