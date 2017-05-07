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
    
    PendingResult<Cast$ApplicationConnectionResult> joinApplication(final GoogleApiClient p0, final String p1);
    
    PendingResult<Cast$ApplicationConnectionResult> launchApplication(final GoogleApiClient p0, final String p1);
    
    void removeMessageReceivedCallbacks(final GoogleApiClient p0, final String p1);
    
    PendingResult<Status> sendMessage(final GoogleApiClient p0, final String p1, final String p2);
    
    void setMessageReceivedCallbacks(final GoogleApiClient p0, final String p1, final Cast$MessageReceivedCallback p2);
    
    PendingResult<Status> stopApplication(final GoogleApiClient p0);
}
