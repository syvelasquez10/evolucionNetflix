// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Cast$CastApi
{
    ApplicationMetadata getApplicationMetadata(final GoogleApiClient p0);
    
    String getApplicationStatus(final GoogleApiClient p0);
    
    double getVolume(final GoogleApiClient p0);
    
    boolean isMute(final GoogleApiClient p0);
    
    PendingResult<Cast$ApplicationConnectionResult> joinApplication(final GoogleApiClient p0);
    
    PendingResult<Cast$ApplicationConnectionResult> joinApplication(final GoogleApiClient p0, final String p1);
    
    PendingResult<Cast$ApplicationConnectionResult> joinApplication(final GoogleApiClient p0, final String p1, final String p2);
    
    PendingResult<Cast$ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1);
    
    PendingResult<Cast$ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1, final LaunchOptions p2);
    
    @Deprecated
    PendingResult<Cast$ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1, final boolean p2);
    
    PendingResult<Status> leaveApplication(final GoogleApiClient p0);
    
    void removeMessageReceivedCallbacks(final GoogleApiClient p0, final String p1);
    
    void requestStatus(final GoogleApiClient p0);
    
    PendingResult<Status> sendMessage(final GoogleApiClient p0, final String p1, final String p2);
    
    void setMessageReceivedCallbacks(final GoogleApiClient p0, final String p1, final Cast$MessageReceivedCallback p2);
    
    void setMute(final GoogleApiClient p0, final boolean p1);
    
    void setVolume(final GoogleApiClient p0, final double p1);
    
    PendingResult<Status> stopApplication(final GoogleApiClient p0);
    
    PendingResult<Status> stopApplication(final GoogleApiClient p0, final String p1);
}
