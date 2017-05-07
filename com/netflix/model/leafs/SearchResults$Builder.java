// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;

public class SearchResults$Builder
{
    private static final int MAX_RESULTS = 20;
    private final SearchResults results;
    
    public SearchResults$Builder() {
        this.results = new SearchResults(null);
    }
    
    public void addPerson(final SearchPerson searchPerson) {
        if (this.results.people == null) {
            this.results.people = (List<SearchPerson>)new ArrayList(20);
            this.results.sectionsList.add(this.results.people);
        }
        this.results.people.add(searchPerson);
    }
    
    public void addSuggestion(final SearchSuggestion searchSuggestion) {
        if (this.results.suggestions == null) {
            this.results.suggestions = (List<SearchSuggestion>)new ArrayList(20);
            this.results.sectionsList.add(this.results.suggestions);
        }
        this.results.suggestions.add(searchSuggestion);
    }
    
    public void addVideo(final SearchVideo searchVideo) {
        if (this.results.videos == null) {
            this.results.videos = (List<SearchVideo>)new ArrayList(20);
            this.results.sectionsList.add(this.results.videos);
        }
        this.results.videos.add(searchVideo);
    }
    
    public SearchResults getResults() {
        return this.results;
    }
}
