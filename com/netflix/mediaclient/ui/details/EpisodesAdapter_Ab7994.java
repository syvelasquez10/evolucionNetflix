// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;

public class EpisodesAdapter_Ab7994 extends EpisodesAdapter
{
    private List<EpisodeDetails> episodeList;
    
    public EpisodesAdapter_Ab7994(final NetflixActivity netflixActivity, final EpisodesFrag episodesFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        super(netflixActivity, episodesFrag, recyclerViewHeaderAdapter$IViewCreator);
    }
    
    public List<EpisodeDetails> getEpisodeList() {
        return this.episodeList;
    }
    
    @Override
    protected void updateEpisodesData(final List<EpisodeDetails> list, final int n) {
        if (this.episodeListFrag instanceof EpisodesFrag_Ab7994) {
            if (((EpisodesFrag_Ab7994)this.episodeListFrag).isShowingEpisodes()) {
                super.updateEpisodesData(this.episodeList = list, n);
            }
            return;
        }
        super.updateEpisodesData(this.episodeList = list, n);
    }
}
