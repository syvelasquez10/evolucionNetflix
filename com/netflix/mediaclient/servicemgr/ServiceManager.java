// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import com.netflix.mediaclient.servicemgr.model.SearchVideoList;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import com.netflix.mediaclient.servicemgr.model.details.PostPlayVideo;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.LoLoMo;
import com.netflix.mediaclient.servicemgr.model.details.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.model.genre.Genre;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.mediaclient.android.app.NetflixStatus;
import java.util.Iterator;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.Log;
import android.os.IBinder;
import android.content.ComponentName;
import com.netflix.mediaclient.service.NetflixService;
import android.content.ServiceConnection;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class ServiceManager implements IServiceManagerAccess
{
    public static final String BROWSE_AGENT_CW_REFRESH = "com.netflix.mediaclient.intent.action.BA_CW_REFRESH";
    public static final String BROWSE_AGENT_EPISODE_REFRESH = "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH";
    public static final String BROWSE_AGENT_IQ_REFRESH = "com.netflix.mediaclient.intent.action.BA_IQ_REFRESH";
    public static final String BROWSE_PARAM_CUR_EPISODE_NUMBER = "curEpisodeNumber";
    public static final String BROWSE_PARAM_CUR_SEASON_NUMBER = "curSeasonNumber";
    public static final String LOCAL_PLAYER_PLAY_START = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START";
    public static final String LOCAL_PLAYER_PLAY_STOP = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP";
    public static final String SOCIAL_NOTIFICATIONS_LIST_HAS_UNREAD = "notifications_list_has_unread";
    public static final String SOCIAL_NOTIFICATIONS_LIST_UPDATED = "com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED";
    private static final String TAG = "ServiceManager";
    private AddToMyListWrapper addToMyListWrapper;
    private final NetflixActivity mActivity;
    private final IBrowseManager mBrowseManager;
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
                    ServiceManager.this.mCallback.onManagerUnavailable(ServiceManager.this, CommonStatus.INTERNAL_ERROR);
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
        this.mBrowseManager = new BrowseManager(this);
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
    
    public void addProfile(final String s, final boolean b, final String s2, final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        final int addCallback = this.addCallback(managerCallback);
        if (mService != null) {
            mService.addProfile(s, b, s2, this.mClientId, addCallback);
            return;
        }
        Log.w("ServiceManager", "addProfile:: service is not available");
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
    
    public AddToListData.StateListener createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final int n, final boolean b) {
        return this.addToMyListWrapper.createAddToMyListWrapper(netflixActivity, textView, s, n, b);
    }
    
    public AddToListData.StateListener createAddToMyListWrapper(final DetailsActivity detailsActivity, final TextView textView, final boolean b) {
        return this.addToMyListWrapper.createAddToMyListWrapper(detailsActivity, textView, detailsActivity.getVideoId(), detailsActivity.getTrackId(), b);
    }
    
    public void editProfile(final String s, final String s2, final boolean b, final String s3, final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        final int addCallback = this.addCallback(managerCallback);
        if (mService != null) {
            mService.editProfile(s, s2, b, s3, this.mClientId, addCallback);
            return;
        }
        Log.w("ServiceManager", "editProfile:: service is not available");
    }
    
    public boolean fetchAvailableAvatarsList(final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.getAvailableAvatarsList(this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "fetchAvailableAvatarsList:: service is not available");
        return false;
    }
    
    public int fetchFriendsForRecommendationList(final String s, final int n, final String s2, final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            final int addCallback = this.addCallback(managerCallback);
            mService.getFriendsForRecommendationList(s, n, s2, this.mClientId, addCallback);
            return addCallback;
        }
        Log.w("ServiceManager", "fetchFriendsForRecommendationList:: service is not available");
        return -1;
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
    
    public IBrowseManager getBrowse() {
        return this.mBrowseManager;
    }
    
    @Override
    public int getClientId() {
        return this.mClientId;
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
    
    public IDiagnosis getDiagnosis() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getDiagnosis();
        }
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
    
    @Override
    public int getRequestId(final ManagerCallback managerCallback) {
        return this.addCallback(managerCallback);
    }
    
    @Override
    public INetflixService getService() {
        return this.validateService();
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
    
    @Override
    public int getWrappedRequestId(final ManagerCallback managerCallback, final String s) {
        return this.addCallback(this.wrapForAddToList(managerCallback, s));
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
    
    public void removeProfile(final String s, final ManagerCallback managerCallback) {
        final INetflixService mService = this.mService;
        final int addCallback = this.addCallback(managerCallback);
        if (mService != null) {
            mService.removeProfile(s, this.mClientId, addCallback);
            return;
        }
        Log.w("ServiceManager", "removeProfile:: service is not available");
    }
    
    public void selectProfile(final String s) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.selectProfile(s);
            return;
        }
        Log.w("ServiceManager", "selectProfile:: service is not available");
    }
    
    public boolean sendRecommendationsToFriends(final String s, final Set<FriendForRecommendation> set, final String s2) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.sendRecommendationsToFriends(s, set, s2);
            return true;
        }
        Log.w("ServiceManager", "sendRecommendationsToFriends:: service is not available");
        return false;
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
    
    public void unregisterAddToMyListListener(final String s, final AddToListData.StateListener stateListener) {
        final AddToMyListWrapper addToMyListWrapper = this.addToMyListWrapper;
        if (addToMyListWrapper != null) {
            addToMyListWrapper.unregister(s, stateListener);
        }
    }
    
    public void updateMyListState(final String s, final boolean b) {
        if (this.addToMyListWrapper != null) {
            this.addToMyListWrapper.updateState(s, b);
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
        
        private void updateListeners(final Status status, final boolean b, final boolean b2) {
            final AddToMyListWrapper access$700 = ServiceManager.this.addToMyListWrapper;
            if (access$700 == null) {
                return;
            }
            if (status.isSucces()) {
                access$700.updateState(this.videoId, b);
                return;
            }
            access$700.updateToError(status, this.videoId, b2);
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
            super.onMovieDetailsFetched(movieDetails, status);
            this.cb.onMovieDetailsFetched(movieDetails, status);
            this.updateListeners(status, movieDetails != null && movieDetails.isInQueue(), false);
        }
        
        @Override
        public void onQueueAdd(final Status status) {
            this.onQueueAdd(status);
            this.cb.onQueueAdd(status);
            this.updateListeners(status, true, true);
        }
        
        @Override
        public void onQueueRemove(final Status status) {
            this.onQueueRemove(status);
            this.cb.onQueueRemove(status);
            this.updateListeners(status, false, true);
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
            super.onShowDetailsFetched(showDetails, status);
            this.cb.onShowDetailsFetched(showDetails, status);
            this.updateListeners(status, showDetails != null && showDetails.isInQueue(), false);
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
        private void updateStatusRequestId(final Status status, final int requestId) {
            if (status instanceof NetflixStatus) {
                ((NetflixStatus)status).setRequestId(requestId);
            }
        }
        
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
        public void onAvailableAvatarsListFetched(final int n, final List<AvatarInfo> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onAvailableAvatarsListFetched requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onAvailableAvatarsListFetched requestId " + n);
                return;
            }
            access$400.onAvailableAvatarsListFetched(list, status);
        }
        
        @Override
        public void onBBVideosFetched(final int n, final List<Billboard> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onBBVideosFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onBBVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onBBVideosFetched requestId " + n);
                return;
            }
            access$400.onBBVideosFetched(list, status);
        }
        
        @Override
        public void onCWVideosFetched(final int n, final List<CWVideo> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onCWVideosFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onCWVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onCWVideosFetched requestId " + n);
                return;
            }
            access$400.onCWVideosFetched(list, status);
        }
        
        @Override
        public void onConnectWithFacebookComplete(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onConnectWithFacebookComplete requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onConnectWithFacebookComplete requestId " + n);
                return;
            }
            access$400.onConnectWithFacebookComplete(status);
        }
        
        @Override
        public void onEpisodeDetailsFetched(final int n, final EpisodeDetails episodeDetails, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onEpisodeDetailsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onEpisodeDetailsFetched requestedEdp=" + episodeDetails);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onEpisodeDetailsFetched requestId " + n);
                return;
            }
            access$400.onEpisodeDetailsFetched(episodeDetails, status);
        }
        
        @Override
        public void onEpisodesFetched(final int n, final List<EpisodeDetails> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onEpisodesFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onEpisodesFetched requestedEpisodes=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onEpisodesFetched requestId " + n);
                return;
            }
            access$400.onEpisodesFetched(list, status);
        }
        
        @Override
        public void onFriendsForRecommendationsListFetched(final int n, final List<FriendForRecommendation> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onFriendsForRecommendationsListFetched requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onFriendsForRecommendationsListFetched requestId " + n);
                return;
            }
            access$400.onFriendsForRecommendationsListFetched(list, status);
        }
        
        @Override
        public void onGenreListsFetched(final int n, final List<GenreList> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onGenreListsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onGenreListsFetched requestedGenreLists=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onGenreListsFetched requestId " + n);
                return;
            }
            access$400.onGenreListsFetched(list, status);
        }
        
        @Override
        public void onGenreLoLoMoPrefetched(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onGenreLoLoMoPrefetched requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                if (Log.isLoggable("ServiceManager", 3)) {
                    Log.d("ServiceManager", "No callback for onGenreLoLoMoPrefetched requestId " + n);
                }
                return;
            }
            access$400.onGenreLoLoMoPrefetched(status);
        }
        
        @Override
        public void onGenresFetched(final int n, final List<Genre> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onGenresFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onGenresFetched requestedGenres=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onGenresFetched requestId " + n);
                return;
            }
            access$400.onGenresFetched(list, status);
        }
        
        @Override
        public void onKidsCharacterDetailsFetched(final int n, final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onKidsCharacterDetailsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
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
            access$400.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, status);
        }
        
        @Override
        public void onLoLoMoPrefetched(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoLoMoPrefetched requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                if (Log.isLoggable("ServiceManager", 3)) {
                    Log.d("ServiceManager", "No callback for onLoLoMoPrefetched requestId " + n);
                }
                return;
            }
            access$400.onLoLoMoPrefetched(status);
        }
        
        @Override
        public void onLoLoMoSummaryFetched(final int n, final LoLoMo loLoMo, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoLoMoSummaryFetched requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLoLoMoSummaryFetched requestId " + n);
                return;
            }
            access$400.onLoLoMoSummaryFetched(loLoMo, status);
        }
        
        @Override
        public void onLoMosFetched(final int n, final List<LoMo> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoMosFetched requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            if (Log.isLoggable("ServiceManager", 2)) {
                Log.v("ServiceManager", "onLoMosFetched requestedLoMos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                if (Log.isLoggable("ServiceManager", 3)) {
                    Log.d("ServiceManager", "No callback for onLoMosFetched requestId " + n);
                }
                return;
            }
            access$400.onLoMosFetched(list, status);
        }
        
        @Override
        public void onLoginComplete(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLoginComplete requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLoginComplete requestId " + n);
                return;
            }
            access$400.onLoginComplete(status);
        }
        
        @Override
        public void onLogoutComplete(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onLogoutComplete requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onLogoutComplete requestId " + n);
                return;
            }
            access$400.onLogoutComplete(status);
        }
        
        @Override
        public void onMovieDetailsFetched(final int n, final MovieDetails movieDetails, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onMovieDetailsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
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
            access$400.onMovieDetailsFetched(movieDetails, status);
        }
        
        @Override
        public void onPinVerified(final int n, final boolean b, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onPinVerified requestId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onPinVerified requestId " + n);
                return;
            }
            access$400.onPinVerified(b, status);
        }
        
        @Override
        public void onPostPlayVideosFetched(final int n, final List<PostPlayVideo> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onPostPlayVideosFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onPostPlayVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onPostPlayVideoFetched requestId " + n);
                return;
            }
            access$400.onPostPlayVideosFetched(list, status);
        }
        
        @Override
        public void onProfileListUpdateStatus(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onProfileListUpdateStatus requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onProfileListUpdateStatus requestId " + n);
                return;
            }
            access$400.onProfileListUpdateStatus(status);
        }
        
        @Override
        public void onQueueAdd(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onQueueAdd requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onQueueAdd requestId " + n);
                return;
            }
            access$400.onQueueAdd(status);
        }
        
        @Override
        public void onQueueRemove(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onQueueRemove requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onQueueRemove requestId " + n);
                return;
            }
            access$400.onQueueRemove(status);
        }
        
        @Override
        public void onResourceFetched(final int n, final String s, final String s2, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 2)) {
                Log.v("ServiceManager", "onResourceFetched requestId=" + n + " requestedUrl=" + s + " localUrl=" + s2 + " res.getStatusCode()=" + status.getStatusCode());
            }
            final ManagerCallback demuxCallback = ServiceManager.this.mCallbackMuxer.demuxCallback(n);
            if (demuxCallback == null) {
                Log.d("ServiceManager", "No callback for onResourceFetched requestId " + n);
                return;
            }
            demuxCallback.onResourceFetched(s, s2, status);
        }
        
        @Override
        public void onSearchResultsFetched(final int n, final ISearchResults searchResults, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSearchResultsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onSearchResultsFetched results=" + searchResults);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSearchResultsFetched requestId " + n);
                return;
            }
            access$400.onSearchResultsFetched(searchResults, status);
        }
        
        @Override
        public void onSeasonDetailsFetched(final int n, final SeasonDetails seasonDetails, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSeasonDetailsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
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
            access$400.onSeasonDetailsFetched(seasonDetails, status);
        }
        
        @Override
        public void onSeasonsFetched(final int n, final List<SeasonDetails> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSeasonsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onSeasonsFetched requestedSeasons=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSeasonsFetched requestId " + n);
                return;
            }
            access$400.onSeasonsFetched(list, status);
        }
        
        @Override
        public void onServiceReady(final int n, final Status status) {
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onServiceReady clientId=" + n + " res.getStatusCode()=" + status.getStatusCode());
            }
            ServiceManager.this.mClientId = n;
            final ManagerStatusListener access$200 = ServiceManager.this.mCallback;
            if (access$200 != null) {
                if (!status.isSucces()) {
                    access$200.onManagerUnavailable(ServiceManager.this, status);
                    return;
                }
                ServiceManager.this.mReady = true;
                access$200.onManagerReady(ServiceManager.this, status);
            }
        }
        
        @Override
        public void onShowDetailsFetched(final int n, final ShowDetails showDetails, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onShowDetailsFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onShowDetailsFetched requestedSdp=" + showDetails);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onShowDetailsFetched requestId " + n);
                return;
            }
            access$400.onShowDetailsFetched(showDetails, status);
        }
        
        @Override
        public void onSimilarVideosFetched(final int n, final SearchVideoList list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSimilarVideosFetched requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSimilarVideosFetched requestId " + n);
                return;
            }
            access$400.onSimilarVideosFetched(list, status);
        }
        
        @Override
        public void onSocialNotificationWasThanked(final int n, final SocialNotificationSummary socialNotificationSummary, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSocialNotificationWasThanked requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSocialNotificationWasThanked requestId " + n);
                return;
            }
            access$400.onSocialNotificationWasThanked(socialNotificationSummary, status);
        }
        
        @Override
        public void onSocialNotificationsListFetched(final int n, final SocialNotificationsList list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onSocialNotificationsListFetched requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onSocialNotificationsListFetched requestId " + n);
                return;
            }
            access$400.onSocialNotificationsListFetched(list, status);
        }
        
        @Override
        public void onVideoHide(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onVideoHide requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onVideoHide requestId " + n);
                return;
            }
            access$400.onVideoHide(status);
        }
        
        @Override
        public void onVideoRatingSet(final int n, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onVideoRatingSet requestId=" + n + " erroCode=" + status.getStatusCode());
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                Log.d("ServiceManager", "No callback for onVideoRatingSet requestId " + n);
                return;
            }
            access$400.onVideoRatingSet(status);
        }
        
        @Override
        public void onVideosFetched(final int n, final List<Video> list, final Status status) {
            this.updateStatusRequestId(status, n);
            if (Log.isLoggable("ServiceManager", 3)) {
                Log.d("ServiceManager", "onVideosFetched requestId=" + n + " erroCode=" + status.getStatusCode());
                Log.d("ServiceManager", "onVideosFetched requestedVideos=" + list);
            }
            final ManagerCallback access$400 = ServiceManager.this.getManagerCallback(n);
            if (access$400 == null) {
                if (Log.isLoggable("ServiceManager", 3)) {
                    Log.d("ServiceManager", "No callback for onVideosFetched requestId " + n);
                }
                return;
            }
            access$400.onVideosFetched(list, status);
        }
    }
}
