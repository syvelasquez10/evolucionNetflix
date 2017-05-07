// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.annotation.SuppressLint;
import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$KubrickEpisodesAdapter;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;

class KubrickKidsShowDetailsFrag$2 implements DetailsPageParallaxScrollListener$IScrollStateChanged
{
    final /* synthetic */ KubrickKidsShowDetailsFrag this$0;
    final /* synthetic */ View val$shadow;
    
    KubrickKidsShowDetailsFrag$2(final KubrickKidsShowDetailsFrag this$0, final View val$shadow) {
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
