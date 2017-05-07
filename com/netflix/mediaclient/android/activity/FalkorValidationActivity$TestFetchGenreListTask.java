// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import java.util.List;

class FalkorValidationActivity$TestFetchGenreListTask extends FalkorValidationActivity$TestRunnerTask<List<GenreList>>
{
    final /* synthetic */ FalkorValidationActivity this$0;
    
    private FalkorValidationActivity$TestFetchGenreListTask(final FalkorValidationActivity this$0) {
        this.this$0 = this$0;
        super(this$0);
    }
    
    @Override
    protected List<GenreList> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.listofGenres;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchGenreLists(n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchGenreLists(n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        this.this$0.listOfGenres = falkorValidationActivity$ObjectNotifierCallback.listofGenres;
    }
}
