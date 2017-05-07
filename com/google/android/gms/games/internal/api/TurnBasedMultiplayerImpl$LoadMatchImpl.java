// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class TurnBasedMultiplayerImpl$LoadMatchImpl extends Games$BaseGamesApiMethodImpl<TurnBasedMultiplayer$LoadMatchResult>
{
    public TurnBasedMultiplayer$LoadMatchResult av(final Status status) {
        return new TurnBasedMultiplayerImpl$LoadMatchImpl$1(this, status);
    }
}
