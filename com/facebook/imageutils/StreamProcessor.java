// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageutils;

import java.io.IOException;
import java.io.InputStream;

class StreamProcessor
{
    public static int readPackedInt(final InputStream inputStream, final int n, final boolean b) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final int read = inputStream.read();
            if (read == -1) {
                throw new IOException("no more bytes");
            }
            if (b) {
                n2 |= (read & 0xFF) << i * 8;
            }
            else {
                n2 = (n2 << 8 | (read & 0xFF));
            }
        }
        return n2;
    }
}
