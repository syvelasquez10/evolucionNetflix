// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag;

class BarkerKidsCharacterDetailsFrag$GalleryAdapter extends BarkerKidsShowDetailsFrag$KubrickKidsAdapter
{
    final /* synthetic */ BarkerKidsCharacterDetailsFrag this$0;
    
    public BarkerKidsCharacterDetailsFrag$GalleryAdapter(final BarkerKidsCharacterDetailsFrag this$0) {
        this.this$0 = this$0;
        super(this$0, this$0.getNetflixActivity(), this$0, new BarkerKidsCharacterDetailsFrag$GalleryAdapter$1(this$0));
    }
    
    @Override
    public int getItemCount() {
        int n = 0;
        if (this.data == null) {
            return 0;
        }
        final int size = this.data.size();
        final int headerViewsCount = this.getHeaderViewsCount();
        if (this.hasFooter()) {
            n = 1;
        }
        return n + (size + headerViewsCount);
    }
}
