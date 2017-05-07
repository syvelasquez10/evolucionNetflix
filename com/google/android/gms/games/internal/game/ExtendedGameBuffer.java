// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class ExtendedGameBuffer extends d<ExtendedGame>
{
    public ExtendedGameBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected ExtendedGame d(final int n, final int n2) {
        return new ExtendedGameRef(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_game_id";
    }
}
