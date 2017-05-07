// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.UserProfile;
import com.netflix.mediaclient.servicemgr.ProfileType;
import com.netflix.mediaclient.servicemgr.SearchResults;
import com.netflix.mediaclient.service.webclient.model.PrefetchVideoList;
import com.netflix.mediaclient.servicemgr.VideoList;
import com.netflix.mediaclient.servicemgr.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.LoLoMo;
import com.netflix.mediaclient.servicemgr.Genre;
import com.netflix.mediaclient.servicemgr.GenreList;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.ui.Asset;
import java.util.Iterator;
import com.netflix.mediaclient.service.browse.cache.BrowseCache;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.ArrayList;
import com.netflix.mediaclient.service.browse.cache.HardBrowseCache;
import com.netflix.mediaclient.service.browse.cache.SoftBrowseCache;
import java.lang.ref.WeakReference;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import android.os.Handler;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import java.util.List;
import com.netflix.mediaclient.service.ServiceAgent;

public class BrowseAgent extends ServiceAgent implements BrowseAgentInterface
{
    public static final String BOOKMARK_TAG = "nf_bookmark";
    public static final String CACHE_KEY_CW_LIST_ID = "continueWatching_list_id";
    public static final String CACHE_KEY_CW_LOMO_INDEX = "cw_lomo_index";
    public static final String CACHE_KEY_CW_LOMO_SUMMARY = "cw_lomo_summary";
    public static final String CACHE_KEY_GENRE_LOLOMO_ID = "genre_lolomo_id";
    public static final String CACHE_KEY_IQ_LIST_ID = "queue_list_id";
    public static final String CACHE_KEY_IQ_LOMO_INDEX = "iq_lomo_index";
    public static final String CACHE_KEY_IQ_LOMO_SUMMARY = "iq_lomo_summary";
    public static final String CACHE_KEY_LOLOMO_ID = "lolomo_id";
    public static final String CACHE_KEY_PREFIX_CW_VIDEOS;
    public static final String CACHE_KEY_PREFIX_EPISODE_DETAILS;
    public static final String CACHE_KEY_PREFIX_GENRE_LOMO;
    public static final String CACHE_KEY_PREFIX_GENRE_VIDEOS;
    public static final String CACHE_KEY_PREFIX_IQ_VIDEOS;
    private static final String CACHE_KEY_PREFIX_KIDS_CDP;
    public static final String CACHE_KEY_PREFIX_LOMO;
    public static final String CACHE_KEY_PREFIX_MDP;
    public static final String CACHE_KEY_PREFIX_SDP;
    private static final String CACHE_KEY_PREFIX_SEASON_DETAILS;
    public static final String CACHE_KEY_PREFIX_VIDEOS;
    public static final String CHARACTERS_KEY = "characters";
    public static final String CONTINUE_WATCHING_KEY = "continueWatching";
    public static final String GENRE_LIST_KEY = "genreList";
    public static final String INSTANT_QUEUE_KEY = "queue";
    public static final String LOLOMO_KEY = "lolomo";
    private static final int MARGIN_FOR_BOOKMARK_RESET_SECONDS = 30;
    private static final int MAX_NUM_EPISODES_ITEMS;
    private static final int MAX_NUM_SEASONS_ITEMS;
    private static final int MAX_NUM_SOFTCACHE_ITEMS;
    static final String SEPERATOR = "_";
    private static final String TAG = "nf_service_browseagent";
    private static final boolean USE_HARD_CACHE_CONST = false;
    private static final boolean USE_SOFT_CACHE_CONST = true;
    private static boolean isCurrentProfileActive;
    private static int mPrefetchFromCWVideo;
    private static int mPrefetchFromVideo;
    private static int mPrefetchToCWVideo;
    private static int mPrefetchToVideo;
    private List<String> cwKeysCache;
    private DataDumper dataDumper;
    private HardCache hardCache;
    private List<String> iqKeysCache;
    private BrowseWebClient mBrowseWebClient;
    private final PlayReceiver mPlayReceiver;
    public final BroadcastReceiver mUserAgentIntentReceiver;
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
        CACHE_KEY_PREFIX_LOMO = getCacheKeyFromClassName(FetchLoMosTask.class);
        CACHE_KEY_PREFIX_MDP = getCacheKeyFromClassName(FetchMovieDetailsTask.class);
        CACHE_KEY_PREFIX_SDP = getCacheKeyFromClassName(FetchShowDetailsTask.class);
        CACHE_KEY_PREFIX_EPISODE_DETAILS = getCacheKeyFromClassName(FetchEpisodeDetailsTask.class);
        CACHE_KEY_PREFIX_GENRE_LOMO = getCacheKeyFromClassName(FetchGenresTask.class);
        CACHE_KEY_PREFIX_CW_VIDEOS = getCacheKeyFromClassName(FetchCWVideosTask.class);
        CACHE_KEY_PREFIX_IQ_VIDEOS = getCacheKeyFromClassName(FetchIQVideosTask.class);
        CACHE_KEY_PREFIX_VIDEOS = getCacheKeyFromClassName(FetchVideosTask.class);
        CACHE_KEY_PREFIX_GENRE_VIDEOS = getCacheKeyFromClassName(FetchGenreVideosTask.class);
        CACHE_KEY_PREFIX_KIDS_CDP = getCacheKeyFromClassName(FetchKidsCharacterDetailsTask.class);
        CACHE_KEY_PREFIX_SEASON_DETAILS = getCacheKeyFromClassName(FetchSeasonDetailsTask.class);
    }
    
    public BrowseAgent() {
        this.mPlayReceiver = new PlayReceiver();
        this.mUserAgentIntentReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (intent != null) {
                    final String action = intent.getAction();
                    Log.i("nf_service_browseagent", "UserAgentIntentReceiver inovoked and received Intent with Action " + intent.getAction());
                    if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_ACTIVE".equals(action)) {
                        BrowseAgent.this.handleProfileActive();
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.NOTIFY_USER_PROFILE_DEACTIVE".equals(action)) {
                        BrowseAgent.this.handleProfileDeactive();
                    }
                }
            }
        };
    }
    
    public static boolean areIqIdsInCache(final HardCache hardCache) {
        if (StringUtils.isEmpty(getLoLoMoId(hardCache))) {
            Log.d("nf_service_browseagent", "LoLoMoId missing in cache");
            return false;
        }
        if (StringUtils.isEmpty(getIQLoMoIndex(hardCache))) {
            Log.d("nf_service_browseagent", "IQ index missing in cache");
            return false;
        }
        if (StringUtils.isEmpty(getIQLoMoId(hardCache))) {
            Log.d("nf_service_browseagent", "IQ LoMo Id missing in cache");
            return false;
        }
        return true;
    }
    
    public static String buildBrowseCacheKey(final String s, final String s2, final String s3, final String s4) {
        return s + "_" + s2 + "_" + s3 + "_" + s4;
    }
    
    public static String buildBrowseGenreLoLoMoCacheKey(final String s) {
        return buildBrowseCacheKey("genre_lolomo_id", s, "0", "0");
    }
    
    private static String buildEpisodeDetailsCacheKey(final String s) {
        return buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_EPISODE_DETAILS, s, "0", "0");
    }
    
    private static String buildSeasonDetailsCacheKey(final String s) {
        return buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SEASON_DETAILS, s, "0", "0");
    }
    
    public static String buildStillUrlFromPos(final String s, final int n, final int n2) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        int nextInt;
        if ((nextInt = n) < 10) {
            nextInt = new Random().nextInt(n2);
        }
        final String value = String.valueOf(nextInt / 10);
        final StringBuilder append = new StringBuilder(s).append("/00000");
        append.replace(append.length() - value.length(), append.length(), value);
        append.append(".jpg");
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            Log.d("nf_bookmark", "baseUrl:" + s + " stillPos:" + value + " stillUrl:" + append.toString());
        }
        return append.toString();
    }
    
    private boolean cacheHasLolomoId(final HardCache hardCache) {
        if (StringUtils.isEmpty(getLoLoMoId(hardCache))) {
            Log.d("nf_service_browseagent", "LoLoMoId missing in cache");
            return false;
        }
        return true;
    }
    
    private static boolean canDoDataFetches() {
        if (!BrowseAgent.isCurrentProfileActive) {
            Log.d("nf_service_browseagent", "wrong state - canDoDataFetches false - skipping browse request");
        }
        return BrowseAgent.isCurrentProfileActive;
    }
    
    private boolean canRefreshCW(final HardCache hardCache) {
        if (StringUtils.isEmpty(getCWLoMoIndex(hardCache))) {
            Log.d("nf_service_browseagent", "CW index missing in cache - cannot refresh CW");
            return false;
        }
        if (StringUtils.isEmpty(getCWLoMoId(hardCache))) {
            Log.d("nf_service_browseagent", "CW id missing in cache - cannot refresh CW");
            return false;
        }
        return true;
    }
    
    public static int computePlayPos(final int n, int n2, final int n3) {
        if (n2 > 0 && n >= n2) {
            n2 = 0;
        }
        else {
            if (n3 > 0 && n >= n3 - 30) {
                return 0;
            }
            if ((n2 = n) < 0) {
                return 0;
            }
        }
        return n2;
    }
    
    public static String getCWLoMoId(final HardCache hardCache) {
        return (String)hardCache.get("continueWatching_list_id");
    }
    
    public static String getCWLoMoIndex(final HardCache hardCache) {
        return (String)hardCache.get("cw_lomo_index");
    }
    
    public static ListOfMoviesSummary getCWLoMoSummary(final HardCache hardCache) {
        return (ListOfMoviesSummary)hardCache.get("cw_lomo_summary");
    }
    
    private static String getCacheKeyFromClassName(final Class<?> clazz) {
        return clazz.getSimpleName();
    }
    
    public static Object getFromCaches(final HardCache hardCache, final SoftCache softCache, final String s) {
        Object o;
        if ((o = softCache.get(s)) == null) {
            o = hardCache.get(s);
        }
        return o;
    }
    
    public static String getGenreLoLoMoId(final HardCache hardCache, final String s) {
        return (String)hardCache.get(buildBrowseGenreLoLoMoCacheKey(s));
    }
    
    public static String getIQLoMoId(final HardCache hardCache) {
        return (String)hardCache.get("queue_list_id");
    }
    
    public static String getIQLoMoIndex(final HardCache hardCache) {
        return (String)hardCache.get("iq_lomo_index");
    }
    
    public static ListOfMoviesSummary getIQLoMoSummary(final HardCache hardCache) {
        return (ListOfMoviesSummary)hardCache.get("iq_lomo_summary");
    }
    
    public static com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails getKidsCharacterDetailsFromCache(final String s, final HardCache hardCache, final SoftCache softCache) {
        return (com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails)getFromCaches(hardCache, softCache, buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_KIDS_CDP, s, "0", "0"));
    }
    
    public static String getLoLoMoId(final HardCache hardCache) {
        return (String)hardCache.get("lolomo_id");
    }
    
    private com.netflix.mediaclient.service.webclient.model.EpisodeDetails getNextPlayableEpisode(com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails, final long lastModified) {
        if (computePlayPos(episodeDetails.getBookmarkPosition(), episodeDetails.getEndtime(), episodeDetails.getRuntime()) == episodeDetails.getBookmarkPosition()) {
            return episodeDetails;
        }
        final String buildEpisodeDetailsCacheKey = buildEpisodeDetailsCacheKey(episodeDetails.getNextEpisodeId());
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
        }
        final WeakReference weakReference = (WeakReference)this.weakEpisodesCache.get(buildEpisodeDetailsCacheKey);
        if (weakReference != null) {
            final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails2 = weakReference.get();
            episodeDetails2.bookmark.setLastModified(lastModified);
            episodeDetails = episodeDetails2;
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("nextPlayableEpisode %s title %s", episodeDetails2.getId(), episodeDetails2.getTitle()));
                episodeDetails = episodeDetails2;
            }
        }
        else {
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", "next episode does not exist in cache - expecting cwRefresh to have it on next update");
            }
            episodeDetails.bookmark.setLastModified(0L);
        }
        return episodeDetails;
    }
    
    private void handleProfileActive() {
        Log.i("nf_service_browseagent", "Flushing all caches...");
        this.flushCaches();
        BrowseAgent.isCurrentProfileActive = true;
    }
    
    private void handleProfileDeactive() {
        BrowseAgent.isCurrentProfileActive = false;
    }
    
    private void initCaches() {
        this.softCache = new SoftBrowseCache(BrowseAgent.MAX_NUM_SOFTCACHE_ITEMS);
        this.hardCache = new HardBrowseCache();
        this.weakSeasonsCache = new SoftBrowseCache(BrowseAgent.MAX_NUM_SEASONS_ITEMS);
        this.weakEpisodesCache = new SoftBrowseCache(BrowseAgent.MAX_NUM_EPISODES_ITEMS);
        this.cwKeysCache = new ArrayList<String>();
        this.iqKeysCache = new ArrayList<String>();
        this.mBrowseWebClient = BrowseWebClientFactory.create(this.hardCache, this.softCache, this.weakSeasonsCache, this.cwKeysCache, this.iqKeysCache, this.getService(), this.getResourceFetcher().getApiNextWebClient());
    }
    
    private Boolean isKidsCharacterDetailsNew(final KidsCharacterDetails kidsCharacterDetails, final KidsCharacterDetails kidsCharacterDetails2) {
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            final String characterId = kidsCharacterDetails.getCharacterId();
            final String playableId = kidsCharacterDetails.getPlayableId();
            String playableId2;
            if (kidsCharacterDetails2 == null) {
                playableId2 = "n/a";
            }
            else {
                playableId2 = kidsCharacterDetails2.getPlayableId();
            }
            Log.d("nf_service_browseagent", String.format("charId: %s, cachePlayableId:%s, newPlayableId:%s", characterId, playableId, playableId2));
        }
        if (kidsCharacterDetails2 == null) {
            return false;
        }
        return !StringUtils.safeEquals(kidsCharacterDetails.getPlayableId(), kidsCharacterDetails2.getPlayableId());
    }
    
    private void launchTask(final Runnable runnable) {
        if (Log.isLoggable("nf_service_browseagent", 2)) {
            Log.v("nf_service_browseagent", "Launching task: " + runnable.getClass().getSimpleName());
        }
        if (this.mBrowseWebClient.isSynchronous()) {
            new BackgroundTask().execute(runnable);
            return;
        }
        runnable.run();
    }
    
    private boolean needToPrefetch() {
        return getLoLoMoId(this.hardCache) == null;
    }
    
    private boolean needToPrefetchGenre(final String s) {
        return getGenreLoLoMoId(this.hardCache, s) == null;
    }
    
    public static void putCWLoMoId(final HardCache hardCache, final Object o) {
        hardCache.put("continueWatching_list_id", o);
    }
    
    public static void putCWLoMoIndex(final HardCache hardCache, final Object o) {
        hardCache.put("cw_lomo_index", o);
    }
    
    public static void putCWLoMoSummary(final HardCache hardCache, final Object o) {
        hardCache.put("cw_lomo_summary", o);
    }
    
    public static void putIQLoMoId(final HardCache hardCache, final Object o) {
        hardCache.put("queue_list_id", o);
    }
    
    public static void putIQLoMoIndex(final HardCache hardCache, final Object o) {
        hardCache.put("iq_lomo_index", o);
    }
    
    public static void putIQLoMoSummary(final HardCache hardCache, final Object o) {
        hardCache.put("iq_lomo_summary", o);
    }
    
    public static void putInBrowseCache(final BrowseCache browseCache, final String s, final Object o) {
        browseCache.put(s, o);
    }
    
    private void putInWeakEpisodesCache(final EpisodeDetails episodeDetails) {
        final String buildEpisodeDetailsCacheKey = buildEpisodeDetailsCacheKey(episodeDetails.getId());
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            Log.d("nf_bookmark", "putting episode in weakEpisodesCache: " + episodeDetails.getTitle() + ", cache key: " + buildEpisodeDetailsCacheKey);
        }
        putInBrowseCache(this.weakEpisodesCache, buildEpisodeDetailsCacheKey, new WeakReference(episodeDetails));
    }
    
    private void putInWeakEpisodesCache(final List<EpisodeDetails> list) {
        final Iterator<EpisodeDetails> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.putInWeakEpisodesCache(iterator.next());
        }
    }
    
    private void putInWeakSeasonsCache(final List<SeasonDetails> list) {
        for (final SeasonDetails seasonDetails : list) {
            putInBrowseCache(this.weakSeasonsCache, buildSeasonDetailsCacheKey(seasonDetails.getId()), new WeakReference(seasonDetails));
        }
    }
    
    private void refreshCacheWithLastPlayed(final Asset asset) {
        final int playbackBookmark = asset.getPlaybackBookmark();
        if (playbackBookmark > 0) {
            final String playableId = asset.getPlayableId();
            final boolean episode = asset.isEpisode();
            final String parentId = asset.getParentId();
            final long currentTimeMillis = System.currentTimeMillis();
            if (!StringUtils.isEmpty(playableId) && (!episode || !StringUtils.isEmpty(parentId))) {
                if (Log.isLoggable("nf_service_browseagent", 3)) {
                    Log.d("nf_bookmark", String.format("%s bookmarkpos %d", playableId, playbackBookmark));
                }
                com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails = null;
                final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails2 = null;
                com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails3 = null;
                Label_0323: {
                    if (!episode) {
                        final MovieDetails movieDetails = (MovieDetails)getFromCaches(this.hardCache, this.softCache, buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, playableId, "0", "0"));
                        episodeDetails3 = episodeDetails2;
                        if (movieDetails != null) {
                            episodeDetails3 = episodeDetails2;
                            if (movieDetails.bookmark != null) {
                                if (Log.isLoggable("nf_service_browseagent", 3)) {
                                    Log.d("nf_bookmark", String.format("%s movie bookmarkPos %d to newPos %d, oldtime %d to newTime %d", playableId, movieDetails.getBookmarkPosition(), playbackBookmark, movieDetails.bookmark.getLastModified(), currentTimeMillis));
                                }
                                movieDetails.setBookmarkPosition(playbackBookmark);
                                movieDetails.bookmark.setLastModified(currentTimeMillis);
                                episodeDetails3 = episodeDetails2;
                                if (Log.isLoggable("nf_service_browseagent", 3)) {
                                    Log.d("nf_bookmark", String.format("%s, bookmarkpos %d playpos %d endtime %d runtime %d", playableId, playbackBookmark, computePlayPos(playbackBookmark, movieDetails.getEndtime(), movieDetails.getRuntime()), movieDetails.getEndtime(), movieDetails.getRuntime()));
                                    episodeDetails3 = episodeDetails2;
                                }
                            }
                        }
                    }
                    else {
                        final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)getFromCaches(this.hardCache, this.softCache, buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, parentId, "0", "0"));
                        if (showDetails == null) {
                            return;
                        }
                        final String buildEpisodeDetailsCacheKey = buildEpisodeDetailsCacheKey(playableId);
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
                        }
                        final WeakReference weakReference = (WeakReference)this.weakEpisodesCache.get(buildEpisodeDetailsCacheKey);
                        if (weakReference != null) {
                            episodeDetails = weakReference.get();
                        }
                        if (episodeDetails != null && episodeDetails.bookmark != null) {
                            if (Log.isLoggable("nf_service_browseagent", 3)) {
                                Log.d("nf_bookmark", String.format("%s episode bookmarkPos %d to newPos %d, oldtime %d to newTime %d", playableId, episodeDetails.getBookmarkPosition(), playbackBookmark, episodeDetails.bookmark.getLastModified(), currentTimeMillis));
                            }
                            episodeDetails.bookmark.setBookmarkPosition(playbackBookmark);
                            episodeDetails.bookmark.setLastModified(currentTimeMillis);
                            if (Log.isLoggable("nf_service_browseagent", 3)) {
                                Log.d("nf_bookmark", String.format("id %s, bookmarkpos %d playpos %d endtime %d runtime %d", playableId, playbackBookmark, computePlayPos(playbackBookmark, episodeDetails.getEndtime(), episodeDetails.getRuntime()), episodeDetails.getEndtime(), episodeDetails.getRuntime()));
                            }
                            final com.netflix.mediaclient.service.webclient.model.EpisodeDetails nextPlayableEpisode = this.getNextPlayableEpisode(episodeDetails, currentTimeMillis);
                            if (Log.isLoggable("nf_service_browseagent", 3)) {
                                Log.d("nf_bookmark", String.format("next playable episode: %s - %s, S%d: E%d", nextPlayableEpisode.getId(), nextPlayableEpisode.getTitle(), nextPlayableEpisode.getSeasonNumber(), nextPlayableEpisode.getEpisodeNumber()));
                            }
                            final boolean safeEquals = StringUtils.safeEquals(showDetails.currentEpisode.getId(), episodeDetails.getId());
                            updateShowOnEpisodePlay(showDetails, nextPlayableEpisode);
                            updateSeasonsInformation(this.weakSeasonsCache, nextPlayableEpisode.getSeasonId(), nextPlayableEpisode.getEpisodeNumber());
                            if (episodeDetails.hasSameSeasonAndEpisodeNumbers(nextPlayableEpisode)) {
                                episodeDetails3 = nextPlayableEpisode;
                                if (safeEquals) {
                                    break Label_0323;
                                }
                            }
                            this.sendEpisodeRefreshBrodcast(nextPlayableEpisode.getSeasonNumber(), nextPlayableEpisode.getEpisodeNumber());
                            episodeDetails3 = nextPlayableEpisode;
                        }
                        else {
                            if (!StringUtils.safeEquals(playableId, showDetails.getCurrentEpisodeId())) {
                                return;
                            }
                            showDetails.currentEpisodeBookmark.setBookmarkPosition(playbackBookmark);
                            showDetails.currentEpisodeBookmark.setLastModified(currentTimeMillis);
                            episodeDetails3 = episodeDetails2;
                            if (Log.isLoggable("nf_service_browseagent", 3)) {
                                Log.d("nf_bookmark", "Episode details don't exist; updated sdp currentEpisode");
                                episodeDetails3 = episodeDetails2;
                            }
                        }
                    }
                }
                this.updateCwOnPlay(playableId, playbackBookmark, currentTimeMillis, episodeDetails3);
            }
        }
    }
    
    private void registerPlayReceiver() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP");
        this.getContext().registerReceiver((BroadcastReceiver)this.mPlayReceiver, intentFilter);
    }
    
    private void registerUserAgentIntentReceiver() {
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mUserAgentIntentReceiver, UserAgentBroadcastIntents.getNotificationIntentFilter());
    }
    
    private void sendCwRefreshBrodcast() {
        this.getContext().sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_CW_REFRESH"));
        Log.v("nf_service_browseagent", "Intent CW_REFRESH sent");
    }
    
    private void sendIqRefreshBrodcast() {
        this.getContext().sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH"));
        Log.v("nf_service_browseagent", "Intent IQ_REFRESH sent");
    }
    
    private void unregisterPlayReceiver() {
        try {
            this.getContext().unregisterReceiver((BroadcastReceiver)this.mPlayReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_service_browseagent", "unregisterPlayReceiver " + ex);
        }
    }
    
    private void unregisterUserAgentIntentReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mUserAgentIntentReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_service_browseagent", "unregisterUserAgentIntentReceiver " + ex);
        }
    }
    
    private void updateCwForNewEpisode(final CWVideo cwVideo, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        if (Log.isLoggable("nf_service_browseagent", 2)) {
            Log.v("nf_service_browseagent", "Updating CWVideo episode info with details: " + episodeDetails.detail);
        }
        cwVideo.currentEpisode = episodeDetails.detail;
        cwVideo.currentEpisodeBookmark = episodeDetails.bookmark;
    }
    
    private void updateCwOnPlay(final String s, final int n, final long n2, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        final List list = (List)this.hardCache.get(buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(BrowseAgent.mPrefetchFromCWVideo), String.valueOf(BrowseAgent.mPrefetchToCWVideo)));
        if (list != null) {
            for (final CWVideo cwVideo : list) {
                final boolean b = false;
                boolean b2;
                if (cwVideo.getPlayableId().equals(s)) {
                    b2 = true;
                    this.updateCwPlayable(cwVideo, n, n2, episodeDetails);
                }
                else {
                    b2 = b;
                    if (episodeDetails != null) {
                        b2 = b;
                        if (StringUtils.safeEquals(cwVideo.getParentId(), episodeDetails.getParentId())) {
                            if (Log.isLoggable("nf_service_browseagent", 2)) {
                                Log.v("nf_service_browseagent", "Updating CW video for new episode in same show, id: " + cwVideo.getParentId());
                            }
                            b2 = true;
                            this.updateCwForNewEpisode(cwVideo, episodeDetails);
                        }
                    }
                }
                if (b2 && cwVideo.bookmarkStill != null) {
                    cwVideo.bookmarkStill.stillUrl = buildStillUrlFromPos(cwVideo.getBaseUrl(), cwVideo.getPlayableBookmarkPosition(), cwVideo.getEndtime());
                }
            }
        }
    }
    
    private void updateCwPlayable(final CWVideo cwVideo, final int n, final long n2, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        if (VideoType.MOVIE.equals(cwVideo.getType())) {
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("cw movie bookmarkPos  updated from %d to %d", cwVideo.getBookmarkPosition(), n));
            }
            cwVideo.bookmark.setBookmarkPosition(n);
            cwVideo.bookmark.setLastModified(n2);
        }
        else {
            if (episodeDetails != null) {
                cwVideo.currentEpisode = episodeDetails.detail;
                cwVideo.currentEpisodeBookmark = episodeDetails.bookmark;
            }
            else {
                cwVideo.currentEpisodeBookmark.setBookmarkPosition(n);
                cwVideo.currentEpisodeBookmark.setLastModified(n2);
            }
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("cw %s updated - ce %s bookmarkTime %d bookmarkPos %d", cwVideo.getId(), cwVideo.getCurrentEpisodeId(), cwVideo.getBookmarkLastUpdateTime(), cwVideo.getBookmarkPosition()));
            }
        }
    }
    
    private void updateEpisodesWithLatestShowInformation(final HardCache hardCache, final SoftCache softCache, final List<EpisodeDetails> list) {
        if (list.size() != 0) {
            final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)getFromCaches(hardCache, softCache, buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, list.get(0).getShowId(), "0", "0"));
            if (showDetails != null && showDetails.socialEvidence != null) {
                final Iterator<EpisodeDetails> iterator = list.iterator();
                while (iterator.hasNext()) {
                    ((com.netflix.mediaclient.service.webclient.model.EpisodeDetails)iterator.next()).showSocialEvidence = showDetails.socialEvidence;
                }
            }
        }
    }
    
    private void updateSeasonWithSdp(final ShowDetails showDetails) {
        if (showDetails == null) {
            return;
        }
        this.fetchSeasons(showDetails.getId(), 0, showDetails.getNumOfSeasons() - 1, new SimpleBrowseAgentCallback() {
            @Override
            public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
                if (n == 0) {
                    for (final SeasonDetails seasonDetails : list) {
                        if (seasonDetails.getSeasonNumber() == showDetails.getCurrentSeasonNumber()) {
                            final com.netflix.mediaclient.service.webclient.model.SeasonDetails seasonDetails2 = (com.netflix.mediaclient.service.webclient.model.SeasonDetails)seasonDetails;
                            if (seasonDetails2.detail == null) {
                                continue;
                            }
                            if (Log.isLoggable("nf_service_browseagent", 3)) {
                                Log.d("nf_service_browseagent", String.format("currentepisode being overwitten from %d to %d", seasonDetails.getCurrentEpisodeNumber(), showDetails.getCurrentEpisodeNumber()));
                            }
                            seasonDetails2.detail.currentEpisodeNumber = showDetails.getCurrentEpisodeNumber();
                        }
                    }
                }
            }
        });
    }
    
    public static void updateSeasonsInformation(final SoftCache softCache, final String s, final int currentEpisodeNumber) {
        final WeakReference weakReference = (WeakReference)softCache.get(buildSeasonDetailsCacheKey(s));
        if (weakReference != null) {
            final com.netflix.mediaclient.service.webclient.model.SeasonDetails seasonDetails = weakReference.get();
            seasonDetails.detail.currentEpisodeNumber = currentEpisodeNumber;
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", "updateSeasonsInformation: seasonId:" + s + " currentEpisode:" + seasonDetails.getCurrentEpisodeNumber());
            }
        }
    }
    
    private static void updateShowOnEpisodePlay(final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        showDetails.currentEpisode = episodeDetails.detail;
        showDetails.currentEpisodeBookmark = episodeDetails.bookmark;
    }
    
    public void addToQueue(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new AddToQueueTask(s, n, browseAgentCallback));
    }
    
    public boolean areIqIdsInCache() {
        return areIqIdsInCache(this.hardCache);
    }
    
    @Override
    public void destroy() {
        this.unregisterUserAgentIntentReceiver();
        this.unregisterPlayReceiver();
        super.destroy();
    }
    
    public void doInit() {
        this.initCaches();
        BrowseAgent.isCurrentProfileActive = false;
        this.registerUserAgentIntentReceiver();
        this.registerPlayReceiver();
        this.dataDumper = new DataDumper(this.mBrowseWebClient, this.hardCache, this.softCache);
        this.initCompleted(0);
    }
    
    public void dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        this.dataDumper.dumpHomeLoLoMosAndVideos(s, s2);
    }
    
    public void dumpHomeLoLoMosAndVideosToLog() {
        this.dataDumper.dumpHomeLoLoMosAndVideosToLog();
    }
    
    public void dumpHomeLoMos() {
        this.dataDumper.dumpHomeLoMos();
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchCWVideosTask(n, n2, browseAgentCallback, true));
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchEpisodeDetailsTask(s, browseAgentCallback));
    }
    
    public void fetchEpisodes(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchEpisodesTask(s, n, n2, browseAgentCallback));
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenreListsTask(browseAgentCallback));
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenreVideosTask(loMo, n, n2, browseAgentCallback));
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenresTask(s, n, n2, browseAgentCallback));
    }
    
    public void fetchIQVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchIQVideosTask(n, n2, browseAgentCallback, true));
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchKidsCharacterDetailsTask(s, browseAgentCallback));
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchLoLoMoSummaryTask(s, browseAgentCallback));
    }
    
    public void fetchLoMos(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchLoMosTask(s, n, n2, browseAgentCallback, true));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchMovieDetailsTask(s, browseAgentCallback));
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchPostPlayTask(s, browseAgentCallback));
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSeasonDetailsTask(s, browseAgentCallback));
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSeasonsTask(s, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchShowDetailsTask(s, s2, browseAgentCallback));
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSimilarVideosForPersonTask(s, n, browseAgentCallback));
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSimilarVideosForQuerySuggestionTask(s, n, browseAgentCallback));
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchVideosTask(loMo, n, n2, browseAgentCallback, true));
    }
    
    public void flushCaches() {
        this.initCaches();
    }
    
    public void hideVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new HideVideoTask(s, browseAgentCallback));
    }
    
    public void logBillboardActivity(final Video video, final BillboardActivityType billboardActivityType) {
        this.launchTask(new LogBillboardActivityTask(video, billboardActivityType));
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("nf_service_browseagent", 4)) {
            Log.i("nf_service_browseagent", "Request to prefetchGenre  LoLoMo");
        }
        if (!this.needToPrefetchGenre(s)) {
            browseAgentCallback.onGenreLoLoMoPrefetched(0);
            return;
        }
        this.launchTask(new PrefetchGenreLoLoMoTask(s, n, n2, n3, n4, b, browseAgentCallback));
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int mPrefetchFromVideo, final int mPrefetchToVideo, final int mPrefetchFromCWVideo, final int mPrefetchToCWVideo, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("nf_service_browseagent", 4)) {
            Log.i("nf_service_browseagent", "Request to prefetch LoLoMo");
        }
        if (!this.needToPrefetch()) {
            browseAgentCallback.onLoLoMoPrefetched(0);
            return;
        }
        BrowseAgent.mPrefetchToCWVideo = mPrefetchToCWVideo;
        BrowseAgent.mPrefetchFromCWVideo = mPrefetchFromCWVideo;
        BrowseAgent.mPrefetchToVideo = mPrefetchToVideo;
        BrowseAgent.mPrefetchFromVideo = mPrefetchFromVideo;
        this.launchTask(new PrefetchLoLoMoTask(n, n2, mPrefetchFromVideo, mPrefetchToVideo, mPrefetchFromCWVideo, mPrefetchToCWVideo, b, b2, browseAgentCallback));
    }
    
    public void refreshCW() {
        Log.v("nf_service_browseagent", "Refreshing CW data from server...");
        this.launchTask(new RefreshCWTask());
    }
    
    public void refreshEpisodesData(final Asset asset) {
        Log.d("nf_bookmark", "Refreshing episodes data");
        final String playableId = asset.getPlayableId();
        final boolean episode = asset.isEpisode();
        final String parentId = asset.getParentId();
        if (StringUtils.isEmpty(playableId) || (episode && StringUtils.isEmpty(parentId))) {
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("populate cache - parentId %s or videoId %s null - skip!", parentId, playableId));
            }
        }
        else if (!episode) {
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("populate cache - parentId %s or videoId %s is Movie - skip!", parentId, playableId));
            }
        }
        else {
            final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)getFromCaches(this.hardCache, this.softCache, buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, parentId, "0", "0"));
            if (showDetails == null || showDetails.currentEpisode == null) {
                Log.w("nf_bookmark", String.format("populate cache - parentId %s or videoId %s null - SDP missing skip!", parentId, playableId));
                return;
            }
            final String seasonId = showDetails.currentEpisode.getSeasonId();
            final int currentEpisodeNumber = showDetails.getCurrentEpisodeNumber();
            final int n = currentEpisodeNumber / 40 * 40;
            final int n2 = n + 40 - 1;
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("populate cache - Show %s, ce %s seasonId %s ceNum %d, reqStart %d reqEnd %d", parentId, playableId, seasonId, currentEpisodeNumber, n, n2));
            }
            this.fetchEpisodes(seasonId, n, n2, new SimpleBrowseAgentCallback() {
                @Override
                public void onEpisodesFetched(final List<EpisodeDetails> list, final int n) {
                    if (Log.isLoggable("nf_service_browseagent", 3)) {
                        Log.d("nf_bookmark", String.format("populate cache - onEpisodesFetched statusCode %d", n));
                    }
                }
            });
        }
    }
    
    public void refreshKidsCharacterDetail(final String s, final BrowseAgentCallback browseAgentCallback) {
        Log.d("nf_service_browseagent", "refreshKidsCharacterDetail id:" + s);
        final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetailsFromCache = getKidsCharacterDetailsFromCache(s, this.hardCache, this.softCache);
        if (kidsCharacterDetailsFromCache == null) {
            Log.d("nf_service_browseagent", String.format("refreshKidsCharacterDetail id:%s, cache null - skip", s));
            browseAgentCallback.onKidsCharacterDetailsFetched(kidsCharacterDetailsFromCache, true, 0);
            return;
        }
        this.launchTask(new RefreshKidsCharacterDetailsTask(s, browseAgentCallback));
    }
    
    public void removeFromQueue(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new RemoveFromQueueTask(s, browseAgentCallback));
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new SearchNetflixTask(s, browseAgentCallback));
    }
    
    public void sendEpisodeRefreshBrodcast(final int n, final int n2) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH");
        intent.putExtra("curSeasonNumber", n);
        intent.putExtra("curEpisodeNumber", n2);
        this.getContext().sendBroadcast(intent);
        Log.v("nf_service_browseagent", "Intent EPISODE_REFRESH sent");
    }
    
    public void sendHomeRefreshBrodcast() {
        this.getContext().sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        Log.v("nf_service_browseagent", "Intent REFRESH_HOME sent");
    }
    
    public void setVideoRating(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new SetVideoRatingTask(s, n, n2, browseAgentCallback));
    }
    
    public void updateCachedCwData(final Asset asset) {
        Log.v("nf_service_browseagent", "Updating cached CW data with asset: " + asset);
        this.refreshCacheWithLastPlayed(asset);
        this.refreshCW();
    }
    
    public void updateCachedVideoPosition(final Asset asset) {
        final StringBuilder append = new StringBuilder().append("Updating video positions for asset: ");
        String title;
        if (asset == null) {
            title = "null";
        }
        else {
            title = asset.getTitle();
        }
        Log.v("nf_service_browseagent", append.append(title).toString());
        this.refreshCacheWithLastPlayed(asset);
    }
    
    protected void updateEpisodeWithLatestShowInformation(final HardCache hardCache, final SoftCache softCache, final EpisodeDetails episodeDetails) {
        final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails2 = (com.netflix.mediaclient.service.webclient.model.EpisodeDetails)episodeDetails;
        final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)getFromCaches(hardCache, softCache, buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, episodeDetails.getShowId(), "0", "0"));
        if (showDetails != null && episodeDetails2.detail != null) {
            if (showDetails.rating != null) {
                episodeDetails2.rating = showDetails.rating;
            }
            if (showDetails.socialEvidence != null) {
                episodeDetails2.showSocialEvidence = showDetails.socialEvidence;
            }
            if (showDetails.detail != null) {
                episodeDetails2.detail.predictedRating = showDetails.detail.predictedRating;
            }
        }
    }
    
    private class AddToQueueTask extends FetchTask<Void>
    {
        private final boolean iqInCache;
        private final int trackId;
        private final BrowseAgentCallback webClientCallback;
        
        public AddToQueueTask(final String s, final int trackId, final BrowseAgentCallback browseAgentCallback) {
            super(s, BrowseAgent.mPrefetchFromVideo, BrowseAgent.mPrefetchToVideo, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onQueueAdd(final int n) {
                    if (n == 0) {
                        if (AddToQueueTask.this.iqInCache) {
                            BrowseAgent.this.sendIqRefreshBrodcast();
                        }
                        LogUtils.reportAddToQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.success, null, null);
                    }
                    else {
                        LogUtils.reportAddToQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.failed, new LogUtils.LogReportErrorArgs(n, ActionOnUIError.displayedError, "", null).getError(), null);
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)AddToQueueTask.this).getCallback().onQueueAdd(n);
                        }
                    });
                }
            };
            this.trackId = trackId;
            this.iqInCache = BrowseAgent.this.areIqIdsInCache();
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.addToQueue(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.trackId, this.iqInCache, this.webClientCallback);
        }
    }
    
    public enum BillboardActivityType
    {
        ACTION("action"), 
        IMPRESSION("impression");
        
        private final String name;
        
        private BillboardActivityType(final String name) {
            this.name = name;
        }
        
        public String getName() {
            return this.name;
        }
    }
    
    private abstract class CachedFetchTask<T> extends FetchTask<T>
    {
        private static final String TAG = "CachedFetchTask";
        private final String cacheKey;
        private T cacheVal;
        private final BrowseCache primaryCache;
        private final BrowseCache secondaryCache;
        
        public CachedFetchTask(final BrowseAgent browseAgent, final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
            this(browseAgent, s, n, n2, browseAgentCallback, true);
        }
        
        public CachedFetchTask(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super(s, n, n2, browseAgentCallback);
            Object primaryCache;
            if (b) {
                primaryCache = BrowseAgent.this.softCache;
            }
            else {
                primaryCache = BrowseAgent.this.hardCache;
            }
            this.primaryCache = (BrowseCache)primaryCache;
            Object secondaryCache;
            if (b) {
                secondaryCache = BrowseAgent.this.hardCache;
            }
            else {
                secondaryCache = BrowseAgent.this.softCache;
            }
            this.secondaryCache = (BrowseCache)secondaryCache;
            this.cacheKey = BrowseAgent.buildBrowseCacheKey(getCacheKeyFromClassName(this.getClass()), s, String.valueOf(n), String.valueOf(n2));
        }
        
        protected String getCacheKey() {
            return this.cacheKey;
        }
        
        protected T getCachedValue() {
            this.cacheVal = (T)this.primaryCache.get(this.cacheKey);
            if (this.cacheVal == null) {
                this.cacheVal = (T)this.secondaryCache.get(this.cacheKey);
            }
            return this.cacheVal;
        }
        
        protected void updateCacheIfNecessary(final T t, final int n) {
            if (this.cacheVal == null && n == 0) {
                this.primaryCache.put(this.cacheKey, t);
            }
        }
    }
    
    private class FetchCWVideosTask extends CachedFetchTask<List<com.netflix.mediaclient.servicemgr.CWVideo>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchCWVideosTask(final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super("continueWatching", n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onCWVideosFetched(final List<com.netflix.mediaclient.servicemgr.CWVideo> list, final int n) {
                    ((CachedFetchTask<List<com.netflix.mediaclient.servicemgr.CWVideo>>)FetchCWVideosTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchCWVideosTask.this).getCallback().onCWVideosFetched(list, n);
                            if (-64 == n) {
                                Log.e("nf_service_browseagent", "Map cache miss for CW - refresh");
                                BrowseAgent.this.sendHomeRefreshBrodcast();
                            }
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<com.netflix.mediaclient.servicemgr.CWVideo> list = ((CachedFetchTask<List<com.netflix.mediaclient.servicemgr.CWVideo>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.cwKeysCache.add(((CachedFetchTask)this).getCacheKey());
                BrowseAgent.this.mBrowseWebClient.fetchCWVideos(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onCWVideosFetched(list, 0);
        }
    }
    
    private class FetchEpisodeDetailsTask extends CachedFetchTask<EpisodeDetails>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchEpisodeDetailsTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final int n) {
                    ((CachedFetchTask<EpisodeDetails>)FetchEpisodeDetailsTask.this).updateCacheIfNecessary(episodeDetails, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (n == 0 && episodeDetails != null) {
                                BrowseAgent.this.updateEpisodeWithLatestShowInformation(BrowseAgent.this.hardCache, BrowseAgent.this.softCache, episodeDetails);
                            }
                            ((FetchTask)FetchEpisodeDetailsTask.this).getCallback().onEpisodeDetailsFetched(episodeDetails, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final EpisodeDetails episodeDetails = ((CachedFetchTask<EpisodeDetails>)this).getCachedValue();
            if (episodeDetails != null) {
                BrowseAgent.this.updateEpisodeWithLatestShowInformation(BrowseAgent.this.hardCache, BrowseAgent.this.softCache, episodeDetails);
                this.webClientCallback.onEpisodeDetailsFetched(episodeDetails, 0);
                return;
            }
            final String access$2300 = buildEpisodeDetailsCacheKey(((FetchTask)this).getCategory());
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", "looking for episode with key: " + access$2300);
            }
            final WeakReference weakReference = (WeakReference)BrowseAgent.this.weakEpisodesCache.get(access$2300);
            if (weakReference != null && weakReference.get() != null) {
                final EpisodeDetails episodeDetails2 = weakReference.get();
                BrowseAgent.this.updateEpisodeWithLatestShowInformation(BrowseAgent.this.hardCache, BrowseAgent.this.softCache, episodeDetails2);
                this.webClientCallback.onEpisodeDetailsFetched(episodeDetails2, 0);
                return;
            }
            BrowseAgent.this.mBrowseWebClient.fetchEpisodeDetails(((FetchTask)this).getCategory(), this.webClientCallback);
        }
    }
    
    private class FetchEpisodesTask extends CachedFetchTask<List<EpisodeDetails>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchEpisodesTask(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
            super(s, n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onEpisodesFetched(final List<EpisodeDetails> list, final int n) {
                    ((CachedFetchTask<List<EpisodeDetails>>)FetchEpisodesTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (n == 0 && list != null) {
                                BrowseAgent.this.putInWeakEpisodesCache(list);
                                BrowseAgent.this.updateEpisodesWithLatestShowInformation(BrowseAgent.this.hardCache, BrowseAgent.this.softCache, list);
                            }
                            ((FetchTask)FetchEpisodesTask.this).getCallback().onEpisodesFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<EpisodeDetails> list = ((CachedFetchTask<List<EpisodeDetails>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchEpisodes(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            BrowseAgent.this.updateEpisodesWithLatestShowInformation(BrowseAgent.this.hardCache, BrowseAgent.this.softCache, list);
            this.webClientCallback.onEpisodesFetched(list, 0);
        }
    }
    
    private class FetchGenreListsTask extends CachedFetchTask<List<GenreList>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchGenreListsTask(final BrowseAgentCallback browseAgentCallback) {
            super("genreList", 0, 0, browseAgentCallback, false);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onGenreListsFetched(final List<GenreList> list, final int n) {
                    ((CachedFetchTask<List<GenreList>>)FetchGenreListsTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchGenreListsTask.this).getCallback().onGenreListsFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<GenreList> list = ((CachedFetchTask<List<GenreList>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchGenreLists(this.webClientCallback);
                return;
            }
            this.webClientCallback.onGenreListsFetched(list, 0);
        }
    }
    
    private class FetchGenreVideosTask extends CachedFetchTask<List<Video>>
    {
        private final LoMo lomo;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchGenreVideosTask(final LoMo lomo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
            super(lomo.getId(), n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideosFetched(final List<Video> list, final int n) {
                    ((CachedFetchTask<List<Video>>)FetchGenreVideosTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchGenreVideosTask.this).getCallback().onVideosFetched(list, n);
                        }
                    });
                }
            };
            this.lomo = lomo;
        }
        
        @Override
        public void run() {
            final List<Video> list = ((CachedFetchTask<List<Video>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchGenreVideos(this.lomo, ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onVideosFetched(list, 0);
        }
    }
    
    private class FetchGenresTask extends CachedFetchTask<List<Genre>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchGenresTask(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
            super(s, n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onGenresFetched(final List<Genre> list, final int n) {
                    ((CachedFetchTask<List<Genre>>)FetchGenresTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchGenresTask.this).getCallback().onGenresFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<Genre> list = ((CachedFetchTask<List<Genre>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchGenres(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onGenresFetched(list, 0);
        }
    }
    
    private class FetchIQVideosTask extends CachedFetchTask<List<Video>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchIQVideosTask(final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super("queue", n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideosFetched(final List<Video> list, final int n) {
                    ((CachedFetchTask<List<Video>>)FetchIQVideosTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchIQVideosTask.this).getCallback().onVideosFetched(list, n);
                            if (-64 == n) {
                                Log.d("nf_service_browseagent", "Map cache miss for IQ - refresh");
                                BrowseAgent.this.sendHomeRefreshBrodcast();
                            }
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<Video> list = ((CachedFetchTask<List<Video>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.iqKeysCache.add(((CachedFetchTask)this).getCacheKey());
                BrowseAgent.this.mBrowseWebClient.fetchIQVideos(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onVideosFetched(list, 0);
        }
    }
    
    private class FetchKidsCharacterDetailsTask extends CachedFetchTask<KidsCharacterDetails>
    {
        String mCharacterId;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchKidsCharacterDetailsTask(final String mCharacterId, final BrowseAgentCallback browseAgentCallback) {
            super(mCharacterId, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final int n) {
                    ((CachedFetchTask<KidsCharacterDetails>)FetchKidsCharacterDetailsTask.this).updateCacheIfNecessary(kidsCharacterDetails, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchKidsCharacterDetailsTask.this).getCallback().onKidsCharacterDetailsFetched(kidsCharacterDetails, b, n);
                        }
                    });
                }
            };
            this.mCharacterId = mCharacterId;
        }
        
        @Override
        public void run() {
            if (((CachedFetchTask<KidsCharacterDetails>)this).getCachedValue() == null) {
                BrowseAgent.this.mBrowseWebClient.fetchKidsCharacterDetails(((FetchTask)this).getCategory(), this.webClientCallback);
                return;
            }
            BrowseAgent.this.refreshKidsCharacterDetail(this.mCharacterId, this.webClientCallback);
        }
    }
    
    private class FetchLoLoMoSummaryTask extends CachedFetchTask<LoLoMo>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchLoLoMoSummaryTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final int n) {
                    ((CachedFetchTask<LoLoMo>)FetchLoLoMoSummaryTask.this).updateCacheIfNecessary(loLoMo, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchLoLoMoSummaryTask.this).getCallback().onLoLoMoSummaryFetched(loLoMo, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final LoLoMo loLoMo = ((CachedFetchTask<LoLoMo>)this).getCachedValue();
            if (loLoMo == null) {
                BrowseAgent.this.mBrowseWebClient.fetchLoLoMoSummary(((FetchTask)this).getCategory(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onLoLoMoSummaryFetched(loLoMo, 0);
        }
    }
    
    private class FetchLoMosTask extends CachedFetchTask<List<LoMo>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchLoMosTask(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super(s, n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onLoMosFetched(final List<LoMo> list, final int n) {
                    ((CachedFetchTask<List<LoMo>>)FetchLoMosTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchLoMosTask.this).getCallback().onLoMosFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<LoMo> list = ((CachedFetchTask<List<LoMo>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchLoMos(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onLoMosFetched(list, 0);
        }
    }
    
    private class FetchMovieDetailsTask extends CachedFetchTask<com.netflix.mediaclient.servicemgr.MovieDetails>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchMovieDetailsTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onMovieDetailsFetched(final com.netflix.mediaclient.servicemgr.MovieDetails movieDetails, final int n) {
                    ((CachedFetchTask<com.netflix.mediaclient.servicemgr.MovieDetails>)FetchMovieDetailsTask.this).updateCacheIfNecessary(movieDetails, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchMovieDetailsTask.this).getCallback().onMovieDetailsFetched(movieDetails, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final com.netflix.mediaclient.servicemgr.MovieDetails movieDetails = ((CachedFetchTask<com.netflix.mediaclient.servicemgr.MovieDetails>)this).getCachedValue();
            if (movieDetails == null) {
                BrowseAgent.this.mBrowseWebClient.fetchMovieDetails(((FetchTask)this).getCategory(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onMovieDetailsFetched(movieDetails, 0);
        }
    }
    
    private class FetchPostPlayTask extends FetchTask<PostPlayVideo>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchPostPlayTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final int n) {
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchPostPlayTask.this).getCallback().onPostPlayVideosFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.fetchPostPlayVideos(((FetchTask)this).getCategory(), this.webClientCallback);
        }
    }
    
    private class FetchSeasonDetailsTask extends CachedFetchTask<SeasonDetails>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchSeasonDetailsTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final int n) {
                    ((CachedFetchTask<SeasonDetails>)FetchSeasonDetailsTask.this).updateCacheIfNecessary(seasonDetails, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchSeasonDetailsTask.this).getCallback().onSeasonDetailsFetched(seasonDetails, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final SeasonDetails seasonDetails = ((CachedFetchTask<SeasonDetails>)this).getCachedValue();
            if (seasonDetails != null) {
                this.webClientCallback.onSeasonDetailsFetched(seasonDetails, 0);
                return;
            }
            final WeakReference weakReference = (WeakReference)BrowseAgent.this.weakSeasonsCache.get(buildSeasonDetailsCacheKey(((FetchTask)this).getCategory()));
            if (weakReference != null && weakReference.get() != null) {
                this.webClientCallback.onSeasonDetailsFetched(weakReference.get(), 0);
                return;
            }
            BrowseAgent.this.mBrowseWebClient.fetchSeasonDetails(((FetchTask)this).getCategory(), this.webClientCallback);
        }
    }
    
    private class FetchSeasonsTask extends CachedFetchTask<List<SeasonDetails>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchSeasonsTask(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
            super(s, n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSeasonsFetched(final List<SeasonDetails> list, final int n) {
                    ((CachedFetchTask<List<SeasonDetails>>)FetchSeasonsTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (n == 0 && list != null) {
                                BrowseAgent.this.putInWeakSeasonsCache(list);
                            }
                            ((FetchTask)FetchSeasonsTask.this).getCallback().onSeasonsFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<SeasonDetails> list = ((CachedFetchTask<List<SeasonDetails>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchSeasons(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            BrowseAgent.this.putInWeakSeasonsCache(list);
            this.webClientCallback.onSeasonsFetched(list, 0);
        }
    }
    
    private class FetchShowDetailsTask extends CachedFetchTask<ShowDetails>
    {
        private final String mCurrentEpisodeId;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchShowDetailsTask(final String s, final String mCurrentEpisodeId, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
                    if (n == 0) {
                        if (StringUtils.isEmpty(FetchShowDetailsTask.this.mCurrentEpisodeId)) {
                            ((CachedFetchTask<ShowDetails>)FetchShowDetailsTask.this).updateCacheIfNecessary(showDetails, n);
                        }
                        else {
                            BrowseAgent.this.updateSeasonWithSdp(showDetails);
                        }
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchShowDetailsTask.this).getCallback().onShowDetailsFetched(showDetails, n);
                        }
                    });
                }
            };
            this.mCurrentEpisodeId = mCurrentEpisodeId;
        }
        
        @Override
        public void run() {
            final ShowDetails showDetails = ((CachedFetchTask<ShowDetails>)this).getCachedValue();
            if (showDetails == null || StringUtils.isNotEmpty(this.mCurrentEpisodeId)) {
                BrowseAgent.this.mBrowseWebClient.fetchShowDetails(((FetchTask)this).getCategory(), this.mCurrentEpisodeId, this.webClientCallback);
                return;
            }
            this.webClientCallback.onShowDetailsFetched(showDetails, 0);
        }
    }
    
    private class FetchSimilarVideosForPersonTask extends CachedFetchTask<VideoList>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchSimilarVideosForPersonTask(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, n, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSimilarVideosFetched(final VideoList list, final int n) {
                    ((CachedFetchTask<VideoList>)FetchSimilarVideosForPersonTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchSimilarVideosForPersonTask.this).getCallback().onSimilarVideosFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final VideoList list = ((CachedFetchTask<VideoList>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchSimilarVideosForPerson(((FetchTask)this).getCategory(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onSimilarVideosFetched(list, 0);
        }
    }
    
    private class FetchSimilarVideosForQuerySuggestionTask extends CachedFetchTask<VideoList>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchSimilarVideosForQuerySuggestionTask(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, n, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSimilarVideosFetched(final VideoList list, final int n) {
                    ((CachedFetchTask<VideoList>)FetchSimilarVideosForQuerySuggestionTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchSimilarVideosForQuerySuggestionTask.this).getCallback().onSimilarVideosFetched(list, n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final VideoList list = ((CachedFetchTask<VideoList>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchSimilarVideosForQuerySuggestion(((FetchTask)this).getCategory(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onSimilarVideosFetched(list, 0);
        }
    }
    
    private abstract static class FetchTask<T> implements Runnable
    {
        private final BrowseAgentCallback callback;
        private final String category;
        private final int from;
        private final int to;
        
        public FetchTask(final String category, final int from, final int to, final BrowseAgentCallback callback) {
            this.category = category;
            this.from = from;
            this.to = to;
            this.callback = callback;
        }
        
        protected BrowseAgentCallback getCallback() {
            return this.callback;
        }
        
        protected String getCategory() {
            return this.category;
        }
        
        protected int getFromIndex() {
            return this.from;
        }
        
        protected int getToIndex() {
            return this.to;
        }
    }
    
    private class FetchVideosTask extends CachedFetchTask<List<Video>>
    {
        private final LoMo lomo;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchVideosTask(final LoMo lomo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super(lomo.getId(), n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideosFetched(final List<Video> list, final int n) {
                    ((CachedFetchTask<List<Video>>)FetchVideosTask.this).updateCacheIfNecessary(list, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchVideosTask.this).getCallback().onVideosFetched(list, n);
                            if (-64 == n) {
                                Log.e("nf_service_browseagent", "Map cache miss on FetchVideos - refresh");
                                BrowseAgent.this.sendHomeRefreshBrodcast();
                            }
                        }
                    });
                }
            };
            this.lomo = lomo;
        }
        
        @Override
        public void run() {
            final List<Video> list = ((CachedFetchTask<List<Video>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchVideos(this.lomo, ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onVideosFetched(list, 0);
        }
    }
    
    private class HideVideoTask extends FetchTask<Void>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public HideVideoTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideoHide(final int n) {
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)HideVideoTask.this).getCallback().onVideoHide(n);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.hideVideo(((FetchTask)this).getCategory(), this.webClientCallback);
        }
    }
    
    private class LogBillboardActivityTask implements Runnable
    {
        private final BillboardActivityType type;
        private final Video video;
        
        public LogBillboardActivityTask(final Video video, final BillboardActivityType type) {
            this.video = video;
            this.type = type;
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.logBillboardActivity(this.video, this.type);
        }
    }
    
    public final class PlayReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if (intent != null) {
                final String action = intent.getAction();
                Log.i("nf_bookmark", "PlayReceiver inovoked and received Intent with Action " + action);
                if (canDoDataFetches()) {
                    if ("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP".equals(action)) {
                        Log.i("nf_bookmark", "Refreshing CW for LOCAL_PLAYER_PLAY_STOP...");
                        BrowseAgent.this.updateCachedCwData(Asset.fromIntent(intent));
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START".equals(action)) {
                        BrowseAgent.this.refreshEpisodesData(Asset.fromIntent(intent));
                    }
                }
            }
        }
    }
    
    private class PrefetchGenreLoLoMoTask extends FetchTask<List<PrefetchVideoList>>
    {
        final int mFromLoMo;
        final boolean mIncludeBoxshots;
        final int mToLoMo;
        private final BrowseAgentCallback webClientCallback;
        
        public PrefetchGenreLoLoMoTask(final String s, final int mFromLoMo, final int mToLoMo, final int n, final int n2, final boolean mIncludeBoxshots, final BrowseAgentCallback browseAgentCallback) {
            super(s, n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onGenreLoLoMoPrefetched(final int n) {
                    if (PrefetchGenreLoLoMoTask.this.mIncludeBoxshots) {
                        throw new RuntimeException("Unimplemented exception");
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)PrefetchGenreLoLoMoTask.this).getCallback().onGenreLoLoMoPrefetched(n);
                        }
                    });
                }
            };
            this.mFromLoMo = mFromLoMo;
            this.mToLoMo = mToLoMo;
            this.mIncludeBoxshots = mIncludeBoxshots;
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.prefetchGenreLoLoMo(((FetchTask)this).getCategory(), this.mFromLoMo, this.mToLoMo, ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
        }
    }
    
    private class PrefetchLoLoMoTask extends FetchTask<List<PrefetchVideoList>>
    {
        final int mFromCWVideo;
        final int mFromLoMo;
        final boolean mIncludeBoxshots;
        final boolean mIncludeExtraCharacters;
        final int mToCWVideo;
        final int mToLoMo;
        private final BrowseAgentCallback webClientCallback;
        
        public PrefetchLoLoMoTask(final int mFromLoMo, final int mToLoMo, final int n, final int n2, final int mFromCWVideo, final int mToCWVideo, final boolean mIncludeExtraCharacters, final boolean mIncludeBoxshots, final BrowseAgentCallback browseAgentCallback) {
            super("lolomo", n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onLoLoMoPrefetched(final int n) {
                    if (PrefetchLoLoMoTask.this.mIncludeBoxshots) {
                        throw new RuntimeException("Unimplemented exception");
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)PrefetchLoLoMoTask.this).getCallback().onLoLoMoPrefetched(n);
                        }
                    });
                }
            };
            this.mFromLoMo = mFromLoMo;
            this.mToLoMo = mToLoMo;
            this.mFromCWVideo = mFromCWVideo;
            this.mToCWVideo = mToCWVideo;
            this.mIncludeBoxshots = mIncludeBoxshots;
            this.mIncludeExtraCharacters = mIncludeExtraCharacters;
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.prefetchLoLoMo(((FetchTask)this).getCategory(), this.mFromLoMo, this.mToLoMo, ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.mFromCWVideo, this.mToCWVideo, this.mIncludeExtraCharacters, this.webClientCallback);
        }
    }
    
    private class RefreshCWTask extends FetchTask<Void>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public RefreshCWTask() {
            super("refreshCW", BrowseAgent.mPrefetchFromCWVideo, BrowseAgent.mPrefetchToCWVideo, null);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onCWListRefresh(final int n) {
                    BrowseAgent.this.sendCwRefreshBrodcast();
                }
            };
        }
        
        @Override
        public void run() {
            if (BrowseAgent.this.canRefreshCW(BrowseAgent.this.hardCache) && BrowseAgent.this.cacheHasLolomoId(BrowseAgent.this.hardCache)) {
                BrowseAgent.this.mBrowseWebClient.refreshCWList(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            Log.d("nf_service_browseagent", "Cache has no CW item so doing nothing for CW refresh");
        }
    }
    
    private class RefreshKidsCharacterDetailsTask extends FetchTask<KidsCharacterDetails>
    {
        String mCharacterId;
        private final BrowseAgentCallback webClientCallback;
        
        public RefreshKidsCharacterDetailsTask(final String mCharacterId, final BrowseAgentCallback browseAgentCallback) {
            super(mCharacterId, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final int n) {
                    final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetailsFromCache = BrowseAgent.getKidsCharacterDetailsFromCache(RefreshKidsCharacterDetailsTask.this.mCharacterId, BrowseAgent.this.hardCache, BrowseAgent.this.softCache);
                    if (kidsCharacterDetailsFromCache == null) {
                        Log.w("nf_service_browseagent", "Cached character details are null, can't refresh - charId: " + RefreshKidsCharacterDetailsTask.this.mCharacterId);
                        return;
                    }
                    final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails2 = (com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails)kidsCharacterDetails;
                    final Boolean access$2000 = BrowseAgent.this.isKidsCharacterDetailsNew(kidsCharacterDetailsFromCache, kidsCharacterDetails);
                    if (b && kidsCharacterDetailsFromCache != null) {
                        kidsCharacterDetailsFromCache.updateWatchNext(kidsCharacterDetails2);
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)RefreshKidsCharacterDetailsTask.this).getCallback().onKidsCharacterDetailsFetched(kidsCharacterDetailsFromCache, access$2000, n);
                        }
                    });
                }
            };
            this.mCharacterId = mCharacterId;
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.refreshKidsCharacterDetils(((FetchTask)this).getCategory(), this.webClientCallback);
        }
    }
    
    private class RemoveFromQueueTask extends FetchTask<Void>
    {
        private final boolean iqInCache;
        private final BrowseAgentCallback webClientCallback;
        
        public RemoveFromQueueTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, BrowseAgent.mPrefetchFromVideo, BrowseAgent.mPrefetchToVideo, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onQueueRemove(final int n) {
                    if (n == 0) {
                        if (RemoveFromQueueTask.this.iqInCache) {
                            BrowseAgent.this.sendIqRefreshBrodcast();
                        }
                        LogUtils.reportRemoveFromQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.success, null);
                    }
                    else {
                        LogUtils.reportRemoveFromQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.failed, new LogUtils.LogReportErrorArgs(n, ActionOnUIError.displayedError, "", null).getError());
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)RemoveFromQueueTask.this).getCallback().onQueueRemove(n);
                        }
                    });
                }
            };
            this.iqInCache = BrowseAgent.this.areIqIdsInCache();
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.removeFromQueue(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.iqInCache, this.webClientCallback);
        }
    }
    
    private class SearchNetflixTask extends CachedFetchTask<SearchResults>
    {
        private final BrowseAgentCallback callback;
        private final String query;
        private final BrowseAgentCallback webClientCallback;
        
        public SearchNetflixTask(final String s, final BrowseAgentCallback callback) {
            super(s, 0, 0, callback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSearchResultsFetched(final SearchResults searchResults, final int n) {
                    ((CachedFetchTask<SearchResults>)SearchNetflixTask.this).updateCacheIfNecessary(searchResults, n);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            SearchNetflixTask.this.callback.onSearchResultsFetched(searchResults, n);
                        }
                    });
                }
            };
            this.query = this.sanitizeQuery(s);
            this.callback = callback;
        }
        
        private String sanitizeQuery(final String s) {
            final String replaceAll = s.replaceAll("\\s+", " ");
            if (Log.isLoggable("nf_service_browseagent", 2)) {
                Log.v("nf_service_browseagent", "Sanitized query from: \"" + s + "\" to \"" + replaceAll + "\"");
            }
            return replaceAll;
        }
        
        @Override
        public void run() {
            final SearchResults searchResults = ((CachedFetchTask<SearchResults>)this).getCachedValue();
            if (searchResults == null) {
                ProfileType profileType = ProfileType.STANDARD;
                final UserProfile currentProfile = BrowseAgent.this.getUserAgent().getCurrentProfile();
                if (currentProfile != null) {
                    profileType = currentProfile.getProfileType();
                }
                BrowseAgent.this.mBrowseWebClient.searchNetflix(this.query, profileType, this.webClientCallback);
                return;
            }
            this.webClientCallback.onSearchResultsFetched(searchResults, 0);
        }
    }
    
    private class SetVideoRatingTask extends CachedFetchTask<Void>
    {
        private final int newRating;
        private final int trackId;
        private final BrowseAgentCallback webClientCallback;
        
        public SetVideoRatingTask(final String s, final int newRating, final int trackId, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideoRatingSet(final int n) {
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)SetVideoRatingTask.this).getCallback().onVideoRatingSet(n);
                        }
                    });
                }
            };
            this.newRating = newRating;
            this.trackId = trackId;
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.setVideoRating(((FetchTask)this).getCategory(), this.newRating, this.trackId, this.webClientCallback);
        }
    }
}
