// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;

class BrowseAgent$FetchKidsCharacterDetailsTask$1$1 implements Runnable
{
    final /* synthetic */ BrowseAgent$FetchKidsCharacterDetailsTask$1 this$2;
    final /* synthetic */ Boolean val$dataChanged;
    final /* synthetic */ KidsCharacterDetails val$kidsCharacterDetails;
    final /* synthetic */ Status val$res;
    
    BrowseAgent$FetchKidsCharacterDetailsTask$1$1(final BrowseAgent$FetchKidsCharacterDetailsTask$1 this$2, final KidsCharacterDetails val$kidsCharacterDetails, final Boolean val$dataChanged, final Status val$res) {
        this.this$2 = this$2;
        this.val$kidsCharacterDetails = val$kidsCharacterDetails;
        this.val$dataChanged = val$dataChanged;
        this.val$res = val$res;
    }
    
    @Override
    public void run() {
        this.this$2.this$1.getCallback().onKidsCharacterDetailsFetched(this.val$kidsCharacterDetails, this.val$dataChanged, this.val$res);
    }
}
