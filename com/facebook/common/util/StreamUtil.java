// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.util;

import com.facebook.common.internal.Preconditions;
import java.io.InputStream;

public class StreamUtil
{
    public static long skip(final InputStream inputStream, final long n) {
        Preconditions.checkNotNull(inputStream);
        Preconditions.checkArgument(n >= 0L);
        long n2 = n;
        long n3;
        while (true) {
            n3 = n;
            if (n2 <= 0L) {
                break;
            }
            final long skip = inputStream.skip(n2);
            if (skip > 0L) {
                n2 -= skip;
            }
            else {
                if (inputStream.read() == -1) {
                    n3 = n - n2;
                    break;
                }
                --n2;
            }
        }
        return n3;
    }
}
