// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.graphics.drawable.TransitionDrawable;

class KidsParallax$1 implements Runnable
{
    final /* synthetic */ KidsParallax this$0;
    final /* synthetic */ TransitionDrawable val$transition;
    
    KidsParallax$1(final KidsParallax this$0, final TransitionDrawable val$transition) {
        this.this$0 = this$0;
        this.val$transition = val$transition;
    }
    
    @Override
    public void run() {
        this.val$transition.startTransition(300);
        this.this$0.isLatched = true;
        this.this$0.animating = false;
    }
}
