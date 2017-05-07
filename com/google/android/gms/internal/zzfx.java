// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchase;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@zzgr
public final class zzfx extends zzfs$zza
{
    private final InAppPurchaseListener zztI;
    
    public zzfx(final InAppPurchaseListener zztI) {
        this.zztI = zztI;
    }
    
    public void zza(final zzfr zzfr) {
        this.zztI.onInAppPurchaseRequested(new zzga(zzfr));
    }
}
