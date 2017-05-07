// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.cache;

import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.Iterator;
import java.lang.ref.WeakReference;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import java.util.ArrayList;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import java.util.List;

public class BrowseWebClientCache
{
    public static final String BILLBOARD_KEY = "billboard";
    public static final String CACHE_KEY_CW_LIST_ID = "continueWatching_list_id";
    public static final String CACHE_KEY_CW_LOMO_INDEX = "cw_lomo_index";
    public static final String CACHE_KEY_CW_LOMO_SUMMARY = "cw_lomo_summary";
    public static final String CACHE_KEY_GENRE_LOLOMO_ID = "genre_lolomo_id";
    public static final String CACHE_KEY_IQ_LIST_ID = "queue_list_id";
    public static final String CACHE_KEY_IQ_LOMO_INDEX = "iq_lomo_index";
    public static final String CACHE_KEY_IQ_LOMO_SUMMARY = "iq_lomo_summary";
    public static final String CACHE_KEY_LOLOMO_ID = "lolomo_id";
    public static final String CHARACTERS_KEY = "characters";
    public static final String CONTINUE_WATCHING_KEY = "continueWatching";
    public static final String GENRE_LIST_KEY = "genreList";
    public static final String INSTANT_QUEUE_KEY = "queue";
    public static final String LOLOMO_KEY = "lolomo";
    private static final int MAX_NUM_EPISODES_ITEMS;
    private static final int MAX_NUM_SEASONS_ITEMS;
    private static final int MAX_NUM_SOFTCACHE_ITEMS;
    public static final String SEPERATOR = "_";
    private static final String TAG = "nf_browse_cache";
    private List<String> cwKeysCache;
    private HardCache hardCache;
    private List<String> iqKeysCache;
    private SoftCache softCache;
    private SoftCache weakEpisodesCache;
    private SoftCache weakSeasonsCache;
    
    static {
        final int n = 300;
        int max_NUM_SOFTCACHE_ITEMS;
        if (ConfigurationAgent.shouldUseLowMemConfig()) {
            max_NUM_SOFTCACHE_ITEMS = 150;
        }
        else {
            max_NUM_SOFTCACHE_ITEMS = 300;
        }
        MAX_NUM_SOFTCACHE_ITEMS = max_NUM_SOFTCACHE_ITEMS;
        int max_NUM_SEASONS_ITEMS;
        if (ConfigurationAgent.shouldUseLowMemConfig()) {
            max_NUM_SEASONS_ITEMS = 25;
        }
        else {
            max_NUM_SEASONS_ITEMS = 50;
        }
        MAX_NUM_SEASONS_ITEMS = max_NUM_SEASONS_ITEMS;
        int max_NUM_EPISODES_ITEMS = n;
        if (ConfigurationAgent.shouldUseLowMemConfig()) {
            max_NUM_EPISODES_ITEMS = 75;
        }
        MAX_NUM_EPISODES_ITEMS = max_NUM_EPISODES_ITEMS;
    }
    
    public BrowseWebClientCache() {
        this.softCache = new SoftBrowseCache(BrowseWebClientCache.MAX_NUM_SOFTCACHE_ITEMS);
        this.hardCache = new HardBrowseCache();
        this.weakSeasonsCache = new SoftBrowseCache(BrowseWebClientCache.MAX_NUM_SEASONS_ITEMS);
        this.weakEpisodesCache = new SoftBrowseCache(BrowseWebClientCache.MAX_NUM_EPISODES_ITEMS);
        this.cwKeysCache = new ArrayList<String>();
        this.iqKeysCache = new ArrayList<String>();
    }
    
    public static String buildBrowseCacheKey(final String s, final String s2, final String s3, final String s4) {
        return s + "_" + s2 + "_" + s3 + "_" + s4;
    }
    
    public static String buildBrowseGenreLoLoMoCacheKey(final String s) {
        return buildBrowseCacheKey("genre_lolomo_id", s, "0", "0");
    }
    
