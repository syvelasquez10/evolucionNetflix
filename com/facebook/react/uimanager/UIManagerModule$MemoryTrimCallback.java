// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.content.res.Configuration;
import android.content.ComponentCallbacks2;

class UIManagerModule$MemoryTrimCallback implements ComponentCallbacks2
{
    final /* synthetic */ UIManagerModule this$0;
    
    private UIManagerModule$MemoryTrimCallback(final UIManagerModule this$0) {
        this.this$0 = this$0;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onLowMemory() {
    }
    
    public void onTrimMemory(final int n) {
        if (n >= 60) {
            YogaNodePool.get().clear();
        }
    }
}
