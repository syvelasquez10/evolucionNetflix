// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.internal.zzgr;

@zzgr
public final class zzc extends zzo$zza
{
    private final AdListener zzsz;
    
    public zzc(final AdListener zzsz) {
        this.zzsz = zzsz;
    }
    
    public void onAdClosed() {
        this.zzsz.onAdClosed();
    }
    
    public void onAdFailedToLoad(final int n) {
        this.zzsz.onAdFailedToLoad(n);
    }
    
    public void onAdLeftApplication() {
        this.zzsz.onAdLeftApplication();
    }
    
    public void onAdLoaded() {
        this.zzsz.onAdLoaded();
    }
    
    public void onAdOpened() {
        this.zzsz.onAdOpened();
    }
}
