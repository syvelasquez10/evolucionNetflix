// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.branches.FalkorSuggestion;
import com.netflix.model.branches.FalkorSeason;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.model.branches.FalkorEpisode;
import com.netflix.falkor.BranchNode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.UnsummarizedList;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.model.branches.SummarizedList;
import com.netflix.model.leafs.SearchTrackableListSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Func;

public class Falkor$Creator
{
    public static final Func<ListOfMoviesSummary> ListOfMoviesSummary;
    public static final Func<Ref> Ref;
    public static Func<SearchTrackableListSummary> SearchTrackableListSummary;
    public static final Func<SummarizedList<Ref, ListOfMoviesSummary>> SummarizedListOfMovieRefs;
    public static final Func<SummarizedList<Ref, SearchTrackableListSummary>> SummarizedListOfSearchResults;
    public static final Func<TrackableListSummary> TrackableListSummary;
    public static final Func<UnsummarizedList<Ref>> UnsummarizedListOfRef;
    
    static {
        Ref = new Falkor$Creator$1();
        TrackableListSummary = new Falkor$Creator$2();
        UnsummarizedListOfRef = new Falkor$Creator$3();
        ListOfMoviesSummary = new Falkor$Creator$4();
        SummarizedListOfMovieRefs = new Falkor$Creator$5();
        SummarizedListOfSearchResults = new Falkor$Creator$6();
        Falkor$Creator.SearchTrackableListSummary = new Falkor$Creator$7();
    }
    
    public static Func<FalkorEpisode> FalkorEpisode(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$9(modelProxy);
    }
    
    public static Func<FalkorPerson> FalkorPerson(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$11(modelProxy);
    }
    
    public static Func<FalkorSeason> FalkorSeason(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$10(modelProxy);
    }
    
    public static Func<FalkorSuggestion> FalkorSuggestion(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$12(modelProxy);
    }
    
    public static Func<FalkorVideo> FalkorVideo(final ModelProxy<? extends BranchNode> modelProxy) {
        return new Falkor$Creator$8(modelProxy);
    }
}
