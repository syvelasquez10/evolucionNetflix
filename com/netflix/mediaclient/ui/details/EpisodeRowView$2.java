// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.View$OnClickListener;

class EpisodeRowView$2 implements View$OnClickListener
{
    final /* synthetic */ EpisodeRowView this$0;
    final /* synthetic */ EpisodeDetails val$episode;
    
    EpisodeRowView$2(final EpisodeRowView this$0, final EpisodeDetails val$episode) {
        this.this$0 = this$0;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        this.this$0.playEpisode(this.val$episode);
    }
}
