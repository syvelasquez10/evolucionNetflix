// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import java.util.List;

class FalkorValidationActivity$TestFetchGenresTask extends FalkorValidationActivity$TestRunnerTask<List<Genre>>
{
    private final String genreListId;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchGenresTask(final FalkorValidationActivity this$0, final GenreList list) {
        this.this$0 = this$0;
        super(this$0, "TestFetchGenresTask [genreList: " + list.getTitle() + "]");
        this.genreListId = list.getId();
    }
    
    @Override
    protected List<Genre> getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.genres;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchGenres(this.genreListId, 0, 19, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchGenres(this.genreListId, 0, 19, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        this.this$0.genres = falkorValidationActivity$ObjectNotifierCallback.genres;
    }
}
