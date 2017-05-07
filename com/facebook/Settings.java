// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

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
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.lang.reflect.Field;
import android.os.AsyncTask;
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
    private static final Uri ATTRIBUTION_ID_CONTENT_URI;
    private static final ThreadFactory DEFAULT_THREAD_FACTORY;
    private static final BlockingQueue<Runnable> DEFAULT_WORK_QUEUE;
    private static final Object LOCK;
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
    
    public static String getAppVersion() {
        return Settings.appVersion;
    }
    
    public static String getApplicationId() {
        return Settings.applicationId;
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
    
    public static long getOnProgressThreshold() {
        return Settings.onProgressThreshold.get();
    }
    
    public static boolean getPlatformCompatibilityEnabled() {
        return Settings.platformCompatibilityEnabled;
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
    
    static Response publishInstallAndWaitForResponse(final Context context, final String s, final boolean b) {
        Label_0044: {
            if (context != null) {
                if (s != null) {
                    break Label_0044;
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
        Label_0297: {
            if (long1 == 0L) {
                break Label_0297;
            }
            GraphObject create2 = null;
            while (true) {
                if (string3 == null) {
                    break Label_0246;
                }
                try {
                    create2 = GraphObject$Factory.create(new JSONObject(string3));
                    if (create2 == null) {
                        return Response.createResponsesFromString("true", null, new RequestBatch(new Request[] { postRequest }), true).get(0);
                    }
                    return new Response(null, null, null, create2, true);
                    while (true) {
                        final SharedPreferences$Editor edit;
                        edit.apply();
                        return;
                        Label_0351: {
                            executeAndWait = postRequest.executeAndWait();
                        }
                        edit = sharedPreferences.edit();
                        edit.putLong(string, System.currentTimeMillis());
                        Block_13: {
                            break Block_13;
                            Label_0329:
                            throw new FacebookException("Install attribution has been disabled on the server.");
                            throw new FacebookException("No attribution id available to send to server.");
                        }
                        edit.putString(string2, executeAndWait.getGraphObject().getInnerJSONObject().toString());
                        continue;
                    }
                }
                // iftrue(Label_0417:, executeAndWait.getGraphObject() == null || executeAndWait.getGraphObject().getInnerJSONObject() == null)
                // iftrue(Label_0351:, Utility.queryAppSettings(s, false).supportsAttribution())
                // iftrue(Label_0329:, attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null || attributionIdentifiers.getAttributionId() != null)
                catch (JSONException ex2) {
                    create2 = null;
                    continue;
                }
                break;
            }
        }
    }
    
    @Deprecated
    public static void setShouldAutoPublishInstall(final boolean shouldAutoPublishInstall) {
        Settings.shouldAutoPublishInstall = shouldAutoPublishInstall;
    }
}
