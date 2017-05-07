// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzgr
public final class zzcl extends zzck$zza
{
    private final OnCustomRenderedAdLoadedListener zztK;
    
    public zzcl(final OnCustomRenderedAdLoadedListener zztK) {
        this.zztK = zztK;
    }
    
    public void zza(final zzcj zzcj) {
        this.zztK.onCustomRenderedAdLoaded(new zzci(zzcj));
    }
}
