// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadXpForGameCategoriesResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class PlayersImpl$LoadXpForGameCategoriesResultImpl extends Games$BaseGamesApiMethodImpl<Players$LoadXpForGameCategoriesResult>
{
    public Players$LoadXpForGameCategoriesResult af(final Status status) {
        return new PlayersImpl$LoadXpForGameCategoriesResultImpl$1(this, status);
    }
}
