// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.content.Context;
import com.google.android.gms.internal.bi;

public final class InterstitialAd
{
    private final bi lj;
    
    public InterstitialAd(final Context context) {
        this.lj = new bi(context);
    }
    
    public AdListener getAdListener() {
        return this.lj.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.lj.getAdUnitId();
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.lj.getInAppPurchaseListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.lj.getMediationAdapterClassName();
    }
    
    public boolean isLoaded() {
        return this.lj.isLoaded();
    }
    
    public void loadAd(final AdRequest adRequest) {
        this.lj.a(adRequest.V());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.lj.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.lj.setAdUnitId(adUnitId);
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener inAppPurchaseListener) {
        this.lj.setInAppPurchaseListener(inAppPurchaseListener);
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener playStorePurchaseListener, final String s) {
        this.lj.setPlayStorePurchaseParams(playStorePurchaseListener, s);
    }
    
    public void show() {
        this.lj.show();
    }
}
