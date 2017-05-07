// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.GamesMetadata$LoadExtendedGamesResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class GamesMetadataImpl$9 extends GamesMetadataImpl$LoadExtendedGamesImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String Yk;
    final /* synthetic */ int Yl;
    final /* synthetic */ boolean Ym;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult>)this, this.Yk, this.Yl, false, false, this.XU, this.Ym);
    }
}
