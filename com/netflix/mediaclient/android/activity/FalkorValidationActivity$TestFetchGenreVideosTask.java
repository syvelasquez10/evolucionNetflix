// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;

class FalkorValidationActivity$TestFetchGenreVideosTask extends FalkorValidationActivity$TestRunnerTask<List<? extends Video>>
{
    private final Genre genre;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchGenreVideosTask(final FalkorValidationActivity this$0, final Genre genre) {
        this.this$0 = this$0;
        super(this$0, "TestFetchGenreVideosTask [genre: " + genre.getTitle() + "]");
        this.genre = genre;
    }
    
    @Override
    protected List<? extends Video> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.videos;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchGenreVideos(this.genre, 0, 9, true, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchGenreVideos(this.genre, 0, 9, true, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        final String id = this.genre.getId();
        Log.v("FalkorValidationActivity", "Storing videos list for genre key: " + id + ", num videos: " + falkorValidationActivity$ObjectNotifierCallback.videos.size());
        this.this$0.videosMap.put(id, falkorValidationActivity$ObjectNotifierCallback.videos);
    }
}
