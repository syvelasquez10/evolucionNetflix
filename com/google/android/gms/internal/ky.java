// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.fitness.result.DataReadResult;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.request.DataInsertRequest;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.HistoryApi;

public class ky implements HistoryApi
{
    @Override
    public PendingResult<Status> deleteData(final GoogleApiClient googleApiClient, final DataDeleteRequest dataDeleteRequest) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(dataDeleteRequest, new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Deprecated
    @Override
    public PendingResult<Status> insert(final GoogleApiClient googleApiClient, final DataInsertRequest dataInsertRequest) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(dataInsertRequest, new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> insertData(final GoogleApiClient googleApiClient, final DataSet dataSet) {
        return this.insert(googleApiClient, new DataInsertRequest.Builder().setDataSet(dataSet).build());
    }
    
    @Override
    public PendingResult<DataReadResult> readData(final GoogleApiClient googleApiClient, final DataReadRequest dataReadRequest) {
        return googleApiClient.a((PendingResult<DataReadResult>)new kj.a<DataReadResult>() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(dataReadRequest, new ky.a((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
            
            protected DataReadResult y(final Status status) {
                return DataReadResult.a(status, dataReadRequest);
            }
        });
    }
    
    private static class a extends kl.a
    {
        private final BaseImplementation.b<DataReadResult> De;
        private int TB;
        private DataReadResult TC;
        
        private a(final BaseImplementation.b<DataReadResult> de) {
            this.TB = 0;
            this.TC = null;
            this.De = de;
        }
        
        public void a(final DataReadResult tc) {
            synchronized (this) {
                Log.v("Fitness", "Received batch result");
                if (this.TC == null) {
                    this.TC = tc;
                }
                else {
                    this.TC.b(tc);
                }
                ++this.TB;
                if (this.TB == this.TC.jF()) {
                    this.De.b(this.TC);
                }
            }
        }
    }
}
