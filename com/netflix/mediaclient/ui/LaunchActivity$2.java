// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.ImageView$ScaleType;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import android.widget.ProgressBar;
import android.widget.ImageView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class LaunchActivity$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ LaunchActivity this$0;
    final /* synthetic */ ImageView val$bg;
    final /* synthetic */ ImageView val$logo;
    final /* synthetic */ ProgressBar val$progress;
    
    LaunchActivity$2(final LaunchActivity this$0, final ImageView val$bg, final ImageView val$logo, final ProgressBar val$progress) {
        this.this$0 = this$0;
        this.val$bg = val$bg;
        this.val$logo = val$logo;
        this.val$progress = val$progress;
    }
    
    public void onGlobalLayout() {
        if (this.val$bg.getWidth() <= 0) {
            return;
        }
        Log.v("LaunchActivity", "Manipulating splash bg, scale factor: " + 2);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = (RelativeLayout$LayoutParams)this.val$bg.getLayoutParams();
        relativeLayout$LayoutParams.width = this.val$bg.getWidth() * 2;
        relativeLayout$LayoutParams.height = this.val$bg.getHeight() * 2;
        this.val$bg.setScaleType(ImageView$ScaleType.FIT_CENTER);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams2 = (RelativeLayout$LayoutParams)this.val$logo.getLayoutParams();
        relativeLayout$LayoutParams2.topMargin *= 2;
        if (DeviceUtils.isLandscape((Context)this.this$0)) {
            final RelativeLayout$LayoutParams relativeLayout$LayoutParams3 = (RelativeLayout$LayoutParams)this.val$progress.getLayoutParams();
            relativeLayout$LayoutParams3.topMargin *= 2;
        }
        ViewUtils.removeGlobalLayoutListener((View)this.val$bg, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
