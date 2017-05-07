// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;

class FalkorValidationActivity$TestFetchMovieDetailsTask extends FalkorValidationActivity$TestRunnerTask<MovieDetails>
{
    private final String movieId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchMovieDetailsTask(final FalkorValidationActivity this$0, final String movieId) {
        this.this$0 = this$0;
        super(this$0, "TestFetchMovieDetailsTask [movieId: " + movieId + "]");
        this.movieId = movieId;
    }
    
    @Override
    protected MovieDetails getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return (MovieDetails)falkorValidationActivity$ObjectNotifierCallback.details;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchMovieDetails(this.movieId, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchMovieDetails(this.movieId, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
