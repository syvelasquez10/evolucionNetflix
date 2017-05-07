// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

public interface UserWebClient
{
    void addWebUserProfile(final String p0, final boolean p1, final String p2, final UserAgentWebCallback p3);
    
    void connectWithFacebook(final String p0, final UserAgentWebCallback p1);
    
    void disconnectFromFacebook(final UserAgentWebCallback p0);
    
    void doDummyWebCall(final UserAgentWebCallback p0);
    
    void editWebUserProfile(final String p0, final String p1, final boolean p2, final String p3, final UserAgentWebCallback p4);
    
    void fetchAccountData(final UserAgentWebCallback p0);
    
    void fetchAvailableAvatarsList(final UserAgentWebCallback p0);
    
    void fetchProfileData(final String p0, final UserAgentWebCallback p1);
    
    boolean isSynchronous();
    
    void removeWebUserProfile(final String p0, final UserAgentWebCallback p1);
    
    void switchWebUserProfile(final String p0, final UserAgentWebCallback p1);
    
    void verifyPin(final String p0, final UserAgentWebCallback p1);
}
