// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import android.os.Looper;
import java.util.List;

public final class Batch extends BaseImplementation$AbstractPendingResult<BatchResult>
{
    private int Iv;
    private boolean Iw;
    private boolean Ix;
    private final PendingResult<?>[] Iy;
    private final Object mw;
    
    private Batch(final List<PendingResult<?>> list, final Looper looper) {
        super(new BaseImplementation$CallbackHandler(looper));
        this.mw = new Object();
        this.Iv = list.size();
        this.Iy = (PendingResult<?>[])new PendingResult[this.Iv];
        for (int i = 0; i < list.size(); ++i) {
            (this.Iy[i] = list.get(i)).a(new Batch$1(this));
        }
    }
    
    @Override
    public void cancel() {
        super.cancel();
        final PendingResult<?>[] iy = this.Iy;
        for (int length = iy.length, i = 0; i < length; ++i) {
            iy[i].cancel();
        }
    }
    
    public BatchResult createFailedResult(final Status status) {
        return new BatchResult(status, this.Iy);
    }
}
