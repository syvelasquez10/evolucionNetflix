// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

public abstract class SimpleUserAgentWebCallback implements UserAgentWebCallback
{
    @Override
    public void onAccountDataFetched(final AccountData accountData, final int n) {
    }
    
    @Override
    public void onConnectWithFacebook(final int n, final String s) {
    }
    
    @Override
    public void onDisconnectWithFacebook(final int n, final String s) {
    }
    
    @Override
    public void onDummyWebCallDone(final int n) {
    }
    
    @Override
    public void onProfileDataFetched(final UserProfile userProfile, final int n) {
    }
    
    @Override
    public void onUserProfileSwitched(final UserBoundCookies userBoundCookies, final int n) {
    }
}
