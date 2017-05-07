// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.SessionStopResult;
import com.google.android.gms.fitness.data.Session;
import android.app.PendingIntent;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.SessionsApi;

public class lc implements SessionsApi
{
    @Override
    public PendingResult<Status> insertSession(final GoogleApiClient googleApiClient, final SessionInsertRequest sessionInsertRequest) {
        return googleApiClient.a((PendingResult<Status>)new lc$3(this, sessionInsertRequest));
    }
    
    @Override
    public PendingResult<SessionReadResult> readSession(final GoogleApiClient googleApiClient, final SessionReadRequest sessionReadRequest) {
        return googleApiClient.a((PendingResult<SessionReadResult>)new lc$4(this, sessionReadRequest));
    }
    
    @Override
    public PendingResult<Status> registerForSessions(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lc$5(this, pendingIntent));
    }
    
    @Override
    public PendingResult<Status> startSession(final GoogleApiClient googleApiClient, final Session session) {
        return googleApiClient.b((PendingResult<Status>)new lc$1(this, session));
    }
    
    @Override
    public PendingResult<SessionStopResult> stopSession(final GoogleApiClient googleApiClient, final String s, final String s2) {
        return googleApiClient.b((PendingResult<SessionStopResult>)new lc$2(this, s, s2));
    }
    
    @Override
    public PendingResult<Status> unregisterForSessions(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return googleApiClient.b((PendingResult<Status>)new lc$6(this, pendingIntent));
    }
}
