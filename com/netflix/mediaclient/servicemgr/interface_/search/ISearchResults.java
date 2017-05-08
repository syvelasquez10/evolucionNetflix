// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.search;

import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;

public interface ISearchResults
{
    int getNumResults();
    
    int getNumResultsPeople();
    
    int getNumResultsSuggestions();
    
    int getNumResultsVideos();
    
    int getNumSections();
    
    SearchTrackable getPeopleListTrackable();
    
    SearchPerson getResultsPeople(final int p0);
    
    SearchSuggestion getResultsSuggestions(final int p0);
    
    SearchVideo getResultsVideos(final int p0);
    
    List<SearchVideo> getResultsVideos();
    
    SearchTrackable getSuggestionsListTrackable();
    
    SearchTrackable getVideosListTrackable();
    
    boolean hasResults();
}
