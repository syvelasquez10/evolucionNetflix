// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.task;

import com.netflix.mediaclient.service.configuration.persistent.Config_Ab7994;
import com.netflix.mediaclient.NetflixApplication;
import java.util.Collection;
import java.util.Arrays;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import java.util.List;
import com.netflix.falkor.PQL;

public final class CmpUtils
{
    public static final PQL BB_CURR_EPISODE_PQL;
    public static final PQL BB_VIDEO_LEAF_PQL;
    public static final PQL CW_CURR_EPISODE_PQL;
    public static final PQL CW_VIDEO_LEAF_PQL;
    static final List<PQL> FETCH_EPISODES_LEAF_TYPES;
    private static final int FROM_SEASON = 0;
    private static final int MAX_KIDS_CHARACTER_GALLERY_VIDEOS = 100;
    public static final int MAX_SEARCH_RESULTS_PER_SECTION_INDEX = 19;
    private static final int MAX_VIDEO_DETAILS_SIMILARS_COUNT = 12;
    private static final int MAX_VIDEO_DETAILS_TRAILERS_COUNT = 25;
    private static final int TO_SEASON = 39;
    
    static {
        FETCH_EPISODES_LEAF_TYPES = PQL.array("summary", "detail", "bookmark", "offlineAvailable");
        CW_VIDEO_LEAF_PQL = PQL.create(PQL.array("summary", "detail", "rating", "inQueue", "bookmark", "offlineAvailable", "cleanBoxart"));
        CW_CURR_EPISODE_PQL = PQL.create("episodes", "current", PQL.array("detail", "bookmark", "offlineAvailable"));
        BB_VIDEO_LEAF_PQL = CmpUtils.CW_VIDEO_LEAF_PQL;
        BB_CURR_EPISODE_PQL = CmpUtils.CW_CURR_EPISODE_PQL;
    }
    
    public static void buildBillboardPql(final List<PQL> list, final String s, final int n, final int n2) {
        final boolean empty = StringUtils.isEmpty(s);
        PQL pql;
        if (empty) {
            pql = PQL.create("lolomo", "billboard", "videoEvidence", PQL.range(n, n2));
        }
        else {
            pql = PQL.create("lolomos", s, "billboard", "videoEvidence", PQL.range(n, n2));
        }
        PQL pql2;
        if (empty) {
            pql2 = PQL.create("lolomo", "billboard", "billboardData", PQL.range(n, n2), "billboardSummary");
        }
        else {
            pql2 = PQL.create("lolomos", s, "billboard", "billboardData", PQL.range(n, n2), "billboardSummary");
        }
        list.add(CmpUtils.BB_VIDEO_LEAF_PQL.prepend(pql));
        list.add(CmpUtils.BB_CURR_EPISODE_PQL.prepend(pql));
        list.add(pql2);
    }
    
    public static void buildCwPql(final List<PQL> list, final String s, final int n, final int n2) {
        PQL pql;
        if (StringUtils.isEmpty(s)) {
            pql = PQL.create("lolomo", "continueWatching", PQL.range(n, n2));
        }
        else {
            pql = PQL.create("lolomos", s, "continueWatching", PQL.range(n, n2));
        }
        list.add(CmpUtils.CW_VIDEO_LEAF_PQL.prepend(pql));
        list.add(CmpUtils.CW_CURR_EPISODE_PQL.prepend(pql));
    }
    
    public static PQL buildKidsCharacterVideoGalleryPql(final String s) {
        return PQL.create("characters", s, "gallery", PQL.range(100), "summary");
    }
    
    public static void buildMovieDetailsPQLs(final List<PQL> list, final List<String> list2) {
        list.add(PQL.create("movies", list2, PQL.array("summary", "detail", "rating", "inQueue", "bookmark", "offlineAvailable", "evidence", "defaultTrailer")));
        list.add(buildVideoSimsPql(true, list2));
        list.add(buildVideoSimsSummaryPql(true, list2));
    }
    
    public static PQL buildScenePql(final String s, final String s2, final String s3) {
        return PQL.create(s, s2, "scenes", s3, "summary");
    }
    
