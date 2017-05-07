// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.util.Log;
import com.facebook.internal.Validate;
import android.content.SharedPreferences$Editor;
import com.facebook.model.GraphObject;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.model.GraphObject$Factory;
import com.facebook.internal.AttributionIdentifiers;
import java.net.HttpURLConnection;
import com.facebook.internal.Utility;
import android.content.pm.ApplicationInfo;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.content.ContentResolver;
import java.lang.reflect.Field;
import android.os.AsyncTask;
import android.content.pm.Signature;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Base64;
import java.security.MessageDigest;
import android.content.Context;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Collection;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.HashSet;
import java.util.concurrent.Executor;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import android.net.Uri;

public final class Settings
{
    private static final String ANALYTICS_EVENT = "event";
    public static final String APPLICATION_ID_PROPERTY = "com.facebook.sdk.ApplicationId";
    private static final String APP_EVENT_PREFERENCES = "com.facebook.sdk.appEventPreferences";
    private static final String ATTRIBUTION_ID_COLUMN_NAME = "aid";
    private static final Uri ATTRIBUTION_ID_CONTENT_URI;
    private static final String ATTRIBUTION_PREFERENCES = "com.facebook.sdk.attributionTracking";
    private static final String AUTO_PUBLISH = "auto_publish";
    public static final String CLIENT_TOKEN_PROPERTY = "com.facebook.sdk.ClientToken";
    private static final int DEFAULT_CORE_POOL_SIZE = 5;
    private static final int DEFAULT_KEEP_ALIVE = 1;
    private static final int DEFAULT_MAXIMUM_POOL_SIZE = 128;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY;
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
    private static final String FACEBOOK_COM = "facebook.com";
    private static final Object LOCK;
    private static final String MOBILE_INSTALL_EVENT = "MOBILE_APP_INSTALL";
    private static final String PUBLISH_ACTIVITY_PATH = "%s/activities";
    private static final String TAG;
    private static volatile String appClientToken;
    private static volatile String appVersion;
    private static volatile String applicationId;
    private static volatile boolean defaultsLoaded;
    private static volatile Executor executor;
    private static volatile String facebookDomain;
    private static volatile boolean isDebugEnabled;
    private static final HashSet<LoggingBehavior> loggingBehaviors;
    private static AtomicLong onProgressThreshold;
    private static volatile boolean platformCompatibilityEnabled;
    private static Boolean sdkInitialized;
    private static volatile boolean shouldAutoPublishInstall;
    
    static {
        TAG = Settings.class.getCanonicalName();
        loggingBehaviors = new HashSet<LoggingBehavior>(Arrays.asList(LoggingBehavior.DEVELOPER_ERRORS));
        Settings.defaultsLoaded = false;
        Settings.facebookDomain = "facebook.com";
        Settings.onProgressThreshold = new AtomicLong(65536L);
        Settings.isDebugEnabled = false;
        LOCK = new Object();
        ATTRIBUTION_ID_CONTENT_URI = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
        DEFAULT_WORK_QUEUE = new LinkedBlockingQueue<Runnable>(10);
        DEFAULT_THREAD_FACTORY = new Settings$1();
        Settings.sdkInitialized = false;
    }
    
    public static final void addLoggingBehavior(final LoggingBehavior loggingBehavior) {
        synchronized (Settings.loggingBehaviors) {
            Settings.loggingBehaviors.add(loggingBehavior);
        }
    }
    
    public static final void clearLoggingBehaviors() {
        synchronized (Settings.loggingBehaviors) {
            Settings.loggingBehaviors.clear();
        }
    }
    
    public static String getAppVersion() {
        return Settings.appVersion;
    }
    
    public static String getApplicationId() {
        return Settings.applicationId;
    }
    
