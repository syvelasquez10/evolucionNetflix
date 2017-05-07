// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$UpdateMatchResult;

class TurnBasedMultiplayerImpl$UpdateMatchImpl$1 implements TurnBasedMultiplayer$UpdateMatchResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ TurnBasedMultiplayerImpl$UpdateMatchImpl aaa;
    
    TurnBasedMultiplayerImpl$UpdateMatchImpl$1(final TurnBasedMultiplayerImpl$UpdateMatchImpl aaa, final Status cw) {
        this.aaa = aaa;
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
