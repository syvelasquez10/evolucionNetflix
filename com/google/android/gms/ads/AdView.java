// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.purchase.PlayStorePurchaseListener;
import android.view.View;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.bh;
import android.view.ViewGroup;

public final class AdView extends ViewGroup
{
    private final bh li;
    
    public AdView(final Context context) {
        super(context);
        this.li = new bh(this);
    }
    
    public AdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.li = new bh(this, set, false);
    }
    
    public AdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.li = new bh(this, set, false);
    }
    
    public void destroy() {
        this.li.destroy();
    }
    
    public AdListener getAdListener() {
        return this.li.getAdListener();
    }
    
    public AdSize getAdSize() {
        return this.li.getAdSize();
    }
    
    public String getAdUnitId() {
        return this.li.getAdUnitId();
    }
    
    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.li.getInAppPurchaseListener();
    }
    
    public String getMediationAdapterClassName() {
        return this.li.getMediationAdapterClassName();
    }
    
    public void loadAd(final AdRequest adRequest) {
        this.li.a(adRequest.V());
    }
    
    protected void onLayout(final boolean b, int n, int n2, final int n3, final int n4) {
        final View child = this.getChildAt(0);
        if (child != null && child.getVisibility() != 8) {
            final int measuredWidth = child.getMeasuredWidth();
            final int measuredHeight = child.getMeasuredHeight();
            n = (n3 - n - measuredWidth) / 2;
            n2 = (n4 - n2 - measuredHeight) / 2;
            child.layout(n, n2, measuredWidth + n, measuredHeight + n2);
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        int n3 = 0;
        final View child = this.getChildAt(0);
        final AdSize adSize = this.getAdSize();
        int n4;
        if (child != null && child.getVisibility() != 8) {
            this.measureChild(child, n, n2);
            n4 = child.getMeasuredWidth();
            n3 = child.getMeasuredHeight();
        }
        else if (adSize != null) {
            final Context context = this.getContext();
            n4 = adSize.getWidthInPixels(context);
            n3 = adSize.getHeightInPixels(context);
        }
        else {
            n4 = 0;
        }
        this.setMeasuredDimension(View.resolveSize(Math.max(n4, this.getSuggestedMinimumWidth()), n), View.resolveSize(Math.max(n3, this.getSuggestedMinimumHeight()), n2));
    }
    
    public void pause() {
        this.li.pause();
    }
    
    public void resume() {
        this.li.resume();
    }
    
    public void setAdListener(final AdListener adListener) {
        this.li.setAdListener(adListener);
    }
    
    public void setAdSize(final AdSize adSize) {
        this.li.setAdSizes(adSize);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.li.setAdUnitId(adUnitId);
    }
    
    public void setInAppPurchaseListener(final InAppPurchaseListener inAppPurchaseListener) {
        this.li.setInAppPurchaseListener(inAppPurchaseListener);
    }
    
    public void setPlayStorePurchaseParams(final PlayStorePurchaseListener playStorePurchaseListener, final String s) {
        this.li.setPlayStorePurchaseParams(playStorePurchaseListener, s);
    }
}
