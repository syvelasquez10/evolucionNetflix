// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Players$LoadProfileSettingsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class PlayersImpl$LoadProfileSettingsResultImpl extends Games$BaseGamesApiMethodImpl<Players$LoadProfileSettingsResult>
{
    protected Players$LoadProfileSettingsResult ae(final Status status) {
        return new PlayersImpl$LoadProfileSettingsResultImpl$1(this, status);
    }
}