    public static String getApplicationSignature(final Context context) {
        if (context != null) {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                final String packageName = context.getPackageName();
                PackageInfo packageInfo;
                try {
                    packageInfo = packageManager.getPackageInfo(packageName, 64);
                    final Signature[] signatures = packageInfo.signatures;
                    if (signatures != null && signatures.length != 0) {
                        final String s = "SHA-1";
                        final MessageDigest messageDigest = MessageDigest.getInstance(s);
                        final MessageDigest messageDigest3;
                        final MessageDigest messageDigest2 = messageDigest3 = messageDigest;
                        final PackageInfo packageInfo2 = packageInfo;
                        final Signature[] array = packageInfo2.signatures;
                        final int n = 0;
                        final Signature signature = array[n];
                        final byte[] array2 = signature.toByteArray();
                        messageDigest3.update(array2);
                        final MessageDigest messageDigest4 = messageDigest2;
                        final byte[] array3 = messageDigest4.digest();
                        final int n2 = 9;
                        return Base64.encodeToString(array3, n2);
                    }
                    return null;
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return null;
                }
                try {
                    final String s = "SHA-1";
                    final MessageDigest messageDigest = MessageDigest.getInstance(s);
                    final MessageDigest messageDigest3;
                    final MessageDigest messageDigest2 = messageDigest3 = messageDigest;
                    final PackageInfo packageInfo2 = packageInfo;
                    final Signature[] array = packageInfo2.signatures;
                    final int n = 0;
                    final Signature signature = array[n];
                    final byte[] array2 = signature.toByteArray();
                    messageDigest3.update(array2);
                    final MessageDigest messageDigest4 = messageDigest2;
                    final byte[] array3 = messageDigest4.digest();
                    final int n2 = 9;
                    return Base64.encodeToString(array3, n2);
                }
                catch (NoSuchAlgorithmException ex2) {
                    return null;
                }
            }
        }
        return null;
    }
    
    private static Executor getAsyncTaskExecutor() {
        Object o3 = null;
        Label_0026: {
            Field field;
            try {
                final Field field2;
                field = (field2 = AsyncTask.class.getField("THREAD_POOL_EXECUTOR"));
                final Object o = null;
                final Object o2 = field2.get(o);
                final Object o4;
                o3 = (o4 = o2);
                if (o4 == null) {
                    return null;
                }
                break Label_0026;
            }
            catch (NoSuchFieldException ex) {
                return null;
            }
            try {
                final Field field2 = field;
                final Object o = null;
                final Object o2 = field2.get(o);
                final Object o4;
                o3 = (o4 = o2);
                if (o4 == null) {
                    return null;
                }
            }
            catch (IllegalAccessException ex2) {
                return null;
            }
        }
        if (!(o3 instanceof Executor)) {
            return null;
        }
        return (Executor)o3;
    }
    
    public static String getAttributionId(final ContentResolver p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/facebook/Settings.ATTRIBUTION_ID_CONTENT_URI:Landroid/net/Uri;
        //     4: iconst_1       
        //     5: anewarray       Ljava/lang/String;
        //     8: dup            
        //     9: iconst_0       
        //    10: ldc             "aid"
        //    12: aastore        
        //    13: aconst_null    
        //    14: aconst_null    
        //    15: aconst_null    
        //    16: invokevirtual   android/content/ContentResolver.query:(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //    19: astore_0       
        //    20: aload_0        
        //    21: ifnull          37
        //    24: aload_0        
        //    25: astore_2       
        //    26: aload_0        
        //    27: invokeinterface android/database/Cursor.moveToFirst:()Z
        //    32: istore_1       
        //    33: iload_1        
        //    34: ifne            51
        //    37: aload_0        
        //    38: ifnull          47
        //    41: aload_0        
        //    42: invokeinterface android/database/Cursor.close:()V
        //    47: aconst_null    
        //    48: astore_2       
        //    49: aload_2        
        //    50: areturn        
        //    51: aload_0        
        //    52: astore_2       
        //    53: aload_0        
        //    54: aload_0        
        //    55: ldc             "aid"
        //    57: invokeinterface android/database/Cursor.getColumnIndex:(Ljava/lang/String;)I
        //    62: invokeinterface android/database/Cursor.getString:(I)Ljava/lang/String;
        //    67: astore_3       
        //    68: aload_3        
        //    69: astore_2       
        //    70: aload_0        
        //    71: ifnull          49
        //    74: aload_0        
        //    75: invokeinterface android/database/Cursor.close:()V
        //    80: aload_3        
        //    81: areturn        
        //    82: astore_3       
        //    83: aconst_null    
        //    84: astore_0       
        //    85: aload_0        
        //    86: astore_2       
        //    87: getstatic       com/facebook/Settings.TAG:Ljava/lang/String;
        //    90: new             Ljava/lang/StringBuilder;
        //    93: dup            
        //    94: invokespecial   java/lang/StringBuilder.<init>:()V
        //    97: ldc_w           "Caught unexpected exception in getAttributionId(): "
        //   100: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   103: aload_3        
        //   104: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   116: pop            
        //   117: aload_0        
        //   118: ifnull          127
        //   121: aload_0        
        //   122: invokeinterface android/database/Cursor.close:()V
        //   127: aconst_null    
        //   128: areturn        
        //   129: astore_0       
        //   130: aconst_null    
        //   131: astore_2       
        //   132: aload_2        
        //   133: ifnull          142
        //   136: aload_2        
        //   137: invokeinterface android/database/Cursor.close:()V
        //   142: aload_0        
        //   143: athrow         
        //   144: astore_0       
        //   145: goto            132
        //   148: astore_3       
        //   149: goto            85
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      20     82     85     Ljava/lang/Exception;
        //  0      20     129    132    Any
        //  26     33     148    152    Ljava/lang/Exception;
        //  26     33     144    148    Any
        //  53     68     148    152    Ljava/lang/Exception;
        //  53     68     144    148    Any
        //  87     117    144    148    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    
    public static String getClientToken() {
        return Settings.appClientToken;
    }
    
    public static Executor getExecutor() {
        synchronized (Settings.LOCK) {
            if (Settings.executor == null) {
                Executor asyncTaskExecutor;
                if ((asyncTaskExecutor = getAsyncTaskExecutor()) == null) {
                    asyncTaskExecutor = new ThreadPoolExecutor(5, 128, 1L, TimeUnit.SECONDS, Settings.DEFAULT_WORK_QUEUE, Settings.DEFAULT_THREAD_FACTORY);
                }
                Settings.executor = asyncTaskExecutor;
            }
            return Settings.executor;
        }
    }
    
    public static String getFacebookDomain() {
        return Settings.facebookDomain;
    }
    
    public static boolean getLimitEventAndDataUsage(final Context context) {
        return context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).getBoolean("limitEventUsage", false);
    }
    
    public static final Set<LoggingBehavior> getLoggingBehaviors() {
        synchronized (Settings.loggingBehaviors) {
            return Collections.unmodifiableSet((Set<? extends LoggingBehavior>)new HashSet<LoggingBehavior>(Settings.loggingBehaviors));
        }
    }
    
    public static long getOnProgressThreshold() {
        return Settings.onProgressThreshold.get();
    }
    
    public static boolean getPlatformCompatibilityEnabled() {
        return Settings.platformCompatibilityEnabled;
    }
    
    public static String getSdkVersion() {
        return "3.21.1";
    }
    
    @Deprecated
    public static boolean getShouldAutoPublishInstall() {
        return Settings.shouldAutoPublishInstall;
    }
    
    public static final boolean isDebugEnabled() {
        return Settings.isDebugEnabled;
    }
    
    public static final boolean isLoggingBehaviorEnabled(final LoggingBehavior loggingBehavior) {
        while (true) {
            synchronized (Settings.loggingBehaviors) {
                if (isDebugEnabled() && Settings.loggingBehaviors.contains(loggingBehavior)) {
                    return true;
                }
            }
            return false;
        }
    }
    
    @Deprecated
    public static final boolean isLoggingEnabled() {
        return isDebugEnabled();
    }
    
    public static void loadDefaultsFromMetadata(final Context context) {
        Settings.defaultsLoaded = true;
        if (context != null) {
            try {
                final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null) {
                    if (Settings.applicationId == null) {
                        Settings.applicationId = applicationInfo.metaData.getString("com.facebook.sdk.ApplicationId");
                    }
                    if (Settings.appClientToken == null) {
                        Settings.appClientToken = applicationInfo.metaData.getString("com.facebook.sdk.ClientToken");
                    }
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
    }
    
    static void loadDefaultsFromMetadataIfNeeded(final Context context) {
        if (!Settings.defaultsLoaded) {
            loadDefaultsFromMetadata(context);
        }
    }
    
    static Response publishInstallAndWaitForResponse(final Context context, final String s, final boolean b) {
        Label_0046: {
            if (context != null) {
                if (s != null) {
                    break Label_0046;
                }
            }
            try {
                throw new IllegalArgumentException("Both context and applicationId must be non-null");
            }
            catch (Exception ex) {
                Utility.logd("Facebook-publish", ex);
                return new Response(null, null, new FacebookRequestError(null, ex));
            }
        }
        final AttributionIdentifiers attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        final SharedPreferences sharedPreferences = context.getSharedPreferences("com.facebook.sdk.attributionTracking", 0);
        final String string = s + "ping";
        final String string2 = s + "json";
        final long long1 = sharedPreferences.getLong(string, 0L);
        final String string3 = sharedPreferences.getString(string2, (String)null);
        if (!b) {
            setShouldAutoPublishInstall(false);
        }
        final GraphObject create = GraphObject$Factory.create();
        create.setProperty("event", "MOBILE_APP_INSTALL");
        Utility.setAppEventAttributionParameters(create, attributionIdentifiers, Utility.getHashedDeviceAndAppID(context, s), getLimitEventAndDataUsage(context));
        create.setProperty("auto_publish", b);
        create.setProperty("application_package_name", context.getPackageName());
        final Request postRequest = Request.newPostRequest(null, String.format("%s/activities", s), create, null);
        Label_0294: {
            if (long1 == 0L) {
                break Label_0294;
            }
            GraphObject create2 = null;
            while (true) {
                if (string3 == null) {
                    break Label_0243;
                }
                try {
                    create2 = GraphObject$Factory.create(new JSONObject(string3));
                    if (create2 == null) {
                        return Response.createResponsesFromString("true", null, new RequestBatch(new Request[] { postRequest }), true).get(0);
                    }
                    return new Response(null, null, null, create2, true);
                    // iftrue(Label_0414:, executeAndWait.getGraphObject() == null || executeAndWait.getGraphObject().getInnerJSONObject() == null)
                    SharedPreferences$Editor edit = null;
                    Response executeAndWait = null;
                Label_0414:
                    while (true) {
                        edit.putString(string2, executeAndWait.getGraphObject().getInnerJSONObject().toString());
                        break Label_0414;
                        Label_0348: {
                            executeAndWait = postRequest.executeAndWait();
                        }
                        edit = sharedPreferences.edit();
                        edit.putLong(string, System.currentTimeMillis());
                        continue;
                    }
                    edit.apply();
                    return executeAndWait;
                    // iftrue(Label_0326:, attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null || attributionIdentifiers.getAttributionId() != null)
                    throw new FacebookException("No attribution id available to send to server.");
                    Label_0326: {
                        throw new FacebookException("Install attribution has been disabled on the server.");
                    }
                }
                // iftrue(Label_0348:, Utility.queryAppSettings(s, false).supportsAttribution())
                catch (JSONException ex2) {
                    create2 = null;
                    continue;
                }
                break;
            }
        }
    }
    
    static void publishInstallAsync(Context applicationContext, final String s, final Request$Callback request$Callback) {
        applicationContext = applicationContext.getApplicationContext();
        getExecutor().execute(new Settings$2(applicationContext, s, request$Callback));
    }
    
    public static final void removeLoggingBehavior(final LoggingBehavior loggingBehavior) {
        synchronized (Settings.loggingBehaviors) {
            Settings.loggingBehaviors.remove(loggingBehavior);
        }
    }
    
    public static void sdkInitialize(final Context context) {
        synchronized (Settings.class) {
            if (!Settings.sdkInitialized) {
                loadDefaultsFromMetadataIfNeeded(context);
                Utility.loadAppSettingsAsync(context, getApplicationId());
                BoltsMeasurementEventListener.getInstance(context.getApplicationContext());
                Settings.sdkInitialized = true;
            }
        }
    }
    
    public static void setAppVersion(final String appVersion) {
        Settings.appVersion = appVersion;
    }
    
    public static void setApplicationId(final String applicationId) {
        Settings.applicationId = applicationId;
    }
    
    public static void setClientToken(final String appClientToken) {
        Settings.appClientToken = appClientToken;
    }
    
    public static void setExecutor(final Executor executor) {
        Validate.notNull(executor, "executor");
        synchronized (Settings.LOCK) {
            Settings.executor = executor;
        }
    }
    
    public static void setFacebookDomain(final String facebookDomain) {
        Log.w(Settings.TAG, "WARNING: Calling setFacebookDomain from non-DEBUG code.");
        Settings.facebookDomain = facebookDomain;
    }
    
    public static final void setIsDebugEnabled(final boolean isDebugEnabled) {
        Settings.isDebugEnabled = isDebugEnabled;
    }
    
    @Deprecated
    public static final void setIsLoggingEnabled(final boolean isDebugEnabled) {
        setIsDebugEnabled(isDebugEnabled);
    }
    
    public static void setLimitEventAndDataUsage(final Context context, final boolean b) {
        context.getSharedPreferences("com.facebook.sdk.appEventPreferences", 0).edit().putBoolean("limitEventUsage", b).apply();
    }
    
    public static void setOnProgressThreshold(final long n) {
        Settings.onProgressThreshold.set(n);
    }
    
    public static void setPlatformCompatibilityEnabled(final boolean platformCompatibilityEnabled) {
        Settings.platformCompatibilityEnabled = platformCompatibilityEnabled;
    }
    
    @Deprecated
    public static void setShouldAutoPublishInstall(final boolean shouldAutoPublishInstall) {
        Settings.shouldAutoPublishInstall = shouldAutoPublishInstall;
    }
}
