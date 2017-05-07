// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import android.util.AttributeSet;
import android.content.Context;
import com.google.android.gms.internal.ag;
import android.view.ViewGroup;

public final class SearchAdView extends ViewGroup
{
    private final ag dZ;
    
    public SearchAdView(final Context context) {
        super(context);
        this.dZ = new ag(this);
    }
    
    public SearchAdView(final Context context, final AttributeSet set) {
        super(context, set);
        this.dZ = new ag(this, set, false);
    }
    
    public SearchAdView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.dZ = new ag(this, set, false);
    }
    
    public void destroy() {
        this.dZ.destroy();
    }
    
    public AdListener getAdListener() {
        return this.dZ.getAdListener();
    }
    
    public AdSize getAdSize() {
        return this.dZ.getAdSize();
    }
    
    public String getAdUnitId() {
        return this.dZ.getAdUnitId();
    }
    
    public void loadAd(final SearchAdRequest searchAdRequest) {
        this.dZ.a(searchAdRequest.v());
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
        this.dZ.pause();
    }
    
    public void resume() {
        this.dZ.resume();
    }
    
    public void setAdListener(final AdListener adListener) {
        this.dZ.setAdListener(adListener);
    }
    
    public void setAdSize(final AdSize adSize) {
        this.dZ.setAdSizes(adSize);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.dZ.setAdUnitId(adUnitId);
    }
}
