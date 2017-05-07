// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import java.util.Locale;
import android.content.Context;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import java.util.List;

public class SearchResults implements com.netflix.mediaclient.servicemgr.SearchResults
{
    private List<SearchPerson> people;
    private final List<Object> sectionsList;
    private List<SearchSuggestion> suggestions;
    private TrackableListSummary summary;
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
    public int getListPos() {
        return 0;
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
        return this.sectionsList.get(n).size();
    }
    
    @Override
    public int getNumSections() {
        return this.sectionsList.size();
    }
    
    @Override
    public String getRequestId() {
        if (this.summary == null) {
            return null;
        }
        return this.summary.getRequestId();
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
    public CharSequence getSectionTitle(final Context context, final int n) {
        final List<SearchSuggestion> value = this.sectionsList.get(n);
        if (value == this.videos) {
            return context.getString(2131493280).toUpperCase(Locale.US);
        }
        if (value == this.people) {
            return context.getString(2131493281).toUpperCase(Locale.US);
        }
        if (value == this.suggestions) {
            return context.getString(2131493282).toUpperCase(Locale.US);
        }
        throw new IllegalStateException("Unknown section type");
    }
    
    @Override
    public int getTrackId() {
        if (this.summary == null) {
            return -1;
        }
        return this.summary.getTrackId();
    }
    
    @Override
    public boolean hasResults() {
        return this.hasVideos() || this.hasPeople() || this.hasSuggestions();
    }
    
    public static class Builder
    {
        private static final int MAX_RESULTS = 10;
        private final SearchResults results;
        
        public Builder() {
            this.results = new SearchResults(null);
        }
        
        public void addPerson(final SearchPerson searchPerson) {
            if (this.results.people == null) {
                this.results.people = (List<SearchPerson>)new ArrayList(10);
                this.results.sectionsList.add(this.results.people);
            }
            this.results.people.add(searchPerson);
        }
        
        public void addSuggestion(final SearchSuggestion searchSuggestion) {
            if (this.results.suggestions == null) {
                this.results.suggestions = (List<SearchSuggestion>)new ArrayList(10);
                this.results.sectionsList.add(this.results.suggestions);
            }
            this.results.suggestions.add(searchSuggestion);
        }
        
        public void addVideo(final SearchVideo searchVideo) {
            if (this.results.videos == null) {
                this.results.videos = (List<SearchVideo>)new ArrayList(10);
                this.results.sectionsList.add(this.results.videos);
            }
            this.results.videos.add(searchVideo);
        }
        
        public SearchResults getResults() {
            return this.results;
        }
        
        public void setSummary(final TrackableListSummary trackableListSummary) {
            this.results.summary = trackableListSummary;
        }
    }
}
