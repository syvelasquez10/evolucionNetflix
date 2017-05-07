// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.request.Requests$LoadRequestSummariesResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class RequestsImpl$8 extends RequestsImpl$LoadRequestSummariesImpl
{
    final /* synthetic */ String Zk;
    final /* synthetic */ int Zr;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.f((BaseImplementation$b<Requests$LoadRequestSummariesResult>)this, this.Zk, this.Zr);
    }
}
