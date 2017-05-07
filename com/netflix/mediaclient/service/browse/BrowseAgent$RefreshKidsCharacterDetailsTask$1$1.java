// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;

class BrowseAgent$RefreshKidsCharacterDetailsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$RefreshKidsCharacterDetailsTask$1 this$2;
    final /* synthetic */ KidsCharacterDetails val$mergedDetails;
    final /* synthetic */ Status val$res;
    final /* synthetic */ Boolean val$useNewData;
    
    BrowseAgent$RefreshKidsCharacterDetailsTask$1$1(final BrowseAgent$RefreshKidsCharacterDetailsTask$1 this$2, final KidsCharacterDetails val$mergedDetails, final Boolean val$useNewData, final Status val$res) {
        this.this$2 = this$2;
        this.val$mergedDetails = val$mergedDetails;
        this.val$useNewData = val$useNewData;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onKidsCharacterDetailsFetched(this.val$mergedDetails, this.val$useNewData, this.val$res);
    }
}
