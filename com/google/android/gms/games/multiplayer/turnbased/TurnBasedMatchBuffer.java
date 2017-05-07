// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class TurnBasedMatchBuffer extends g<TurnBasedMatch>
{
    public TurnBasedMatchBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_match_id";
    }
    
    protected TurnBasedMatch l(final int n, final int n2) {
        return new TurnBasedMatchRef(this.IC, n, n2);
    }
}
