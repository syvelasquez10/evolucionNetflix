// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;

class KubrickShowDetailsFrag$2 implements DetailsPageParallaxScrollListener$IScrollStateChanged
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    KubrickShowDetailsFrag$2(final KubrickShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onHeaderShown() {
    }
    
    @Override
    public void onItemsShown() {
    }
    
    @Override
    public void onScrollMaxReached() {
    }
    
    @Override
    public void onScrollMinReached() {
        this.this$0.heroSlideshow.start();
    }
    
    @Override
    public void onScrollStart() {
        this.this$0.heroSlideshow.stop();
    }
    
    @Override
    public void onScrollStop() {
    }
}
