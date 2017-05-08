// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import android.app.PendingIntent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface ActivityRecognitionApi
{
    PendingResult<Status> removeActivityUpdates(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> requestActivityUpdates(final GoogleApiClient p0, final long p1, final PendingIntent p2);
}
