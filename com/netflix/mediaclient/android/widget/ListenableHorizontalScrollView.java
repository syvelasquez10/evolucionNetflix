// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.HorizontalScrollView;

public class ListenableHorizontalScrollView extends HorizontalScrollView
{
    private ListenableHorizontalScrollView$OnScrollChangedListener onScrollChangedListener;
    
    public ListenableHorizontalScrollView(final Context context) {
        super(context);
    }
    
    public ListenableHorizontalScrollView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public ListenableHorizontalScrollView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected void onScrollChanged(final int n, final int n2, final int n3, final int n4) {
        super.onScrollChanged(n, n2, n3, n4);
        if (this.onScrollChangedListener != null) {
            this.onScrollChangedListener.onScrollChanged(n, n2, n3, n4);
        }
    }
    
    public void setOnScrollChangedListener(final ListenableHorizontalScrollView$OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }
}
