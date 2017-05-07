// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.javabridge.ui.android.registration.DeactivateCompleteCommand;
import com.netflix.mediaclient.javabridge.ui.android.registration.SelectedAccountCompleteCommand;
import com.netflix.mediaclient.javabridge.ui.android.registration.CreateAccountCompleteCommand;
import com.netflix.mediaclient.event.CallbackEvent;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import org.json.JSONException;
import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import java.util.List;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.drm.DrmManager;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.Callback;

public class UserAgentStateManager implements Callback
{
    private static final String TAG = "nf_service_useragentstate";
    private DeviceAccount mCurrentDeviceAcc;
    private String mEmail;
    private ErrorLogging mErrorLogger;
    private boolean mInitilizationCompleted;
    private boolean mNeedLogout;
    private String mPassword;
    private final int mPrimaryAccountIndex;
    private String mProfileId;
    private final UserProfileMap mProfileMap;
    private final Registration mRegistration;
    private UserAgentStateManager$STATES mState;
    private ActivationTokens mToken;
    private final UserAgentStateManager$StateManagerCallback mUserAgent;
    
    UserAgentStateManager(final Registration mRegistration, final DrmManager drmManager, final UserAgentStateManager$StateManagerCallback mUserAgent, final Context context, final ErrorLogging mErrorLogger) {
        this.mState = UserAgentStateManager$STATES.INIT;
        this.mPrimaryAccountIndex = 0;
        this.mRegistration = mRegistration;
        this.mUserAgent = mUserAgent;
        this.mProfileMap = new UserProfileMap(context);
        this.mErrorLogger = mErrorLogger;
        this.mInitilizationCompleted = false;
    }
    
    private void cleanupAfterDeactivation() {
        this.mUserAgent.userAccountInactive();
        this.mRegistration.deactivateAll(this);
        this.mUserAgent.userAccountDeactivated();
        this.mProfileMap.clear();
        this.transitionTo(UserAgentStateManager$STATES.INIT);
    }
    
    private void deviceAccountCreated(final boolean b, final String s) {
        while (true) {
            Log.d("nf_service_useragentstate", "@event deviceAccountCreated");
            Label_0084: {
                synchronized (this.mState) {
                    if (!this.validateState(UserAgentStateManager$STATES.NEED_CREATE_DEVACC, "deviceAccountCreated")) {
                        return;
                    }
                    if (!b) {
                        break Label_0084;
                    }
                    this.mCurrentDeviceAcc = this.getAccountWithKey(this.mRegistration.getDeviceAccounts(), s);
                    if (this.mCurrentDeviceAcc != null) {
                        this.transitionTo(UserAgentStateManager$STATES.NEED_SELECT_DEVACC);
                        return;
                    }
                }
                this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
                return;
            }
            this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
        }
    }
    
    private void deviceAccountDeactivated(final boolean b) {
        Log.d("nf_service_useragentstate", "@event deviceAccountDeactivated");
        final UserAgentStateManager$STATES mState = this.mState;
        // monitorenter(mState)
        Label_0029: {
            if (!b) {
                break Label_0029;
            }
            try {
                this.transitionTo(UserAgentStateManager$STATES.NEED_DEACTIVATE_ACC);
                return;
                this.cleanupAfterDeactivation();
            }
            finally {
            }
            // monitorexit(mState)
        }
    }
    
    private void deviceAccountSelected(final boolean b) {
        while (true) {
            Log.d("nf_service_useragentstate", "@event deviceAccountSelected");
            synchronized (this.mState) {
                if (!this.validateState(UserAgentStateManager$STATES.NEED_SELECT_DEVACC, "deviceAccountSelected")) {
                    return;
                }
                if (b) {
                    this.hasDeviceAccount();
                    return;
                }
            }
            this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
        }
    }
    
