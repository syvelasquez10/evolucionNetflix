// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.search;

import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;

public interface ISearchResults extends Trackable
{
    int getNumResults();
    
    int getNumResultsForSection(final int p0);
    
    int getNumResultsPeople();
    
    int getNumResultsSuggestions();
    
    int getNumResultsVideos();
    
    int getNumSections();
    
    Object getResult(final int p0);
    
    Object getResultsPeople(final int p0);
    
    Object getResultsSuggestions(final int p0);
    
    Object getResultsVideos(final int p0);
    
    CharSequence getSectionTitle(final Context p0, final int p1);
    
    boolean hasResults();
}
