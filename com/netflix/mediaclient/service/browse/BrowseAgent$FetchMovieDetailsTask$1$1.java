// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;

class BrowseAgent$FetchMovieDetailsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchMovieDetailsTask$1 this$2;
    final /* synthetic */ MovieDetails val$movieDetails;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchMovieDetailsTask$1$1(final BrowseAgent$FetchMovieDetailsTask$1 this$2, final MovieDetails val$movieDetails, final Status val$res) {
        this.this$2 = this$2;
        this.val$movieDetails = val$movieDetails;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onMovieDetailsFetched(this.val$movieDetails, this.val$res);
    }
}
