// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.plus;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Account
{
    void clearDefaultAccount(final GoogleApiClient p0);
    
    String getAccountName(final GoogleApiClient p0);
    
    PendingResult<Status> revokeAccessAndDisconnect(final GoogleApiClient p0);
}
