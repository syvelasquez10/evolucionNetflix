// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.i;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.result.DataTypeResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.ConfigApi;

public class kx implements ConfigApi
{
    @Override
    public PendingResult<DataTypeResult> createCustomDataType(final GoogleApiClient googleApiClient, final DataTypeCreateRequest dataTypeCreateRequest) {
        return googleApiClient.b((PendingResult<DataTypeResult>)new kx$1(this, dataTypeCreateRequest));
    }
    
    @Override
    public PendingResult<Status> disableFit(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new kx$3(this));
    }
    
    @Override
    public PendingResult<DataTypeResult> readDataType(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<DataTypeResult>)new kx$2(this, new i(s)));
    }
}
