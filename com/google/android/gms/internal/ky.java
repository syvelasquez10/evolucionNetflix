// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataInsertRequest$Builder;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataInsertRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.HistoryApi;

public class ky implements HistoryApi
{
    @Override
    public PendingResult<Status> deleteData(final GoogleApiClient googleApiClient, final DataDeleteRequest dataDeleteRequest) {
        return googleApiClient.a((PendingResult<Status>)new ky$2(this, dataDeleteRequest));
    }
    
    @Deprecated
    @Override
    public PendingResult<Status> insert(final GoogleApiClient googleApiClient, final DataInsertRequest dataInsertRequest) {
        return googleApiClient.a((PendingResult<Status>)new ky$1(this, dataInsertRequest));
    }
    
    @Override
    public PendingResult<Status> insertData(final GoogleApiClient googleApiClient, final DataSet dataSet) {
        return this.insert(googleApiClient, new DataInsertRequest$Builder().setDataSet(dataSet).build());
    }
    
    @Override
    public PendingResult<DataReadResult> readData(final GoogleApiClient googleApiClient, final DataReadRequest dataReadRequest) {
        return googleApiClient.a((PendingResult<DataReadResult>)new ky$3(this, dataReadRequest));
    }
}
