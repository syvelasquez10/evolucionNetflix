// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadPlayersResultImpl extends a implements Players$LoadPlayersResult
{
    private final PlayerBuffer WO;
    
    GamesClientImpl$LoadPlayersResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WO = new PlayerBuffer(dataHolder);
    }
    
    @Override
    public PlayerBuffer getPlayers() {
        return this.WO;
    }
}
