// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;

class FalkorValidationActivity$TestFetchCwVideosTask extends FalkorValidationActivity$TestRunnerTask<List<? extends Video>>
{
    final /* synthetic */ FalkorValidationActivity this$0;
    
    private FalkorValidationActivity$TestFetchCwVideosTask(final FalkorValidationActivity this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    protected List<? extends Video> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.cwVideos;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchCWVideos(0, 9, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchCWVideos(0, 9, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
