// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.android.app.Status;
import java.util.List;

class BrowseAgent$FetchGenresTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchGenresTask$1 this$2;
    final /* synthetic */ List val$requestedGenres;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchGenresTask$1$1(final BrowseAgent$FetchGenresTask$1 this$2, final List val$requestedGenres, final Status val$res) {
        this.this$2 = this$2;
        this.val$requestedGenres = val$requestedGenres;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onGenresFetched(this.val$requestedGenres, this.val$res);
    }
}
