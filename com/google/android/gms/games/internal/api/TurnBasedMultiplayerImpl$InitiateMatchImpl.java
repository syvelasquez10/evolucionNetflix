// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class TurnBasedMultiplayerImpl$InitiateMatchImpl extends Games$BaseGamesApiMethodImpl<TurnBasedMultiplayer$InitiateMatchResult>
{
    public TurnBasedMultiplayer$InitiateMatchResult at(final Status status) {
        return new TurnBasedMultiplayerImpl$InitiateMatchImpl$1(this, status);
    }
}
