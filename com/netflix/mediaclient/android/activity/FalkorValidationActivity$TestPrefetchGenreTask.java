// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;

class FalkorValidationActivity$TestPrefetchGenreTask extends FalkorValidationActivity$TestRunnerTask<Void>
{
    private final GenreList genreList;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestPrefetchGenreTask(final FalkorValidationActivity this$0, final GenreList genreList) {
        this.this$0 = this$0;
        super(this$0, "TestPrefetchGenreTask [genreList: " + genreList.getTitle() + "]");
        this.genreList = genreList;
    }
    
    @Override
    protected Void getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return null;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.prefetchGenreLoLoMo(this.genreList.getId(), 0, 19, 0, 9, true, false, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.prefetchGenreLoLoMo(this.genreList.getId(), 0, 19, 0, 9, true, false, n, n2);
    }
    
    @Override
    protected boolean shouldValidate() {
        return false;
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
