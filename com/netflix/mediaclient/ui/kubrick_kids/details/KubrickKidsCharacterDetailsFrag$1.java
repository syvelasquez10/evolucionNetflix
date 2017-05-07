// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;

class KubrickKidsCharacterDetailsFrag$1 implements DetailsPageParallaxScrollListener$IScrollStateChanged
{
    final /* synthetic */ KubrickKidsCharacterDetailsFrag this$0;
    final /* synthetic */ View val$shadow;
    
    KubrickKidsCharacterDetailsFrag$1(final KubrickKidsCharacterDetailsFrag this$0, final View val$shadow) {
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
