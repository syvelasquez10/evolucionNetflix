// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.coppola.details;

import com.netflix.mediaclient.ui.details.AbsEpisodeView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.PressedStateHandler$DelayedOnClickListener;
import com.netflix.mediaclient.util.Coppola1Utils;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.TextView;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.details.BarkerShowDetailsFrag$BarkerPlayerDialogEpisodesView;

public class CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView extends BarkerShowDetailsFrag$BarkerPlayerDialogEpisodesView
{
    private static final int DEFAULT_SYNOPSIS_MAX_LINES = 4;
    private static final int DEFAULT_TITLE_MAX_LINES = 1;
    private static final int MAX_SYNOPSIS_MAX_LINES = 20;
    private static final int MAX_TITLE_MAX_LINES = 4;
    final /* synthetic */ CoppolaShowDetailsFrag this$0;
    
    public CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView(final CoppolaShowDetailsFrag this$0, final Context context) {
        this.this$0 = this$0;
        super(this$0, context, 2130903092);
        this.synopsis.setOnClickListener((View$OnClickListener)new CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView$1(this, this$0));
    }
    
    @Override
    protected void adjustEpisodeImageHeight() {
        final int n = DeviceUtils.getScreenWidthInPixels(this.getContext()) / 2 - this.this$0.getActivity().getResources().getDimensionPixelOffset(2131362150);
        final int height = (int)(n * 0.5625f);
        this.image.getLayoutParams().width = n;
        this.image.getLayoutParams().height = height;
        this.progressBarBackground.getLayoutParams().width = n;
        this.progressBarBackground.getLayoutParams().height = height;
        this.progressBar.getLayoutParams().width = n;
        this.unavailable.getLayoutParams().width = n;
        this.unavailable.getLayoutParams().height = height;
    }
    
    @Override
    protected void setupPlayButton(final EpisodeDetails episodeDetails) {
        if (this.isCurrentEpisode && Coppola1Utils.isAutoplay((Context)this.this$0.getActivity())) {
            this.disablePlay();
            this.nowPlaying.setVisibility(0);
        }
        else {
            super.setupPlayButton(episodeDetails);
            this.nowPlaying.setVisibility(8);
            if (this.isCurrentEpisode) {
                this.enablePlay(episodeDetails);
                this.image.setOnClickListener((View$OnClickListener)new PressedStateHandler$DelayedOnClickListener(this.image.getPressedStateHandler(), (View$OnClickListener)new CoppolaShowDetailsFrag$CoppolaPlayerDialogEpisodesView$2(this, episodeDetails)));
            }
        }
    }
    
    @Override
    protected void updateProgressBar() {
        super.updateProgressBar();
        if (this.isCurrentEpisode && Coppola1Utils.isAutoplay((Context)this.this$0.getActivity())) {
            this.progressBar.setVisibility(8);
            this.progressBarBackground.setVisibility(0);
        }
        else if (ViewUtils.isVisible(this.progressBarBackground)) {
            this.progressBar.setVisibility(0);
        }
    }
    
    @Override
    protected void updateSynopsis(final EpisodeDetails episodeDetails) {
        super.updateSynopsis(episodeDetails);
        this.synopsis.setMaxLines(4);
        this.title.setMaxLines(1);
    }
    
    @Override
    protected void updateTitle(final EpisodeDetails episodeDetails) {
        if (this.title == null) {
            return;
        }
        this.title.setTextColor(this.getResources().getColor(2131624117));
        this.title.setText((CharSequence)(AbsEpisodeView.createTitleText(episodeDetails, (Context)this.this$0.getActivity()) + " "));
    }
}
