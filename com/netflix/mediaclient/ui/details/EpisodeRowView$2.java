// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.Log;
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
        if (!(this.this$0.getContext() instanceof EpisodeRowView$EpisodeRowListenerProvider)) {
            Log.w("EpisodeRowView", "Context is not an EpisodeRowListenerProvider, context: " + this.this$0.getContext());
            return;
        }
        final EpisodeRowView$EpisodeRowListener episodeRowListener = ((EpisodeRowView$EpisodeRowListenerProvider)this.this$0.getContext()).getEpisodeRowListener();
        if (episodeRowListener != null) {
            PlayContext playContext = PlayContext.EMPTY_CONTEXT;
            if (this.this$0.getContext() instanceof PlayContextProvider) {
                playContext = ((PlayContextProvider)this.this$0.getContext()).getPlayContext();
            }
            episodeRowListener.onEpisodeSelectedForPlayback(this.val$episode, playContext);
            return;
        }
        Log.w("EpisodeRowView", "No EpisodeRowListener provided: " + this.this$0.getContext());
    }
}
