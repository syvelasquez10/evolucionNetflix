// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;

public interface ServiceAgent$UserAgentInterface
{
    void doDummyWebCall(final UserAgentWebCallback p0);
    
    UserProfile getCurrentProfile();
    
    String getCurrentProfileGuid();
    
    String getGeoCountry();
    
    String getLanguagesInCsv();
    
    String getReqCountry();
    
    TextStyle getSubtitleDefaults();
    
    UserCredentialRegistry getUserCredentialRegistry();
    
    TextStyle getUserSubtitlePreferences();
    
    boolean isCurrentProfileIQEnabled();
    
    boolean isProfileSwitchingDisabled();
    
    boolean isUserLoggedIn();
    
    void logoutUser();
    
    void refreshProfileSwitchingStatus();
}
