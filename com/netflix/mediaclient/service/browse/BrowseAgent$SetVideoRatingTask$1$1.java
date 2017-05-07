// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.UserRating;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$SetVideoRatingTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$SetVideoRatingTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ UserRating val$userRating;
    
    BrowseAgent$SetVideoRatingTask$1$1(final BrowseAgent$SetVideoRatingTask$1 this$2, final UserRating val$userRating, final Status val$res) {
        this.this$2 = this$2;
        this.val$userRating = val$userRating;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onVideoRatingSet(this.val$userRating, this.val$res);
    }
}
