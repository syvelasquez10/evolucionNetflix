// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Currency;
import java.math.BigDecimal;
import android.content.Intent;
import android.content.ComponentName;
import bolts.AppLinks;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONArray;
import com.facebook.internal.AttributionIdentifiers;
import com.facebook.internal.Utility$FetchedAppSettings;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import com.facebook.internal.Logger;
import java.util.ArrayList;
import java.util.Set;
import android.util.Log;
import android.app.Activity;
import java.util.List;
import java.util.Iterator;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import android.content.Context;

public class AppEventsLogger
{
    public static final String ACTION_APP_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_FLUSHED";
    public static final String APP_EVENTS_EXTRA_FLUSH_RESULT = "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT";
    public static final String APP_EVENTS_EXTRA_NUM_EVENTS_FLUSHED = "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED";
    private static final int APP_SUPPORTS_ATTRIBUTION_ID_RECHECK_PERIOD_IN_SECONDS = 86400;
    private static final int FLUSH_APP_SESSION_INFO_IN_SECONDS = 30;
    private static final int FLUSH_PERIOD_IN_SECONDS = 15;
    private static final int NUM_LOG_EVENTS_TO_TRY_TO_FLUSH_AFTER = 100;
    private static final String SOURCE_APPLICATION_HAS_BEEN_SET_BY_THIS_INTENT = "_fbSourceApplicationHasBeenSet";
    private static final String TAG;
    private static Context applicationContext;
    private static ScheduledThreadPoolExecutor backgroundExecutor;
    private static AppEventsLogger$FlushBehavior flushBehavior;
    private static String hashedDeviceAndAppId;
    private static boolean isOpenedByApplink;
    private static boolean requestInFlight;
    private static String sourceApplication;
    private static Map<AppEventsLogger$AccessTokenAppIdPair, AppEventsLogger$SessionEventsState> stateMap;
    private static Object staticLock;
    private final AppEventsLogger$AccessTokenAppIdPair accessTokenAppId;
    private final Context context;
    
    static {
        TAG = AppEventsLogger.class.getCanonicalName();
        AppEventsLogger.stateMap = new ConcurrentHashMap<AppEventsLogger$AccessTokenAppIdPair, AppEventsLogger$SessionEventsState>();
        AppEventsLogger.flushBehavior = AppEventsLogger$FlushBehavior.AUTO;
        AppEventsLogger.staticLock = new Object();
    }
    
    private AppEventsLogger(final Context context, String s, Session session) {
        Validate.notNull(context, "context");
        this.context = context;
        Session activeSession = session;
        if (session == null) {
            activeSession = Session.getActiveSession();
        }
        Label_0100: {
            if (activeSession == null || (s != null && !s.equals(activeSession.getApplicationId()))) {
                break Label_0100;
            }
            this.accessTokenAppId = new AppEventsLogger$AccessTokenAppIdPair(activeSession);
            Label_0111_Outer:Block_8_Outer:
            while (true) {
                session = (Session)AppEventsLogger.staticLock;
                synchronized (session) {
                    if (AppEventsLogger.hashedDeviceAndAppId == null) {
                        AppEventsLogger.hashedDeviceAndAppId = Utility.getHashedDeviceAndAppID(context, s);
                    }
                    if (AppEventsLogger.applicationContext == null) {
                        AppEventsLogger.applicationContext = context.getApplicationContext();
                    }
                    initializeTimersIfNeeded();
                    return;
                    while (true) {
                        while (true) {
                            this.accessTokenAppId = new AppEventsLogger$AccessTokenAppIdPair(null, (String)session);
                            s = (String)session;
                            continue Label_0111_Outer;
                            session = (Session)Utility.getMetadataApplicationId(context);
                            continue Block_8_Outer;
                        }
                        continue;
                    }
                }
                // iftrue(Label_0111:, session = s != null)
            }
        }
    }
    
