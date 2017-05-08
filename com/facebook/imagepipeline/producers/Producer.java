// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

public interface Producer<T>
{
    void produceResults(final Consumer<T> p0, final ProducerContext p1);
}
