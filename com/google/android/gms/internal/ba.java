// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.AppEventListener;

@ez
public final class ba extends bf$a
{
    private final AppEventListener oi;
    
    public ba(final AppEventListener oi) {
        this.oi = oi;
    }
    
    public void onAppEvent(final String s, final String s2) {
        this.oi.onAppEvent(s, s2);
    }
}
