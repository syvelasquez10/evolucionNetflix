// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import android.content.res.Configuration;
import android.content.ComponentCallbacks2;

final class ImageManager$e implements ComponentCallbacks2
{
    private final ImageManager$b Kq;
    
    public ImageManager$e(final ImageManager$b kq) {
        this.Kq = kq;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
    }
    
    public void onLowMemory() {
        this.Kq.evictAll();
    }
    
    public void onTrimMemory(final int n) {
        if (n >= 60) {
            this.Kq.evictAll();
        }
        else if (n >= 20) {
            this.Kq.trimToSize(this.Kq.size() / 2);
        }
    }
}
