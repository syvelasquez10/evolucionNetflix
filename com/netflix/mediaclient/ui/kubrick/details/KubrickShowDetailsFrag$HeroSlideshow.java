// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.Log;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AbsListView;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.widget.GridView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.View;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
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
    
    public void startHeroSlideshow() {
        if (this.this$0.showDetails instanceof KubrickShowDetails) {
            final KubrickShowDetails kubrickShowDetails = (KubrickShowDetails)this.this$0.showDetails;
            (this.slideShowImageUrls = kubrickShowDetails.getHeroImages()).add(kubrickShowDetails.getKubrickStoryImgUrl());
            this.currentSlideshowIndex = 0;
            this.handler.postDelayed(this.slideshowTask, 3000L);
        }
    }
}
