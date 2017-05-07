// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.pushnotification.MessageData;
import java.util.List;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.falkor.ModelProxy;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.service.browse.DataDumper;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;

public final class BrowseManager implements IBrowseManager
{
    private static final String ERROR_PARAM_WITH_NULL = "Parameter cannot be null";
    private static final String TAG = "ServiceManagerBrowse";
    private final IServiceManagerAccess mgr;
    
    public BrowseManager(final IServiceManagerAccess mgr) {
        this.mgr = mgr;
    }
    
    @Override
    public boolean addToQueue(final String s, final VideoType videoType, final int n, final boolean b, final String s2, final ManagerCallback managerCallback) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int wrappedRequestId = this.mgr.getWrappedRequestId(managerCallback, s);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "addToQueue requestId=" + wrappedRequestId + " id=" + s);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().addToQueue(s, videoType, n, b, s2, this.mgr.getClientId(), wrappedRequestId);
            return true;
        }
        Log.w("ServiceManagerBrowse", "addToQueue:: service is not available");
        return false;
    }
    
    @Override
    public void dumpCacheToDisk() {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().dumpCacheToDisk();
            return;
        }
        Log.w("ServiceManagerBrowse", "dumpCacheToDisk:: service is not available");
    }
    
    @Override
    public void dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        if (this.mgr.getService() != null) {
            new DataDumper(this).dumpHomeLoLoMosAndVideosToHtml(s, s2);
            return;
        }
        Log.w("ServiceManagerBrowse", "dumpHomeLoLoMosAndVideos:: service is not available");
    }
    
    @Override
    public boolean fetchCWVideos(final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "fetchCWLoMo requestId=" + requestId + " fromVideo=" + n + " toVideo=" + n2);
            }
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().fetchCWVideos(n, n2, this.mgr.getClientId(), requestId);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "fetchCWVideos:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public boolean fetchEpisodeDetails(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchEpisodeDetails requestId=" + requestId + " episodeId=" + s2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().fetchEpisodeDetails(s2, this.mgr.getClientId(), requestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchEpisodeDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchEpisodes requestId=" + requestId + " id=" + s2 + " fromEpisode=" + n + " toEpisode=" + n2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().fetchEpisodes(s2, videoType, n, n2, this.mgr.getClientId(), requestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchEpisodes:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchGenreLists(final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "fetchGenreLists requestId=" + requestId);
            }
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().fetchGenreLists(this.mgr.getClientId(), requestId);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "fetchGenreLists:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public boolean fetchGenreVideos(final LoMo loMo, final int n, final int n2, final boolean b, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(loMo.getId())) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final LoMo loMo2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchGenreVideos requestId=" + requestId + " genreLoMoId=" + loMo2.getId() + " fromVideo=" + n + " toVideo=" + n2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b2;
        if (service != null) {
            service.getBrowse().fetchGenreVideos(loMo2, n, n2, b, this.mgr.getClientId(), requestId);
            b2 = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchGenreVideos:: service is not available");
            b2 = false;
        }
        // monitorexit(this)
        return b2;
    }
    
    @Override
    public boolean fetchGenres(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        while (true) {
            final boolean b = false;
            final INetflixService service;
            Label_0097: {
                synchronized (this) {
                    service = this.mgr.getService();
                    boolean b2;
                    if (service == null) {
                        Log.w("ServiceManagerBrowse", "fetchGenres:: service is not available");
                        b2 = b;
                    }
                    else {
                        if (!StringUtils.isEmpty(s)) {
                            break Label_0097;
                        }
                        b2 = b;
                        if (Log.isLoggable()) {
                            Log.w("ServiceManagerBrowse", String.format("fetchGenres:: stack:%s", android.util.Log.getStackTraceString((Throwable)new Exception("Parameter cannot be null"))));
                            b2 = b;
                        }
                    }
                    return b2;
                }
            }
            final int requestId = this.mgr.getRequestId(managerCallback);
            final String s2;
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "fetchGenres requestId=" + requestId + " id=" + s2);
            }
            service.getBrowse().fetchGenres(s2, n, n2, this.mgr.getClientId(), requestId);
            return true;
        }
    }
    
    @Override
    public boolean fetchIQVideos(final LoMo loMo, final int n, final int n2, final boolean b, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "fetchIQLoMo requestId=" + requestId + " fromVideo=" + n + " toVideo=" + n2);
            }
            final INetflixService service = this.mgr.getService();
            boolean b2;
            if (service != null) {
                service.getBrowse().fetchIQVideos(loMo, n, n2, b, this.mgr.getClientId(), requestId);
                b2 = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "fetchIQVideos:: service is not available");
                b2 = false;
            }
            return b2;
        }
    }
    
    @Override
    public boolean fetchKidsCharacterDetails(final String s, final ManagerCallback managerCallback) {
        boolean b = true;
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", String.format("fetchKidsCharacterDetails requestId=%d,  characterId=%s", requestId, s2));
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().fetchKidsCharacterDetails(s2, this.mgr.getClientId(), requestId);
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchKidsCharacterDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchLoLoMoSummary(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchLoLoMoSummary requestId=" + requestId + " category=" + s2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().fetchLoLoMoSummary(s2, this.mgr.getClientId(), requestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchLoLoMoSummary:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchLoMos(final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "fetchLoLoMo requestId=" + requestId + " fromLoMo=" + n + " toLoMo=" + n2);
            }
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().fetchLoMos(n, n2, this.mgr.getClientId(), requestId);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "fetchLoMos:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public boolean fetchMovieDetails(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final String s2;
        final int wrappedRequestId = this.mgr.getWrappedRequestId(managerCallback, s2);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchMovieDetails requestId=" + wrappedRequestId + " movieId=" + s2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().fetchMovieDetails(s2, this.mgr.getClientId(), wrappedRequestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchMovieDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchNotificationsList(final int n, final int n2, final ManagerCallback managerCallback) {
        final int requestId = this.mgr.getRequestId(managerCallback);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchNotificationsList requestId=" + requestId);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().fetchNotifications(n, n2, this.mgr.getClientId(), requestId);
            return true;
        }
        Log.w("ServiceManagerBrowse", "fetchNotificationsList:: service is not available");
        return false;
    }
    
    @Override
    public boolean fetchPostPlayVideos(final String s, final VideoType videoType, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "fetchPostPlayVideos requestId=" + requestId + " currentPlayableId=" + s);
            }
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().fetchPostPlayVideos(s, videoType, this.mgr.getClientId(), requestId);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "fetchPostPlayVideos:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public boolean fetchSeasonDetails(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchSeasonDetails requestId=" + requestId + " seasonId=" + s2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().fetchSeasonDetails(s2, this.mgr.getClientId(), requestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchSeasonDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchSeasons(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchSeasons requestId=" + requestId + " id=" + s2 + " fromSeason=" + n + " toSeason=" + n2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().fetchSeasons(s2, n, n2, this.mgr.getClientId(), requestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchSeasons:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    @Override
    public boolean fetchShowDetails(final String s, final String s2, final boolean b, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final String s3;
        final int wrappedRequestId = this.mgr.getWrappedRequestId(managerCallback, s3);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchShowDetails requestId=" + wrappedRequestId + " id=" + s3);
        }
        final INetflixService service = this.mgr.getService();
        boolean b2;
        if (service != null) {
            service.getBrowse().fetchShowDetails(s3, s2, b, this.mgr.getClientId(), wrappedRequestId);
            b2 = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchShowDetails:: service is not available");
            b2 = false;
        }
        // monitorexit(this)
        return b2;
    }
    
    @Override
    public boolean fetchShowDetailsAndSeasons(final String s, final String s2, final boolean b, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final String s3;
        final int wrappedRequestId = this.mgr.getWrappedRequestId(managerCallback, s3);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchShowDetailsAndSeasons requestId=" + wrappedRequestId + " id=" + s3);
        }
        final INetflixService service = this.mgr.getService();
        boolean b2;
        if (service != null) {
            service.getBrowse().fetchShowDetailsAndSeasons(s3, s2, b, this.mgr.getClientId(), wrappedRequestId);
            b2 = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "fetchShowDetailsAndSeasons:: service is not available");
            b2 = false;
        }
        // monitorexit(this)
        return b2;
    }
    
    @Override
    public boolean fetchSimilarVideosForPerson(final String s, final int n, final ManagerCallback managerCallback, final String s2) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().fetchSimilarVideosForPerson(s, n, this.mgr.getClientId(), requestId, s2);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "searchNetflix:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public boolean fetchSimilarVideosForQuerySuggestion(final String s, final int n, final ManagerCallback managerCallback, final String s2) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().fetchSimilarVideosForQuerySuggestion(s, n, this.mgr.getClientId(), requestId, s2);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "searchNetflix:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public boolean fetchVideoSummary(final String s, final ManagerCallback managerCallback) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "fetchVideoSummary requestId=" + requestId + " videoId=" + s);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().fetchVideoSummary(s, this.mgr.getClientId(), requestId);
            return true;
        }
        Log.w("ServiceManagerBrowse", "fetchVideoSummary:: service is not available");
        return false;
    }
    
    @Override
    public boolean fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final ManagerCallback managerCallback) {
        // monitorenter(this)
        while (true) {
            if (loMo != null) {
                try {
                    if (StringUtils.isEmpty(loMo.getId())) {
                        throw new IllegalArgumentException("Parameter cannot be null");
                    }
                }
                finally {
                }
                // monitorexit(this)
                final int requestId = this.mgr.getRequestId(managerCallback);
                final LoMo loMo2;
                if (Log.isLoggable()) {
                    Log.d("ServiceManagerBrowse", "fetchVideos requestId=" + requestId + " loMoId=" + loMo2.getId() + " fromVideo=" + n + " toVideo=" + n2);
                }
                final INetflixService service = this.mgr.getService();
                boolean b4;
                if (service != null) {
                    service.getBrowse().fetchVideos(loMo2, n, n2, b, b2, b3, this.mgr.getClientId(), requestId);
                    b4 = true;
                }
                else {
                    Log.w("ServiceManagerBrowse", "fetchVideos:: service is not available");
                    b4 = false;
                }
                // monitorexit(this)
                return b4;
            }
            continue;
        }
    }
    
    @Override
    public boolean flushCaches() {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().flushCaches();
            return true;
        }
        Log.w("ServiceManagerBrowse", "flushCaches:: service is not available");
        return false;
    }
    
    @Override
    public ModelProxy<?> getModelProxy() {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            return service.getBrowse().getModelProxy();
        }
        Log.w("ServiceManagerBrowse", "getModelProxy:: service is not available");
        return null;
    }
    
    @Override
    public void invalidateCachedEpisodes(final String s, final VideoType videoType) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "purgeCachedEpisodes id=" + s2);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().invalidateCachedEpisodes(s2, videoType);
        }
        else {
            Log.w("ServiceManagerBrowse", "purgeCachedEpisodes:: service is not available");
        }
    }
    // monitorexit(this)
    
    @Override
    public void logBillboardActivity(final Video video, final BillboardInteractionType billboardInteractionType) {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().logBillboardActivity(video, billboardInteractionType);
            return;
        }
        Log.w("ServiceManagerBrowse", "logBillboardActivity:: service is not available");
    }
    
    @Override
    public void markNotificationAsRead(final SocialNotificationSummary socialNotificationSummary) {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().markNotificationAsRead(socialNotificationSummary);
            return;
        }
        Log.w("ServiceManagerBrowse", "markNotificationAsRead:: service is not available");
    }
    
    @Override
    public void markNotificationsAsRead(final List<SocialNotificationSummary> list) {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().markNotificationsAsRead(list);
            return;
        }
        Log.w("ServiceManagerBrowse", "markNotificationsAsRead:: service is not available");
    }
    
    @Override
    public boolean prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final boolean b2, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "prefetchGenreLoLoMo requestId=" + requestId + " genreId=" + s + " fromLoMo=" + n + " toLoMo=" + n2 + " fromVideo=" + n3 + " toVideo=" + n4 + "includeBoxshots=" + b2);
            }
            final INetflixService service = this.mgr.getService();
            boolean b3;
            if (service != null) {
                service.getBrowse().prefetchGenreLoLoMo(s, n, n2, n3, n4, b, b2, this.mgr.getClientId(), requestId);
                b3 = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "prefetchGenreLoLoMo:: service is not available");
                b3 = false;
            }
            return b3;
        }
    }
    
    @Override
    public boolean prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final boolean b3, final ManagerCallback managerCallback) {
        final int requestId = this.mgr.getRequestId(managerCallback);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "prefetchLoLoMo requestId=" + requestId + " fromLoMo=" + n + " toLoMo=" + n2 + " fromVideo=" + n3 + " toVideo=" + n4 + " fromCWVideo=" + n5 + " toCWVideo=" + n6 + " includeExtraCharacters=" + b + "includeBoxshots=" + b3);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, b3, this.mgr.getClientId(), requestId);
            return true;
        }
        Log.w("ServiceManagerBrowse", "prefetchLoLoMo:: service is not available");
        return false;
    }
    
    @Override
    public void refreshAll() {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().refreshAll();
            return;
        }
        Log.w("ServiceManagerBrowse", "refreshAll:: service is not available");
    }
    
    @Override
    public void refreshCw() {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().refreshCw();
            return;
        }
        Log.w("ServiceManagerBrowse", "refreshCw:: service is not available");
    }
    
    @Override
    public void refreshIq() {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().refreshIq();
            return;
        }
        Log.w("ServiceManagerBrowse", "refreshIq:: service is not available");
    }
    
    @Override
    public void refreshSocialNotifications(final boolean b) {
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().refreshSocialNotifications(b, false, null);
            return;
        }
        Log.w("ServiceManagerBrowse", "refreshSocialNotifications:: service is not available");
    }
    
    @Override
    public boolean removeFromQueue(final String s, final VideoType videoType, final String s2, final ManagerCallback managerCallback) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int wrappedRequestId = this.mgr.getWrappedRequestId(managerCallback, s);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "removeFromQueue requestId=" + wrappedRequestId + " id=" + s);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().removeFromQueue(s, videoType, s2, this.mgr.getClientId(), wrappedRequestId);
            return true;
        }
        Log.w("ServiceManagerBrowse", "removeFromQueue:: service is not available");
        return false;
    }
    
    @Override
    public boolean searchNetflix(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int requestId = this.mgr.getRequestId(managerCallback);
            if (Log.isLoggable()) {
                Log.d("ServiceManagerBrowse", "searchNetflix requestId=" + requestId);
            }
            final INetflixService service = this.mgr.getService();
            boolean b;
            if (service != null) {
                service.getBrowse().searchNetflix(s, this.mgr.getClientId(), requestId);
                b = true;
            }
            else {
                Log.w("ServiceManagerBrowse", "searchNetflix:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    @Override
    public void sendThanksToSocialNotification(final SocialNotificationSummary socialNotificationSummary, final ManagerCallback managerCallback) {
        final int requestId = this.mgr.getRequestId(managerCallback);
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "sendThanksToSocialNotification requestId=" + requestId);
        }
        final INetflixService service = this.mgr.getService();
        if (service != null) {
            service.getBrowse().sendThanksToSocialNotification(socialNotificationSummary, this.mgr.getClientId(), requestId);
            return;
        }
        Log.w("ServiceManagerBrowse", "sendThanksToSocialNotification:: service is not available");
    }
    
    @Override
    public boolean setVideoRating(final String s, final VideoType videoType, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int requestId = this.mgr.getRequestId(managerCallback);
        final String s2;
        if (Log.isLoggable()) {
            Log.d("ServiceManagerBrowse", "setVideoRating requestId=" + requestId + " id=" + s2);
        }
        final INetflixService service = this.mgr.getService();
        boolean b;
        if (service != null) {
            service.getBrowse().setVideoRating(s2, videoType, n, n2, this.mgr.getClientId(), requestId);
            b = true;
        }
        else {
            Log.w("ServiceManagerBrowse", "setVideoRating:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
}
