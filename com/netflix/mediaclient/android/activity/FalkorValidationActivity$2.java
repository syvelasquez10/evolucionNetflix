// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.HashSet;

final class FalkorValidationActivity$2 extends HashSet<String>
{
    FalkorValidationActivity$2() {
        this.add(FalkorValidationActivity.createIgnoreKey(LoMo.class, "getId"));
        this.add(FalkorValidationActivity.createIgnoreKey(LoMo.class, "getRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(Genre.class, "getId"));
        this.add(FalkorValidationActivity.createIgnoreKey(Genre.class, "getRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(SearchTrackable.class, "getRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(SearchTrackable.class, "getReferenceId"));
        this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getTvCardUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getHorzDispUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getSquareUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(MovieDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(ShowDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(EpisodeDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(MovieDetails.class, "getSimilarsRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(ShowDetails.class, "getSimilarsRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(EpisodeDetails.class, "getBoxshotURL"));
    }
}
