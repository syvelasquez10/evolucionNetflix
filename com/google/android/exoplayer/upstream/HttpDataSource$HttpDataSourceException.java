// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.io.IOException;

public class HttpDataSource$HttpDataSourceException extends IOException
{
    public final DataSpec dataSpec;
    public final int type;
    
    public HttpDataSource$HttpDataSourceException(final IOException ex, final DataSpec dataSpec, final int type) {
        super(ex);
        this.dataSpec = dataSpec;
        this.type = type;
    }
    
    public HttpDataSource$HttpDataSourceException(final String s, final DataSpec dataSpec, final int type) {
        super(s);
        this.dataSpec = dataSpec;
        this.type = type;
    }
    
    public HttpDataSource$HttpDataSourceException(final String s, final IOException ex, final DataSpec dataSpec, final int type) {
        super(s, ex);
        this.dataSpec = dataSpec;
        this.type = type;
    }
}
