// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.multiplayer.realtime;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class a extends d<Room>
{
    public a(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected Room b(final int n, final int n2) {
        return new c(this.nE, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_match_id";
    }
}
