// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.CustomRenderedAd;
import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzgk
public final class zzci extends zzch$zza
{
    private final OnCustomRenderedAdLoadedListener zztr;
    
    public zzci(final OnCustomRenderedAdLoadedListener zztr) {
        this.zztr = zztr;
    }
    
    public void zza(final zzcg zzcg) {
        this.zztr.onCustomRenderedAdLoaded(new zzcf(zzcg));
    }
}
