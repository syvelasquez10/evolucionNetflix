// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.zzgk;

@zzgk
public final class zzc extends zzn$zza
{
    private final AdListener zzso;
    
    public zzc(final AdListener zzso) {
        this.zzso = zzso;
    }
    
    public void onAdClosed() {
        this.zzso.onAdClosed();
    }
    
    public void onAdFailedToLoad(final int n) {
        this.zzso.onAdFailedToLoad(n);
    }
    
    public void onAdLeftApplication() {
        this.zzso.onAdLeftApplication();
    }
    
    public void onAdLoaded() {
        this.zzso.onAdLoaded();
    }
    
    public void onAdOpened() {
        this.zzso.onAdOpened();
    }
}
