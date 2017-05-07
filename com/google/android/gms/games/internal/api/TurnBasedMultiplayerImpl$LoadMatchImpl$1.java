// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchResult;

class TurnBasedMultiplayerImpl$LoadMatchImpl$1 implements TurnBasedMultiplayer$LoadMatchResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ TurnBasedMultiplayerImpl$LoadMatchImpl ZY;
    
    TurnBasedMultiplayerImpl$LoadMatchImpl$1(final TurnBasedMultiplayerImpl$LoadMatchImpl zy, final Status cw) {
        this.ZY = zy;
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
