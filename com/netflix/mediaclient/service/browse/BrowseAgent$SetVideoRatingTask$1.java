// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.UserRating;

class BrowseAgent$SetVideoRatingTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$SetVideoRatingTask this$1;
    
    BrowseAgent$SetVideoRatingTask$1(final BrowseAgent$SetVideoRatingTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onVideoRatingSet(final UserRating userRating, final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$SetVideoRatingTask$1$1(this, userRating, status));
    }
}
