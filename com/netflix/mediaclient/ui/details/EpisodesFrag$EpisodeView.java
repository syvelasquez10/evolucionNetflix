// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.util.StringUtils;
import android.widget.ImageView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.content.Context;

public class EpisodesFrag$EpisodeView extends AbsEpisodeView
{
    final /* synthetic */ EpisodesFrag this$0;
    
    public EpisodesFrag$EpisodeView(final EpisodesFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(context, n);
    }
    
    @Override
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.playButton == null) {
            return;
        }
        final ImageView playButton = this.playButton;
        int visibility;
        if (episodeDetails.isAvailableToStream()) {
            visibility = 0;
        }
        else {
            visibility = 4;
        }
        playButton.setVisibility(visibility);
        this.playButton.setOnClickListener((View$OnClickListener)new EpisodesFrag$EpisodeView$1(this, episodeDetails));
    }
    
    public void update(final EpisodeDetails episodeDetails) {
        this.update(episodeDetails, StringUtils.safeEquals(this.this$0.showDetails.getCurrentEpisodeId(), episodeDetails.getId()));
    }
}
