// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
import android.content.ServiceConnection;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public final class ServiceManager implements IServiceManagerAccess
{
    public static final String BROWSE_AGENT_CW_REFRESH = "com.netflix.mediaclient.intent.action.BA_CW_REFRESH";
    public static final String BROWSE_AGENT_EPISODE_REFRESH = "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH";
    public static final String BROWSE_AGENT_IQ_REFRESH = "com.netflix.mediaclient.intent.action.BA_IQ_REFRESH";
    public static final String BROWSE_AGENT_POPULAR_TITLES_REFRESH = "com.netflix.mediaclient.intent.action.BA_POPULAR_TITLES_REFRESH";
    public static final String BROWSE_PARAM_CUR_EPISODE_NUMBER = "curEpisodeNumber";
    public static final String BROWSE_PARAM_CUR_SEASON_NUMBER = "curSeasonNumber";
    public static final String DETAIL_PAGE_RELOAD = "com.netflix.mediaclient.intent.action.DETAIL_PAGE_REFRESH";
    public static final String LOCAL_PLAYER_PLAY_START = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START";
    public static final String LOCAL_PLAYER_PLAY_STOP = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP";
    public static final String NOTIFICATIONS_LIST_STATUS = "notifications_list_status";
    public static final String SOCIAL_NOTIFICATIONS_LIST_UPDATED = "com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED";
    private static final String TAG = "ServiceManager";
    private AddToMyListWrapper addToMyListWrapper;
    private final NetflixActivity mActivity;
    private final IBrowseManager mBrowseManager;
    private ManagerStatusListener mCallback;
    private final ServiceManager$CallbackMuxer mCallbackMuxer;
    private int mClientId;
    private final ServiceConnection mConnection;
    private NetflixService mLocalService;
    private boolean mReady;
    private INetflixService mService;
    private ServiceManager$ServiceListener mServiceListener;
    
    public ServiceManager(final NetflixActivity mActivity, final ManagerStatusListener mCallback) {
        this.mClientId = -1;
        this.mCallbackMuxer = new ServiceManager$CallbackMuxer(null);
        this.mConnection = (ServiceConnection)new ServiceManager$1(this);
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
    
    public static void sendCwRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_CW_REFRESH"));
        Log.v("ServiceManager", "Intent CW_REFRESH sent");
    }
    
    public static void sendHomeRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        Log.v("ServiceManager", "Intent REFRESH_HOME sent");
    }
    
    public static void sendIqRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_IQ_REFRESH"));
        Log.v("ServiceManager", "Intent IQ_REFRESH sent");
    }
    
    public static void sendPopularTitlesRefreshBrodcast(final Context context) {
        context.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.BA_POPULAR_TITLES_REFRESH"));
        Log.v("ServiceManager", "Intent BROWSE_AGENT_POPULAR_TITLES_REFRESH sent");
    }
    
    private INetflixService validateService() {
        if (this.mService == null || this.mClientId < 0) {
            return null;
        }
        return this.mService;
    }
    
    private ManagerCallback wrapForAddToList(final ManagerCallback managerCallback, final String s) {
        return new ServiceManager$AddToListCallbackWrapper(this, managerCallback, s);
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
    
    public AddToListData$StateListener createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final VideoType videoType, final int n, final boolean b) {
        return this.addToMyListWrapper.createAddToMyListWrapper(netflixActivity, textView, s, videoType, n, b);
    }
    
    public AddToListData$StateListener createAddToMyListWrapper(final DetailsActivity detailsActivity, final TextView textView, final TextView textView2, final boolean b) {
        return this.addToMyListWrapper.createAddToMyListWrapper(detailsActivity, textView, textView2, detailsActivity.getVideoId(), detailsActivity.getVideoType(), detailsActivity.getTrackId(), b);
    }
    
    public AddToListData$StateListener createAddToMyListWrapper(final DetailsActivity detailsActivity, final TextView textView, final boolean b) {
        return this.addToMyListWrapper.createAddToMyListWrapper(detailsActivity, textView, detailsActivity.getVideoId(), detailsActivity.getVideoType(), detailsActivity.getTrackId(), b);
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
    
    public boolean fetchResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final ManagerCallback managerCallback) {
        boolean b = false;
        // monitorenter(this)
        if (s != null) {
            try {
                final int addCallback = this.addCallback(managerCallback);
                if (Log.isLoggable()) {
                    Log.d("ServiceManager", "fetchResource requestId=" + addCallback + " resourceUrl=" + s);
                }
                final INetflixService validateService = this.validateService();
                if (validateService != null) {
                    validateService.fetchResource(s, clientLogging$AssetType, this.mClientId, addCallback);
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
    
    public ServiceAgent$ConfigurationAgentInterface getConfiguration() {
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
    
    public void registerAddToMyListListener(final String s, final AddToListData$StateListener addToListData$StateListener) {
        final AddToMyListWrapper addToMyListWrapper = this.addToMyListWrapper;
        if (addToMyListWrapper != null) {
            addToMyListWrapper.register(s, addToListData$StateListener);
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
    
    public boolean sendRecommendationsToFriends(final String s, final Set<FriendForRecommendation> set, final String s2, final String s3) {
        final INetflixService mService = this.mService;
        if (mService != null) {
            mService.sendRecommendationsToFriends(s, set, s2, s3);
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
    
    public void uiComingFromBackground() {
        if (this.mService != null) {
            this.mService.uiComingFromBackground();
        }
    }
    
    public void unregisterAddToMyListListener(final String s, final AddToListData$StateListener addToListData$StateListener) {
        final AddToMyListWrapper addToMyListWrapper = this.addToMyListWrapper;
        if (addToMyListWrapper != null) {
            addToMyListWrapper.unregister(s, addToListData$StateListener);
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
}
