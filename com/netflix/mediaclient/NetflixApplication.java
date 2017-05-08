// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import com.netflix.mediaclient.event.UIEvent;
import android.app.Application$ActivityLifecycleCallbacks;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.falkor.cache.FalkorCache;
import io.realm.Realm;
import com.netflix.mediaclient.service.pservice.PServiceWidgetProvider;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.res.Configuration;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.common.NetflixCommon$Host;
import android.app.Application;
import com.netflix.mediaclient.common.NetflixCommon;
import com.netflix.cstatssamurai.ClientStats;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat$Builder;
import com.netflix.mediaclient.util.IntentUtils;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.ui.launch.LaunchActivity;
import android.content.Intent;
import android.content.Context;
import com.google.gson.GsonBuilder;
import com.netflix.mediaclient.android.app.UserInputManager;
import com.netflix.mediaclient.util.l10n.UserLocale;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;
import java.util.TimerTask;
import java.util.Timer;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.BroadcastReceiver;
import com.google.gson.Gson;
import android.support.multidex.MultiDexApplication;

public class NetflixApplication extends MultiDexApplication
{
    public static final String LOAD_TAG = "NflxLoading";
    private static final int SO_FAILED_T0_LOAD = 2000;
    private static final int SO_LOADING_CLASS_NOT_FOUND = 2003;
    private static final int SO_LOADING_GENERIC_ERROR = 2004;
    private static final int SO_LOADING_UNSATISFIED_LINK = 2002;
    private static final int SO_VERSION_MISMATCH = 2001;
    private static final String TAG = "NetflixApplication";
    private static final String TAG_LOCALE = "nf_locale";
    private static final Gson gson;
    private static NetflixApplication instance;
    private static boolean isDebugToastEnabled;
    private static boolean sAactivityVisible;
    private static boolean sEnableSmartLock;
    private final long MAX_ACTIVITY_TRANSITION_TIME_MS;
    private final BroadcastReceiver broadcastReceiver;
    private NetflixActivity currentActivity;
    private Timer mActivityTransitionTimer;
    private TimerTask mActivityTransitionTimerTask;
    private BitmapLruCache mBitmapCache;
    private final AtomicBoolean mIsNetflixServiceReady;
    private UserLocale mServiceLocale;
    private boolean mSignedUpOnce;
    private final UserInputManager mUserInput;
    private boolean ttrComplete;
    private boolean wasInBackground;
    
    static {
        gson = new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
        NetflixApplication.sEnableSmartLock = true;
        NetflixApplication.isDebugToastEnabled = false;
    }
    
    public NetflixApplication() {
        this.mSignedUpOnce = false;
        this.ttrComplete = false;
        this.mUserInput = new UserInputManager();
        this.MAX_ACTIVITY_TRANSITION_TIME_MS = 600L;
        this.mIsNetflixServiceReady = new AtomicBoolean(false);
        this.broadcastReceiver = new NetflixApplication$2(this);
    }
    
    public static void activityPaused() {
        NetflixApplication.sAactivityVisible = false;
    }
    
    public static void activityResumed() {
        NetflixApplication.sAactivityVisible = true;
    }
    
    public static Intent createShowApplicationIntent(final Context context) {
        return new Intent(context, (Class)LaunchActivity.class).setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
    }
    
    public static Context getContext() {
        return (Context)NetflixApplication.instance;
    }
    
    public static Context getCurrentActivityForDebug() {
        return (Context)NetflixApplication.instance.getCurrentActivity();
    }
    
    public static Gson getGson() {
        return NetflixApplication.gson;
    }
    
    public static boolean isActivityVisible() {
        return NetflixApplication.sAactivityVisible;
    }
    
    public static boolean isDebugToastEnabled() {
        return NetflixApplication.isDebugToastEnabled;
    }
    
    public static boolean isSmartlockEnabled() {
        return NetflixApplication.sEnableSmartLock;
    }
    
