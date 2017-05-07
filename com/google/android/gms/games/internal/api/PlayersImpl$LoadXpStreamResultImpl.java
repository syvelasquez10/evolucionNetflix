// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadXpStreamResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class PlayersImpl$LoadXpStreamResultImpl extends Games$BaseGamesApiMethodImpl<Players$LoadXpStreamResult>
{
    public Players$LoadXpStreamResult ag(final Status status) {
        return new PlayersImpl$LoadXpStreamResultImpl$1(this, status);
    }
}
