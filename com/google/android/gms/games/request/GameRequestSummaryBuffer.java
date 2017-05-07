// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.request;

import com.google.android.gms.common.data.DataBuffer;

public final class GameRequestSummaryBuffer extends DataBuffer<GameRequestSummary>
{
    public GameRequestSummary br(final int n) {
        return new GameRequestSummaryRef(this.BB, n);
    }
}
