// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Map;
import com.facebook.common.executors.StatefulRunnable;

public abstract class StatefulProducerRunnable<T> extends StatefulRunnable<T>
{
    private final Consumer<T> mConsumer;
    private final ProducerListener mProducerListener;
    private final String mProducerName;
    private final String mRequestId;
    
    public StatefulProducerRunnable(final Consumer<T> mConsumer, final ProducerListener mProducerListener, final String mProducerName, final String mRequestId) {
        this.mConsumer = mConsumer;
        this.mProducerListener = mProducerListener;
        this.mProducerName = mProducerName;
        this.mRequestId = mRequestId;
        this.mProducerListener.onProducerStart(this.mRequestId, this.mProducerName);
    }
    
    @Override
    protected abstract void disposeResult(final T p0);
    
    protected Map<String, String> getExtraMapOnCancellation() {
        return null;
    }
    
    protected Map<String, String> getExtraMapOnFailure(final Exception ex) {
        return null;
    }
    
    protected Map<String, String> getExtraMapOnSuccess(final T t) {
        return null;
    }
    
    @Override
    protected void onCancellation() {
        final ProducerListener mProducerListener = this.mProducerListener;
        final String mRequestId = this.mRequestId;
        final String mProducerName = this.mProducerName;
        Map<String, String> extraMapOnCancellation;
        if (this.mProducerListener.requiresExtraMap(this.mRequestId)) {
            extraMapOnCancellation = this.getExtraMapOnCancellation();
        }
        else {
            extraMapOnCancellation = null;
        }
        mProducerListener.onProducerFinishWithCancellation(mRequestId, mProducerName, extraMapOnCancellation);
        this.mConsumer.onCancellation();
    }
    
    @Override
    protected void onFailure(final Exception ex) {
        final ProducerListener mProducerListener = this.mProducerListener;
        final String mRequestId = this.mRequestId;
        final String mProducerName = this.mProducerName;
        Map<String, String> extraMapOnFailure;
        if (this.mProducerListener.requiresExtraMap(this.mRequestId)) {
            extraMapOnFailure = this.getExtraMapOnFailure(ex);
        }
        else {
            extraMapOnFailure = null;
        }
        mProducerListener.onProducerFinishWithFailure(mRequestId, mProducerName, ex, extraMapOnFailure);
        this.mConsumer.onFailure(ex);
    }
    
    @Override
    protected void onSuccess(final T t) {
        final ProducerListener mProducerListener = this.mProducerListener;
        final String mRequestId = this.mRequestId;
        final String mProducerName = this.mProducerName;
        Map<String, String> extraMapOnSuccess;
        if (this.mProducerListener.requiresExtraMap(this.mRequestId)) {
            extraMapOnSuccess = this.getExtraMapOnSuccess(t);
        }
        else {
            extraMapOnSuccess = null;
        }
        mProducerListener.onProducerFinishWithSuccess(mRequestId, mProducerName, extraMapOnSuccess);
        this.mConsumer.onNewResult(t, true);
    }
}
