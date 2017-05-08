// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class VideoDetailsViewGroup$3 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ VideoDetailsViewGroup this$0;
    
    VideoDetailsViewGroup$3(final VideoDetailsViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener((View)this.this$0, (ViewTreeObserver$OnGlobalLayoutListener)this);
        if (Log.isLoggable() && this.this$0.imgGroup != null) {
            Log.v("VideoDetailsViewGroup", "img group width: " + this.this$0.imgGroup.getWidth() + ", height: " + this.this$0.imgGroup.getHeight());
        }
        this.this$0.alignViews();
    }
}
