// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.Player;
import com.google.android.gms.games.OnNearbyPlayerDetectedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$NearbyPlayerDetectedCallback extends d$b<OnNearbyPlayerDetectedListener>
{
    private final Player Xa;
    
    protected void a(final OnNearbyPlayerDetectedListener onNearbyPlayerDetectedListener) {
        onNearbyPlayerDetectedListener.a(this.Xa);
    }
    
    @Override
    protected void gT() {
    }
}
