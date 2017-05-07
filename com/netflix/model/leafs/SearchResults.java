// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs;

import java.util.Locale;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;

public class SearchResults implements ISearchResults
{
    private List<SearchPerson> people;
    private SearchTrackableListSummary peopleListSummary;
    private final List<Object> sectionsList;
    private List<SearchSuggestion> suggestions;
    private SearchTrackableListSummary suggestionsListSummary;
    private SearchTrackableListSummary videoListSummary;
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
    public int getNumResults() {
        int n = 0;
        final Iterator<List> iterator = this.sectionsList.iterator();
        while (iterator.hasNext()) {
            n += iterator.next().size();
        }
        return n;
    }
    
    @Override
    public int getNumResultsForSection(final int n) {
        if (n < this.sectionsList.size()) {
            return this.sectionsList.get(n).size();
        }
        return 0;
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
    public Object getResult(int n) {
        for (final List<Object> list : this.sectionsList) {
            if (n < list.size()) {
                return list.get(n);
            }
            n -= list.size();
        }
        return null;
    }
    
    @Override
    public Object getResultsPeople(final int n) {
        if (this.people != null && n < this.people.size()) {
            return this.people.get(n);
        }
        return null;
    }
    
    @Override
    public Object getResultsSuggestions(final int n) {
        if (this.suggestions != null && n < this.suggestions.size()) {
            return this.suggestions.get(n);
        }
        return null;
    }
    
    @Override
    public Object getResultsVideos(final int n) {
        if (this.videos != null && n < this.videos.size()) {
            return this.videos.get(n);
        }
        return null;
    }
    
    @Override
    public CharSequence getSectionTitle(final Context context, final int n) {
        final List<SearchSuggestion> value = this.sectionsList.get(n);
        if (value == this.videos) {
            return context.getString(2131493291).toUpperCase(Locale.US);
        }
        if (value == this.people) {
            return context.getString(2131493292).toUpperCase(Locale.US);
        }
        if (value == this.suggestions) {
            return context.getString(2131493293).toUpperCase(Locale.US);
        }
        throw new IllegalStateException("Unknown section type");
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
    
    public static class Builder
    {
        private static final int MAX_RESULTS = 75;
        private final SearchResults results;
        
        public Builder() {
            this.results = new SearchResults(null);
        }
        
        public void addPerson(final SearchPerson searchPerson) {
            if (this.results.people == null) {
                this.results.people = (List<SearchPerson>)new ArrayList(75);
                this.results.sectionsList.add(this.results.people);
            }
            this.results.people.add(searchPerson);
        }
        
        public void addSuggestion(final SearchSuggestion searchSuggestion) {
            if (this.results.suggestions == null) {
                this.results.suggestions = (List<SearchSuggestion>)new ArrayList(75);
                this.results.sectionsList.add(this.results.suggestions);
            }
            this.results.suggestions.add(searchSuggestion);
        }
        
        public void addVideo(final SearchVideo searchVideo) {
            if (this.results.videos == null) {
                this.results.videos = (List<SearchVideo>)new ArrayList(75);
                this.results.sectionsList.add(this.results.videos);
            }
            this.results.videos.add(searchVideo);
        }
        
        public SearchResults getResults() {
            return this.results;
        }
    }
}
