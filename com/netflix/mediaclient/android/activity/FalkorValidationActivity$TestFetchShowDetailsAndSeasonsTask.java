// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import android.support.v4.util.Pair;

class FalkorValidationActivity$TestFetchShowDetailsAndSeasonsTask extends FalkorValidationActivity$TestRunnerTask<Pair<ShowDetails, List<SeasonDetails>>>
{
    private final String showId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchShowDetailsAndSeasonsTask(final FalkorValidationActivity this$0, final String showId) {
        this.this$0 = this$0;
        super(this$0, "TestFetchShowDetailsAndSeasonsTask [showId: " + showId + "]");
        this.showId = showId;
    }
    
    @Override
    protected Pair<ShowDetails, List<SeasonDetails>> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.showAndSeasons;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchShowDetailsAndSeasons(this.showId, null, true, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchShowDetailsAndSeasons(this.showId, null, true, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
