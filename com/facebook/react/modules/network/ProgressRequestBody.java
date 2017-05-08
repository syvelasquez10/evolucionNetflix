// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okio.Okio;
import okhttp3.MediaType;
import okio.Sink;
import okio.BufferedSink;
import okhttp3.RequestBody;

public class ProgressRequestBody extends RequestBody
{
    private BufferedSink mBufferedSink;
    private final ProgressListener mProgressListener;
    private final RequestBody mRequestBody;
    
    public ProgressRequestBody(final RequestBody mRequestBody, final ProgressListener mProgressListener) {
        this.mRequestBody = mRequestBody;
        this.mProgressListener = mProgressListener;
    }
    
    private Sink sink(final Sink sink) {
        return (Sink)new ProgressRequestBody$1(this, sink);
    }
    
    public long contentLength() {
        return this.mRequestBody.contentLength();
    }
    
    public MediaType contentType() {
        return this.mRequestBody.contentType();
    }
    
    public void writeTo(final BufferedSink bufferedSink) {
        if (this.mBufferedSink == null) {
            this.mBufferedSink = Okio.buffer(this.sink((Sink)bufferedSink));
        }
        this.mRequestBody.writeTo(this.mBufferedSink);
        this.mBufferedSink.flush();
    }
}
