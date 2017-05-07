// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.i;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.fitness.result.DataTypeResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.ConfigApi;

public class kx implements ConfigApi
{
    @Override
    public PendingResult<DataTypeResult> createCustomDataType(final GoogleApiClient googleApiClient, final DataTypeCreateRequest dataTypeCreateRequest) {
        return googleApiClient.b((PendingResult<DataTypeResult>)new kj.a<DataTypeResult>() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(dataTypeCreateRequest, new kx.a((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
            
            protected DataTypeResult x(final Status status) {
                return DataTypeResult.F(status);
            }
        });
    }
    
    @Override
    public PendingResult<Status> disableFit(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<DataTypeResult> readDataType(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<DataTypeResult>)new kj.a<DataTypeResult>() {
            final /* synthetic */ i Tw = new i(s);
            
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(this.Tw, new kx.a((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
            
            protected DataTypeResult x(final Status status) {
                return DataTypeResult.F(status);
            }
        });
    }
    
    private static class a extends kn.a
    {
        private final BaseImplementation.b<DataTypeResult> De;
        
        private a(final BaseImplementation.b<DataTypeResult> de) {
            this.De = de;
        }
        
        public void a(final DataTypeResult dataTypeResult) {
            this.De.b(dataTypeResult);
        }
    }
}
