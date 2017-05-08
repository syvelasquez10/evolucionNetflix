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
import android.content.res.Configuration;
import android.content.ComponentCallbacks2;

class MemoryPressureRouter$1 implements ComponentCallbacks2
{
    final /* synthetic */ MemoryPressureRouter this$0;
    
    MemoryPressureRouter$1(final MemoryPressureRouter this$0) {
        this.this$0 = this$0;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onLowMemory() {
    }
    
    public void onTrimMemory(final int n) {
        this.this$0.trimMemory(n);
    }
}
