// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.buffet;

import java.lang.ref.WeakReference;

class BuffetManager$SnackbarRecord
{
    boolean animated;
    final WeakReference<BuffetManager$Callback> callback;
    int duration;
    
    BuffetManager$SnackbarRecord(final int duration, final boolean animated, final BuffetManager$Callback buffetManager$Callback) {
        this.callback = new WeakReference<BuffetManager$Callback>(buffetManager$Callback);
        this.duration = duration;
        this.animated = animated;
    }
    
    boolean isSnackbar(final BuffetManager$Callback buffetManager$Callback) {
        return buffetManager$Callback != null && this.callback.get() == buffetManager$Callback;
    }
}
