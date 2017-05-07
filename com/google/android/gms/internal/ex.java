// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.a;
import com.google.android.gms.ads.doubleclick.c;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;

@ez
public final class ex extends eu.a
{
    private final PublisherInterstitialAd oF;
    private final c oG;
    
    public ex(final c og, final PublisherInterstitialAd of) {
        this.oG = og;
        this.oF = of;
    }
    
    public void a(final es es) {
        this.oG.a(this.oF, new ev(es));
    }
    
    public boolean e(final String s, final String s2) {
        return this.oG.a(this.oF, s, s2);
    }
}
