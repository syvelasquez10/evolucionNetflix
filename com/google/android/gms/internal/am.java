// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class am extends ar.a
{
    private final AppEventListener lV;
    
    public am(final AppEventListener lv) {
        this.lV = lv;
    }
    
    public void onAppEvent(final String s, final String s2) {
        this.lV.onAppEvent(s, s2);
    }
}
