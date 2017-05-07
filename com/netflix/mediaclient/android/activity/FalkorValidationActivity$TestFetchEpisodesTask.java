// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;

class FalkorValidationActivity$TestFetchEpisodesTask extends FalkorValidationActivity$TestRunnerTask<List<EpisodeDetails>>
{
    private final String id;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchEpisodesTask(final FalkorValidationActivity this$0, final String id) {
        this.this$0 = this$0;
        super(this$0, "TestFetchEpisodesTask [id: " + id + "]");
        this.id = id;
    }
    
    @Override
    protected List<EpisodeDetails> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.episodes;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchEpisodes(this.id, VideoType.SHOW, 0, 199, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchEpisodes(this.id, VideoType.SHOW, 0, 199, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
