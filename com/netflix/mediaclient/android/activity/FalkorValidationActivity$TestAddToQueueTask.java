// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.android.app.Status;

class FalkorValidationActivity$TestAddToQueueTask extends FalkorValidationActivity$TestRunnerTask<Status>
{
    private final String falkorVideoId;
    private final VideoType falkorVideoType;
    final /* synthetic */ FalkorValidationActivity this$0;
    private final int trackId;
    private final String volleyVideoId;
    private final VideoType volleyVideoType;
    
    public FalkorValidationActivity$TestAddToQueueTask(final FalkorValidationActivity this$0, final String volleyVideoId, final VideoType volleyVideoType, final String falkorVideoId, final VideoType falkorVideoType) {
        this.this$0 = this$0;
        super(this$0, "TestAddToQueueTask [volley: " + volleyVideoId + ", falkor: " + falkorVideoId + "]");
        this.volleyVideoId = volleyVideoId;
        this.volleyVideoType = volleyVideoType;
        this.falkorVideoId = falkorVideoId;
        this.falkorVideoType = falkorVideoType;
        this.trackId = 1234567;
    }
    
    @Override
    protected Status getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.status;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.addToQueue(this.volleyVideoId, this.volleyVideoType, this.trackId, true, null, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.addToQueue(this.falkorVideoId, this.falkorVideoType, this.trackId, true, null, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
