// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import android.os.Handler;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import java.util.List;

public interface INetflixService
{
    public static final String EXTRA_STATUS_CODE = "status_code";
    public static final String INTENT_CATEGORY_NETFLIX_SERVICE = "com.netflix.mediaclient.intent.category.NETFLIX_SERVICE";
    public static final String INTENT_NAME_DESTROYED = "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED";
    public static final String INTENT_NAME_INIT_COMPLETE = "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE";
    
    void addProfile(final String p0, final boolean p1, final String p2, final int p3, final int p4);
    
    void createAutoLoginToken(final long p0, final int p1, final int p2);
    
    boolean deleteLocalResource(final String p0);
    
    void editProfile(final String p0, final String p1, final boolean p2, final String p3, final int p4, final int p5);
    
    void fetchResource(final String p0, final IClientLogging$AssetType p1, final int p2, final int p3);
    
    void fetchResource(final String p0, final IClientLogging$AssetType p1, final long p2, final long p3, final int p4, final int p5);
    
    String getAccountOwnerToken();
    
    List<? extends UserProfile> getAllProfiles();
    
    void getAvailableAvatarsList(final int p0, final int p1);
    
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
    
    IErrorHandler getErrorHandler();
    
    Handler getHandler();
    
    IMdx getMdx();
    
    IPlayer getNflxPlayer();
    
    String getNrdpComponentVersion(final NrdpComponent p0);
    
    IPushNotification getPushNotification();
    
    SignUpParams getSignUpParams();
    
    String getSoftwareVersion();
    
    String getUserEmail();
    
    IVoip getVoip();
    
    boolean isCurrentProfileIQEnabled();
    
    boolean isDeviceHd();
    
    boolean isProfileSwitchingDisabled();
    
    boolean isTablet();
    
    boolean isUserAgeVerified();
    
    boolean isUserLoggedIn();
    
    void loginUser(final String p0, final String p1, final int p2, final int p3);
    
    void loginUserByTokens(final ActivationTokens p0, final int p1, final int p2);
    
    void logoutUser(final int p0, final int p1);
    
    void refreshProfileSwitchingStatus();
    
    void registerCallback(final INetflixServiceCallback p0);
    
    void removeProfile(final String p0, final int p1, final int p2);
    
    void selectProfile(final String p0);
    
    void setCurrentAppLocale(final String p0);
    
    void uiComingFromBackground();
    
    void unregisterCallback(final INetflixServiceCallback p0);
    
    void verifyAge(final int p0, final int p1);
    
    void verifyPin(final String p0, final int p1, final int p2);
}
