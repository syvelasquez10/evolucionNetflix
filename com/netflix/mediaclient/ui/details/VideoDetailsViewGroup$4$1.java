// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.RatingInfo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.rating.Ratings$ThumbRatingWithCLCallback;

class VideoDetailsViewGroup$4$1 extends Ratings$ThumbRatingWithCLCallback
{
    final /* synthetic */ VideoDetailsViewGroup$4 this$1;
    
    VideoDetailsViewGroup$4$1(final VideoDetailsViewGroup$4 this$1, final String s, final VideoDetails videoDetails) {
        this.this$1 = this$1;
        super(s, videoDetails);
    }
    
    protected void onThumbRatingError(final Status status) {
    }
    
    protected void onThumbRatingSet(final RatingInfo ratingInfo) {
    }
}
