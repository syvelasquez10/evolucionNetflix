// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$KubrickEpisodeView;

class KubrickKidsShowDetailsFrag$KubrickKidsEpisodeView extends KubrickShowDetailsFrag$KubrickEpisodeView
{
    final /* synthetic */ KubrickKidsShowDetailsFrag this$0;
    
    public KubrickKidsShowDetailsFrag$KubrickKidsEpisodeView(final KubrickKidsShowDetailsFrag this$0, final Context context, final int n) {
        this.this$0 = this$0;
        super(this$0, context, n);
    }
    
    @Override
    protected CharSequence createTitleText(final EpisodeDetails episodeDetails) {
        return episodeDetails.getTitle();
    }
    
    @Override
    protected void updateProgressBar() {
        if (this.progressBar == null) {
            return;
        }
        if (this.progressVal > 0 && this.isCurrentEpisode) {
            this.progressBar.setVisibility(0);
            this.progressBar.setProgress(this.progressVal);
            this.progressBar.setSecondaryProgress(0);
            return;
        }
        this.progressBar.setVisibility(8);
    }
    
    @Override
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title == null) {
            return;
        }
        super.updateTitle(episodeDetails);
        this.title.setTextColor(this.getResources().getColor(2131296434));
    }
}
