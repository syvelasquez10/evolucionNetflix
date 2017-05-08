// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.tooltips;

import android.util.Log;
import android.text.Html;
import android.content.SharedPreferences;
import android.view.View$OnLayoutChangeListener;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;

class Tooltip$3 implements Tooltip$Callback
{
    final /* synthetic */ Tooltip this$0;
    
    Tooltip$3(final Tooltip this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onDismissed(final Tooltip tooltip) {
        if (this.this$0.mKey != null) {
            this.this$0.getPrefs().edit().putBoolean(this.this$0.getPrefKey(), true).apply();
        }
        if (this.this$0.mCallback != null) {
            this.this$0.mCallback.onDismissed(this.this$0);
        }
    }
    
    @Override
    public void onShown(final Tooltip tooltip) {
        if (this.this$0.mCallback != null) {
            this.this$0.mCallback.onShown(this.this$0);
        }
    }
}
