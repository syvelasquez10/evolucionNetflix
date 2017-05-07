// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class GameInstanceBuffer extends DataBuffer<GameInstance>
{
    public GameInstanceBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public GameInstance bh(final int n) {
        return new GameInstanceRef(this.BB, n);
    }
}
