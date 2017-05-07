// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.Intent;
import android.os.RemoteException;
import com.google.android.gms.ads.purchase.InAppPurchaseResult;

@ez
public class eo implements InAppPurchaseResult
{
    private final ek sL;
    
    public eo(final ek sl) {
        this.sL = sl;
    }
    
    @Override
    public void finishPurchase() {
        try {
            this.sL.finishPurchase();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward finishPurchase to InAppPurchaseResult", (Throwable)ex);
        }
    }
    
    @Override
    public String getProductId() {
        try {
            return this.sL.getProductId();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward getProductId to InAppPurchaseResult", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public Intent getPurchaseData() {
        try {
            return this.sL.getPurchaseData();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward getPurchaseData to InAppPurchaseResult", (Throwable)ex);
            return null;
        }
    }
    
    @Override
    public int getResultCode() {
        try {
            return this.sL.getResultCode();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward getPurchaseData to InAppPurchaseResult", (Throwable)ex);
            return 0;
        }
    }
    
    @Override
    public boolean isVerified() {
        try {
            return this.sL.isVerified();
        }
        catch (RemoteException ex) {
            gs.d("Could not forward isVerified to InAppPurchaseResult", (Throwable)ex);
            return false;
        }
    }
}
