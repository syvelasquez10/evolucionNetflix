// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Intent;
import com.netflix.mediaclient.service.user.UserAgentBroadcastIntents;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

public class BrowseAgent extends ServiceAgent implements ServiceAgent$BrowseAgentInterface
{
    public static final String BOOKMARK_TAG = "nf_bookmark";
    public static final String CACHE_KEY_PREFIX_BB_VIDEOS;
    public static final String CACHE_KEY_PREFIX_CW_VIDEOS;
    public static final String CACHE_KEY_PREFIX_EPISODE_DETAILS;
    public static final String CACHE_KEY_PREFIX_GENRE_LOMO;
    public static final String CACHE_KEY_PREFIX_GENRE_VIDEOS;
    public static final String CACHE_KEY_PREFIX_IN_QUEUE = "inQueue";
    public static final String CACHE_KEY_PREFIX_IQ_VIDEOS;
    public static final String CACHE_KEY_PREFIX_KIDS_CDP;
    public static final String CACHE_KEY_PREFIX_LOMO;
    public static final String CACHE_KEY_PREFIX_MDP;
    public static final String CACHE_KEY_PREFIX_NOTIFICATIONS;
    public static final String CACHE_KEY_PREFIX_SDP;
    public static final String CACHE_KEY_PREFIX_SEASON_DETAILS;
    public static final String CACHE_KEY_PREFIX_VIDEOS;
    private static final long EPISODE_RATE_LIMIT_DELAY = 30000L;
    private static final int FETCH_REQUEST_BATCH_SIZE = 40;
    private static final int MARGIN_FOR_BOOKMARK_RESET_SECONDS = 30;
    private static final int PREFETCH_BILLBOARD_VIDEO_INDEX = 9;
    private static final int REFRESH_NOTIFICATIONS_INTERVAL = 3600000;
    private static final int STILL_THRESH = 10;
    private static final String TAG = "nf_service_browseagent";
    private static final boolean USE_HARD_CACHE_CONST = false;
    private static final boolean USE_SOFT_CACHE_CONST = true;
    private static boolean isCurrentProfileActive;
    private static int sPrefetchFromCWVideo;
    private static int sPrefetchFromVideo;
    private static int sPrefetchToCWVideo;
    private static int sPrefetchToLoMo;
    private static int sPrefetchToVideo;
    private static long sPrevEpisodeRefreshTime;
    private LegacyDataDumper dataDumper;
    private BrowseWebClient mBrowseWebClient;
    private BrowseWebClientCache mCache;
    private final BrowseAgent$PlayReceiver mPlayReceiver;
    public final BroadcastReceiver mUserAgentIntentReceiver;
    private final Runnable refreshNotificationsRunnable;
    
    static {
        CACHE_KEY_PREFIX_LOMO = getCacheKeyFromClassName(BrowseAgent$FetchLoMosTask.class);
        CACHE_KEY_PREFIX_MDP = getCacheKeyFromClassName(BrowseAgent$FetchMovieDetailsTask.class);
        CACHE_KEY_PREFIX_SDP = getCacheKeyFromClassName(BrowseAgent$FetchShowDetailsTask.class);
        CACHE_KEY_PREFIX_EPISODE_DETAILS = getCacheKeyFromClassName(BrowseAgent$FetchEpisodeDetailsTask.class);
        CACHE_KEY_PREFIX_GENRE_LOMO = getCacheKeyFromClassName(BrowseAgent$FetchGenresTask.class);
        CACHE_KEY_PREFIX_CW_VIDEOS = getCacheKeyFromClassName(BrowseAgent$FetchCWVideosTask.class);
        CACHE_KEY_PREFIX_BB_VIDEOS = getCacheKeyFromClassName(BrowseAgent$FetchBillboardVideosTask.class);
        CACHE_KEY_PREFIX_IQ_VIDEOS = getCacheKeyFromClassName(BrowseAgent$FetchIQVideosTask.class);
        CACHE_KEY_PREFIX_VIDEOS = getCacheKeyFromClassName(BrowseAgent$FetchVideosTask.class);
        CACHE_KEY_PREFIX_GENRE_VIDEOS = getCacheKeyFromClassName(BrowseAgent$FetchGenreVideosTask.class);
        CACHE_KEY_PREFIX_KIDS_CDP = getCacheKeyFromClassName(BrowseAgent$FetchKidsCharacterDetailsTask.class);
        CACHE_KEY_PREFIX_SEASON_DETAILS = getCacheKeyFromClassName(BrowseAgent$FetchSeasonDetailsTask.class);
        CACHE_KEY_PREFIX_NOTIFICATIONS = getCacheKeyFromClassName(BrowseAgent$FetchSocialNotificationsTask.class);
    }
    
