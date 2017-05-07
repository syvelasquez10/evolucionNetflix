// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.database.CharArrayBuffer;

public final class fc
{
    public static void b(final String s, final CharArrayBuffer charArrayBuffer) {
        if (charArrayBuffer.data == null || charArrayBuffer.data.length < s.length()) {
            charArrayBuffer.data = s.toCharArray();
        }
        else {
            s.getChars(0, s.length(), charArrayBuffer.data, 0);
        }
        charArrayBuffer.sizeCopied = s.length();
    }
}
