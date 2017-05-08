// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import java.util.List;
import java.util.Map;

public final class HttpDataSource$InvalidResponseCodeException extends HttpDataSource$HttpDataSourceException
{
    public final Map<String, List<String>> headerFields;
    public final int responseCode;
    
    public HttpDataSource$InvalidResponseCodeException(final int responseCode, final Map<String, List<String>> headerFields, final DataSpec dataSpec) {
        super("Response code: " + responseCode, dataSpec, 1);
        this.responseCode = responseCode;
        this.headerFields = headerFields;
    }
}
