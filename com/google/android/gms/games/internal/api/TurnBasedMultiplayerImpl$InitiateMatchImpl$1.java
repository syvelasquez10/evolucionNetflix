// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;

class TurnBasedMultiplayerImpl$InitiateMatchImpl$1 implements TurnBasedMultiplayer$InitiateMatchResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ TurnBasedMultiplayerImpl$InitiateMatchImpl ZW;
    
    TurnBasedMultiplayerImpl$InitiateMatchImpl$1(final TurnBasedMultiplayerImpl$InitiateMatchImpl zw, final Status cw) {
        this.ZW = zw;
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
