// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;

class FalkorValidationActivity$TestFetchVideosTask extends FalkorValidationActivity$TestRunnerTask<List<? extends Video>>
{
    private final LoMo lomo;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchVideosTask(final FalkorValidationActivity this$0, final LoMo lomo) {
        this.this$0 = this$0;
        super(this$0, "TestFetchVideosTask [lomo: " + lomo.getTitle() + "]");
        this.lomo = lomo;
    }
    
    @Override
    protected List<? extends Video> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        if (this.lomo.isBillboard()) {
            return falkorValidationActivity$ObjectNotifierCallback.bbVideos;
        }
        return falkorValidationActivity$ObjectNotifierCallback.videos;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchVideos(this.lomo, 0, 9, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchVideos(this.lomo, 0, 9, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        final String id = this.lomo.getId();
        int n;
        if (this.lomo.isBillboard()) {
            n = falkorValidationActivity$ObjectNotifierCallback.bbVideos.size();
            this.this$0.videosMap.put(id, falkorValidationActivity$ObjectNotifierCallback.bbVideos);
        }
        else {
            n = falkorValidationActivity$ObjectNotifierCallback.videos.size();
            this.this$0.videosMap.put(id, falkorValidationActivity$ObjectNotifierCallback.videos);
        }
        Log.v("FalkorValidationActivity", "Storing videos list for lomo key: " + id + ", num items: " + n);
    }
}
