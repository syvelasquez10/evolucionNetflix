// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.scroll;

import android.os.SystemClock;

public class OnScrollDispatchHelper
{
    private long mLastScrollEventTimeMs;
    private int mPrevX;
    private int mPrevY;
    
    public OnScrollDispatchHelper() {
        this.mPrevX = Integer.MIN_VALUE;
        this.mPrevY = Integer.MIN_VALUE;
        this.mLastScrollEventTimeMs = -11L;
    }
    
    public boolean onScrollChanged(final int mPrevX, final int mPrevY) {
        final long uptimeMillis = SystemClock.uptimeMillis();
        final boolean b = uptimeMillis - this.mLastScrollEventTimeMs > 10L || this.mPrevX != mPrevX || this.mPrevY != mPrevY;
        this.mLastScrollEventTimeMs = uptimeMillis;
        this.mPrevX = mPrevX;
        this.mPrevY = mPrevY;
        return b;
    }
}
