// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.TimeUtils;
import android.content.Context;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import android.view.View$OnClickListener;

class KubrickShowDetailsFrag$KubrickEpisodeView$1 implements View$OnClickListener
{
    final /* synthetic */ KubrickShowDetailsFrag$KubrickEpisodeView this$1;
    final /* synthetic */ EpisodeDetails val$episode;
    
    KubrickShowDetailsFrag$KubrickEpisodeView$1(final KubrickShowDetailsFrag$KubrickEpisodeView this$1, final EpisodeDetails val$episode) {
        this.this$1 = this$1;
        this.val$episode = val$episode;
    }
    
    public void onClick(final View view) {
        this.this$1.playEpisode(this.val$episode);
    }
}
