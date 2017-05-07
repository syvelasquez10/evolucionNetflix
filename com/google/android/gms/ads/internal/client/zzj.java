// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.internal.zzgr;

@zzgr
public final class zzj extends zzu$zza
{
    private final AppEventListener zztj;
    
    public zzj(final AppEventListener zztj) {
        this.zztj = zztj;
    }
    
    public void onAppEvent(final String s, final String s2) {
        this.zztj.onAppEvent(s, s2);
    }
}
