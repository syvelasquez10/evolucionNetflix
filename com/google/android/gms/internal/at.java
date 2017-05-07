// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

@ez
public final class at extends bc$a
{
    private final AdListener nR;
    
    public at(final AdListener nr) {
        this.nR = nr;
    }
    
    public void onAdClosed() {
        this.nR.onAdClosed();
    }
    
    public void onAdFailedToLoad(final int n) {
        this.nR.onAdFailedToLoad(n);
    }
    
    public void onAdLeftApplication() {
        this.nR.onAdLeftApplication();
    }
    
    public void onAdLoaded() {
        this.nR.onAdLoaded();
    }
    
    public void onAdOpened() {
        this.nR.onAdOpened();
    }
}
