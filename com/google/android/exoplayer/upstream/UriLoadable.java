// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.InputStream;
import android.net.Uri;

public final class UriLoadable<T> implements Loader$Loadable
{
    private final DataSpec dataSpec;
    private volatile boolean isCanceled;
    private final UriLoadable$Parser<T> parser;
    private volatile T result;
    private final UriDataSource uriDataSource;
    
    public UriLoadable(final String s, final UriDataSource uriDataSource, final UriLoadable$Parser<T> parser) {
        this.uriDataSource = uriDataSource;
        this.parser = parser;
        this.dataSpec = new DataSpec(Uri.parse(s), 1);
    }
    
    @Override
    public final void cancelLoad() {
        this.isCanceled = true;
    }
    
    public final T getResult() {
        return this.result;
    }
    
    @Override
    public final boolean isLoadCanceled() {
        return this.isCanceled;
    }
    
    @Override
    public final void load() {
        final DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(this.uriDataSource, this.dataSpec);
        try {
            dataSourceInputStream.open();
            this.result = this.parser.parse(this.uriDataSource.getUri(), dataSourceInputStream);
        }
        finally {
            dataSourceInputStream.close();
        }
    }
}
