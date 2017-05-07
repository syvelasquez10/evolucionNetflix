// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.UserRating;

class FalkorValidationActivity$TestSetVideoRatingTask extends FalkorValidationActivity$TestRunnerTask<UserRating>
{
    private static final int MAX_VIDEO_RATING = 5;
    private final int newRating;
    final /* synthetic */ FalkorValidationActivity this$0;
    private final Trackable trackable;
    private final Video video;
    
    public FalkorValidationActivity$TestSetVideoRatingTask(final FalkorValidationActivity this$0, final Video video, final Trackable trackable) {
        this.this$0 = this$0;
        super(this$0, "TestSetVideoRatingTask [video: " + video.getTitle() + "]");
        this.video = video;
        this.trackable = trackable;
        this.newRating = video.hashCode() % 5 + 1;
    }
    
    @Override
    protected UserRating getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.rating;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.setVideoRating(this.video.getId(), this.video.getType(), this.newRating, this.trackable.getTrackId(), n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.setVideoRating(this.video.getId(), this.video.getType(), this.newRating, this.trackable.getTrackId(), n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
