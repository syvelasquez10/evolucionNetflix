// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class TurnBasedMatchBuffer extends d<TurnBasedMatch>
{
    public TurnBasedMatchBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected TurnBasedMatch getEntry(final int n, final int n2) {
        return new TurnBasedMatchRef(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_match_id";
    }
}
