// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.annotation.TargetApi;
import android.os.Trace;

public final class TraceUtil
{
    public static void beginSection(final String s) {
        if (Util.SDK_INT >= 18) {
            beginSectionV18(s);
        }
    }
    
    @TargetApi(18)
    private static void beginSectionV18(final String s) {
        Trace.beginSection(s);
    }
    
    public static void endSection() {
        if (Util.SDK_INT >= 18) {
            endSectionV18();
        }
    }
    
    @TargetApi(18)
    private static void endSectionV18() {
        Trace.endSection();
    }
}
