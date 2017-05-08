// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.util.Log;
import android.text.Html;
import android.content.SharedPreferences;
import android.view.View$OnLayoutChangeListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.LayoutInflater;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.view.ViewGroup;
import android.view.View;
import android.view.View$OnClickListener;

class Tooltip$1 implements View$OnClickListener
{
    final /* synthetic */ Tooltip this$0;
    
    Tooltip$1(final Tooltip this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.mTooltipLayout.dismiss(this.this$0.mParent);
    }
}
