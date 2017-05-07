// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzgk;

@zzgk
public final class zzi extends zzt$zza
{
    private final AppEventListener zzsK;
    
    public zzi(final AppEventListener zzsK) {
        this.zzsK = zzsK;
    }
    
    public void onAppEvent(final String s, final String s2) {
        this.zzsK.onAppEvent(s, s2);
    }
}
