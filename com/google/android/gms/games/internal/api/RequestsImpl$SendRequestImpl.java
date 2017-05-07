// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.request.Requests$SendRequestResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class RequestsImpl$SendRequestImpl extends Games$BaseGamesApiMethodImpl<Requests$SendRequestResult>
{
    public Requests$SendRequestResult am(final Status status) {
        return new RequestsImpl$SendRequestImpl$1(this, status);
    }
}
