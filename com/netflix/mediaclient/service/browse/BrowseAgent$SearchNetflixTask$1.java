// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;

class BrowseAgent$SearchNetflixTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$SearchNetflixTask this$1;
    
    BrowseAgent$SearchNetflixTask$1(final BrowseAgent$SearchNetflixTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
        this.this$1.updateCacheIfNecessary(searchResults, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$SearchNetflixTask$1$1(this, searchResults, status));
    }
}
