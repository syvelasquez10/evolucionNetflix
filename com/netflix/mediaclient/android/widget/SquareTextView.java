// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.view.View$MeasureSpec;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;

public class SquareTextView extends TextView
{
    public SquareTextView(final Context context) {
        super(context);
    }
    
    public SquareTextView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public SquareTextView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public SquareTextView(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
    }
    
    public void onMeasure(int size, int size2) {
        size = View$MeasureSpec.getSize(size);
        size2 = View$MeasureSpec.getSize(size2);
        if (size > size2) {
            size = size2;
        }
        this.setMeasuredDimension(size, size);
    }
}
