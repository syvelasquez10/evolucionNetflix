// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

import com.facebook.common.internal.Preconditions;

public class SimpleDataSource<T> extends AbstractDataSource<T>
{
    public static <T> SimpleDataSource<T> create() {
        return new SimpleDataSource<T>();
    }
    
    public boolean setFailure(final Throwable t) {
        return super.setFailure(Preconditions.checkNotNull(t));
    }
    
    public boolean setProgress(final float progress) {
        return super.setProgress(progress);
    }
    
    public boolean setResult(final T t, final boolean b) {
        return super.setResult(Preconditions.checkNotNull(t), b);
    }
}
