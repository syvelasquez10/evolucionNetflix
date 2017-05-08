// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.model.leafs.SearchTrackableListSummary;
import com.netflix.falkor.CachedModelProxy$GetResult;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.model.leafs.SearchResults$Builder;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.search.SearchUtils;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.user.ProfileType;
import com.netflix.falkor.PQL;
import java.util.List;

public class FetchSearchResultsTask extends CmpTask
{
    private static final int MAX_SEARCH_RESULTS_PER_SECTION_INDEX = 19;
    private static final List<PQL> SEARCH_LEAF_TYPES;
    private static final List<PQL> SEARCH_RESULT_TYPES;
    private final ProfileType profileType;
    private final String query;
    
    static {
        SEARCH_RESULT_TYPES = PQL.array("videos", "people", "suggestions");
        SEARCH_LEAF_TYPES = PQL.array("summary", "searchTitle");
    }
    
    public FetchSearchResultsTask(final CachedModelProxy cachedModelProxy, final String s, final BrowseAgentCallback browseAgentCallback) {
        super(cachedModelProxy, browseAgentCallback);
        this.query = SearchUtils.sanitizeQuery(s);
        final UserProfile currentProfile = this.modelProxy.getServiceProvider().getService().getCurrentProfile();
        ProfileType profileType;
        if (currentProfile == null) {
            profileType = ProfileType.STANDARD;
        }
        else {
            profileType = currentProfile.getProfileType();
        }
        this.profileType = profileType;
    }
    
    @Override
    protected void buildPqlList(final List<PQL> list) {
        list.add(PQL.create("search", FetchSearchResultsTask.SEARCH_RESULT_TYPES, this.query, this.profileType, PQL.range(19), FetchSearchResultsTask.SEARCH_LEAF_TYPES));
        list.add(PQL.create("search", FetchSearchResultsTask.SEARCH_RESULT_TYPES, this.query, this.profileType, "summary"));
    }
    
    @Override
    protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
        browseAgentCallback.onSearchResultsFetched((ISearchResults)new SearchResults$Builder().getResults(), status);
    }
    
    @Override
    protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        final SearchResults$Builder searchResults$Builder = new SearchResults$Builder();
        searchResults$Builder.setVideoListSummary((SearchTrackable)this.modelProxy.getValue(PQL.create("search", "videos", this.query, this.profileType, "summary")));
        searchResults$Builder.setPeopleListSummary((SearchTrackable)this.modelProxy.getValue(PQL.create("search", "people", this.query, this.profileType, "summary")));
        searchResults$Builder.setSuggestionsListSummary((SearchTrackable)this.modelProxy.getValue(PQL.create("search", "suggestions", this.query, this.profileType, "summary")));
        final List itemsAsList = this.modelProxy.getItemsAsList(PQL.create("search", "videos", this.query, this.profileType, PQL.range(19), "searchTitle"));
        if (!itemsAsList.isEmpty()) {
            searchResults$Builder.addVideos((Collection)itemsAsList);
        }
        final List itemsAsList2 = this.modelProxy.getItemsAsList(PQL.create("search", "people", this.query, this.profileType, PQL.range(19), "searchTitle"));
        if (!itemsAsList2.isEmpty()) {
            searchResults$Builder.addPeople((Collection)itemsAsList2);
        }
        final List itemsAsList3 = this.modelProxy.getItemsAsList(PQL.create("search", "suggestions", this.query, this.profileType, PQL.range(19), "searchTitle"));
        if (!itemsAsList3.isEmpty()) {
            searchResults$Builder.addSuggestions((Collection)itemsAsList3);
        }
        browseAgentCallback.onSearchResultsFetched((ISearchResults)searchResults$Builder.getResults(), CommonStatus.OK);
    }
}