    private void loadAndVerifyNativeLibraries() {
        Throwable t = null;
        try {
            SecurityRepository.loadLibraries((Context)this);
            if (!SecurityRepository.isLoaded()) {
                t = new IllegalStateException("Native libraries are missing!");
                Log.e("NetflixApplication", "Failed to load JNI libraries. Report", t);
                this.reportFailedToLoadNativeLibraries(t, 2000);
            }
            else {
                final int libraryVersion = SecurityRepository.getLibraryVersion();
                final int versionCode = AndroidManifestUtils.getVersionCode((Context)this);
                if (Log.isLoggable()) {
                    Log.d("NetflixApplication", "Manifest library version: " + versionCode + ", real: " + libraryVersion);
                }
                if (libraryVersion != versionCode) {
                    Log.e("NetflixApplication", "Versions do not match!");
                    t = new IllegalStateException("Native library mismatch");
                    this.reportFailedToLoadNativeLibraries(t, 2001);
                }
            }
            if (t != null) {
                Log.e("NetflixApplication", "Crash happend, re-throw", t);
                throw t;
            }
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            Log.e("NetflixApplication", "Failed to load JNI libraries. Report", unsatisfiedLinkError);
            this.reportFailedToLoadNativeLibraries(unsatisfiedLinkError, 2002);
            throw unsatisfiedLinkError;
        }
        catch (NoClassDefFoundError noClassDefFoundError) {
            Log.e("NetflixApplication", "Failed to load JNI libraries. Report", noClassDefFoundError);
            this.reportFailedToLoadNativeLibraries(noClassDefFoundError, 2003);
            throw noClassDefFoundError;
        }
        catch (Throwable t2) {
            Log.e("NetflixApplication", "Failed to load JNI libraries. Generic error. Report", t2);
            this.reportFailedToLoadNativeLibraries(t2, 2004);
            throw new RuntimeException(t2);
        }
    }
    
    private void registerReceiver() {
        Log.d("NetflixApplication", "Registering application broadcast receiver");
        IntentUtils.registerSafelyLocalBroadcastReceiver((Context)this, this.broadcastReceiver, "com.netflix.mediaclient.intent.category.NETFLIX_SERVICE", "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE", "com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED");
    }
    
    private void reportFailedToLoadNativeLibraries(final Throwable t, final int n) {
        Log.d("NetflixApplication", "Send warning notification!");
        final NotificationCompat$Builder setAutoCancel = new NotificationCompat$Builder((Context)this).setOngoing(false).setOnlyAlertOnce(false).setSmallIcon(2130838007).setWhen(System.currentTimeMillis()).setTicker(this.getString(2131297010, new Object[] { n })).setContentTitle(this.getString(2131297011, new Object[] { n })).setContentText(this.getString(2131296538, new Object[] { n })).setAutoCancel(true);
        setAutoCancel.setContentIntent(PendingIntent.getActivity((Context)this, 0, new Intent("android.intent.action.UNINSTALL_PACKAGE", Uri.parse("package:com.netflix.mediaclient")), 134217728));
        final Notification build = setAutoCancel.build();
        final NotificationManager notificationManager = (NotificationManager)this.getSystemService("notification");
        if (notificationManager != null) {
            Log.d("NetflixApplication", "Send warning notification done started...");
            notificationManager.notify(1, build);
            Log.d("NetflixApplication", "Send warning notification done.");
            return;
        }
        Log.e("NetflixApplication", "Can not send warning, notification manager is null!");
    }
    
    public static void setEnableDebugToast(final boolean isDebugToastEnabled) {
        NetflixApplication.isDebugToastEnabled = isDebugToastEnabled;
    }
    
    public static void setEnableSmartLock(final boolean b) {
    }
    
    private void setupClientStats() {
        ClientStats.getInstance().setEnabled(true);
    }
    
    private void setupNetflixCommon() {
        NetflixCommon.initWith(this, new NetflixApplication$1(this));
    }
    
    public void clearSignedInOnce() {
        this.mSignedUpOnce = false;
        PreferenceUtils.putStringPref((Context)this, "useragent_userprofiles_data", null);
    }
    
