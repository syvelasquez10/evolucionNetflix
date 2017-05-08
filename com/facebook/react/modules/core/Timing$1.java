// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.core;

import com.facebook.react.bridge.ExecutorToken;
import java.util.Comparator;

class Timing$1 implements Comparator<Timing$Timer>
{
    final /* synthetic */ Timing this$0;
    
    Timing$1(final Timing this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int compare(final Timing$Timer timing$Timer, final Timing$Timer timing$Timer2) {
        final long n = timing$Timer.mTargetTime - timing$Timer2.mTargetTime;
        if (n == 0L) {
            return 0;
        }
        if (n < 0L) {
            return -1;
        }
        return 1;
    }
}
