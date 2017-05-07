// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class TurnBasedMultiplayerImpl$CancelMatchImpl extends Games$BaseGamesApiMethodImpl<TurnBasedMultiplayer$CancelMatchResult>
{
    private final String BL;
    
    public TurnBasedMultiplayerImpl$CancelMatchImpl(final String bl) {
        this.BL = bl;
    }
    
    public TurnBasedMultiplayer$CancelMatchResult as(final Status status) {
        return new TurnBasedMultiplayerImpl$CancelMatchImpl$1(this, status);
    }
}
