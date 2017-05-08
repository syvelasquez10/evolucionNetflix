// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.datasource;

import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.Producer;

public class ProducerToDataSourceAdapter<T> extends AbstractProducerToDataSourceAdapter<T>
{
    private ProducerToDataSourceAdapter(final Producer<T> producer, final SettableProducerContext settableProducerContext, final RequestListener requestListener) {
        super(producer, settableProducerContext, requestListener);
    }
    
    public static <T> DataSource<T> create(final Producer<T> producer, final SettableProducerContext settableProducerContext, final RequestListener requestListener) {
        return new ProducerToDataSourceAdapter<T>(producer, settableProducerContext, requestListener);
    }
}
