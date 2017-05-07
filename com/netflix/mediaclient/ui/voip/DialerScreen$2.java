// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.voip;

import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.servicemgr.IVoip$Call;
import com.netflix.mediaclient.util.AndroidUtils;
import android.media.ToneGenerator;
import android.view.View$OnTouchListener;
import android.os.Handler;
import com.netflix.mediaclient.util.TimeFormatterHelper;
import android.support.design.widget.FloatingActionButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View;
import android.media.AudioManager;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class DialerScreen$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ DialerScreen this$0;
    
    DialerScreen$2(final DialerScreen this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        if (DeviceUtils.isLandscape((Context)this.this$0.mOwner)) {
            final int measuredHeight = this.this$0.mStatusContainer.getMeasuredHeight();
            final LinearLayout$LayoutParams layoutParams = new LinearLayout$LayoutParams(measuredHeight, measuredHeight);
            layoutParams.gravity = 1;
            this.this$0.mDialerContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            final LinearLayout$LayoutParams layoutParams2 = new LinearLayout$LayoutParams(measuredHeight, -2);
            layoutParams2.gravity = 1;
            this.this$0.mButtonsContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        }
        else {
            final int measuredWidth = this.this$0.mStatusContainer.getMeasuredWidth();
            final int n = this.this$0.mStatusContainer.getMeasuredHeight() + this.this$0.mButtonsContainer.getMeasuredHeight();
            final int n2 = this.this$0.mButtonsContainer.getMeasuredHeight() + measuredWidth;
            if (Log.isLoggable()) {
                Log.d("VoipActivity", "w in portrait: " + measuredWidth);
                Log.d("VoipActivity", "old screen h in portrait: " + n);
                Log.d("VoipActivity", "new screen h in portrait: " + n2);
            }
            int n3 = measuredWidth;
            if (n2 > n) {
                n3 = measuredWidth - n2 + n;
            }
            final LinearLayout$LayoutParams layoutParams3 = new LinearLayout$LayoutParams(n3, n3);
            layoutParams3.gravity = 1;
            this.this$0.mDialerContainer.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        }
        ViewUtils.removeGlobalLayoutListener(this.this$0.mStatusContainer, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
