// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.branches.FalkorEpisode;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.UnsummarizedList;
import com.netflix.model.leafs.TrackableListSummary;
import com.netflix.model.branches.SummarizedList;
import com.netflix.falkor.Ref;
import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Func;
import com.netflix.falkor.BranchNode;

public class Falkor
{
    public static final String BOOKMARK = "bookmark";
    public static final String BOOKMARK_STILL = "bookmarkStill";
    public static final String DETAIL = "detail";
    public static final String IN_QUEUE = "inQueue";
    public static final BranchNode NULL_ROOT;
    public static final String RATING = "rating";
    public static final String SEARCH_TITLE = "searchTitle";
    public static final String SOCIAL_EVIDENCE = "socialEvidence";
    public static final String SUMMARY = "summary";
    
    static {
        NULL_ROOT = null;
    }
    
    public static class Creator
    {
        public static final Func<ListOfMoviesSummary> ListOfMoviesSummary;
        public static final Func<Ref> Ref;
        public static final Func<SummarizedList<Ref, ListOfMoviesSummary>> SummarizedListOfMovieRefs;
        public static final Func<TrackableListSummary> TrackableListSummary;
        public static final Func<UnsummarizedList<Ref>> UnsummarizedListOfRef;
        
        static {
            Ref = new Func<Ref>() {
                @Override
                public Ref call() {
                    return new Ref();
                }
            };
            TrackableListSummary = new Func<TrackableListSummary>() {
                @Override
                public TrackableListSummary call() {
                    return new TrackableListSummary();
                }
            };
            UnsummarizedListOfRef = new Func<UnsummarizedList<Ref>>() {
                @Override
                public UnsummarizedList<Ref> call() {
                    return new UnsummarizedList<Ref>(Creator.Ref);
                }
            };
            ListOfMoviesSummary = new Func<ListOfMoviesSummary>() {
                @Override
                public ListOfMoviesSummary call() {
                    return new ListOfMoviesSummary();
                }
            };
            SummarizedListOfMovieRefs = new Func<SummarizedList<Ref, ListOfMoviesSummary>>() {
                @Override
                public SummarizedList<Ref, ListOfMoviesSummary> call() {
                    return new SummarizedList<Ref, ListOfMoviesSummary>(Creator.Ref, Creator.ListOfMoviesSummary);
                }
            };
        }
        
        public static Func<FalkorEpisode> FalkorEpisode(final ModelProxy<? extends BranchNode> modelProxy) {
            return new Func<FalkorEpisode>() {
                @Override
                public FalkorEpisode call() {
                    return new FalkorEpisode(modelProxy);
                }
            };
        }
        
        public static Func<FalkorVideo> FalkorVideo(final ModelProxy<? extends BranchNode> modelProxy) {
            return new Func<FalkorVideo>() {
                @Override
                public FalkorVideo call() {
                    return new FalkorVideo(modelProxy);
                }
            };
        }
    }
}
