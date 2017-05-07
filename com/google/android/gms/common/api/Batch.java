// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.ArrayList;
import android.os.Looper;
import java.util.List;

public final class Batch extends AbstractPendingResult<BatchResult>
{
    private int Iv;
    private boolean Iw;
    private boolean Ix;
    private final PendingResult<?>[] Iy;
    private final Object mw;
    
    private Batch(final List<PendingResult<?>> list, final Looper looper) {
        super((CallbackHandler)new BaseImplementation.CallbackHandler(looper));
        this.mw = new Object();
        this.Iv = list.size();
        this.Iy = (PendingResult<?>[])new PendingResult[this.Iv];
        for (int i = 0; i < list.size(); ++i) {
            (this.Iy[i] = list.get(i)).a((PendingResult.a)new PendingResult.a() {
                @Override
                public void n(Status jo) {
                    while (true) {
                    Label_0101:
                        while (true) {
                            synchronized (Batch.this.mw) {
                                if (((BaseImplementation.AbstractPendingResult)Batch.this).isCanceled()) {
                                    return;
                                }
                                if (jo.isCanceled()) {
                                    Batch.this.Ix = true;
                                    Batch.this.Iv--;
                                    if (Batch.this.Iv == 0) {
                                        if (!Batch.this.Ix) {
                                            break Label_0101;
                                        }
                                        ((BaseImplementation.AbstractPendingResult)Batch.this).cancel();
                                    }
                                    return;
                                }
                            }
                            final Status status;
                            if (!status.isSuccess()) {
                                Batch.this.Iw = true;
                                continue;
                            }
                            continue;
                        }
                        if (Batch.this.Iw) {
                            jo = new Status(13);
                        }
                        else {
                            jo = Status.Jo;
                        }
                        ((BaseImplementation.AbstractPendingResult<BatchResult>)Batch.this).b(new BatchResult(jo, Batch.this.Iy));
                    }
                }
            });
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
    
    public static final class Builder
    {
        private List<PendingResult<?>> IA;
        private Looper IB;
        
        public Builder(final GoogleApiClient googleApiClient) {
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
}
