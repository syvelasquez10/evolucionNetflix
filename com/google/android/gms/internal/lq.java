// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognitionApi;

public class lq implements ActivityRecognitionApi
{
    @Override
    public PendingResult<Status> removeActivityUpdates(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lq$2(this, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> requestActivityUpdates(final GoogleApiClient googleApiClient, final long n, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lq$1(this, n, pendingIntent));
    }
}
