// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.AdListener;

public final class t extends ab.a
{
    private final AdListener ev;
    
    public t(final AdListener ev) {
        this.ev = ev;
    }
    
    public void onAdClosed() {
        this.ev.onAdClosed();
    }
    
    public void onAdFailedToLoad(final int n) {
        this.ev.onAdFailedToLoad(n);
    }
    
    public void onAdLeftApplication() {
        this.ev.onAdLeftApplication();
    }
    
    public void onAdLoaded() {
        this.ev.onAdLoaded();
    }
    
    public void onAdOpened() {
        this.ev.onAdOpened();
    }
}
