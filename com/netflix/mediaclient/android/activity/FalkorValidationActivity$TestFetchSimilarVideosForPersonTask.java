// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.service.falkor.FalkorAccess;
import com.netflix.mediaclient.service.BrowseAccess;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;

class FalkorValidationActivity$TestFetchSimilarVideosForPersonTask extends FalkorValidationActivity$TestRunnerTask<SearchVideoListProvider>
{
    private final String originalSearchTerm;
    private final SearchPerson person;
    final /* synthetic */ FalkorValidationActivity this$0;
    
    public FalkorValidationActivity$TestFetchSimilarVideosForPersonTask(final FalkorValidationActivity this$0, final SearchPerson person, final String originalSearchTerm) {
        this.this$0 = this$0;
        super(this$0, "TestFetchSimilarVideosTask [person: " + person.getName() + "]");
        this.person = person;
        this.originalSearchTerm = originalSearchTerm;
    }
    
    @Override
    protected SearchVideoListProvider getOutput(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
        return falkorValidationActivity$ObjectNotifierCallback.searchVideoList;
    }
    
    @Override
    protected void makeBrowseRequest(final BrowseAccess browseAccess, final int n, final int n2) {
        browseAccess.fetchSimilarVideosForPerson(this.person.getId(), 49, n, n2, this.originalSearchTerm);
    }
    
    @Override
    protected void makeFalkorRequest(final FalkorAccess falkorAccess, final int n, final int n2) {
        falkorAccess.fetchSimilarVideosForPerson(this.person.getId(), 49, n, n2, this.originalSearchTerm);
    }
    
    @Override
    protected void storeResults(final FalkorValidationActivity$ObjectNotifierCallback falkorValidationActivity$ObjectNotifierCallback) {
    }
}
