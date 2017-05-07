// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness;

import com.google.android.gms.fitness.result.SessionStopResult;
import com.google.android.gms.fitness.data.Session;
import android.app.PendingIntent;
import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.fitness.request.SessionReadRequest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.fitness.request.SessionInsertRequest;
import com.google.android.gms.common.api.GoogleApiClient;

public interface SessionsApi
{
    PendingResult<Status> insertSession(final GoogleApiClient p0, final SessionInsertRequest p1);
    
    PendingResult<SessionReadResult> readSession(final GoogleApiClient p0, final SessionReadRequest p1);
    
    PendingResult<Status> registerForSessions(final GoogleApiClient p0, final PendingIntent p1);
    
    PendingResult<Status> startSession(final GoogleApiClient p0, final Session p1);
    
    PendingResult<SessionStopResult> stopSession(final GoogleApiClient p0, final String p1, final String p2);
    
    PendingResult<Status> unregisterForSessions(final GoogleApiClient p0, final PendingIntent p1);
}
