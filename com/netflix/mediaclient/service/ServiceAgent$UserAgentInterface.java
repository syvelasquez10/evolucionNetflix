// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service;

import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.repository.UserLocale;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;

public interface ServiceAgent$UserAgentInterface
{
    public static final String AUTOLOGIN = "com.netflix.mediaclient.intent.action.USER_AUTOLOGIN";
    public static final String CATEGORY_NFUSER = "com.netflix.mediaclient.intent.category.USER";
    public static final String CREATE_AUTOLOGIN_TOKEN = "com.netflix.mediaclient.intent.action.USER_CREATE_AUTOLOGIN_TOKEN";
    public static final long DEFAULT_EXPIRATION_IN_MS = 900000L;
    public static final String EXTRA_EXPIRATION = "expiration";
    
    void doDummyWebCall(final UserAgentWebCallback p0);
    
    UserLocale getCurrentAppLocale();
    
    UserProfile getCurrentProfile();
    
    String getCurrentProfileGuid();
    
    String getGeoCountry();
    
    String getLanguagesInCsv();
    
    String getReqCountry();
    
    TextStyle getSubtitleDefaults();
    
    UserCredentialRegistry getUserCredentialRegistry();
    
    TextStyle getUserSubtitlePreferences();
    
    boolean isAgeVerified();
    
    boolean isCurrentProfileIQEnabled();
    
    boolean isPotentialPrivacyViolationFoundForLogging(final String p0);
    
    boolean isProfileSwitchingDisabled();
    
    boolean isUserLoggedIn();
    
    void logoutUser();
    
    void refreshProfileSwitchingStatus();
}
