// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface RecordingApi
{
    PendingResult<ListSubscriptionsResult> listSubscriptions(final GoogleApiClient p0);
    
    PendingResult<ListSubscriptionsResult> listSubscriptions(final GoogleApiClient p0, final DataType p1);
    
    PendingResult<Status> subscribe(final GoogleApiClient p0, final DataSource p1);
    
    PendingResult<Status> subscribe(final GoogleApiClient p0, final DataType p1);
    
    PendingResult<Status> unsubscribe(final GoogleApiClient p0, final DataSource p1);
    
    PendingResult<Status> unsubscribe(final GoogleApiClient p0, final DataType p1);
    
    PendingResult<Status> unsubscribe(final GoogleApiClient p0, final Subscription p1);
}
