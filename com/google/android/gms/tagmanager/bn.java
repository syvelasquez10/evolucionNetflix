// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;

class bn
{
    int nN() {
        return Build$VERSION.SDK_INT;
    }
    
    public bm ov() {
        if (this.nN() < 8) {
            return new av();
        }
        return new aw();
    }
}
