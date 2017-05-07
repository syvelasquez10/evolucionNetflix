// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$FetchShowDetailsTask$1$2 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchShowDetailsTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ List val$seasons;
    final /* synthetic */ ShowDetails val$showDetails;
    
    BrowseAgent$FetchShowDetailsTask$1$2(final BrowseAgent$FetchShowDetailsTask$1 this$2, final ShowDetails val$showDetails, final List val$seasons, final Status val$res) {
        this.this$2 = this$2;
        this.val$showDetails = val$showDetails;
        this.val$seasons = val$seasons;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onShowDetailsAndSeasonsFetched(this.val$showDetails, this.val$seasons, this.val$res);
    }
}
