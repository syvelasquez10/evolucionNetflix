// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class z extends ae.a
{
    private final AppEventListener eI;
    
    public z(final AppEventListener ei) {
        this.eI = ei;
    }
    
    public void onAppEvent(final String s, final String s2) {
        this.eI.onAppEvent(s, s2);
    }
}
