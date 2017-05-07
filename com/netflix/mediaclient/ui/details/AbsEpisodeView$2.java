// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.View$OnClickListener;

class AbsEpisodeView$2 implements View$OnClickListener
{
    final /* synthetic */ AbsEpisodeView this$0;
    final /* synthetic */ EpisodeDetails val$episode;
    
    AbsEpisodeView$2(final AbsEpisodeView this$0, final EpisodeDetails val$episode) {
        this.this$0 = this$0;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        this.this$0.playEpisode(this.val$episode);
    }
}
