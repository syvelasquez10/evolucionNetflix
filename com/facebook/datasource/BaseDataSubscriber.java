// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.datasource;

public abstract class BaseDataSubscriber<T> implements DataSubscriber<T>
{
    @Override
    public void onCancellation(final DataSource<T> dataSource) {
    }
    
    @Override
    public void onFailure(final DataSource<T> dataSource) {
        try {
            this.onFailureImpl(dataSource);
        }
        finally {
            dataSource.close();
        }
    }
    
    protected abstract void onFailureImpl(final DataSource<T> p0);
    
    @Override
    public void onNewResult(final DataSource<T> dataSource) {
        final boolean finished = dataSource.isFinished();
        try {
            this.onNewResultImpl(dataSource);
        }
        finally {
            if (finished) {
                dataSource.close();
            }
        }
    }
    
    protected abstract void onNewResultImpl(final DataSource<T> p0);
    
    @Override
    public void onProgressUpdate(final DataSource<T> dataSource) {
    }
}
