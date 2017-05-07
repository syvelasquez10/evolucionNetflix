// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;

class BrowseAgent$FetchSimilarVideosForPersonTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchSimilarVideosForPersonTask this$1;
    
    BrowseAgent$FetchSimilarVideosForPersonTask$1(final BrowseAgent$FetchSimilarVideosForPersonTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSimilarVideosFetched(final SearchVideoListProvider searchVideoListProvider, final Status status) {
        this.this$1.updateCacheIfNecessary(searchVideoListProvider, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchSimilarVideosForPersonTask$1$1(this, searchVideoListProvider, status));
    }
}
