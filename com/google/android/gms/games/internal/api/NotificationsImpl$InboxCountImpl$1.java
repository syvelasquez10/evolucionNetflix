// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$InboxCountResult;

class NotificationsImpl$InboxCountImpl$1 implements Notifications$InboxCountResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ NotificationsImpl$InboxCountImpl YS;
    
    NotificationsImpl$InboxCountImpl$1(final NotificationsImpl$InboxCountImpl ys, final Status cw) {
        this.YS = ys;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
