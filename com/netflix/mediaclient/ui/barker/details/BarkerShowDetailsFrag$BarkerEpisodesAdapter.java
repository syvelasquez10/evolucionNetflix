// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.widget.AdapterView$OnItemSelectedListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Parcelable;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import android.view.View;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.EpisodesAdapter;

public class BarkerShowDetailsFrag$BarkerEpisodesAdapter extends EpisodesAdapter
{
    final /* synthetic */ BarkerShowDetailsFrag this$0;
    
    public BarkerShowDetailsFrag$BarkerEpisodesAdapter(final BarkerShowDetailsFrag this$0, final NetflixActivity netflixActivity, final BarkerShowDetailsFrag barkerShowDetailsFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        this.this$0 = this$0;
        super(netflixActivity, barkerShowDetailsFrag, recyclerViewHeaderAdapter$IViewCreator);
    }
    
    private void showEpisodesViews() {
        if (this.this$0.currentEpisodes != null && this.this$0.currentEpisodes.size() > 0) {
            this.this$0.spinnerViewGroup.setVisibility(0);
            this.this$0.leWrapper.hide(false);
            this.this$0.onLoaded(CommonStatus.OK);
        }
    }
    
    @Override
    protected void onPostItemViewBind(final int n) {
        super.onPostItemViewBind(n);
        if (this.this$0.parallaxScroller != null) {
            this.this$0.parallaxScroller.resetDynamicViewsYPosition();
        }
    }
    
    @Override
    protected void updateEpisodesData(final List<EpisodeDetails> list, final int n) {
        if (!this.this$0.showRelated) {
            super.updateEpisodesData(list, n);
            this.this$0.currentEpisodes = list;
            this.showEpisodesViews();
            if (!this.this$0.showDetailsOnLaunch) {
                while (true) {
                    for (int i = 0; i < this.this$0.currentEpisodes.size(); ++i) {
                        if (StringUtils.safeEquals(((EpisodeDetails)this.this$0.currentEpisodes.get(i)).getId(), this.this$0.episodeId)) {
                            ((LinearLayoutManager)this.this$0.recyclerView.getLayoutManager()).scrollToPositionWithOffset(i + n + 1, 0);
                            return;
                        }
                    }
                    int i = 0;
                    continue;
                }
            }
        }
    }
}
