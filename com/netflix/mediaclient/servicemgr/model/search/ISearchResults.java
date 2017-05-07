// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.search;

import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.model.branches.FalkorObject;
import java.util.List;

public interface ISearchResults
{
    List<FalkorObject> getAllResults();
    
    int getNumResults();
    
    int getNumResultsPeople();
    
    int getNumResultsSuggestions();
    
    int getNumResultsVideos();
    
    int getNumSections();
    
    SearchTrackable getPeopleListTrackable();
    
    SearchPerson getResultsPeople(final int p0);
    
    SearchSuggestion getResultsSuggestions(final int p0);
    
    SearchVideo getResultsVideos(final int p0);
    
    SearchTrackable getSuggestionsListTrackable();
    
    SearchTrackable getVideosListTrackable();
    
    boolean hasResults();
}
