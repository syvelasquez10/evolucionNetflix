// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadPlayersResult;

class PlayersImpl$LoadPlayersImpl$1 implements Players$LoadPlayersResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ PlayersImpl$LoadPlayersImpl Zb;
    
    PlayersImpl$LoadPlayersImpl$1(final PlayersImpl$LoadPlayersImpl zb, final Status cw) {
        this.Zb = zb;
        this.CW = cw;
    }
    
    @Override
    public PlayerBuffer getPlayers() {
        return new PlayerBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
