// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import java.util.Iterator;
import com.netflix.model.branches.FalkorObject;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import java.util.Collection;

public class SearchResults$Builder
{
    private static final int MAX_RESULTS = 50;
    private final SearchResults results;
    
    public SearchResults$Builder() {
        this.results = new SearchResults(null);
    }
    
    public void addPeople(final Collection<SearchPerson> collection) {
        if (this.results.people == null) {
            this.results.people = (List<SearchPerson>)new ArrayList(50);
            this.results.sectionsList.add(this.results.people);
        }
        this.results.people.addAll(collection);
    }
    
    public void addPerson(final SearchPerson searchPerson) {
        if (this.results.people == null) {
            this.results.people = (List<SearchPerson>)new ArrayList(50);
            this.results.sectionsList.add(this.results.people);
        }
        this.results.people.add(searchPerson);
    }
    
    public void addSuggestion(final SearchSuggestion searchSuggestion) {
        if (this.results.suggestions == null) {
            this.results.suggestions = (List<SearchSuggestion>)new ArrayList(50);
            this.results.sectionsList.add(this.results.suggestions);
        }
        this.results.suggestions.add(searchSuggestion);
    }
    
    public void addSuggestions(final Collection<SearchSuggestion> collection) {
        if (this.results.suggestions == null) {
            this.results.suggestions = (List<SearchSuggestion>)new ArrayList(50);
            this.results.sectionsList.add(this.results.suggestions);
        }
        this.results.suggestions.addAll(collection);
    }
    
    public void addVideo(final SearchVideo searchVideo) {
        if (this.results.videos == null) {
            this.results.videos = (List<SearchVideo>)new ArrayList(50);
            this.results.sectionsList.add(this.results.videos);
        }
        this.results.videos.add(searchVideo);
    }
    
    public void addVideos(final Collection<SearchVideo> collection) {
        if (this.results.videos == null) {
            this.results.videos = (List<SearchVideo>)new ArrayList(50);
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
