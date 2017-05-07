// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.List;

class FalkorValidationActivity$TestFetchLomosTask extends FalkorValidationActivity$TestRunnerTask<List<LoMo>>
{
    final /* synthetic */ FalkorValidationActivity this$0;
    
    private FalkorValidationActivity$TestFetchLomosTask(final FalkorValidationActivity this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    protected List<LoMo> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.lomos;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchLoMos(0, 19, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchLoMos(0, 19, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        this.this$0.lomos = falkorValidationActivity$ObjectNotifierCallback.lomos;
    }
}
