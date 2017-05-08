// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import android.app.Notification;
import com.netflix.mediaclient.service.job.NetflixJobExecutor;
import com.netflix.mediaclient.service.job.NetflixJob$NetflixJobId;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcher;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.NetflixPowerManager;
import com.netflix.mediaclient.service.job.NetflixJobScheduler;
import android.os.Handler;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.media.BookmarkStore;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;
import com.netflix.model.leafs.OnRampEligibility$Action;

public interface INetflixService
{
    public static final String EXTRA_STATUS_CODE = "status_code";
    public static final String INTENT_CATEGORY_NETFLIX_SERVICE = "com.netflix.mediaclient.intent.category.NETFLIX_SERVICE";
    public static final String INTENT_NAME_DESTROYED = "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED";
    public static final String INTENT_NAME_INIT_COMPLETE = "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE";
    
    void addNetworkRequestInspector(final IMSLClient$NetworkRequestInspector p0, final Class[] p1);
    
    void addProfile(final String p0, final boolean p1, final String p2, final int p3, final int p4);
    
    void consumeUmaAlert();
    
    void createAutoLoginToken(final long p0, final int p1, final int p2);
    
    boolean deleteLocalResource(final String p0);
    
    void doOnRampEligibilityAction(final OnRampEligibility$Action p0, final int p1, final int p2);
    
    void editProfile(final String p0, final String p1, final boolean p2, final String p3, final int p4, final int p5);
    
    void fetchAndCacheResource(final String p0, final IClientLogging$AssetType p1, final int p2, final int p3);
    
    void fetchResource(final String p0, final IClientLogging$AssetType p1, final int p2, final int p3);
    
    void fetchResource(final String p0, final IClientLogging$AssetType p1, final long p2, final long p3, final int p4, final int p5);
    
    void fetchSurvey(final int p0, final int p1);
    
    String getAccountOwnerToken();
    
    List<? extends UserProfile> getAllProfiles();
    
    void getAvailableAvatarsList(final int p0, final int p1);
    
    BookmarkStore getBookmarkStore();
    
    IBrowseInterface getBrowse();
    
    IClientLogging getClientLogging();
    
    ServiceAgent$ConfigurationAgentInterface getConfiguration();
    
    String getCurrentAppLocale();
    
    UserProfile getCurrentProfile();
    
    String getCurrentProfileEmail();
    
    String getCurrentProfileFirstName();
    
    String getCurrentProfileLastName();
    
    String getCurrentProfileToken();
    
    DeviceCategory getDeviceCategory();
    
    IDiagnosis getDiagnosis();
    
    EsnProvider getESN();
    
    EogAlert getEndOfGrandfatheringAlert();
    
    IErrorHandler getErrorHandler();
    
    Handler getHandler();
    
    NetflixJobScheduler getJobScheduler();
    
    IMSLClient getMSLClient();
    
    IMdx getMdx();
    
    NetflixPowerManager getNetflixPowerManager();
    
    IPlayer getNflxPlayer();
    
    String getNrdDeviceModel();
    
    String getNrdpComponentVersion(final NrdpComponent p0);
    
    OfflineAgentInterface getOfflineAgent();
    
    IPlayer getOfflinePlayer();
    
    IPushNotification getPushNotification();
    
    ResourceFetcher getResourceFetcher();
    
    SignUpParams getSignUpParams();
    
    String getSoftwareVersion();
    
    long getStartedTimeInMs();
    
    ThumbMessaging getThumbMessaging();
    
    String getUserEmail();
    
    UmaAlert getUserMessageAlert();
    
    IVoip getVoip();
    
    boolean isCurrentProfileInstantQueueEnabled();
    
    boolean isDeviceHd();
    
    boolean isNonMemberPlayback();
    
    boolean isProfileSwitchingDisabled();
    
    boolean isTablet();
    
    boolean isUserAgeVerified();
    
    boolean isUserLoggedIn();
    
    void loginUser(final String p0, final String p1, final int p2, final int p3);
    
    void loginUserByTokens(final ActivationTokens p0, final int p1, final int p2);
    
    void logoutUser(final int p0, final int p1);
    
    void markSurveysAsRead();
    
    void recordPlanSelection(final String p0, final String p1);
    
    void recordThumbRatingThanksSeen();
    
    void recordThumbRatingWelcomeSeen();
    
    void recordUserMessageImpression(final String p0, final String p1);
    
    void refreshCurrentUserMessageArea();
    
    void refreshProfileSwitchingStatus();
    
    void registerCallback(final INetflixServiceCallback p0);
    
    void registerJobExecutor(final NetflixJob$NetflixJobId p0, final NetflixJobExecutor p1);
    
    void removeProfile(final String p0, final int p1, final int p2);
    
    void requestBackgroundForNotification(final int p0, final boolean p1);
    
    void requestForegroundForNotification(final int p0, final Notification p1);
    
    void resetThumbMessagingForDebug();
    
    void selectProfile(final String p0);
    
    void setCurrentAppLocale(final String p0);
    
    void setNonMemberPlayback(final boolean p0);
    
    void startJob(final NetflixJob$NetflixJobId p0);
    
    void stopJob(final NetflixJob$NetflixJobId p0);
    
    void uiComingFromBackground();
    
    void unregisterCallback(final INetflixServiceCallback p0);
    
    void verifyAge(final int p0, final int p1);
    
    void verifyPin(final String p0, final PinVerifier$PinType p1, final String p2, final int p3, final int p4);
}
