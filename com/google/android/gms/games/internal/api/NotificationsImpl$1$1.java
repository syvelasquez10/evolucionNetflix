// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusChangeResult;

class NotificationsImpl$1$1 implements Notifications$GameMuteStatusChangeResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ NotificationsImpl$1 YM;
    
    NotificationsImpl$1$1(final NotificationsImpl$1 ym, final Status cw) {
        this.YM = ym;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
