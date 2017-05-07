// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class GameBuffer extends DataBuffer<Game>
{
    public GameBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    public Game get(final int n) {
        return new GameRef(this.IC, n);
    }
}
