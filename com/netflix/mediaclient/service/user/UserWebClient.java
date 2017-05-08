// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.model.leafs.OnRampEligibility$Action;

public interface UserWebClient
{
    void addWebUserProfile(final String p0, final boolean p1, final String p2, final UserAgentWebCallback p3);
    
    void autoLogin(final String p0, final UserAgentWebCallback p1);
    
    void createAutoLoginToken(final long p0, final UserAgentWebCallback p1);
    
    void doDummyWebCall(final UserAgentWebCallback p0);
    
    void doOnRampEligibilityAction(final OnRampEligibility$Action p0, final UserAgentWebCallback p1);
    
    void editWebUserProfile(final String p0, final String p1, final boolean p2, final String p3, final UserAgentWebCallback p4);
    
    void fetchAccountData(final UserAgentWebCallback p0);
    
    void fetchAvailableAvatarsList(final UserAgentWebCallback p0);
    
    void fetchProfileData(final String p0, final UserAgentWebCallback p1);
    
    void fetchSurvey(final UserAgentWebCallback p0);
    
    void fetchUserData(final UserAgentWebCallback p0);
    
    boolean isSynchronous();
    
    void markSurveysAsRead();
    
    void recordPlanSelection(final String p0, final String p1);
    
    void recordThumbRatingWelcomeSeen();
    
    void recordUmsImpression(final String p0, final String p1);
    
    void refreshUserMessage(final User p0);
    
    void removeWebUserProfile(final String p0, final UserAgentWebCallback p1);
    
    void switchWebUserProfile(final String p0, final UserAgentWebCallback p1);
    
    void verifyPin(final String p0, final PinVerifier$PinType p1, final String p2, final UserAgentWebCallback p3);
}
