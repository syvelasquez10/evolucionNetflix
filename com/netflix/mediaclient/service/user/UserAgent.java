// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.util.PrivacyUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.webapi.WebApiCommand;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
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
    private static final String TAG = "nf_service_useragent";
    private final UserAgentWebCallback commonProfilesUpdateCallback;
    private final ConfigurationAgentWebCallback configDataCallback;
    private boolean isProfileSwitchingDisabled;
    private Status localeSupportStatus;
    private UserAgent$AccountErrorReceiver mAccountErrorReceiver;
    private EventListener mActivateListener;
    private EventListener mAppResetListener;
    private EventListener mBindListener;
    private DeviceAccount mCurrentUserAccount;
    private UserProfile mCurrentUserProfile;
    private EventListener mDeactivateListener;
    private String mDynecomNetflixId;
    private String mDynecomSecureNetflixId;
    private boolean mIsNonMemberPlayback;
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
        this.localeSupportStatus = CommonStatus.OK;
        this.commonProfilesUpdateCallback = new UserAgent$6(this);
        this.configDataCallback = new UserAgent$7(this);
    }
    
    private List<UserProfile> buildListOfUserProfiles(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "populateListOfUserProfiles with json " + s);
        }
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        final String stringPref = PreferenceUtils.getStringPref(this.getContext(), "useragent_current_userprofile_guid", null);
        final ArrayList<UserProfile> list = new ArrayList<UserProfile>();
        try {
            final JSONArray jsonArray = (JSONArray)new JSONTokener(s).nextValue();
            for (int i = 0; i < jsonArray.length(); ++i) {
                final UserProfile mCurrentUserProfile = new UserProfile(jsonArray.opt(i).toString());
                if (Log.isLoggable()) {
                    Log.d("nf_service_useragent", "has userprofile " + mCurrentUserProfile);
                }
                list.add(mCurrentUserProfile);
                if (this.mCurrentUserProfile == null && StringUtils.safeEquals(mCurrentUserProfile.getProfileGuid(), stringPref)) {
                    this.mCurrentUserProfile = mCurrentUserProfile;
                }
            }
        }
        catch (JSONException ex) {
            Log.e("nf_service_useragent", "error while populateListOfUserProfiles " + ex);
        }
        return list;
    }
    
    private NrmConfigData buildNewNrmConfigData() {
        final NrmConfigData nrmConfigData = new NrmConfigData();
        nrmConfigData.isUserBound = true;
        final NrmConfigData nrmConfigData2 = this.getConfigurationAgent().getNrmConfigData();
        String netflixId;
        if (nrmConfigData2 != null) {
            netflixId = nrmConfigData2.netflixId;
        }
        else {
            netflixId = "";
        }
        Log.d("nf_service_useragent", "transferUserCookiesIntoNrmConfig - bf4- netflixId: %s", netflixId);
        if (nrmConfigData2 == null) {
            nrmConfigData.netflixId = this.mDynecomNetflixId;
            nrmConfigData.secureNetflixId = this.mDynecomSecureNetflixId;
        }
        else {
            if (StringUtils.isNotEmpty(this.mDynecomNetflixId) && !StringUtils.safeEquals(this.mDynecomNetflixId, nrmConfigData2.netflixId)) {
                nrmConfigData.netflixId = this.mDynecomNetflixId;
            }
            if (StringUtils.isNotEmpty(this.mDynecomSecureNetflixId) && !StringUtils.safeEquals(this.mDynecomSecureNetflixId, nrmConfigData2.secureNetflixId)) {
                nrmConfigData.secureNetflixId = this.mDynecomSecureNetflixId;
                return nrmConfigData;
            }
        }
        return nrmConfigData;
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
        SignInLogUtils.reportSignInRequestSessionEnded(this.getContext(), (SignInLogging$SignInType)null, IClientLogging$CompletionReason.success, (Error)null);
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
        final NetflixPreference netflixPreference = new NetflixPreference(this.getContext());
        netflixPreference.removePref("useragent_userprofiles_data");
        netflixPreference.removePref("useragent_user_data");
        netflixPreference.removePref("useragent_current_userprofile_guid");
        netflixPreference.putBooleanPref("nf_user_status_loggedin", false);
        PartnerReceiver.broadcastUserStatus(this.getContext(), false);
        netflixPreference.putBooleanPref("user_profile_was_selected", false);
        netflixPreference.commit();
    }
    
    private String extractToken(final String s, final String s2) {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "Extracting token: " + s);
        }
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
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "Execute autologin with token: " + stringExtra);
        }
        if (this.mUser != null) {
            Log.e("nf_service_useragent", "User is already logged in, autologin is NOT possible!");
            return;
        }
        SignInLogUtils.reportSignInRequestSessionStarted(this.getContext(), SignInLogging$SignInType.autologin);
        this.mUserWebClient.autoLogin(stringExtra, new UserAgent$9(this));
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
        this.getMainHandler().post((Runnable)new UserAgent$2(this, status));
    }
    
    private void notifyLogoutComplete(final StatusCode statusCode) {
        this.getMainHandler().post((Runnable)new UserAgent$3(this));
    }
    
    private void notifyOtherOfProfileActivated() {
        UserAgentBroadcastIntents.signalProfileActive(this.getContext());
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN");
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(intent);
        this.getService().getClientLogging().getBreadcrumbLogging().leaveBreadcrumb("Login complete");
        PartnerReceiver.broadcastUserStatus(this.getContext(), true);
    }
    
    private void persistCurrentProfileGuid(final UserProfile userProfile) {
        if (Log.isLoggable()) {
            Log.d("nf_service_useragent", "persistCurrentProfileGuid " + userProfile);
        }
        if (userProfile != null) {
            PreferenceUtils.putStringPref(this.getContext(), "useragent_current_userprofile_guid", userProfile.getProfileGuid());
            return;
        }
        PreferenceUtils.removePref(this.getContext(), "useragent_current_userprofile_guid");
    }
    
    private void persistListOfUserProfiles(final List<UserProfile> list) {
        if (list == null) {
            return;
        }
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
    
    private void setCurrentUserProfile(final UserProfile mCurrentUserProfile) {
        this.mCurrentUserProfile = mCurrentUserProfile;
    }
    
    private void setPrimaryProfileLocale(final AccountData accountData) {
        if (accountData != null && accountData.getUserProfiles() != null) {
            for (final UserProfile userProfile : accountData.getUserProfiles()) {
                if (userProfile.isPrimaryProfile() && this.getCurrentAppLocale() == null) {
                    this.setUserPreferredLanguages(userProfile.getLanguages());
                    this.getApplication().refreshLocale(this.getCurrentAppLocale());
                }
            }
        }
    }
    
    private void transferUserCookiesIntoNrmConfig() {
        final NrmConfigData buildNewNrmConfigData = this.buildNewNrmConfigData();
        this.getConfigurationAgent().persistNrmConfigData(buildNewNrmConfigData);
        Log.d("nf_service_useragent", "transferUserCookiesIntoNrmConfig - aftr - netflixId: %s, mDynecomNetflixId: %s", buildNewNrmConfigData.netflixId, this.mDynecomNetflixId);
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
    
    private void verifyLoginViaDynecom(final String s, final String s2, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.getConfigurationAgent().verifyLoginViaDynecom(s, s2, new UserAgent$1(this, userAgent$UserAgentCallback));
    }
    
    public void addWebUserProfile(final String s, final boolean b, final String s2, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "addWebUserProfile");
        this.launchWebTask(new UserAgent$AddWebUserProfilesTask(this, s, b, s2, userAgent$UserAgentCallback));
    }
    
    public void consumeUmaAlert() {
        if (this.mUser != null) {
            final UmaAlert userMessageAlert = this.getUserMessageAlert();
            if (userMessageAlert != null) {
                if (Log.isLoggable()) {
                    Log.d("nf_service_useragent", "UMA marked as consumed");
                }
                userMessageAlert.setConsumed(true);
                this.persistUser(this.mUser);
                LocalBroadcastManager.getInstance(this.getContext()).sendBroadcast(new Intent("RefreshUserMessageRequest.ACTION_UMA_MESSAGE_CONSUMED"));
            }
        }
    }
    
    public void createAutoLoginToken(final long n, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        if (userAgent$UserAgentCallback == null) {
            throw new IllegalStateException("Callback can not be null!");
        }
        Log.d("nf_service_useragent", "Create auto login token");
        this.launchTask(new UserAgent$CreateAutoLoginTokenTask(this, n, userAgent$UserAgentCallback));
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
            this.getService().getBookmarkStore().updateValidProfiles(this.mListOfUserProfiles);
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
        while (true) {
            Label_0295: {
                if (!this.getConfigurationAgent().shouldAlertForMissingLocale()) {
                    break Label_0295;
                }
                final UserLocaleRepository userLocaleRepository = this.userLocaleRepository;
                if (UserLocaleRepository.wasPreviouslyAlerted(this.getContext())) {
                    break Label_0295;
                }
                final NetflixImmutableStatus localeSupportStatus = CommonStatus.NON_SUPPORTED_LOCALE;
                this.localeSupportStatus = localeSupportStatus;
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
                return;
            }
            final NetflixImmutableStatus localeSupportStatus = CommonStatus.OK;
            continue;
        }
    }
    
    public void doOnRampEligibilityAction(final OnRampEligibility$Action onRampEligibility$Action, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.launchTask(new UserAgent$DoOnRampEligibilityAction(this, onRampEligibility$Action, userAgent$UserAgentCallback));
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
    
    public void fetchProfileData(final String s) {
        Log.d("nf_service_useragent", "fetchProfileData");
        this.launchWebTask(new UserAgent$FetchProfileDataTask(this, s, null));
    }
    
    public void fetchSurvey(final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.launchTask(new UserAgent$FetchSurveyTask(this, userAgent$UserAgentCallback));
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
    
    @Override
    public String getCurrentAppLanguage() {
        if (this.userLocaleRepository == null) {
            return null;
        }
        if (this.mCurrentUserProfile == null || this.mCurrentUserProfile.getLanguagesList() == null || this.mCurrentUserProfile.getLanguagesList().size() < 1) {
            return this.userLocaleRepository.getCurrentAppLocale().getRaw();
        }
        final UserLocale userLocale = new UserLocale(this.mCurrentUserProfile.getLanguagesList().get(0));
        final UserLocale currentAppLocale = this.userLocaleRepository.getCurrentAppLocale();
        if (Log.isLoggable()) {
            final String raw = userLocale.getRaw();
            final String raw2 = currentAppLocale.getRaw();
            String s;
            if (currentAppLocale.equalsByLanguage(userLocale)) {
                s = userLocale.getRaw();
            }
            else {
                s = currentAppLocale.getRaw();
            }
            Log.d("nf_service_useragent", String.format("nf_loc userPref:%s appLocaleRaw:%s - picking %s", raw, raw2, s));
        }
        if (currentAppLocale.equalsByLanguage(userLocale)) {
            return userLocale.getRaw();
        }
        return currentAppLocale.getRaw();
    }
    
    @Override
    public UserLocale getCurrentAppLocale() {
        return this.userLocaleRepository.getCurrentAppLocale();
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
    
    public String getEmail() {
        if (this.mUser != null) {
            return this.mUser.getEmail();
        }
        return null;
    }
    
    public EogAlert getEogAlert() {
        if (this.mUser != null) {
            return this.mUser.eogAlert;
        }
        return null;
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
    public IMSLClient$MSLUserCredentialRegistry getMSLUserCredentialRegistry() {
        return new UserAgent$10(this);
    }
    
    @Override
    public String getNetflixID() {
        if (this.isUserLoggedIn()) {
            return this.mCurrentUserAccount.getNetflixId();
        }
        if (this.getConfigurationAgent() != null && this.getConfigurationAgent().getNrmConfigData() != null) {
            return this.getConfigurationAgent().getNrmConfigData().netflixId;
        }
        return null;
    }
    
    @Override
    public String getNetflixIdName() {
        return WebApiCommand.getNetflixIdName();
    }
    
    @Override
    public String getPrimaryProfileGuid() {
        Log.i("nf_service_useragent", "getPrimaryProfileGuid");
        if (this.mListOfUserProfiles != null) {
            for (final UserProfile userProfile : this.mListOfUserProfiles) {
                Log.i("nf_service_useragent", "getPrimaryProfileGuid " + userProfile.getFirstName());
                if (userProfile.isPrimaryProfile()) {
                    return userProfile.getProfileGuid();
                }
            }
        }
        return null;
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
        if (this.isUserLoggedIn()) {
            return this.mCurrentUserAccount.getSecureId();
        }
        if (this.getConfigurationAgent() != null && this.getConfigurationAgent().getNrmConfigData() != null) {
            return this.getConfigurationAgent().getNrmConfigData().secureNetflixId;
        }
        return null;
    }
    
    @Override
    public String getSecureNetflixIdName() {
        return WebApiCommand.getSecureNetflixIdName();
    }
    
    @Override
    public TextStyle getSubtitleDefaults() {
        return this.mSubtitleDefaults;
    }
    
    public ThumbMessaging getThumbMessaging() {
        if (this.mUser != null) {
            return this.mUser.getThumbMessaging();
        }
        return null;
    }
    
    @Override
    public UserCredentialRegistry getUserCredentialRegistry() {
        return this;
    }
    
    public UmaAlert getUserMessageAlert() {
        if (!KidsUtils.isKidsProfile((com.netflix.mediaclient.servicemgr.interface_.user.UserProfile)this.getCurrentProfile()) && this.mUser != null) {
            return this.mUser.getUmaAlert();
        }
        return null;
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
        Status localeSupportStatus = status;
        if (status.equals(CommonStatus.OK)) {
            localeSupportStatus = status;
            if (!this.localeSupportStatus.equals(CommonStatus.OK)) {
                localeSupportStatus = this.localeSupportStatus;
            }
        }
        this.initCompleted(localeSupportStatus);
    }
    
    @Override
    public boolean isAgeVerified() {
        return this.mUser != null && this.mUser.isAgeVerified();
    }
    
    @Override
    public boolean isCurrentProfileInstantQueueEnabled() {
        if (this.mCurrentUserProfile == null) {
            Log.d("nf_service_useragent", "isCurrentProfileInstantQueueEnabled is null");
        }
        else if (!KidsUtils.isKidsParity(this.getContext()) || !this.mCurrentUserProfile.isKidsProfile() || !KidsUtils.isMyListForKidsDisabled()) {
            if (Log.isLoggable()) {
                Log.d("nf_service_useragent", String.format("isCurrentProfileInstantQueueEnabled %s called: %b ", this.mCurrentUserProfile.getFirstName(), this.mCurrentUserProfile.isIQEnabled()));
            }
            return this.mCurrentUserProfile.isIQEnabled();
        }
        return false;
    }
    
    public boolean isNonMemberPlayback() {
        return this.mIsNonMemberPlayback;
    }
    
    @Override
    public boolean isPotentialPrivacyViolationFoundForLogging(final String s) {
        if (PrivacyUtils.isPotentialPrivacyViolationFound(s, (com.netflix.mediaclient.servicemgr.interface_.user.User)this.mUser)) {
            if (Log.isLoggable()) {
                Log.w("nf_service_useragent", "Privacy violation for " + s + " found with current user " + this.mUser);
            }
            return true;
        }
        for (final UserProfile userProfile : this.mListOfUserProfiles) {
            if (PrivacyUtils.isPotentialPrivacyViolationFound(s, (com.netflix.mediaclient.servicemgr.interface_.user.UserProfile)userProfile)) {
                if (Log.isLoggable()) {
                    Log.w("nf_service_useragent", "Privacy violation for " + s + " found with profile " + userProfile);
                }
                return true;
            }
        }
        Log.d("nf_service_useragent", "Privacy violatoon NOT found, value can be logged safely.");
        return false;
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
        if (this.getConfigurationAgent().isDynecomSignInEnabled()) {
            Log.d("nf_service_useragent", "Login via Dynecom");
            this.verifyLoginViaDynecom(s, s2, mLoginCallback);
        }
        else {
            Log.d("nf_service_useragent", "Login via NCCP");
            if (!this.mUserAgentStateManager.activateAccByEmailPassword(s, s2)) {
                this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_REGISTRATION_EXISTS, "UserAgent: activateAccByEmailPassword fails, NRD registration exist", false, RootCause.clientFailure));
                return;
            }
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
        ((VoipAuthorizationTokensUpdater)this.getService().getVoip()).removeUserAuthorizationTokens();
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
    
    public void markSurveysAsRead() {
        this.launchTask(new UserAgent$MarkSurveysAsReadTask(this));
    }
    
    @Override
    public void profileActivated(final String s, final DeviceAccount mCurrentUserAccount) {
        this.mCurrentUserAccount = mCurrentUserAccount;
        for (final UserProfile currentUserProfile : this.mListOfUserProfiles) {
            if (currentUserProfile.getProfileGuid().equals(s)) {
                this.setCurrentUserProfile(currentUserProfile);
                this.persistCurrentProfileGuid(this.mCurrentUserProfile);
                BrowseExperience.refresh(this.getContext(), this.mCurrentUserProfile);
                PersistentConfig.refresh();
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
    
    public void recordPlanSelection(final String s, final String s2) {
        if (StringUtils.isNotEmpty(s) && StringUtils.isNotEmpty(s2)) {
            if (Log.isLoggable()) {
                Log.d("nf_service_useragent", String.format("record ums planSelection plandId: %s, priceTier:%s", s, s2));
            }
            this.launchWebTask(new UserAgent$RecordPlanSelection(this, s, s2));
            return;
        }
        Log.d("nf_service_useragent", "planId or priceTier is null - skip reporting");
    }
    
    public void recordThumbRatingThanksSeen() {
        this.mUser.setThumbMessaging(ThumbMessaging.builder().setShouldShowOneTimeProfileThumbsMessage(this.mUser.getThumbMessaging().shouldShowOneTimeProfileThumbsMessage()).setShouldShowFirstThumbsRatingMessage(false).build());
    }
    
    public void recordThumbRatingWelcomeSeen() {
        this.mUser.setThumbMessaging(ThumbMessaging.builder().setShouldShowOneTimeProfileThumbsMessage(false).setShouldShowFirstThumbsRatingMessage(this.mUser.getThumbMessaging().shouldShowFirstThumbsRatingMessage()).build());
        this.launchWebTask(new UserAgent$4(this));
    }
    
    public void recordUmsImpression(final String s, final String s2) {
        if (StringUtils.isNotEmpty(s) && StringUtils.isNotEmpty(s2)) {
            if (Log.isLoggable()) {
                Log.d("nf_service_useragent", String.format("record ums impression msgType: %s, impressionType:%s", s, s2));
            }
            this.launchWebTask(new UserAgent$RecordUmsImpression(this, s, s2));
            return;
        }
        Log.d("nf_service_useragent", "msgName or impressionType is null - skip reporting");
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
    
    public void refreshUserMessage() {
        final User mUser = this.mUser;
        if (mUser != null) {
            if (Log.isLoggable()) {
                Log.d("nf_service_useragent", "UMA refreshing from server...");
            }
            this.launchWebTask(new UserAgent$5(this, mUser));
        }
    }
    
    public void removeWebUserProfile(final String s, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        Log.d("nf_service_useragent", "removeWebUserProfile");
        this.launchWebTask(new UserAgent$RemoveWebUserProfilesTask(this, s, userAgent$UserAgentCallback));
    }
    
    public void resetThumbMessagingForDebug() {
        this.mUser.setThumbMessaging(ThumbMessaging.builder().setShouldShowOneTimeProfileThumbsMessage(true).setShouldShowFirstThumbsRatingMessage(true).build());
    }
    
    public void selectProfile(final String s) {
        this.mUserAgentStateManager.selectNewProfile(s);
    }
    
    @Override
    public void selectProfileResult(final Status status) {
        UserAgentBroadcastIntents.signalProfileSelectionResult(this.getContext(), status.getStatusCode().getValue(), null);
    }
    
    public void setCurrentAppLocale(final String s) {
        if (this.userLocaleRepository != null) {
            this.userLocaleRepository.setApplicationLanguage(new UserLocale(s));
        }
    }
    
    public void setNonMemberPlayback(final boolean mIsNonMemberPlayback) {
        this.mIsNonMemberPlayback = mIsNonMemberPlayback;
    }
    
    public void setUserPreferredLanguages(final String[] array) {
        this.userLocaleRepository.setPreferredLanguages(StringUtils.joinArray(array));
        this.getNrdController().setDeviceLocale(this.userLocaleRepository.getCurrentAppLocale().getLocale());
    }
    
    @Override
    public boolean shouldFetchAccountDataAsync() {
        return this.isAccountDataAvailable() && PrefetchLolomoABTestUtils.isConfigRequestAsync(this.getContext());
    }
    
    @Override
    public void switchWebUserProfile(final String s) {
        Log.d("nf_service_useragent", "switchWebUserProfile");
        this.getService().getClientLogging().onProfileSwitch();
        this.launchWebTask(new UserAgent$SwitchWebUserProfilesTask(this, s));
    }
    
    public void tokenActivate(final ActivationTokens activationTokens, final UserAgent$UserAgentCallback mLoginCallback) {
        Log.d("nf_service_useragent", "loginUser tokenActivate");
        SignInLogging$SignInType signInLogging$SignInType;
        if (activationTokens.autoLoginSource) {
            signInLogging$SignInType = SignInLogging$SignInType.autologin;
        }
        else {
            signInLogging$SignInType = SignInLogging$SignInType.tokenActivate;
        }
        if (this.mUserAgentStateManager == null) {
            SignInLogUtils.reportSignInRequestSessionEnded(this.getContext(), signInLogging$SignInType, IClientLogging$CompletionReason.failed, (Error)null);
            this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_ERROR, "UserAgent: activateAccByToken fails UserAgentStateManager is null", true, RootCause.clientFailure));
            return;
        }
        if (!this.mUserAgentStateManager.activateAccByToken(activationTokens)) {
            this.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_REGISTRATION_EXISTS, "UserAgent: activateAccByToken fails, NRD registration exist", false, RootCause.clientFailure));
            this.notifyLoginComplete(new NetflixStatus(StatusCode.NRD_REGISTRATION_EXISTS));
            SignInLogUtils.reportSignInRequestSessionEnded(this.getContext(), signInLogging$SignInType, IClientLogging$CompletionReason.failed, (Error)null);
            return;
        }
        this.mLoginCallback = mLoginCallback;
    }
    
    @Override
    public boolean updateUserCredentials(final String mDynecomNetflixId, final String mDynecomSecureNetflixId) {
        if (!this.isUserLoggedIn()) {
            Log.d("nf_service_useragent", "got cookies from dynecom");
            this.mDynecomNetflixId = mDynecomNetflixId;
            this.mDynecomSecureNetflixId = mDynecomSecureNetflixId;
            Log.d("nf_service_useragent", "NetlixId: %s, secureNetflixId: %s", this.mDynecomNetflixId, this.mDynecomSecureNetflixId);
            return false;
        }
        this.mCurrentUserAccount = this.mUserAgentStateManager.updateAccountTokens(mDynecomNetflixId, mDynecomSecureNetflixId);
        return true;
    }
    
    @Override
    public void userAccountActivated(final DeviceAccount mCurrentUserAccount) {
        this.mCurrentUserAccount = mCurrentUserAccount;
        UserAgentBroadcastIntents.signalUserAccountActive(this.getContext());
        PersistentConfig.refresh();
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
    
    public void verifyPin(final String s, final PinVerifier$PinType pinVerifier$PinType, final String s2, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.launchTask(new UserAgent$VerifyPinTask(this, s, pinVerifier$PinType, s2, userAgent$UserAgentCallback));
    }
}
