// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.content.res.Resources;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.app.Status;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;

class BarkerKidsCharacterDetailsFrag$1 implements DetailsPageParallaxScrollListener$IScrollStateChanged
{
    final /* synthetic */ BarkerKidsCharacterDetailsFrag this$0;
    
    BarkerKidsCharacterDetailsFrag$1(final BarkerKidsCharacterDetailsFrag this$0) {
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
        this.this$0.heroSlideshow.stop(false);
    }
    
    @Override
    public void onScrollStop() {
    }
}
