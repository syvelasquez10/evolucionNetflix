// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.request.Requests$UpdateRequestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class RequestsImpl$1 extends RequestsImpl$UpdateRequestsImpl
{
    final /* synthetic */ String[] Zo;
    final /* synthetic */ RequestsImpl Zp;
    
    RequestsImpl$1(final RequestsImpl zp, final String[] zo) {
        this.Zp = zp;
        this.Zo = zo;
        super((RequestsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Requests$UpdateRequestsResult>)this, this.Zo);
    }
}
