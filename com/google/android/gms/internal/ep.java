// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

@ez
public class ep implements InAppPurchase
{
    private final eg sx;
    
    public ep(final eg sx) {
        this.sx = sx;
    }
    
    @Override
    public String getProductId() {
        try {
            return this.sx.getProductId();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward getProductId to InAppPurchase", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public void recordPlayBillingResolution(final int n) {
        try {
            this.sx.recordPlayBillingResolution(n);
        }
        catch (RemoteException ex) {
            gs.d("Could not forward recordPlayBillingResolution to InAppPurchase", (Throwable)ex);
        }
    }
    
    @Override
    public void recordResolution(final int n) {
        try {
            this.sx.recordResolution(n);
        }
        catch (RemoteException ex) {
            gs.d("Could not forward recordResolution to InAppPurchase", (Throwable)ex);
        }
    }
}
