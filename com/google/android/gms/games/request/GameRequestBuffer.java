// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class GameRequestBuffer extends d<GameRequest>
{
    public GameRequestBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected GameRequest getEntry(final int n, final int n2) {
        return new GameRequestRef(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_request_id";
    }
}
