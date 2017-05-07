// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.player;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class ExtendedPlayerBuffer extends DataBuffer<ExtendedPlayer>
{
    public ExtendedPlayerBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public ExtendedPlayer bk(final int n) {
        return new ExtendedPlayerRef(this.BB, n);
    }
}
