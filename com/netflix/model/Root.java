// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model;

import com.netflix.falkor.Func;
import java.util.HashSet;
import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.branches.Show;
import com.netflix.mediaclient.service.webclient.model.branches.Movie;
import com.netflix.falkor.Ref;
import com.netflix.falkor.BranchMap;
import com.netflix.falkor.BranchNode;

public class Root implements BranchNode
{
    private static final String TAG = "Root";
    BranchMap<SummarizedList<Ref>> lists;
    Ref lolomo;
    BranchMap<SummarizedList<Ref>> lolomos;
    BranchMap<Movie> movies;
    BranchMap<Show> shows;
    
    @Override
    public Object get(final String s) {
        if ("lolomos".equals(s)) {
            return this.lolomos;
        }
        if ("lolomo".equals(s)) {
            return this.lolomo;
        }
        if ("lists".equals(s)) {
            return this.lists;
        }
        if ("shows".equals(s)) {
            return this.shows;
        }
        if ("movies".equals(s)) {
            return this.movies;
        }
        Log.d("Root", "Could not find key: " + s);
        return null;
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
        if (this.shows != null) {
            set.add("shows");
        }
        if (this.movies != null) {
            set.add("movies");
        }
        return set;
    }
    
    public BranchMap<SummarizedList<Ref>> getList() {
        return this.lists;
    }
    
    public BranchMap<SummarizedList<Ref>> getLolomos() {
        return this.lolomos;
    }
    
    public BranchMap<Movie> getMovies() {
        return this.movies;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        if ("lolomos".equals(s)) {
            return this.lolomos = new BranchMap<SummarizedList<Ref>>(new Func<SummarizedList<Ref>>() {
                @Override
                public SummarizedList<Ref> call() {
                    return new SummarizedList<Ref>(new Func<Ref>() {
                        @Override
                        public Ref call() {
                            return new Ref();
                        }
                    });
                }
            });
        }
        if ("lolomo".equals(s)) {
            return this.lolomo = new Ref();
        }
        if ("lists".equals(s)) {
            return this.lists = new BranchMap<SummarizedList<Ref>>(new Func<SummarizedList<Ref>>() {
                @Override
                public SummarizedList<Ref> call() {
                    return new SummarizedList<Ref>(new Func<Ref>() {
                        @Override
                        public Ref call() {
                            return new Ref();
                        }
                    });
                }
            });
        }
        if ("shows".equals(s)) {
            return this.shows = new BranchMap<Show>(new Func<Show>() {
                @Override
                public Show call() {
                    return new Show();
                }
            });
        }
        if ("movies".equals(s)) {
            return this.movies = new BranchMap<Movie>(new Func<Movie>() {
                @Override
                public Movie call() {
                    return new Movie();
                }
            });
        }
        throw new IllegalArgumentException("Unknown key: " + s);
    }
    
    public BranchMap<Show> getShows() {
        return this.shows;
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("lolomos".equals(s)) {
            this.lolomos = (BranchMap<SummarizedList<Ref>>)o;
        }
        else {
            if ("lolomo".equals(s)) {
                this.lolomo = (Ref)o;
                return;
            }
            if ("lists".equals(s)) {
                this.lists = (BranchMap<SummarizedList<Ref>>)o;
                return;
            }
            if ("shows".equals(s)) {
                this.shows = (BranchMap<Show>)o;
                return;
            }
            if ("movies".equals(s)) {
                this.movies = (BranchMap<Movie>)o;
            }
        }
    }
    
    @Override
    public String toString() {
        return "Root [lolomos=" + this.lolomos + ", lists=" + this.lists + ", shows=" + this.shows + ", movies=" + this.movies + "]";
    }
}
