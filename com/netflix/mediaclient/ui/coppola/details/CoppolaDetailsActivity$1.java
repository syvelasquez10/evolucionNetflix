// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import com.netflix.mediaclient.android.widget.TappableSurfaceView;

class CoppolaDetailsActivity$1 implements Runnable
{
    final /* synthetic */ CoppolaDetailsActivity this$0;
    final /* synthetic */ TappableSurfaceView val$surface;
    
    CoppolaDetailsActivity$1(final CoppolaDetailsActivity this$0, final TappableSurfaceView val$surface) {
        this.this$0 = this$0;
        this.val$surface = val$surface;
    }
    
    @Override
    public void run() {
        if (this.val$surface != null) {
            this.val$surface.requestLayout();
        }
    }
}