    public NetflixActivity getCurrentActivity() {
        return this.currentActivity;
    }
    
    public BitmapLruCache getImageCache() {
        return this.mBitmapCache;
    }
    
    public UserInputManager getUserInput() {
        return this.mUserInput;
    }
    
    public boolean hasSignedUpOnce() {
        return this.mSignedUpOnce;
    }
    
    public boolean isReady() {
        return this.mIsNetflixServiceReady.get();
    }
    
    public boolean isTTRComplete() {
        return this.ttrComplete;
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (Log.isLoggable()) {
            Log.d("NetflixApplication", "onConfigurationChanged");
        }
        this.refreshLocale(this.mServiceLocale);
        if (AndroidUtils.isWidgetInstalled(this.getApplicationContext())) {
            PServiceWidgetProvider.notifyWidgetsOnConfigurationChange((Context)this);
        }
    }
    
    public void onCreate() {
        super.onCreate();
        NetflixApplication.instance = this;
        Log.d("NetflixApplication", "Application onCreate");
        this.setupNetflixCommon();
        Log.d("NetflixApplication", "Loading native libraries");
        this.loadAndVerifyNativeLibraries();
        Realm.init((Context)this);
        FalkorCache.init(this);
        DebugOverlay.init(this);
        this.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.mUserInput);
        this.registerReceiver();
        this.setupClientStats();
    }
    
    public void onTrimMemory(final int n) {
        super.onTrimMemory(n);
        Log.i("NetflixApplication", "onTrimMemory: " + n);
        if (n >= 60 && this.mBitmapCache != null) {
            Log.i("NetflixApplication", "Flushing BitmapCache");
            this.mBitmapCache.evictAll();
        }
    }
    
    public void publishEvent(final UIEvent uiEvent) {
        throw new IllegalStateException("TODO: Not implemented - move this to netflixService");
    }
    
    public void refreshLastKnownLocale() {
        this.refreshLocale(this.mServiceLocale);
    }
    
    public void refreshLocale(final UserLocale mServiceLocale) {
        if (mServiceLocale == null) {
            Log.d("nf_locale", "serviceLocale = null, exit");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_locale", "refreshLocale with language = " + mServiceLocale);
        }
        this.mServiceLocale = mServiceLocale;
        LocalizationUtils.updateLocale(mServiceLocale.getLocale(), this);
    }
    
    public void refreshLocale(final String s) {
        if (s == null) {
            Log.d("nf_locale", "serviceLocale = null, exit");
            return;
        }
        this.refreshLocale(new UserLocale(s));
    }
    
    public void releaseCurrentActivity(final NetflixActivity netflixActivity) {
        if (this.currentActivity != null && this.currentActivity.equals(netflixActivity)) {
            this.currentActivity = null;
        }
    }
    
    public void setCurrentActivity(final NetflixActivity netflixActivity) {
    }
    
    public void setImageCache(final BitmapLruCache mBitmapCache) {
        if (this.mBitmapCache != null) {
            Log.w("NetflixApplication", "ImageCache is set more than once!");
        }
        this.mBitmapCache = mBitmapCache;
    }
    
    public void setSignedInOnce() {
        this.mSignedUpOnce = true;
    }
    
    public void setTTRComplete() {
        this.ttrComplete = true;
    }
    
    public void startActivityTransitionTimer() {
        this.mActivityTransitionTimer = new Timer();
        this.mActivityTransitionTimerTask = new NetflixApplication$3(this);
        this.mActivityTransitionTimer.schedule(this.mActivityTransitionTimerTask, 600L);
    }
    
    public void stopActivityTransitionTimer() {
        if (this.mActivityTransitionTimerTask != null) {
            this.mActivityTransitionTimerTask.cancel();
        }
        if (this.mActivityTransitionTimer != null) {
            this.mActivityTransitionTimer.cancel();
        }
        this.wasInBackground = false;
    }
    
    public boolean wasInBackground() {
        final boolean wasInBackground = this.wasInBackground;
        this.wasInBackground = false;
        return wasInBackground;
    }
}
