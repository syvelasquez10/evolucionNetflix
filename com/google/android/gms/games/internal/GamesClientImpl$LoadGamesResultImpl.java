// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GamesMetadata$LoadGamesResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadGamesResultImpl extends a implements GamesMetadata$LoadGamesResult
{
    private final GameBuffer WK;
    
    GamesClientImpl$LoadGamesResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WK = new GameBuffer(dataHolder);
    }
    
    @Override
    public GameBuffer getGames() {
        return this.WK;
    }
}
