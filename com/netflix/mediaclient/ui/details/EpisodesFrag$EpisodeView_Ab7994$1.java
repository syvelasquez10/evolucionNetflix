// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.View$OnClickListener;

class EpisodesFrag$EpisodeView_Ab7994$1 implements View$OnClickListener
{
    final /* synthetic */ EpisodesFrag$EpisodeView_Ab7994 this$1;
    final /* synthetic */ EpisodeDetails val$episode;
    
    EpisodesFrag$EpisodeView_Ab7994$1(final EpisodesFrag$EpisodeView_Ab7994 this$1, final EpisodeDetails val$episode) {
        this.this$1 = this$1;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        this.this$1.playEpisode(this.val$episode);
    }
}
