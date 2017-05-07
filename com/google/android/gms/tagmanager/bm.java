// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;

class bm
{
    int jZ() {
        return Build$VERSION.SDK_INT;
    }
    
    public bl kH() {
        if (this.jZ() < 8) {
            return new av();
        }
        return new aw();
    }
}
