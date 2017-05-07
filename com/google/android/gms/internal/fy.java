// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Notifications;

public final class fy implements Notifications
{
    @Override
    public void clear(final GoogleApiClient googleApiClient, final int n) {
        Games.j(googleApiClient).clearNotifications(n);
    }
    
    @Override
    public void clearAll(final GoogleApiClient googleApiClient) {
        this.clear(googleApiClient, -1);
    }
}
