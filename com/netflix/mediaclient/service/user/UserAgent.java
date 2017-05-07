// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.repository.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;

public class UserAgent extends ServiceAgent implements ServiceAgent$UserAgentInterface, UserAgentStateManager$StateManagerCallback, UserCredentialRegistry
{
    private static final String ACTIVATE = "activate";
    private static final String ACTIVATE_COMPLETE = "activateComplete";
    private static final String APP_RESET_Required = "appResetRequired";
    private static final String BIND = "bind";
    private static final String DEACTIVATED = "deactivated";
    private static final String NETFLIX_ID = "NetflixId";
    private static final String NETFLIX_ID_TEST = "NetflixIdTest";
    private static final String SECURE_NETFLIX_ID = "SecureNetflixId";
    private static final String SECURE_NETFLIX_ID_TEST = "SecureNetflixIdTest";
    private static final String TAG = "nf_service_useragent";
    private final UserAgentWebCallback commonProfilesUpdateCallback;
    private final ConfigurationAgentWebCallback configDataCallback;
    private boolean isProfileSwitchingDisabled;
    private UserAgent$AccountErrorReceiver mAccountErrorReceiver;
    private EventListener mActivateListener;
    private EventListener mAppResetListener;
    private EventListener mBindListener;
    private DeviceAccount mCurrentUserAccount;
    private UserProfile mCurrentUserProfile;
    private EventListener mDeactivateListener;
    private List<UserProfile> mListOfUserProfiles;
    private UserAgent$UserAgentCallback mLoginCallback;
    private UserAgent$UserAgentCallback mLogoutCallback;
    private Nrdp mNrdp;
    private UserAgent$PlayStopReceiver mPlayStopReceiver;
    private Registration mRegistration;
    private TextStyle mSubtitleDefaults;
    private TextStyle mSubtitleSettings;
    private User mUser;
    private UserAgentStateManager mUserAgentStateManager;
    private UserWebClient mUserWebClient;
    private UserLocaleRepository userLocaleRepository;
    
    public UserAgent() {
        this.commonProfilesUpdateCallback = new UserAgent$3(this);
        this.configDataCallback = new UserAgent$4(this);
    }
    
