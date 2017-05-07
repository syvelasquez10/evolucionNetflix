// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.android.app.Status;

class BrowseAgent$FetchSeasonDetailsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchSeasonDetailsTask$1 this$2;
    final /* synthetic */ Status val$res;
    final /* synthetic */ SeasonDetails val$seasonDetails;
    
    BrowseAgent$FetchSeasonDetailsTask$1$1(final BrowseAgent$FetchSeasonDetailsTask$1 this$2, final SeasonDetails val$seasonDetails, final Status val$res) {
        this.this$2 = this$2;
        this.val$seasonDetails = val$seasonDetails;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onSeasonDetailsFetched(this.val$seasonDetails, this.val$res);
    }
}
