// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.EpisodesAdapter;

class KubrickShowDetailsFrag$KubrickEpisodesAdapter extends EpisodesAdapter
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    public KubrickShowDetailsFrag$KubrickEpisodesAdapter(final KubrickShowDetailsFrag this$0, final NetflixActivity netflixActivity, final KubrickShowDetailsFrag kubrickShowDetailsFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        this.this$0 = this$0;
        super(netflixActivity, kubrickShowDetailsFrag, recyclerViewHeaderAdapter$IViewCreator);
    }
    
    @Override
    protected void updateEpisodesData(final List<EpisodeDetails> currentEpisodes) {
        super.updateEpisodesData(currentEpisodes);
        this.this$0.currentEpisodes = currentEpisodes;
    }
}
