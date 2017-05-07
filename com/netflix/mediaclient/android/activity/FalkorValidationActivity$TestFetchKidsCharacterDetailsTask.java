// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;

class FalkorValidationActivity$TestFetchKidsCharacterDetailsTask extends FalkorValidationActivity$TestRunnerTask<KidsCharacterDetails>
{
    private final String characterId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchKidsCharacterDetailsTask(final FalkorValidationActivity this$0, final String characterId) {
        this.this$0 = this$0;
        super(this$0, "TestFetchKidsCharacterDetailsTask [characterId: " + characterId + "]");
        this.characterId = characterId;
    }
    
    @Override
    protected KidsCharacterDetails getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.kidsCharacterDetails;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchKidsCharacterDetails(this.characterId, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchKidsCharacterDetails(this.characterId, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
