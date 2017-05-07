// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class RoomBuffer extends d<Room>
{
    public RoomBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected Room e(final int n, final int n2) {
        return new RoomRef(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_match_id";
    }
}
