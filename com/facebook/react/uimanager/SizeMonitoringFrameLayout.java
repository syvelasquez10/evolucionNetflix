// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.util.AttributeSet;
import android.content.Context;
import android.widget.FrameLayout;

public class SizeMonitoringFrameLayout extends FrameLayout
{
    private SizeMonitoringFrameLayout$OnSizeChangedListener mOnSizeChangedListener;
    
    public SizeMonitoringFrameLayout(final Context context) {
        super(context);
    }
    
    public SizeMonitoringFrameLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public SizeMonitoringFrameLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (this.mOnSizeChangedListener != null) {
            this.mOnSizeChangedListener.onSizeChanged(n, n2, n3, n4);
        }
    }
    
    public void setOnSizeChangedListener(final SizeMonitoringFrameLayout$OnSizeChangedListener mOnSizeChangedListener) {
        this.mOnSizeChangedListener = mOnSizeChangedListener;
    }
}
