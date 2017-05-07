// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchase;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@zzgk
public final class zzfr extends zzfm$zza
{
    private final InAppPurchaseListener zztp;
    
    public zzfr(final InAppPurchaseListener zztp) {
        this.zztp = zztp;
    }
    
    public void zza(final zzfl zzfl) {
        this.zztp.onInAppPurchaseRequested(new zzfu(zzfl));
    }
}
