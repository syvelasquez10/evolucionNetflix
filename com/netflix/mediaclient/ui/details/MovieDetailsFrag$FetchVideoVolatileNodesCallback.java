// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.text.TextUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class MovieDetailsFrag$FetchVideoVolatileNodesCallback extends LoggingManagerCallback
{
    private final boolean inQueue;
    private final int matchPercentage;
    final /* synthetic */ MovieDetailsFrag this$0;
    private final int userRating;
    private final String videoId;
    
    private MovieDetailsFrag$FetchVideoVolatileNodesCallback(final MovieDetailsFrag this$0, final String videoId, final int userRating, final int matchPercentage, final boolean inQueue) {
        this.this$0 = this$0;
        super("MovieDetailsFrag");
        this.videoId = videoId;
        this.userRating = userRating;
        this.matchPercentage = matchPercentage;
        this.inQueue = inQueue;
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        if (AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$0.getNetflixActivity())) {
            Log.v("MovieDetailsFrag", "Activity state is invalid");
        }
        else {
            if (status.isError()) {
                Log.w("MovieDetailsFrag", "Volatile data has invalid status code");
                return;
            }
            if (movieDetails == null) {
                Log.v("MovieDetailsFrag", "Volatile data no details in response");
                return;
            }
            if (!movieDetails.getId().equals(this.videoId)) {
                Log.v("MovieDetailsFrag", "Ignoring stale volatile data callback");
                return;
            }
            if (!TextUtils.equals((CharSequence)movieDetails.getId(), (CharSequence)this.videoId) || this.matchPercentage != movieDetails.getMatchPercentage() || this.inQueue != movieDetails.isInQueue()) {
                this.this$0.updateVolatileDataInView(movieDetails);
            }
        }
    }
}