    public BrowseAgent() {
        this.mPlayReceiver = new BrowseAgent$PlayReceiver(this);
        this.refreshNotificationsRunnable = new BrowseAgent$3(this);
        this.mUserAgentIntentReceiver = new BrowseAgent$4(this);
    }
    
    public static String buildStillUrlFromPos(final CWVideo cwVideo) {
        final String baseUrl = cwVideo.getBaseUrl();
        final int playableBookmarkPosition = cwVideo.getPlayableBookmarkPosition();
        if (StringUtils.isEmpty(baseUrl)) {
            return cwVideo.getInterestingUrl();
        }
        if (playableBookmarkPosition < 10) {
            if (Log.isLoggable("nf_bookmark", 2)) {
                Log.v("nf_bookmark", String.format("%s bookmark < threshold(%d), using interesting url %s ", cwVideo.getId(), 10, cwVideo.getInterestingUrl()));
            }
            return cwVideo.getInterestingUrl();
        }
        final String value = String.valueOf(playableBookmarkPosition / 10);
        final StringBuilder append = new StringBuilder(baseUrl).append("/00000");
        append.replace(append.length() - value.length(), append.length(), value);
        append.append(".jpg");
        if (Log.isLoggable("nf_service_browseagent", 2)) {
            Log.v("nf_bookmark", String.format("%s stillId: %s, stillUrl: %s", cwVideo.getId(), value, append.toString()));
        }
        return append.toString();
    }
    
    private static boolean canDoDataFetches() {
        if (!BrowseAgent.isCurrentProfileActive) {
            Log.d("nf_service_browseagent", "wrong state - canDoDataFetches false - skipping browse request");
        }
        return BrowseAgent.isCurrentProfileActive;
    }
    
