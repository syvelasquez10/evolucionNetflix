// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

public abstract class SimpleUserAgentWebCallback implements UserAgentWebCallback
{
    @Override
    public void onAccountDataFetched(final AccountData accountData, final Status status) {
    }
    
    @Override
    public void onAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
    }
    
    @Override
    public void onConnectWithFacebook(final Status status) {
    }
    
    @Override
    public void onDisconnectWithFacebook(final Status status) {
    }
    
    @Override
    public void onDummyWebCallDone(final Status status) {
    }
    
    @Override
    public void onPinVerified(final boolean b, final Status status) {
    }
    
    @Override
    public void onProfileDataFetched(final UserProfile userProfile, final Status status) {
    }
    
    @Override
    public void onUserProfileSwitched(final UserBoundCookies userBoundCookies, final Status status) {
    }
    
    @Override
    public void onUserProfilesUpdated(final AccountData accountData, final Status status) {
    }
}
