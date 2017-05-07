// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$UpdateRequestsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class RequestsImpl$UpdateRequestsImpl extends Games$BaseGamesApiMethodImpl<Requests$UpdateRequestsResult>
{
    public Requests$UpdateRequestsResult an(final Status status) {
        return new RequestsImpl$UpdateRequestsImpl$1(this, status);
    }
}
