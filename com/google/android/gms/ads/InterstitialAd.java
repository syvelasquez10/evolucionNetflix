// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.content.Context;
import com.google.android.gms.internal.au;

public final class InterstitialAd
{
    private final au kv;
    
    public InterstitialAd(final Context context) {
        this.kv = new au(context);
    }
    
    public AdListener getAdListener() {
        return this.kv.getAdListener();
    }
    
    public String getAdUnitId() {
        return this.kv.getAdUnitId();
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.kv.getInAppPurchaseListener();
    }
    
    public boolean isLoaded() {
        return this.kv.isLoaded();
    }
    
    public void loadAd(final AdRequest adRequest) {
        this.kv.a(adRequest.O());
    }
    
    public void setAdListener(final AdListener adListener) {
        this.kv.setAdListener(adListener);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.kv.setAdUnitId(adUnitId);
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener inAppPurchaseListener) {
        this.kv.setInAppPurchaseListener(inAppPurchaseListener);
    }
    
    public void show() {
        this.kv.show();
    }
}
