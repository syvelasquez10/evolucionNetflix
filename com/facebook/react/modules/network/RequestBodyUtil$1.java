// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import java.io.Closeable;
import okhttp3.internal.Util;
import okio.Source;
import okio.Okio;
import okio.BufferedSink;
import java.io.IOException;
import okhttp3.MediaType;
import java.io.InputStream;
import okhttp3.RequestBody;

final class RequestBodyUtil$1 extends RequestBody
{
    final /* synthetic */ InputStream val$inputStream;
    final /* synthetic */ MediaType val$mediaType;
    
    RequestBodyUtil$1(final MediaType val$mediaType, final InputStream val$inputStream) {
        this.val$mediaType = val$mediaType;
        this.val$inputStream = val$inputStream;
    }
    
    public long contentLength() {
        try {
            return this.val$inputStream.available();
        }
        catch (IOException ex) {
            return 0L;
        }
    }
    
    public MediaType contentType() {
        return this.val$mediaType;
    }
    
    public void writeTo(final BufferedSink bufferedSink) {
        Object source = null;
        try {
            bufferedSink.writeAll((Source)(source = Okio.source(this.val$inputStream)));
        }
        finally {
            Util.closeQuietly((Closeable)source);
        }
    }
}
