// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$UpdateMatchResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class TurnBasedMultiplayerImpl$UpdateMatchImpl extends Games$BaseGamesApiMethodImpl<TurnBasedMultiplayer$UpdateMatchResult>
{
    public TurnBasedMultiplayer$UpdateMatchResult ax(final Status status) {
        return new TurnBasedMultiplayerImpl$UpdateMatchImpl$1(this, status);
    }
}
