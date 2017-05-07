// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.GamesMetadata$LoadGamesResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class GamesMetadataImpl$1 extends GamesMetadataImpl$LoadGamesImpl
{
    final /* synthetic */ GamesMetadataImpl Yj;
    
    GamesMetadataImpl$1(final GamesMetadataImpl yj) {
        this.Yj = yj;
        super((GamesMetadataImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.f((BaseImplementation$b<GamesMetadata$LoadGamesResult>)this);
    }
}
