// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient;

import android.util.DisplayMetrics;
import android.content.res.Resources;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Locale;
import com.netflix.mediaclient.repository.UserLocale;
import com.netflix.mediaclient.event.UIEvent;
import android.app.Application$ActivityLifecycleCallbacks;
import com.netflix.mediaclient.service.pservice.PServiceWidgetProvider;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.res.Configuration;
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
import com.netflix.mediaclient.android.app.UserInputManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;
import java.util.TimerTask;
import java.util.Timer;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.BroadcastReceiver;
import com.google.gson.Gson;
import android.app.Application;

public class NetflixApplication extends Application
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
    private static boolean sAactivityVisible;
    private static boolean sEnableSmartLock;
    private final long MAX_ACTIVITY_TRANSITION_TIME_MS;
    private final BroadcastReceiver broadcastReceiver;
    private NetflixActivity currentActivity;
    private Timer mActivityTransitionTimer;
    private TimerTask mActivityTransitionTimerTask;
    private BitmapLruCache mBitmapCache;
    private final AtomicBoolean mIsNetflixServiceReady;
    private String mServiceLocale;
    private boolean mSignedUpOnce;
    private final UserInputManager mUserInput;
    private boolean wasInBackground;
    
    static {
        gson = new Gson();
        NetflixApplication.sEnableSmartLock = true;
    }
    
    public NetflixApplication() {
        this.mSignedUpOnce = false;
        this.mUserInput = new UserInputManager();
        this.MAX_ACTIVITY_TRANSITION_TIME_MS = 600L;
        this.mIsNetflixServiceReady = new AtomicBoolean(false);
        this.broadcastReceiver = new NetflixApplication$1(this);
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
    
    public static Gson getGson() {
        return NetflixApplication.gson;
    }
    
    public static boolean isActivityVisible() {
        return NetflixApplication.sAactivityVisible;
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
        final NotificationCompat$Builder setAutoCancel = new NotificationCompat$Builder((Context)this).setOngoing(false).setOnlyAlertOnce(false).setSmallIcon(2130837757).setWhen(System.currentTimeMillis()).setTicker(this.getString(2131165708, new Object[] { n })).setContentTitle(this.getString(2131165709, new Object[] { n })).setContentText(this.getString(2131165412, new Object[] { n })).setAutoCancel(true);
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
    
    public static void setEnableSmartLock(final boolean b) {
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
        Log.d("NetflixApplication", "Application onCreate");
        Log.d("NetflixApplication", "Loading native libraries");
        this.loadAndVerifyNativeLibraries();
        this.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.mUserInput);
        this.registerReceiver();
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
    
    public void refreshLocale(final String mServiceLocale) {
        if (Log.isLoggable()) {
            Log.d("nf_locale", "refreshLocale with language = " + mServiceLocale);
        }
        if (mServiceLocale == null) {
            Log.d("nf_locale", "serviceLocale = null");
        }
        else {
            this.mServiceLocale = mServiceLocale;
            final Locale locale = new UserLocale(this.mServiceLocale).getLocale();
            final Locale default1 = Locale.getDefault();
            if (locale.getLanguage().equals(default1.getLanguage()) && (StringUtils.isEmpty(locale.getCountry()) || locale.getCountry().equals(default1.getCountry()))) {
                if (Log.isLoggable()) {
                    Log.d("nf_locale", "No need to refreshLocale: serviceLocale=" + locale + " current Locale language=" + default1);
                }
            }
            else {
                if (Log.isLoggable()) {
                    Log.i("nf_locale", "Changing language from " + default1 + " to " + locale);
                }
                Locale.setDefault(locale);
                final Configuration configuration = new Configuration();
                configuration.locale = locale;
                final Resources resources = this.getResources();
                if (resources == null) {
                    Log.w("nf_locale", "NA::refreshLocale: Resources are NULL. It should NOT happen!");
                    return;
                }
                final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
                if (displayMetrics == null) {
                    Log.w("nf_locale", "NA::refreshLocale: DisplayMetrics is NULL. It should NOT happen!");
                    return;
                }
                try {
                    resources.updateConfiguration(configuration, displayMetrics);
                }
                catch (Exception ex) {
                    Log.e("nf_locale", "NA::refreshLocale: Failed to update configuration", ex);
                }
            }
        }
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
    
    public void startActivityTransitionTimer() {
        this.mActivityTransitionTimer = new Timer();
        this.mActivityTransitionTimerTask = new NetflixApplication$2(this);
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
    
    public boolean wasInBackground(final boolean b) {
        final boolean wasInBackground = this.wasInBackground;
        this.wasInBackground = false;
        return wasInBackground;
    }
}
