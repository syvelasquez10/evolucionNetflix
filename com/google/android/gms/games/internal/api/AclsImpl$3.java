// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class AclsImpl$3 extends AclsImpl$UpdateNotifyAclImpl
{
    final /* synthetic */ String Yc;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.p((BaseImplementation$b<Status>)this, this.Yc);
    }
}
