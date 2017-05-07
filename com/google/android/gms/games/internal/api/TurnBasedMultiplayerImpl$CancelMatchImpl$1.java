// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;

class TurnBasedMultiplayerImpl$CancelMatchImpl$1 implements TurnBasedMultiplayer$CancelMatchResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ TurnBasedMultiplayerImpl$CancelMatchImpl ZV;
    
    TurnBasedMultiplayerImpl$CancelMatchImpl$1(final TurnBasedMultiplayerImpl$CancelMatchImpl zv, final Status cw) {
        this.ZV = zv;
        this.CW = cw;
    }
    
    @Override
    public String getMatchId() {
        return this.ZV.BL;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
