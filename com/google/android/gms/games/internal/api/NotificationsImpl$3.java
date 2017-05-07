// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusLoadResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

class NotificationsImpl$3 extends Games$BaseGamesApiMethodImpl<Notifications$GameMuteStatusLoadResult>
{
    final /* synthetic */ String YL;
    
    public Notifications$GameMuteStatusLoadResult Z(final Status status) {
        return new NotificationsImpl$3$1(this, status);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.q((BaseImplementation$b<Notifications$GameMuteStatusLoadResult>)this, this.YL);
    }
}