    public static void buildShowDetailsPQL(final List<PQL> list, final List<String> list2, final String s, final boolean b, final boolean b2, final boolean b3) {
        final ArrayList list3 = new ArrayList(10);
        list3.addAll(Arrays.asList("summary", "detail", "rating", "inQueue", "hasWatched", "defaultTrailer"));
        if (b2) {
            list3.addAll(Arrays.asList("kubrick", "heroImgs", "evidence"));
        }
        list.add(PQL.create("shows", list2, list3));
        list.add(PQL.create("shows", list2, "episodes", "current", PQL.array("detail", "bookmark", "offlineAvailable")));
        if (StringUtils.isNotEmpty(s)) {
            list.add(PQL.create("episodes", s, PQL.array("detail", "bookmark", "offlineAvailable")));
        }
        list.add(buildVideoSimsPql(false, list2));
        list.add(buildVideoSimsSummaryPql(false, list2));
        if (Config_Ab7994.shouldRenderTabs(NetflixApplication.getContext())) {
            list.add(buildVideoTrailersSummaryPql(false, list2));
            list.add(buildVideoTrailersPql(false, list2));
        }
        if (b) {
            list.add(PQL.create("shows", list2, "seasons", "summary"));
            list.add(getSeasonsPQL(list2));
        }
        if (b3) {
            list.add(PQL.create("shows", list2, "seasons", "current", "episodes", PQL.range(0, 39), CmpUtils.FETCH_EPISODES_LEAF_TYPES));
        }
    }
    
    public static PQL buildVideoSimsPql(final boolean b, final String s) {
        String s2;
        if (b) {
            s2 = "movies";
        }
        else {
            s2 = "shows";
        }
        return PQL.create(s2, s, "similars", PQL.range(11), "summary");
    }
    
    public static PQL buildVideoSimsPql(final boolean b, final List<String> list) {
        String s;
        if (b) {
            s = "movies";
        }
        else {
            s = "shows";
        }
        return PQL.create(s, list, "similars", PQL.range(11), "summary");
    }
    
    private static PQL buildVideoSimsSummaryPql(final boolean b, final String s) {
        String s2;
        if (b) {
            s2 = "movies";
        }
        else {
            s2 = "shows";
        }
        return PQL.create(s2, s, "similars", "summary");
    }
    
    public static PQL buildVideoSimsSummaryPql(final boolean b, final List<String> list) {
        String s;
        if (b) {
            s = "movies";
        }
        else {
            s = "shows";
        }
        return PQL.create(s, list, "similars", "summary");
    }
    
    public static PQL buildVideoTrailersPql(final boolean b, final String s) {
        String s2;
        if (b) {
            s2 = "movies";
        }
        else {
            s2 = "shows";
        }
        return PQL.create(s2, s, "trailers", PQL.range(24), PQL.array("summary", "trickplayBaseUrl", "intrUrl"));
    }
    
    public static PQL buildVideoTrailersPql(final boolean b, final List<String> list) {
        String s;
        if (b) {
            s = "movies";
        }
        else {
            s = "shows";
        }
        return PQL.create(s, list, "trailers", PQL.range(24), PQL.array("summary", "trickplayBaseUrl", "intrUrl"));
    }
    
    public static PQL buildVideoTrailersSummaryPql(final boolean b, final String s) {
        String s2;
        if (b) {
            s2 = "movies";
        }
        else {
            s2 = "shows";
        }
        return PQL.create(s2, s, "trailers", "summary");
    }
    
    public static PQL buildVideoTrailersSummaryPql(final boolean b, final List<String> list) {
        String s;
        if (b) {
            s = "movies";
        }
        else {
            s = "shows";
        }
        return PQL.create(s, list, "trailers", "summary");
    }
    
    public static void buildVolatileVideoPQLs(final List<PQL> list, final String s, final boolean b) {
        if (b) {
            list.add(PQL.create("movies", s, PQL.array("inQueue", "rating", "bookmark")));
            return;
        }
        list.add(PQL.create("shows", s, PQL.array("inQueue", "rating")));
        list.add(PQL.create("shows", s, "seasons", "summary"));
        list.add(PQL.create("shows", s, "episodes", "current", PQL.array("detail", "bookmark")));
    }
    
    public static PQL getSeasonsPQL(final List<String> list) {
        return PQL.create("shows", list, "seasons", PQL.range(0, 39), "detail");
    }
}
