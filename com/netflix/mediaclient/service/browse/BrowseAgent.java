// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.servicemgr.model.user.ProfileType;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.service.browse.cache.BrowseCache;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.model.CWVideo;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Random;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import android.os.Handler;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent;

public class BrowseAgent extends ServiceAgent implements BrowseAgentInterface
{
    public static final String BOOKMARK_TAG = "nf_bookmark";
    public static final String CACHE_KEY_PREFIX_BB_VIDEOS;
    public static final String CACHE_KEY_PREFIX_CW_VIDEOS;
    public static final String CACHE_KEY_PREFIX_EPISODE_DETAILS;
    public static final String CACHE_KEY_PREFIX_GENRE_LOMO;
    public static final String CACHE_KEY_PREFIX_GENRE_VIDEOS;
    public static final String CACHE_KEY_PREFIX_IQ_VIDEOS;
    public static final String CACHE_KEY_PREFIX_KIDS_CDP;
    public static final String CACHE_KEY_PREFIX_LOMO;
    public static final String CACHE_KEY_PREFIX_MDP;
    public static final String CACHE_KEY_PREFIX_SDP;
    public static final String CACHE_KEY_PREFIX_SEASON_DETAILS;
    public static final String CACHE_KEY_PREFIX_VIDEOS;
    private static final long EPISODE_RATE_LIMIT_DELAY = 30000L;
    private static final int MARGIN_FOR_BOOKMARK_RESET_SECONDS = 30;
    private static final int PREFETCH_BILLBOARD_VIDEO_INDEX = 9;
    private static final String TAG = "nf_service_browseagent";
    private static final boolean USE_HARD_CACHE_CONST = false;
    private static final boolean USE_SOFT_CACHE_CONST = true;
    private static boolean isCurrentProfileActive;
    private static int mPrefetchFromCWVideo;
    private static int mPrefetchFromVideo;
    private static int mPrefetchToCWVideo;
    private static int mPrefetchToVideo;
    private static long mPrevEpisodeRefreshTime;
    private DataDumper dataDumper;
    private BrowseWebClient mBrowseWebClient;
    private BrowseWebClientCache mCache;
    private final PlayReceiver mPlayReceiver;
    public final BroadcastReceiver mUserAgentIntentReceiver;
    
