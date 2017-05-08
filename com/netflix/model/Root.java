// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model;

import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.model.leafs.DiscoverySummary;
import com.netflix.model.branches.FalkorSuggestion;
import com.netflix.model.branches.FalkorSeason;
import com.netflix.model.branches.SearchMap;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.model.leafs.social.IrisNotificationsListSummary;
import com.netflix.model.branches.FalkorIrisNotification;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.model.leafs.LoLoMoSummary;
import com.netflix.model.leafs.ListOfMoviesSummary;
import com.netflix.model.branches.SummarizedList;
import com.netflix.model.leafs.ListOfListOfGenres;
import com.netflix.model.branches.UnsummarizedList;
import com.netflix.model.branches.FalkorExpiringContent;
import com.netflix.falkor.Ref;
import com.netflix.model.branches.FalkorEvidenceList;
import com.netflix.model.branches.FalkorEpisode;
import com.netflix.model.branches.FalkorKidsCharacter;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.falkor.BranchMap;
import java.io.Flushable;
import com.netflix.falkor.BranchNode;

public class Root implements BranchNode, Flushable
{
    private static final String TAG = "Root";
    private BranchMap<FalkorActorStill> actorVideoStills;
    private BranchMap<FalkorKidsCharacter> characters;
    private BranchMap<FalkorEpisode> episodes;
    private BranchMap<FalkorEvidenceList<Ref>> evidenceLists;
    private BranchMap<FalkorExpiringContent> expiringContent;
    private BranchMap<UnsummarizedList<Ref>> flatGenres;
    private ListOfListOfGenres genreList;
    private BranchMap<SummarizedList<Ref, ListOfMoviesSummary>> lists;
    private Ref lolomo;
    private BranchMap<SummarizedList<Ref, LoLoMoSummary>> lolomos;
    private BranchMap<FalkorVideo> movies;
    private BranchMap<Ref> nonMemberVideos;
    private BranchMap<FalkorIrisNotification> notifications;
    private SummarizedList<Ref, IrisNotificationsListSummary> notificationsList;
    private BranchMap<FalkorPerson> people;
    private ModelProxy<? extends BranchNode> proxy;
    private SearchMap search;
    private BranchMap<FalkorSeason> seasons;
    private BranchMap<FalkorVideo> shows;
    private BranchMap<FalkorSuggestion> suggestions;
    private BranchMap<Ref> topGenres;
    private BranchMap<SummarizedList<Ref, DiscoverySummary>> turboCollection;
    private BranchMap<Ref> videos;
    
