// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$LoadRequestSummariesResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class RequestsImpl$LoadRequestSummariesImpl extends Games$BaseGamesApiMethodImpl<Requests$LoadRequestSummariesResult>
{
    public Requests$LoadRequestSummariesResult ak(final Status status) {
        return new RequestsImpl$LoadRequestSummariesImpl$1(this, status);
    }
}
