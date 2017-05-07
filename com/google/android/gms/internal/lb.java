// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.l;
import com.google.android.gms.fitness.data.k;
import android.app.PendingIntent;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.fitness.request.DataSourcesRequest;
import com.google.android.gms.fitness.request.DataSourceListener;
import com.google.android.gms.fitness.request.p;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.n;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.SensorsApi;

public class lb implements SensorsApi
{
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final n n) {
        return googleApiClient.a((PendingResult<Status>)new a<Status>() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(n, new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
            
            protected Status d(final Status status) {
                return status;
            }
        });
    }
    
    private PendingResult<Status> a(final GoogleApiClient googleApiClient, final p p3, final DataSourceListener dataSourceListener) {
        return googleApiClient.b((PendingResult<Status>)new a<Status>() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(p3, new lb.c((BaseImplementation.b)this, dataSourceListener), kj.getContext().getPackageName());
            }
            
            protected Status d(final Status status) {
                return status;
            }
        });
    }
    
    @Override
    public PendingResult<DataSourcesResult> findDataSources(final GoogleApiClient googleApiClient, final DataSourcesRequest dataSourcesRequest) {
        return googleApiClient.a((PendingResult<DataSourcesResult>)new a<DataSourcesResult>() {
            protected DataSourcesResult A(final Status status) {
                return DataSourcesResult.E(status);
            }
            
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(dataSourcesRequest, new lb.b((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> register(final GoogleApiClient googleApiClient, final SensorRequest sensorRequest, final PendingIntent pendingIntent) {
        return this.a(googleApiClient, new n(sensorRequest, null, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> register(final GoogleApiClient googleApiClient, final SensorRequest sensorRequest, final DataSourceListener dataSourceListener) {
        return this.a(googleApiClient, new n(sensorRequest, l.a.iO().a(dataSourceListener), null));
    }
    
    @Override
    public PendingResult<Status> unregister(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return this.a(googleApiClient, new p(null, pendingIntent), null);
    }
    
    @Override
    public PendingResult<Status> unregister(final GoogleApiClient googleApiClient, final DataSourceListener dataSourceListener) {
        final l b = l.a.iO().b(dataSourceListener);
        if (b == null) {
            return new kt<Status>(Status.Jo);
        }
        return this.a(googleApiClient, new p(b, null), dataSourceListener);
    }
    
    private abstract static class a<R extends Result> extends BaseImplementation.a<R, kj>
    {
        public a() {
            super(Fitness.CU);
        }
    }
    
    private static class b extends km.a
    {
        private final BaseImplementation.b<DataSourcesResult> De;
        
        private b(final BaseImplementation.b<DataSourcesResult> de) {
            this.De = de;
        }
        
        public void a(final DataSourcesResult dataSourcesResult) {
            this.De.b(dataSourcesResult);
        }
    }
    
    private static class c extends ks.a
    {
        private final BaseImplementation.b<Status> De;
        private final DataSourceListener TM;
        
        private c(final BaseImplementation.b<Status> de, final DataSourceListener tm) {
            this.De = de;
            this.TM = tm;
        }
        
        public void k(final Status status) {
            if (this.TM != null && status.isSuccess()) {
                l.a.iO().c(this.TM);
            }
            this.De.b(status);
        }
    }
}