    @Override
    public void flush() {
        for (final String s : this.getKeys()) {
            if (Log.isLoggable()) {
                Log.v("Root", "Flushing key: " + s);
            }
            this.remove(s);
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
            case "flatGenre": {
                return this.flatGenres;
            }
            case "evidenceLists": {
                return this.evidenceLists;
            }
            case "genreList": {
                return this.genreList;
            }
            case "topGenres": {
                return this.topGenres;
            }
            case "videos": {
                return this.videos;
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
            case "seasons": {
                return this.seasons;
            }
            case "search": {
                return this.search;
            }
            case "people": {
                return this.people;
            }
            case "videoCastStills": {
                return this.actorVideoStills;
            }
            case "suggestions": {
                return this.suggestions;
            }
            case "characters": {
                return this.characters;
            }
            case "notifications": {
                return this.notifications;
            }
            case "notificationsList": {
                return this.notificationsList;
            }
            case "nonMemberVideos": {
                return this.nonMemberVideos;
            }
            case "expiringContentNotice": {
                return this.expiringContent;
            }
            case "turboCollection": {
                return this.turboCollection;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final LinkedHashSet<String> set = new LinkedHashSet<String>();
        if (this.lolomo != null) {
            set.add("lolomo");
        }
        if (this.lolomos != null) {
            set.add("lolomos");
        }
        if (this.lists != null) {
            set.add("lists");
        }
        if (this.flatGenres != null) {
            set.add("flatGenre");
        }
        if (this.evidenceLists != null) {
            set.add("evidenceLists");
        }
        if (this.genreList != null) {
            set.add("genreList");
        }
        if (this.topGenres != null) {
            set.add("topGenres");
        }
        if (this.videos != null) {
            set.add("videos");
        }
        if (this.movies != null) {
            set.add("movies");
        }
        if (this.shows != null) {
            set.add("shows");
        }
        if (this.episodes != null) {
            set.add("episodes");
        }
        if (this.seasons != null) {
            set.add("seasons");
        }
        if (this.search != null) {
            set.add("search");
        }
        if (this.people != null) {
            set.add("people");
        }
        if (this.actorVideoStills != null) {
            set.add("videoCastStills");
        }
        if (this.suggestions != null) {
            set.add("suggestions");
        }
        if (this.characters != null) {
            set.add("characters");
        }
        if (this.notifications != null) {
            set.add("notifications");
        }
        if (this.notificationsList != null) {
            set.add("notificationsList");
        }
        if (this.nonMemberVideos != null) {
            set.add("nonMemberVideos");
        }
        if (this.expiringContent != null) {
            set.add("expiringContentNotice");
        }
        if (this.turboCollection != null) {
            set.add("turboCollection");
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
                return this.lolomos = new BranchMap<SummarizedList<Ref, LoLoMoSummary>>(Falkor$Creator.SummarizedListOfLoLoMoSummaryRefs);
            }
            case "lists": {
                return this.lists = new BranchMap<SummarizedList<Ref, ListOfMoviesSummary>>(Falkor$Creator.SummarizedListOfMovieRefs);
            }
            case "flatGenre": {
                return this.flatGenres = new BranchMap<UnsummarizedList<Ref>>(Falkor$Creator.UnsummarizedListOfRef);
            }
            case "evidenceLists": {
                return this.evidenceLists = new BranchMap<FalkorEvidenceList<Ref>>(Falkor$Creator.FalkorEvidenceList(this.proxy));
            }
            case "genreList": {
                return this.genreList = new ListOfListOfGenres();
            }
            case "topGenres": {
                return this.topGenres = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "videos": {
                return this.videos = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "shows": {
                return this.shows = new BranchMap<FalkorVideo>(Falkor$Creator.FalkorVideo(this.proxy));
            }
            case "movies": {
                return this.movies = new BranchMap<FalkorVideo>(Falkor$Creator.FalkorVideo(this.proxy));
            }
            case "episodes": {
                return this.episodes = new BranchMap<FalkorEpisode>(Falkor$Creator.FalkorEpisode(this.proxy));
            }
            case "seasons": {
                return this.seasons = new BranchMap<FalkorSeason>(Falkor$Creator.FalkorSeason(this.proxy));
            }
            case "people": {
                return this.people = new BranchMap<FalkorPerson>(Falkor$Creator.FalkorPerson(this.proxy));
            }
            case "videoCastStills": {
                return this.actorVideoStills = new BranchMap<FalkorActorStill>(Falkor$Creator.FalkorActorStill(this.proxy));
            }
            case "suggestions": {
                return this.suggestions = new BranchMap<FalkorSuggestion>(Falkor$Creator.FalkorSuggestion(this.proxy));
            }
            case "search": {
                return this.search = new SearchMap();
            }
            case "characters": {
                return this.characters = new BranchMap<FalkorKidsCharacter>(Falkor$Creator.FalkorKidsCharacter(this.proxy));
            }
            case "notifications": {
                return this.notifications = new BranchMap<FalkorIrisNotification>(Falkor$Creator.FalkorSocialNotifications(this.proxy));
            }
            case "notificationsList": {
                return this.notificationsList = Falkor$Creator.FalkorSocialNotificationsList(this.proxy);
            }
            case "nonMemberVideos": {
                return this.nonMemberVideos = new BranchMap<Ref>(Falkor$Creator.Ref);
            }
            case "expiringContentNotice": {
                return this.expiringContent = new BranchMap<FalkorExpiringContent>(Falkor$Creator.FalkorExpiringContent(this.proxy));
            }
            case "turboCollection": {
                return this.turboCollection = new BranchMap<SummarizedList<Ref, DiscoverySummary>>(Falkor$Creator.FalkorDiscoveryList(this.proxy));
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        this.set(s, null);
    }
    
    @Override
    public void set(final String s, final Object o) {
        switch (s) {
            default: {
                Log.d("Root", "Don't know how to set key: " + s);
            }
            case "lolomos": {
                this.lolomos = (BranchMap<SummarizedList<Ref, LoLoMoSummary>>)o;
            }
            case "lolomo": {
                this.lolomo = (Ref)o;
            }
            case "lists": {
                this.lists = (BranchMap<SummarizedList<Ref, ListOfMoviesSummary>>)o;
            }
            case "flatGenre": {
                this.flatGenres = (BranchMap<UnsummarizedList<Ref>>)o;
            }
            case "evidenceLists": {
                this.evidenceLists = (BranchMap<FalkorEvidenceList<Ref>>)o;
            }
            case "genreList": {
                this.genreList = (ListOfListOfGenres)o;
            }
            case "topGenres": {
                this.topGenres = (BranchMap<Ref>)o;
            }
            case "videos": {
                this.videos = (BranchMap<Ref>)o;
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
            case "seasons": {
                this.seasons = (BranchMap<FalkorSeason>)o;
            }
            case "search": {
                this.search = (SearchMap)o;
            }
            case "people": {
                this.people = (BranchMap<FalkorPerson>)o;
            }
            case "videoCastStills": {
                this.actorVideoStills = (BranchMap<FalkorActorStill>)o;
            }
            case "suggestions": {
                this.suggestions = (BranchMap<FalkorSuggestion>)o;
            }
            case "characters": {
                this.characters = (BranchMap<FalkorKidsCharacter>)o;
            }
            case "nonMemberVideos": {
                this.nonMemberVideos = (BranchMap<Ref>)o;
            }
            case "expiringContentNotice": {
                this.expiringContent = (BranchMap<FalkorExpiringContent>)o;
            }
            case "notifications": {
                this.notifications = (BranchMap<FalkorIrisNotification>)o;
            }
            case "notificationsList": {
                this.notificationsList = (SummarizedList<Ref, IrisNotificationsListSummary>)o;
            }
            case "turboCollection": {
                this.turboCollection = (BranchMap<SummarizedList<Ref, DiscoverySummary>>)o;
            }
        }
    }
    
    public void setProxy(final ModelProxy<? extends BranchNode> proxy) {
        this.proxy = proxy;
    }
    
    @Override
    public String toString() {
        return "Root{lolomo=" + this.lolomo + ", lolomos=" + this.lolomos + ", lists=" + this.lists + ", flastGenres=" + this.flatGenres + ", evidenceLists=" + this.evidenceLists + ", genreList=" + this.genreList + ", topGenres=" + this.topGenres + ", videos=" + this.videos + ", movies=" + this.movies + ", shows=" + this.shows + ", episodes=" + this.episodes + ", seasons=" + this.seasons + ", people=" + this.people + ", actorVideoStills=" + this.actorVideoStills + ", suggestions=" + this.suggestions + ", characters=" + this.characters + ", search=" + this.search + ", notifications=" + this.notifications + ", notificationsList=" + this.notificationsList + ", nonMemberVideos=" + this.nonMemberVideos + ", proxy=" + this.proxy + ", turboCollections=" + this.turboCollection + '}';
    }
}
