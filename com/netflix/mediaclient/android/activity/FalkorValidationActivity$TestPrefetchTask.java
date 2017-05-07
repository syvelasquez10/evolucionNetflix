// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;

class FalkorValidationActivity$TestPrefetchTask extends FalkorValidationActivity$TestRunnerTask<Void>
{
    final /* synthetic */ FalkorValidationActivity this$0;
    
    private FalkorValidationActivity$TestPrefetchTask(final FalkorValidationActivity this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    protected Void getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return null;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.prefetchLoLoMo(0, 19, 0, 9, 0, 9, false, false, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.prefetchLoLoMo(0, 19, 0, 9, 0, 9, false, false, n, n2);
    }
    
    @Override
    protected boolean shouldValidate() {
        return false;
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
