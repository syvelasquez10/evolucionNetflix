// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;

class FalkorValidationActivity$TestFetchSeasonsTask extends FalkorValidationActivity$TestRunnerTask<List<SeasonDetails>>
{
    private final String showId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchSeasonsTask(final FalkorValidationActivity this$0, final String showId) {
        this.this$0 = this$0;
        super(this$0, "TestFetchSeasonsTask [showId: " + showId + "]");
        this.showId = showId;
    }
    
    @Override
    protected List<SeasonDetails> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.seasons;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchSeasons(this.showId, 0, 19, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchSeasons(this.showId, 0, 19, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
