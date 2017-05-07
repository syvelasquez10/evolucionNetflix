// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.plus.model.moments.Moment;
import com.google.android.gms.common.api.Status;
import android.net.Uri;
import com.google.android.gms.plus.Moments$LoadMomentsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Moments;

public final class nr implements Moments
{
    @Override
    public PendingResult<Moments$LoadMomentsResult> load(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<Moments$LoadMomentsResult>)new nr$1(this));
    }
    
    @Override
    public PendingResult<Moments$LoadMomentsResult> load(final GoogleApiClient googleApiClient, final int n, final String s, final Uri uri, final String s2, final String s3) {
        return googleApiClient.a((PendingResult<Moments$LoadMomentsResult>)new nr$2(this, n, s, uri, s2, s3));
    }
    
    @Override
    public PendingResult<Status> remove(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Status>)new nr$4(this, s));
    }
    
    @Override
    public PendingResult<Status> write(final GoogleApiClient googleApiClient, final Moment moment) {
        return googleApiClient.b((PendingResult<Status>)new nr$3(this, moment));
    }
}
