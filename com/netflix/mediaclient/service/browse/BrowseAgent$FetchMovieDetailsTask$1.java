// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;

class BrowseAgent$FetchMovieDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchMovieDetailsTask this$1;
    
    BrowseAgent$FetchMovieDetailsTask$1(final BrowseAgent$FetchMovieDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        this.this$1.updateCacheIfNecessary(movieDetails, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchMovieDetailsTask$1$1(this, movieDetails, status));
    }
}
