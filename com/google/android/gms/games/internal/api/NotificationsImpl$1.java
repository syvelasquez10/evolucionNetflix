// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusChangeResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

class NotificationsImpl$1 extends Games$BaseGamesApiMethodImpl<Notifications$GameMuteStatusChangeResult>
{
    final /* synthetic */ String YL;
    
    public Notifications$GameMuteStatusChangeResult Y(final Status status) {
        return new NotificationsImpl$1$1(this, status);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.d((BaseImplementation$b<Notifications$GameMuteStatusChangeResult>)this, this.YL, true);
    }
}
