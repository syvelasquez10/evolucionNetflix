// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.List;

class BrowseAgent$FetchCWVideosTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchCWVideosTask this$1;
    
    BrowseAgent$FetchCWVideosTask$1(final BrowseAgent$FetchCWVideosTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onCWVideosFetched(final List<CWVideo> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchCWVideosTask$1$1(this, list, status));
    }
}
