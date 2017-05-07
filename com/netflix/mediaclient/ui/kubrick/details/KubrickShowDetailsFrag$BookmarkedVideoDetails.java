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
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.widget.TextView;
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
    
    @Override
    public void updateDetails(final VideoDetails videoDetails, final VideoDetailsViewGroup$DetailsStringProvider provider) {
        if (videoDetails == null) {
            String id;
            if (this.this$0.showDetails != null) {
                id = this.this$0.showDetails.getId();
            }
            else {
                id = "null";
            }
            String currentEpisodeId;
            if (this.this$0.showDetails == null || this.this$0.showDetails.getCurrentEpisodeId() == null) {
                currentEpisodeId = "null";
            }
            else {
                currentEpisodeId = this.this$0.showDetails.getCurrentEpisodeId();
            }
            this.this$0.getNetflixActivity().getServiceManager().getClientLogging().getErrorLogging().logHandledException(String.format("SPY-7979 - VideoDetails empty for show  %s, current episode %s", id, currentEpisodeId));
        }
        else {
            super.updateDetails(videoDetails, provider);
            this.provider = provider;
            final String currentEpisodeId2 = this.this$0.showDetails.getCurrentEpisodeId();
            if (this.this$0.manager != null) {
                this.this$0.manager.getBrowse().fetchEpisodeDetails(currentEpisodeId2, new KubrickShowDetailsFrag$BookmarkedVideoDetails$FetchBookmarkCallback(this, "KubrickShowDetailsFrag"));
            }
        }
    }
    
    @Override
    protected void updateImage(final VideoDetails videoDetails, final NetflixActivity netflixActivity, final String s) {
    }
    
    @Override
    public void updateSynopsis(final VideoDetails videoDetails) {
    }
}
