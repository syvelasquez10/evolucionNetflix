// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.n;

public final class BatchResult implements Result
{
    private final Status CM;
    private final PendingResult<?>[] Iy;
    
    BatchResult(final Status cm, final PendingResult<?>[] iy) {
        this.CM = cm;
        this.Iy = iy;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
    
    public <R extends Result> R take(final BatchResultToken<R> batchResultToken) {
        n.b(batchResultToken.mId < this.Iy.length, (Object)"The result token does not belong to this batch");
        return (R)this.Iy[batchResultToken.mId].await(0L, TimeUnit.MILLISECONDS);
    }
}
