// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.internal.e;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Account;

public final class hy implements Account
{
    private static e a(final GoogleApiClient googleApiClient, final Api.c<e> c) {
        final boolean b = true;
        fq.b(googleApiClient != null, "GoogleApiClient parameter is required.");
        fq.a(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        final e e = googleApiClient.a(c);
        fq.a(e != null && b, (Object)"GoogleApiClient is not configured to use the Plus.API Api. Pass this into GoogleApiClient.Builder#addApi() to use this feature.");
        return e;
    }
    
    @Override
    public void clearDefaultAccount(final GoogleApiClient googleApiClient) {
        a(googleApiClient, Plus.wx).clearDefaultAccount();
    }
    
    @Override
    public String getAccountName(final GoogleApiClient googleApiClient) {
        return a(googleApiClient, Plus.wx).getAccountName();
    }
    
    @Override
    public PendingResult<Status> revokeAccessAndDisconnect(final GoogleApiClient googleApiClient) {
        return googleApiClient.b((PendingResult<Status>)new a() {
            protected void a(final e e) {
                e.n((d<Status>)this);
            }
        });
    }
    
    private abstract static class a extends Plus.a<Status>
    {
        public Status f(final Status status) {
            return status;
        }
    }
}
