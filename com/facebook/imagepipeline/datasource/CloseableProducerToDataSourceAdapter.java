// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.datasource;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.common.references.CloseableReference;

public class CloseableProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<CloseableReference<T>>
{
    private CloseableProducerToDataSourceAdapter(final Producer<CloseableReference<T>> producer, final SettableProducerContext settableProducerContext, final RequestListener requestListener) {
        super(producer, settableProducerContext, requestListener);
    }
    
    public static <T> DataSource<CloseableReference<T>> create(final Producer<CloseableReference<T>> producer, final SettableProducerContext settableProducerContext, final RequestListener requestListener) {
        return (DataSource<CloseableReference<T>>)new CloseableProducerToDataSourceAdapter((Producer<CloseableReference<Object>>)producer, settableProducerContext, requestListener);
    }
    
    @Override
    protected void closeResult(final CloseableReference<T> closeableReference) {
        CloseableReference.closeSafely(closeableReference);
    }
    
    @Override
    public CloseableReference<T> getResult() {
        return CloseableReference.cloneOrNull(super.getResult());
    }
    
    @Override
    protected void onNewResultImpl(final CloseableReference<T> closeableReference, final boolean b) {
        super.onNewResultImpl(CloseableReference.cloneOrNull(closeableReference), b);
    }
}
