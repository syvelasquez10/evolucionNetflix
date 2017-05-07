// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class GameRequestBuffer extends g<GameRequest>
{
    public GameRequestBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_request_id";
    }
    
    protected GameRequest n(final int n, final int n2) {
        return new GameRequestRef(this.IC, n, n2);
    }
}
