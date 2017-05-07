// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import java.util.List;

class BrowseAgent$FetchGenresTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchGenresTask this$1;
    
    BrowseAgent$FetchGenresTask$1(final BrowseAgent$FetchGenresTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onGenresFetched(final List<Genre> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchGenresTask$1$1(this, list, status));
    }
}
