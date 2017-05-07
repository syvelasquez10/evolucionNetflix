// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Notifications;

public final class NotificationsImpl implements Notifications
{
    @Override
    public void clear(final GoogleApiClient googleApiClient, final int n) {
        Games.c(googleApiClient).dC(n);
    }
    
    @Override
    public void clearAll(final GoogleApiClient googleApiClient) {
        this.clear(googleApiClient, 31);
    }
}
