// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class TurnBasedMultiplayerImpl$LoadMatchesImpl extends Games$BaseGamesApiMethodImpl<TurnBasedMultiplayer$LoadMatchesResult>
{
    public TurnBasedMultiplayer$LoadMatchesResult aw(final Status status) {
        return new TurnBasedMultiplayerImpl$LoadMatchesImpl$1(this, status);
    }
}
