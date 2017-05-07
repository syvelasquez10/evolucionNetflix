// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ListView;

public class StaticListView extends ListView
{
    private boolean isStatic;
    
    public StaticListView(final Context context) {
        super(context);
        this.isStatic = true;
    }
    
    public StaticListView(final Context context, final AttributeSet set) {
        super(context, set);
        this.isStatic = true;
    }
    
    public StaticListView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.isStatic = true;
    }
    
    public void onMeasure(final int n, final int n2) {
        if (this.isStatic) {
            super.onMeasure(n, View$MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        }
        else {
            super.onMeasure(n, n2);
        }
        this.getLayoutParams().height = this.getMeasuredHeight();
    }
    
    public void setAsStatic(final boolean isStatic) {
        if (this.isStatic != isStatic) {
            this.isStatic = isStatic;
            this.requestLayout();
        }
    }
}
