// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchGenreListsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchGenreListsTask$1 this$2;
    final /* synthetic */ List val$requestedGenreLists;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchGenreListsTask$1$1(final BrowseAgent$FetchGenreListsTask$1 this$2, final List val$requestedGenreLists, final Status val$res) {
        this.this$2 = this$2;
        this.val$requestedGenreLists = val$requestedGenreLists;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onGenreListsFetched(this.val$requestedGenreLists, this.val$res);
    }
}
