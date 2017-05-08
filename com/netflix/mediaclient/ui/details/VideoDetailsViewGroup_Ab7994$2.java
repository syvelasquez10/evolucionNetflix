// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.view.View;
import android.view.View$OnClickListener;

class VideoDetailsViewGroup_Ab7994$2 implements View$OnClickListener
{
    final /* synthetic */ VideoDetailsViewGroup_Ab7994 this$0;
    
    VideoDetailsViewGroup_Ab7994$2(final VideoDetailsViewGroup_Ab7994 this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final ShowDetailsActivity showDetailsActivity = (ShowDetailsActivity)this.this$0.getContext();
        if (showDetailsActivity != null) {
            final EpisodesFrag_Ab7994 episodesFrag_Ab7994 = (EpisodesFrag_Ab7994)showDetailsActivity.getSecondaryFrag();
            if (episodesFrag_Ab7994 != null) {
                switch (view.getId()) {
                    default: {}
                    case 2131820739: {
                        episodesFrag_Ab7994.showEpisodes();
                        this.this$0.setFirstTabAsSelected();
                    }
                    case 2131820741: {
                        episodesFrag_Ab7994.showSims();
                        this.this$0.setSecondTabAsSelected();
                    }
                    case 2131820974: {
                        episodesFrag_Ab7994.showTrailers();
                        this.this$0.setSecondTabAsSelected();
                    }
                }
            }
        }
    }
}