    public static String buildEpisodeDetailsCacheKey(final String s) {
        return buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_EPISODE_DETAILS, s, "0", "0");
    }
    
    public static String buildSeasonDetailsCacheKey(final String s) {
        return buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SEASON_DETAILS, s, "0", "0");
    }
    
    public static Object getIQVideosFromBrowseCache(final int n, final int n2, final BrowseWebClientCache browseWebClientCache) {
        return browseWebClientCache.getFromCaches(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_IQ_VIDEOS, "queue", String.valueOf(n), String.valueOf(n2)));
    }
    
    private void putCWLoMoIndex(final String s) {
        this.getHardCache().put("cw_lomo_index", s);
    }
    
    private void putIQLoMoIndex(final String s) {
        this.getHardCache().put("iq_lomo_index", s);
    }
    
    public boolean areIqIdsInCache() {
        if (StringUtils.isEmpty(this.getLoLoMoId())) {
            Log.d("nf_browse_cache", "LoLoMoId missing in cache");
            return false;
        }
        if (StringUtils.isEmpty(this.getIQLoMoIndex())) {
            Log.d("nf_browse_cache", "IQ index missing in cache");
            return false;
        }
        if (StringUtils.isEmpty(this.getIQLoMoId())) {
            Log.d("nf_browse_cache", "IQ LoMo Id missing in cache");
            return false;
        }
        return true;
    }
    
    public boolean cacheHasLolomoId() {
        if (StringUtils.isEmpty(this.getLoLoMoId())) {
            Log.d("nf_browse_cache", "LoLoMoId missing in cache");
            return false;
        }
        return true;
    }
    
    public boolean canRefreshCW() {
        if (StringUtils.isEmpty(this.getCWLoMoIndex())) {
            Log.d("nf_browse_cache", "CW index missing in cache - cannot refresh CW");
            return false;
        }
        if (StringUtils.isEmpty(this.getCWLoMoId())) {
            Log.d("nf_browse_cache", "CW id missing in cache - cannot refresh CW");
            return false;
        }
        return true;
    }
    
    public boolean canRefreshIQ() {
        if (StringUtils.isEmpty(this.getIQLoMoIndex())) {
            Log.d("nf_browse_cache", "IQ index missing in cache - cannot refresh IQ");
            return false;
        }
        if (StringUtils.isEmpty(this.getIQLoMoId())) {
            Log.d("nf_browse_cache", "IQ id missing in cache - cannot refresh IQ");
            return false;
        }
        return true;
    }
    
    public String getCWLoMoId() {
        return (String)this.getHardCache().get("continueWatching_list_id");
    }
    
    public String getCWLoMoIndex() {
        return (String)this.getHardCache().get("cw_lomo_index");
    }
    
    public ListOfMoviesSummary getCWLoMoSummary() {
        return (ListOfMoviesSummary)this.getHardCache().get("cw_lomo_summary");
    }
    
    public List<String> getCwKeysCache() {
        return this.cwKeysCache;
    }
    
    public Object getFromCaches(final String s) {
        Object o;
        if ((o = this.getSoftCache().get(s)) == null) {
            o = this.getHardCache().get(s);
        }
        return o;
    }
    
    public String getGenreLoLoMoId(String buildBrowseGenreLoLoMoCacheKey) {
        buildBrowseGenreLoLoMoCacheKey = buildBrowseGenreLoLoMoCacheKey(buildBrowseGenreLoLoMoCacheKey);
        return (String)this.getHardCache().get(buildBrowseGenreLoLoMoCacheKey);
    }
    
    public HardCache getHardCache() {
        return this.hardCache;
    }
    
    public String getIQLoMoId() {
        return (String)this.getHardCache().get("queue_list_id");
    }
    
    public String getIQLoMoIndex() {
        return (String)this.getHardCache().get("iq_lomo_index");
    }
    
    public ListOfMoviesSummary getIQLoMoSummary() {
        return (ListOfMoviesSummary)this.getHardCache().get("iq_lomo_summary");
    }
    
    public List<String> getIqKeysCache() {
        return this.iqKeysCache;
    }
    
    public KidsCharacterDetails getKidsCharacterDetailsFromCache(final String s) {
        return (KidsCharacterDetails)this.getFromCaches(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_KIDS_CDP, s, "0", "0"));
    }
    
    public String getLoLoMoId() {
        return (String)this.getHardCache().get("lolomo_id");
    }
    
    public SoftCache getSoftCache() {
        return this.softCache;
    }
    
    public SoftCache getWeakEpisodesCache() {
        return this.weakEpisodesCache;
    }
    
    public SoftCache getWeakSeasonsCache() {
        return this.weakSeasonsCache;
    }
    
    public boolean needToPrefetch() {
        return this.getLoLoMoId() == null;
    }
    
    public boolean needToPrefetchGenre(final String s) {
        return this.getGenreLoLoMoId(s) == null;
    }
    
    public void putBBVideosInBrowseCache(final Object o, final int n, final int n2) {
        this.putInHardCache(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_BB_VIDEOS, "billboard", String.valueOf(n), String.valueOf(n2)), o);
    }
    
    public void putCWIdsInCache(final ListOfMoviesSummary listOfMoviesSummary, final String s) {
        this.putCWLoMoSummary(listOfMoviesSummary);
        this.putCWLoMoIndex(String.valueOf(s));
        this.putCWLoMoId(listOfMoviesSummary.getId());
    }
    
    public void putCWLoMoId(final String s) {
        this.getHardCache().put("continueWatching_list_id", s);
    }
    
    public void putCWLoMoSummary(final Object o) {
        this.getHardCache().put("cw_lomo_summary", o);
    }
    
    public void putCWVideosInBrowseCache(final Object o, final int n, final int n2) {
        this.putInHardCache(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(n), String.valueOf(n2)), o);
    }
    
    public void putGenreLoLoMoIdInBrowseCache(final String s, final String s2) {
        final String buildBrowseGenreLoLoMoCacheKey = buildBrowseGenreLoLoMoCacheKey(s);
        Log.d("nf_browse_cache", String.format("greneId %s grenreLoLoMoId %s", s, s2));
        this.putInHardCache(buildBrowseGenreLoLoMoCacheKey, s2);
    }
    
    public void putGenreLoMoInBrowseCache(final String s, final Object o, final int n, final int n2) {
        this.putInSoftCache(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_GENRE_VIDEOS, s, String.valueOf(n), String.valueOf(n2)), o);
    }
    
    public void putGenreLoMoSummaryInBrowseCache(final Object o, final String s, final int n, final int n2) {
        this.putInHardCache(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_GENRE_LOMO, s, String.valueOf(n), String.valueOf(n2)), o);
    }
    
    public void putIQIdsInCache(final ListOfMoviesSummary listOfMoviesSummary, final String s) {
        this.putIQLoMoSummary(listOfMoviesSummary);
        this.putIQLoMoIndex(String.valueOf(s));
        this.putIQLoMoId(listOfMoviesSummary.getId());
    }
    
    public void putIQLoMoId(final String s) {
        this.getHardCache().put("queue_list_id", s);
    }
    
    public void putIQLoMoSummary(final Object o) {
        this.getHardCache().put("iq_lomo_summary", o);
    }
    
    public void putIQVideosInBrowseCache(final Object o, final int n, final int n2) {
        final String buildBrowseCacheKey = buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_IQ_VIDEOS, "queue", String.valueOf(n), String.valueOf(n2));
        this.getIqKeysCache().add(buildBrowseCacheKey);
        this.putInHardCache(buildBrowseCacheKey, o);
    }
    
    public void putInHardCache(final String s, final Object o) {
        this.getHardCache().put(s, o);
    }
    
    public void putInSoftCache(final String s, final Object o) {
        this.getSoftCache().put(s, o);
    }
    
    public void putInWeakEpisodesCache(final EpisodeDetails episodeDetails) {
        final String buildEpisodeDetailsCacheKey = buildEpisodeDetailsCacheKey(episodeDetails.getId());
        if (Log.isLoggable("nf_browse_cache", 3)) {
            Log.d("nf_browse_cache", "putting episode in weakEpisodesCache: " + episodeDetails.getTitle() + ", cache key: " + buildEpisodeDetailsCacheKey);
        }
        this.getWeakEpisodesCache().put(buildEpisodeDetailsCacheKey, new WeakReference(episodeDetails));
    }
    
    public void putInWeakEpisodesCache(final List<EpisodeDetails> list) {
        final Iterator<EpisodeDetails> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.putInWeakEpisodesCache(iterator.next());
        }
    }
    
    public void putInWeakSeasonsCache(final List<SeasonDetails> list) {
        for (final SeasonDetails seasonDetails : list) {
            this.getWeakSeasonsCache().put(buildSeasonDetailsCacheKey(seasonDetails.getId()), new WeakReference(seasonDetails));
        }
    }
    
    public void putLoLoMoIdInBrowseCache(final String s) {
        this.putInHardCache("lolomo_id", s);
        Log.d("nf_browse_cache", "lolomoId =" + s);
    }
    
    public void putLoMoInBrowseCache(final String s, final Object o, final int n, final int n2) {
        this.putInHardCache(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_VIDEOS, s, String.valueOf(n), String.valueOf(n2)), o);
    }
    
    public void putLoMoSummaryInBrowseCache(final Object o, final int n, final int n2) {
        this.putInHardCache(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_LOMO, "lolomo", String.valueOf(n), String.valueOf(n2)), o);
    }
}
