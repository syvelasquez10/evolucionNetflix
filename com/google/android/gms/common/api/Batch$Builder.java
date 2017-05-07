// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.ArrayList;
import android.os.Looper;
import java.util.List;

public final class Batch$Builder
{
    private List<PendingResult<?>> IA;
    private Looper IB;
    
    public Batch$Builder(final GoogleApiClient googleApiClient) {
        this.IA = new ArrayList<PendingResult<?>>();
        this.IB = googleApiClient.getLooper();
    }
    
    public <R extends Result> BatchResultToken<R> add(final PendingResult<R> pendingResult) {
        final BatchResultToken<R> batchResultToken = new BatchResultToken<R>(this.IA.size());
        this.IA.add(pendingResult);
        return batchResultToken;
    }
    
    public Batch build() {
        return new Batch(this.IA, this.IB, null);
    }
}
