// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.android.app.Status;

public interface UserAgentStateManager$StateManagerCallback
{
    void fetchAccountData();
    
    void initialized(final Status p0);
    
    void profileActivated(final String p0, final DeviceAccount p1);
    
    void profileInactive();
    
    void readyToSelectProfile();
    
    void selectProfileResult(final Status p0);
    
    boolean shouldFetchAccountDataAsync();
    
    void switchWebUserProfile(final String p0);
    
    void userAccountActivated(final DeviceAccount p0);
    
    void userAccountDataResult(final Status p0);
    
    void userAccountDeactivated();
    
    void userAccountInactive();
}
