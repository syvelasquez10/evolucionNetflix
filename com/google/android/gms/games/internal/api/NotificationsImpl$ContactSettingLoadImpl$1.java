// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$ContactSettingLoadResult;

class NotificationsImpl$ContactSettingLoadImpl$1 implements Notifications$ContactSettingLoadResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ NotificationsImpl$ContactSettingLoadImpl YR;
    
    NotificationsImpl$ContactSettingLoadImpl$1(final NotificationsImpl$ContactSettingLoadImpl yr, final Status cw) {
        this.YR = yr;
        this.CW = cw;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
