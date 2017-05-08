// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react;

import com.facebook.react.bridge.MemoryPressure;
import android.content.ComponentCallbacks;
import java.util.Collections;
import java.util.LinkedHashSet;
import android.content.Context;
import com.facebook.react.bridge.MemoryPressureListener;
import java.util.Set;
import android.content.ComponentCallbacks2;

public class MemoryPressureRouter
{
    private final ComponentCallbacks2 mCallbacks;
    private final Set<MemoryPressureListener> mListeners;
    
    MemoryPressureRouter(final Context context) {
        this.mListeners = Collections.synchronizedSet(new LinkedHashSet<MemoryPressureListener>());
        this.mCallbacks = (ComponentCallbacks2)new MemoryPressureRouter$1(this);
        context.getApplicationContext().registerComponentCallbacks((ComponentCallbacks)this.mCallbacks);
    }
    
    private void dispatchMemoryPressure(final MemoryPressure memoryPressure) {
        final MemoryPressureListener[] array = this.mListeners.toArray(new MemoryPressureListener[this.mListeners.size()]);
        for (int length = array.length, i = 0; i < length; ++i) {
            array[i].handleMemoryPressure(memoryPressure);
        }
    }
    
    private void trimMemory(final int n) {
        if (n >= 80) {
            this.dispatchMemoryPressure(MemoryPressure.CRITICAL);
        }
        else {
            if (n >= 40 || n == 15) {
                this.dispatchMemoryPressure(MemoryPressure.MODERATE);
                return;
            }
            if (n == 20) {
                this.dispatchMemoryPressure(MemoryPressure.UI_HIDDEN);
            }
        }
    }
    
    public void addMemoryPressureListener(final MemoryPressureListener memoryPressureListener) {
        this.mListeners.add(memoryPressureListener);
    }
    
    public void destroy(final Context context) {
        context.getApplicationContext().unregisterComponentCallbacks((ComponentCallbacks)this.mCallbacks);
    }
    
    public void removeMemoryPressureListener(final MemoryPressureListener memoryPressureListener) {
        this.mListeners.remove(memoryPressureListener);
    }
}
