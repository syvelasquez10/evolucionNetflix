// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;

class FalkorValidationActivity$TestFetchShowDetailsTask extends FalkorValidationActivity$TestRunnerTask<ShowDetails>
{
    private final String showId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchShowDetailsTask(final FalkorValidationActivity this$0, final String showId) {
        this.this$0 = this$0;
        super(this$0, "TestFetchShowDetailsTask [showId: " + showId + "]");
        this.showId = showId;
    }
    
    @Override
    protected ShowDetails getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return (ShowDetails)falkorValidationActivity$ObjectNotifierCallback.details;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchShowDetails(this.showId, null, true, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchShowDetails(this.showId, null, true, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
