// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class PlayerBuffer extends DataBuffer<Player>
{
    public PlayerBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public Player get(final int n) {
        return new PlayerRef(this.BB, n);
    }
}
