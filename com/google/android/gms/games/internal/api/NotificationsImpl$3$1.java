// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusLoadResult;

class NotificationsImpl$3$1 implements Notifications$GameMuteStatusLoadResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ NotificationsImpl$3 YO;
    
    NotificationsImpl$3$1(final NotificationsImpl$3 yo, final Status cw) {
        this.YO = yo;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
