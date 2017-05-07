// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchase;

public class cq implements InAppPurchase
{
    private final cn oF;
    
    public cq(final cn of) {
        this.oF = of;
    }
    
    @Override
    public String getProductId() {
        try {
            return this.oF.getProductId();
        }
        catch (RemoteException ex) {
            dw.c("Could not forward getProductId to InAppPurchase", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public void recordPlayBillingResolution(final int n) {
        try {
            this.oF.recordPlayBillingResolution(n);
        }
        catch (RemoteException ex) {
            dw.c("Could not forward recordPlayBillingResolution to InAppPurchase", (Throwable)ex);
        }
    }
    
    @Override
    public void recordResolution(final int n) {
        try {
            this.oF.recordResolution(n);
        }
        catch (RemoteException ex) {
            dw.c("Could not forward recordResolution to InAppPurchase", (Throwable)ex);
        }
    }
}
