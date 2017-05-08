// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;

public class CoppolaShowDetailsFrag extends KubrickShowDetailsFrag
{
    private static final String TAG = "CoppolaShowDetailsFrag";
    
    public CoppolaShowDetailsFrag() {
        this.viewCreatorEpisodes = new CoppolaShowDetailsFrag$1(this);
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        return EpisodesFrag.applyCreateArgs(new CoppolaShowDetailsFrag(), s, s2, b, false);
    }
    
    @Override
    protected void animateIn() {
        if (!this.isListVisible()) {
            Log.v("CoppolaShowDetailsFrag", "Showing recycler view");
            this.recyclerView.setVisibility(0);
        }
        this.recyclerView.animate().alpha(1.0f).setDuration(500L);
    }
    
    @Override
    protected int getRecyclerViewShadowWidth() {
        return DeviceUtils.getScreenWidthInPixels((Context)this.getActivity());
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new VideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
    }
    
    @Override
    protected int retrieveNumColumns() {
        return 1;
    }
    
    public void setCurrentEpisodeId(final String episodeId) {
        this.episodeId = episodeId;
    }
    
    @Override
    protected void setScrollPosition() {
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        return null;
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
    }
    
    @Override
    protected void updateSeasonData(final List<SeasonDetails> list) {
        super.updateSeasonData(list);
        this.showViews();
    }
}
