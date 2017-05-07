// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.request.Requests$SendRequestResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class RequestsImpl$4 extends RequestsImpl$SendRequestImpl
{
    final /* synthetic */ String XX;
    final /* synthetic */ String[] Zs;
    final /* synthetic */ int Zt;
    final /* synthetic */ byte[] Zu;
    final /* synthetic */ int Zv;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Requests$SendRequestResult>)this, this.XX, this.Zs, this.Zt, this.Zu, this.Zv);
    }
}
