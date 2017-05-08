// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.coppola.details.CoppolaDetailsActivity;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.View$OnClickListener;

class EpisodesFrag$EpisodeView$1 implements View$OnClickListener
{
    final /* synthetic */ EpisodesFrag$EpisodeView this$1;
    final /* synthetic */ EpisodeDetails val$episode;
    
    EpisodesFrag$EpisodeView$1(final EpisodesFrag$EpisodeView this$1, final EpisodeDetails val$episode) {
        this.this$1 = this$1;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        this.this$1.playEpisode(this.val$episode);
        if (this.this$1.this$0.getActivity() instanceof CoppolaDetailsActivity) {
            ((CoppolaDetailsActivity)this.this$1.this$0.getActivity()).setEpisodesLayoutCurrentEpisodeId(this.val$episode.getId(), this.this$1.this$0.currSeasonIndex);
        }
    }
}
