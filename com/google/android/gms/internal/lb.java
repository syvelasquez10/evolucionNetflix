// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.l;
import com.google.android.gms.fitness.data.l$a;
import com.google.android.gms.fitness.data.k;
import android.app.PendingIntent;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataSourceListener;
import com.google.android.gms.fitness.request.p;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.SensorsApi;

public class lb implements SensorsApi
{
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final n n) {
        return googleApiClient.a((PendingResult<Status>)new lb$2(this, n));
    }
    
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final p p3, final DataSourceListener dataSourceListener) {
        return googleApiClient.b((PendingResult<Status>)new lb$3(this, dataSourceListener, p3));
    }
    
    @Override
    public PendingResult<DataSourcesResult> findDataSources(final GoogleApiClient googleApiClient, final DataSourcesRequest dataSourcesRequest) {
        return googleApiClient.a((PendingResult<DataSourcesResult>)new lb$1(this, dataSourcesRequest));
    }
    
    @Override
    public PendingResult<Status> register(final GoogleApiClient googleApiClient, final SensorRequest sensorRequest, final PendingIntent pendingIntent) {
        return this.a(googleApiClient, new n(sensorRequest, null, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> register(final GoogleApiClient googleApiClient, final SensorRequest sensorRequest, final DataSourceListener dataSourceListener) {
        return this.a(googleApiClient, new n(sensorRequest, l$a.iO().a(dataSourceListener), null));
    }
    
    @Override
    public PendingResult<Status> unregister(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return this.a(googleApiClient, new p(null, pendingIntent), null);
    }
    
    @Override
    public PendingResult<Status> unregister(final GoogleApiClient googleApiClient, final DataSourceListener dataSourceListener) {
        final l b = l$a.iO().b(dataSourceListener);
        if (b == null) {
            return new kt<Status>(Status.Jo);
        }
        return this.a(googleApiClient, new p(b, null), dataSourceListener);
    }
}
