// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.widget.AdapterView$OnItemSelectedListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.graphics.Bitmap$Config;
import android.content.Context;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;

class KubrickShowDetailsFrag$BookmarkedVideoDetails extends KubrickVideoDetailsViewGroup
{
    EpisodeDetails epDetails;
    VideoDetailsViewGroup$DetailsStringProvider provider;
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    boolean updateSynopsis;
    
    public KubrickShowDetailsFrag$BookmarkedVideoDetails(final KubrickShowDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
    }
    
    private void updateWithBookmark() {
        this.updateImage(this.epDetails.getInterestingUrl(), this.this$0.getNetflixActivity(), this.epDetails.getInterestingUrl(), Bitmap$Config.RGB_565);
        this.setBookmarkVisibility(0);
        this.setEvidenceVisibility(8);
        this.updateBookmarkTitle(this.epDetails);
        this.updateBookmark(this.epDetails.getPlayable());
        this.synopsis.setText((CharSequence)this.epDetails.getSynopsis());
    }
    
    private void updateWithNoBookmark() {
        if (this.this$0.heroSlideshow != null) {
            this.this$0.heroSlideshow.start();
        }
        this.updateImage(this.this$0.showDetails.getStoryUrl(), this.this$0.getNetflixActivity(), this.this$0.showDetails.getStoryUrl(), Bitmap$Config.ARGB_8888);
        this.setBookmarkVisibility(8);
        this.synopsis.setText((CharSequence)this.this$0.showDetails.getSynopsis());
    }
    
    @Override
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider provider) {
        super.updateDetails(videoDetails, provider);
        this.provider = provider;
        final String currentEpisodeId = this.this$0.showDetails.getCurrentEpisodeId();
        if (this.this$0.manager != null && !StringUtils.isEmpty(currentEpisodeId)) {
            this.this$0.manager.getBrowse().fetchEpisodeDetails(currentEpisodeId, new KubrickShowDetailsFrag$BookmarkedVideoDetails$FetchBookmarkCallback(this, "KubrickShowDetailsFrag"));
            return;
        }
        this.updateWithNoBookmark();
        this.this$0.showViews();
    }
    
    @Override
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
    }
    
    @Override
    public void updateSynopsis(final VideoDetails videoDetails) {
    }
}
