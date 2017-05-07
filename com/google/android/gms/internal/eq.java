// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchaseResult;
import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;

@ez
public final class eq extends el.a
{
    private final PlayStorePurchaseListener oD;
    
    public eq(final PlayStorePurchaseListener od) {
        this.oD = od;
    }
    
    public void a(final ek ek) {
        this.oD.onInAppPurchaseFinished(new eo(ek));
    }
    
    public boolean isValidPurchase(final String s) {
        return this.oD.isValidPurchase(s);
    }
}
