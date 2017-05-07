// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class af extends ao.a
{
    private final AdListener lF;
    
    public af(final AdListener lf) {
        this.lF = lf;
    }
    
    public void onAdClosed() {
        this.lF.onAdClosed();
    }
    
    public void onAdFailedToLoad(final int n) {
        this.lF.onAdFailedToLoad(n);
    }
    
    public void onAdLeftApplication() {
        this.lF.onAdLeftApplication();
    }
    
    public void onAdLoaded() {
        this.lF.onAdLoaded();
    }
    
    public void onAdOpened() {
        this.lF.onAdOpened();
    }
}
