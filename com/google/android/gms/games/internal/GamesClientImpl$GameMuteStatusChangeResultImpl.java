// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusChangeResult;

final class GamesClientImpl$GameMuteStatusChangeResultImpl implements Notifications$GameMuteStatusChangeResult
{
    private final Status CM;
    private final String Wy;
    private final boolean Wz;
    
    public GamesClientImpl$GameMuteStatusChangeResultImpl(final int n, final String wy, final boolean wz) {
        this.CM = new Status(n);
        this.Wy = wy;
        this.Wz = wz;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
