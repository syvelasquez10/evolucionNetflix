// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;

class FalkorValidationActivity$TestFetchLolomoSummaryTask extends FalkorValidationActivity$TestRunnerTask<LoLoMo>
{
    private final String category;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchLolomoSummaryTask(final FalkorValidationActivity this$0, final String category) {
        this.this$0 = this$0;
        super(this$0, "TestFetchLolomoSummaryTask[category: " + category + "]");
        this.category = category;
    }
    
    @Override
    protected LoLoMo getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.summary;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchLoLoMoSummary(this.category, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchLoLoMoSummary(this.category, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
