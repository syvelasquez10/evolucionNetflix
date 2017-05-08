// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import com.netflix.mediaclient.Log;
import android.widget.ImageView;
import android.view.ViewTreeObserver$OnPreDrawListener;

class InteractiveTracker$1 implements ViewTreeObserver$OnPreDrawListener
{
    final /* synthetic */ InteractiveTracker this$0;
    final /* synthetic */ ImageView val$view;
    
    InteractiveTracker$1(final InteractiveTracker this$0, final ImageView val$view) {
        this.this$0 = this$0;
        this.val$view = val$view;
    }
    
    public boolean onPreDraw() {
        this.val$view.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
        Log.d("InteractiveTracker", "onPreDraw");
        if (!this.this$0.isComplete() && this.this$0.onscreenListeners.isEmpty()) {
            Log.i("InteractiveTracker", "ALL IMAGES LOADED!!!.. before first ");
            this.this$0.isNowInteractive();
        }
        return true;
    }
}
