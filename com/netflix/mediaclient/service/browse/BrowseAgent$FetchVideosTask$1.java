// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;

class BrowseAgent$FetchVideosTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchVideosTask this$1;
    
    BrowseAgent$FetchVideosTask$1(final BrowseAgent$FetchVideosTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onVideosFetched(final List<Video> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchVideosTask$1$1(this, list, status));
    }
}