    private List<UserProfile> buildListOfUserProfiles(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "populateListOfUserProfiles with json " + s);
        }
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        final ArrayList<UserProfile> list = new ArrayList<UserProfile>();
        try {
            final JSONArray jsonArray = (JSONArray)new JSONTokener(s).nextValue();
            for (int i = 0; i < jsonArray.length(); ++i) {
                final UserProfile userProfile = new UserProfile(jsonArray.opt(i).toString());
                if (Log.isLoggable()) {
                    Log.d("nf_service_useragent", "has userprofile " + userProfile);
                }
                list.add(userProfile);
            }
        }
        catch (JSONException ex) {
            Log.e("nf_service_useragent", "error while populateListOfUserProfiles " + ex);
        }
        return list;
    }
    
    private void checkCurrentProfileTypeWasChanged(final UserProfile userProfile) {
        if (!this.mCurrentUserProfile.getProfileType().equals(userProfile.getProfileType())) {
            Log.i("nf_service_useragent", "Current profile type changed - sending REFRESH_HOME intent");
            this.getContext().sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
            NetflixActivity.finishAllActivities(this.getContext());
            if (NetflixApplication.isActivityVisible()) {
                Log.i("nf_service_useragent", "Application is currently in foreground - restarting profiles gate");
                this.getContext().startActivity(ProfileSelectionActivity.createStartIntentSingleTop(this.getContext()));
            }
        }
    }
    
    private void doLoginComplete() {
        this.notifyLoginComplete(new NetflixStatus(StatusCode.OK));
        this.getApplication().setSignedInOnce();
        PreferenceUtils.putBooleanPref(this.getContext(), "nf_user_status_loggedin", true);
    }
    
    private void doLogoutComplete() {
        this.notifyLogoutComplete(StatusCode.OK);
        Log.d("nf_service_useragent", "Logout complete");
        this.getService().getClientLogging().getBreadcrumbLogging().leaveBreadcrumb("Logout complete");
        this.mCurrentUserProfile = null;
        this.mListOfUserProfiles = null;
        this.mUser = null;
        this.mSubtitleSettings = null;
        this.mSubtitleDefaults = null;
        PreferenceUtils.removePref(this.getContext(), "useragent_userprofiles_data");
        PreferenceUtils.removePref(this.getContext(), "useragent_user_data");
        PreferenceUtils.putBooleanPref(this.getContext(), "nf_user_status_loggedin", false);
        PartnerReceiver.broadcastUserStatus(this.getContext(), false);
        PreferenceUtils.putBooleanPref(this.getContext(), "user_profile_was_selected", false);
    }
    
    private String extractToken(final String s, final String s2) {
        Log.d("nf_service_useragent", "Extracting token: " + s);
        if (s2 != null) {
            final int index = s2.indexOf(s, 0);
            if (index >= 0) {
                final int index2 = s2.indexOf(";", index);
                if (index2 > index) {
                    return s2.substring(s.length() + index, index2);
                }
            }
        }
        return null;
    }
    
    private void handleAutoLogin(final Intent intent) {
        Log.d("nf_service_useragent", "Handle autologin");
        final String stringExtra = intent.getStringExtra("token");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_service_useragent", "Token not found, autologin is not possible");
            return;
        }
        Log.d("nf_service_useragent", "Execute autologin with token: " + stringExtra);
        if (this.mUser != null) {
            Log.e("nf_service_useragent", "User is already logged in, autologin is NOT possible!");
            return;
        }
        this.mUserWebClient.autoLogin(stringExtra, new UserAgent$6(this));
    }
    
    private void handleCreateAutoLoginToken(final Intent intent) {
        Log.e("nf_service_useragent", "You can not create auto login token in production!");
    }
    
    private boolean isAccountDataAvailable() {
        return this.mListOfUserProfiles != null && !this.mListOfUserProfiles.isEmpty() && this.mUser != null;
    }
    
    private boolean isLatestProfileDataValid(final UserProfile userProfile) {
        return userProfile != null && StringUtils.safeEquals(this.getCurrentProfileGuid(), userProfile.getProfileGuid());
    }
    
    private void launchTask(final UserAgent$FetchTask<?> userAgent$FetchTask) {
        if (Log.isLoggable()) {
            Log.v("nf_service_useragent", "Launching task: " + userAgent$FetchTask.getClass().getSimpleName());
        }
        if (this.mUserWebClient.isSynchronous()) {
            new BackgroundTask().execute(userAgent$FetchTask);
            return;
        }
        userAgent$FetchTask.run();
    }
    
    private void launchWebTask(final Runnable runnable) {
        if (Log.isLoggable()) {
            Log.v("nf_service_useragent", "Launching task: " + runnable.getClass().getSimpleName());
        }
        if (this.mUserWebClient.isSynchronous()) {
            new BackgroundTask().execute(runnable);
            return;
        }
        runnable.run();
    }
    
    private void notifyLoginComplete(final Status status) {
        this.getMainHandler().post((Runnable)new UserAgent$1(this, status));
    }
    
    private void notifyLogoutComplete(final StatusCode statusCode) {
        this.getMainHandler().post((Runnable)new UserAgent$2(this));
    }
    
    private void notifyOtherOfProfileActivated() {
        UserAgentBroadcastIntents.signalProfileActive(this.getContext());
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
        this.getService().getClientLogging().getBreadcrumbLogging().leaveBreadcrumb("Login complete");
        PartnerReceiver.broadcastUserStatus(this.getContext(), true);
    }
    
    private void persistListOfUserProfiles(final List<UserProfile> list) {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<UserProfile> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)iterator.next().toString());
        }
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "persistListOfUserProfiles " + jsonArray.toString());
        }
        PreferenceUtils.putStringPref(this.getContext(), "useragent_userprofiles_data", jsonArray.toString());
    }
    
    private void persistUser(final User user) {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "persistUser " + user.toString());
        }
        PreferenceUtils.putStringPref(this.getContext(), "useragent_user_data", user.toString());
    }
    
    private User populateUser(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "populateUser with json " + s);
        }
        User user;
        if (StringUtils.isEmpty(s)) {
            user = null;
        }
        else {
            final User user2 = user = new User(s);
            if (Log.isLoggable()) {
                Log.d("nf_service_useragent", "has user " + user2);
                return user2;
            }
        }
        return user;
    }
    
    private void registerAccountErrorReceiver() {
        LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.mAccountErrorReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.DELETED_PROFILE"));
    }
    
    private void registerPlayStopReceiver() {
        this.getContext().registerReceiver((BroadcastReceiver)this.mPlayStopReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP"));
    }
    
    private void setUserPreferredLanguages(final String[] preferredLanguages) {
        this.userLocaleRepository.setPreferredLanguages(StringUtils.joinArray(preferredLanguages));
        this.getNrdController().setPreferredLanguages(preferredLanguages);
        this.getNrdController().setDeviceLocale(this.userLocaleRepository.getCurrentAppLocale().getLocale());
    }
    
    private void unregisterAccountErrorReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.mAccountErrorReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_service_useragent", "unregisterAccountErrorReceiver " + ex);
        }
    }
    
    private void unregisterPlayStopReceiver() {
        try {
            this.getContext().unregisterReceiver((BroadcastReceiver)this.mPlayStopReceiver);
        }
        catch (Exception ex) {
            Log.i("nf_service_useragent", "unregisterPlayStopReceiver " + ex);
        }
    }
    
    private void updateAndPersistProfilesList(final UserProfile userProfile) {
        while (true) {
            for (final UserProfile userProfile2 : this.mListOfUserProfiles) {
                if (StringUtils.safeEquals(userProfile2.getProfileGuid(), userProfile.getProfileGuid())) {
                    if (userProfile2 != null) {
                        this.mListOfUserProfiles.remove(userProfile2);
                    }
                    this.mListOfUserProfiles.add(userProfile);
                    this.persistListOfUserProfiles(this.mListOfUserProfiles);
                    return;
                }
            }
            UserProfile userProfile2 = null;
            continue;
        }
    }
    
    public void addWebUserProfile(final String s, final boolean b, final String s2, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "addWebUserProfile");
        this.launchWebTask(new UserAgent$AddWebUserProfilesTask(this, s, b, s2, userAgent$UserAgentCallback));
    }
    
    public void connectWithFacebook(final String s, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.launchTask(new UserAgent$ConnectWithFacebookTask(this, s, userAgent$UserAgentCallback));
    }
    
    @Override
    public void destroy() {
        if (this.mRegistration != null) {
            this.mRegistration.removeEventListener("activateComplete", this.mActivateListener);
            this.mRegistration.removeEventListener("activate", this.mActivateListener);
            this.mRegistration.removeEventListener("deactivated", this.mDeactivateListener);
            this.mRegistration.removeEventListener("bind", this.mBindListener);
            this.mRegistration.removeEventListener("appResetRequired", this.mAppResetListener);
        }
        this.userLocaleRepository = null;
        this.unregisterPlayStopReceiver();
        this.unregisterAccountErrorReceiver();
        super.destroy();
    }
    
    @Override
    public void doDummyWebCall(final UserAgentWebCallback userAgentWebCallback) {
        this.launchWebTask(new UserAgent$DoDummyWebCallTask(this, userAgentWebCallback));
    }
    
    public void doInit() {
        this.refreshProfileSwitchingStatus();
        this.mCurrentUserAccount = null;
        this.mCurrentUserProfile = null;
        this.mSubtitleSettings = null;
        this.mPlayStopReceiver = new UserAgent$PlayStopReceiver(this);
        this.mAccountErrorReceiver = new UserAgent$AccountErrorReceiver(this);
        final String stringPref = PreferenceUtils.getStringPref(this.getContext(), "useragent_userprofiles_data", null);
        if (stringPref != null) {
            this.getApplication().setSignedInOnce();
            this.mListOfUserProfiles = this.buildListOfUserProfiles(stringPref);
        }
        final String stringPref2 = PreferenceUtils.getStringPref(this.getContext(), "useragent_user_data", null);
        if (stringPref2 != null) {
            this.mUser = this.populateUser(stringPref2);
            this.mSubtitleDefaults = TextStyle.buildSubtitleSettings(this.mUser.getSubtitleDefaults());
        }
        this.mUserWebClient = UserWebClientFactory.create(this.getService(), this.getResourceFetcher().getApiNextWebClient());
        this.userLocaleRepository = new UserLocaleRepository();
        final String rawDeviceLocale = UserLocale.getRawDeviceLocale(this.getContext());
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "Current device locale as raw user locale: " + rawDeviceLocale);
        }
        this.userLocaleRepository.setApplicationLanguage(new UserLocale(rawDeviceLocale));
        this.mNrdp = this.getNrdController().getNrdp();
        if (this.mNrdp == null || !this.mNrdp.isReady()) {
            this.initCompleted(CommonStatus.NRD_ERROR);
            Log.e("nf_service_useragent", "NRDP is NOT READY");
            return;
        }
        this.mRegistration = this.mNrdp.getRegistration();
        this.mActivateListener = new UserAgent$ActivateListener(this, null);
        this.mDeactivateListener = new UserAgent$DeactivateListener(this, null);
        this.mBindListener = new UserAgent$BindListener(this, null);
        this.mAppResetListener = new UserAgent$AppResetListener(this, null);
        this.mRegistration.addEventListener("activateComplete", this.mActivateListener);
        this.mRegistration.addEventListener("activate", this.mActivateListener);
        this.mRegistration.addEventListener("deactivated", this.mDeactivateListener);
        this.mRegistration.addEventListener("bind", this.mBindListener);
        this.mRegistration.addEventListener("appResetRequired", this.mAppResetListener);
        (this.mUserAgentStateManager = new UserAgentStateManager(this.mRegistration, this.getConfigurationAgent().getDrmManager(), this, this.getContext(), this.getService().getClientLogging().getErrorLogging())).initialize(this.getConfigurationAgent().isLogoutRequired(), this.getConfigurationAgent().isEsnMigrationRequired());
        this.registerPlayStopReceiver();
        this.registerAccountErrorReceiver();
    }
    
    public void editWebUserProfile(final String s, final String s2, final boolean b, final String s3, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "editWebUserProfile");
        this.launchWebTask(new UserAgent$EditWebUserProfilesTask(this, s, s2, b, s3, userAgent$UserAgentCallback));
    }
    
    @Override
    public void fetchAccountData() {
        Log.d("nf_service_useragent", "fetch account level config data");
        this.getConfigurationAgent().fetchAccountConfigData(this.configDataCallback);
    }
    
    public void fetchAvailableAvatarsList(final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "fetchAvailableAvatarsList");
        this.launchWebTask(new UserAgent$FetchAvailableAvatarsListTask(this, userAgent$UserAgentCallback));
    }
    
    public void fetchFriendsForRecommendations(final String s, final int n, final String s2, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "fetchFriendsForRecommendations");
        this.launchWebTask(new UserAgent$FetchFriendsForRecommendationsTask(this, s, n, s2, userAgent$UserAgentCallback));
    }
    
    public void fetchProfileData(final String s) {
        Log.d("nf_service_useragent", "fetchProfileData");
        this.launchWebTask(new UserAgent$FetchProfileDataTask(this, s, null));
    }
    
    public String getAccountOwnerToken() {
        if (this.mUser == null) {
            return null;
        }
        return this.mUser.getUserToken();
    }
    
    public List<? extends com.netflix.mediaclient.servicemgr.interface_.user.UserProfile> getAllProfiles() {
        return this.mListOfUserProfiles;
    }
    
    public String getCurrentAppLocale() {
        return this.userLocaleRepository.getCurrentAppLocale().getRaw();
    }
    
    @Override
    public UserProfile getCurrentProfile() {
        return this.mCurrentUserProfile;
    }
    
    public String getCurrentProfileEmail() {
        Log.d("nf_service_useragent", "getCurrentProfileEmail called");
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getCurrentProfileEmail  is null");
            return null;
        }
        return this.mCurrentUserProfile.getEmail();
    }
    
    public String getCurrentProfileFirstName() {
        Log.d("nf_service_useragent", "getCurrentProfileFirstName called");
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getCurrentProfileFirstName is null");
            return null;
        }
        return this.mCurrentUserProfile.getFirstName();
    }
    
    @Override
    public String getCurrentProfileGuid() {
        Log.d("nf_service_useragent", "getCurrentProfileGuid called");
        if (this.mCurrentUserProfile == null) {
            return null;
        }
        return this.mCurrentUserProfile.getProfileGuid();
    }
    
    public String getCurrentProfileLastName() {
        Log.d("nf_service_useragent", "getCurrentProfileLastName called");
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getCurrentProfileLastName is null");
            return null;
        }
        return this.mCurrentUserProfile.getLastName();
    }
    
    public String getCurrentProfileToken() {
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "currentProfile is null");
            return null;
        }
        return this.mCurrentUserProfile.getProfileToken();
    }
    
    @Override
    public String getGeoCountry() {
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getGeoCountry is null");
            return null;
        }
        return this.mCurrentUserProfile.getGeoCountry();
    }
    
    @Override
    public String getLanguagesInCsv() {
        if (this.mCurrentUserProfile != null) {
            return this.mCurrentUserProfile.getLanguagesInCsv();
        }
        return null;
    }
    
    @Override
    public String getNetflixID() {
        if (!this.isUserLoggedIn()) {
            return null;
        }
        return this.mCurrentUserAccount.getNetflixId();
    }
    
    @Override
    public String getNetflixIdName() {
        return "NetflixId";
    }
    
    @Override
    public String getReqCountry() {
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getReqCountry is null");
            return null;
        }
        return this.mCurrentUserProfile.getReqCountry();
    }
    
    @Override
    public String getSecureNetflixID() {
        if (!this.isUserLoggedIn()) {
            return null;
        }
        return this.mCurrentUserAccount.getSecureId();
    }
    
    @Override
    public String getSecureNetflixIdName() {
        return "SecureNetflixId";
    }
    
    @Override
    public TextStyle getSubtitleDefaults() {
        return this.mSubtitleDefaults;
    }
    
    @Override
    public UserCredentialRegistry getUserCredentialRegistry() {
        return this;
    }
    
    @Override
    public TextStyle getUserSubtitlePreferences() {
        return this.mSubtitleSettings;
    }
    
    public boolean handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_service_useragent", "Intent is null");
            return false;
        }
        final String action = intent.getAction();
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "Received command " + action);
        }
        if ("com.netflix.mediaclient.intent.action.USER_AUTOLOGIN".equals(action)) {
            this.handleAutoLogin(intent);
        }
        else {
            if (!"com.netflix.mediaclient.intent.action.USER_CREATE_AUTOLOGIN_TOKEN".equals(action)) {
                Log.e("nf_service_useragent", "Uknown command!");
                return false;
            }
            this.handleCreateAutoLoginToken(intent);
        }
        return true;
    }
    
    @Override
    public void initialized(final Status status) {
        this.initCompleted(status);
    }
    
    @Override
    public boolean isAgeVerified() {
        return this.mUser != null && this.mUser.isAgeVerified();
    }
    
    public boolean isCurrentProfileFacebookConnected() {
        boolean socialConnected = false;
        if (this.mCurrentUserProfile != null) {
            socialConnected = this.mCurrentUserProfile.isSocialConnected();
        }
        Log.d("nf_service_useragent", "isCurrentProfileFacebookConnected result: " + socialConnected);
        return socialConnected;
    }
    
    @Override
    public boolean isCurrentProfileIQEnabled() {
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "isCurrentProfileIQEnabled is null");
            return false;
        }
        Log.d("nf_service_useragent", String.format("isCurrentProfileIQEnabled %s called: %b ", this.mCurrentUserProfile.getFirstName(), this.mCurrentUserProfile.isIQEnabled()));
        return this.mCurrentUserProfile.isIQEnabled();
    }
    
    @Override
    public boolean isProfileSwitchingDisabled() {
        return this.isProfileSwitchingDisabled;
    }
    
    @Override
    public boolean isUserLoggedIn() {
        return this.mCurrentUserAccount != null;
    }
    
    public void loginUser(final String s, final String s2, final UserAgent$UserAgentCallback mLoginCallback) {
        Log.d("nf_service_useragent", "loginUser activateAccByEmailPassword");
        if (this.mUserAgentStateManager == null) {
            this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_ERROR, "UserAgent: activateAccByEmailPassword fails, UserAgentStateManager is null", true, RootCause.clientFailure));
            return;
        }
        if (!this.mUserAgentStateManager.activateAccByEmailPassword(s, s2)) {
            this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_REGISTRATION_EXISTS, "UserAgent: activateAccByEmailPassword fails, NRD registration exist", false, RootCause.clientFailure));
            return;
        }
        this.mLoginCallback = mLoginCallback;
    }
    
    @Override
    public void logoutUser() {
        this.logoutUser(null);
    }
    
    public void logoutUser(final UserAgent$UserAgentCallback mLogoutCallback) {
        if (!this.isReady()) {
            Log.w("nf_service_useragent", "Can't log user out because agent has not been initialized!");
            return;
        }
        this.mLogoutCallback = mLogoutCallback;
        this.getService().getClientLogging().onUserLogout();
        if (!this.isUserLoggedIn()) {
            this.notifyLogoutComplete(StatusCode.OK);
            return;
        }
        if (this.mCurrentUserProfile != null) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT");
            intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
            intent.putExtra("uid", this.getService().getAccountOwnerToken());
            intent.putExtra("nid", this.getNetflixID());
            intent.putExtra("sid", this.getSecureNetflixID());
            intent.putExtra("esn", this.getConfigurationAgent().getEsnProvider().getEsn());
            intent.putExtra("device_cat", this.getConfigurationAgent().getDeviceCategory().getValue());
            intent.putExtra("uid", this.getService().getCurrentProfileToken());
            LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
        }
        this.mUserAgentStateManager.signoutAcc();
    }
    
    @Override
    public void profileActivated(final String s, final DeviceAccount mCurrentUserAccount) {
        this.mCurrentUserAccount = mCurrentUserAccount;
        for (final UserProfile mCurrentUserProfile : this.mListOfUserProfiles) {
            if (mCurrentUserProfile.getProfileGuid().equals(s)) {
                this.mCurrentUserProfile = mCurrentUserProfile;
                BrowseExperience.refresh(this.getContext(), this.mCurrentUserProfile);
                if (this.mCurrentUserProfile != null && this.mCurrentUserProfile.getSubtitlePreference() != null) {
                    this.mSubtitleSettings = TextStyle.buildSubtitleSettings(this.getCurrentProfile().getSubtitlePreference());
                }
                this.setUserPreferredLanguages(this.mCurrentUserProfile.getLanguages());
                this.notifyOtherOfProfileActivated();
                return;
            }
        }
        Log.e("nf_service_useragent", "profileActivated cannot find profileId");
        final NetflixService service = this.getService();
        if (service != null) {
            Log.e("nf_service_useragent", "Activated ProfileId not found in list of user profiles: ");
            service.getClientLogging().getErrorLogging().logHandledException("Activated ProfileId not found in list of user profiles: ");
        }
        this.mCurrentUserProfile = null;
        this.mSubtitleSettings = null;
    }
    
    @Override
    public void profileInactive() {
        this.mCurrentUserProfile = null;
        this.mSubtitleSettings = null;
        UserAgentBroadcastIntents.signalProfileDeactivated(this.getContext());
    }
    
    @Override
    public void readyToSelectProfile() {
        UserAgentBroadcastIntents.signalProfileReady2Select(this.getContext());
    }
    
    @Override
    public void refreshProfileSwitchingStatus() {
        if (AndroidUtils.getAndroidVersion() >= 18) {
            this.isProfileSwitchingDisabled = RestrictedProfilesReceiver.isProfileSwitchingDisabled(this.getContext());
        }
        else {
            this.isProfileSwitchingDisabled = false;
        }
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "Is profile switching disabled: " + this.isProfileSwitchingDisabled);
        }
    }
    
    public void removeWebUserProfile(final String s, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "removeWebUserProfile");
        this.launchWebTask(new UserAgent$RemoveWebUserProfilesTask(this, s, userAgent$UserAgentCallback));
    }
    
    public void selectProfile(final String s) {
        this.mUserAgentStateManager.selectNewProfile(s);
    }
    
    @Override
    public void selectProfileResult(final Status status) {
        UserAgentBroadcastIntents.signalProfileSelectionResult(this.getContext(), status.getStatusCode().getValue(), null);
    }
    
    public void sendRecommendationsToFriends(final String s, final Set<FriendForRecommendation> set, final String s2, final String s3) {
        Log.d("nf_service_useragent", "sendRecommendationsToFriends");
        this.launchWebTask(new UserAgent$SendRecommendationsTask(s, set, s2, s3));
    }
    
    public void setCurrentAppLocale(final String s) {
        if (this.userLocaleRepository != null) {
            this.userLocaleRepository.setApplicationLanguage(new UserLocale(s));
        }
    }
    
    @Override
    public void switchWebUserProfile(final String s) {
        Log.d("nf_service_useragent", "switchWebUserProfile");
        this.getService().getClientLogging().onProfileSwitch();
        this.launchWebTask(new UserAgent$SwitchWebUserProfilesTask(this, s));
    }
    
    public void tokenActivate(final ActivationTokens activationTokens, final UserAgent$UserAgentCallback mLoginCallback) {
        Log.d("nf_service_useragent", "loginUser tokenActivate");
        if (this.mUserAgentStateManager == null) {
            this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_ERROR, "UserAgent: activateAccByToken fails UserAgentStateManager is null", true, RootCause.clientFailure));
            return;
        }
        if (!this.mUserAgentStateManager.activateAccByToken(activationTokens)) {
            this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_REGISTRATION_EXISTS, "UserAgent: activateAccByToken fails, NRD registration exist", false, RootCause.clientFailure));
            this.notifyLoginComplete(new NetflixStatus(StatusCode.NRD_REGISTRATION_EXISTS));
            return;
        }
        this.mLoginCallback = mLoginCallback;
    }
    
    @Override
    public boolean updateUserCredentials(final String s, final String s2) {
        if (!this.isUserLoggedIn()) {
            return false;
        }
        this.mCurrentUserAccount = this.mUserAgentStateManager.updateAccountTokens(s, s2);
        return true;
    }
    
    @Override
    public void userAccountActivated(final DeviceAccount mCurrentUserAccount) {
        this.mCurrentUserAccount = mCurrentUserAccount;
        UserAgentBroadcastIntents.signalUserAccountActive(this.getContext());
    }
    
    @Override
    public void userAccountDataResult(final Status status) {
        if (status.isSucces()) {
            this.doLoginComplete();
            return;
        }
        this.notifyLoginComplete(status);
    }
    
    @Override
    public void userAccountDeactivated() {
        UserAgentBroadcastIntents.signalUserAccountDeactivated(this.getContext());
        this.getConfigurationAgent().clearAccountConfigData();
        this.doLogoutComplete();
    }
    
    @Override
    public void userAccountInactive() {
        this.mCurrentUserAccount = null;
    }
    
    public void verifyAge(final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.launchTask(new UserAgent$VerifyAgeTask(this, userAgent$UserAgentCallback));
    }
    
    public void verifyPin(final String s, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.launchTask(new UserAgent$VerifyPinTask(this, s, userAgent$UserAgentCallback));
    }
}
