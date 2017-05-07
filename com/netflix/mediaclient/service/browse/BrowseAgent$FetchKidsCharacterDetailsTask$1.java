// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;

class BrowseAgent$FetchKidsCharacterDetailsTask$1 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ BrowseAgent$FetchKidsCharacterDetailsTask this$1;
    
    BrowseAgent$FetchKidsCharacterDetailsTask$1(final BrowseAgent$FetchKidsCharacterDetailsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
        this.this$1.updateCacheIfNecessary(kidsCharacterDetails, status);
        this.this$1.this$0.getMainHandler().post((Runnable)new BrowseAgent$FetchKidsCharacterDetailsTask$1$1(this, kidsCharacterDetails, b, status));
    }
}
