// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.Subscription;
import com.google.android.gms.fitness.request.ah$a;
import com.google.android.gms.fitness.data.Subscription$a;
import com.google.android.gms.fitness.request.ae$a;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.l$a;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;
import com.google.android.gms.fitness.request.l;
import com.google.android.gms.fitness.request.ah;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.ae;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.RecordingApi;

public class la implements RecordingApi
{
    public PendingResult<Status> a(final GoogleApiClient googleApiClient, final ae ae) {
        return googleApiClient.a((PendingResult<Status>)new la$2(this, ae));
    }
    
    public PendingResult<Status> a(final GoogleApiClient googleApiClient, final ah ah) {
        return googleApiClient.b((PendingResult<Status>)new la$3(this, ah));
    }
    
    public PendingResult<ListSubscriptionsResult> a(final GoogleApiClient googleApiClient, final l l) {
        return googleApiClient.a((PendingResult<ListSubscriptionsResult>)new la$1(this, l));
    }
    
    @Override
    public PendingResult<ListSubscriptionsResult> listSubscriptions(final GoogleApiClient googleApiClient) {
        return this.a(googleApiClient, new l$a().jk());
    }
    
    @Override
    public PendingResult<ListSubscriptionsResult> listSubscriptions(final GoogleApiClient googleApiClient, final DataType dataType) {
        return this.a(googleApiClient, new l$a().c(dataType).jk());
    }
    
    @Override
    public PendingResult<Status> subscribe(final GoogleApiClient googleApiClient, final DataSource dataSource) {
        return this.a(googleApiClient, new ae$a().b(new Subscription$a().b(dataSource).iR()).jD());
    }
    
    @Override
    public PendingResult<Status> subscribe(final GoogleApiClient googleApiClient, final DataType dataType) {
        return this.a(googleApiClient, new ae$a().b(new Subscription$a().b(dataType).iR()).jD());
    }
    
    @Override
    public PendingResult<Status> unsubscribe(final GoogleApiClient googleApiClient, final DataSource dataSource) {
        return this.a(googleApiClient, new ah$a().d(dataSource).jE());
    }
    
    @Override
    public PendingResult<Status> unsubscribe(final GoogleApiClient googleApiClient, final DataType dataType) {
        return this.a(googleApiClient, new ah$a().d(dataType).jE());
    }
    
    @Override
    public PendingResult<Status> unsubscribe(final GoogleApiClient googleApiClient, final Subscription subscription) {
        if (subscription.getDataType() == null) {
            return this.unsubscribe(googleApiClient, subscription.getDataSource());
        }
        return this.unsubscribe(googleApiClient, subscription.getDataType());
    }
}
