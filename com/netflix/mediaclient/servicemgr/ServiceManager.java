// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.servicemgr.interface_.offline.DownloadState;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.widget.TextView;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Context;
import android.content.ServiceConnection;

public final class ServiceManager implements IServiceManagerAccess
{
    public static final String BROWSE_AGENT_CW_REFRESH = "com.netflix.mediaclient.intent.action.BA_CW_REFRESH";
    public static final String BROWSE_AGENT_EPISODE_REFRESH = "com.netflix.mediaclient.intent.action.BA_EPISODE_REFRESH";
    public static final String BROWSE_AGENT_IQ_REFRESH = "com.netflix.mediaclient.intent.action.BA_IQ_REFRESH";
    public static final String BROWSE_AGENT_POPULAR_TITLES_REFRESH = "com.netflix.mediaclient.intent.action.BA_POPULAR_TITLES_REFRESH";
    public static final String BROWSE_PARAM_CUR_EPISODE_NUMBER = "curEpisodeNumber";
    public static final String BROWSE_PARAM_CUR_SEASON_NUMBER = "curSeasonNumber";
    public static final String DETAIL_PAGE_RELOAD = "com.netflix.mediaclient.intent.action.DETAIL_PAGE_REFRESH";
    public static final String DISCOVERY_LIST_UPDATED = "com.netflix.mediaclient.intent.action.DISCOVERY_LIST_UPDATED";
    public static final String IRIS_NOTIFICATIONS_LIST_UPDATED = "com.netflix.mediaclient.intent.action.BA_IRIS_NOTIFICATION_LIST_UPDATED";
    public static final String LOCAL_PLAYER_PLAY_START = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_START";
    public static final String LOCAL_PLAYER_PLAY_STOP = "com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP";
    public static final String NOTIFICATIONS_LIST_STATUS = "notifications_list_status";
    public static final String PLAYER_EXTRA_PLAYBACK_TYPE = "playbackType";
    private static final String TAG = "ServiceManager";
    private AddToMyListWrapper addToMyListWrapper;
    private final IBrowseManager mBrowseManager;
    private ManagerStatusListener mCallback;
    private final ServiceManager$CallbackMuxer mCallbackMuxer;
    private int mClientId;
    private final ServiceConnection mConnection;
    private final Context mContext;
    private NetflixService mLocalService;
    private boolean mReady;
    private INetflixService mService;
    private ServiceManager$ServiceListener mServiceListener;
    
