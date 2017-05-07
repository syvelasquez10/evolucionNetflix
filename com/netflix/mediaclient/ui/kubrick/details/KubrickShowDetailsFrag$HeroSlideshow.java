// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.view.View;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.servicemgr.model.details.KubrickShowDetails;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.List;
import android.os.Handler;

class KubrickShowDetailsFrag$HeroSlideshow
{
    private static final int INTERVAL_3_SECS = 3000;
    private static final int START_DELAY_3_SECS = 3000;
    private int currentSlideshowIndex;
    private final Handler handler;
    private List<String> slideShowImageUrls;
    private final Runnable slideshowTask;
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    private KubrickShowDetailsFrag$HeroSlideshow(final KubrickShowDetailsFrag this$0) {
        this.this$0 = this$0;
        this.handler = new Handler();
        this.slideshowTask = new KubrickShowDetailsFrag$HeroSlideshow$1(this);
    }
    
    private void animateSlideshow() {
        final AdvancedImageView heroImage = this.this$0.detailsViewGroup.getHeroImage();
        if (heroImage != null && this.this$0.getActivity() != null && this.slideShowImageUrls != null && this.currentSlideshowIndex < this.slideShowImageUrls.size()) {
            NetflixActivity.getImageLoader((Context)this.this$0.getActivity()).getImg(this.slideShowImageUrls.get(this.currentSlideshowIndex), IClientLogging$AssetType.boxArt, heroImage.getMeasuredWidth(), heroImage.getMeasuredHeight(), new KubrickShowDetailsFrag$HeroSlideshow$2(this, heroImage));
            ++this.currentSlideshowIndex;
            if (this.currentSlideshowIndex >= this.slideShowImageUrls.size()) {
                this.currentSlideshowIndex = 0;
            }
        }
    }
    
    public void start() {
        if (this.this$0.showDetails instanceof KubrickShowDetails) {
            final KubrickShowDetails kubrickShowDetails = (KubrickShowDetails)this.this$0.showDetails;
            (this.slideShowImageUrls = kubrickShowDetails.getHeroImages()).add(kubrickShowDetails.getKubrickStoryImgUrl());
            this.currentSlideshowIndex = 0;
            this.handler.postDelayed(this.slideshowTask, 3000L);
        }
    }
    
    public void stop() {
        this.handler.removeCallbacks(this.slideshowTask);
    }
}
