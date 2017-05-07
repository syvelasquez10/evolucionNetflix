// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Account;

public final class no implements Account
{
    private static e a(final GoogleApiClient googleApiClient, final Api.c<e> c) {
        final boolean b = true;
        n.b(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        n.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final e e = googleApiClient.a(c);
        n.a(e != null && b, (Object)"GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e;
    }
    
    @Override
    public void clearDefaultAccount(final GoogleApiClient googleApiClient) {
        a(googleApiClient, Plus.CU).clearDefaultAccount();
    }
    
    @Override
    public String getAccountName(final GoogleApiClient googleApiClient) {
        return a(googleApiClient, Plus.CU).getAccountName();
    }
    
    @Override
    public PendingResult<Status> revokeAccessAndDisconnect(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final e e) {
                e.m((BaseImplementation.b<Status>)this);
            }
        });
    }
    
    private abstract static class a extends Plus.a<Status>
    {
        public Status d(final Status status) {
            return status;
        }
    }
}
