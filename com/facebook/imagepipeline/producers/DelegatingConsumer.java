// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

public abstract class DelegatingConsumer<I, O> extends BaseConsumer<I>
{
    private final Consumer<O> mConsumer;
    
    public DelegatingConsumer(final Consumer<O> mConsumer) {
        this.mConsumer = mConsumer;
    }
    
    public Consumer<O> getConsumer() {
        return this.mConsumer;
    }
    
    @Override
    protected void onCancellationImpl() {
        this.mConsumer.onCancellation();
    }
    
    @Override
    protected void onFailureImpl(final Throwable t) {
        this.mConsumer.onFailure(t);
    }
    
    @Override
    protected void onProgressUpdateImpl(final float n) {
        this.mConsumer.onProgressUpdate(n);
    }
}
