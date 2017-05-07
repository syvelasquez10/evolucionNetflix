// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.List;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.os.IBinder;
import android.content.ComponentName;
import com.netflix.mediaclient.service.NetflixService;
import android.content.ServiceConnection;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class ServiceManager
{
    public static final String BROWSE_AGENT_CW_REFRESH = "com.netflix.mediaclient.intent.action.BA_CW_REFRESH";
    public static final String BROWSE_AGENT_EPISODE_REFRESH = "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH";
    public static final String BROWSE_AGENT_IQ_REFRESH = "com.netflix.mediaclient.intent.action.BA_IQ_REFRESH";
    public static final String BROWSE_PARAM_CUR_EPISODE_NUMBER = "curEpisodeNumber";
    public static final String BROWSE_PARAM_CUR_SEASON_NUMBER = "curSeasonNumber";
    private static final String ERROR_PARAM_WITH_NULL = "Parameter cannot be null";
    public static final String LOCAL_PLAYER_PLAY_START = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START";
    public static final String LOCAL_PLAYER_PLAY_STOP = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP";
    private static final String TAG = "ServiceManager";
    private AddToMyListWrapper addToMyListWrapper;
    private final NetflixActivity mActivity;
    private ManagerStatusListener mCallback;
    private final CallbackMuxer mCallbackMuxer;
    private int mClientId;
    private final ServiceConnection mConnection;
    private NetflixService mLocalService;
    private boolean mReady;
    private INetflixService mService;
    private ServiceListener mServiceListener;
    
    public ServiceManager(final NetflixActivity mActivity, final ManagerStatusListener mCallback) {
        this.mClientId = -1;
        this.mCallbackMuxer = new CallbackMuxer();
        this.mConnection = (ServiceConnection)new ServiceConnection() {
            public final void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                Log.d("ServiceManager", "ServiceConnected with IBinder: " + binder);
                final NetflixService.LocalBinder localBinder = (NetflixService.LocalBinder)binder;
                ServiceManager.this.mService = localBinder.getService();
                ServiceManager.this.addToMyListWrapper = new AddToMyListWrapper(ServiceManager.this);
                ServiceManager.this.mLocalService = localBinder.getService();
                if (ServiceManager.this.mServiceListener == null) {
                    ServiceManager.this.mServiceListener = new ServiceListener();
                }
                ServiceManager.this.mService.registerCallback(ServiceManager.this.mServiceListener);
            }
            
            public final void onServiceDisconnected(final ComponentName componentName) {
                Log.d("ServiceManager", "onServiceDisconnected");
                if (ServiceManager.this.mCallback != null) {
                    ServiceManager.this.mCallback.onManagerUnavailable(ServiceManager.this, -2);
                    ServiceManager.this.mCallback = null;
                }
                ServiceManager.this.mLocalService = null;
                ServiceManager.this.mService = null;
            }
        };
        if (mCallback == null) {
            throw new IllegalStateException("listener is null");
        }
        Log.d("ServiceManager", "ServiceManager created");
        this.mCallback = mCallback;
        (this.mActivity = mActivity).startService(new Intent((Context)this.mActivity, (Class)NetflixService.class));
        if (!this.mActivity.bindService(this.getServiceIntent(), this.mConnection, 1)) {
            Log.e("ServiceManager", "ServiceManager could not bind to NetflixService!");
        }
    }
    
    private int addCallback(final ManagerCallback managerCallback) {
        if (managerCallback != null) {
            return this.mCallbackMuxer.muxCallback(managerCallback);
        }
        Log.e("ServiceManager", "Callback that is added is null!");
        return 0;
    }
    
    private ManagerCallback getManagerCallback(final int n) {
        return this.mCallbackMuxer.demuxCallback(n);
    }
    
    private Intent getServiceIntent() {
        return new Intent((Context)this.mActivity, (Class)NetflixService.class);
    }
    
    private INetflixService validateService() {
        if (this.mService == null || this.mClientId < 0) {
            return null;
        }
        return this.mService;
    }
    
    private ManagerCallback wrapForAddToList(final ManagerCallback managerCallback, final String s) {
        return new AddToListCallbackWrapper(managerCallback, s);
    }
    
    boolean addToQueue(final String s, final int n, final ManagerCallback managerCallback) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int addCallback = this.addCallback(this.wrapForAddToList(managerCallback, s));
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "addToQueue requestId=" + addCallback + " id=" + s);
        }
        final INetflixService validateService = this.validateService();
        if (validateService != null) {
            validateService.addToQueue(s, n, this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "addToQueue:: service is not available");
        return false;
    }
    
    public boolean cancelAllImageLoaderRequests() {
        ThreadUtils.assertOnMain();
        final ImageLoader imageLoader = this.getImageLoader();
        if (imageLoader != null) {
            Log.d("ServiceManager", "Cancelling all pending image requests");
            imageLoader.cancelAllRequests();
            return true;
        }
        Log.w("ServiceManager", "Failed to cancell all pending image requests. Image loader not available!");
        return false;
    }
    
    public boolean connectWithFacebook(final String s, final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.connectWithFacebook(s, this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "connectWithFacebook:: service is not available");
        return false;
    }
    
    public AddToListData.StateListener createAddToMyListWrapper(final DetailsActivity detailsActivity, final TextView textView) {
        return this.addToMyListWrapper.createAddToMyListWrapper(detailsActivity, textView);
    }
    
    public boolean dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.dumpHomeLoLoMosAndVideos(s, s2);
            return true;
        }
        Log.w("ServiceManager", "dumpHomeLoLoMosAndVideos:: service is not available");
        return false;
    }
    
    public boolean dumpHomeLoLoMosAndVideosToLog() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.dumpHomeLoLoMosAndVideosToLog();
            return true;
        }
        Log.w("ServiceManager", "dumpHomeLoLoMosAndVideos:: service is not available");
        return false;
    }
    
    public boolean dumpHomeLoMos() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.dumpHomeLoMos();
            return true;
        }
        Log.w("ServiceManager", " dumpHomeLoMos:: service is not available");
        return false;
    }
    
    public boolean fetchCWVideos(final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "fetchCWLoMo requestId=" + addCallback + " fromVideo=" + n + " toVideo=" + n2);
            }
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.fetchCWVideos(n, n2, this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "fetchCWVideos:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public boolean fetchEpisodeDetails(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchEpisodeDetails requestId=" + addCallback + " episodeId=" + s2);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchEpisodeDetails(s2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchEpisodeDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchEpisodes(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchEpisodes requestId=" + addCallback + " id=" + s2 + " fromEpisode=" + n + " toEpisode=" + n2);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchEpisodes(s2, n, n2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchEpisodes:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchGenreLists(final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "fetchGenreLists requestId=" + addCallback);
            }
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.fetchGenreLists(this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "fetchGenreLists:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public boolean fetchGenreVideos(final LoMo loMo, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(loMo.getId())) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final LoMo loMo2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchGenreVideos requestId=" + addCallback + " genreLoMoId=" + loMo2.getId() + " fromVideo=" + n + " toVideo=" + n2);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchGenreVideos(loMo2, n, n2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchGenreVideos:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchGenres(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        while (true) {
            boolean b = false;
            final INetflixService validateService;
            Label_0099: {
                synchronized (this) {
                    validateService = this.validateService();
                    if (validateService == null) {
                        Log.w("ServiceManager", "fetchGenres:: service is not available");
                    }
                    else {
                        final String stackTraceString = android.util.Log.getStackTraceString((Throwable)new Exception("Parameter cannot be null"));
                        Log.w("ServiceManager", String.format("fetchGenres:: stack:%s", stackTraceString));
                        if (!StringUtils.isEmpty(s)) {
                            break Label_0099;
                        }
                        validateService.getClientLogging().getErrorLogging().logHandledException(stackTraceString);
                    }
                    return b;
                }
            }
            final int addCallback = this.addCallback(managerCallback);
            final String s2;
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "fetchGenres requestId=" + addCallback + " id=" + s2);
            }
            validateService.fetchGenres(s2, n, n2, this.mClientId, addCallback);
            b = true;
            return b;
        }
    }
    
    public boolean fetchIQVideos(final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "fetchIQLoMo requestId=" + addCallback + " fromVideo=" + n + " toVideo=" + n2);
            }
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.fetchIQVideos(n, n2, this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "fetchIQVideos:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public boolean fetchKidsCharacterDetails(final String s, final ManagerCallback managerCallback) {
        boolean b = true;
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", String.format("fetchKidsCharacterDetails requestId=%d,  characterId=%s", addCallback, s2));
        }
        if (this.validateService() != null) {
            this.mService.fetchKidsCharacterDetails(s2, this.mClientId, addCallback);
        }
        else {
            Log.w("ServiceManager", "fetchKidsCharacterDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchLoLoMoSummary(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchLoLoMoSummary requestId=" + addCallback + " category=" + s2);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchLoLoMoSummary(s2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchLoLoMoSummary:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchLoMos(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchLoLoMo requestId=" + addCallback + " category=" + s2 + " fromLoMo=" + n + " toLoMo=" + n2);
        }
        final INetflixService mService = this.mService;
        boolean b;
        if (mService != null) {
            mService.fetchLoMos(s2, n, n2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchLoMos:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchMovieDetails(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final String s2;
        final int addCallback = this.addCallback(this.wrapForAddToList(managerCallback, s2));
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchMovieDetails requestId=" + addCallback + " movieId=" + s2);
        }
        boolean b;
        if (this.validateService() != null) {
            this.mService.fetchMovieDetails(s2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchMovieDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchPostPlayVideos(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "fetchPostPlayVideos requestId=" + addCallback + " currentPlayableId=" + s);
            }
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.fetchPostPlayVideos(s, this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "fetchPostPlayVideos:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public boolean fetchResource(final String s, final IClientLogging.AssetType assetType, final ManagerCallback managerCallback) {
        boolean b = false;
        // monitorenter(this)
        if (s != null) {
            try {
                final int addCallback = this.addCallback(managerCallback);
                if (Log.isLoggable("ServiceManager", 3)) {
                    Log.d("ServiceManager", "fetchResource requestId=" + addCallback + " resourceUrl=" + s);
                }
                final INetflixService validateService = this.validateService();
                if (validateService != null) {
                    validateService.fetchResource(s, assetType, this.mClientId, addCallback);
                    b = true;
                }
                else {
                    Log.w("ServiceManager", "fetchResource:: service is not available");
                }
            }
            finally {
            }
            // monitorexit(this)
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchSeasonDetails(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchSeasonDetails requestId=" + addCallback + " seasonId=" + s2);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchSeasonDetails(s2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchSeasonDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchSeasons(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchSeasons requestId=" + addCallback + " id=" + s2 + " fromSeason=" + n + " toSeason=" + n2);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchSeasons(s2, n, n2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchSeasons:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchShowDetails(final String s, final String s2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final String s3;
        final int addCallback = this.addCallback(this.wrapForAddToList(managerCallback, s3));
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "fetchShowDetails requestId=" + addCallback + " id=" + s3);
        }
        final INetflixService validateService = this.validateService();
        boolean b;
        if (validateService != null) {
            validateService.fetchShowDetails(s3, s2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "fetchShowDetails:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public boolean fetchSimilarVideosForPerson(final String s, final int n, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.fetchSimilarVideosForPerson(s, n, this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "searchNetflix:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public boolean fetchSimilarVideosForQuerySuggestion(final String s, final int n, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.fetchSimilarVideosForQuerySuggestion(s, n, this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "searchNetflix:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public boolean fetchVideos(final LoMo loMo, final int n, final int n2, final ManagerCallback managerCallback) {
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
                final int addCallback = this.addCallback(managerCallback);
                final LoMo loMo2;
                if (Log.isLoggable("ServiceManager", 3)) {
                    Log.d("ServiceManager", "fetchVideos requestId=" + addCallback + " loMoId=" + loMo2.getId() + " fromVideo=" + n + " toVideo=" + n2);
                }
                final INetflixService validateService = this.validateService();
                boolean b;
                if (validateService != null) {
                    validateService.fetchVideos(loMo2, n, n2, this.mClientId, addCallback);
                    b = true;
                }
                else {
                    Log.w("ServiceManager", "fetchVideos:: service is not available");
                    b = false;
                }
                // monitorexit(this)
                return b;
            }
            continue;
        }
    }
    
    public boolean flushCaches() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.flushCaches();
            return true;
        }
        Log.w("ServiceManager", "flushCaches:: service is not available");
        return false;
    }
    
    public NetflixActivity getActivity() {
        return this.mActivity;
    }
    
    public List<? extends UserProfile> getAllProfiles() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getAllProfiles();
        }
        Log.w("ServiceManager", "getAllProfiles:: service is not available");
        return null;
    }
    
    public IClientLogging getClientLogging() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getClientLogging();
        }
        Log.w("ServiceManager", "getClientLogging:: service is not available");
        return null;
    }
    
    public ServiceAgent.ConfigurationAgentInterface getConfiguration() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getConfiguration();
        }
        Log.w("ServiceManager", "getConfiguration: service is not available");
        return null;
    }
    
    public String getCurrentAppLocale() {
        final INetflixService validateService = this.validateService();
        if (validateService != null) {
            return validateService.getCurrentAppLocale();
        }
        Log.w("ServiceManager", "getCurrentAppLocale:: service is not available");
        return null;
    }
    
    public UserProfile getCurrentProfile() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getCurrentProfile();
        }
        Log.w("ServiceManager", "getCurrentProfile:: service is not available");
        return null;
    }
    
    public DeviceCategory getDeviceCategory() {
        if (this.mService != null) {
            return this.mService.getDeviceCategory();
        }
        Log.w("ServiceManager", "getDeviceCategory:: service is not available");
        return null;
    }
    
    public EsnProvider getESNProvider() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getESN();
        }
        Log.w("ServiceManager", "getESNProvider:: service is not available");
        return null;
    }
    
    public ImageLoader getImageLoader() {
        final NetflixService mLocalService = this.mLocalService;
        if (mLocalService == null) {
            Log.w("ServiceManager", "getImageLoader:: Netflix service is not available, return null.");
            return null;
        }
        return mLocalService.getImageLoader();
    }
    
    public IMdx getMdx() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getMdx();
        }
        Log.w("ServiceManager", "getMdx:: service is not available");
        return null;
    }
    
    public String getNrdpComponentVersion(final NrdpComponent nrdpComponent) {
        if (this.mService != null) {
            return this.mService.getNrdpComponentVersion(nrdpComponent);
        }
        Log.w("ServiceManager", "getNrdpComponentVersion:: service is not available");
        return null;
    }
    
    public IPlayer getPlayer() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getNflxPlayer();
        }
        Log.w("ServiceManager", "getPlayer:: service is not available");
        return null;
    }
    
    public IPushNotification getPushNotification() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getPushNotification();
        }
        Log.w("ServiceManager", "getPushNotification:: service is not available");
        return null;
    }
    
    public SignUpParams getSignUpParams() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getSignUpParams();
        }
        Log.w("ServiceManager", "getSignUpParams:: service is not available");
        return null;
    }
    
    public String getSoftwareVersion() {
        if (this.mService != null) {
            return this.mService.getSoftwareVersion();
        }
        Log.w("ServiceManager", "getSoftwareVersion:: service is not available");
        return null;
    }
    
    public boolean hideVideo(final String s, final ManagerCallback managerCallback) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int addCallback = this.addCallback(managerCallback);
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "hideVideo requestId=" + addCallback + " id=" + s);
        }
        final INetflixService validateService = this.validateService();
        if (validateService != null) {
            validateService.hideVideo(s, this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "hideVideo:: service is not available");
        return false;
    }
    
    public boolean isCurrentProfileFacebookConnected() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.isCurrentProfileFacebookConnected();
        }
        Log.w("ServiceManager", "isCurrentProfileFacebookConnected:: service is not available");
        return false;
    }
    
    public boolean isCurrentProfileIQEnabled() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.isCurrentProfileIQEnabled();
        }
        Log.w("ServiceManager", "isCurrentProfileIQEnabled:: service is not available");
        return false;
    }
    
    public boolean isDeviceHd() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.isDeviceHd();
        }
        Log.w("ServiceManager", "isDeviceHd:: service is not available");
        return false;
    }
    
    public boolean isProfileSwitchingDisabled() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.isProfileSwitchingDisabled();
        }
        Log.w("ServiceManager", "isProfileSwitchingDisabled:: service is not available");
        return false;
    }
    
    public boolean isReady() {
        return this.mReady;
    }
    
    public boolean isTablet() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.isTablet();
        }
        Log.w("ServiceManager", "isTablet:: service is not available");
        return false;
    }
    
    public boolean isUserLoggedIn() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.isUserLoggedIn();
        }
        Log.w("ServiceManager", "isUserLoggedIn:: service is not available");
        return false;
    }
    
    public void logBillboardActivity(final Video video, final BrowseAgent.BillboardActivityType billboardActivityType) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.logBillboardActivity(video, billboardActivityType);
            return;
        }
        Log.w("ServiceManager", "selectProfile:: service is not available");
    }
    
    public boolean loginUser(final String s, final String s2, final ManagerCallback managerCallback) {
        final int addCallback = this.addCallback(managerCallback);
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.loginUser(s, s2, this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "loginUser:: service is not available");
        return false;
    }
    
    public boolean loginUserByTokens(final ActivationTokens activationTokens, final ManagerCallback managerCallback) {
        final int addCallback = this.addCallback(managerCallback);
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.loginUserByTokens(activationTokens, this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "loginUserByTokens:: service is not available");
        return false;
    }
    
    public boolean logoutUser(final ManagerCallback managerCallback) {
        final int addCallback = this.addCallback(managerCallback);
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.logoutUser(this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "logoutUser:: service is not available");
        return false;
    }
    
    public boolean prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "prefetchGenreLoLoMo requestId=" + addCallback + " genreId=" + s + " fromLoMo=" + n + " toLoMo=" + n2 + " fromVideo=" + n3 + " toVideo=" + n4 + "includeBoxshots=" + b);
            }
            final INetflixService validateService = this.validateService();
            boolean b2;
            if (validateService != null) {
                validateService.prefetchGenreLoLoMo(s, n, n2, n3, n4, b, this.mClientId, addCallback);
                b2 = true;
            }
            else {
                Log.w("ServiceManager", "prefetchGenreLoLoMo:: service is not available");
                b2 = false;
            }
            return b2;
        }
    }
    
    public boolean prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final ManagerCallback managerCallback) {
        final int addCallback = this.addCallback(managerCallback);
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "prefetchLoLoMo requestId=" + addCallback + " fromLoMo=" + n + " toLoMo=" + n2 + " fromVideo=" + n3 + " toVideo=" + n4 + " fromCWVideo=" + n5 + " toCWVideo=" + n6 + " includeExtraCharacters=" + b + "includeBoxshots=" + b2);
        }
        final INetflixService validateService = this.validateService();
        if (validateService != null) {
            validateService.prefetchLoLoMo(n, n2, n3, n4, n5, n6, b, b2, this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "prefetchLoLoMo:: service is not available");
        return false;
    }
    
    public void refreshProfileSwitchingStatus() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.refreshProfileSwitchingStatus();
            return;
        }
        Log.w("ServiceManager", "refreshProfileSwitchingStatus:: service is not available");
    }
    
    public void registerAddToMyListListener(final String s, final AddToListData.StateListener stateListener) {
        final AddToMyListWrapper addToMyListWrapper = this.addToMyListWrapper;
        if (addToMyListWrapper != null) {
            addToMyListWrapper.register(s, stateListener);
        }
    }
    
    public void release() {
        synchronized (this) {
            if (this.mService != null) {
                if (this.mServiceListener != null) {
                    Log.d("ServiceManager", "ServiceManager unregisterCallback");
                    this.mService.unregisterCallback(this.mServiceListener);
                }
                Log.d("ServiceManager", "ServiceManager unbindService");
                this.mActivity.unbindService(this.mConnection);
                if (this.mCallbackMuxer != null) {
                    this.mCallbackMuxer.reset();
                }
                this.mReady = false;
            }
        }
    }
    
    boolean removeFromQueue(final String s, final ManagerCallback managerCallback) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Parameter cannot be null");
        }
        final int addCallback = this.addCallback(this.wrapForAddToList(managerCallback, s));
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "removeFromQueue requestId=" + addCallback + " id=" + s);
        }
        final INetflixService validateService = this.validateService();
        if (validateService != null) {
            validateService.removeFromQueue(s, this.mClientId, addCallback);
            return true;
        }
        Log.w("ServiceManager", "removeFromQueue:: service is not available");
        return false;
    }
    
    public boolean searchNetflix(final String s, final ManagerCallback managerCallback) {
        synchronized (this) {
            final int addCallback = this.addCallback(managerCallback);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "searchNetflix requestId=" + addCallback);
            }
            final INetflixService validateService = this.validateService();
            boolean b;
            if (validateService != null) {
                validateService.searchNetflix(s, this.mClientId, addCallback);
                b = true;
            }
            else {
                Log.w("ServiceManager", "searchNetflix:: service is not available");
                b = false;
            }
            return b;
        }
    }
    
    public void selectProfile(final String s) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.selectProfile(s);
            return;
        }
        Log.w("ServiceManager", "selectProfile:: service is not available");
    }
    
    public boolean setCurrentAppLocale(final String currentAppLocale) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.setCurrentAppLocale(currentAppLocale);
            return true;
        }
        Log.w("ServiceManager", "setCurrentAppLocale:: service is not available");
        return false;
    }
    
    public boolean setVideoRating(final String s, final int n, final int n2, final ManagerCallback managerCallback) {
        synchronized (this) {
            if (StringUtils.isEmpty(s)) {
                throw new IllegalArgumentException("Parameter cannot be null");
            }
        }
        final int addCallback = this.addCallback(managerCallback);
        final String s2;
        if (Log.isLoggable("ServiceManager", 3)) {
            Log.d("ServiceManager", "setVideoRating requestId=" + addCallback + " id=" + s2);
        }
        boolean b;
        if (this.validateService() != null) {
            this.mService.setVideoRating(s2, n, n2, this.mClientId, addCallback);
            b = true;
        }
        else {
            Log.w("ServiceManager", "setVideoRating:: service is not available");
            b = false;
        }
        // monitorexit(this)
        return b;
    }
    
    public void unregisterAddToMyListListener(final String s, final AddToListData.StateListener stateListener) {
        final AddToMyListWrapper addToMyListWrapper = this.addToMyListWrapper;
        if (addToMyListWrapper != null) {
            addToMyListWrapper.unregister(s, stateListener);
        }
    }
    
    public boolean verifyPin(final String s, final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.verifyPin(s, this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "verifyPin:: service is not available");
        return false;
    }
    
    private class AddToListCallbackWrapper extends SimpleManagerCallback
    {
        private final ManagerCallback cb;
        private final String videoId;
        
        public AddToListCallbackWrapper(final ManagerCallback cb, final String videoId) {
            this.cb = cb;
            this.videoId = videoId;
            final AddToMyListWrapper access$700 = ServiceManager.this.addToMyListWrapper;
            if (access$700 != null) {
                access$700.updateToLoading(videoId);
            }
        }
        
        private void updateListeners(final int n, final boolean b, final boolean b2) {
            final AddToMyListWrapper access$700 = ServiceManager.this.addToMyListWrapper;
            if (access$700 == null) {
                return;
            }
            if (n == 0) {
                access$700.updateState(this.videoId, b);
                return;
            }
            access$700.updateToError(n, this.videoId, b2);
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final int n) {
            super.onMovieDetailsFetched(movieDetails, n);
            this.cb.onMovieDetailsFetched(movieDetails, n);
            this.updateListeners(n, movieDetails != null && movieDetails.isInQueue(), false);
        }
        
        @Override
        public void onQueueAdd(final int n) {
            this.onQueueAdd(n);
            this.cb.onQueueAdd(n);
            this.updateListeners(n, true, true);
        }
        
        @Override
        public void onQueueRemove(final int n) {
            this.onQueueRemove(n);
            this.cb.onQueueRemove(n);
            this.updateListeners(n, false, true);
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final int n) {
            super.onShowDetailsFetched(showDetails, n);
            this.cb.onShowDetailsFetched(showDetails, n);
            this.updateListeners(n, showDetails != null && showDetails.isInQueue(), false);
        }
    }
    
    private static class CallbackMuxer
    {
        private final ArrayList<MuxedCallback> muxedCallbacks;
        
        private CallbackMuxer() {
            this.muxedCallbacks = new ArrayList<MuxedCallback>();
        }
        
        public ManagerCallback demuxCallback(final int n) {
            synchronized (this) {
                for (final MuxedCallback muxedCallback : this.muxedCallbacks) {
                    if (muxedCallback.getRequestId() == n) {
                        this.muxedCallbacks.remove(muxedCallback);
                        return muxedCallback.getDemuxedCallback();
                    }
                }
                return null;
            }
        }
        
        public int muxCallback(final ManagerCallback managerCallback) {
            synchronized (this) {
                final MuxedCallback muxedCallback = new MuxedCallback(managerCallback);
                this.muxedCallbacks.add(muxedCallback);
                return muxedCallback.getRequestId();
            }
        }
        
        public void reset() {
            synchronized (this) {
                this.muxedCallbacks.clear();
            }
        }
        
        private static class MuxedCallback
        {
            private static int sRequestIdCounter;
            private final ManagerCallback callback;
            private final int requestId;
            
            static {
                MuxedCallback.sRequestIdCounter = 0;
            }
            
            public MuxedCallback(final ManagerCallback callback) {
                ++MuxedCallback.sRequestIdCounter;
                this.requestId = MuxedCallback.sRequestIdCounter;
                this.callback = callback;
            }
            
            public ManagerCallback getDemuxedCallback() {
                return this.callback;
            }
            
            public int getRequestId() {
                return this.requestId;
            }
        }
    }
    
    private class ServiceListener implements INetflixServiceCallback
    {
        @Override
        public int hashCode() {
            int hashCode;
            final int n = hashCode = super.hashCode();
            if (n < 0) {
                hashCode = -n;
            }
            return hashCode;
        }
        
        @Override
        public void onCWVideosFetched(final int n, final List<CWVideo> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onCWVideosFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onCWVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onCWVideosFetched requestId " + n);
                return;
            }
            access$400.onCWVideosFetched(list, n2);
        }
        
        @Override
        public void onConnectWithFacebookComplete(final int n, final int n2, final String s) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onConnectWithFacebookComplete requestId=" + n + " statusCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onConnectWithFacebookComplete requestId " + n);
                return;
            }
            access$400.onConnectWithFacebookComplete(n2, s);
        }
        
        @Override
        public void onEpisodeDetailsFetched(final int n, final EpisodeDetails episodeDetails, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onEpisodeDetailsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onEpisodeDetailsFetched requestedEdp=" + episodeDetails);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onEpisodeDetailsFetched requestId " + n);
                return;
            }
            access$400.onEpisodeDetailsFetched(episodeDetails, n2);
        }
        
        @Override
        public void onEpisodesFetched(final int n, final List<EpisodeDetails> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onEpisodesFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onEpisodesFetched requestedEpisodes=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onEpisodesFetched requestId " + n);
                return;
            }
            access$400.onEpisodesFetched(list, n2);
        }
        
        @Override
        public void onGenreListsFetched(final int n, final List<GenreList> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onGenreListsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onGenreListsFetched requestedGenreLists=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onGenreListsFetched requestId " + n);
                return;
            }
            access$400.onGenreListsFetched(list, n2);
        }
        
        @Override
        public void onGenreLoLoMoPrefetched(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onGenreLoLoMoPrefetched requestId=" + n + " statusCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onGenreLoLoMoPrefetched requestId " + n);
                return;
            }
            access$400.onGenreLoLoMoPrefetched(n2);
        }
        
        @Override
        public void onGenresFetched(final int n, final List<Genre> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onGenresFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onGenresFetched requestedGenres=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onGenresFetched requestId " + n);
                return;
            }
            access$400.onGenresFetched(list, n2);
        }
        
        @Override
        public void onKidsCharacterDetailsFetched(final int n, final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onKidsCharacterDetailsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onKidsCharacterDetailsFetched kidsCharacterDetails=" + kidsCharacterDetails);
                if (kidsCharacterDetails != null) {
                    Log.d("ServiceManager", "onKidsCharacterDetailsFetched gallery size=" + kidsCharacterDetails.getGallery().size());
                    Log.d("ServiceManager", "onKidsCharacterDetailsFetched gallery track id=" + kidsCharacterDetails.getGalleryTrackId());
                }
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onKidsCharacterDetailsFetched requestId " + n);
                return;
            }
            access$400.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, n2);
        }
        
        @Override
        public void onLoLoMoPrefetched(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoLoMoPrefetched requestId=" + n + " statusCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLoLoMoPrefetched requestId " + n);
                return;
            }
            access$400.onLoLoMoPrefetched(n2);
        }
        
        @Override
        public void onLoLoMoSummaryFetched(final int n, final LoLoMo loLoMo, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoLoMoSummaryFetched requestId=" + n + " erroCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLoLoMoSummaryFetched requestId " + n);
                return;
            }
            access$400.onLoLoMoSummaryFetched(loLoMo, n2);
        }
        
        @Override
        public void onLoMosFetched(final int n, final List<LoMo> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoMosFetched requestId=" + n + " statusCode=" + n2);
            }
            if (Log.isLoggable("ServiceManager", 2)) {
                Log.v("ServiceManager", "onLoMosFetched requestedLoMos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLoMosFetched requestId " + n);
                return;
            }
            access$400.onLoMosFetched(list, n2);
        }
        
        @Override
        public void onLoginComplete(final int n, final int n2, final String s) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoginComplete requestId=" + n + " statusCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLoginComplete requestId " + n);
                return;
            }
            access$400.onLoginComplete(n2, s);
        }
        
        @Override
        public void onLogoutComplete(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLogoutComplete requestId=" + n + " statusCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLogoutComplete requestId " + n);
                return;
            }
            access$400.onLogoutComplete(n2);
        }
        
        @Override
        public void onMovieDetailsFetched(final int n, final MovieDetails movieDetails, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onMovieDetailsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onMovieDetailsFetched requestedMdp=" + movieDetails);
                if (movieDetails != null) {
                    Log.d("ServiceManager", "onMovieDetailsFetched sims size=" + movieDetails.getSimilars().size());
                    Log.d("ServiceManager", "onMovieDetailsFetched sims track id=" + movieDetails.getSimilarsTrackId());
                }
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onMovieDetailsFetched requestId " + n);
                return;
            }
            access$400.onMovieDetailsFetched(movieDetails, n2);
        }
        
        @Override
        public void onPinVerified(final int n, final boolean b, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onPinVerified requestId=" + n + " statusCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onPinVerified requestId " + n);
                return;
            }
            access$400.onPinVerified(b, n2);
        }
        
        @Override
        public void onPostPlayVideosFetched(final int n, final List<PostPlayVideo> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onPostPlayVideosFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onPostPlayVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onPostPlayVideoFetched requestId " + n);
                return;
            }
            access$400.onPostPlayVideosFetched(list, n2);
        }
        
        @Override
        public void onQueueAdd(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onQueueAdd requestId=" + n + " erroCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onQueueAdd requestId " + n);
                return;
            }
            access$400.onQueueAdd(n2);
        }
        
        @Override
        public void onQueueRemove(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onQueueRemove requestId=" + n + " erroCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onQueueRemove requestId " + n);
                return;
            }
            access$400.onQueueRemove(n2);
        }
        
        @Override
        public void onResourceFetched(final int n, final String s, final String s2, final int n2) {
            if (Log.isLoggable("ServiceManager", 2)) {
                Log.v("ServiceManager", "onResourceFetched requestId=" + n + " requestedUrl=" + s + " localUrl=" + s2 + " statusCode=" + n2);
            }
            final ManagerCallback demuxCallback = ServiceManager.this.mCallbackMuxer.demuxCallback(n);
            if (demuxCallback == null) {
                Log.d("ServiceManager", "No callback for onResourceFetched requestId " + n);
                return;
            }
            demuxCallback.onResourceFetched(s, s2, n2);
        }
        
        @Override
        public void onSearchResultsFetched(final int n, final SearchResults searchResults, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSearchResultsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onSearchResultsFetched results=" + searchResults);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSearchResultsFetched requestId " + n);
                return;
            }
            access$400.onSearchResultsFetched(searchResults, n2);
        }
        
        @Override
        public void onSeasonDetailsFetched(final int n, final SeasonDetails seasonDetails, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSeasonDetailsFetched requestId=" + n + " erroCode=" + n2);
                final StringBuilder append = new StringBuilder().append("onSeasonDetailsFetched seasonDetailsId=");
                String id;
                if (seasonDetails == null) {
                    id = "n/a";
                }
                else {
                    id = seasonDetails.getId();
                }
                Log.d("ServiceManager", append.append(id).toString());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSeasonDetailsFetched requestId " + n);
                return;
            }
            access$400.onSeasonDetailsFetched(seasonDetails, n2);
        }
        
        @Override
        public void onSeasonsFetched(final int n, final List<SeasonDetails> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSeasonsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onSeasonsFetched requestedSeasons=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSeasonsFetched requestId " + n);
                return;
            }
            access$400.onSeasonsFetched(list, n2);
        }
        
        @Override
        public void onServiceReady(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onServiceReady clientId=" + n + " statusCode=" + n2);
            }
            ServiceManager.this.mClientId = n;
            final ManagerStatusListener access$200 = ServiceManager.this.mCallback;
            if (access$200 != null) {
                if (n2 < 0) {
                    access$200.onManagerUnavailable(ServiceManager.this, n2);
                    return;
                }
                ServiceManager.this.mReady = true;
                access$200.onManagerReady(ServiceManager.this, n2);
            }
        }
        
        @Override
        public void onShowDetailsFetched(final int n, final ShowDetails showDetails, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onShowDetailsFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onShowDetailsFetched requestedSdp=" + showDetails);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onShowDetailsFetched requestId " + n);
                return;
            }
            access$400.onShowDetailsFetched(showDetails, n2);
        }
        
        @Override
        public void onSimilarVideosFetched(final int n, final VideoList list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSimilarVideosFetched requestId=" + n + " erroCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSimilarVideosFetched requestId " + n);
                return;
            }
            access$400.onSimilarVideosFetched(list, n2);
        }
        
        @Override
        public void onVideoHide(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onVideoHide requestId=" + n + " erroCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onVideoHide requestId " + n);
                return;
            }
            access$400.onVideoHide(n2);
        }
        
        @Override
        public void onVideoRatingSet(final int n, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onVideoRatingSet requestId=" + n + " erroCode=" + n2);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onVideoRatingSet requestId " + n);
                return;
            }
            access$400.onVideoRatingSet(n2);
        }
        
        @Override
        public void onVideosFetched(final int n, final List<Video> list, final int n2) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onVideosFetched requestId=" + n + " erroCode=" + n2);
                Log.d("ServiceManager", "onVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onVideosFetched requestId " + n);
                return;
            }
            access$400.onVideosFetched(list, n2);
        }
    }
}
