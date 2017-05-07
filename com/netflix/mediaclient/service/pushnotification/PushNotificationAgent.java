// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.util.StringUtils;
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
    private static final long DELTA_FOR_REPORT = 86400000L;
    private static final String TAG = "nf_push";
    private static int idCounter;
    private String gcmRegistrationId;
    private NotificationUserSettings mCurrentUserSettings;
    private boolean mGcmRegistered;
    private boolean mGcmSupported;
    private ImageLoader mImageLoader;
    private Map<String, NotificationUserSettings> mSettings;
    private final BroadcastReceiver pushNotificationReceiver;
    
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
                        PushNotificationAgent.this.unregisterOnUserLogout(PushNotificationAgent.this.createUserData(intent));
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN".equals(action)) {
                        Log.d("nf_push", "optIn");
                        PushNotificationAgent.this.registerForPushNotification();
                        return;
                    }
                    if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT".equals(action)) {
                        Log.d("nf_push", "optOut");
                        PushNotificationAgent.this.unregisterFromPushNotification();
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
        userData.currentProfileUserId = intent.getStringExtra("cp_uid");
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "CreateUserData: " + userData);
        }
        return userData;
    }
    
    private void doRegisterForNotifications() {
        this.validateCurrentUser();
        if (this.mCurrentUserSettings == null) {
            Log.d("nf_push", "User is NOT logged in, do nothing. We can not register");
            return;
        }
        this.mCurrentUserSettings.optedIn = true;
        this.mCurrentUserSettings.optInDisplayed = true;
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Save user settings " + this.mCurrentUserSettings);
        }
        this.saveSettings();
        GCMRegistrar.register(this.getContext(), "484286080282");
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
        userData.currentProfileUserId = this.getService().getCurrentProfileUserId();
        userData.accountCountry = this.getUserAgent().getReqCountry();
        userData.accountCountry = this.getUserAgent().getGeoCountry();
        userData.languages = this.getUserAgent().getLanguagesInCsv();
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "GetUserData: " + userData);
        }
        return userData;
    }
    
    private boolean isApplicationUpdated() {
        if (this.mCurrentUserSettings == null) {
            Log.d("nf_push", "User NOT found. Device is not registered. No need for check for update!");
        }
        else {
            if (!this.mCurrentUserSettings.optedIn) {
                Log.d("nf_push", "User is NOT oped in, no reason to force reregistration");
                return false;
            }
            final int versionCode = AndroidManifestUtils.getVersionCode(this.getContext());
            if (this.mCurrentUserSettings.oldAppVersion != Integer.MIN_VALUE && this.mCurrentUserSettings.oldAppVersion != versionCode) {
                if (Log.isLoggable("nf_push", 3)) {
                    Log.d("nf_push", "App version changed from " + this.mCurrentUserSettings.oldAppVersion + " to " + versionCode + "; resetting registration id");
                }
                return true;
            }
        }
        return false;
    }
    
    private boolean isBeaconDeltaExpire() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long timestamp = this.mCurrentUserSettings.timestamp;
        if (timestamp <= 0L) {
            Log.d("nf_push", "We do not know when last time beacon was sent. Probably data from previous app version");
            return true;
        }
        final long n = currentTimeMillis - timestamp;
        if (n <= 0L) {
            Log.d("nf_push", "Now is older than last time? Time messed up. Assume it was not sent");
            return true;
        }
        if (n >= 86400000L) {
            Log.d("nf_push", "More than 24 hours elapsed. Sent.");
            return true;
        }
        return false;
    }
    
    private void loadConfiguration() {
        this.mSettings = NotificationUserSettings.loadSettings(this.getContext());
    }
    
    private void onLogin() {
        final String userId = this.getService().getUserId();
        final String currentProfileUserId = this.getService().getCurrentProfileUserId();
        boolean b = false;
        boolean b2 = false;
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "onLogin with user ID: " + userId);
        }
        this.mCurrentUserSettings = this.mSettings.get(userId);
        Label_0130: {
            if (this.mCurrentUserSettings != null) {
                break Label_0130;
            }
            Log.d("nf_push", "User was not know from before");
            this.mCurrentUserSettings = this.createNewCurrentUserSettings(userId, currentProfileUserId);
            try {
                // iftrue(Label_0170:, StringUtils.safeEquals(this.mCurrentUserSettings.currentProfileUserId, currentProfileUserId))
                while (true) {
                Block_5:
                    while (true) {
                        Log.d("nf_push", "Checks if application is updated (only if app was registered before)...");
                        if (this.isApplicationUpdated()) {
                            Log.d("nf_push", "Application was updated, execute silent reregistration!");
                            this.doRegisterForNotifications();
                        }
                        else if (!this.validateRegistration()) {
                            if (b2 || this.isBeaconDeltaExpire()) {
                                Log.d("nf_push", "Report");
                                this.report(this.mGcmRegistered);
                                return;
                            }
                            break Label_0130;
                        }
                        return;
                        this.mCurrentUserSettings.current = true;
                        break Block_5;
                        Log.d("nf_push", "User was know from before and he opted in " + this.mCurrentUserSettings.optedIn);
                        b2 = b;
                        continue;
                    }
                    Log.d("nf_push", "currentProfile change detected");
                    b = true;
                    this.updateCurrentUserSettings(currentProfileUserId);
                    Label_0170: {
                        b2 = b;
                    }
                    continue;
                }
            }
            // iftrue(Label_0100:, !Log.isLoggable("nf_push", 3))
            catch (Throwable t) {
                Log.e("nf_push", "Check if we are registered already failed!", t);
                return;
            }
        }
        Log.d("nf_push", "No need to report, it was already done inside of last 24 hours or profileDidNotChange");
    }
    
    private void onNotificationCanceled(final Intent intent) {
        final String stringExtra = intent.getStringExtra("guid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received cancel notification WITHOUT Event GUID! Do nothing!");
            return;
        }
        final String stringExtra2 = intent.getStringExtra("messageGuid");
        if (StringUtils.isEmpty(stringExtra2)) {
            Log.e("nf_push", "Received cancel notification WITHOUT GUID! Do nothing!");
            return;
        }
        this.getService().getClientLogging().getCmpEventLogging().reportUserFeedbackOnReceivedPushNotification(new MessageData(stringExtra2, stringExtra), UserFeedbackOnReceivedPushNotification.canceled);
    }
    
    private void registerForPushNotification() {
        Log.d("nf_push", "Notification is enabled by UI.");
        if (!this.mGcmSupported) {
            Log.e("nf_push", "Notification is enabled by UI, but device does NOT support GCM!");
            return;
        }
        if (this.mGcmRegistered) {
            Log.d("nf_push", "Notification is enabled by UI, device does support GCM, but it is already registered!");
            return;
        }
        Log.d("nf_push", "Notification is enabled by UI, device does support GCM and device is NOT registered!");
        this.doRegisterForNotifications();
        Log.d("nf_push", "Registered!");
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
    
    private void report(final boolean b) {
        this.report(b, null);
    }
    
    private void report(final boolean b, final UserData userData) {
        if (!this.isGcmSupported()) {
            Log.e("nf_push", "We can not report anything is device does not support push notifications!");
            return;
        }
        if (userData == null) {
            Log.d("nf_push", "Gets user data");
            this.getUserData();
        }
        else {
            Log.d("nf_push", "Use given user data");
        }
        this.getService().getClientLogging().getCustomerEventLogging().reportNotificationOptIn(b, this.gcmRegistrationId);
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
    
    private void unregisterFromPushNotification() {
        this.validateCurrentUser();
        if (this.mCurrentUserSettings == null) {
            Log.d("nf_push", "User is NOT logged in, do nothing");
            return;
        }
        this.mCurrentUserSettings.optedIn = false;
        this.mCurrentUserSettings.optInDisplayed = true;
        this.saveSettings();
        Log.d("nf_push", "Notification is enabled by UI.");
        if (this.mGcmSupported) {
            if (this.mGcmRegistered) {
                Log.d("nf_push", "Notification is enabled by UI, device does support GCM, but it is already registered! Unregister!");
            }
            else {
                Log.d("nf_push", "Notification is enabled by UI, device does support GCM and device is NOT registered! Just in case deactivate!");
            }
            GCMRegistrar.unregister(this.getContext());
            if (this.mCurrentUserSettings != null) {
                this.mCurrentUserSettings.optInDisplayed = true;
            }
            this.report(false);
            this.mGcmRegistered = false;
            this.gcmRegistrationId = null;
            return;
        }
        Log.d("nf_push", "Notification is enabled by UI, but device does NOT support GCM! Do nothing!");
    }
    
    private void unregisterOnUserLogout(final UserData userData) {
        synchronized (this) {
            Log.d("nf_push", "User is loging out");
            if (!this.isGcmSupported()) {
                Log.e("nf_push", "We can not do anything because device does not support push notifications!");
            }
            else {
                Log.d("nf_push", "User is opted in, unregister device and send opt put state, but preserve user choice so we can restore it on his/her next login");
                GCMRegistrar.unregister(this.getContext());
                this.report(false, userData);
                if (userData != null) {
                    this.mCurrentUserSettings = this.mSettings.get(userData.userId);
                    if (this.mCurrentUserSettings == null) {
                        Log.e("nf_push", "User is logging out and it was uknown before?");
                        this.mCurrentUserSettings = new NotificationUserSettings();
                        this.mCurrentUserSettings.current = true;
                        this.mCurrentUserSettings.userId = userData.userId;
                        this.mCurrentUserSettings.optedIn = true;
                        this.mCurrentUserSettings.currentProfileUserId = userData.currentProfileUserId;
                        this.mCurrentUserSettings.oldAppVersion = AndroidManifestUtils.getVersionCode(this.getContext());
                    }
                }
                this.saveSettings();
                this.mCurrentUserSettings = null;
                this.mGcmRegistered = false;
                this.gcmRegistrationId = null;
            }
        }
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
            if (this.validateRegistration()) {
                return;
            }
        }
    }
    
    private boolean validateRegistration() {
        Log.d("nf_push", "Checks GCM registration...");
        this.gcmRegistrationId = GCMRegistrar.getRegistrationId(this.getContext());
        if (StringUtils.isEmpty(this.gcmRegistrationId)) {
            Log.d("nf_push", "Not registered to GCM");
            this.mGcmRegistered = false;
            if (this.mCurrentUserSettings.optedIn) {
                Log.d("nf_push", "User was opted in, execute silent reregistration");
                this.doRegisterForNotifications();
                return true;
            }
        }
        else {
            this.mGcmRegistered = true;
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "Already registered to GCM with id: " + this.gcmRegistrationId);
            }
        }
        return false;
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
        this.initCompleted(0);
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
            this.setRegistrationIdFromRegistrationServer(intent.getStringExtra("reg_id"));
        }
        else if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONUNREGISTERED".equals(intent.getAction())) {
            Log.d("nf_push", "Handle unregistration");
            this.unregistrationFromFromRegistrationServer(intent.getStringExtra("reg_id"));
        }
        else if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_GCM_ONMESSAGE".equals(intent.getAction())) {
            Log.d("nf_push", "Handle message");
            this.onMessage(intent);
        }
        else {
            if (!"com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED".equals(intent.getAction())) {
                Log.e("nf_push", "Uknown command!");
                return false;
            }
            Log.d("nf_push", "Handle notification canceled");
            this.onNotificationCanceled(intent);
        }
        return true;
    }
    
    public boolean isGcmRegistered() {
        return this.mGcmRegistered;
    }
    
    public boolean isGcmSupported() {
        return this.mGcmSupported;
    }
    
    @Override
    public boolean isReady() {
        return this.mGcmSupported;
    }
    
    @Override
    public boolean isRegistered() {
        return this.mGcmRegistered;
    }
    
    @Override
    public boolean isSupported() {
        return this.isGcmSupported();
    }
    
    @Override
    public void onMessage(final Intent intent) {
        Log.d("nf_push", "Message received, create notification. Running it on main thread.");
        NotificationFactory.createNotification(this.getContext(), intent, this.mImageLoader, this.getMessageId(this.getContext()), this.getService().getClientLogging().getErrorLogging());
    }
    
    @Override
    public void setRegistrationIdFromRegistrationServer(final String gcmRegistrationId) {
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "setRegistrationIdFromRegistrationServer " + gcmRegistrationId);
        }
        this.gcmRegistrationId = gcmRegistrationId;
        this.report(this.mGcmRegistered = true);
    }
    
    @Override
    public void unregistrationFromFromRegistrationServer(final String s) {
        if (s != null && s.equals(this.gcmRegistrationId)) {
            Log.d("nf_push", "Same registrration ID, report to back end");
        }
        else {
            Log.e("nf_push", "Received registration ID " + s + " is NOT the same as registration ID known to app " + this.gcmRegistrationId + ". Ignore!");
        }
        this.report(false);
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
