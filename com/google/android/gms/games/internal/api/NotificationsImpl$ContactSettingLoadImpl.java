// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$ContactSettingLoadResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class NotificationsImpl$ContactSettingLoadImpl extends Games$BaseGamesApiMethodImpl<Notifications$ContactSettingLoadResult>
{
    public Notifications$ContactSettingLoadResult aa(final Status status) {
        return new NotificationsImpl$ContactSettingLoadImpl$1(this, status);
    }
}
