// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okio.Okio;
import okhttp3.MediaType;
import okio.BufferedSink;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.Sink;
import okio.ForwardingSink;

class ProgressRequestBody$1 extends ForwardingSink
{
    long bytesWritten;
    long contentLength;
    final /* synthetic */ ProgressRequestBody this$0;
    
    ProgressRequestBody$1(final ProgressRequestBody this$0, final Sink sink) {
        this.this$0 = this$0;
        super(sink);
        this.bytesWritten = 0L;
        this.contentLength = 0L;
    }
    
    public void write(final Buffer buffer, long bytesWritten) {
        super.write(buffer, bytesWritten);
        if (this.contentLength == 0L) {
            this.contentLength = this.this$0.contentLength();
        }
        this.bytesWritten += bytesWritten;
        final ProgressListener access$000 = this.this$0.mProgressListener;
        bytesWritten = this.bytesWritten;
        access$000.onProgress(bytesWritten, this.contentLength, this.bytesWritten == this.contentLength);
    }
}
