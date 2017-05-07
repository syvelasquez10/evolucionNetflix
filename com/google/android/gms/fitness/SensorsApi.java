// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.fitness.request.DataSourceListener;
import com.google.android.gms.common.api.Status;
import android.app.PendingIntent;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.common.api.GoogleApiClient;

public interface SensorsApi
{
    PendingResult<DataSourcesResult> findDataSources(final GoogleApiClient p0, final DataSourcesRequest p1);
    
    PendingResult<Status> register(final GoogleApiClient p0, final SensorRequest p1, final PendingIntent p2);
    
    PendingResult<Status> register(final GoogleApiClient p0, final SensorRequest p1, final DataSourceListener p2);
    
    PendingResult<Status> unregister(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> unregister(final GoogleApiClient p0, final DataSourceListener p1);
}