    private static int accumulatePersistedEvents() {
        final AppEventsLogger$PersistedEvents andClearStore = AppEventsLogger$PersistedEvents.readAndClearStore(AppEventsLogger.applicationContext);
        final Iterator<AppEventsLogger$AccessTokenAppIdPair> iterator = andClearStore.keySet().iterator();
        int n = 0;
        while (iterator.hasNext()) {
            final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair = iterator.next();
            final AppEventsLogger$SessionEventsState sessionEventsState = getSessionEventsState(AppEventsLogger.applicationContext, appEventsLogger$AccessTokenAppIdPair);
            final List<AppEventsLogger$AppEvent> events = andClearStore.getEvents(appEventsLogger$AccessTokenAppIdPair);
            sessionEventsState.accumulatePersistedEvents(events);
            n += events.size();
        }
        return n;
    }
    
    public static void activateApp(final Context context) {
        Settings.sdkInitialize(context);
        activateApp(context, Utility.getMetadataApplicationId(context));
    }
    
    public static void activateApp(final Context context, String sourceApplication) {
        if (context == null || sourceApplication == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        if (context instanceof Activity) {
            setSourceApplication((Activity)context);
        }
        else {
            resetSourceApplication();
            Log.d(AppEventsLogger.class.getName(), "To set source application the context of activateApp must be an instance of Activity");
        }
        Settings.publishInstallAsync(context, sourceApplication, null);
        final AppEventsLogger appEventsLogger = new AppEventsLogger(context, sourceApplication, null);
        final long currentTimeMillis = System.currentTimeMillis();
        sourceApplication = getSourceApplication();
        AppEventsLogger.backgroundExecutor.execute(new AppEventsLogger$1(appEventsLogger, currentTimeMillis, sourceApplication));
    }
    
    private static AppEventsLogger$FlushStatistics buildAndExecuteRequests(final AppEventsLogger$FlushReason appEventsLogger$FlushReason, final Set<AppEventsLogger$AccessTokenAppIdPair> set) {
        final AppEventsLogger$FlushStatistics appEventsLogger$FlushStatistics = new AppEventsLogger$FlushStatistics(null);
        final boolean limitEventAndDataUsage = Settings.getLimitEventAndDataUsage(AppEventsLogger.applicationContext);
        final ArrayList<Request> list = new ArrayList<Request>();
        for (final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair : set) {
            final AppEventsLogger$SessionEventsState sessionEventsState = getSessionEventsState(appEventsLogger$AccessTokenAppIdPair);
            if (sessionEventsState != null) {
                final Request buildRequestForSession = buildRequestForSession(appEventsLogger$AccessTokenAppIdPair, sessionEventsState, limitEventAndDataUsage, appEventsLogger$FlushStatistics);
                if (buildRequestForSession == null) {
                    continue;
                }
                list.add(buildRequestForSession);
            }
        }
        if (list.size() > 0) {
            Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "Flushing %d events due to %s.", appEventsLogger$FlushStatistics.numEvents, appEventsLogger$FlushReason.toString());
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                iterator2.next().executeAndWait();
            }
            return appEventsLogger$FlushStatistics;
        }
        return null;
    }
    
    private static Request buildRequestForSession(final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair, final AppEventsLogger$SessionEventsState appEventsLogger$SessionEventsState, final boolean b, final AppEventsLogger$FlushStatistics appEventsLogger$FlushStatistics) {
        final String applicationId = appEventsLogger$AccessTokenAppIdPair.getApplicationId();
        final Utility$FetchedAppSettings queryAppSettings = Utility.queryAppSettings(applicationId, false);
        final Request postRequest = Request.newPostRequest(null, String.format("%s/activities", applicationId), null, null);
        Bundle parameters;
        if ((parameters = postRequest.getParameters()) == null) {
            parameters = new Bundle();
        }
        parameters.putString("access_token", appEventsLogger$AccessTokenAppIdPair.getAccessToken());
        postRequest.setParameters(parameters);
        if (queryAppSettings == null) {
            return null;
        }
        final int populateRequest = appEventsLogger$SessionEventsState.populateRequest(postRequest, queryAppSettings.supportsImplicitLogging(), queryAppSettings.supportsAttribution(), b);
        if (populateRequest == 0) {
            return null;
        }
        appEventsLogger$FlushStatistics.numEvents += populateRequest;
        postRequest.setCallback(new AppEventsLogger$7(appEventsLogger$AccessTokenAppIdPair, postRequest, appEventsLogger$SessionEventsState, appEventsLogger$FlushStatistics));
        return postRequest;
    }
    
    public static void deactivateApp(final Context context) {
        deactivateApp(context, Utility.getMetadataApplicationId(context));
    }
    
    public static void deactivateApp(final Context context, final String s) {
        if (context == null || s == null) {
            throw new IllegalArgumentException("Both context and applicationId must be non-null");
        }
        resetSourceApplication();
        AppEventsLogger.backgroundExecutor.execute(new AppEventsLogger$2(new AppEventsLogger(context, s, null), System.currentTimeMillis()));
    }
    
    static void eagerFlush() {
        if (getFlushBehavior() != AppEventsLogger$FlushBehavior.EXPLICIT_ONLY) {
            flush(AppEventsLogger$FlushReason.EAGER_FLUSHING_EVENT);
        }
    }
    
    private static void flush(final AppEventsLogger$FlushReason appEventsLogger$FlushReason) {
        Settings.getExecutor().execute(new AppEventsLogger$6(appEventsLogger$FlushReason));
    }
    
    private static void flushAndWait(final AppEventsLogger$FlushReason p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/facebook/AppEventsLogger.staticLock:Ljava/lang/Object;
        //     3: astore_1       
        //     4: aload_1        
        //     5: monitorenter   
        //     6: getstatic       com/facebook/AppEventsLogger.requestInFlight:Z
        //     9: ifeq            15
        //    12: aload_1        
        //    13: monitorexit    
        //    14: return         
        //    15: iconst_1       
        //    16: putstatic       com/facebook/AppEventsLogger.requestInFlight:Z
        //    19: new             Ljava/util/HashSet;
        //    22: dup            
        //    23: getstatic       com/facebook/AppEventsLogger.stateMap:Ljava/util/Map;
        //    26: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //    31: invokespecial   java/util/HashSet.<init>:(Ljava/util/Collection;)V
        //    34: astore_2       
        //    35: aload_1        
        //    36: monitorexit    
        //    37: invokestatic    com/facebook/AppEventsLogger.accumulatePersistedEvents:()I
        //    40: pop            
        //    41: aconst_null    
        //    42: astore_1       
        //    43: aload_0        
        //    44: aload_2        
        //    45: invokestatic    com/facebook/AppEventsLogger.buildAndExecuteRequests:(Lcom/facebook/AppEventsLogger$FlushReason;Ljava/util/Set;)Lcom/facebook/AppEventsLogger$FlushStatistics;
        //    48: astore_0       
        //    49: getstatic       com/facebook/AppEventsLogger.staticLock:Ljava/lang/Object;
        //    52: astore_1       
        //    53: aload_1        
        //    54: monitorenter   
        //    55: iconst_0       
        //    56: putstatic       com/facebook/AppEventsLogger.requestInFlight:Z
        //    59: aload_1        
        //    60: monitorexit    
        //    61: aload_0        
        //    62: ifnull          135
        //    65: new             Landroid/content/Intent;
        //    68: dup            
        //    69: ldc             "com.facebook.sdk.APP_EVENTS_FLUSHED"
        //    71: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //    74: astore_1       
        //    75: aload_1        
        //    76: ldc             "com.facebook.sdk.APP_EVENTS_NUM_EVENTS_FLUSHED"
        //    78: aload_0        
        //    79: getfield        com/facebook/AppEventsLogger$FlushStatistics.numEvents:I
        //    82: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;I)Landroid/content/Intent;
        //    85: pop            
        //    86: aload_1        
        //    87: ldc             "com.facebook.sdk.APP_EVENTS_FLUSH_RESULT"
        //    89: aload_0        
        //    90: getfield        com/facebook/AppEventsLogger$FlushStatistics.result:Lcom/facebook/AppEventsLogger$FlushResult;
        //    93: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;
        //    96: pop            
        //    97: getstatic       com/facebook/AppEventsLogger.applicationContext:Landroid/content/Context;
        //   100: invokestatic    android/support/v4/content/LocalBroadcastManager.getInstance:(Landroid/content/Context;)Landroid/support/v4/content/LocalBroadcastManager;
        //   103: aload_1        
        //   104: invokevirtual   android/support/v4/content/LocalBroadcastManager.sendBroadcast:(Landroid/content/Intent;)Z
        //   107: pop            
        //   108: return         
        //   109: astore_0       
        //   110: aload_1        
        //   111: monitorexit    
        //   112: aload_0        
        //   113: athrow         
        //   114: astore_0       
        //   115: getstatic       com/facebook/AppEventsLogger.TAG:Ljava/lang/String;
        //   118: ldc_w           "Caught unexpected exception while flushing: "
        //   121: aload_0        
        //   122: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   125: aload_1        
        //   126: astore_0       
        //   127: goto            49
        //   130: astore_0       
        //   131: aload_1        
        //   132: monitorexit    
        //   133: aload_0        
        //   134: athrow         
        //   135: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      14     109    114    Any
        //  15     37     109    114    Any
        //  43     49     114    130    Ljava/lang/Exception;
        //  55     61     130    135    Any
        //  110    112    109    114    Any
        //  131    133    130    135    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 79, Size: 79
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private static void flushIfNecessary() {
        synchronized (AppEventsLogger.staticLock) {
            if (getFlushBehavior() != AppEventsLogger$FlushBehavior.EXPLICIT_ONLY && getAccumulatedEventCount() > 100) {
                flush(AppEventsLogger$FlushReason.EVENT_THRESHOLD);
            }
        }
    }
    
    private static int getAccumulatedEventCount() {
        synchronized (AppEventsLogger.staticLock) {
            final Iterator<AppEventsLogger$SessionEventsState> iterator = AppEventsLogger.stateMap.values().iterator();
            int n = 0;
            while (iterator.hasNext()) {
                n += iterator.next().getAccumulatedEventCount();
            }
            return n;
        }
    }
    
    public static AppEventsLogger$FlushBehavior getFlushBehavior() {
        synchronized (AppEventsLogger.staticLock) {
            return AppEventsLogger.flushBehavior;
        }
    }
    
    @Deprecated
    public static boolean getLimitEventUsage(final Context context) {
        return Settings.getLimitEventAndDataUsage(context);
    }
    
    private static AppEventsLogger$SessionEventsState getSessionEventsState(final Context context, final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair) {
        final AppEventsLogger$SessionEventsState appEventsLogger$SessionEventsState = AppEventsLogger.stateMap.get(appEventsLogger$AccessTokenAppIdPair);
        AttributionIdentifiers attributionIdentifiers = null;
        if (appEventsLogger$SessionEventsState == null) {
            attributionIdentifiers = AttributionIdentifiers.getAttributionIdentifiers(context);
        }
        synchronized (AppEventsLogger.staticLock) {
            AppEventsLogger$SessionEventsState appEventsLogger$SessionEventsState2;
            if ((appEventsLogger$SessionEventsState2 = AppEventsLogger.stateMap.get(appEventsLogger$AccessTokenAppIdPair)) == null) {
                appEventsLogger$SessionEventsState2 = new AppEventsLogger$SessionEventsState(attributionIdentifiers, context.getPackageName(), AppEventsLogger.hashedDeviceAndAppId);
                AppEventsLogger.stateMap.put(appEventsLogger$AccessTokenAppIdPair, appEventsLogger$SessionEventsState2);
            }
            return appEventsLogger$SessionEventsState2;
        }
    }
    
    private static AppEventsLogger$SessionEventsState getSessionEventsState(final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair) {
        synchronized (AppEventsLogger.staticLock) {
            return AppEventsLogger.stateMap.get(appEventsLogger$AccessTokenAppIdPair);
        }
    }
    
    static String getSourceApplication() {
        String s = "Unclassified";
        if (AppEventsLogger.isOpenedByApplink) {
            s = "Applink";
        }
        String string = s;
        if (AppEventsLogger.sourceApplication != null) {
            string = s + "(" + AppEventsLogger.sourceApplication + ")";
        }
        return string;
    }
    
    private static void handleResponse(final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair, final Request request, Response result, final AppEventsLogger$SessionEventsState appEventsLogger$SessionEventsState, final AppEventsLogger$FlushStatistics appEventsLogger$FlushStatistics) {
        final FacebookRequestError error = result.getError();
        final Object success = AppEventsLogger$FlushResult.SUCCESS;
        while (true) {
            while (true) {
                Label_0034: {
                    if (error == null) {
                        final String format = "Success";
                        result = (Response)success;
                        break Label_0034;
                    }
                    if (error.getErrorCode() == -1) {
                        result = (Response)AppEventsLogger$FlushResult.NO_CONNECTIVITY;
                        final String format = "Failed: No Connectivity";
                        break Label_0034;
                    }
                    Label_0157: {
                        break Label_0157;
                    Label_0111_Outer:
                        while (true) {
                            final String s = (String)request.getTag();
                            while (true) {
                            Label_0201:
                                while (true) {
                                    try {
                                        final String string = new JSONArray(s).toString(2);
                                        final String format;
                                        Logger.log(LoggingBehavior.APP_EVENTS, AppEventsLogger.TAG, "Flush completed\nParams: %s\n  Result: %s\n  Events JSON: %s", request.getGraphObject().toString(), format, string);
                                        if (error != null) {
                                            final boolean b = true;
                                            appEventsLogger$SessionEventsState.clearInFlightAndStats(b);
                                            if (result == AppEventsLogger$FlushResult.NO_CONNECTIVITY) {
                                                AppEventsLogger$PersistedEvents.persistEvents(AppEventsLogger.applicationContext, appEventsLogger$AccessTokenAppIdPair, appEventsLogger$SessionEventsState);
                                            }
                                            if (result != AppEventsLogger$FlushResult.SUCCESS && appEventsLogger$FlushStatistics.result != AppEventsLogger$FlushResult.NO_CONNECTIVITY) {
                                                appEventsLogger$FlushStatistics.result = (AppEventsLogger$FlushResult)result;
                                            }
                                            return;
                                        }
                                        break Label_0201;
                                        format = String.format("Failed:\n  Response: %s\n  Error %s", result.toString(), error.toString());
                                        result = (Response)AppEventsLogger$FlushResult.SERVER_ERROR;
                                        break;
                                    }
                                    catch (JSONException ex) {
                                        final String string = "<Can't encode events for debug logging>";
                                        continue Label_0111_Outer;
                                    }
                                    break;
                                }
                                final boolean b = false;
                                continue;
                            }
                        }
                    }
                }
                if (Settings.isLoggingBehaviorEnabled(LoggingBehavior.APP_EVENTS)) {
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    private static void initializeTimersIfNeeded() {
        Object staticLock = AppEventsLogger.staticLock;
        synchronized (staticLock) {
            if (AppEventsLogger.backgroundExecutor != null) {
                return;
            }
            AppEventsLogger.backgroundExecutor = new ScheduledThreadPoolExecutor(1);
            // monitorexit(staticLock)
            staticLock = new AppEventsLogger$3();
            AppEventsLogger.backgroundExecutor.scheduleAtFixedRate((Runnable)staticLock, 0L, 15L, TimeUnit.SECONDS);
            staticLock = new AppEventsLogger$4();
            AppEventsLogger.backgroundExecutor.scheduleAtFixedRate((Runnable)staticLock, 0L, 86400L, TimeUnit.SECONDS);
        }
    }
    
    private void logAppSessionResumeEvent(final long n, final String s) {
        AppEventsLogger$PersistedAppSessionInfo.onResume(AppEventsLogger.applicationContext, this.accessTokenAppId, this, n, s);
    }
    
    private void logAppSessionSuspendEvent(final long n) {
        AppEventsLogger$PersistedAppSessionInfo.onSuspend(AppEventsLogger.applicationContext, this.accessTokenAppId, this, n);
    }
    
    private static void logEvent(final Context context, final AppEventsLogger$AppEvent appEventsLogger$AppEvent, final AppEventsLogger$AccessTokenAppIdPair appEventsLogger$AccessTokenAppIdPair) {
        Settings.getExecutor().execute(new AppEventsLogger$5(context, appEventsLogger$AccessTokenAppIdPair, appEventsLogger$AppEvent));
    }
    
    private void logEvent(final String s, final Double n, final Bundle bundle, final boolean b) {
        logEvent(this.context, new AppEventsLogger$AppEvent(this.context, s, n, bundle, b), this.accessTokenAppId);
    }
    
    public static AppEventsLogger newLogger(final Context context) {
        return new AppEventsLogger(context, null, null);
    }
    
    public static AppEventsLogger newLogger(final Context context, final Session session) {
        return new AppEventsLogger(context, null, session);
    }
    
    public static AppEventsLogger newLogger(final Context context, final String s) {
        return new AppEventsLogger(context, s, null);
    }
    
    public static AppEventsLogger newLogger(final Context context, final String s, final Session session) {
        return new AppEventsLogger(context, s, session);
    }
    
    private static void notifyDeveloperError(final String s) {
        Logger.log(LoggingBehavior.DEVELOPER_ERRORS, "AppEvents", s);
    }
    
    public static void onContextStop() {
        AppEventsLogger$PersistedEvents.persistEvents(AppEventsLogger.applicationContext, AppEventsLogger.stateMap);
    }
    
    static void resetSourceApplication() {
        AppEventsLogger.sourceApplication = null;
        AppEventsLogger.isOpenedByApplink = false;
    }
    
    public static void setFlushBehavior(final AppEventsLogger$FlushBehavior flushBehavior) {
        synchronized (AppEventsLogger.staticLock) {
            AppEventsLogger.flushBehavior = flushBehavior;
        }
    }
    
    @Deprecated
    public static void setLimitEventUsage(final Context context, final boolean b) {
        Settings.setLimitEventAndDataUsage(context, b);
    }
    
    private static void setSourceApplication(final Activity activity) {
        final ComponentName callingActivity = activity.getCallingActivity();
        if (callingActivity != null) {
            final String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                resetSourceApplication();
                return;
            }
            AppEventsLogger.sourceApplication = packageName;
        }
        final Intent intent = activity.getIntent();
        if (intent == null || intent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)) {
            resetSourceApplication();
            return;
        }
        final Bundle appLinkData = AppLinks.getAppLinkData(intent);
        if (appLinkData == null) {
            resetSourceApplication();
            return;
        }
        AppEventsLogger.isOpenedByApplink = true;
        final Bundle bundle = appLinkData.getBundle("referer_app_link");
        if (bundle == null) {
            AppEventsLogger.sourceApplication = null;
            return;
        }
        AppEventsLogger.sourceApplication = bundle.getString("package");
        intent.putExtra("_fbSourceApplicationHasBeenSet", true);
    }
    
    static void setSourceApplication(final String sourceApplication, final boolean isOpenedByApplink) {
        AppEventsLogger.sourceApplication = sourceApplication;
        AppEventsLogger.isOpenedByApplink = isOpenedByApplink;
    }
    
    public void flush() {
        flush(AppEventsLogger$FlushReason.EXPLICIT);
    }
    
    public String getApplicationId() {
        return this.accessTokenAppId.getApplicationId();
    }
    
    boolean isValidForSession(final Session session) {
        return this.accessTokenAppId.equals(new AppEventsLogger$AccessTokenAppIdPair(session));
    }
    
    public void logEvent(final String s) {
        this.logEvent(s, null);
    }
    
    public void logEvent(final String s, final double n) {
        this.logEvent(s, n, null);
    }
    
    public void logEvent(final String s, final double n, final Bundle bundle) {
        this.logEvent(s, n, bundle, false);
    }
    
    public void logEvent(final String s, final Bundle bundle) {
        this.logEvent(s, null, bundle, false);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency) {
        this.logPurchase(bigDecimal, currency, null);
    }
    
    public void logPurchase(final BigDecimal bigDecimal, final Currency currency, final Bundle bundle) {
        if (bigDecimal == null) {
            notifyDeveloperError("purchaseAmount cannot be null");
            return;
        }
        if (currency == null) {
            notifyDeveloperError("currency cannot be null");
            return;
        }
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("fb_currency", currency.getCurrencyCode());
        this.logEvent("fb_mobile_purchase", bigDecimal.doubleValue(), bundle2);
        eagerFlush();
    }
    
    public void logSdkEvent(final String s, final Double n, final Bundle bundle) {
        this.logEvent(s, n, bundle, true);
    }
}