    private void clearExistingNotificationsInCache() {
        for (int n = 0, n2 = 0; n < this.mCache.getMaxItemsInSoftCache() && this.mCache.getSoftCache().remove(BrowseWebClientCache.buildNotificationsCacheKey(n2)) != null; n2 += 20, ++n) {}
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
    
    private void fetchSocialNotificationsInternal(final int n, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        Log.d("nf_service_browseagent", "fetchSocialNotifications");
        this.launchTask(new BrowseAgent$FetchSocialNotificationsTask(this, n, b, browseAgentCallback));
    }
    
    private static String getCacheKeyFromClassName(final Class<?> clazz) {
        return clazz.getSimpleName();
    }
    
    private SocialNotificationSummary getFirstUnreadNotification(final SocialNotificationsList list) {
        final List<SocialNotificationSummary> socialNotifications = list.getSocialNotifications();
        if (socialNotifications == null) {
            return null;
        }
        for (final SocialNotificationSummary socialNotificationSummary : socialNotifications) {
            if (!socialNotificationSummary.getWasRead()) {
                return socialNotificationSummary;
            }
        }
        return null;
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
    
    private void insertNotificationsInCache(final int n, final SocialNotificationsList list) {
        this.mCache.getSoftCache().put(BrowseWebClientCache.buildNotificationsCacheKey(n), list);
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
    
    private boolean isSameAsCachedNotifications(final SocialNotificationsList list) {
        final boolean b = false;
        final SocialNotificationsList list2 = (SocialNotificationsList)BrowseWebClientCache.getNotificationListFromCache(0, this.mCache);
        boolean b2;
        if (list2 == null && list == null) {
            b2 = true;
        }
        else {
            b2 = b;
            if (list2 != null) {
                b2 = b;
                if (list != null) {
                    final List<SocialNotificationSummary> socialNotifications = list.getSocialNotifications();
                    final List<SocialNotificationSummary> socialNotifications2 = list2.getSocialNotifications();
                    if (socialNotifications2 == null && socialNotifications == null) {
                        return true;
                    }
                    b2 = b;
                    if (socialNotifications2 != null) {
                        b2 = b;
                        if (socialNotifications != null) {
                            b2 = b;
                            if (socialNotifications2.size() == socialNotifications.size()) {
                                final Iterator<SocialNotificationSummary> iterator = socialNotifications.iterator();
                                int n = 0;
                                while (iterator.hasNext()) {
                                    b2 = b;
                                    if (!iterator.next().equals(socialNotifications2.get(n))) {
                                        return b2;
                                    }
                                    ++n;
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return b2;
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
                com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails;
                if (!episode) {
                    final MovieDetails movieDetails = (MovieDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, playableId, "0", "0"));
                    if (movieDetails != null && movieDetails.bookmark != null) {
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", String.format("%s movie bookmarkPos %d to newPos %d, oldtime %d to newTime %d", playableId, movieDetails.getBookmarkPosition(), playbackBookmark, movieDetails.bookmark.getLastModified(), currentTimeMillis));
                        }
                        movieDetails.setBookmarkPosition(playbackBookmark);
                        movieDetails.bookmark.setLastModified(currentTimeMillis);
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", String.format("%s, bookmarkpos %d playpos %d endtime %d runtime %d", playableId, playbackBookmark, computePlayPos(playbackBookmark, movieDetails.getEndtime(), movieDetails.getRuntime()), movieDetails.getEndtime(), movieDetails.getRuntime()));
                        }
                    }
                    episodeDetails = null;
                }
                else {
                    final ShowDetails showDetails = (ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, parentId, "0", "0"));
                    if (showDetails == null) {
                        return;
                    }
                    final String buildEpisodeDetailsCacheKey = BrowseWebClientCache.buildEpisodeDetailsCacheKey(playableId);
                    if (Log.isLoggable("nf_service_browseagent", 3)) {
                        Log.d("nf_bookmark", "looking for episode with key: " + buildEpisodeDetailsCacheKey);
                    }
                    final WeakReference weakReference = (WeakReference)this.mCache.getWeakEpisodesCache().get(buildEpisodeDetailsCacheKey);
                    com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails2;
                    if (weakReference != null) {
                        episodeDetails2 = weakReference.get();
                    }
                    else {
                        episodeDetails2 = null;
                    }
                    if (episodeDetails2 != null && episodeDetails2.bookmark != null) {
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", String.format("%s episode bookmarkPos %d to newPos %d, oldtime %d to newTime %d", playableId, episodeDetails2.getBookmarkPosition(), playbackBookmark, episodeDetails2.bookmark.getLastModified(), currentTimeMillis));
                        }
                        episodeDetails2.bookmark.setBookmarkPosition(playbackBookmark);
                        episodeDetails2.bookmark.setLastModified(currentTimeMillis);
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", String.format("id %s, bookmarkpos %d playpos %d endtime %d runtime %d", playableId, playbackBookmark, computePlayPos(playbackBookmark, episodeDetails2.getEndtime(), episodeDetails2.getRuntime()), episodeDetails2.getEndtime(), episodeDetails2.getRuntime()));
                        }
                        final com.netflix.mediaclient.service.webclient.model.EpisodeDetails nextPlayableEpisode = this.getNextPlayableEpisode(episodeDetails2, currentTimeMillis);
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", String.format("next playable episode: %s - %s, S%d: E%d", nextPlayableEpisode.getId(), nextPlayableEpisode.getTitle(), nextPlayableEpisode.getSeasonNumber(), nextPlayableEpisode.getEpisodeNumber()));
                        }
                        updateShowOnEpisodePlay(showDetails, nextPlayableEpisode);
                        episodeDetails = nextPlayableEpisode;
                        if (!this.shouldRateLimitEpisodeRefresh()) {
                            this.sendEpisodeRefreshBrodcast(this.getContext(), nextPlayableEpisode.getSeasonNumber(), nextPlayableEpisode.getEpisodeNumber());
                            episodeDetails = nextPlayableEpisode;
                        }
                    }
                    else {
                        if (!StringUtils.safeEquals(playableId, showDetails.getCurrentEpisodeId())) {
                            return;
                        }
                        showDetails.currentEpisodeBookmark.setBookmarkPosition(playbackBookmark);
                        showDetails.currentEpisodeBookmark.setLastModified(currentTimeMillis);
                        if (Log.isLoggable("nf_service_browseagent", 3)) {
                            Log.d("nf_bookmark", "Episode details don't exist; updated sdp currentEpisode");
                        }
                        episodeDetails = null;
                    }
                }
                this.updateCwOnPlay(playableId, playbackBookmark, currentTimeMillis, episodeDetails);
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
        this.launchTask(new BrowseAgent$RefreshKidsCharacterDetailsTask(this, s, browseAgentCallback));
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
    
    private void rescheduleNotificationsRefresh() {
        this.getMainHandler().removeCallbacks(this.refreshNotificationsRunnable);
        this.getMainHandler().postDelayed(this.refreshNotificationsRunnable, 3600000L);
    }
    
    public static void sendCwRefreshBrodcast(final Context context) {
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
    
    public static void sendHomeRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        Log.v("nf_service_browseagent", "Intent REFRESH_HOME sent");
    }
    
    public static void sendIqRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH"));
        Log.v("nf_service_browseagent", "Intent IQ_REFRESH sent");
    }
    
    private boolean shouldRateLimitEpisodeRefresh() {
        Log.v("nf_service_browseagent", String.format("EPISODE_REFRESH ? %b,  delta: %d", System.currentTimeMillis() - BrowseAgent.sPrevEpisodeRefreshTime > 30000L, System.currentTimeMillis() - BrowseAgent.sPrevEpisodeRefreshTime));
        if (System.currentTimeMillis() - BrowseAgent.sPrevEpisodeRefreshTime < 30000L) {
            Log.d("nf_service_browseagent", String.format("skip BROWSE_AGENT_EPISODE_REFRESH was done in %d ", System.currentTimeMillis() - BrowseAgent.sPrevEpisodeRefreshTime));
            return true;
        }
        BrowseAgent.sPrevEpisodeRefreshTime = System.currentTimeMillis();
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
    
    private void updateCachedCwData(final Asset asset) {
        Log.v("nf_service_browseagent", "Updating cached CW data with asset: " + asset);
        this.refreshCacheWithLastPlayed(asset);
        this.refreshCw();
    }
    
    private void updateCwForNewEpisode(final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        if (Log.isLoggable("nf_service_browseagent", 2)) {
            Log.v("nf_service_browseagent", "Updating CWVideo episode info with details: " + episodeDetails.detail);
        }
        cwVideo.currentEpisode = episodeDetails.detail;
        cwVideo.currentEpisodeBookmark = episodeDetails.bookmark;
    }
    
    private void updateCwOnPlay(final String s, final int n, final long n2, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        final List list = (List)this.mCache.getHardCache().get(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(BrowseAgent.sPrefetchFromCWVideo), String.valueOf(BrowseAgent.sPrefetchToCWVideo)));
        if (list != null) {
            for (final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo : list) {
                final boolean b = false;
                boolean b2;
                if (cwVideo.getPlayableId().equals(s)) {
                    this.updateCwPlayable(cwVideo, n, n2, episodeDetails);
                    b2 = true;
                }
                else {
                    b2 = b;
                    if (episodeDetails != null) {
                        b2 = b;
                        if (StringUtils.safeEquals(cwVideo.getParentId(), episodeDetails.getParentId())) {
                            if (Log.isLoggable("nf_service_browseagent", 2)) {
                                Log.v("nf_service_browseagent", "Updating CW video for new episode in same show, id: " + cwVideo.getParentId());
                            }
                            this.updateCwForNewEpisode(cwVideo, episodeDetails);
                            b2 = true;
                        }
                    }
                }
                if (b2 && cwVideo.bookmarkStill != null) {
                    cwVideo.bookmarkStill.stillUrl = buildStillUrlFromPos(cwVideo);
                }
            }
        }
    }
    
    private void updateCwPlayable(final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo, final int n, final long n2, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
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
            final ShowDetails showDetails = (ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, list.get(0).getShowId(), "0", "0"));
            if (showDetails != null && showDetails.socialEvidence != null) {
                final Iterator<EpisodeDetails> iterator = list.iterator();
                while (iterator.hasNext()) {
                    ((com.netflix.mediaclient.service.webclient.model.EpisodeDetails)iterator.next()).showSocialEvidence = showDetails.socialEvidence;
                }
            }
        }
    }
    
    private static void updateShowOnEpisodePlay(final ShowDetails showDetails, final com.netflix.mediaclient.service.webclient.model.EpisodeDetails episodeDetails) {
        showDetails.currentEpisode.deepCopy(episodeDetails.detail);
        showDetails.currentEpisodeBookmark.deepCopy(episodeDetails.bookmark);
    }
    
    public void addToQueue(final String s, final int n, final boolean b, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$AddToQueueTask(this, s, n, b, s2, browseAgentCallback));
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
        this.dataDumper = new LegacyDataDumper(this.mBrowseWebClient, this.mCache.getHardCache(), this.mCache.getSoftCache());
        this.initCompleted(CommonStatus.OK);
    }
    
