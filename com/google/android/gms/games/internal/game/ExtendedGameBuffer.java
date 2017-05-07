// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.game;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class ExtendedGameBuffer extends g<ExtendedGame>
{
    public ExtendedGameBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_game_id";
    }
    
    protected ExtendedGame h(final int n, final int n2) {
        return new ExtendedGameRef(this.IC, n, n2);
    }
}
