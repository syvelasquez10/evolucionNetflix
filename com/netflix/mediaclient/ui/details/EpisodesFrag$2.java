// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class EpisodesFrag$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ EpisodesFrag this$0;
    
    EpisodesFrag$2(final EpisodesFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener((View)this.this$0.detailsViewGroup, (ViewTreeObserver$OnGlobalLayoutListener)this);
        this.this$0.setupDetailsPageParallaxScrollListener();
    }
}
