// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchGenreVideosTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchGenreVideosTask$1 this$2;
    final /* synthetic */ List val$requestedVideos;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchGenreVideosTask$1$1(final BrowseAgent$FetchGenreVideosTask$1 this$2, final List val$requestedVideos, final Status val$res) {
        this.this$2 = this$2;
        this.val$requestedVideos = val$requestedVideos;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onVideosFetched(this.val$requestedVideos, this.val$res);
    }
}
