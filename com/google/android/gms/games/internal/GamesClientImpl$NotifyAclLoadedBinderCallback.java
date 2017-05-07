// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.internal.game.Acls$LoadAclResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$NotifyAclLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Acls$LoadAclResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$NotifyAclLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Acls$LoadAclResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void C(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadAclResultImpl(dataHolder));
    }
}
