// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

public final class SystemClock implements Clock
{
    @Override
    public long elapsedRealtime() {
        return android.os.SystemClock.elapsedRealtime();
    }
}
