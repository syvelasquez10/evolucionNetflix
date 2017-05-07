// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class RoomBuffer extends g<Room>
{
    public RoomBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_match_id";
    }
    
    protected Room k(final int n, final int n2) {
        return new RoomRef(this.IC, n, n2);
    }
}
