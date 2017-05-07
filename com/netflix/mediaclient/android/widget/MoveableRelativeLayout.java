// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.Log;
import android.widget.RelativeLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.RelativeLayout;

public class MoveableRelativeLayout extends RelativeLayout
{
    private static final String TAG = "nf_widget";
    
    public MoveableRelativeLayout(final Context context) {
        super(context);
    }
    
    public MoveableRelativeLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public MoveableRelativeLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public boolean addViewInLayout(final View view) {
        ViewGroup$LayoutParams viewGroup$LayoutParams;
        if ((viewGroup$LayoutParams = view.getLayoutParams()) == null && (viewGroup$LayoutParams = this.generateDefaultLayoutParams()) == null) {
            throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null");
        }
        return this.addViewInLayout(view, -1, viewGroup$LayoutParams);
    }
    
    public boolean addViewInLayout(final View view, final int n, final RelativeLayout$LayoutParams relativeLayout$LayoutParams) {
        Log.d("nf_widget", "addViewInLayout");
        return super.addViewInLayout(view, n, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
    }
    
    public boolean addViewInLayout(final View view, final RelativeLayout$LayoutParams relativeLayout$LayoutParams) {
        return this.addViewInLayout(view, -1, relativeLayout$LayoutParams);
    }
}
