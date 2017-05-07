// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationsList;
import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import android.widget.Toast;
import com.netflix.mediaclient.service.webclient.model.leafs.AvatarInfo;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.repository.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
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
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.configuration.SimpleConfigurationAgentWebCallback;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent;

public class UserAgent extends ServiceAgent implements UserAgentInterface, UserCredentialRegistry, StateManagerCallback
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
    private AccountErrorReceiver mAccountErrorReceiver;
    private EventListener mActivateListener;
    private EventListener mAppResetListener;
    private EventListener mBindListener;
    private DeviceAccount mCurrentUserAccount;
    private UserProfile mCurrentUserProfile;
    private EventListener mDeactivateListener;
    private List<UserProfile> mListOfUserProfiles;
    private UserAgentCallback mLoginCallback;
    private UserAgentCallback mLogoutCallback;
    private Nrdp mNrdp;
    private PlayStopReceiver mPlayStopReceiver;
    private Registration mRegistration;
    private TextStyle mSubtitleDefaults;
    private TextStyle mSubtitleSettings;
    private User mUser;
    private UserAgentStateManager mUserAgentStateManager;
    private UserWebClient mUserWebClient;
    private UserLocaleRepository userLocaleRepository;
    
    public UserAgent() {
        this.commonProfilesUpdateCallback = new SimpleUserAgentWebCallback() {
            @Override
            public void onUserProfilesUpdated(final AccountData accountData, final Status status) {
                if (Log.isLoggable("nf_service_useragent", 2)) {
                    Log.v("nf_service_useragent", "onUserProfilesUpdated: " + status.getStatusCode());
                }
                if (status.isSucces() && accountData != null) {
                    final List<UserProfile> userProfiles = accountData.getUserProfiles();
                    if (Log.isLoggable("nf_service_useragent", 3)) {
                        Log.d("nf_service_useragent", String.format("onUserProfilesUpdated got profiles: %d", userProfiles.size()));
                    }
                    UserAgent.this.mListOfUserProfiles = userProfiles;
                    UserAgent.this.persistListOfUserProfiles(userProfiles);
                    if (UserAgent.this.mCurrentUserProfile != null) {
                        for (final UserProfile userProfile : userProfiles) {
                            if (StringUtils.safeEquals(UserAgent.this.mCurrentUserProfile.getProfileGuid(), userProfile.getProfileGuid())) {
                                UserAgent.this.checkCurrentProfileTypeWasChanged(userProfile);
                                UserAgent.this.mCurrentUserProfile = userProfile;
                            }
                        }
                    }
                    UserAgentBroadcastIntents.signalProfilesListUpdated(UserAgent.this.getContext());
                    return;
                }
                Log.e("nf_service_useragent", "Updating user profiles failed with statusCode=" + status.getStatusCode());
            }
        };
        this.configDataCallback = new SimpleConfigurationAgentWebCallback() {
            @Override
            public void onConfigDataFetched(final ConfigData configData, final Status status) {
                if (Log.isLoggable("nf_service_useragent", 3)) {
                    Log.d("nf_service_useragent", String.format("onConfigDataFetched res.isSuccess:%b, isAccountDataAvailable:%b", status.isSucces(), UserAgent.this.isAccountDataAvailable()));
                }
                if (status.isSucces() || UserAgent.this.isAccountDataAvailable()) {
                    Log.d("nf_service_useragent", "pfetchUserData");
                    UserAgent.this.launchWebTask(new FetchAccountDataTask(null));
                    return;
                }
                UserAgent.this.mUserAgentStateManager.accountDataFetchFailed(status, UserAgent.this.isAccountDataAvailable());
            }
        };
    }
    
    private List<UserProfile> buildListOfUserProfiles(final String s) {
        if (Log.isLoggable("nf_service_useragent", 3)) {
            Log.d("nf_service_useragent", "populateListOfUserProfiles with json " + s);
        }
        List<UserProfile> list;
        if (StringUtils.isEmpty(s)) {
            list = null;
        }
        else {
            final ArrayList<UserProfile> list2 = new ArrayList<UserProfile>();
            try {
                final JSONArray jsonArray = (JSONArray)new JSONTokener(s).nextValue();
                int n = 0;
                while (true) {
                    list = list2;
                    if (n >= jsonArray.length()) {
                        break;
                    }
                    final UserProfile userProfile = new UserProfile(jsonArray.opt(n).toString());
                    if (Log.isLoggable("nf_service_useragent", 3)) {
                        Log.d("nf_service_useragent", "has userprofile " + userProfile);
                    }
                    list2.add(userProfile);
                    ++n;
                }
            }
            catch (JSONException ex) {
                Log.e("nf_service_useragent", "error while populateListOfUserProfiles " + ex);
                return list2;
            }
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
        final String s3 = null;
        if (s2 == null) {
            return null;
        }
        final int index = s2.indexOf(s, 0);
        String substring = s3;
        if (index >= 0) {
            final int index2 = s2.indexOf(";", index);
            substring = s3;
            if (index2 > index) {
                substring = s2.substring(s.length() + index, index2);
            }
        }
        return substring;
    }
    
    private boolean isAccountDataAvailable() {
        return this.mListOfUserProfiles != null && !this.mListOfUserProfiles.isEmpty() && this.mUser != null;
    }
    
    private boolean isLatestProfileDataValid(final UserProfile userProfile) {
        return userProfile != null && StringUtils.safeEquals(this.getCurrentProfileGuid(), userProfile.getProfileGuid());
    }
    
    private void launchTask(final FetchTask<?> fetchTask) {
        if (Log.isLoggable("nf_service_useragent", 2)) {
            Log.v("nf_service_useragent", "Launching task: " + fetchTask.getClass().getSimpleName());
        }
        if (this.mUserWebClient.isSynchronous()) {
            new BackgroundTask().execute(fetchTask);
            return;
        }
        fetchTask.run();
    }
    
    private void launchWebTask(final Runnable runnable) {
        if (Log.isLoggable("nf_service_useragent", 2)) {
            Log.v("nf_service_useragent", "Launching task: " + runnable.getClass().getSimpleName());
        }
        if (this.mUserWebClient.isSynchronous()) {
            new BackgroundTask().execute(runnable);
            return;
        }
        runnable.run();
    }
    
    private void notifyLoginComplete(final Status status) {
        this.getMainHandler().post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (UserAgent.this.mLoginCallback != null) {
                    UserAgent.this.mLoginCallback.onLoginComplete(status);
                    UserAgent.this.mLoginCallback = null;
                }
            }
        });
    }
    
    private void notifyLogoutComplete(final StatusCode statusCode) {
        this.getMainHandler().post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (UserAgent.this.mLogoutCallback != null) {
                    UserAgent.this.mLogoutCallback.onLogoutComplete(new NetflixStatus(StatusCode.OK));
                    Log.d("nf_service_useragent", "Received deactivate complete and notified UI");
                    UserAgent.this.mLogoutCallback = null;
                }
            }
        });
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
        if (Log.isLoggable("nf_service_useragent", 3)) {
            Log.d("nf_service_useragent", "persistListOfUserProfiles " + jsonArray.toString());
        }
        PreferenceUtils.putStringPref(this.getContext(), "useragent_userprofiles_data", jsonArray.toString());
    }
    
    private void persistUser(final User user) {
        if (Log.isLoggable("nf_service_useragent", 3)) {
            Log.d("nf_service_useragent", "persistUser " + user.toString());
        }
        PreferenceUtils.putStringPref(this.getContext(), "useragent_user_data", user.toString());
    }
    
    private User populateUser(final String s) {
        if (Log.isLoggable("nf_service_useragent", 3)) {
            Log.d("nf_service_useragent", "populateUser with json " + s);
        }
        User user;
        if (StringUtils.isEmpty(s)) {
            user = null;
        }
        else {
            final User user2 = user = new User(s);
            if (Log.isLoggable("nf_service_useragent", 3)) {
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
        final UserProfile userProfile2 = null;
        final Iterator<UserProfile> iterator = this.mListOfUserProfiles.iterator();
        UserProfile userProfile3;
        do {
            userProfile3 = userProfile2;
            if (!iterator.hasNext()) {
                break;
            }
            userProfile3 = iterator.next();
        } while (!StringUtils.safeEquals(userProfile3.getProfileGuid(), userProfile.getProfileGuid()));
        if (userProfile3 != null) {
            this.mListOfUserProfiles.remove(userProfile3);
        }
        this.mListOfUserProfiles.add(userProfile);
        this.persistListOfUserProfiles(this.mListOfUserProfiles);
    }
    
    public void addWebUserProfile(final String s, final boolean b, final String s2, final UserAgentCallback userAgentCallback) {
        Log.d("nf_service_useragent", "addWebUserProfile");
        this.launchWebTask(new AddWebUserProfilesTask(s, b, s2, userAgentCallback));
    }
    
    public void connectWithFacebook(final String s, final UserAgentCallback userAgentCallback) {
        this.launchTask((FetchTask<?>)new ConnectWithFacebookTask(s, userAgentCallback));
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
        this.launchWebTask(new DoDummyWebCallTask(userAgentWebCallback));
    }
    
    public void doInit() {
        this.refreshProfileSwitchingStatus();
        this.mCurrentUserAccount = null;
        this.mCurrentUserProfile = null;
        this.mSubtitleSettings = null;
        this.mPlayStopReceiver = new PlayStopReceiver();
        this.mAccountErrorReceiver = new AccountErrorReceiver();
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
        if (Log.isLoggable("nf_service_useragent", 3)) {
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
        this.mActivateListener = new ActivateListener();
        this.mDeactivateListener = new DeactivateListener();
        this.mBindListener = new BindListener();
        this.mAppResetListener = new AppResetListener();
        this.mRegistration.addEventListener("activateComplete", this.mActivateListener);
        this.mRegistration.addEventListener("activate", this.mActivateListener);
        this.mRegistration.addEventListener("deactivated", this.mDeactivateListener);
        this.mRegistration.addEventListener("bind", this.mBindListener);
        this.mRegistration.addEventListener("appResetRequired", this.mAppResetListener);
        (this.mUserAgentStateManager = new UserAgentStateManager(this.mRegistration, this.getConfigurationAgent().getDrmManager(), (UserAgentStateManager.StateManagerCallback)this, this.getContext(), this.getService().getClientLogging().getErrorLogging())).initialize(this.getConfigurationAgent().isLogoutRequired(), this.getConfigurationAgent().isEsnMigrationRequired());
        this.registerPlayStopReceiver();
        this.registerAccountErrorReceiver();
    }
    
    public void editWebUserProfile(final String s, final String s2, final boolean b, final String s3, final UserAgentCallback userAgentCallback) {
        Log.d("nf_service_useragent", "editWebUserProfile");
        this.launchWebTask(new EditWebUserProfilesTask(s, s2, b, s3, userAgentCallback));
    }
    
    @Override
    public void fetchAccountData() {
        Log.d("nf_service_useragent", "fetch account level config data");
        this.getConfigurationAgent().fetchAccountConfigData(this.configDataCallback);
    }
    
    public void fetchAvailableAvatarsList(final UserAgentCallback userAgentCallback) {
        Log.d("nf_service_useragent", "fetchAvailableAvatarsList");
        this.launchWebTask(new FetchAvailableAvatarsListTask(userAgentCallback));
    }
    
    public void fetchFriendsForRecommendations(final String s, final int n, final String s2, final UserAgentCallback userAgentCallback) {
        Log.d("nf_service_useragent", "fetchFriendsForRecommendations");
        this.launchWebTask(new FetchFriendsForRecommendationsTask(s, n, s2, userAgentCallback));
    }
    
    public void fetchProfileData(final String s) {
        Log.d("nf_service_useragent", "fetchProfileData");
        this.launchWebTask(new FetchProfileDataTask(s, null));
    }
    
    public String getAccountOwnerToken() {
        if (this.mUser == null) {
            return null;
        }
        return this.mUser.getUserToken();
    }
    
    public List<? extends com.netflix.mediaclient.servicemgr.model.user.UserProfile> getAllProfiles() {
        return this.mListOfUserProfiles;
    }
    
    public String getCurrentAppLocale() {
        return this.userLocaleRepository.getCurrentAppLocale().getRaw();
    }
    
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
    
    @Override
    public void initialized(final Status status) {
        this.initCompleted(status);
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
    
    public void loginUser(final String s, final String s2, final UserAgentCallback mLoginCallback) {
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
    
    public void logoutUser(final UserAgentCallback mLogoutCallback) {
        this.mLogoutCallback = mLogoutCallback;
        this.getService().getClientLogging().flush();
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
    public void profileActivated(String string, final DeviceAccount mCurrentUserAccount) {
        this.mCurrentUserAccount = mCurrentUserAccount;
        for (final UserProfile mCurrentUserProfile : this.mListOfUserProfiles) {
            if (mCurrentUserProfile.getProfileGuid().equals(string)) {
                this.mCurrentUserProfile = mCurrentUserProfile;
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
            final StringBuilder sb = new StringBuilder();
            sb.append("ProfileId not found: ").append(string);
            final ArrayList<String> list = new ArrayList<String>();
            final Iterator<UserProfile> iterator2 = this.mListOfUserProfiles.iterator();
            while (iterator2.hasNext()) {
                list.add(iterator2.next().getProfileGuid());
            }
            sb.append(", guids: ").append(list.toString());
            string = sb.toString();
            Log.e("nf_service_useragent", string);
            service.getClientLogging().getErrorLogging().logHandledException(string);
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
        if (Log.isLoggable("nf_service_useragent", 3)) {
            Log.d("nf_service_useragent", "Is profile switching disabled: " + this.isProfileSwitchingDisabled);
        }
    }
    
    public void removeWebUserProfile(final String s, final UserAgentCallback userAgentCallback) {
        Log.d("nf_service_useragent", "removeWebUserProfile");
        this.launchWebTask(new RemoveWebUserProfilesTask(s, userAgentCallback));
    }
    
    public void selectProfile(final String s) {
        this.mUserAgentStateManager.selectNewProfile(s);
    }
    
    @Override
    public void selectProfileResult(final Status status) {
        UserAgentBroadcastIntents.signalProfileSelectionResult(this.getContext(), status.getStatusCode().getValue(), null);
    }
    
    public void sendRecommendationsToFriends(final String s, final Set<FriendForRecommendation> set, final String s2) {
        Log.d("nf_service_useragent", "sendRecommendationsToFriends");
        this.launchWebTask(new SendRecommendationsTask(s, set, s2));
    }
    
    public void setCurrentAppLocale(final String s) {
        if (this.userLocaleRepository != null) {
            this.userLocaleRepository.setApplicationLanguage(new UserLocale(s));
        }
    }
    
    @Override
    public void switchWebUserProfile(final String s) {
        Log.d("nf_service_useragent", "switchWebUserProfile");
        this.getService().getClientLogging().flush();
        this.launchWebTask(new SwitchWebUserProfilesTask(s));
    }
    
    public void tokenActivate(final ActivationTokens activationTokens, final UserAgentCallback mLoginCallback) {
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
    
    public void verifyPin(final String s, final UserAgentCallback userAgentCallback) {
        this.launchTask((FetchTask<?>)new VerifyPinTask(s, userAgentCallback));
    }
    
    public final class AccountErrorReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if (intent != null) {
                final String action = intent.getAction();
                if (Log.isLoggable("nf_service_useragent", 3)) {
                    Log.i("nf_service_useragent", "AccountErrorReceiver inovoked and received Intent with Action " + action);
                }
                if ("com.netflix.mediaclient.intent.action.DELETED_PROFILE".equals(action)) {
                    UserAgent.this.mCurrentUserProfile = null;
                    UserAgent.this.mUserAgentStateManager.onAccountErrors(context, StatusCode.DELETED_PROFILE);
                }
            }
        }
    }
    
    private class ActivateListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            Log.d("nf_service_useragent", "Received a activate event ");
            if (uiEvent instanceof ActivateEvent) {
                final ActivateEvent activateEvent = (ActivateEvent)uiEvent;
                if (!activateEvent.failed()) {
                    final String cookies = activateEvent.getCookies();
                    final String access$600 = UserAgent.this.extractToken(UserAgent.this.getNetflixIdName() + "=", cookies);
                    final String access$601 = UserAgent.this.extractToken(UserAgent.this.getSecureNetflixIdName() + "=", cookies);
                    if (StringUtils.isNotEmpty(access$600) && StringUtils.isNotEmpty(access$601)) {
                        UserAgent.this.mUserAgentStateManager.accountOrProfileActivated(true, access$600, access$601);
                    }
                }
                else {
                    if (activateEvent.isActionId()) {
                        UserAgent.this.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                        if (Log.isLoggable("nf_service_useragent", 3)) {
                            Log.d("nf_service_useragent", "Received a activate event with ActionID error: " + activateEvent.getActionID() + " Received msg " + activateEvent.getMessage());
                        }
                        final NetflixStatus actionIdResult = StatusUtils.toActionIdResult(activateEvent);
                        UserAgent.this.notifyLoginComplete(actionIdResult);
                        UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), actionIdResult.getStatusCode().getValue(), null);
                        return;
                    }
                    if (activateEvent.isNetworkError()) {
                        Log.d("nf_service_useragent", "Received a activate event with Network error");
                        final NetflixStatus actionIdResult2 = StatusUtils.toActionIdResult(activateEvent);
                        actionIdResult2.setDisplayMessage(true);
                        actionIdResult2.setMessage(activateEvent.getMessage());
                        UserAgent.this.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                        UserAgent.this.notifyLoginComplete(actionIdResult2);
                        UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), StatusCode.NETWORK_ERROR.getValue(), null);
                        return;
                    }
                    Log.e("nf_service_useragent", "Received a unexpected Activate event");
                }
            }
        }
    }
    
    private class AddWebUserProfilesTask implements Runnable
    {
        UserAgentCallback mCallback;
        String profileIconName;
        String profileName;
        boolean startInKidsZone;
        
        public AddWebUserProfilesTask(final String profileName, final boolean startInKidsZone, final String profileIconName, final UserAgentCallback mCallback) {
            this.profileName = profileName;
            this.startInKidsZone = startInKidsZone;
            this.profileIconName = profileIconName;
            this.mCallback = mCallback;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.addWebUserProfile(this.profileName, this.startInKidsZone, this.profileIconName, new ProfilesUpdateCallBackWithResult(this.mCallback));
        }
    }
    
    private class AppResetListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            Log.d("nf_service_useragent", "Received an App reset event ");
            AndroidUtils.clearApplicationData(UserAgent.this.getContext());
            NetflixActivity.finishAllActivities(UserAgent.this.getContext());
            final Intent intent = new Intent();
            intent.setClass(UserAgent.this.getContext(), (Class)NetflixService.class);
            UserAgent.this.getContext().stopService(intent);
        }
    }
    
    private class BindListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            Log.d("nf_service_useragent", "Received a bind event ");
        }
    }
    
    private class ConnectWithFacebookTask extends FetchTask<Void>
    {
        final String accessToken;
        private final UserAgentWebCallback webClientCallback;
        
        public ConnectWithFacebookTask(final String accessToken, final UserAgentCallback userAgentCallback) {
            super(userAgentCallback);
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onConnectWithFacebook(final Status status) {
                    if (status.isSucces()) {
                        UserAgent.this.mCurrentUserProfile.setFacebookConnectStatus(true);
                    }
                    UserAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)ConnectWithFacebookTask.this).getCallback().onConnectWithFacebook(status);
                        }
                    });
                }
            };
            this.accessToken = accessToken;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.connectWithFacebook(this.accessToken, this.webClientCallback);
        }
    }
    
    private class DeactivateListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            Log.d("nf_service_useragent", "Received a deactivate event ");
        }
    }
    
    private class DoDummyWebCallTask implements Runnable
    {
        UserAgentWebCallback mCallback;
        private final UserAgentWebCallback webClientCallback;
        
        public DoDummyWebCallTask(final UserAgentWebCallback mCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onDummyWebCallDone(final Status status) {
                    if (DoDummyWebCallTask.this.mCallback != null) {
                        DoDummyWebCallTask.this.mCallback.onDummyWebCallDone(status);
                        DoDummyWebCallTask.this.mCallback = null;
                    }
                    Log.d("nf_service_useragent", "dummy web call done");
                }
            };
            this.mCallback = mCallback;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.doDummyWebCall(this.webClientCallback);
        }
    }
    
    private class EditWebUserProfilesTask implements Runnable
    {
        UserAgentCallback mCallback;
        String profileIconName;
        String profileId;
        String profileName;
        boolean startInKidsZone;
        
        public EditWebUserProfilesTask(final String profileId, final String profileName, final boolean startInKidsZone, final String profileIconName, final UserAgentCallback mCallback) {
            this.profileId = profileId;
            this.profileName = profileName;
            this.startInKidsZone = startInKidsZone;
            this.profileIconName = profileIconName;
            this.mCallback = mCallback;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.editWebUserProfile(this.profileId, this.profileName, this.startInKidsZone, this.profileIconName, new ProfilesUpdateCallBackWithResult(this.mCallback));
        }
    }
    
    private class FetchAccountDataTask implements Runnable
    {
        private final UserAgentWebCallback webClientCallback;
        
        public FetchAccountDataTask(final UserAgentWebCallback userAgentWebCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onAccountDataFetched(final AccountData accountData, final Status status) {
                    if (status.isSucces()) {
                        UserAgent.this.mListOfUserProfiles = accountData.getUserProfiles();
                        UserAgent.this.mUser = accountData.getUser();
                        UserAgent.this.mSubtitleDefaults = TextStyle.buildSubtitleSettings(UserAgent.this.mUser.getSubtitleDefaults());
                        for (final UserProfile userProfile : UserAgent.this.mListOfUserProfiles) {
                            if (Log.isLoggable("nf_service_useragent", 3)) {
                                Log.d("nf_service_useragent", String.format("fetchAccountData profileName %s profileId %s socialStatus %s", userProfile.getFirstName(), userProfile.getProfileGuid(), userProfile.isSocialConnected()));
                            }
                        }
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.accountDataFetched(accountData);
                        }
                        UserAgent.this.persistListOfUserProfiles(UserAgent.this.mListOfUserProfiles);
                        UserAgent.this.persistUser(UserAgent.this.mUser);
                    }
                    else {
                        if (Log.isLoggable("nf_service_useragent", 6)) {
                            Log.e("nf_service_useragent", "fetchAccountData failed (skipping user info update) with statusCode=" + status.getStatusCode());
                        }
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.accountDataFetchFailed(status, UserAgent.this.isAccountDataAvailable());
                        }
                    }
                }
            };
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.fetchAccountData(this.webClientCallback);
        }
    }
    
    private class FetchAvailableAvatarsListTask implements Runnable
    {
        UserAgentCallback mCallback;
        private final UserAgentWebCallback webClientCallback;
        
        public FetchAvailableAvatarsListTask(final UserAgentCallback mCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onAvatarsListFetched(final List<AvatarInfo> list, final Status status) {
                    FetchAvailableAvatarsListTask.this.mCallback.onAvailableAvatarsListFetched(list, status);
                }
            };
            this.mCallback = mCallback;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.fetchAvailableAvatarsList(this.webClientCallback);
        }
    }
    
    private class FetchFriendsForRecommendationsTask implements Runnable
    {
        private final int fromIndex;
        UserAgentCallback mCallback;
        private final String searchTerm;
        private final String videoId;
        private final UserAgentWebCallback webClientCallback;
        
        public FetchFriendsForRecommendationsTask(final String videoId, final int fromIndex, final String searchTerm, final UserAgentCallback mCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> list, final Status status) {
                    FetchFriendsForRecommendationsTask.this.mCallback.onFriendsForRecommendationsListFetched(list, status);
                }
            };
            this.mCallback = mCallback;
            this.videoId = videoId;
            this.fromIndex = fromIndex;
            this.searchTerm = searchTerm;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.fetchFriendsForRecommendations(this.videoId, this.fromIndex, this.searchTerm, this.webClientCallback);
        }
    }
    
    private class FetchProfileDataTask implements Runnable
    {
        String profileId;
        private final UserAgentWebCallback webClientCallback;
        
        public FetchProfileDataTask(final String profileId, final UserAgentWebCallback userAgentWebCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onProfileDataFetched(final UserProfile userProfile, final Status status) {
                    if (status.isSucces() && UserAgent.this.isLatestProfileDataValid(userProfile)) {
                        if (StringUtils.safeEquals(UserAgent.this.mCurrentUserProfile.toString(), userProfile.toString())) {
                            Log.d("nf_service_useragent", "onProfileDataFetched nothing changed ignore.. ");
                            return;
                        }
                        UserAgent.this.updateAndPersistProfilesList(userProfile);
                        if (!StringUtils.safeEquals(UserAgent.this.mCurrentUserProfile.getLanguagesInCsv(), userProfile.getLanguagesInCsv())) {
                            Log.d("nf_service_useragent", "onProfileDataFetched language changed, update ");
                            UserAgent.this.setUserPreferredLanguages(userProfile.getLanguages());
                        }
                        UserAgent.this.mSubtitleSettings = TextStyle.buildSubtitleSettings(userProfile.getSubtitlePreference());
                        UserAgent.this.mCurrentUserProfile = userProfile;
                    }
                    else if (Log.isLoggable("nf_service_useragent", 6)) {
                        Log.e("nf_service_useragent", "Ignore onProfileDataFetched failed (skipping userProfile update) with statusCode=" + status.getStatusCode());
                    }
                }
            };
            this.profileId = profileId;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.fetchProfileData(this.profileId, this.webClientCallback);
        }
    }
    
    private abstract static class FetchTask<T> implements Runnable
    {
        private final UserAgentCallback callback;
        
        public FetchTask(final UserAgentCallback callback) {
            this.callback = callback;
        }
        
        protected UserAgentCallback getCallback() {
            return this.callback;
        }
    }
    
    public final class PlayStopReceiver extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if (intent != null) {
                final String action = intent.getAction();
                Log.i("nf_service_useragent", "PlayStopReceiver inovoked and received Intent with Action " + action);
                if ("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP".equals(action)) {
                    if (UserAgent.this.getCurrentProfileGuid() != null && UserAgent.this.mCurrentUserProfile != null) {
                        Log.i("nf_service_useragent", "Starting userProfile fetch ");
                        UserAgent.this.fetchProfileData(UserAgent.this.getCurrentProfileGuid());
                        return;
                    }
                    Log.i("nf_service_useragent", "canDoDataFetches false - skipping fetchProfileData request");
                }
            }
        }
    }
    
    private class ProfilesUpdateCallBackWithResult extends SimpleUserAgentWebCallback
    {
        UserAgentCallback mResultCallback;
        
        private ProfilesUpdateCallBackWithResult(final UserAgentCallback mResultCallback) {
            this.mResultCallback = mResultCallback;
        }
        
        @Override
        public void onUserProfilesUpdated(final AccountData accountData, final Status status) {
            UserAgent.this.commonProfilesUpdateCallback.onUserProfilesUpdated(accountData, status);
            this.mResultCallback.onProfilesListUpdateResult(status);
        }
    }
    
    private class RemoveWebUserProfilesTask implements Runnable
    {
        UserAgentCallback mCallback;
        String profileId;
        
        public RemoveWebUserProfilesTask(final String profileId, final UserAgentCallback mCallback) {
            this.profileId = profileId;
            this.mCallback = mCallback;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.removeWebUserProfile(this.profileId, new ProfilesUpdateCallBackWithResult(this.mCallback));
        }
    }
    
    private class SendRecommendationsTask implements Runnable
    {
        private final Set<FriendForRecommendation> friends;
        private final String messageBody;
        private final String videoId;
        private final UserAgentWebCallback webClientCallback;
        
        public SendRecommendationsTask(final String videoId, final Set<FriendForRecommendation> friends, final String messageBody) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onRecommendationsSent(final Set<FriendForRecommendation> set, final Status status) {
                    Label_0182: {
                        if (!status.isSucces() || set == null || set.size() <= 0) {
                            break Label_0182;
                        }
                        int n = 0;
                        Object firstName = null;
                        for (final FriendForRecommendation friendForRecommendation : set) {
                            if (friendForRecommendation.wasRecommended()) {
                                if (firstName == null) {
                                    firstName = friendForRecommendation.getFriendProfile().getFirstName();
                                }
                                else {
                                    ++n;
                                }
                            }
                        }
                        if (firstName == null) {
                            break Label_0182;
                        }
                        String s;
                        if (n == 0) {
                            s = UserAgent.this.getContext().getString(2131493391, new Object[] { firstName });
                        }
                        else {
                            s = UserAgent.this.getContext().getResources().getQuantityString(2131623938, n, new Object[] { n, firstName });
                        }
                        Toast.makeText(UserAgent.this.getContext(), (CharSequence)s, 1).show();
                        return;
                    }
                    if (Log.isLoggable("nf_service_useragent", 6)) {
                        Log.e("nf_service_useragent", "Problem occured trying to send recommendations! Result: " + status + "; Friends: " + set);
                    }
                }
            };
            this.videoId = videoId;
            this.friends = friends;
            this.messageBody = messageBody;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.sendRecommendationsToFriends(this.videoId, this.friends, this.messageBody, this.webClientCallback);
        }
    }
    
    private class SwitchWebUserProfilesTask implements Runnable
    {
        String profileId;
        private final UserAgentWebCallback webClientCallback;
        
        public SwitchWebUserProfilesTask(final String profileId) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onUserProfileSwitched(final UserBoundCookies userBoundCookies, final Status status) {
                    if (status.isSucces()) {
                        if (Log.isLoggable("nf_service_useragent", 3)) {
                            Log.d("nf_service_useragent", String.format("switchWebUserProfile  netflixId %s secureNetflixId %s", userBoundCookies.getUserBoundNetflixId(), userBoundCookies.getUserBoundSecureNetflixId()));
                        }
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.profileSwitched(userBoundCookies);
                        }
                    }
                    else {
                        if (Log.isLoggable("nf_service_useragent", 6)) {
                            Log.e("nf_service_useragent", "switchWebUserProfile failed  with statusCode=" + status.getStatusCode());
                        }
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.profileSwitchedFailed(status);
                        }
                    }
                }
            };
            this.profileId = profileId;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.switchWebUserProfile(this.profileId, this.webClientCallback);
        }
    }
    
    public interface UserAgentCallback
    {
        void onAvailableAvatarsListFetched(final List<AvatarInfo> p0, final Status p1);
        
        void onConnectWithFacebook(final Status p0);
        
        void onFriendsForRecommendationsListFetched(final List<FriendForRecommendation> p0, final Status p1);
        
        void onLoginComplete(final Status p0);
        
        void onLogoutComplete(final Status p0);
        
        void onPinVerified(final boolean p0, final Status p1);
        
        void onProfilesListUpdateResult(final Status p0);
        
        void onSocialNotificationsListFetched(final SocialNotificationsList p0, final Status p1);
    }
    
    private class VerifyPinTask extends FetchTask<Void>
    {
        final String enteredPin;
        private final UserAgentWebCallback webClientCallback;
        
        public VerifyPinTask(final String enteredPin, final UserAgentCallback userAgentCallback) {
            super(userAgentCallback);
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onPinVerified(final boolean b, final Status status) {
                    UserAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)VerifyPinTask.this).getCallback().onPinVerified(b, status);
                        }
                    });
                }
            };
            this.enteredPin = enteredPin;
        }
        
        @Override
        public void run() {
            UserAgent.this.mUserWebClient.verifyPin(this.enteredPin, this.webClientCallback);
        }
    }
}