    public ServiceManager(final Context mContext, final ManagerStatusListener mCallback) {
        this.mClientId = -1;
        this.mCallbackMuxer = new ServiceManager$CallbackMuxer(null);
        this.mConnection = (ServiceConnection)new ServiceManager$1(this);
        if (mCallback == null) {
            throw new IllegalStateException("listener is null");
        }
        Log.d("ServiceManager", "ServiceManager created");
        this.mCallback = mCallback;
        this.mBrowseManager = new BrowseManager(this);
        (this.mContext = mContext).startService(new Intent(this.mContext, (Class)NetflixService.class));
        if (!this.mContext.bindService(this.getServiceIntent(), this.mConnection, 1)) {
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
        return new Intent(this.mContext, (Class)NetflixService.class);
    }
    
    public static ServiceManager getServiceManager(final NetflixActivity netflixActivity) {
        if (netflixActivity == null || netflixActivity.isFinishing()) {
            return null;
        }
        final ServiceManager serviceManager = netflixActivity.getServiceManager();
        if (serviceManager != null && serviceManager.isReady()) {
            return serviceManager;
        }
        return null;
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
    
    private boolean validateService() {
        if (!this.isReady() || this.mClientId < 0) {
            ErrorLoggingManager.logHandledException("SPY-8020 - ServiceMgr called before NetflixService is ready " + this.mService);
            return false;
        }
        return true;
    }
    
    private ManagerCallback wrapForAddToList(final ManagerCallback managerCallback, final String s) {
        return new ServiceManager$AddToListCallbackWrapper(this, managerCallback, s);
    }
    
    public void addNetworkRequestInspector(final IMSLClient$NetworkRequestInspector imslClient$NetworkRequestInspector, final Class[] array) {
        if (this.mService == null) {
            Log.e("ServiceManager", "Netflix service is not available. Unable to add a network request inspector");
            return;
        }
        this.mService.addNetworkRequestInspector(imslClient$NetworkRequestInspector, array);
    }
    
    public void addProfile(final String s, final boolean b, final String s2, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.addProfile(s, b, s2, this.mClientId, this.addCallback(managerCallback));
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
    
    public void cancelRequests(final Object o) {
        if (this.mService != null && this.mService.getResourceFetcher() != null) {
            this.mService.getResourceFetcher().cancelRequests(o);
        }
    }
    
    public void consumeUmaAlert() {
        if (this.validateService()) {
            this.mService.consumeUmaAlert();
            return;
        }
        Log.w("ServiceManager", "recordUserMessageImpression:: service is not available");
    }
    
    public AddToListData$StateListener createAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final VideoType videoType, final int n) {
        return this.addToMyListWrapper.createAddToMyListWrapper(netflixActivity, textView, s, videoType, n);
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
    
    public boolean createAutoLoginToken(final long n, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.createAutoLoginToken(n, this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "createAutoLoginToken:: service is not available");
        return false;
    }
    
    public AddToListData$StateListener createMementoAddToMyListWrapper(final NetflixActivity netflixActivity, final TextView textView, final String s, final VideoType videoType, final int n) {
        return this.addToMyListWrapper.createMementoAddToMyListWrapper(netflixActivity, textView, s, videoType, n);
    }
    
    public void doOnRampEligibilityAction(final OnRampEligibility$Action onRampEligibility$Action, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.doOnRampEligibilityAction(onRampEligibility$Action, this.mClientId, this.addCallback(managerCallback));
            return;
        }
        Log.w("ServiceManager", "doOnRampEligibilityAction:: service is not available");
    }
    
    public void editProfile(final String s, final String s2, final boolean b, final String s3, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.editProfile(s, s2, b, s3, this.mClientId, this.addCallback(managerCallback));
            return;
        }
        Log.w("ServiceManager", "editProfile:: service is not available");
    }
    
    public boolean fetchAndCacheResource(final String s, final IClientLogging$AssetType clientLogging$AssetType, final ManagerCallback managerCallback) {
        boolean b = false;
        // monitorenter(this)
        Label_0023: {
            if (s != null) {
                break Label_0023;
            }
            try {
                Log.d("ServiceManager", "fetchAndCacheResource:: resourceUrl is null");
                return b;
                // iftrue(Label_0073:, !Log.isLoggable())
                int addCallback = 0;
                Label_0073: {
                    while (true) {
                        Log.d("ServiceManager", "fetchAndCacheResource requestId=" + addCallback + " resourceUrl=" + s);
                        break Label_0073;
                        addCallback = this.addCallback(managerCallback);
                        continue;
                    }
                    Label_0103: {
                        Log.w("ServiceManager", "fetchAndCacheResource:: service is not available");
                    }
                    return b;
                }
                // iftrue(Label_0103:, !this.validateService())
                this.mService.fetchAndCacheResource(s, clientLogging$AssetType, this.mClientId, addCallback);
                b = true;
                return b;
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public boolean fetchAvailableAvatarsList(final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.getAvailableAvatarsList(this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "fetchAvailableAvatarsList:: service is not available");
        return false;
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
                if (this.validateService()) {
                    this.mService.fetchResource(s, clientLogging$AssetType, this.mClientId, addCallback);
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
    
    public void fetchSurvey(final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.fetchSurvey(this.mClientId, this.addCallback(managerCallback));
            return;
        }
        Log.w("ServiceManager", "fetchSurvey:: service is not available");
    }
    
    public List<? extends UserProfile> getAllProfiles() {
        if (this.validateService()) {
            return this.mService.getAllProfiles();
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
    
    public Context getContext() {
        return this.mContext;
    }
    
    public String getCurrentAppLocale() {
        if (this.validateService()) {
            return this.mService.getCurrentAppLocale();
        }
        Log.w("ServiceManager", "getCurrentAppLocale:: service is not available");
        return null;
    }
    
    public UserProfile getCurrentProfile() {
        if (this.validateService()) {
            return this.mService.getCurrentProfile();
        }
        Log.w("ServiceManager", "getCurrentProfile:: service is not available");
        return null;
    }
    
    public String getCurrentProfileGuidOrNull() {
        final UserProfile currentProfile = this.getCurrentProfile();
        if (currentProfile != null) {
            return currentProfile.getProfileGuid();
        }
        return null;
    }
    
    public DeviceCategory getDeviceCategory() {
        if (this.validateService()) {
            return this.mService.getDeviceCategory();
        }
        Log.w("ServiceManager", "getDeviceCategory:: service is not available");
        return null;
    }
    
    public IDiagnosis getDiagnosis() {
        if (this.validateService()) {
            return this.mService.getDiagnosis();
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
    
    public EogAlert getEndOfGrandfatheringAlert() {
        if (this.validateService()) {
            return this.mService.getEndOfGrandfatheringAlert();
        }
        Log.w("ServiceManager", "getEndOfGrandfatheringAlert:: service is not available");
        return null;
    }
    
    public IErrorHandler getErrorHandler() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getErrorHandler();
        }
        Log.w("ServiceManager", "getErrorHandler:: service is not available");
        return null;
    }
    
    public ImageLoader getImageLoader() {
        if (this.validateService()) {
            return this.mLocalService.getImageLoader();
        }
        Log.w("ServiceManager", "getImageLoader:: Netflix service is not available or not ready, return null.");
        return null;
    }
    
    public IMdx getMdx() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getMdx();
        }
        Log.w("ServiceManager", "getMdx:: service is not available");
        return null;
    }
    
    public String getNrdDeviceModel() {
        if (this.validateService()) {
            return this.mService.getNrdDeviceModel();
        }
        Log.w("ServiceManager", "getNrdDeviceModel:: service is not available");
        return null;
    }
    
    public String getNrdpComponentVersion(final NrdpComponent nrdpComponent) {
        if (this.validateService()) {
            return this.mService.getNrdpComponentVersion(nrdpComponent);
        }
        Log.w("ServiceManager", "getNrdpComponentVersion:: service is not available");
        return null;
    }
    
    public OfflineAgentInterface getOfflineAgent() {
        final INetflixService mService = this.mService;
        if (mService == null) {
            Log.w("ServiceManager", "getOfflineAgent:: service is not available");
            return null;
        }
        final OfflineAgentInterface offlineAgent = mService.getOfflineAgent();
        if (offlineAgent == null) {
            Log.w("ServiceManager", "getOfflineAgent:: is null");
            return null;
        }
        if (!offlineAgent.isOfflineFeatureEnabled()) {
            Log.w("ServiceManager", "getOfflineAgent:: not available ");
            return null;
        }
        return offlineAgent;
    }
    
    public OfflinePlayableViewData getOfflinePlayableData(final String s) {
        if (this.isReady()) {
            final OfflineAgentInterface offlineAgent = this.getOfflineAgent();
            if (offlineAgent != null) {
                return offlineAgent.getLatestOfflinePlayableList().getOfflinePlayableViewData(s);
            }
        }
        return null;
    }
    
    public IPlayer getPlayer(final IPlayer$PlaybackType player$PlaybackType) {
        final INetflixService mService = this.mService;
        if (mService == null || player$PlaybackType == null) {
            Log.w("ServiceManager", "getPlayer:: returning null playbackType=" + player$PlaybackType);
            return null;
        }
        if (player$PlaybackType == IPlayer$PlaybackType.OfflinePlayback) {
            return mService.getOfflinePlayer();
        }
        return mService.getNflxPlayer();
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
        this.validateService();
        return this.mService;
    }
    
    public SignUpParams getSignUpParams() {
        if (this.validateService()) {
            return this.mService.getSignUpParams();
        }
        Log.w("ServiceManager", "getSignUpParams:: service is not available");
        return null;
    }
    
    public String getSoftwareVersion() {
        if (this.validateService()) {
            return this.mService.getSoftwareVersion();
        }
        Log.w("ServiceManager", "getSoftwareVersion:: service is not available");
        return null;
    }
    
    public String getUserEmail() {
        if (this.validateService()) {
            return this.mService.getUserEmail();
        }
        Log.w("ServiceManager", "getUserEmail:: service is not available");
        return null;
    }
    
    public UmaAlert getUserMessageAlert() {
        if (this.validateService()) {
            return this.mService.getUserMessageAlert();
        }
        Log.w("ServiceManager", "getUserMessageAlert:: service is not available");
        return null;
    }
    
    public IVoip getVoip() {
        final INetflixService mService = this.mService;
        if (mService != null) {
            return mService.getVoip();
        }
        Log.w("ServiceManager", "getVoip:: service is not available");
        return null;
    }
    
    @Override
    public int getWrappedRequestId(final ManagerCallback managerCallback, final String s) {
        return this.addCallback(this.wrapForAddToList(managerCallback, s));
    }
    
    public boolean isCurrentProfileIQEnabled() {
        if (this.validateService()) {
            return this.mService.isCurrentProfileIQEnabled();
        }
        Log.w("ServiceManager", "isCurrentProfileIQEnabled:: service is not available");
        return false;
    }
    
    public boolean isDeviceHd() {
        if (this.validateService()) {
            return this.mService.isDeviceHd();
        }
        Log.w("ServiceManager", "isDeviceHd:: service is not available");
        return false;
    }
    
    public boolean isDolbyDigitalPlus51Supported() {
        return this.validateService() && this.mService.getConfiguration().isDolbyDigitalPlus51Supported();
    }
    
    public boolean isNonMemberPlayback() {
        if (this.validateService()) {
            return this.mService.isNonMemberPlayback();
        }
        Log.w("ServiceManager", "isNonMemberPlayback:: service is not available");
        return false;
    }
    
    public boolean isOfflineFeatureAvailable() {
        return this.getOfflineAgent() != null;
    }
    
    public boolean isProfileSwitchingDisabled() {
        if (this.validateService()) {
            return this.mService.isProfileSwitchingDisabled();
        }
        Log.w("ServiceManager", "isProfileSwitchingDisabled:: service is not available");
        return false;
    }
    
    public boolean isReady() {
        return this.mService != null && this.mReady;
    }
    
    public boolean isTablet() {
        if (this.validateService()) {
            return this.mService.isTablet();
        }
        Log.w("ServiceManager", "isTablet:: service is not available");
        return false;
    }
    
    public boolean isUserAgeVerified() {
        if (this.validateService()) {
            return this.mService.isUserAgeVerified();
        }
        Log.w("ServiceManager", "isUserAgeVerified:: service is not available");
        return false;
    }
    
    public boolean isUserLoggedIn() {
        if (this.validateService()) {
            return this.mService.isUserLoggedIn();
        }
        Log.w("ServiceManager", "isUserLoggedIn:: service is not available");
        return false;
    }
    
    public boolean loginUser(final String s, final String s2, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.loginUser(s, s2, this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "loginUser:: service is not available");
        return false;
    }
    
    public boolean loginUserByTokens(final ActivationTokens activationTokens, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.loginUserByTokens(activationTokens, this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "loginUserByTokens:: service is not available");
        return false;
    }
    
    public boolean logoutUser(final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.logoutUser(this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "logoutUser:: service is not available");
        return false;
    }
    
    public void markSurveysAsRead() {
        if (this.validateService()) {
            this.mService.markSurveysAsRead();
            return;
        }
        Log.w("ServiceManager", "markSurveysAsRead:: service is not available");
    }
    
    public void recordPlanSelection(final String s, final String s2) {
        if (this.validateService()) {
            this.mService.recordPlanSelection(s, s2);
            return;
        }
        Log.w("ServiceManager", "recordPlanSelection:: service is not available");
    }
    
    public void recordUserMessageImpression(final String s, final String s2) {
        if (this.validateService()) {
            this.mService.recordUserMessageImpression(s, s2);
            return;
        }
        Log.w("ServiceManager", "recordUserMessageImpression:: service is not available");
    }
    
    public void refreshCurrentUserMessageArea() {
        if (this.validateService()) {
            this.mService.refreshCurrentUserMessageArea();
            return;
        }
        Log.w("ServiceManager", "refreshUserMessage:: service is not available");
    }
    
    public void refreshProfileSwitchingStatus() {
        if (this.validateService()) {
            this.mService.refreshProfileSwitchingStatus();
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
                this.mContext.unbindService(this.mConnection);
                if (this.mCallbackMuxer != null) {
                    this.mCallbackMuxer.reset();
                }
                this.mClientId = -1;
                this.mReady = false;
            }
        }
    }
    
    public void removeProfile(final String s, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.removeProfile(s, this.mClientId, this.addCallback(managerCallback));
            return;
        }
        Log.w("ServiceManager", "removeProfile:: service is not available");
    }
    
    public void selectProfile(final String s) {
        if (this.validateService()) {
            this.mService.selectProfile(s);
            return;
        }
        Log.w("ServiceManager", "selectProfile:: service is not available");
    }
    
    public boolean setCurrentAppLocale(final String currentAppLocale) {
        if (this.validateService()) {
            this.mService.setCurrentAppLocale(currentAppLocale);
            return true;
        }
        Log.w("ServiceManager", "setCurrentAppLocale:: service is not available");
        return false;
    }
    
    public boolean setNonMemberPlayback(final boolean nonMemberPlayback) {
        if (this.validateService()) {
            this.mService.setNonMemberPlayback(nonMemberPlayback);
            return true;
        }
        Log.w("ServiceManager", "setNonMemberPlayback:: service is not available");
        return false;
    }
    
    public boolean shouldAlertForMissingLocale() {
        if (this.validateService()) {
            return this.mService.getConfiguration().shouldAlertForMissingLocale();
        }
        Log.w("ServiceManager", "shouldAlertForMissingLocale:: service is not available");
        return false;
    }
    
    public void startJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (this.isReady()) {
            this.mService.startJob(netflixJob$NetflixJobId);
        }
    }
    
    public void stopJob(final NetflixJob$NetflixJobId netflixJob$NetflixJobId) {
        if (this.isReady()) {
            this.mService.stopJob(netflixJob$NetflixJobId);
        }
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
    
    public boolean verifyAge(final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.verifyAge(this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "verifyAge:: service is not available");
        return false;
    }
    
    public boolean verifyPin(final String s, final ManagerCallback managerCallback) {
        if (this.validateService()) {
            this.mService.verifyPin(s, this.mClientId, this.addCallback(managerCallback));
            return true;
        }
        Log.w("ServiceManager", "verifyPin:: service is not available");
        return false;
    }
    
    public boolean willPlayOffline(final String s) {
        final OfflinePlayableViewData offlinePlayableData = this.getOfflinePlayableData(s);
        return offlinePlayableData != null && offlinePlayableData.getDownloadState() == DownloadState.Complete;
    }
}
