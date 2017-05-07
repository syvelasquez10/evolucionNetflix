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
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.Log;
import android.widget.AdapterView$OnItemSelectedListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.view.View;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.EpisodesAdapter;

public class KubrickShowDetailsFrag$KubrickEpisodesAdapter extends EpisodesAdapter
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    public KubrickShowDetailsFrag$KubrickEpisodesAdapter(final KubrickShowDetailsFrag this$0, final NetflixActivity netflixActivity, final KubrickShowDetailsFrag kubrickShowDetailsFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        this.this$0 = this$0;
        super(netflixActivity, kubrickShowDetailsFrag, recyclerViewHeaderAdapter$IViewCreator);
    }
    
    private void showEpisodesViews() {
        if (this.this$0.currentEpisodes != null && this.this$0.currentEpisodes.size() > 0) {
            this.this$0.spinnerViewGroup.setVisibility(0);
            this.this$0.leWrapper.hide(false);
        }
    }
    
    @Override
    protected void updateEpisodesData(final List<EpisodeDetails> list, final int n) {
        if (!this.this$0.showRelated) {
            super.updateEpisodesData(list, n);
            this.this$0.currentEpisodes = list;
            this.showEpisodesViews();
        }
    }
}
