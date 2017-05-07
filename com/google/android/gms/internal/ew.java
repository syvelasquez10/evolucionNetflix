// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.a;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.b;

@ez
public final class ew extends et$a
{
    private final b oE;
    private final PublisherAdView sQ;
    
    public ew(final b oe, final PublisherAdView sq) {
        this.oE = oe;
        this.sQ = sq;
    }
    
    public void a(final es es) {
        this.oE.a(this.sQ, new ev(es));
    }
    
    public boolean e(final String s, final String s2) {
        return this.oE.a(this.sQ, s, s2);
    }
}
