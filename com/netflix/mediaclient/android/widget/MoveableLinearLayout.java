// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.widget;

import com.netflix.mediaclient.Log;
import android.widget.LinearLayout$LayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.LinearLayout;

public class MoveableLinearLayout extends LinearLayout
{
    private static final String TAG = "nf_widget";
    
    public MoveableLinearLayout(final Context context) {
        super(context);
    }
    
    public MoveableLinearLayout(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public MoveableLinearLayout(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    public boolean addViewInLayout(final View view) {
        Object o;
        if ((o = view.getLayoutParams()) == null && (o = this.generateDefaultLayoutParams()) == null) {
            throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null");
        }
        return this.addViewInLayout(view, -1, (ViewGroup$LayoutParams)o);
    }
    
    public boolean addViewInLayout(final View view, final int n, final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        Log.d("nf_widget", "addViewInLayout");
        return super.addViewInLayout(view, n, (ViewGroup$LayoutParams)linearLayout$LayoutParams);
    }
    
    public boolean addViewInLayout(final View view, final LinearLayout$LayoutParams linearLayout$LayoutParams) {
        return this.addViewInLayout(view, -1, linearLayout$LayoutParams);
    }
}
