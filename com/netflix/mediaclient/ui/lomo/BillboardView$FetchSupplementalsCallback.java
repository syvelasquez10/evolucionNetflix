// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import android.widget.Button;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class BillboardView$FetchSupplementalsCallback extends LoggingManagerCallback
{
    private String bookmarkPosition;
    private boolean continueWatching;
    private final Button ctaButton;
    final /* synthetic */ BillboardView this$0;
    
    public BillboardView$FetchSupplementalsCallback(final BillboardView this$0, final Button ctaButton, final String bookmarkPosition) {
        this.this$0 = this$0;
        super("BillboardView");
        this.continueWatching = false;
        this.bookmarkPosition = bookmarkPosition;
        this.ctaButton = ctaButton;
    }
    
    public BillboardView$FetchSupplementalsCallback(final BillboardView this$0, final Button ctaButton, final String bookmarkPosition, final boolean continueWatching) {
        this.this$0 = this$0;
        super("BillboardView");
        this.continueWatching = false;
        this.continueWatching = continueWatching;
        this.bookmarkPosition = bookmarkPosition;
        this.ctaButton = ctaButton;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        super.onEpisodeDetailsFetched(episodeDetails, status);
        if (episodeDetails != null) {
            this.this$0.addPlayableToCTA(episodeDetails, this.ctaButton, this.bookmarkPosition);
        }
    }
    
    @Override
    public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
        super.onEpisodesFetched(list, status);
        if (!list.isEmpty()) {
            this.this$0.addPlayableToCTA(list.get(0).getPlayable(), this.ctaButton, this.bookmarkPosition);
        }
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        if (movieDetails != null) {
            this.this$0.addPlayableToCTA(movieDetails, this.ctaButton, this.bookmarkPosition);
        }
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        if (showDetails != null) {
            if (!this.continueWatching) {
                this.this$0.addPlayableToCTA(showDetails, this.ctaButton, this.bookmarkPosition);
                return;
            }
            ((NetflixActivity)this.this$0.getContext()).getServiceManager().getBrowse().fetchEpisodeDetails(showDetails.getCurrentEpisodeId(), null, new BillboardView$FetchSupplementalsCallback(this.this$0, this.ctaButton, this.bookmarkPosition));
        }
    }
}
