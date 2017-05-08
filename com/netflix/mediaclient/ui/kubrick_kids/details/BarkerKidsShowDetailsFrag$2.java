// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.kubrick.details.BarkerVideoDetailsViewGroup;
import android.view.View;
import com.netflix.mediaclient.ui.kubrick.details.BarkerHelper;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.Log;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.PorterDuff$Mode;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import android.view.ViewGroup;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;

class BarkerKidsShowDetailsFrag$2 implements DetailsPageParallaxScrollListener$IScrollStateChanged
{
    final /* synthetic */ BarkerKidsShowDetailsFrag this$0;
    
    BarkerKidsShowDetailsFrag$2(final BarkerKidsShowDetailsFrag this$0) {
        this.this$0 = this$0;
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
        this.this$0.isSeasonUserSelected = false;
        this.this$0.heroSlideshow.stop(false);
    }
    
    @Override
    public void onScrollStop() {
    }
}
