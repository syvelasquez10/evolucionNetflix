// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;

class FalkorValidationActivity$TestFetchEpisodeDetailsTask extends FalkorValidationActivity$TestRunnerTask<EpisodeDetails>
{
    private final String episodeId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchEpisodeDetailsTask(final FalkorValidationActivity this$0, final String episodeId) {
        this.this$0 = this$0;
        super(this$0, "TestFetchEpisodeDetailsTask [episodeId: " + episodeId + "]");
        this.episodeId = episodeId;
    }
    
    @Override
    protected EpisodeDetails getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.episodeDetails;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchEpisodeDetails(this.episodeId, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchEpisodeDetails(this.episodeId, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
