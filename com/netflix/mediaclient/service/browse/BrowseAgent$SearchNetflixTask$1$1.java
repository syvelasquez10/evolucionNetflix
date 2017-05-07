// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.model.user.ProfileType;
import com.netflix.mediaclient.ui.search.SearchUtils;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$SearchNetflixTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$SearchNetflixTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ ISearchResults val$searchResults;
    
    BrowseAgent$SearchNetflixTask$1$1(final BrowseAgent$SearchNetflixTask$1 this$2, final ISearchResults val$searchResults, final Status val$res) {
        this.this$2 = this$2;
        this.val$searchResults = val$searchResults;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.callback.onSearchResultsFetched(this.val$searchResults, this.val$res);
    }
}
