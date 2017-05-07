// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;

class FalkorValidationActivity$TestFetchSearchResultsTask extends FalkorValidationActivity$TestRunnerTask<ISearchResults>
{
    private final String query;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchSearchResultsTask(final FalkorValidationActivity this$0, final String query) {
        this.this$0 = this$0;
        super(this$0, "TestFetchSearchResultsTask [query: " + query + "]");
        this.query = query;
    }
    
    @Override
    protected ISearchResults getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.searchResults;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.searchNetflix(this.query, n, n2);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.searchNetflix(this.query, n, n2);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        this.this$0.searchResults = falkorValidationActivity$ObjectNotifierCallback.searchResults;
    }
}
