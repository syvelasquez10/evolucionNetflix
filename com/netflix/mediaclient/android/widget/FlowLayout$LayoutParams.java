// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.content.res.TypedArray;
import com.netflix.android.widgetry.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup$LayoutParams;

public class FlowLayout$LayoutParams extends ViewGroup$LayoutParams
{
    int horizontalSpacing;
    int x;
    int y;
    
    public FlowLayout$LayoutParams(final int n, final int n2) {
        super(n, n2);
        this.horizontalSpacing = -1;
    }
    
    public FlowLayout$LayoutParams(Context obtainStyledAttributes, final AttributeSet set) {
        super(obtainStyledAttributes, set);
        this.horizontalSpacing = -1;
        obtainStyledAttributes = (Context)obtainStyledAttributes.obtainStyledAttributes(set, R$styleable.FlowLayout_LayoutParams);
        try {
            this.horizontalSpacing = ((TypedArray)obtainStyledAttributes).getDimensionPixelSize(R$styleable.FlowLayout_LayoutParams_layout_fl_horizontalSpacing, -1);
        }
        finally {
            ((TypedArray)obtainStyledAttributes).recycle();
        }
    }
}
