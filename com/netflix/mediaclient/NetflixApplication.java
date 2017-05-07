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
    
    private void loadAndVerifyNativeLibraries() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_0        
        //     3: invokestatic    com/netflix/mediaclient/repository/SecurityRepository.loadLibraries:(Landroid/content/Context;)Z
        //     6: pop            
        //     7: invokestatic    com/netflix/mediaclient/repository/SecurityRepository.isLoaded:()Z
        //    10: ifne            73
        //    13: new             Ljava/lang/IllegalStateException;
        //    16: dup            
        //    17: ldc             "Native libraries are missing!"
        //    19: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    22: astore_3       
        //    23: aload_3        
        //    24: astore          4
        //    26: aload_3        
        //    27: astore          4
        //    29: aload_3        
        //    30: astore          4
        //    32: ldc             "NetflixApplication"
        //    34: ldc             "Failed to load JNI libraries. Report"
        //    36: aload_3        
        //    37: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    40: pop            
        //    41: aload_3        
        //    42: astore          4
        //    44: aload_3        
        //    45: astore          4
        //    47: aload_3        
        //    48: astore          4
        //    50: aload_0        
        //    51: aload_3        
        //    52: sipush          2000
        //    55: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //    58: aload_3        
        //    59: ifnull          235
        //    62: ldc             "NetflixApplication"
        //    64: ldc             "Crash happend, re-throw"
        //    66: aload_3        
        //    67: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    70: pop            
        //    71: aload_3        
        //    72: athrow         
        //    73: invokestatic    com/netflix/mediaclient/repository/SecurityRepository.getLibraryVersion:()I
        //    76: istore_1       
        //    77: aload_0        
        //    78: invokestatic    com/netflix/mediaclient/util/AndroidManifestUtils.getVersionCode:(Landroid/content/Context;)I
        //    81: istore_2       
        //    82: ldc             "NetflixApplication"
        //    84: iconst_3       
        //    85: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    88: ifeq            125
        //    91: ldc             "NetflixApplication"
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: ldc             "Expected native library version: "
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: iload_2        
        //   106: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   109: ldc             ", real: "
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: iload_1        
        //   115: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   118: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   121: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   124: pop            
        //   125: iload_1        
        //   126: iload_2        
        //   127: if_icmpeq       58
        //   130: ldc             "NetflixApplication"
        //   132: ldc             "Versions do not match!"
        //   134: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   137: pop            
        //   138: new             Ljava/lang/IllegalStateException;
        //   141: dup            
        //   142: ldc             "Native library mismatch"
        //   144: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   147: astore_3       
        //   148: aload_3        
        //   149: astore          4
        //   151: aload_3        
        //   152: astore          4
        //   154: aload_3        
        //   155: astore          4
        //   157: aload_0        
        //   158: aload_3        
        //   159: sipush          2001
        //   162: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   165: goto            58
        //   168: astore_3       
        //   169: ldc             "NetflixApplication"
        //   171: ldc             "Failed to load JNI libraries. Report"
        //   173: aload_3        
        //   174: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   177: pop            
        //   178: aload_0        
        //   179: aload_3        
        //   180: sipush          2002
        //   183: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   186: aload_3        
        //   187: athrow         
        //   188: astore_3       
        //   189: ldc             "NetflixApplication"
        //   191: ldc             "Failed to load JNI libraries. Report"
        //   193: aload_3        
        //   194: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   197: pop            
        //   198: aload_0        
        //   199: aload_3        
        //   200: sipush          2003
        //   203: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   206: aload_3        
        //   207: athrow         
        //   208: astore_3       
        //   209: ldc             "NetflixApplication"
        //   211: ldc             "Failed to load JNI libraries. Generic error. Report"
        //   213: aload_3        
        //   214: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   217: pop            
        //   218: aload_0        
        //   219: aload_3        
        //   220: sipush          2004
        //   223: invokespecial   com/netflix/mediaclient/NetflixApplication.reportFailedToLoadNativeLibraries:(Ljava/lang/Throwable;I)V
        //   226: new             Ljava/lang/RuntimeException;
        //   229: dup            
        //   230: aload_3        
        //   231: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //   234: athrow         
        //   235: return         
        //   236: astore_3       
        //   237: goto            209
        //   240: astore_3       
        //   241: goto            189
        //   244: astore_3       
        //   245: goto            169
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  2      23     168    169    Ljava/lang/UnsatisfiedLinkError;
        //  2      23     188    189    Ljava/lang/NoClassDefFoundError;
        //  2      23     208    209    Ljava/lang/Throwable;
        //  32     41     244    248    Ljava/lang/UnsatisfiedLinkError;
        //  32     41     240    244    Ljava/lang/NoClassDefFoundError;
        //  32     41     236    240    Ljava/lang/Throwable;
        //  50     58     244    248    Ljava/lang/UnsatisfiedLinkError;
        //  50     58     240    244    Ljava/lang/NoClassDefFoundError;
        //  50     58     236    240    Ljava/lang/Throwable;
        //  73     125    168    169    Ljava/lang/UnsatisfiedLinkError;
        //  73     125    188    189    Ljava/lang/NoClassDefFoundError;
        //  73     125    208    209    Ljava/lang/Throwable;
        //  130    148    168    169    Ljava/lang/UnsatisfiedLinkError;
        //  130    148    188    189    Ljava/lang/NoClassDefFoundError;
        //  130    148    208    209    Ljava/lang/Throwable;
        //  157    165    244    248    Ljava/lang/UnsatisfiedLinkError;
        //  157    165    240    244    Ljava/lang/NoClassDefFoundError;
        //  157    165    236    240    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
        final NotificationCompat.Builder setAutoCancel = new NotificationCompat.Builder((Context)this).setOngoing(false).setOnlyAlertOnce(false).setSmallIcon(2130837719).setWhen(System.currentTimeMillis()).setTicker(this.getString(2131296652, new Object[] { n })).setContentTitle(this.getString(2131296650, new Object[] { n })).setContentText(this.getString(2131296651, new Object[] { n })).setAutoCancel(true);
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
