// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.Notifications$GameMuteStatusLoadResult;

final class GamesClientImpl$GameMuteStatusLoadResultImpl implements Notifications$GameMuteStatusLoadResult
{
    private final Status CM;
    private final String Wy;
    private final boolean Wz;
    
    public GamesClientImpl$GameMuteStatusLoadResultImpl(final DataHolder dataHolder) {
        try {
            this.CM = new Status(dataHolder.getStatusCode());
            if (dataHolder.getCount() > 0) {
                this.Wy = dataHolder.c("external_game_id", 0, 0);
                this.Wz = dataHolder.d("muted", 0, 0);
            }
            else {
                this.Wy = null;
                this.Wz = false;
            }
        }
        finally {
            dataHolder.close();
        }
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
