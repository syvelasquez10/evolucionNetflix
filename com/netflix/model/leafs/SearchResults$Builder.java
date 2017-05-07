// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import java.util.Collection;

public class SearchResults$Builder
{
    private static final int MAX_RESULTS = 20;
    private final SearchResults results;
    
    public SearchResults$Builder() {
        this.results = new SearchResults(null);
    }
    
    public void addPeople(final Collection<SearchPerson> collection) {
        if (this.results.people == null) {
            this.results.people = (List<SearchPerson>)new ArrayList(20);
            this.results.sectionsList.add(this.results.people);
        }
        this.results.people.addAll(collection);
    }
    
    public void addSuggestions(final Collection<SearchSuggestion> collection) {
        if (this.results.suggestions == null) {
            this.results.suggestions = (List<SearchSuggestion>)new ArrayList(20);
            this.results.sectionsList.add(this.results.suggestions);
        }
        this.results.suggestions.addAll(collection);
    }
    
    public void addVideos(final Collection<SearchVideo> collection) {
        if (this.results.videos == null) {
            this.results.videos = (List<SearchVideo>)new ArrayList(20);
            this.results.sectionsList.add(this.results.videos);
        }
        this.results.videos.addAll(collection);
    }
    
    public SearchResults getResults() {
        return this.results;
    }
    
    public void setPeopleListSummary(final SearchTrackable searchTrackable) {
        this.results.peopleListSummary = searchTrackable;
    }
    
    public void setSuggestionsListSummary(final SearchTrackable searchTrackable) {
        this.results.suggestionsListSummary = searchTrackable;
    }
    
    public void setVideoListSummary(final SearchTrackable searchTrackable) {
        this.results.videoListSummary = searchTrackable;
    }
}
