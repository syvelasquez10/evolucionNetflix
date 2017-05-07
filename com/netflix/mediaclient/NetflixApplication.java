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
import android.content.res.Configuration;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.ui.LaunchActivity;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import com.netflix.mediaclient.android.app.UserInputManager;
import java.util.concurrent.atomic.AtomicBoolean;
import com.netflix.mediaclient.util.gfx.BitmapLruCache;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.BroadcastReceiver;
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
    private final BroadcastReceiver broadcastReceiver;
    private NetflixActivity currentActivity;
    private BitmapLruCache mBitmapCache;
    private final AtomicBoolean mIsNetflixServiceReady;
    private String mServiceLocale;
    private boolean mSignedUpOnce;
    private UserInputManager mUserInput;
    
    public NetflixApplication() {
        this.mSignedUpOnce = false;
        this.mUserInput = new UserInputManager();
        this.mIsNetflixServiceReady = new AtomicBoolean(false);
        this.broadcastReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("NetflixApplication", 2)) {
                    Log.v("NetflixApplication", "Received intent " + intent);
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED".equals(action)) {
                    Log.d("NetflixApplication", "Netflix service is destroyed");
                    NetflixApplication.this.mIsNetflixServiceReady.set(false);
                }
                else if ("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE".equals(action)) {
                    final int intExtra = intent.getIntExtra("status_code", -1);
                    if (Log.isLoggable("NetflixApplication", 3)) {
                        Log.d("NetflixApplication", "Netflix service is ready with status " + intExtra);
                    }
                    if (NetflixService.isServiceReady(intExtra)) {
                        Log.d("NetflixApplication", " Netflix application is ready");
                        NetflixApplication.this.mIsNetflixServiceReady.set(true);
                        return;
                    }
                    Log.d("NetflixApplication", " Netflix application is NOT ready");
                    NetflixApplication.this.mIsNetflixServiceReady.set(false);
                }
                else if (Log.isLoggable("NetflixApplication", 3)) {
                    Log.d("NetflixApplication", "We do not support action " + action);
                }
            }
        };
    }
    
    public static Intent createShowApplicationIntent(final Context context) {
        return new Intent(context, (Class)LaunchActivity.class).setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER");
    }
    
    private void registerReceiver() {
        Log.d("NetflixApplication", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_INIT_COMPLETE");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.NETFLIX_SERVICE_DESTROYED");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.NETFLIX_SERVICE");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.broadcastReceiver, intentFilter);
        }
        catch (Throwable t) {
            Log.e("NetflixApplication", "Failed to register ", t);
        }
    }
    
    private void reportFailedToLoadNativeLibraries(final Throwable t, final int n) {
        Log.d("NetflixApplication", "Send warning notification!");
        final NotificationCompat.Builder setAutoCancel = new NotificationCompat.Builder((Context)this).setOngoing(false).setOnlyAlertOnce(false).setSmallIcon(2130837603).setWhen(System.currentTimeMillis()).setTicker(this.getString(2131493215, new Object[] { n })).setContentTitle(this.getString(2131493213, new Object[] { n })).setContentText(this.getString(2131493214, new Object[] { n })).setAutoCancel(true);
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
    
    private void verifyNativeLibraries() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: invokestatic    com/netflix/mediaclient/repository/SecurityRepository.getCrittercismAppId:()Ljava/lang/String;
        //     5: pop            
        //     6: invokestatic    com/netflix/mediaclient/repository/SecurityRepository.isLoaded:()Z
        //     9: ifne            75
        //    12: new             Ljava/lang/IllegalStateException;
        //    15: dup            
        //    16: ldc_w           "Native libraries are missing!"
        //    19: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    22: astore_3       
        //    23: aload_3        
        //    24: astore          4
        //    26: aload_3        
        //    27: astore          4
        //    29: aload_3        
        //    30: astore          4
        //    32: ldc             "NetflixApplication"
        //    34: ldc_w           "Failed to load JNI libraries. Report"
        //    37: aload_3        
        //    38: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    41: pop            
        //    42: aload_3        
        //    43: astore          4
        //    45: aload_3        
        //    46: astore          4
        //    48: aload_3        
        //    49: astore          4
        //    51: aload_0        
        //    52: aload_3        
        //    53: sipush          2000
        //    56: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //    59: aload_3        
        //    60: ifnull          244
        //    63: ldc             "NetflixApplication"
        //    65: ldc_w           "Crash happend, re-throw"
        //    68: aload_3        
        //    69: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    72: pop            
        //    73: aload_3        
        //    74: athrow         
        //    75: invokestatic    com/netflix/mediaclient/repository/SecurityRepository.getLibraryVersion:()I
        //    78: istore_1       
        //    79: aload_0        
        //    80: invokestatic    com/netflix/mediaclient/util/AndroidManifestUtils.getVersionCode:(Landroid/content/Context;)I
        //    83: istore_2       
        //    84: ldc             "NetflixApplication"
        //    86: iconst_3       
        //    87: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    90: ifeq            129
        //    93: ldc             "NetflixApplication"
        //    95: new             Ljava/lang/StringBuilder;
        //    98: dup            
        //    99: invokespecial   java/lang/StringBuilder.<init>:()V
        //   102: ldc_w           "Expected native library version: "
        //   105: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   108: iload_2        
        //   109: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   112: ldc_w           ", real: "
        //   115: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   118: iload_1        
        //   119: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   122: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   125: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   128: pop            
        //   129: iload_1        
        //   130: iload_2        
        //   131: if_icmpeq       59
        //   134: ldc             "NetflixApplication"
        //   136: ldc_w           "Versions do not match!"
        //   139: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   142: pop            
        //   143: new             Ljava/lang/IllegalStateException;
        //   146: dup            
        //   147: ldc_w           "Native library mismatch"
        //   150: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   153: astore_3       
        //   154: aload_3        
        //   155: astore          4
        //   157: aload_3        
        //   158: astore          4
        //   160: aload_3        
        //   161: astore          4
        //   163: aload_0        
        //   164: aload_3        
        //   165: sipush          2001
        //   168: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   171: goto            59
        //   174: astore_3       
        //   175: ldc             "NetflixApplication"
        //   177: ldc_w           "Failed to load JNI libraries. Report"
        //   180: aload_3        
        //   181: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   184: pop            
        //   185: aload_0        
        //   186: aload_3        
        //   187: sipush          2002
        //   190: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   193: aload_3        
        //   194: athrow         
        //   195: astore_3       
        //   196: ldc             "NetflixApplication"
        //   198: ldc_w           "Failed to load JNI libraries. Report"
        //   201: aload_3        
        //   202: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   205: pop            
        //   206: aload_0        
        //   207: aload_3        
        //   208: sipush          2003
        //   211: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   214: aload_3        
        //   215: athrow         
        //   216: astore_3       
        //   217: ldc             "NetflixApplication"
        //   219: ldc_w           "Failed to load JNI libraries. Generic error. Report"
        //   222: aload_3        
        //   223: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   226: pop            
        //   227: aload_0        
        //   228: aload_3        
        //   229: sipush          2004
        //   232: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   235: new             Ljava/lang/RuntimeException;
        //   238: dup            
        //   239: aload_3        
        //   240: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   243: athrow         
        //   244: return         
        //   245: astore_3       
        //   246: goto            217
        //   249: astore_3       
        //   250: goto            196
        //   253: astore_3       
        //   254: goto            175
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  2      23     174    175    Ljava/lang/UnsatisfiedLinkError;
        //  2      23     195    196    Ljava/lang/NoClassDefFoundError;
        //  2      23     216    217    Ljava/lang/Throwable;
        //  32     42     253    257    Ljava/lang/UnsatisfiedLinkError;
        //  32     42     249    253    Ljava/lang/NoClassDefFoundError;
        //  32     42     245    249    Ljava/lang/Throwable;
        //  51     59     253    257    Ljava/lang/UnsatisfiedLinkError;
        //  51     59     249    253    Ljava/lang/NoClassDefFoundError;
        //  51     59     245    249    Ljava/lang/Throwable;
        //  75     129    174    175    Ljava/lang/UnsatisfiedLinkError;
        //  75     129    195    196    Ljava/lang/NoClassDefFoundError;
        //  75     129    216    217    Ljava/lang/Throwable;
        //  134    154    174    175    Ljava/lang/UnsatisfiedLinkError;
        //  134    154    195    196    Ljava/lang/NoClassDefFoundError;
        //  134    154    216    217    Ljava/lang/Throwable;
        //  163    171    253    257    Ljava/lang/UnsatisfiedLinkError;
        //  163    171    249    253    Ljava/lang/NoClassDefFoundError;
        //  163    171    245    249    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
        if (Log.isLoggable("NetflixApplication", 3)) {
            Log.d("NetflixApplication", "onConfigurationChanged");
        }
        this.refreshLocale(this.mServiceLocale);
    }
    
    public void onCreate() {
        super.onCreate();
        Log.d("NetflixApplication", "Application started");
        Log.d("NetflixApplication", "Load native libraries ");
        this.verifyNativeLibraries();
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
        if (Log.isLoggable("nf_locale", 3)) {
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
                if (Log.isLoggable("nf_locale", 3)) {
                    Log.d("nf_locale", "No need to refreshLocale: serviceLocale=" + locale + " current Locale language=" + default1);
                }
            }
            else {
                if (Log.isLoggable("nf_locale", 4)) {
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
}
