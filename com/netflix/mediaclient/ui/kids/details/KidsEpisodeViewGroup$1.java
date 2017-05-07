// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.View$OnClickListener;

class KidsEpisodeViewGroup$1 implements View$OnClickListener
{
    final /* synthetic */ KidsEpisodeViewGroup this$0;
    final /* synthetic */ EpisodeDetails val$episode;
    
    KidsEpisodeViewGroup$1(final KidsEpisodeViewGroup this$0, final EpisodeDetails val$episode) {
        this.this$0 = this$0;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        final KidsDetailsActivity kidsDetailsActivity = (KidsDetailsActivity)this.this$0.getContext();
        PlaybackLauncher.startPlaybackAfterPIN(kidsDetailsActivity, this.val$episode.getPlayable(), kidsDetailsActivity.getPlayContext());
    }
}
