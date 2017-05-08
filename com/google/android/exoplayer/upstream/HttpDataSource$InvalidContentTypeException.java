// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

public final class HttpDataSource$InvalidContentTypeException extends HttpDataSource$HttpDataSourceException
{
    public final String contentType;
    
    public HttpDataSource$InvalidContentTypeException(final String contentType, final DataSpec dataSpec) {
        super("Invalid content type: " + contentType, dataSpec, 1);
        this.contentType = contentType;
    }
}
