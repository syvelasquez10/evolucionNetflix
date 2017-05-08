// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.io.Closeable;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;

class StrictLineReader$1 extends ByteArrayOutputStream
{
    final /* synthetic */ StrictLineReader this$0;
    
    StrictLineReader$1(final StrictLineReader this$0, final int n) {
        this.this$0 = this$0;
        super(n);
    }
    
    @Override
    public String toString() {
        Label_0056: {
            if (this.count <= 0 || this.buf[this.count - 1] != 13) {
                break Label_0056;
            }
            int count = this.count - 1;
            try {
                return new String(this.buf, 0, count, this.this$0.charset.name());
                count = this.count;
                return new String(this.buf, 0, count, this.this$0.charset.name());
            }
            catch (UnsupportedEncodingException ex) {
                throw new AssertionError((Object)ex);
            }
        }
    }
}
