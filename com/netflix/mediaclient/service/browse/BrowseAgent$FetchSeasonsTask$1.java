// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;

class BrowseAgent$FetchSeasonsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchSeasonsTask this$1;
    
    BrowseAgent$FetchSeasonsTask$1(final BrowseAgent$FetchSeasonsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
        this.this$1.updateCacheIfNecessary(list, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchSeasonsTask$1$1(this, status, list));
    }
}
