// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.search;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.internal.client.zzy;
import android.view.ViewGroup;

public final class SearchAdView extends ViewGroup
{
    private final zzy zznS;
    
    public AdListener getAdListener() {
        return this.zznS.getAdListener();
    }
    
    public AdSize getAdSize() {
        return this.zznS.getAdSize();
    }
    
    public String getAdUnitId() {
        return this.zznS.getAdUnitId();
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
        int n4;
        if (child != null && child.getVisibility() != 8) {
            this.measureChild(child, n, n2);
            n4 = child.getMeasuredWidth();
            n3 = child.getMeasuredHeight();
        }
        else {
            final AdSize adSize = this.getAdSize();
            if (adSize != null) {
                final Context context = this.getContext();
                n4 = adSize.getWidthInPixels(context);
                n3 = adSize.getHeightInPixels(context);
            }
            else {
                n4 = 0;
            }
        }
        this.setMeasuredDimension(View.resolveSize(Math.max(n4, this.getSuggestedMinimumWidth()), n), View.resolveSize(Math.max(n3, this.getSuggestedMinimumHeight()), n2));
    }
    
    public void setAdListener(final AdListener adListener) {
        this.zznS.setAdListener(adListener);
    }
    
    public void setAdSize(final AdSize adSize) {
        this.zznS.setAdSizes(adSize);
    }
    
    public void setAdUnitId(final String adUnitId) {
        this.zznS.setAdUnitId(adUnitId);
    }
}
