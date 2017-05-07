// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchase;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;

public final class cp extends co.a
{
    private final InAppPurchaseListener mp;
    
    public cp(final InAppPurchaseListener mp) {
        this.mp = mp;
    }
    
    public void a(final cn cn) {
        this.mp.onInAppPurchaseRequested(new cq(cn));
    }
}
