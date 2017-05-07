// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.internal.fq;

public final class BatchResult implements Result
{
    private final PendingResult<?>[] AP;
    private final Status wJ;
    
    BatchResult(final Status wj, final PendingResult<?>[] ap) {
        this.wJ = wj;
        this.AP = ap;
    }
    
    @Override
    public Status getStatus() {
        return this.wJ;
    }
    
    public <R extends Result> R take(final BatchResultToken<R> batchResultToken) {
        fq.b(batchResultToken.mId < this.AP.length, "The result token does not belong to this batch");
        final PendingResult<?> pendingResult = this.AP[batchResultToken.mId];
        this.AP[batchResultToken.mId] = null;
        return (R)pendingResult.await();
    }
}
