// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import android.net.Uri;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.google.android.gcm.GCMRegistrar;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.service.logging.UserData;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;
import java.util.Map;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.service.ServiceAgent;

public class PushNotificationAgent extends ServiceAgent implements IPushNotification
{
    private static final boolean GCM_INFO_OPT_IN = true;
    private static final long SERVICE_KILL_DELAY_FOR_GCM_REPORTING_MS = 30000L;
    private static final String TAG = "nf_push";
    private static int idCounter;
    private String gcmRegistrationId;
    private NotificationUserSettings mCurrentUserSettings;
    private boolean mGcmInfoEventStartedService;
    private boolean mGcmRegistered;
    private boolean mGcmSupported;
    private ImageLoader mImageLoader;
    private Map<String, NotificationUserSettings> mSettings;
    private final BroadcastReceiver pushNotificationReceiver;
    private boolean reportOnRegistered;
    
    static {
        PushNotificationAgent.idCounter = -1;
    }
    
    public PushNotificationAgent() {
        this.pushNotificationReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("nf_push", 2)) {
                    Log.v("nf_push", "Received intent " + intent);
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN".equals(action)) {
                    Log.d("nf_push", "onLogin");
                    PushNotificationAgent.this.onLogin();
                }
                else {
                    if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT".equals(action)) {
                        Log.d("nf_push", "onLogout");
                        PushNotificationAgent.this.onLogout(PushNotificationAgent.this.createUserData(intent));
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN".equals(action)) {
                        Log.d("nf_push", "optIn");
                        PushNotificationAgent.this.onNotificationOptIn(true);
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT".equals(action)) {
                        Log.d("nf_push", "optOut");
                        PushNotificationAgent.this.onNotificationOptIn(false);
                        return;
                    }
                    if (Log.isLoggable("nf_push", 3)) {
                        Log.d("nf_push", "We do not support action " + action);
                    }
                }
            }
        };
        Log.d("nf_push", "PushNotificationAgent::");
    }
    
    private NotificationUserSettings createNewCurrentUserSettings(final String userId, final String currentProfileUserId) {
        final NotificationUserSettings notificationUserSettings = new NotificationUserSettings();
        notificationUserSettings.current = true;
        notificationUserSettings.userId = userId;
        notificationUserSettings.currentProfileUserId = currentProfileUserId;
        notificationUserSettings.oldAppVersion = AndroidManifestUtils.getVersionCode(this.getContext());
        this.mSettings.put(userId, notificationUserSettings);
        return notificationUserSettings;
    }
    
    private UserData createUserData(final Intent intent) {
        final UserData userData = new UserData();
        userData.esn = intent.getStringExtra("esn");
        userData.deviceCategory = intent.getStringExtra("device_cat");
        userData.netflixId = intent.getStringExtra("nid");
        userData.secureNetflixId = intent.getStringExtra("sid");
        userData.userId = intent.getStringExtra("uid");
        userData.currentProfileToken = intent.getStringExtra("cp_uid");
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "CreateUserData: " + userData);
        }
        return userData;
    }
    
    private void doGcmRegistration() {
        if (!this.isGcmSupported()) {
            Log.e("nf_push", "device does NOT support GCM!");
        }
        else if (!this.mGcmRegistered) {
            Log.d("nf_push", "device supports GCM and device is NOT registered!");
            GCMRegistrar.register(this.getContext(), "484286080282");
        }
    }
    
    private int getMessageId(final Context context) {
        synchronized (this) {
            if (PushNotificationAgent.idCounter == -1) {
                PushNotificationAgent.idCounter = PreferenceUtils.getIntPref(context, "nf_notification_id_counter", 1);
            }
            final int idCounter = PushNotificationAgent.idCounter;
            ++PushNotificationAgent.idCounter;
            new BackgroundTask().execute(new Runnable() {
                @Override
                public void run() {
                    PreferenceUtils.putIntPref(context, "nf_notification_id_counter", PushNotificationAgent.idCounter);
                }
            });
            return idCounter;
        }
    }
    
    private UserData getUserData() {
        final UserData userData = new UserData();
        userData.esn = this.getConfigurationAgent().getEsnProvider().getEsn();
        userData.deviceCategory = this.getConfigurationAgent().getDeviceCategory().getValue();
        userData.netflixId = this.getUserAgent().getUserCredentialRegistry().getNetflixID();
        userData.secureNetflixId = this.getUserAgent().getUserCredentialRegistry().getSecureNetflixID();
        userData.userId = this.getService().getUserId();
        userData.currentProfileToken = this.getService().getCurrentProfileUserId();
        userData.accountCountry = this.getUserAgent().getReqCountry();
        userData.accountCountry = this.getUserAgent().getGeoCountry();
        userData.languages = this.getUserAgent().getLanguagesInCsv();
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "GetUserData: " + userData);
        }
        return userData;
    }
    
    private void loadConfiguration() {
        this.mSettings = NotificationUserSettings.loadSettings(this.getContext());
    }
    
    private void onGcmRegistration(final String gcmRegistrationId) {
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "onGcmRegistration " + gcmRegistrationId);
        }
        this.gcmRegistrationId = gcmRegistrationId;
        this.mGcmRegistered = true;
        if (this.reportOnRegistered && this.mCurrentUserSettings != null) {
            this.report(this.mCurrentUserSettings.optedIn, true);
        }
    }
    
    private void onGcmUnregistration(final String s) {
        if (s != null && s.equals(this.gcmRegistrationId)) {
            Log.d("nf_push", "onGcmUnregistration - Same registrration ID");
        }
        else {
            Log.e("nf_push", "onGcmUnregistration - Received registration ID: " + s + " is NOT the same as registration ID known to app: " + this.gcmRegistrationId);
        }
        this.mGcmRegistered = false;
    }
    
    private void onLogin() {
        if (!this.isGcmSupported()) {
            Log.e("nf_push", "We can not do anything because device does not support push notifications!");
            return;
        }
        final String userId = this.getService().getUserId();
        final String currentProfileUserId = this.getService().getCurrentProfileUserId();
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "onLogin with user ID: " + userId);
        }
        this.mCurrentUserSettings = this.mSettings.get(userId);
        while (true) {
            Label_0161: {
                if (this.mCurrentUserSettings != null) {
                    break Label_0161;
                }
                Log.d("nf_push", "User was not know from before");
                this.mCurrentUserSettings = this.createNewCurrentUserSettings(userId, currentProfileUserId);
                Label_0241: {
                    try {
                        Log.d("nf_push", String.format("report sGcmInfoEventStartedService: %s", this.mGcmInfoEventStartedService));
                        if (this.mGcmInfoEventStartedService) {
                            this.reportAndKillService();
                            return;
                        }
                        break Label_0241;
                    }
                    catch (Throwable t) {
                        Log.e("nf_push", "Check if we are registered already failed!", t);
                        return;
                    }
                    break Label_0161;
                }
                if (this.wasNotificationOptInDisplayed()) {
                    SettingsConfiguration.setPushOptInStatus(this.getContext(), this.mCurrentUserSettings.optedIn);
                    this.report(this.mCurrentUserSettings.optedIn, true);
                    return;
                }
                Log.d("nf_push", String.format("onLogin: dont report yet, wasNotificationOptInDisplayed: %b", this.wasNotificationOptInDisplayed()));
                return;
            }
            this.mCurrentUserSettings.current = true;
            if (!StringUtils.safeEquals(this.mCurrentUserSettings.currentProfileUserId, currentProfileUserId)) {
                Log.d("nf_push", "currentProfile change detected");
                this.updateCurrentUserSettings(currentProfileUserId);
            }
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "User was know from before and he opted in " + this.mCurrentUserSettings.optedIn);
            }
            continue;
        }
    }
    
    private void onLogout(final UserData userData) {
        synchronized (this) {
            Log.d("nf_push", "User is logging out");
            if (!this.isGcmSupported()) {
                Log.e("nf_push", "We can not do anything because device does not support push notifications!");
            }
            else {
                this.report(false, false, userData);
                if (userData != null) {
                    this.mCurrentUserSettings = this.mSettings.get(userData.userId);
                    if (this.mCurrentUserSettings == null) {
                        Log.e("nf_push", "User is logging out and it was uknown before?");
                        this.mCurrentUserSettings = new NotificationUserSettings();
                        this.mCurrentUserSettings.current = true;
                        this.mCurrentUserSettings.userId = userData.userId;
                        this.mCurrentUserSettings.optedIn = true;
                        this.mCurrentUserSettings.currentProfileUserId = userData.currentProfileToken;
                        this.mCurrentUserSettings.oldAppVersion = AndroidManifestUtils.getVersionCode(this.getContext());
                    }
                }
                this.saveSettings();
                this.mCurrentUserSettings = null;
            }
        }
    }
    
    private void onMessage(final Intent intent) {
        Log.d("nf_push", "Message received, create notification. Running it on main thread.");
        NotificationFactory.createNotification(this.getService(), intent, this.mImageLoader, this.getMessageId(this.getContext()), this.getService().getClientLogging().getErrorLogging());
    }
    
    private void onNotificationBrowserRedirect(final Intent intent) {
        final String stringExtra = intent.getStringExtra("guid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received browser redirect notification WITHOUT GUID! Do nothing!");
            return;
        }
        final String stringExtra2 = intent.getStringExtra("messageGuid");
        if (StringUtils.isEmpty(stringExtra2)) {
            Log.e("nf_push", "Received browser redirect notification WITHOUT MESSAGE GUID! Do nothing!");
            return;
        }
        final String stringExtra3 = intent.getStringExtra("originator");
        if (StringUtils.isEmpty(stringExtra3)) {
            Log.w("nf_push", "Received browser redirect notification WITHOUT ORIGINATOR! Pass default!");
        }
        this.getService().getClientLogging().getCmpEventLogging().reportUserFeedbackOnReceivedPushNotification(new MessageData(stringExtra, stringExtra2, stringExtra3), UserFeedbackOnReceivedPushNotification.opened);
        AndroidUtils.logIntent("nf_push", intent);
        final String stringExtra4 = intent.getStringExtra("target_url");
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "URI to be redirected to " + stringExtra4);
        }
        if (stringExtra4 != null) {
            final Intent intent2 = new Intent("android.intent.action.VIEW");
            intent2.setData(Uri.parse(stringExtra4));
            intent2.setFlags(872415232);
            this.getService().startActivity(intent2);
            return;
        }
        Log.e("nf_push", "URI is missing! Can not open to browser!");
    }
    
    private void onNotificationCanceled(final Intent intent) {
        final String stringExtra = intent.getStringExtra("guid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received cancel notification WITHOUT GUID! Do nothing!");
            return;
        }
        final String stringExtra2 = intent.getStringExtra("messageGuid");
        if (StringUtils.isEmpty(stringExtra2)) {
            Log.e("nf_push", "Received cancel notification WITHOUT MESSAGE GUID! Do nothing!");
            return;
        }
        final String stringExtra3 = intent.getStringExtra("originator");
        if (StringUtils.isEmpty(stringExtra3)) {
            Log.w("nf_push", "Received cancel notification WITHOUT ORIGINATOR! Pass default!");
        }
        this.getService().getClientLogging().getCmpEventLogging().reportUserFeedbackOnReceivedPushNotification(new MessageData(stringExtra, stringExtra2, stringExtra3), UserFeedbackOnReceivedPushNotification.canceled);
    }
    
    private void onNotificationOptIn(final boolean b) {
        Log.d("nf_push", String.format("onNotificationOptIn - user optIn ? %b", b));
        this.validateCurrentUser();
        this.updateSettingsOnOptedIn(b);
        SettingsConfiguration.setPushOptInStatus(this.getContext(), b);
        this.report(b, true);
    }
    
    private void registerReceiver() {
        Log.d("nf_push", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance(this.getContext()).registerReceiver(this.pushNotificationReceiver, intentFilter);
        }
        catch (Throwable t) {
            Log.e("nf_push", "Failed to register ", t);
        }
    }
    
    private void report(final boolean b, final boolean b2) {
        this.report(b, b2, null);
    }
    
    private void report(final boolean b, final boolean b2, final UserData userData) {
        if (!this.isGcmSupported()) {
            Log.e("nf_push", "We can not report anything if device does not support push notifications!");
        }
        else {
            if (userData == null) {
                Log.d("nf_push", "Gets user data");
                this.getUserData();
            }
            else {
                Log.d("nf_push", "Use given user data");
            }
            if (!StringUtils.isEmpty(this.gcmRegistrationId)) {
                this.getService().getClientLogging().getCustomerEventLogging().reportNotificationOptIn(b, b2, this.gcmRegistrationId);
                return;
            }
            this.reportOnRegistered = true;
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", String.format("can't report yet.. wait for registration to finish.. optIn:%b,  gcmInfoOptIn:%b", b, b2));
            }
        }
    }
    
    private void saveSettings() {
        final Map<String, NotificationUserSettings> mSettings = this.mSettings;
        if (mSettings == null) {
            Log.e("nf_push", "This should not happen! Map is null!");
            return;
        }
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                NotificationUserSettings.saveSettings(PushNotificationAgent.this.getContext(), mSettings);
            }
        });
    }
    
    private void unregisterReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.pushNotificationReceiver);
        }
        catch (Throwable t) {
            Log.e("nf_push", "Failed to unregister ", t);
        }
    }
    
    private void updateCurrentUserSettings(final String currentProfileUserId) {
        this.mCurrentUserSettings.currentProfileUserId = currentProfileUserId;
        this.mCurrentUserSettings.timestamp = System.currentTimeMillis();
        this.mSettings.put(this.mCurrentUserSettings.userId, this.mCurrentUserSettings);
        NotificationUserSettings.saveSettings(this.getContext(), this.mSettings);
    }
    
    private void updateSettingsOnOptedIn(final boolean optedIn) {
        if (this.mCurrentUserSettings == null) {
            Log.d("nf_push", "User is NOT logged in, do nothing. We can not register");
            return;
        }
        this.mCurrentUserSettings.optedIn = optedIn;
        this.mCurrentUserSettings.optInDisplayed = true;
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Save user settings " + this.mCurrentUserSettings);
        }
        this.saveSettings();
    }
    
    private void validateCurrentUser() {
        final String userId = this.getService().getUserId();
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "User ID: " + userId);
        }
        if (StringUtils.isEmpty(userId)) {
            Log.e("nf_push", "User ID is empty! This should NOT happen!");
            this.getService().getClientLogging().getErrorLogging().logHandledException("PushNotificationAgent.validateCurrentUser: user ID is empty!");
        }
        else if (this.mCurrentUserSettings == null || !userId.equals(this.mCurrentUserSettings.userId)) {
            Log.d("nf_push", "We DO NOT have user! Try to find it from settings");
            if (this.mCurrentUserSettings != null) {
                this.mCurrentUserSettings.current = false;
            }
            this.mCurrentUserSettings = this.mSettings.get(userId);
            if (this.mCurrentUserSettings == null) {
                Log.d("nf_push", "User was not know from before");
                this.mCurrentUserSettings = this.createNewCurrentUserSettings(userId, this.getService().getCurrentProfileUserId());
                return;
            }
            this.mCurrentUserSettings.current = true;
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "User was know from before and he opted in " + this.mCurrentUserSettings.optedIn);
            }
        }
    }
    
    @Override
    public void destroy() {
        Log.d("nf_push", "PNA:: destroy and unregister receiver");
        this.unregisterReceiver();
        super.destroy();
    }
    
    @Override
    protected void doInit() {
        this.loadConfiguration();
        this.verifyGCM();
        this.registerReceiver();
        this.mImageLoader = this.getService().getImageLoader();
        this.doGcmRegistration();
        this.initCompleted(CommonStatus.OK);
    }
    
    public String getGcmRegistrationId() {
        return this.gcmRegistrationId;
    }
    
    public boolean handleCommand(final Intent intent) {
        if (intent == null) {
            Log.w("nf_push", "Intent is null");
            return false;
        }
        if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONREGISTERED".equals(intent.getAction())) {
            Log.d("nf_push", "Handle registration");
            this.onGcmRegistration(intent.getStringExtra("reg_id"));
        }
        else if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONUNREGISTERED".equals(intent.getAction())) {
            Log.d("nf_push", "Handle unregistration");
            this.onGcmUnregistration(intent.getStringExtra("reg_id"));
        }
        else if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONMESSAGE".equals(intent.getAction())) {
            Log.d("nf_push", "Handle message");
            this.onMessage(intent);
        }
        else if ("com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED".equals(intent.getAction())) {
            Log.d("nf_push", "Handle notification canceled");
            this.onNotificationCanceled(intent);
        }
        else {
            if (!"com.netflix.mediaclient.intent.action.NOTIFICATION_BROWSER_REDIRECT".equals(intent.getAction())) {
                Log.e("nf_push", "Uknown command!");
                return false;
            }
            Log.d("nf_push", "Handle notification browser redirect");
            this.onNotificationBrowserRedirect(intent);
        }
        return true;
    }
    
    @Override
    public void informServiceStartedOnGcmInfo() {
        Log.d("nf_push", "noting that gcmInfoEvent started NetflixService");
        this.mGcmInfoEventStartedService = true;
    }
    
    public boolean isGcmSupported() {
        return this.mGcmSupported;
    }
    
    @Override
    public boolean isReady() {
        return this.mGcmSupported;
    }
    
    @Override
    public boolean isSupported() {
        return this.isGcmSupported();
    }
    
    @Override
    public void reportAndKillService() {
        Log.d("nf_push", "Telling back-end to stop sending gcm info events");
        this.report(this.mCurrentUserSettings.optedIn, false);
        Log.d("nf_push", "Stopping NetflixService in 30000");
        this.getService().stopSelfInMs(30000L);
    }
    
    public void verifyGCM() {
        try {
            GCMRegistrar.checkDevice(this.getContext());
            Log.d("nf_push", "Device supports GCM");
            this.mGcmSupported = true;
        }
        catch (Throwable t) {
            Log.e("nf_push", "Device does NOT supports GCM");
        }
    }
    
    @Override
    public boolean wasNotificationOptInDisplayed() {
        if (!this.mGcmSupported) {
            Log.e("nf_push", "Device is NOT GCM registered, do not display notification! That is why true is returned");
            return true;
        }
        this.validateCurrentUser();
        if (this.mCurrentUserSettings == null) {
            Log.d("nf_push", "Current user is empty. Do NOT display opt in dialog!");
            return true;
        }
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Current user was displayed " + this.mCurrentUserSettings.optInDisplayed);
        }
        return this.mCurrentUserSettings.optInDisplayed;
    }
}
