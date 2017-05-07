// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Players$LoadProfileSettingsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$ProfileSettingsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Players$LoadProfileSettingsResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$ProfileSettingsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Players$LoadProfileSettingsResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void Q(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadProfileSettingsResultImpl(dataHolder));
    }
}
