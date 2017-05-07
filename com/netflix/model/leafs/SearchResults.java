// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Iterator;
import java.util.Collection;
import com.netflix.model.branches.FalkorObject;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;

public class SearchResults implements ISearchResults
{
    private List<SearchPerson> people;
    private SearchTrackable peopleListSummary;
    private final List<Object> sectionsList;
    private List<SearchSuggestion> suggestions;
    private SearchTrackable suggestionsListSummary;
    private SearchTrackable videoListSummary;
    private List<SearchVideo> videos;
    
    private SearchResults() {
        this.sectionsList = new ArrayList<Object>(3);
    }
    
    private boolean hasPeople() {
        return this.people != null && this.people.size() > 0;
    }
    
    private boolean hasSuggestions() {
        return this.suggestions != null && this.suggestions.size() > 0;
    }
    
    private boolean hasVideos() {
        return this.videos != null && this.videos.size() > 0;
    }
    
    @Override
    public List<FalkorObject> getAllResults() {
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<FalkorObject>();
        if (this.videos != null) {
            list.addAll(this.videos);
        }
        if (this.people != null) {
            list.addAll(this.people);
        }
        if (this.suggestions != null) {
            list.addAll(this.suggestions);
        }
        return (List<FalkorObject>)list;
    }
    
    @Override
    public int getNumResults() {
        final Iterator<List> iterator = this.sectionsList.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += iterator.next().size();
        }
        return n;
    }
    
    @Override
    public int getNumResultsPeople() {
        if (this.people == null) {
            return 0;
        }
        return this.people.size();
    }
    
    @Override
    public int getNumResultsSuggestions() {
        if (this.suggestions == null) {
            return 0;
        }
        return this.suggestions.size();
    }
    
    @Override
    public int getNumResultsVideos() {
        if (this.videos == null) {
            return 0;
        }
        return this.videos.size();
    }
    
    @Override
    public int getNumSections() {
        return this.sectionsList.size();
    }
    
    @Override
    public SearchTrackable getPeopleListTrackable() {
        return this.peopleListSummary;
    }
    
    @Override
    public SearchPerson getResultsPeople(final int n) {
        if (this.people != null && n < this.people.size()) {
            return this.people.get(n);
        }
        return null;
    }
    
    @Override
    public SearchSuggestion getResultsSuggestions(final int n) {
        if (this.suggestions != null && n < this.suggestions.size()) {
            return this.suggestions.get(n);
        }
        return null;
    }
    
    @Override
    public SearchVideo getResultsVideos(final int n) {
        if (this.videos != null && n < this.videos.size()) {
            return this.videos.get(n);
        }
        return null;
    }
    
    @Override
    public SearchTrackable getSuggestionsListTrackable() {
        return this.suggestionsListSummary;
    }
    
    @Override
    public SearchTrackable getVideosListTrackable() {
        return this.videoListSummary;
    }
    
    @Override
    public boolean hasResults() {
        return this.hasVideos() || this.hasPeople() || this.hasSuggestions();
    }
}
