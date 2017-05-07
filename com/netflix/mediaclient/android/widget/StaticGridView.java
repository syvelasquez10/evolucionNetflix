// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.GridView;

public class StaticGridView extends GridView
{
    public StaticGridView(final Context context) {
        super(context);
    }
    
    public StaticGridView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public StaticGridView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public void onMeasure(final int n, final int n2) {
        super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        this.getLayoutParams().height = this.getMeasuredHeight();
    }
}
