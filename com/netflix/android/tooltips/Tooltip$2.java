// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.text.Html;
import android.content.SharedPreferences;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.View$OnLayoutChangeListener;

class Tooltip$2 implements View$OnLayoutChangeListener
{
    final /* synthetic */ Tooltip this$0;
    final /* synthetic */ View val$target;
    
    Tooltip$2(final Tooltip this$0, final View val$target) {
        this.this$0 = this$0;
        this.val$target = val$target;
    }
    
    public void onLayoutChange(final View view, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        if (view == this.val$target || view == this.this$0.mTooltipLayout) {
            this.this$0.mTooltipLayout.setTarget(this.val$target);
        }
    }
}