    private boolean fallbackToPrimaryAccount() {
        Log.d("nf_service_useragentstate", String.format("fallbackToPrimaryAccount state:%s", this.mState));
        final String primaryAccountKey = this.mProfileMap.getPrimaryAccountKey();
        if (!StringUtils.isEmpty(primaryAccountKey)) {
            final DeviceAccount[] deviceAccounts = this.mRegistration.getDeviceAccounts();
            if (deviceAccounts.length != 0) {
                final DeviceAccount accountWithKey = this.getAccountWithKey(deviceAccounts, primaryAccountKey);
                if (accountWithKey != null) {
                    this.mCurrentDeviceAcc = accountWithKey;
                    this.mProfileId = null;
                    this.transitionTo(UserAgentStateManager$STATES.NEED_SELECT_DEVACC);
                    return true;
                }
            }
        }
        return false;
    }
    
    private DeviceAccount getAccountWithKey(final DeviceAccount[] array, final String s) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final DeviceAccount deviceAccount = array[i];
            if (deviceAccount.getAccountKey().equals(s)) {
                return deviceAccount;
            }
        }
        return null;
    }
    
    private DeviceAccount getNextAccountToDeactivate() {
        final DeviceAccount[] deviceAccounts = this.mRegistration.getDeviceAccounts();
        if (deviceAccounts.length == 0) {
            return null;
        }
        return deviceAccounts[deviceAccounts.length - 1];
    }
    
    private void hasDeviceAccount() {
        if (!this.mRegistration.isRegistered()) {
            this.mNeedLogout = false;
            this.mProfileMap.clearEsnMigrationForCurrentAccount();
            if (!this.isProfileIdValid()) {
                this.transitionTo(UserAgentStateManager$STATES.WAIT_ACTIVATE_ACC);
            }
            else {
                if (this.mToken != null) {
                    this.transitionTo(UserAgentStateManager$STATES.NEED_ACTIVATE_PROFILE);
                    return;
                }
                if (!this.fallbackToPrimaryAccount()) {
                    this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
                }
            }
            return;
        }
        if (this.mProfileMap.isCurrentAccountNeedEsnMigration()) {
            this.mProfileMap.clearEsnMigrationForCurrentAccount();
            if (!this.isProfileIdValid()) {
                this.transitionTo(UserAgentStateManager$STATES.NEED_ESN_MIGRATION);
                return;
            }
            if (this.mToken != null) {
                this.transitionTo(UserAgentStateManager$STATES.NEED_ACTIVATE_PROFILE);
                return;
            }
            this.transitionTo(UserAgentStateManager$STATES.NEED_ESN_MIGRATION);
        }
        else {
            if (this.mNeedLogout) {
                this.transitionTo(UserAgentStateManager$STATES.NEED_DEACTIVATE_ACC);
                this.mNeedLogout = false;
                return;
            }
            if (!this.isProfileIdValid()) {
                this.transitionTo(UserAgentStateManager$STATES.NEED_FETCH_PROFILE_DATA);
                return;
            }
            this.transitionTo(UserAgentStateManager$STATES.NEED_VALIDATE_PROFILE_DATA);
        }
    }
    
    private void init() {
        while (true) {
            Log.d("nf_service_useragentstate", "@init");
            final DeviceAccount[] deviceAccounts;
            synchronized (this.mState) {
                this.mProfileId = null;
                this.mCurrentDeviceAcc = null;
                final DeviceAccount currentDeviceAccount = this.mRegistration.getCurrentDeviceAccount();
                if (currentDeviceAccount != null) {
                    Log.d("nf_service_useragentstate", "@init has currentDeviceAccount");
                    this.mCurrentDeviceAcc = currentDeviceAccount;
                    this.hasDeviceAccount();
                    return;
                }
                this.mProfileId = (String)this.mProfileMap.getCurrentProfileIdAndKey().first;
                final String s = (String)this.mProfileMap.getCurrentProfileIdAndKey().second;
                if (Log.isLoggable("nf_service_useragentstate", 3)) {
                    Log.d("nf_service_useragentstate", "@init get from map [" + this.mProfileId + "] with key[" + s + "]");
                }
                deviceAccounts = this.mRegistration.getDeviceAccounts();
                if (deviceAccounts.length == 0) {
                    if (StringUtils.isNotEmpty(s)) {
                        Log.w("nf_service_useragentstate", "@init no device account but has current account key " + s);
                    }
                    this.transitionTo(UserAgentStateManager$STATES.NEED_CREATE_DEVACC);
                    return;
                }
            }
            final String s2;
            if (StringUtils.isNotEmpty(s2)) {
                this.mCurrentDeviceAcc = this.getAccountWithKey(deviceAccounts, s2);
            }
            if (this.mCurrentDeviceAcc == null) {
                this.mCurrentDeviceAcc = deviceAccounts[0];
            }
            this.transitionTo(UserAgentStateManager$STATES.NEED_SELECT_DEVACC);
        }
    }
    
    private boolean isCurrentProfileValid(final List<UserProfile> list) {
        if (StringUtils.isEmpty(this.mProfileId)) {
            Log.w("nf_service_useragentstate", "isCurrentProfileValid but mProfileId is null");
            return true;
        }
        final Iterator<UserProfile> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (this.mProfileId.equals(iterator.next().getProfileGuid())) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isProfileIdValid() {
        return StringUtils.isNotEmpty(this.mProfileId);
    }
    
    private void setNetflixIdToNrdpAccount(final String s, final String s2) {
        Label_0045: {
            if (!StringUtils.isNotEmpty(s) || !StringUtils.isNotEmpty(s2)) {
                Log.w("nf_service_useragentstate", "setNetflixIdToNrdpAccount has null NetflixId, will fallback thru primary account");
                break Label_0045;
            }
            try {
                this.mRegistration.setActivationTokens(new ActivationTokens(s, s2));
                return;
            }
            catch (JSONException ex) {
                Log.w("nf_service_useragentstate", "setNetflixIdToNrdpAccount failed will fallback thru primary account");
            }
        }
        if (!this.fallbackToPrimaryAccount()) {
            this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
        }
    }
    
    private void signalInitilizationCompleted() {
        if (!this.mInitilizationCompleted) {
            this.mUserAgent.initialized(CommonStatus.OK);
            this.mInitilizationCompleted = true;
        }
    }
    
    private void transitionTo(final UserAgentStateManager$STATES mState) {
        final UserAgentStateManager$STATES mState2 = this.mState;
        this.mState = mState;
        switch (UserAgentStateManager$1.$SwitchMap$com$netflix$mediaclient$service$user$UserAgentStateManager$STATES[mState.ordinal()]) {
            default: {
                Log.d("nf_service_useragentstate", "@state default");
                break;
            }
            case 1: {
                Log.d("nf_service_useragentstate", "@state INIT");
                this.init();
            }
            case 2: {
                Log.d("nf_service_useragentstate", "@state NEED_ACTIVATE_PROFILE");
                if (this.mRegistration != null) {
                    this.mRegistration.tokenActivate(this.mToken);
                }
                this.mToken = null;
            }
            case 3: {
                Log.d("nf_service_useragentstate", "@state NEED_CHANGE_PROFILE_FROM_PRIMARY");
                this.mUserAgent.switchWebUserProfile(this.mProfileId);
            }
            case 4: {
                Log.d("nf_service_useragentstate", "@state NEED_CHANGE_PROFILE");
                if (this.mCurrentDeviceAcc != null) {
                    this.mRegistration.deactivate(this.mCurrentDeviceAcc, null);
                }
                this.mUserAgent.profileInactive();
                this.mUserAgent.switchWebUserProfile(this.mProfileId);
            }
            case 5: {
                Log.d("nf_service_useragentstate", "@state NEED_CREATE_DEVACC");
                this.mUserAgent.profileInactive();
                this.mUserAgent.userAccountInactive();
                this.mRegistration.createDeviceAccount(this);
            }
            case 6: {
                Log.d("nf_service_useragentstate", "@state NEED_DEACTIVATE_ACC");
                final DeviceAccount nextAccountToDeactivate = this.getNextAccountToDeactivate();
                if (nextAccountToDeactivate != null) {
                    this.mRegistration.deactivate(nextAccountToDeactivate, this);
                    return;
                }
                this.cleanupAfterDeactivation();
            }
            case 7: {
                Log.d("nf_service_useragentstate", "@state NEED_EMAIL_ACTIVATE");
                if (this.mRegistration != null) {
                    this.mRegistration.emailActivate(this.mEmail, this.mPassword);
                    return;
                }
                break;
            }
            case 8: {
                Log.d("nf_service_useragentstate", "@state NEED_ESN_MIGRATION");
                if (this.mRegistration != null) {
                    this.mRegistration.esnMigration();
                    return;
                }
                break;
            }
            case 9: {
                Log.d("nf_service_useragentstate", "@state NEED_FETCH_PROFILE_DATA");
                this.mUserAgent.userAccountActivated(this.mCurrentDeviceAcc);
                this.mUserAgent.fetchAccountData();
            }
            case 10: {
                Log.d("nf_service_useragentstate", "@state NEED_SELECT_DEVACC");
                this.mProfileMap.setCurrentAccount(this.mProfileId, this.mCurrentDeviceAcc.getAccountKey());
                this.mRegistration.selectDeviceAccount(this.mCurrentDeviceAcc, this);
            }
            case 11: {
                Log.d("nf_service_useragentstate", "@state NEED_TOKEN_ACTIVATE");
                if (this.mRegistration != null) {
                    this.mRegistration.tokenActivate(this.mToken);
                }
                this.mToken = null;
            }
            case 12: {
                Log.d("nf_service_useragentstate", "@state NEED_VALIDATE_PROFILE_DATA");
                this.mUserAgent.userAccountActivated(this.mCurrentDeviceAcc);
                this.mUserAgent.fetchAccountData();
            }
            case 13: {
                Log.d("nf_service_useragentstate", "@state PROFILE_ACTIVATED");
                this.mUserAgent.profileActivated(this.mProfileId, this.mCurrentDeviceAcc);
                this.signalInitilizationCompleted();
            }
            case 14: {
                Log.d("nf_service_useragentstate", "@state WAIT_ACTIVATE_ACC");
                this.signalInitilizationCompleted();
            }
            case 15: {
                Log.d("nf_service_useragentstate", "@state WAIT_SELECT_PROFILE");
                this.signalInitilizationCompleted();
                this.mUserAgent.readyToSelectProfile();
            }
            case 16: {
                Log.d("nf_service_useragentstate", "@state FATAL_ERROR");
                this.mErrorLogger.logHandledException("FATAL_ERROR in user agent state - prev state: " + mState2);
                this.transitionTo(UserAgentStateManager$STATES.NEED_DEACTIVATE_ACC);
            }
        }
    }
    
    private boolean validateState(final UserAgentStateManager$STATES userAgentStateManager$STATES, final String s) {
        if (this.mState == userAgentStateManager$STATES) {
            if (Log.isLoggable("nf_service_useragentstate", 3)) {
                Log.d("nf_service_useragentstate", s + " in expected state");
            }
            return true;
        }
        return false;
    }
    
    void accountDataFetchFailed(final Status status, final boolean b) {
        Log.d("nf_service_useragentstate", "@event profileDataFetchFailed");
        if (Log.isLoggable("nf_service_useragentstate", 3)) {
            Log.d("nf_service_useragentstate", String.format("res:%s, isAccountDataAvailable:%b", status.getStatusCode(), b));
        }
        while (true) {
            synchronized (this.mState) {
                if (this.validateState(UserAgentStateManager$STATES.NEED_FETCH_PROFILE_DATA, "accountDataFetchFailed") && !b) {
                    this.mUserAgent.userAccountDataResult(status);
                }
                else {
                    this.mUserAgent.userAccountDataResult(CommonStatus.OK);
                }
                if (status.getStatusCode() == StatusCode.USER_NOT_AUTHORIZED) {
                    if (!this.isProfileIdValid() || !this.fallbackToPrimaryAccount()) {
                        this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
                    }
                    return;
                }
            }
            if (this.validateState(UserAgentStateManager$STATES.NEED_FETCH_PROFILE_DATA, "accountDataFetchFailed")) {
                if (b) {
                    this.transitionTo(UserAgentStateManager$STATES.WAIT_SELECT_PROFILE);
                    return;
                }
                this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
            }
            else if (this.validateState(UserAgentStateManager$STATES.NEED_VALIDATE_PROFILE_DATA, "accountDataFetchFailed")) {
                this.transitionTo(UserAgentStateManager$STATES.PROFILE_ACTIVATED);
            }
        }
    }
    
    void accountDataFetched(final AccountData accountData) {
        while (true) {
            Log.d("nf_service_useragentstate", "@event accountDataFetched");
            Label_0090: {
                synchronized (this.mState) {
                    this.mUserAgent.userAccountDataResult(CommonStatus.OK);
                    if (this.validateState(UserAgentStateManager$STATES.NEED_FETCH_PROFILE_DATA, "accountDataFetched")) {
                        this.transitionTo(UserAgentStateManager$STATES.WAIT_SELECT_PROFILE);
                    }
                    else if (this.validateState(UserAgentStateManager$STATES.NEED_VALIDATE_PROFILE_DATA, "accountDataFetched")) {
                        if (!this.isCurrentProfileValid(accountData.getUserProfiles())) {
                            break Label_0090;
                        }
                        this.transitionTo(UserAgentStateManager$STATES.PROFILE_ACTIVATED);
                    }
                    return;
                }
            }
            if (!this.fallbackToPrimaryAccount()) {
                this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
            }
        }
    }
    
    void accountOrProfileActivated(final boolean b, final String netflixId, final String secureId) {
        while (true) {
            Log.d("nf_service_useragentstate", "@event accountOrProfileActivated");
            Label_0243: {
            Label_0180:
                while (true) {
                    synchronized (this.mState) {
                        if (!this.validateState(UserAgentStateManager$STATES.NEED_TOKEN_ACTIVATE, "accountOrProfileActivated") && !this.validateState(UserAgentStateManager$STATES.NEED_EMAIL_ACTIVATE, "accountOrProfileActivated") && !this.validateState(UserAgentStateManager$STATES.NEED_ACTIVATE_PROFILE, "accountOrProfileActivated") && !this.validateState(UserAgentStateManager$STATES.NEED_ESN_MIGRATION, "accountOrProfileActivated")) {
                            Log.d("nf_service_useragentstate", "@event accountOrProfileActivated not expected, ignored");
                            return;
                        }
                        if (StringUtils.isNotEmpty(netflixId) && StringUtils.isNotEmpty(secureId)) {
                            final int n = 1;
                            if (!b || n == 0) {
                                break Label_0243;
                            }
                            this.mCurrentDeviceAcc.setNetflixId(netflixId);
                            this.mCurrentDeviceAcc.setSecureId(secureId);
                            this.setNetflixIdToNrdpAccount(netflixId, secureId);
                            if (this.validateState(UserAgentStateManager$STATES.NEED_TOKEN_ACTIVATE, "accountOrProfileActivated") || this.validateState(UserAgentStateManager$STATES.NEED_EMAIL_ACTIVATE, "accountOrProfileActivated")) {
                                this.transitionTo(UserAgentStateManager$STATES.NEED_FETCH_PROFILE_DATA);
                                return;
                            }
                            break Label_0180;
                        }
                    }
                    final int n = 0;
                    continue;
                }
                if (this.validateState(UserAgentStateManager$STATES.NEED_ACTIVATE_PROFILE, "accountOrProfileActivated")) {
                    this.transitionTo(UserAgentStateManager$STATES.PROFILE_ACTIVATED);
                    return;
                }
                if (!this.validateState(UserAgentStateManager$STATES.NEED_ESN_MIGRATION, "accountOrProfileActivated")) {
                    return;
                }
                if (this.isProfileIdValid()) {
                    this.transitionTo(UserAgentStateManager$STATES.PROFILE_ACTIVATED);
                    return;
                }
                this.transitionTo(UserAgentStateManager$STATES.NEED_FETCH_PROFILE_DATA);
                return;
            }
            if (!this.isProfileIdValid()) {
                this.transitionTo(UserAgentStateManager$STATES.WAIT_ACTIVATE_ACC);
                return;
            }
            this.mUserAgent.selectProfileResult(new NetflixStatus(StatusCode.NRD_ERROR));
            if (!this.fallbackToPrimaryAccount()) {
                this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
            }
        }
    }
    
    public boolean activateAccByEmailPassword(final String mEmail, final String mPassword) {
        Log.d("nf_service_useragentstate", "@event activateAccByEmailPassword");
        synchronized (this.mState) {
            if (!this.validateState(UserAgentStateManager$STATES.WAIT_ACTIVATE_ACC, "activateAccByEmailPassword")) {
                return false;
            }
            this.mEmail = mEmail;
            this.mPassword = mPassword;
            this.transitionTo(UserAgentStateManager$STATES.NEED_EMAIL_ACTIVATE);
            return true;
        }
    }
    
    public boolean activateAccByToken(final ActivationTokens mToken) {
        Log.d("nf_service_useragentstate", "@event activateAccByToken");
        synchronized (this.mState) {
            if (!this.validateState(UserAgentStateManager$STATES.WAIT_ACTIVATE_ACC, "activateAccByToken")) {
                return false;
            }
            this.mToken = mToken;
            this.transitionTo(UserAgentStateManager$STATES.NEED_TOKEN_ACTIVATE);
            return true;
        }
    }
    
    @Override
    public void done(final CallbackEvent callbackEvent) {
        Log.d("nf_service_useragentstate", "account related callback " + callbackEvent.toString());
        Label_0111: {
            if (!(callbackEvent instanceof CreateAccountCompleteCommand)) {
                break Label_0111;
            }
            Log.d("nf_service_useragentstate", "Received CreateAccountComplete callback");
            try {
                final CreateAccountCompleteCommand createAccountCompleteCommand = new CreateAccountCompleteCommand(callbackEvent.getData());
                this.deviceAccountCreated(createAccountCompleteCommand.isCreatedSuccess(), String.valueOf(createAccountCompleteCommand.getKey()));
                return;
            }
            catch (JSONException ex) {
                Log.e("nf_service_useragentstate", "CreateAccount error " + ex);
                this.deviceAccountCreated(false, "");
                return;
            }
        }
        if (callbackEvent instanceof SelectedAccountCompleteCommand) {
            Log.d("nf_service_useragentstate", "Received SelectedAccount callback");
            try {
                this.deviceAccountSelected(new SelectedAccountCompleteCommand(callbackEvent.getData()).isSelectedSuccess());
                return;
            }
            catch (JSONException ex2) {
                Log.e("nf_service_useragentstate", "SelectedAccount error " + ex2);
                this.deviceAccountSelected(false);
                return;
            }
        }
        if (callbackEvent instanceof DeactivateCompleteCommand) {
            Log.d("nf_service_useragentstate", "Received deactivate complete cmd");
            this.deviceAccountDeactivated(true);
        }
    }
    
    public void initialize(final boolean mNeedLogout, final boolean b) {
        this.mNeedLogout = mNeedLogout;
        if (b) {
            this.mProfileMap.markAllAccountForEsnMigration();
        }
        this.init();
    }
    
    void onAccountErrors(final Context context, final StatusCode statusCode) {
        Log.d("nf_service_useragentstate", "onAccountErrors statusCode: " + statusCode);
        if (StatusCode.DELETED_PROFILE.equals(statusCode)) {
            if (this.fallbackToPrimaryAccount()) {
                UserAgentBroadcastIntents.signalProfileInvalid(context);
                return;
            }
            this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
        }
    }
    
    void profileSwitched(UserBoundCookies acccountKeyFromProfileId) {
        while (true) {
            Log.d("nf_service_useragentstate", "@event profileSwitched");
            synchronized (this.mState) {
                try {
                    this.mToken = new ActivationTokens(acccountKeyFromProfileId.getUserBoundNetflixId(), acccountKeyFromProfileId.getUserBoundSecureNetflixId());
                    this.mUserAgent.selectProfileResult(CommonStatus.OK);
                    acccountKeyFromProfileId = (UserBoundCookies)this.mProfileMap.getAcccountKeyFromProfileId(this.mProfileId);
                    if (StringUtils.isNotEmpty((String)acccountKeyFromProfileId)) {
                        this.mCurrentDeviceAcc = this.getAccountWithKey(this.mRegistration.getDeviceAccounts(), (String)acccountKeyFromProfileId);
                    }
                    if (this.mCurrentDeviceAcc != null) {
                        this.transitionTo(UserAgentStateManager$STATES.NEED_SELECT_DEVACC);
                        return;
                    }
                }
                catch (JSONException ex) {
                    Log.e("nf_service_useragentstate", "profileSwitched failed with userBoundCookies " + acccountKeyFromProfileId);
                    this.mUserAgent.selectProfileResult(CommonStatus.INTERNAL_ERROR);
                    if (!this.fallbackToPrimaryAccount()) {
                        this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
                    }
                    return;
                }
            }
            this.transitionTo(UserAgentStateManager$STATES.NEED_CREATE_DEVACC);
        }
    }
    
    void profileSwitchedFailed(final Status status) {
        while (true) {
            Label_0118: {
                synchronized (this.mState) {
                    final StatusCode statusCode = status.getStatusCode();
                    if (Log.isLoggable("nf_service_useragentstate", 2)) {
                        Log.v("nf_service_useragentstate", "profileSwitchFailed, status: " + statusCode + ", state: " + this.mState);
                    }
                    this.mUserAgent.selectProfileResult(status);
                    if (statusCode == StatusCode.USER_NOT_AUTHORIZED) {
                        if (!this.fallbackToPrimaryAccount()) {
                            this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
                        }
                    }
                    else {
                        if (statusCode != StatusCode.NETWORK_ERROR) {
                            break Label_0118;
                        }
                        this.transitionTo(UserAgentStateManager$STATES.WAIT_SELECT_PROFILE);
                    }
                    return;
                }
            }
            if (this.validateState(UserAgentStateManager$STATES.NEED_CHANGE_PROFILE_FROM_PRIMARY, "profileSwitchedFailed")) {
                if (!this.fallbackToPrimaryAccount()) {
                    this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
                }
            }
            else if (!this.fallbackToPrimaryAccount()) {
                this.transitionTo(UserAgentStateManager$STATES.FATAL_ERROR);
            }
        }
    }
    
    public void selectNewProfile(final String mProfileId) {
        while (true) {
            Log.d("nf_service_useragentstate", "@event selectNewProfile");
            synchronized (this.mState) {
                if (!this.validateState(UserAgentStateManager$STATES.WAIT_SELECT_PROFILE, "selectNewProfile") && !this.validateState(UserAgentStateManager$STATES.PROFILE_ACTIVATED, "selectNewProfile")) {
                    return;
                }
                if (this.isProfileIdValid()) {
                    this.mCurrentDeviceAcc = null;
                    this.mProfileId = mProfileId;
                    this.transitionTo(UserAgentStateManager$STATES.NEED_CHANGE_PROFILE);
                    return;
                }
            }
            this.mCurrentDeviceAcc = null;
            final String mProfileId2;
            this.mProfileId = mProfileId2;
            this.transitionTo(UserAgentStateManager$STATES.NEED_CHANGE_PROFILE_FROM_PRIMARY);
        }
    }
    
    public void signoutAcc() {
        Log.d("nf_service_useragentstate", "@event signoutAcc");
        synchronized (this.mState) {
            this.transitionTo(UserAgentStateManager$STATES.NEED_DEACTIVATE_ACC);
        }
    }
    
    DeviceAccount updateAccountTokens(final String netflixId, final String secureId) {
        synchronized (this.mState) {
            if (this.mCurrentDeviceAcc != null) {
                this.mCurrentDeviceAcc.setNetflixId(netflixId);
                this.mCurrentDeviceAcc.setSecureId(secureId);
                this.setNetflixIdToNrdpAccount(netflixId, secureId);
            }
            // monitorexit(this.mState)
            return this.mCurrentDeviceAcc;
        }
    }
}
