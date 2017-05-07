// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusChangeResult;

class NotificationsImpl$2$1 implements Notifications$GameMuteStatusChangeResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ NotificationsImpl$2 YN;
    
    NotificationsImpl$2$1(final NotificationsImpl$2 yn, final Status cw) {
        this.YN = yn;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
