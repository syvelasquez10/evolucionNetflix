// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import okio.Okio;
import okhttp3.MediaType;
import okio.BufferedSource;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.Source;
import okio.ForwardingSource;

class ProgressResponseBody$1 extends ForwardingSource
{
    final /* synthetic */ ProgressResponseBody this$0;
    
    ProgressResponseBody$1(final ProgressResponseBody this$0, final Source source) {
        this.this$0 = this$0;
        super(source);
    }
    
    public long read(final Buffer buffer, long access$000) {
        final long read = super.read(buffer, access$000);
        final ProgressResponseBody this$0 = this.this$0;
        final long access$2 = this.this$0.mTotalBytesRead;
        if (read != -1L) {
            access$000 = read;
        }
        else {
            access$000 = 0L;
        }
        this$0.mTotalBytesRead = access$000 + access$2;
        final ProgressListener access$3 = this.this$0.mProgressListener;
        access$000 = this.this$0.mTotalBytesRead;
        access$3.onProgress(access$000, this.this$0.mResponseBody.contentLength(), read == -1L);
        return read;
    }
}