    static {
        CACHE_KEY_PREFIX_LOMO = getCacheKeyFromClassName(FetchLoMosTask.class);
        CACHE_KEY_PREFIX_MDP = getCacheKeyFromClassName(FetchMovieDetailsTask.class);
        CACHE_KEY_PREFIX_SDP = getCacheKeyFromClassName(FetchShowDetailsTask.class);
        CACHE_KEY_PREFIX_EPISODE_DETAILS = getCacheKeyFromClassName(FetchEpisodeDetailsTask.class);
        CACHE_KEY_PREFIX_GENRE_LOMO = getCacheKeyFromClassName(FetchGenresTask.class);
        CACHE_KEY_PREFIX_CW_VIDEOS = getCacheKeyFromClassName(FetchCWVideosTask.class);
        CACHE_KEY_PREFIX_BB_VIDEOS = getCacheKeyFromClassName(FetchBillboardVideosTask.class);
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
    
    private static boolean canDoDataFetches() {
        if (!BrowseAgent.isCurrentProfileActive) {
            Log.d("nf_service_browseagent", "wrong state - canDoDataFetches false - skipping browse request");
        }
        return BrowseAgent.isCurrentProfileActive;
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
    
    private static String getCacheKeyFromClassName(final Class<?> clazz) {
        return clazz.getSimpleName();
    }
    
    private com.netflix.mediaclient.service.webclient.model.EpisodeDetails getNextPlayableEpisode(com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails, final long lastModified) {
        if (computePlayPos(episodeDetails.getBookmarkPosition(), episodeDetails.getEndtime(), episodeDetails.getRuntime()) == episodeDetails.getBookmarkPosition()) {
            return episodeDetails;
        }
        final String buildEpisodeDetailsCacheKey = BrowseWebClientCache.buildEpisodeDetailsCacheKey(episodeDetails.getNextEpisodeId());
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
        }
        final WeakReference weakReference = (WeakReference)this.mCache.getWeakEpisodesCache().get(buildEpisodeDetailsCacheKey);
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
        this.mCache = new BrowseWebClientCache();
        this.mBrowseWebClient = BrowseWebClientFactory.create(this.mCache, this.getService(), this.getResourceFetcher().getApiNextWebClient());
    }
    
    private Boolean isKidsCharacterDetailsNew(final KidsCharacterDetails kidsCharacterDetails, final KidsCharacterDetails kidsCharacterDetails2) {
        if (Log.isLoggable("nf_service_browseagent", 3)) {
            final String characterId = kidsCharacterDetails.getCharacterId();
            final String playableId = kidsCharacterDetails.getPlayable().getPlayableId();
            String playableId2;
            if (kidsCharacterDetails2 == null) {
                playableId2 = "n/a";
            }
            else {
                playableId2 = kidsCharacterDetails2.getPlayable().getPlayableId();
            }
            Log.d("nf_service_browseagent", String.format("charId: %s, cachePlayableId:%s, newPlayableId:%s", characterId, playableId, playableId2));
        }
        if (kidsCharacterDetails2 == null) {
            return false;
        }
        return !StringUtils.safeEquals(kidsCharacterDetails.getPlayable().getPlayableId(), kidsCharacterDetails2.getPlayable().getPlayableId());
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
                com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails3;
                if (!episode) {
                    final MovieDetails movieDetails = (MovieDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, playableId, "0", "0"));
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
                    final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, parentId, "0", "0"));
                    if (showDetails == null) {
                        return;
                    }
                    final String buildEpisodeDetailsCacheKey = BrowseWebClientCache.buildEpisodeDetailsCacheKey(playableId);
                    if (Log.isLoggable("nf_service_browseagent", 3)) {
                        Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
                    }
                    final WeakReference weakReference = (WeakReference)this.mCache.getWeakEpisodesCache().get(buildEpisodeDetailsCacheKey);
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
                        updateShowOnEpisodePlay(showDetails, nextPlayableEpisode);
                        updateSeasonsInformation(this.mCache, nextPlayableEpisode.getSeasonId(), nextPlayableEpisode.getEpisodeNumber());
                        episodeDetails3 = nextPlayableEpisode;
                        if (!this.shouldRateLimitEpisodeRefresh()) {
                            this.sendEpisodeRefreshBrodcast(this.getContext(), nextPlayableEpisode.getSeasonNumber(), nextPlayableEpisode.getEpisodeNumber());
                            episodeDetails3 = nextPlayableEpisode;
                        }
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
                this.updateCwOnPlay(playableId, playbackBookmark, currentTimeMillis, episodeDetails3);
            }
        }
    }
    
    private void refreshKidsCharacterDetail(final String s, final BrowseAgentCallback browseAgentCallback) {
        Log.d("nf_service_browseagent", "refreshKidsCharacterDetail id:" + s);
        final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetailsFromCache = this.mCache.getKidsCharacterDetailsFromCache(s);
        if (kidsCharacterDetailsFromCache == null) {
            Log.d("nf_service_browseagent", String.format("refreshKidsCharacterDetail id:%s, cache null - skip", s));
            browseAgentCallback.onKidsCharacterDetailsFetched(kidsCharacterDetailsFromCache, true, CommonStatus.OK);
            return;
        }
        this.launchTask(new RefreshKidsCharacterDetailsTask(s, browseAgentCallback));
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
    
    private static void sendCwRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_CW_REFRESH"));
        Log.v("nf_service_browseagent", "Intent CW_REFRESH sent");
    }
    
    private void sendEpisodeRefreshBrodcast(final Context context, final int n, final int n2) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH");
        intent.putExtra("curSeasonNumber", n);
        intent.putExtra("curEpisodeNumber", n2);
        context.sendBroadcast(intent);
        Log.v("nf_service_browseagent", "Intent EPISODE_REFRESH sent");
    }
    
    private void sendHomeRefreshBrodcast() {
        this.getContext().sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        Log.v("nf_service_browseagent", "Intent REFRESH_HOME sent");
    }
    
    private static void sendIqRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH"));
        Log.v("nf_service_browseagent", "Intent IQ_REFRESH sent");
    }
    
    private boolean shouldRateLimitEpisodeRefresh() {
        Log.v("nf_service_browseagent", String.format("EPISODE_REFRESH ? %b,  delta: %d", System.currentTimeMillis() - BrowseAgent.mPrevEpisodeRefreshTime > 30000L, System.currentTimeMillis() - BrowseAgent.mPrevEpisodeRefreshTime));
        if (System.currentTimeMillis() - BrowseAgent.mPrevEpisodeRefreshTime < 30000L) {
            Log.d("nf_service_browseagent", String.format("skip BROWSE_AGENT_EPISODE_REFRESH was done in %d ", System.currentTimeMillis() - BrowseAgent.mPrevEpisodeRefreshTime));
            return true;
        }
        BrowseAgent.mPrevEpisodeRefreshTime = System.currentTimeMillis();
        return false;
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
        final List list = (List)this.mCache.getHardCache().get(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(BrowseAgent.mPrefetchFromCWVideo), String.valueOf(BrowseAgent.mPrefetchToCWVideo)));
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
    
    private void updateEpisodesWithLatestShowInformation(final List<EpisodeDetails> list) {
        if (list.size() != 0) {
            final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, list.get(0).getShowId(), "0", "0"));
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
            public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
                if (status.isSucces()) {
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
    
    public static void updateSeasonsInformation(final BrowseWebClientCache browseWebClientCache, final String s, final int currentEpisodeNumber) {
        final WeakReference weakReference = (WeakReference)browseWebClientCache.getWeakSeasonsCache().get(BrowseWebClientCache.buildSeasonDetailsCacheKey(s));
        if (weakReference != null) {
            final com.netflix.mediaclient.service.webclient.model.SeasonDetails seasonDetails = weakReference.get();
            seasonDetails.detail.currentEpisodeNumber = currentEpisodeNumber;
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", "updateSeasonsInformation: seasonId:" + s + " currentEpisode:" + seasonDetails.getCurrentEpisodeNumber());
            }
        }
    }
    
    private static void updateShowOnEpisodePlay(final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        showDetails.currentEpisode.deepCopy(episodeDetails.detail);
        showDetails.currentEpisodeBookmark.deepCopy(episodeDetails.bookmark);
    }
    
    public void addToQueue(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new AddToQueueTask(s, n, browseAgentCallback));
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
        this.dataDumper = new DataDumper(this.mBrowseWebClient, this.mCache.getHardCache(), this.mCache.getSoftCache());
        this.initCompleted(CommonStatus.OK);
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
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchIQVideosTask(n, n2, browseAgentCallback, true));
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchKidsCharacterDetailsTask(s, browseAgentCallback));
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchLoLoMoSummaryTask(s, browseAgentCallback));
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchLoMosTask(n, n2, browseAgentCallback, true));
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
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.launchTask(new FetchSimilarVideosForPersonTask(s, n, browseAgentCallback, s2));
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.launchTask(new FetchSimilarVideosForQuerySuggestionTask(s, n, browseAgentCallback, s2));
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (LoMoType.BILLBOARD.equals(loMo.getType())) {
            this.launchTask(new FetchBillboardVideosTask(loMo, n, n2, browseAgentCallback, true));
            return;
        }
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
        if (!this.mCache.needToPrefetchGenre(s)) {
            browseAgentCallback.onGenreLoLoMoPrefetched(CommonStatus.OK);
            return;
        }
        this.launchTask(new PrefetchGenreLoLoMoTask(s, n, n2, n3, n4, b, browseAgentCallback));
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int mPrefetchFromVideo, final int mPrefetchToVideo, final int mPrefetchFromCWVideo, final int mPrefetchToCWVideo, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        Log.i("nf_service_browseagent", "Request to prefetch LoLoMo");
        if (!this.mCache.needToPrefetch()) {
            browseAgentCallback.onLoLoMoPrefetched(CommonStatus.OK);
            return;
        }
        BrowseAgent.mPrefetchToCWVideo = mPrefetchToCWVideo;
        BrowseAgent.mPrefetchFromCWVideo = mPrefetchFromCWVideo;
        BrowseAgent.mPrefetchToVideo = mPrefetchToVideo;
        BrowseAgent.mPrefetchFromVideo = mPrefetchFromVideo;
        this.launchTask(new PrefetchLoLoMoTask(n2, mPrefetchToVideo, mPrefetchToCWVideo, 9, b, b2, browseAgentCallback));
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
            final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, parentId, "0", "0"));
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
                public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
                    if (Log.isLoggable("nf_service_browseagent", 3)) {
                        Log.d("nf_bookmark", String.format("populate cache - onEpisodesFetched res %d", status.getStatusCode().getValue()));
                    }
                }
            });
        }
    }
    
    public void refreshIQ() {
        Log.v("nf_service_browseagent", "Refreshing IQ data from server...");
        this.launchTask(new RefreshIQTask());
    }
    
    public void removeFromQueue(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new RemoveFromQueueTask(s, browseAgentCallback));
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new SearchNetflixTask(s, browseAgentCallback));
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
    
    protected void updateEpisodeWithLatestShowInformation(final EpisodeDetails episodeDetails) {
        final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails2 = (com.netflix.mediaclient.service.webclient.model.EpisodeDetails)episodeDetails;
        final com.netflix.mediaclient.service.webclient.model.ShowDetails showDetails = (com.netflix.mediaclient.service.webclient.model.ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, episodeDetails.getShowId(), "0", "0"));
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
                public void onQueueAdd(final Status status) {
                    if (status.isSucces()) {
                        if (AddToQueueTask.this.iqInCache) {
                            sendIqRefreshBrodcast(BrowseAgent.this.getContext());
                        }
                        LogUtils.reportAddToQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.success, null, null);
                    }
                    else {
                        LogUtils.reportAddToQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.failed, new LogUtils.LogReportErrorArgs(status, ActionOnUIError.displayedError, "", null).getError(), null);
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)AddToQueueTask.this).getCallback().onQueueAdd(status);
                        }
                    });
                }
            };
            this.trackId = trackId;
            this.iqInCache = BrowseAgent.this.mCache.areIqIdsInCache();
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
                primaryCache = BrowseAgent.this.mCache.getSoftCache();
            }
            else {
                primaryCache = BrowseAgent.this.mCache.getHardCache();
            }
            this.primaryCache = (BrowseCache)primaryCache;
            Object secondaryCache;
            if (b) {
                secondaryCache = BrowseAgent.this.mCache.getHardCache();
            }
            else {
                secondaryCache = BrowseAgent.this.mCache.getSoftCache();
            }
            this.secondaryCache = (BrowseCache)secondaryCache;
            this.cacheKey = BrowseWebClientCache.buildBrowseCacheKey(getCacheKeyFromClassName(this.getClass()), s, String.valueOf(n), String.valueOf(n2));
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
        
        protected void updateCacheIfNecessary(final T t, final Status status) {
            if (this.cacheVal == null && status.isSucces()) {
                if (Log.isLoggable("CachedFetchTask", 2)) {
                    Log.v("CachedFetchTask", "+ Putting in cache: " + this.cacheKey + ", hash: " + t.hashCode());
                }
                this.primaryCache.put(this.cacheKey, t);
            }
        }
    }
    
    private class FetchBillboardVideosTask extends CachedFetchTask<List<Billboard>>
    {
        private final LoMo lomo;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchBillboardVideosTask(final LoMo lomo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super("billboard", n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onBBVideosFetched(final List<Billboard> list, final Status status) {
                    ((CachedFetchTask<List<Billboard>>)FetchBillboardVideosTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchBillboardVideosTask.this).getCallback().onBBVideosFetched(list, status);
                            if (StatusCode.SERVER_ERROR_MAP_CACHE_MISS == status.getStatusCode()) {
                                Log.e("nf_service_browseagent", "Map cache miss on FetchBBVideos - refresh");
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
            final List<Billboard> list = ((CachedFetchTask<List<Billboard>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mCache.getCwKeysCache().add(((CachedFetchTask)this).getCacheKey());
                BrowseAgent.this.mBrowseWebClient.fetchBBVideos(this.lomo, ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onBBVideosFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchCWVideosTask extends CachedFetchTask<List<com.netflix.mediaclient.servicemgr.model.CWVideo>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchCWVideosTask(final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super("continueWatching", n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onCWVideosFetched(final List<com.netflix.mediaclient.servicemgr.model.CWVideo> list, final Status status) {
                    ((CachedFetchTask<List<com.netflix.mediaclient.servicemgr.model.CWVideo>>)FetchCWVideosTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchCWVideosTask.this).getCallback().onCWVideosFetched(list, status);
                            if (StatusCode.SERVER_ERROR_MAP_CACHE_MISS == status.getStatusCode()) {
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
            final List<com.netflix.mediaclient.servicemgr.model.CWVideo> list = ((CachedFetchTask<List<com.netflix.mediaclient.servicemgr.model.CWVideo>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mCache.getCwKeysCache().add(((CachedFetchTask)this).getCacheKey());
                BrowseAgent.this.mBrowseWebClient.fetchCWVideos(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onCWVideosFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchEpisodeDetailsTask extends CachedFetchTask<EpisodeDetails>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchEpisodeDetailsTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
                    ((CachedFetchTask<EpisodeDetails>)FetchEpisodeDetailsTask.this).updateCacheIfNecessary(episodeDetails, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (status.isSucces() && episodeDetails != null) {
                                BrowseAgent.this.updateEpisodeWithLatestShowInformation(episodeDetails);
                            }
                            ((FetchTask)FetchEpisodeDetailsTask.this).getCallback().onEpisodeDetailsFetched(episodeDetails, status);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final EpisodeDetails episodeDetails = ((CachedFetchTask<EpisodeDetails>)this).getCachedValue();
            if (episodeDetails != null) {
                BrowseAgent.this.updateEpisodeWithLatestShowInformation(episodeDetails);
                this.webClientCallback.onEpisodeDetailsFetched(episodeDetails, CommonStatus.OK);
                return;
            }
            final String buildEpisodeDetailsCacheKey = BrowseWebClientCache.buildEpisodeDetailsCacheKey(((FetchTask)this).getCategory());
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
            }
            final WeakReference weakReference = (WeakReference)BrowseAgent.this.mCache.getWeakEpisodesCache().get(buildEpisodeDetailsCacheKey);
            if (weakReference != null && weakReference.get() != null) {
                final EpisodeDetails episodeDetails2 = weakReference.get();
                BrowseAgent.this.updateEpisodeWithLatestShowInformation(episodeDetails2);
                this.webClientCallback.onEpisodeDetailsFetched(episodeDetails2, CommonStatus.OK);
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
                public void onEpisodesFetched(final List<EpisodeDetails> list, final Status status) {
                    ((CachedFetchTask<List<EpisodeDetails>>)FetchEpisodesTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (status.isSucces() && list != null) {
                                BrowseAgent.this.mCache.putInWeakEpisodesCache(list);
                                BrowseAgent.this.updateEpisodesWithLatestShowInformation(list);
                            }
                            ((FetchTask)FetchEpisodesTask.this).getCallback().onEpisodesFetched(list, status);
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
            BrowseAgent.this.updateEpisodesWithLatestShowInformation(list);
            this.webClientCallback.onEpisodesFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchGenreListsTask extends CachedFetchTask<List<GenreList>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchGenreListsTask(final BrowseAgentCallback browseAgentCallback) {
            super("genreList", 0, 0, browseAgentCallback, false);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onGenreListsFetched(final List<GenreList> list, final Status status) {
                    ((CachedFetchTask<List<GenreList>>)FetchGenreListsTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchGenreListsTask.this).getCallback().onGenreListsFetched(list, status);
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
            this.webClientCallback.onGenreListsFetched(list, CommonStatus.OK);
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
                public void onVideosFetched(final List<Video> list, final Status status) {
                    ((CachedFetchTask<List<Video>>)FetchGenreVideosTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchGenreVideosTask.this).getCallback().onVideosFetched(list, status);
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
            this.webClientCallback.onVideosFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchGenresTask extends CachedFetchTask<List<Genre>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchGenresTask(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
            super(s, n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onGenresFetched(final List<Genre> list, final Status status) {
                    ((CachedFetchTask<List<Genre>>)FetchGenresTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchGenresTask.this).getCallback().onGenresFetched(list, status);
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
            this.webClientCallback.onGenresFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchIQVideosTask extends CachedFetchTask<List<Video>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchIQVideosTask(final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super("queue", n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideosFetched(final List<Video> list, final Status status) {
                    ((CachedFetchTask<List<Video>>)FetchIQVideosTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchIQVideosTask.this).getCallback().onVideosFetched(list, status);
                            if (StatusCode.SERVER_ERROR_MAP_CACHE_MISS == status.getStatusCode()) {
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
                BrowseAgent.this.mCache.getIqKeysCache().add(((CachedFetchTask)this).getCacheKey());
                BrowseAgent.this.mBrowseWebClient.fetchIQVideos(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onVideosFetched(list, CommonStatus.OK);
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
                public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
                    ((CachedFetchTask<KidsCharacterDetails>)FetchKidsCharacterDetailsTask.this).updateCacheIfNecessary(kidsCharacterDetails, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchKidsCharacterDetailsTask.this).getCallback().onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
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
                public void onLoLoMoSummaryFetched(final LoLoMo loLoMo, final Status status) {
                    ((CachedFetchTask<LoLoMo>)FetchLoLoMoSummaryTask.this).updateCacheIfNecessary(loLoMo, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchLoLoMoSummaryTask.this).getCallback().onLoLoMoSummaryFetched(loLoMo, status);
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
            this.webClientCallback.onLoLoMoSummaryFetched(loLoMo, CommonStatus.OK);
        }
    }
    
    private class FetchLoMosTask extends CachedFetchTask<List<LoMo>>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchLoMosTask(final int n, final int n2, final BrowseAgentCallback browseAgentCallback, final boolean b) {
            super("lolomo", n, n2, browseAgentCallback, b);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onLoMosFetched(final List<LoMo> list, final Status status) {
                    ((CachedFetchTask<List<LoMo>>)FetchLoMosTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchLoMosTask.this).getCallback().onLoMosFetched(list, status);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final List<LoMo> list = ((CachedFetchTask<List<LoMo>>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchLoMos(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onLoMosFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchMovieDetailsTask extends CachedFetchTask<com.netflix.mediaclient.servicemgr.model.details.MovieDetails>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchMovieDetailsTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onMovieDetailsFetched(final com.netflix.mediaclient.servicemgr.model.details.MovieDetails movieDetails, final Status status) {
                    ((CachedFetchTask<com.netflix.mediaclient.servicemgr.model.details.MovieDetails>)FetchMovieDetailsTask.this).updateCacheIfNecessary(movieDetails, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchMovieDetailsTask.this).getCallback().onMovieDetailsFetched(movieDetails, status);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final com.netflix.mediaclient.servicemgr.model.details.MovieDetails movieDetails = ((CachedFetchTask<com.netflix.mediaclient.servicemgr.model.details.MovieDetails>)this).getCachedValue();
            if (movieDetails == null) {
                BrowseAgent.this.mBrowseWebClient.fetchMovieDetails(((FetchTask)this).getCategory(), this.webClientCallback);
                return;
            }
            this.webClientCallback.onMovieDetailsFetched(movieDetails, CommonStatus.OK);
        }
    }
    
    private class FetchPostPlayTask extends FetchTask<PostPlayVideo>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public FetchPostPlayTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onPostPlayVideosFetched(final List<PostPlayVideo> list, final Status status) {
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchPostPlayTask.this).getCallback().onPostPlayVideosFetched(list, status);
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
                public void onSeasonDetailsFetched(final SeasonDetails seasonDetails, final Status status) {
                    ((CachedFetchTask<SeasonDetails>)FetchSeasonDetailsTask.this).updateCacheIfNecessary(seasonDetails, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchSeasonDetailsTask.this).getCallback().onSeasonDetailsFetched(seasonDetails, status);
                        }
                    });
                }
            };
        }
        
        @Override
        public void run() {
            final SeasonDetails seasonDetails = ((CachedFetchTask<SeasonDetails>)this).getCachedValue();
            if (seasonDetails != null) {
                this.webClientCallback.onSeasonDetailsFetched(seasonDetails, CommonStatus.OK);
                return;
            }
            final WeakReference weakReference = (WeakReference)BrowseAgent.this.mCache.getWeakSeasonsCache().get(BrowseWebClientCache.buildSeasonDetailsCacheKey(((FetchTask)this).getCategory()));
            if (weakReference != null && weakReference.get() != null) {
                this.webClientCallback.onSeasonDetailsFetched(weakReference.get(), CommonStatus.OK);
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
                public void onSeasonsFetched(final List<SeasonDetails> list, final Status status) {
                    ((CachedFetchTask<List<SeasonDetails>>)FetchSeasonsTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            if (status.isSucces() && list != null) {
                                BrowseAgent.this.mCache.putInWeakSeasonsCache(list);
                            }
                            ((FetchTask)FetchSeasonsTask.this).getCallback().onSeasonsFetched(list, status);
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
            BrowseAgent.this.mCache.putInWeakSeasonsCache(list);
            this.webClientCallback.onSeasonsFetched(list, CommonStatus.OK);
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
                public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
                    if (status.isSucces()) {
                        if (StringUtils.isEmpty(FetchShowDetailsTask.this.mCurrentEpisodeId)) {
                            ((CachedFetchTask<ShowDetails>)FetchShowDetailsTask.this).updateCacheIfNecessary(showDetails, status);
                        }
                        else {
                            BrowseAgent.this.updateSeasonWithSdp(showDetails);
                        }
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchShowDetailsTask.this).getCallback().onShowDetailsFetched(showDetails, status);
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
            this.webClientCallback.onShowDetailsFetched(showDetails, CommonStatus.OK);
        }
    }
    
    private class FetchSimilarVideosForPersonTask extends CachedFetchTask<SearchVideoList>
    {
        private String originalSearchTerm;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchSimilarVideosForPersonTask(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String originalSearchTerm) {
            super(s, 0, n, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
                    ((CachedFetchTask<SearchVideoList>)FetchSimilarVideosForPersonTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchSimilarVideosForPersonTask.this).getCallback().onSimilarVideosFetched(list, status);
                        }
                    });
                }
            };
            this.originalSearchTerm = originalSearchTerm;
        }
        
        @Override
        public void run() {
            final SearchVideoList list = ((CachedFetchTask<SearchVideoList>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchSimilarVideosForPerson(((FetchTask)this).getCategory(), ((FetchTask)this).getToIndex(), this.webClientCallback, this.originalSearchTerm);
                return;
            }
            this.webClientCallback.onSimilarVideosFetched(list, CommonStatus.OK);
        }
    }
    
    private class FetchSimilarVideosForQuerySuggestionTask extends CachedFetchTask<SearchVideoList>
    {
        private String originalSearchTerm;
        private final BrowseAgentCallback webClientCallback;
        
        public FetchSimilarVideosForQuerySuggestionTask(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String originalSearchTerm) {
            super(s, 0, n, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSimilarVideosFetched(final SearchVideoList list, final Status status) {
                    ((CachedFetchTask<SearchVideoList>)FetchSimilarVideosForQuerySuggestionTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchSimilarVideosForQuerySuggestionTask.this).getCallback().onSimilarVideosFetched(list, status);
                        }
                    });
                }
            };
            this.originalSearchTerm = originalSearchTerm;
        }
        
        @Override
        public void run() {
            final SearchVideoList list = ((CachedFetchTask<SearchVideoList>)this).getCachedValue();
            if (list == null) {
                BrowseAgent.this.mBrowseWebClient.fetchSimilarVideosForQuerySuggestion(((FetchTask)this).getCategory(), ((FetchTask)this).getToIndex(), this.webClientCallback, this.originalSearchTerm);
                return;
            }
            this.webClientCallback.onSimilarVideosFetched(list, CommonStatus.OK);
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
                public void onVideosFetched(final List<Video> list, final Status status) {
                    ((CachedFetchTask<List<Video>>)FetchVideosTask.this).updateCacheIfNecessary(list, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)FetchVideosTask.this).getCallback().onVideosFetched(list, status);
                            if (StatusCode.SERVER_ERROR_MAP_CACHE_MISS == status.getStatusCode()) {
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
            this.webClientCallback.onVideosFetched(list, CommonStatus.OK);
        }
    }
    
    private class HideVideoTask extends FetchTask<Void>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public HideVideoTask(final String s, final BrowseAgentCallback browseAgentCallback) {
            super(s, 0, 0, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onVideoHide(final Status status) {
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)HideVideoTask.this).getCallback().onVideoHide(status);
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
    
    private class PrefetchGenreLoLoMoTask extends FetchTask<Void>
    {
        final int mFromLoMo;
        final boolean mIncludeBoxshots;
        final int mToLoMo;
        private final BrowseAgentCallback webClientCallback;
        
        public PrefetchGenreLoLoMoTask(final String s, final int mFromLoMo, final int mToLoMo, final int n, final int n2, final boolean mIncludeBoxshots, final BrowseAgentCallback browseAgentCallback) {
            super(s, n, n2, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onGenreLoLoMoPrefetched(final Status status) {
                    if (PrefetchGenreLoLoMoTask.this.mIncludeBoxshots) {
                        throw new RuntimeException("Unimplemented exception");
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)PrefetchGenreLoLoMoTask.this).getCallback().onGenreLoLoMoPrefetched(status);
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
    
    private class PrefetchLoLoMoTask extends FetchTask<Void>
    {
        final boolean mIncludeBoxshots;
        final boolean mIncludeExtraCharacters;
        final int mToBBVideo;
        final int mToCWVideo;
        final int mToLoMo;
        private final BrowseAgentCallback webClientCallback;
        
        public PrefetchLoLoMoTask(final int mToLoMo, final int n, final int mToCWVideo, final int mToBBVideo, final boolean mIncludeExtraCharacters, final boolean mIncludeBoxshots, final BrowseAgentCallback browseAgentCallback) {
            super("lolomo", 0, n, browseAgentCallback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onLoLoMoPrefetched(final Status status) {
                    if (PrefetchLoLoMoTask.this.mIncludeBoxshots) {
                        throw new RuntimeException("Unimplemented exception");
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)PrefetchLoLoMoTask.this).getCallback().onLoLoMoPrefetched(status);
                        }
                    });
                }
            };
            this.mToLoMo = mToLoMo;
            this.mToCWVideo = mToCWVideo;
            this.mToBBVideo = mToBBVideo;
            this.mIncludeBoxshots = mIncludeBoxshots;
            this.mIncludeExtraCharacters = mIncludeExtraCharacters;
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.prefetchLoLoMo(((FetchTask)this).getCategory(), this.mToLoMo, ((FetchTask)this).getToIndex(), this.mToCWVideo, this.mToBBVideo, this.mIncludeExtraCharacters, this.webClientCallback);
        }
    }
    
    private class RefreshCWTask extends FetchTask<Void>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public RefreshCWTask() {
            super("refreshCW", BrowseAgent.mPrefetchFromCWVideo, BrowseAgent.mPrefetchToCWVideo, null);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onCWListRefresh(final Status status) {
                    sendCwRefreshBrodcast(BrowseAgent.this.getContext());
                }
            };
        }
        
        @Override
        public void run() {
            if (BrowseAgent.this.mCache.canRefreshCW() && BrowseAgent.this.mCache.cacheHasLolomoId()) {
                BrowseAgent.this.mBrowseWebClient.refreshCWList(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            Log.d("nf_service_browseagent", "Cache has no CW item so doing nothing for CW refresh");
        }
    }
    
    private class RefreshIQTask extends FetchTask<Void>
    {
        private final BrowseAgentCallback webClientCallback;
        
        public RefreshIQTask() {
            super("refreshIQ", BrowseAgent.mPrefetchFromVideo, BrowseAgent.mPrefetchToVideo, null);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onIQListRefresh(final Status status) {
                    sendIqRefreshBrodcast(BrowseAgent.this.getContext());
                }
            };
        }
        
        @Override
        public void run() {
            if (BrowseAgent.this.mCache.canRefreshIQ() && BrowseAgent.this.mCache.cacheHasLolomoId()) {
                BrowseAgent.this.mBrowseWebClient.refreshIQList(((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.webClientCallback);
                return;
            }
            Log.d("nf_service_browseagent", "Cache has no IQ item so doing nothing for IQ refresh");
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
                public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
                    final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetailsFromCache = BrowseAgent.this.mCache.getKidsCharacterDetailsFromCache(RefreshKidsCharacterDetailsTask.this.mCharacterId);
                    if (kidsCharacterDetailsFromCache == null) {
                        Log.w("nf_service_browseagent", "Cached character details are null, can't refresh - charId: " + RefreshKidsCharacterDetailsTask.this.mCharacterId);
                        return;
                    }
                    final com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails kidsCharacterDetails2 = (com.netflix.mediaclient.service.webclient.model.KidsCharacterDetails)kidsCharacterDetails;
                    final Boolean access$1800 = BrowseAgent.this.isKidsCharacterDetailsNew(kidsCharacterDetailsFromCache, kidsCharacterDetails);
                    if (b && kidsCharacterDetailsFromCache != null) {
                        kidsCharacterDetailsFromCache.updateWatchNext(kidsCharacterDetails2);
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)RefreshKidsCharacterDetailsTask.this).getCallback().onKidsCharacterDetailsFetched(kidsCharacterDetailsFromCache, access$1800, status);
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
                public void onQueueRemove(final Status status) {
                    if (status.isSucces()) {
                        if (RemoveFromQueueTask.this.iqInCache) {
                            sendIqRefreshBrodcast(BrowseAgent.this.getContext());
                        }
                        LogUtils.reportRemoveFromQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.success, null);
                    }
                    else {
                        LogUtils.reportRemoveFromQueueActionEnded(BrowseAgent.this.getContext(), IClientLogging.CompletionReason.failed, new LogUtils.LogReportErrorArgs(status, ActionOnUIError.displayedError, "", null).getError());
                    }
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)RemoveFromQueueTask.this).getCallback().onQueueRemove(status);
                        }
                    });
                }
            };
            this.iqInCache = BrowseAgent.this.mCache.areIqIdsInCache();
        }
        
        @Override
        public void run() {
            BrowseAgent.this.mBrowseWebClient.removeFromQueue(((FetchTask)this).getCategory(), ((FetchTask)this).getFromIndex(), ((FetchTask)this).getToIndex(), this.iqInCache, this.webClientCallback);
        }
    }
    
    private class SearchNetflixTask extends CachedFetchTask<ISearchResults>
    {
        private final BrowseAgentCallback callback;
        private final String query;
        private final BrowseAgentCallback webClientCallback;
        
        public SearchNetflixTask(final String s, final BrowseAgentCallback callback) {
            super(s, 0, 0, callback);
            this.webClientCallback = new SimpleBrowseAgentCallback() {
                @Override
                public void onSearchResultsFetched(final ISearchResults searchResults, final Status status) {
                    ((CachedFetchTask<ISearchResults>)SearchNetflixTask.this).updateCacheIfNecessary(searchResults, status);
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            SearchNetflixTask.this.callback.onSearchResultsFetched(searchResults, status);
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
            final ISearchResults searchResults = ((CachedFetchTask<ISearchResults>)this).getCachedValue();
            if (searchResults == null) {
                ProfileType profileType = ProfileType.STANDARD;
                final UserProfile currentProfile = BrowseAgent.this.getUserAgent().getCurrentProfile();
                if (currentProfile != null) {
                    profileType = currentProfile.getProfileType();
                }
                BrowseAgent.this.mBrowseWebClient.searchNetflix(this.query, profileType, this.webClientCallback);
                return;
            }
            this.webClientCallback.onSearchResultsFetched(searchResults, CommonStatus.OK);
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
                public void onVideoRatingSet(final Status status) {
                    BrowseAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)SetVideoRatingTask.this).getCallback().onVideoRatingSet(status);
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
