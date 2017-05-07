// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model;

import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.Log;
import java.io.IOException;
import java.util.Iterator;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.branches.UnsummarizedList;
import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.SummarizedList;
import com.netflix.model.leafs.ListOfListOfGenres;
import com.netflix.model.branches.FalkorEpisode;
import com.netflix.falkor.BranchMap;
import java.io.Flushable;
import com.netflix.falkor.BranchNode;

public class Root implements BranchNode, Flushable
{
    public static final String EPISODES = "episodes";
    public static final String GENRE_LIST = "genreList";
    public static final String LISTS = "lists";
    public static final String LOLOMO = "lolomo";
    public static final String LOLOMOS = "lolomos";
    public static final String MOVIES = "movies";
    public static final String SHOWS = "shows";
    public static final String SIMILARS = "similars";
    public static final String SUMMARY = "summary";
    private static final String TAG = "Root";
    private BranchMap<FalkorEpisode> episodes;
    private ListOfListOfGenres genreList;
    private BranchMap<SummarizedList<Ref, ListOfMoviesSummary>> lists;
    private Ref lolomo;
    private BranchMap<UnsummarizedList<Ref>> lolomos;
    private BranchMap<FalkorVideo> movies;
    private ModelProxy<? extends BranchNode> proxy;
    private BranchMap<FalkorVideo> shows;
    
    @Override
    public void flush() throws IOException {
        final Iterator<String> iterator = this.getKeys().iterator();
        while (iterator.hasNext()) {
            this.set(iterator.next(), null);
        }
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                Log.d("Root", "Could not find key: " + s);
                return null;
            }
            case "lolomos": {
                return this.lolomos;
            }
            case "lolomo": {
                return this.lolomo;
            }
            case "lists": {
                return this.lists;
            }
            case "genreList": {
                return this.genreList;
            }
            case "shows": {
                return this.shows;
            }
            case "movies": {
                return this.movies;
            }
            case "episodes": {
                return this.episodes;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final HashSet<String> set = new HashSet<String>();
        if (this.lolomos != null) {
            set.add("lolomos");
        }
        if (this.lolomo != null) {
            set.add("lolomo");
        }
        if (this.lists != null) {
            set.add("lists");
        }
        if (this.genreList != null) {
            set.add("genreList");
        }
        if (this.shows != null) {
            set.add("shows");
        }
        if (this.movies != null) {
            set.add("movies");
        }
        if (this.episodes != null) {
            set.add("episodes");
        }
        return set;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        switch (s) {
            default: {
                throw new IllegalArgumentException("Unknown key: " + s);
            }
            case "lolomo": {
                return this.lolomo = new Ref();
            }
            case "lolomos": {
                return this.lolomos = new BranchMap<UnsummarizedList<Ref>>(Falkor.Creator.UnsummarizedListOfRef);
            }
            case "lists": {
                return this.lists = new BranchMap<SummarizedList<Ref, ListOfMoviesSummary>>(Falkor.Creator.SummarizedListOfMovieRefs);
            }
            case "genreList": {
                return this.genreList = new ListOfListOfGenres();
            }
            case "shows": {
                return this.shows = new BranchMap<FalkorVideo>(Falkor.Creator.FalkorVideo(this.proxy));
            }
            case "movies": {
                return this.movies = new BranchMap<FalkorVideo>(Falkor.Creator.FalkorVideo(this.proxy));
            }
            case "episodes": {
                return this.episodes = new BranchMap<FalkorEpisode>(Falkor.Creator.FalkorEpisode(this.proxy));
            }
        }
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {
                Log.d("Root", "Don't know how to set key: " + s);
            }
            case "lolomos": {
                this.lolomos = (BranchMap<UnsummarizedList<Ref>>)o;
            }
            case "lolomo": {
                this.lolomo = (Ref)o;
            }
            case "lists": {
                this.lists = (BranchMap<SummarizedList<Ref, ListOfMoviesSummary>>)o;
            }
            case "genreList": {
                this.genreList = (ListOfListOfGenres)o;
            }
            case "shows": {
                this.shows = (BranchMap<FalkorVideo>)o;
            }
            case "movies": {
                this.movies = (BranchMap<FalkorVideo>)o;
            }
            case "episodes": {
                this.episodes = (BranchMap<FalkorEpisode>)o;
            }
        }
    }
    
    public void setProxy(final ModelProxy<? extends BranchNode> proxy) {
        this.proxy = proxy;
    }
    
    @Override
    public String toString() {
        return "Root [lolomo=" + this.lolomo + ", lolomos=" + this.lolomos + ", lists=" + this.lists + ", movies=" + this.movies + ", shows=" + this.shows + ", episodes=" + this.episodes + "]";
    }
}