    public void dumpHomeLoLoMosAndVideosToLog() {
        this.dataDumper.dumpHomeLoLoMosAndVideosToLog();
    }
    
    public void dumpHomeLoMos() {
        this.dataDumper.dumpHomeLoMos();
    }
    
    @Override
    public void fetchBillboardsFromCache(final int n, final BrowseAgentCallback browseAgentCallback) {
        int min = Math.min(n, 9);
        List list;
        if ((list = (List)BrowseWebClientCache.getBBVideosFromBrowseCache(0, 9, this.mCache)) == null) {
            min = n - 1;
            if ((list = (List)BrowseWebClientCache.getBBVideosFromBrowseCache(0, min, this.mCache)) == null) {
                browseAgentCallback.onBBVideosFetched(null, CommonStatus.OK);
                return;
            }
        }
        browseAgentCallback.onBBVideosFetched(list.subList(0, Math.min(min, list.size())), CommonStatus.OK);
    }
    
    @Override
    public void fetchCWFromCache(int min, final BrowseAgentCallback browseAgentCallback) {
        int min2 = Math.min(min, BrowseAgent.sPrefetchToCWVideo);
        List list;
        if ((list = (List)BrowseWebClientCache.getCWVideosFromBrowseCache(BrowseAgent.sPrefetchFromCWVideo, BrowseAgent.sPrefetchToCWVideo, this.mCache)) == null) {
            min2 = min - 1;
            if ((list = (List)BrowseWebClientCache.getCWVideosFromBrowseCache(0, min2, this.mCache)) == null) {
                browseAgentCallback.onCWVideosFetched(null, CommonStatus.OK);
                return;
            }
        }
        min = Math.min(min2, list.size());
        if (list == null || BrowseAgent.sPrefetchFromCWVideo > min) {
            browseAgentCallback.onCWVideosFetched(null, CommonStatus.OK);
            return;
        }
        browseAgentCallback.onCWVideosFetched(list.subList(BrowseAgent.sPrefetchFromCWVideo, min), CommonStatus.OK);
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchCWVideosTask(this, n, n2, browseAgentCallback, true));
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchEpisodeDetailsTask(this, s, browseAgentCallback));
    }
    
    public void fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchEpisodesTask(this, s, n, n2, browseAgentCallback));
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchGenreListsTask(this, browseAgentCallback));
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchGenreVideosTask(this, loMo, n, n2, b, browseAgentCallback));
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchGenresTask(this, s, n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchIQFromCache(int min, final BrowseAgentCallback browseAgentCallback) {
        int min2 = Math.min(min, BrowseAgent.sPrefetchToVideo);
        List list;
        if ((list = (List)BrowseWebClientCache.getIQVideosFromBrowseCache(BrowseAgent.sPrefetchFromVideo, BrowseAgent.sPrefetchToVideo, this.mCache)) == null) {
            min2 = min - 1;
            if ((list = (List)BrowseWebClientCache.getIQVideosFromBrowseCache(0, min2, this.mCache)) == null) {
                browseAgentCallback.onVideosFetched(null, CommonStatus.OK);
                return;
            }
        }
        min = Math.min(min2, list.size());
        if (BrowseAgent.sPrefetchFromVideo > min) {
            browseAgentCallback.onVideosFetched(null, CommonStatus.OK);
            return;
        }
        browseAgentCallback.onVideosFetched(list.subList(BrowseAgent.sPrefetchFromVideo, min), CommonStatus.OK);
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchIQVideosTask(this, n, n2, b, browseAgentCallback, true));
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchKidsCharacterDetailsTask(this, s, browseAgentCallback));
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchLoLoMoSummaryTask(this, s, browseAgentCallback));
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchLoMosTask(this, n, n2, browseAgentCallback, true));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchMovieDetailsTask(this, s, browseAgentCallback));
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final VideoType videoType, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchPostPlayTask(this, s, browseAgentCallback));
    }
    
    public void fetchPreAppData(final int n, int n2) {
        if (Log.isLoggable("nf_service_browseagent", 3) && this.mCache.cacheHasLolomoId()) {
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                throw new IllegalStateException("Bad call fetchPreAppData when lolomoId is present");
            }
            this.refreshAll();
        }
        final BrowseAgent$1 browseAgent$1 = new BrowseAgent$1(this);
        --n2;
        this.launchTask(new BrowseAgent$PrefetchLoLoMoTask(this, n - 1, n2, n2, n2, false, false, false, browseAgent$1));
    }
    
    @Override
    public void fetchRecommendedListFromCache(int min, final BrowseAgentCallback browseAgentCallback) {
        final List list = (List)BrowseWebClientCache.getLoMoListFromBrowseCache(0, BrowseAgent.sPrefetchToLoMo, this.mCache);
        if (list == null) {
            browseAgentCallback.onVideosFetched(null, CommonStatus.OK);
            return;
        }
        while (true) {
            for (final LoMo loMo : list) {
                if (loMo.getType() == LoMoType.STANDARD) {
                    if (Log.isLoggable("nf_service_browseagent", 3)) {
                        Log.d("nf_service_browseagent", String.format("fetchNonSpecialRowFromCache listTitle: %s, listId: %s", loMo.getTitle(), loMo.getId()));
                    }
                    final List<Video> list2 = (List<Video>)BrowseWebClientCache.getVideoListFromBrowseCache(loMo.getId(), BrowseAgent.sPrefetchFromVideo, BrowseAgent.sPrefetchToVideo, this.mCache);
                    if (list2 == null) {
                        browseAgentCallback.onVideosFetched(null, CommonStatus.OK);
                        return;
                    }
                    min = Math.min(Math.min(min, BrowseAgent.sPrefetchToVideo), list2.size());
                    if (BrowseAgent.sPrefetchFromVideo > min) {
                        browseAgentCallback.onVideosFetched(null, CommonStatus.OK);
                        return;
                    }
                    browseAgentCallback.onVideosFetched(list2.subList(BrowseAgent.sPrefetchFromVideo, min), CommonStatus.OK);
                    return;
                }
            }
            final List<Video> list2 = null;
            continue;
        }
    }
    
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchSeasonDetailsTask(this, s, browseAgentCallback));
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchSeasonsTask(this, s, n, n2, browseAgentCallback));
    }
    
    public void fetchShowDetails(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchShowDetailsTask(this, s, s2, false, b, browseAgentCallback));
    }
    
    public void fetchShowDetailsAndSeasons(final String s, final String s2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$FetchShowDetailsTask(this, s, s2, true, b, browseAgentCallback));
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.launchTask(new BrowseAgent$FetchSimilarVideosForPersonTask(this, s, n, browseAgentCallback, s2));
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.launchTask(new BrowseAgent$FetchSimilarVideosForQuerySuggestionTask(this, s, n, browseAgentCallback, s2));
    }
    
    public void fetchSocialNotifications(final int n, final BrowseAgentCallback browseAgentCallback) {
        Log.d("nf_service_browseagent", "fetchSocialNotifications");
        this.fetchSocialNotificationsInternal(n, false, browseAgentCallback);
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (LoMoType.BILLBOARD.equals(loMo.getType())) {
            this.launchTask(new BrowseAgent$FetchBillboardVideosTask(this, loMo, n, n2, browseAgentCallback, true));
            return;
        }
        this.launchTask(new BrowseAgent$FetchVideosTask(this, loMo, n, n2, b, b2, browseAgentCallback, true));
    }
    
    public void flushCaches() {
        this.initCaches();
    }
    
    public void hideVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$HideVideoTask(this, s, browseAgentCallback));
    }
    
    public void logBillboardActivity(final Video video, final BrowseAgent$BillboardActivityType browseAgent$BillboardActivityType) {
        this.launchTask(new BrowseAgent$LogBillboardActivityTask(this, video, browseAgent$BillboardActivityType));
    }
    
    public void markSocialNotificationsAsRead(final List<SocialNotificationSummary> list) {
        Log.d("nf_service_browseagent", "markSocialNotificationsAsRead");
        this.launchTask(new BrowseAgent$MarkNotificationsAsReadTask(list));
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        if (Log.isLoggable("nf_service_browseagent", 4)) {
            Log.i("nf_service_browseagent", "Request to prefetchGenre  LoLoMo");
        }
        if (!this.mCache.needToPrefetchGenre(s)) {
            browseAgentCallback.onGenreLoLoMoPrefetched(CommonStatus.OK);
            return;
        }
        this.launchTask(new BrowseAgent$PrefetchGenreLoLoMoTask(this, s, n, n2, n3, n4, b, browseAgentCallback));
    }
    
    public void prefetchLoLoMo(final int n, final int sPrefetchToLoMo, final int sPrefetchFromVideo, final int sPrefetchToVideo, final int sPrefetchFromCWVideo, final int sPrefetchToCWVideo, final boolean b, final boolean b2, final boolean b3, final BrowseAgentCallback browseAgentCallback) {
        Log.i("nf_service_browseagent", "Request to prefetch LoLoMo");
        if (!this.mCache.needToPrefetch()) {
            browseAgentCallback.onLoLoMoPrefetched(CommonStatus.OK);
            return;
        }
        BrowseAgent.sPrefetchToLoMo = sPrefetchToLoMo;
        BrowseAgent.sPrefetchToCWVideo = sPrefetchToCWVideo;
        BrowseAgent.sPrefetchFromCWVideo = sPrefetchFromCWVideo;
        BrowseAgent.sPrefetchToVideo = sPrefetchToVideo;
        BrowseAgent.sPrefetchFromVideo = sPrefetchFromVideo;
        this.launchTask(new BrowseAgent$PrefetchLoLoMoTask(this, sPrefetchToLoMo, sPrefetchToVideo, sPrefetchToCWVideo, 9, b, b2, b3, browseAgentCallback));
    }
    
    public void refreshAll() {
        this.flushCaches();
        sendHomeRefreshBrodcast(this.getContext());
    }
    
    public void refreshCw() {
        Log.v("nf_service_browseagent", "Refreshing CW data from server...");
        this.launchTask(new BrowseAgent$RefreshCWTask(this));
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
            final ShowDetails showDetails = (ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, parentId, "0", "0"));
            if (showDetails == null || showDetails.currentEpisode == null) {
                Log.w("nf_bookmark", String.format("populate cache - parentId %s or videoId %s null - SDP missing skip!", parentId, playableId));
                return;
            }
            final String seasonId = showDetails.currentEpisode.getSeasonId();
            final int currentEpisodeNumber = showDetails.getCurrentEpisodeNumber();
            final int n = currentEpisodeNumber / 40 * 40;
            final int n2 = 40 + n - 1;
            if (Log.isLoggable("nf_service_browseagent", 3)) {
                Log.d("nf_bookmark", String.format("populate cache - Show %s, ce %s seasonId %s ceNum %d, reqStart %d reqEnd %d", parentId, playableId, seasonId, currentEpisodeNumber, n, n2));
            }
            this.fetchEpisodes(seasonId, VideoType.SEASON, n, n2, new BrowseAgent$5(this));
        }
    }
    
    public void refreshIq() {
        Log.v("nf_service_browseagent", "Refreshing IQ data from server...");
        this.launchTask(new BrowseAgent$RefreshIQTask(this));
    }
    
    public void refreshSocialNotifications(final boolean b, final boolean b2, final MessageData messageData) {
        this.fetchSocialNotificationsInternal(0, b, new BrowseAgent$2(this, b2, messageData));
        this.rescheduleNotificationsRefresh();
    }
    
    public void removeFromQueue(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$RemoveFromQueueTask(this, s, s2, browseAgentCallback));
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$SearchNetflixTask(this, s, browseAgentCallback));
    }
    
    public void sendThanksToSocialNotification(final SocialNotificationSummary socialNotificationSummary, final BrowseAgentCallback browseAgentCallback) {
        Log.d("nf_service_browseagent", "sendThanksToSocialNotification");
        this.launchTask(new BrowseAgent$SendThanksToSocialNotificationTask(this, socialNotificationSummary, browseAgentCallback));
    }
    
    public void setVideoRating(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new BrowseAgent$SetVideoRatingTask(this, s, n, n2, browseAgentCallback));
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
        final ShowDetails showDetails = (ShowDetails)this.mCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, episodeDetails.getShowId(), "0", "0"));
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
}
