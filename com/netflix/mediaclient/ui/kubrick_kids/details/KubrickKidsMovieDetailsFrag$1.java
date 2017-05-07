// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;

class KubrickKidsMovieDetailsFrag$1 implements DetailsPageParallaxScrollListener$IScrollStateChanged
{
    final /* synthetic */ KubrickKidsMovieDetailsFrag this$0;
    final /* synthetic */ View val$shadow;
    
    KubrickKidsMovieDetailsFrag$1(final KubrickKidsMovieDetailsFrag this$0, final View val$shadow) {
        this.this$0 = this$0;
        this.val$shadow = val$shadow;
    }
    
    @Override
    public void onHeaderShown() {
        if (this.val$shadow != null) {
            this.val$shadow.setVisibility(8);
        }
    }
    
    @Override
    public void onItemsShown() {
        if (this.val$shadow != null) {
            this.val$shadow.setVisibility(0);
        }
    }
    
    @Override
    public void onScrollMaxReached() {
    }
    
    @Override
    public void onScrollMinReached() {
    }
    
    @Override
    public void onScrollStart() {
    }
    
    @Override
    public void onScrollStop() {
    }
}
