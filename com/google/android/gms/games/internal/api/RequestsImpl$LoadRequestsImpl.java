// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$LoadRequestsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class RequestsImpl$LoadRequestsImpl extends Games$BaseGamesApiMethodImpl<Requests$LoadRequestsResult>
{
    public Requests$LoadRequestsResult al(final Status status) {
        return new RequestsImpl$LoadRequestsImpl$1(this, status);
    }
}
