// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okio.Okio;
import okhttp3.MediaType;
import okio.Source;
import okio.BufferedSource;
import okhttp3.ResponseBody;

public class ProgressResponseBody extends ResponseBody
{
    private BufferedSource mBufferedSource;
    private final ProgressListener mProgressListener;
    private final ResponseBody mResponseBody;
    private long mTotalBytesRead;
    
    public ProgressResponseBody(final ResponseBody mResponseBody, final ProgressListener mProgressListener) {
        this.mResponseBody = mResponseBody;
        this.mProgressListener = mProgressListener;
        this.mTotalBytesRead = 0L;
    }
    
    private Source source(final Source source) {
        return (Source)new ProgressResponseBody$1(this, source);
    }
    
    public long contentLength() {
        return this.mResponseBody.contentLength();
    }
    
    public MediaType contentType() {
        return this.mResponseBody.contentType();
    }
    
    public BufferedSource source() {
        if (this.mBufferedSource == null) {
            this.mBufferedSource = Okio.buffer(this.source((Source)this.mResponseBody.source()));
        }
        return this.mBufferedSource;
    }
    
    public long totalBytesRead() {
        return this.mTotalBytesRead;
    }
}
