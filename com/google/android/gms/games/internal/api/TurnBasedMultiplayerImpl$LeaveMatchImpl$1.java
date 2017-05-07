// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LeaveMatchResult;

class TurnBasedMultiplayerImpl$LeaveMatchImpl$1 implements TurnBasedMultiplayer$LeaveMatchResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ TurnBasedMultiplayerImpl$LeaveMatchImpl ZX;
    
    TurnBasedMultiplayerImpl$LeaveMatchImpl$1(final TurnBasedMultiplayerImpl$LeaveMatchImpl zx, final Status cw) {
        this.ZX = zx;
        this.CW = cw;
    }
    
    @Override
    public TurnBasedMatch getMatch() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
