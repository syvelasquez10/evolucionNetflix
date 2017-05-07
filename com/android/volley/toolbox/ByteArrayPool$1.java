// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.util.Comparator;

final class ByteArrayPool$1 implements Comparator<byte[]>
{
    @Override
    public int compare(final byte[] array, final byte[] array2) {
        return array.length - array2.length;
    }
}
