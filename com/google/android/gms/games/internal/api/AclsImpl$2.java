// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.internal.game.Acls$LoadAclResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class AclsImpl$2 extends AclsImpl$LoadNotifyAclImpl
{
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.h((BaseImplementation$b<Acls$LoadAclResult>)this);
    }
}
