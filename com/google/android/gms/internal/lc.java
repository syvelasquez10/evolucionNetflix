// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.request.z;
import com.google.android.gms.fitness.request.x;
import com.google.android.gms.fitness.result.SessionStopResult;
import com.google.android.gms.fitness.request.v;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.fitness.request.t;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.common.api.BaseImplementation;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.SessionsApi;

public class lc implements SessionsApi
{
    @Override
    public PendingResult<Status> insertSession(final GoogleApiClient googleApiClient, final SessionInsertRequest sessionInsertRequest) {
        return googleApiClient.a((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(sessionInsertRequest, new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<SessionReadResult> readSession(final GoogleApiClient googleApiClient, final SessionReadRequest sessionReadRequest) {
        return googleApiClient.a((PendingResult<SessionReadResult>)new kj.a<SessionReadResult>() {
            protected SessionReadResult C(final Status status) {
                return SessionReadResult.H(status);
            }
            
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(sessionReadRequest, new lc.a((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> registerForSessions(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new t(pendingIntent), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> startSession(final GoogleApiClient googleApiClient, final Session session) {
        return googleApiClient.b((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new v.a().b(session).jx(), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<SessionStopResult> stopSession(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<SessionStopResult>)new kj.a<SessionStopResult>() {
            protected SessionStopResult B(final Status status) {
                return SessionStopResult.I(status);
            }
            
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new x.a().br(s).bs(s2).jy(), new lc.b((BaseImplementation.b)this), kj.getContext().getPackageName());
            }
        });
    }
    
    @Override
    public PendingResult<Status> unregisterForSessions(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new kj.c() {
            protected void a(final kj kj) throws RemoteException {
                kj.iT().a(new z(pendingIntent), new kj.b((BaseImplementation.b<Status>)this), kj.getContext().getPackageName());
            }
        });
    }
    
    private static class a extends kq.a
    {
        private final BaseImplementation.b<SessionReadResult> De;
        
        private a(final BaseImplementation.b<SessionReadResult> de) {
            this.De = de;
        }
        
        public void a(final SessionReadResult sessionReadResult) throws RemoteException {
            this.De.b(sessionReadResult);
        }
    }
    
    private static class b extends kr.a
    {
        private final BaseImplementation.b<SessionStopResult> De;
        
        private b(final BaseImplementation.b<SessionStopResult> de) {
            this.De = de;
        }
        
        public void a(final SessionStopResult sessionStopResult) {
            this.De.b(sessionStopResult);
        }
    }
}
