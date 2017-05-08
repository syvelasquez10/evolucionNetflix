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
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayLocationType;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.view.View$OnClickListener;

class CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView$2 implements View$OnClickListener
{
    final /* synthetic */ CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView this$1;
    final /* synthetic */ EpisodeDetails val$episode;
    
    CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView$2(final CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView this$1, final EpisodeDetails val$episode) {
        this.this$1 = this$1;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        PlayContext playContext = PlayContext.EMPTY_CONTEXT;
        if (this.this$1.getContext() instanceof PlayContextProvider) {
            playContext = ((PlayContextProvider)this.this$1.getContext()).getPlayContext();
        }
        playContext.setPlayLocation(PlayLocationType.EPISODE);
        PlaybackLauncher.startPlaybackAfterPIN(this.this$1.this$0.getNetflixActivity(), this.val$episode.getPlayable(), playContext);
        this.this$1.this$0.episodeId = this.val$episode.getId();
    }
}
