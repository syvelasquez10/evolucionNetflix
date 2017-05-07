// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$FetchSimilarVideosForQuerySuggestionTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchSimilarVideosForQuerySuggestionTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ SearchVideoListProvider val$videoList;
    
    BrowseAgent$FetchSimilarVideosForQuerySuggestionTask$1$1(final BrowseAgent$FetchSimilarVideosForQuerySuggestionTask$1 this$2, final SearchVideoListProvider val$videoList, final Status val$res) {
        this.this$2 = this$2;
        this.val$videoList = val$videoList;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onSimilarVideosFetched(this.val$videoList, this.val$res);
    }
}
