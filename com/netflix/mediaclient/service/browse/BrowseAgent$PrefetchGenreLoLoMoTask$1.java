// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$PrefetchGenreLoLoMoTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$PrefetchGenreLoLoMoTask this$1;
    
    BrowseAgent$PrefetchGenreLoLoMoTask$1(final BrowseAgent$PrefetchGenreLoLoMoTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onGenreLoLoMoPrefetched(final Status status) {
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$PrefetchGenreLoLoMoTask$1$1(this, status));
    }
}
