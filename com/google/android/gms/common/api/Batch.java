// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.ArrayList;
import android.os.Looper;
import java.util.List;

public final class Batch extends a<BatchResult>
{
    private int AM;
    private boolean AN;
    private boolean AO;
    private final PendingResult<?>[] AP;
    private final Object li;
    
    private Batch(final List<PendingResult<?>> list, final Looper looper) {
        super((c)new c(looper));
        this.li = new Object();
        this.AM = list.size();
        this.AP = (PendingResult<?>[])new PendingResult[this.AM];
        for (int i = 0; i < list.size(); ++i) {
            (this.AP[i] = list.get(i)).a((PendingResult.a)new PendingResult.a() {
                @Override
                public void l(Status bv) {
                    while (true) {
                    Label_0101:
                        while (true) {
                            synchronized (Batch.this.li) {
                                if (((a.a)Batch.this).isCanceled()) {
                                    return;
                                }
                                if (bv.isCanceled()) {
                                    Batch.this.AO = true;
                                    Batch.this.AM--;
                                    if (Batch.this.AM == 0) {
                                        if (!Batch.this.AO) {
                                            break Label_0101;
                                        }
                                        ((a.a)Batch.this).cancel();
                                    }
                                    return;
                                }
                            }
                            final Status status;
                            if (!status.isSuccess()) {
                                Batch.this.AN = true;
                                continue;
                            }
                            continue;
                        }
                        if (Batch.this.AN) {
                            bv = new Status(13);
                        }
                        else {
                            bv = Status.Bv;
                        }
                        ((a.a<BatchResult>)Batch.this).a(new BatchResult(bv, Batch.this.AP));
                    }
                }
            });
        }
    }
    
    @Override
    public void cancel() {
        super.cancel();
        final PendingResult<?>[] ap = this.AP;
        for (int length = ap.length, i = 0; i < length; ++i) {
            ap[i].cancel();
        }
    }
    
    public BatchResult createFailedResult(final Status status) {
        return new BatchResult(status, this.AP);
    }
    
    public static final class Builder
    {
        private List<PendingResult<?>> AR;
        private Looper AS;
        
        public Builder(final GoogleApiClient googleApiClient) {
            this.AR = new ArrayList<PendingResult<?>>();
            this.AS = googleApiClient.getLooper();
        }
        
        public <R extends Result> BatchResultToken<R> add(final PendingResult<R> pendingResult) {
            final BatchResultToken<R> batchResultToken = new BatchResultToken<R>(this.AR.size());
            this.AR.add(pendingResult);
            return batchResultToken;
        }
        
        public Batch build() {
            return new Batch(this.AR, this.AS, null);
        }
    }
}
