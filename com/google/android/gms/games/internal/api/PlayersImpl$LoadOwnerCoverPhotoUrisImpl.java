// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadOwnerCoverPhotoUrisResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class PlayersImpl$LoadOwnerCoverPhotoUrisImpl extends Games$BaseGamesApiMethodImpl<Players$LoadOwnerCoverPhotoUrisResult>
{
    public Players$LoadOwnerCoverPhotoUrisResult ac(final Status status) {
        return new PlayersImpl$LoadOwnerCoverPhotoUrisImpl$1(this, status);
    }
}
