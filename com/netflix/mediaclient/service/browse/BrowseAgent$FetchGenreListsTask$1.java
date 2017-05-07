// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import java.util.List;

class BrowseAgent$FetchGenreListsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchGenreListsTask this$1;
    
    BrowseAgent$FetchGenreListsTask$1(final BrowseAgent$FetchGenreListsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onGenreListsFetched(final List<GenreList> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchGenreListsTask$1$1(this, list, status));
    }
}
