// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Build$VERSION;

public class zzqw
{
    public zzqv zzBW() {
        if (this.zzzz() < 8) {
            return new zzqt();
        }
        return new zzqu();
    }
    
    int zzzz() {
        return Build$VERSION.SDK_INT;
    }
}
