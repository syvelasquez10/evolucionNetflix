// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideosProvider;

class FalkorValidationActivity$TestFetchPostPlayVideosTask extends FalkorValidationActivity$TestRunnerTask<PostPlayVideosProvider>
{
    final /* synthetic */ FalkorValidationActivity this$0;
    private final VideoType type;
    private final String videoId;
    
    public FalkorValidationActivity$TestFetchPostPlayVideosTask(final FalkorValidationActivity this$0, final String videoId, final VideoType type) {
        this.this$0 = this$0;
        super(this$0, "FetchPostPlayVideosTask [videoId: " + videoId + "]");
        this.videoId = videoId;
        this.type = type;
    }
    
    @Override
    protected PostPlayVideosProvider getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.postPlayVideos;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchPostPlayVideos(this.videoId, this.type, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchPostPlayVideos(this.videoId, this.type, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
