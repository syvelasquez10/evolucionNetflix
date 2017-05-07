// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.request.Requests$LoadRequestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class RequestsImpl$3 extends RequestsImpl$LoadRequestsImpl
{
    final /* synthetic */ int Yu;
    final /* synthetic */ RequestsImpl Zp;
    final /* synthetic */ int Zq;
    final /* synthetic */ int Zr;
    
    RequestsImpl$3(final RequestsImpl zp, final int zq, final int zr, final int yu) {
        this.Zp = zp;
        this.Zq = zq;
        this.Zr = zr;
        this.Yu = yu;
        super((RequestsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Requests$LoadRequestsResult>)this, this.Zq, this.Zr, this.Yu);
    }
}
