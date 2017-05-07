// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.details.KubrickShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;
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
        this.add(FalkorValidationActivity.createIgnoreKey(PostPlayContext.class, "getRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(CWVideo.class, "canBeSharedOnFacebook"));
        this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getTvCardUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getHorzDispUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(Video.class, "getSquareUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(KubrickVideo.class, "getTvCardUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(KubrickVideo.class, "getUserRating"));
        this.add(FalkorValidationActivity.createIgnoreKey(MovieDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(ShowDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(KubrickShowDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(EpisodeDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(PostPlayVideo.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(MovieDetails.class, "getSimilarsRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(ShowDetails.class, "getSimilarsRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(KubrickShowDetails.class, "getSimilarsRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(EpisodeDetails.class, "getBoxshotURL"));
        this.add(FalkorValidationActivity.createIgnoreKey(Status.class, "getError"));
        this.add(FalkorValidationActivity.createIgnoreKey(Status.class, "getRequestId"));
        this.add(FalkorValidationActivity.createIgnoreKey(SearchVideo.class, "getTvCardUrl"));
        this.add(FalkorValidationActivity.createIgnoreKey(KidsCharacterDetails.class, "getGallery"));
        this.add(FalkorValidationActivity.createIgnoreKey(KidsCharacterDetails.class, "getPlayable"));
        this.add(FalkorValidationActivity.createIgnoreKey(KidsCharacterDetails.class, "getGalleryRequestId"));
    }
}
