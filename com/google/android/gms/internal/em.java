// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.ads.purchase.InAppPurchase;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;

@ez
public final class em extends eh$a
{
    private final InAppPurchaseListener oC;
    
    public em(final InAppPurchaseListener oc) {
        this.oC = oc;
    }
    
    public void a(final eg eg) {
        this.oC.onInAppPurchaseRequested(new ep(eg));
    }
}
