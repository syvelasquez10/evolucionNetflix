// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.res.Configuration;
import android.content.ComponentCallbacks2;

class TagManager$3 implements ComponentCallbacks2
{
    final /* synthetic */ TagManager zzaSw;
    
    TagManager$3(final TagManager zzaSw) {
        this.zzaSw = zzaSw;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onLowMemory() {
    }
    
    public void onTrimMemory(final int n) {
        if (n == 20) {
            this.zzaSw.dispatch();
        }
    }
}
