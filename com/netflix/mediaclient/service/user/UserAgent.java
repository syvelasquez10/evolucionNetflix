// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.repository.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.PreferenceUtils;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import android.os.Handler;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.configuration.SimpleConfigurationAgentWebCallback;
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
    private static final String BIND = "bind";
    private static final String DEACTIVATED = "deactivated";
    private static final String NETFLIX_ID = "NetflixId";
    private static final String NETFLIX_ID_TEST = "NetflixIdTest";
    private static final String SECURE_NETFLIX_ID = "SecureNetflixId";
    private static final String SECURE_NETFLIX_ID_TEST = "SecureNetflixIdTest";
    private static final String TAG = "nf_service_useragent";
    private final ConfigurationAgentWebCallback configDataCallback;
    private boolean isProfileSwitchingDisabled;
    private EventListener mActivateListener;
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
        this.configDataCallback = new SimpleConfigurationAgentWebCallback() {
            @Override
            public void onConfigDataFetched(final ConfigData configData, final int n) {
                if (n == 0) {
                    Log.d("nf_service_useragent", "pfetchUserData");
                    UserAgent.this.launchWebTask(new FetchAccountDataTask(null));
                    return;
                }
                UserAgent.this.mUserAgentStateManager.accountDataFetchFailed(n, UserAgent.this.isAccountDataAvailable());
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
    
    private void doLoginComplete() {
        this.notifyLoginComplete(0, null);
        this.getApplication().setSignedInOnce();
        PreferenceUtils.putBooleanPref(this.getContext(), "nf_user_status_loggedin", true);
    }
    
    private void doLogoutComplete() {
        this.notifyLogoutComplete(0);
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
        return userProfile != null && StringUtils.safeEquals(this.getCurrentProfileId(), userProfile.getProfileId());
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
    
    private void notifyLoginComplete(final int n, final String s) {
        this.getMainHandler().post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (UserAgent.this.mLoginCallback != null) {
                    UserAgent.this.mLoginCallback.onLoginComplete(n, s);
                    UserAgent.this.mLoginCallback = null;
                }
            }
        });
    }
    
    private void notifyLogoutComplete(final int n) {
        this.getMainHandler().post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (UserAgent.this.mLogoutCallback != null) {
                    UserAgent.this.mLogoutCallback.onLogoutComplete(0);
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
    
    private void registerPlayStopReceiver() {
        this.getContext().registerReceiver((BroadcastReceiver)this.mPlayStopReceiver, new IntentFilter("com.netflix.mediaclient.intent.action.LOCAL_PLAYER_PLAY_STOP"));
    }
    
    private void setUserPreferredLanguages(final String[] preferredLanguages) {
        this.userLocaleRepository.setPreferredLanguages(StringUtils.joinArray(preferredLanguages));
        this.getNrdController().setPreferredLanguages(preferredLanguages);
        this.getNrdController().setDeviceLocale(this.userLocaleRepository.getCurrentAppLocale().getLocale());
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
        } while (!StringUtils.safeEquals(userProfile3.getProfileId(), userProfile.getProfileId()));
        if (userProfile3 != null) {
            this.mListOfUserProfiles.remove(userProfile3);
        }
        this.mListOfUserProfiles.add(userProfile);
        this.persistListOfUserProfiles(this.mListOfUserProfiles);
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
        }
        this.userLocaleRepository = null;
        this.unregisterPlayStopReceiver();
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
            this.initCompleted(-4);
            Log.e("nf_service_useragent", "NRDP is NOT READY");
            return;
        }
        this.mRegistration = this.mNrdp.getRegistration();
        this.mActivateListener = new ActivateListener();
        this.mDeactivateListener = new DeactivateListener();
        this.mBindListener = new BindListener();
        this.mRegistration.addEventListener("activateComplete", this.mActivateListener);
        this.mRegistration.addEventListener("activate", this.mActivateListener);
        this.mRegistration.addEventListener("deactivated", this.mDeactivateListener);
        this.mRegistration.addEventListener("bind", this.mBindListener);
        (this.mUserAgentStateManager = new UserAgentStateManager(this.mRegistration, this.getConfigurationAgent().getDrmManager(), (UserAgentStateManager.StateManagerCallback)this, this.getContext(), this.getService().getClientLogging().getErrorLogging())).initialize(this.getConfigurationAgent().isLogoutRequired(), this.getConfigurationAgent().isEsnMigrationRequired());
        this.registerPlayStopReceiver();
    }
    
    @Override
    public void fetchAccountData() {
        Log.d("nf_service_useragent", "fetch account level config data");
        this.getConfigurationAgent().fetchAccountConfigData(this.configDataCallback);
    }
    
    public void fetchProfileData(final String s) {
        Log.d("nf_service_useragent", "fetchProfileData");
        this.launchWebTask(new FetchProfileDataTask(s, null));
    }
    
    public List<? extends com.netflix.mediaclient.servicemgr.UserProfile> getAllProfiles() {
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
    public String getCurrentProfileId() {
        Log.d("nf_service_useragent", "getCurrentProfileId called");
        if (this.mCurrentUserProfile == null) {
            return null;
        }
        return this.mCurrentUserProfile.getProfileId();
    }
    
    public String getCurrentProfileLastName() {
        Log.d("nf_service_useragent", "getCurrentProfileLastName called");
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getCurrentProfileLastName is null");
            return null;
        }
        return this.mCurrentUserProfile.getLastName();
    }
    
    public String getCurrentProfileUserId() {
        Log.d("nf_service_useragent", "getCurrentProfileUserId called");
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "getCurrentProfileUserId is null");
            return null;
        }
        return this.mCurrentUserProfile.getUserId();
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
        Log.d("nf_service_useragent", "getNetfilxID request");
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
        Log.d("nf_service_useragent", "getSecureNetfilxID request");
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
    
    public String getUserId() {
        Log.d("nf_service_useragent", "getUserId called");
        if (this.mUser == null) {
            return null;
        }
        return this.mUser.getUserId();
    }
    
    @Override
    public TextStyle getUserSubtitlePreferences() {
        return this.mSubtitleSettings;
    }
    
    @Override
    public void initialized(final int n) {
        this.initCompleted(n);
    }
    
    public boolean isCurrentProfileFacebookConnected() {
        Log.d("nf_service_useragent", "isCurrentProfileFacebookConnected called");
        return this.mCurrentUserProfile != null && this.mCurrentUserProfile.isSocialConnected();
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
            this.notifyLoginComplete(-4, null);
            return;
        }
        if (!this.mUserAgentStateManager.activateAccByEmailPassword(s, s2)) {
            this.notifyLoginComplete(-41, null);
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
        if (!this.isUserLoggedIn()) {
            this.notifyLogoutComplete(0);
            return;
        }
        if (this.mCurrentUserProfile != null) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT");
            intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
            intent.putExtra("uid", this.getService().getUserId());
            intent.putExtra("nid", this.getNetflixID());
            intent.putExtra("sid", this.getSecureNetflixID());
            intent.putExtra("esn", this.getConfigurationAgent().getEsnProvider().getEsn());
            intent.putExtra("device_cat", this.getConfigurationAgent().getDeviceCategory().getValue());
            intent.putExtra("uid", this.getService().getCurrentProfileUserId());
            LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
        }
        this.mUserAgentStateManager.signoutAcc();
    }
    
    @Override
    public void profileActivated(final String s, final DeviceAccount mCurrentUserAccount) {
        this.mCurrentUserAccount = mCurrentUserAccount;
        for (final UserProfile mCurrentUserProfile : this.mListOfUserProfiles) {
            if (mCurrentUserProfile.getProfileId().equals(s)) {
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
    
    public void selectProfile(final String s) {
        this.mUserAgentStateManager.selectNewProfile(s);
    }
    
    @Override
    public void selectProfileResult(final int n) {
        UserAgentBroadcastIntents.signalProfileSelectionResult(this.getContext(), n, null);
    }
    
    public void setCurrentAppLocale(final String s) {
        if (this.userLocaleRepository != null) {
            this.userLocaleRepository.setApplicationLanguage(new UserLocale(s));
        }
    }
    
    @Override
    public void switchWebUserProfile(final String s) {
        Log.d("nf_service_useragent", "switchWebUserProfile");
        this.launchWebTask(new SwitchWebUserProfilesTask(s));
    }
    
    public void tokenActivate(final ActivationTokens activationTokens, final UserAgentCallback mLoginCallback) {
        Log.d("nf_service_useragent", "loginUser tokenActivate");
        if (this.mUserAgentStateManager == null) {
            this.notifyLoginComplete(-4, null);
            return;
        }
        if (!this.mUserAgentStateManager.activateAccByToken(activationTokens)) {
            this.notifyLoginComplete(-41, null);
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
        this.doLoginComplete();
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
    
    private class ActivateListener implements EventListener
    {
        @Override
        public void received(final UIEvent uiEvent) {
            Log.d("nf_service_useragent", "Received a activate event ");
            if (uiEvent instanceof ActivateEvent) {
                final ActivateEvent activateEvent = (ActivateEvent)uiEvent;
                if (!activateEvent.failed()) {
                    final String cookies = activateEvent.getCookies();
                    final String access$500 = UserAgent.this.extractToken(UserAgent.this.getNetflixIdName() + "=", cookies);
                    final String access$501 = UserAgent.this.extractToken(UserAgent.this.getSecureNetflixIdName() + "=", cookies);
                    if (StringUtils.isNotEmpty(access$500) && StringUtils.isNotEmpty(access$501)) {
                        UserAgent.this.mUserAgentStateManager.accountOrProfileActivated(true, access$500, access$501);
                    }
                }
                else if (activateEvent.isActionId()) {
                    UserAgent.this.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                    final int actionID = activateEvent.getActionID();
                    Log.d("nf_service_useragent", "Received a activate event with ActionID error: " + actionID + " Received msg " + activateEvent.getMessage());
                    switch (actionID) {
                        default: {
                            UserAgent.this.notifyLoginComplete(-4, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -4, null);
                        }
                        case 1: {
                            UserAgent.this.notifyLoginComplete(-200, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -200, null);
                        }
                        case 2: {
                            UserAgent.this.notifyLoginComplete(-201, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -201, null);
                        }
                        case 3: {
                            UserAgent.this.notifyLoginComplete(-202, activateEvent.getMessage());
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -202, activateEvent.getMessage());
                        }
                        case 4: {
                            UserAgent.this.notifyLoginComplete(-203, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -203, null);
                        }
                        case 5: {
                            UserAgent.this.notifyLoginComplete(-204, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -204, null);
                        }
                        case 6: {
                            UserAgent.this.notifyLoginComplete(-205, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -205, null);
                        }
                        case 7: {
                            UserAgent.this.notifyLoginComplete(-206, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -206, null);
                        }
                        case 8: {
                            UserAgent.this.notifyLoginComplete(-207, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -207, null);
                        }
                        case 9: {
                            UserAgent.this.notifyLoginComplete(-208, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -208, null);
                        }
                        case 10: {
                            UserAgent.this.notifyLoginComplete(-209, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -209, null);
                        }
                        case 11: {
                            UserAgent.this.notifyLoginComplete(-210, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -210, null);
                        }
                        case 12: {
                            UserAgent.this.notifyLoginComplete(-211, null);
                            UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -211, null);
                        }
                    }
                }
                else {
                    if (activateEvent.isNetworkError()) {
                        Log.d("nf_service_useragent", "Received a activate event with Network error");
                        UserAgent.this.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                        UserAgent.this.notifyLoginComplete(-3, activateEvent.getMessage());
                        UserAgentBroadcastIntents.signalProfileSelectionResult(UserAgent.this.getContext(), -3, null);
                        return;
                    }
                    Log.e("nf_service_useragent", "Received a unexpected Activate event");
                }
            }
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
                public void onConnectWithFacebook(final int n, final String s) {
                    if (n == 0) {
                        UserAgent.this.mCurrentUserProfile.setFacebookConnectStatus(true);
                    }
                    UserAgent.this.getMainHandler().post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            ((FetchTask)ConnectWithFacebookTask.this).getCallback().onConnectWithFacebook(n, s);
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
                public void onDummyWebCallDone(final int n) {
                    if (DoDummyWebCallTask.this.mCallback != null) {
                        DoDummyWebCallTask.this.mCallback.onDummyWebCallDone(n);
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
    
    private class FetchAccountDataTask implements Runnable
    {
        private final UserAgentWebCallback webClientCallback;
        
        public FetchAccountDataTask(final UserAgentWebCallback userAgentWebCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onAccountDataFetched(final AccountData accountData, final int n) {
                    if (n == 0) {
                        UserAgent.this.mListOfUserProfiles = accountData.getUserProfiles();
                        UserAgent.this.mUser = accountData.getUser();
                        UserAgent.this.mSubtitleDefaults = TextStyle.buildSubtitleSettings(UserAgent.this.mUser.getSubtitleDefaults());
                        for (final UserProfile userProfile : UserAgent.this.mListOfUserProfiles) {
                            Log.d("nf_service_useragent", String.format("fetchAccountData profileName %s profileId %s socialStatus %s", userProfile.getFirstName(), userProfile.getProfileId(), userProfile.isSocialConnected()));
                        }
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.accountDataFetched(accountData);
                        }
                        UserAgent.this.persistListOfUserProfiles(UserAgent.this.mListOfUserProfiles);
                        UserAgent.this.persistUser(UserAgent.this.mUser);
                    }
                    else {
                        Log.e("nf_service_useragent", "fetchAccountData failed (skipping user info update) with statusCode=" + n);
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.accountDataFetchFailed(n, UserAgent.this.isAccountDataAvailable());
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
    
    private class FetchProfileDataTask implements Runnable
    {
        String profileId;
        private final UserAgentWebCallback webClientCallback;
        
        public FetchProfileDataTask(final String profileId, final UserAgentWebCallback userAgentWebCallback) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onProfileDataFetched(final UserProfile userProfile, final int n) {
                    if (n != 0 || !UserAgent.this.isLatestProfileDataValid(userProfile)) {
                        Log.e("nf_service_useragent", "Ignore onProfileDataFetched failed (skipping userProfile update) with statusCode=" + n);
                        return;
                    }
                    if (!StringUtils.safeEquals(UserAgent.this.mCurrentUserProfile.toString(), userProfile.toString())) {
                        UserAgent.this.updateAndPersistProfilesList(userProfile);
                        if (!StringUtils.safeEquals(UserAgent.this.mCurrentUserProfile.getLanguagesInCsv(), userProfile.getLanguagesInCsv())) {
                            Log.d("nf_service_useragent", "onProfileDataFetched language changed, update ");
                            UserAgent.this.setUserPreferredLanguages(userProfile.getLanguages());
                        }
                        UserAgent.this.mSubtitleSettings = TextStyle.buildSubtitleSettings(userProfile.getSubtitlePreference());
                        UserAgent.this.mCurrentUserProfile = userProfile;
                        return;
                    }
                    Log.d("nf_service_useragent", "onProfileDataFetched nothing changed ignore.. ");
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
                    if (UserAgent.this.getCurrentProfileId() != null && UserAgent.this.mCurrentUserProfile != null) {
                        Log.i("nf_service_useragent", "Starting userProfile fetch ");
                        UserAgent.this.fetchProfileData(UserAgent.this.getCurrentProfileId());
                        return;
                    }
                    Log.i("nf_service_useragent", "canDoDataFetches false - skipping fetchProfileData request");
                }
            }
        }
    }
    
    private class SwitchWebUserProfilesTask implements Runnable
    {
        String profileId;
        private final UserAgentWebCallback webClientCallback;
        
        public SwitchWebUserProfilesTask(final String profileId) {
            this.webClientCallback = new SimpleUserAgentWebCallback() {
                @Override
                public void onUserProfileSwitched(final UserBoundCookies userBoundCookies, final int n) {
                    if (n == 0) {
                        Log.d("nf_service_useragent", String.format("switchWebUserProfile  netflixId %s secureNetflixId %s", userBoundCookies.getUserBoundNetflixId(), userBoundCookies.getUserBoundSecureNetflixId()));
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.profileSwitched(userBoundCookies);
                        }
                    }
                    else {
                        Log.e("nf_service_useragent", "switchWebUserProfile failed  with statusCode=" + n);
                        if (UserAgent.this.mUserAgentStateManager != null) {
                            UserAgent.this.mUserAgentStateManager.profileSwitchedFailed(n);
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
        void onConnectWithFacebook(final int p0, final String p1);
        
        void onLoginComplete(final int p0, final String p1);
        
        void onLogoutComplete(final int p0);
    }
}
