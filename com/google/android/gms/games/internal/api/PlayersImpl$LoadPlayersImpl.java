// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class PlayersImpl$LoadPlayersImpl extends Games$BaseGamesApiMethodImpl<Players$LoadPlayersResult>
{
    public Players$LoadPlayersResult ad(final Status status) {
        return new PlayersImpl$LoadPlayersImpl$1(this, status);
    }
}
