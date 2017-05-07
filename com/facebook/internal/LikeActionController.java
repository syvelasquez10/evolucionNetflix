// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import java.io.Closeable;
import java.io.IOException;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import com.facebook.Settings;
import com.facebook.SessionState;
import android.os.Looper;
import com.facebook.widget.FacebookDialog$PendingCall;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.FacebookDialog$Callback;
import com.facebook.RequestBatch$Callback;
import com.facebook.RequestBatch;
import org.json.JSONException;
import android.util.Log;
import org.json.JSONObject;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.FacebookRequestError;
import android.app.Activity;
import android.content.Intent;
import com.facebook.Session;
import java.util.UUID;
import android.os.Bundle;
import android.content.Context;
import com.facebook.AppEventsLogger;
import android.os.Handler;
import java.util.concurrent.ConcurrentHashMap;

public class LikeActionController
{
    public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_ERROR = "com.facebook.sdk.LikeActionController.DID_ERROR";
    public static final String ACTION_LIKE_ACTION_CONTROLLER_DID_RESET = "com.facebook.sdk.LikeActionController.DID_RESET";
    public static final String ACTION_LIKE_ACTION_CONTROLLER_UPDATED = "com.facebook.sdk.LikeActionController.UPDATED";
    public static final String ACTION_OBJECT_ID_KEY = "com.facebook.sdk.LikeActionController.OBJECT_ID";
    private static final int ERROR_CODE_OBJECT_ALREADY_LIKED = 3501;
    public static final String ERROR_INVALID_OBJECT_ID = "Invalid Object Id";
    private static final String JSON_BOOL_IS_OBJECT_LIKED_KEY = "is_object_liked";
    private static final String JSON_BUNDLE_PENDING_CALL_ANALYTICS_BUNDLE = "pending_call_analytics_bundle";
    private static final String JSON_INT_VERSION_KEY = "com.facebook.internal.LikeActionController.version";
    private static final String JSON_STRING_LIKE_COUNT_WITHOUT_LIKE_KEY = "like_count_string_without_like";
    private static final String JSON_STRING_LIKE_COUNT_WITH_LIKE_KEY = "like_count_string_with_like";
    private static final String JSON_STRING_OBJECT_ID_KEY = "object_id";
    private static final String JSON_STRING_PENDING_CALL_ID_KEY = "pending_call_id";
    private static final String JSON_STRING_SOCIAL_SENTENCE_WITHOUT_LIKE_KEY = "social_sentence_without_like";
    private static final String JSON_STRING_SOCIAL_SENTENCE_WITH_LIKE_KEY = "social_sentence_with_like";
    private static final String JSON_STRING_UNLIKE_TOKEN_KEY = "unlike_token";
    private static final String LIKE_ACTION_CONTROLLER_STORE = "com.facebook.LikeActionController.CONTROLLER_STORE_KEY";
    private static final String LIKE_ACTION_CONTROLLER_STORE_OBJECT_SUFFIX_KEY = "OBJECT_SUFFIX";
    private static final String LIKE_ACTION_CONTROLLER_STORE_PENDING_OBJECT_ID_KEY = "PENDING_CONTROLLER_KEY";
    private static final int LIKE_ACTION_CONTROLLER_VERSION = 2;
    private static final String LIKE_DIALOG_RESPONSE_LIKE_COUNT_STRING_KEY = "like_count_string";
    private static final String LIKE_DIALOG_RESPONSE_OBJECT_IS_LIKED_KEY = "object_is_liked";
    private static final String LIKE_DIALOG_RESPONSE_SOCIAL_SENTENCE_KEY = "social_sentence";
    private static final String LIKE_DIALOG_RESPONSE_UNLIKE_TOKEN_KEY = "unlike_token";
    private static final int MAX_CACHE_SIZE = 128;
    private static final int MAX_OBJECT_SUFFIX = 1000;
    private static final String TAG;
    private static final ConcurrentHashMap<String, LikeActionController> cache;
    private static FileLruCache controllerDiskCache;
    private static WorkQueue diskIOWorkQueue;
    private static Handler handler;
    private static boolean isInitialized;
    private static boolean isPendingBroadcastReset;
    private static WorkQueue mruCacheWorkQueue;
    private static String objectIdForPendingController;
    private static volatile int objectSuffix;
    private AppEventsLogger appEventsLogger;
    private Context context;
    private boolean isObjectLiked;
    private boolean isObjectLikedOnServer;
    private boolean isPendingLikeOrUnlike;
    private String likeCountStringWithLike;
    private String likeCountStringWithoutLike;
    private String objectId;
    private boolean objectIsPage;
    private Bundle pendingCallAnalyticsBundle;
    private UUID pendingCallId;
    private Session session;
    private String socialSentenceWithLike;
    private String socialSentenceWithoutLike;
    private String unlikeToken;
    private String verifiedObjectId;
    
    static {
        TAG = LikeActionController.class.getSimpleName();
        cache = new ConcurrentHashMap<String, LikeActionController>();
        LikeActionController.mruCacheWorkQueue = new WorkQueue(1);
        LikeActionController.diskIOWorkQueue = new WorkQueue(1);
    }
    
    private LikeActionController(final Context context, final Session session, final String objectId) {
        this.context = context;
        this.session = session;
        this.objectId = objectId;
        this.appEventsLogger = AppEventsLogger.newLogger(context, session);
    }
    
    private static void broadcastAction(final Context context, final LikeActionController likeActionController, final String s) {
        broadcastAction(context, likeActionController, s, null);
    }
    
    private static void broadcastAction(final Context context, final LikeActionController likeActionController, final String s, final Bundle bundle) {
        final Intent intent = new Intent(s);
        Bundle bundle2 = bundle;
        if (likeActionController != null) {
            if ((bundle2 = bundle) == null) {
                bundle2 = new Bundle();
            }
            bundle2.putString("com.facebook.sdk.LikeActionController.OBJECT_ID", likeActionController.getObjectId());
        }
        if (bundle2 != null) {
            intent.putExtras(bundle2);
        }
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
    }
    
    private boolean canUseOGPublish(final boolean b) {
        return !this.objectIsPage && this.verifiedObjectId != null && this.session != null && this.session.getPermissions() != null && this.session.getPermissions().contains("publish_actions") && (b || !Utility.isNullOrEmpty(this.unlikeToken));
    }
    
    private static void createControllerForObjectId(final Context context, final String s, final LikeActionController$CreationCallback likeActionController$CreationCallback) {
        final LikeActionController controllerFromInMemoryCache = getControllerFromInMemoryCache(s);
        if (controllerFromInMemoryCache != null) {
            invokeCallbackWithController(likeActionController$CreationCallback, controllerFromInMemoryCache);
            return;
        }
        LikeActionController deserializeFromDiskSynchronously;
        if ((deserializeFromDiskSynchronously = deserializeFromDiskSynchronously(context, s)) == null) {
            deserializeFromDiskSynchronously = new LikeActionController(context, Session.getActiveSession(), s);
            serializeToDiskAsync(deserializeFromDiskSynchronously);
        }
        putControllerInMemoryCache(s, deserializeFromDiskSynchronously);
        LikeActionController.handler.post((Runnable)new LikeActionController$2(deserializeFromDiskSynchronously));
        invokeCallbackWithController(likeActionController$CreationCallback, deserializeFromDiskSynchronously);
    }
    
    private static LikeActionController deserializeFromDiskSynchronously(final Context p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: aconst_null    
        //     4: astore          5
        //     6: aload_1        
        //     7: invokestatic    com/facebook/internal/LikeActionController.getCacheKeyForObjectId:(Ljava/lang/String;)Ljava/lang/String;
        //    10: astore_1       
        //    11: getstatic       com/facebook/internal/LikeActionController.controllerDiskCache:Lcom/facebook/internal/FileLruCache;
        //    14: aload_1        
        //    15: invokevirtual   com/facebook/internal/FileLruCache.get:(Ljava/lang/String;)Ljava/io/InputStream;
        //    18: astore_1       
        //    19: aload           5
        //    21: astore_3       
        //    22: aload_1        
        //    23: ifnull          56
        //    26: aload_1        
        //    27: astore_2       
        //    28: aload_1        
        //    29: invokestatic    com/facebook/internal/Utility.readStreamToString:(Ljava/io/InputStream;)Ljava/lang/String;
        //    32: astore          6
        //    34: aload           5
        //    36: astore_3       
        //    37: aload_1        
        //    38: astore_2       
        //    39: aload           6
        //    41: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    44: ifne            56
        //    47: aload_1        
        //    48: astore_2       
        //    49: aload_0        
        //    50: aload           6
        //    52: invokestatic    com/facebook/internal/LikeActionController.deserializeFromJson:(Landroid/content/Context;Ljava/lang/String;)Lcom/facebook/internal/LikeActionController;
        //    55: astore_3       
        //    56: aload_3        
        //    57: astore_0       
        //    58: aload_1        
        //    59: ifnull          68
        //    62: aload_1        
        //    63: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    66: aload_3        
        //    67: astore_0       
        //    68: aload_0        
        //    69: areturn        
        //    70: astore_0       
        //    71: aconst_null    
        //    72: astore_1       
        //    73: aload_1        
        //    74: astore_2       
        //    75: getstatic       com/facebook/internal/LikeActionController.TAG:Ljava/lang/String;
        //    78: ldc_w           "Unable to deserialize controller from disk"
        //    81: aload_0        
        //    82: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    85: pop            
        //    86: aload           4
        //    88: astore_0       
        //    89: aload_1        
        //    90: ifnull          68
        //    93: aload_1        
        //    94: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    97: aconst_null    
        //    98: areturn        
        //    99: astore_0       
        //   100: aconst_null    
        //   101: astore_2       
        //   102: aload_2        
        //   103: ifnull          110
        //   106: aload_2        
        //   107: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //   110: aload_0        
        //   111: athrow         
        //   112: astore_0       
        //   113: goto            102
        //   116: astore_0       
        //   117: goto            73
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      19     70     73     Ljava/io/IOException;
        //  6      19     99     102    Any
        //  28     34     116    120    Ljava/io/IOException;
        //  28     34     112    116    Any
        //  39     47     116    120    Ljava/io/IOException;
        //  39     47     112    116    Any
        //  49     56     116    120    Ljava/io/IOException;
        //  49     56     112    116    Any
        //  75     86     112    116    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0056:
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
    
    private static LikeActionController deserializeFromJson(final Context context, final String s) {
        try {
            final JSONObject jsonObject = new JSONObject(s);
            if (jsonObject.optInt("com.facebook.internal.LikeActionController.version", -1) != 2) {
                return null;
            }
            final LikeActionController likeActionController = new LikeActionController(context, Session.getActiveSession(), jsonObject.getString("object_id"));
            likeActionController.likeCountStringWithLike = jsonObject.optString("like_count_string_with_like", (String)null);
            likeActionController.likeCountStringWithoutLike = jsonObject.optString("like_count_string_without_like", (String)null);
            likeActionController.socialSentenceWithLike = jsonObject.optString("social_sentence_with_like", (String)null);
            likeActionController.socialSentenceWithoutLike = jsonObject.optString("social_sentence_without_like", (String)null);
            likeActionController.isObjectLiked = jsonObject.optBoolean("is_object_liked");
            likeActionController.unlikeToken = jsonObject.optString("unlike_token", (String)null);
            final String optString = jsonObject.optString("pending_call_id", (String)null);
            if (!Utility.isNullOrEmpty(optString)) {
                likeActionController.pendingCallId = UUID.fromString(optString);
            }
            final JSONObject optJSONObject = jsonObject.optJSONObject("pending_call_analytics_bundle");
            LikeActionController likeActionController2 = likeActionController;
            if (optJSONObject != null) {
                likeActionController.pendingCallAnalyticsBundle = BundleJSONConverter.convertToBundle(optJSONObject);
                likeActionController2 = likeActionController;
            }
            return likeActionController2;
        }
        catch (JSONException ex) {
            Log.e(LikeActionController.TAG, "Unable to deserialize controller from JSON", (Throwable)ex);
            return null;
        }
    }
    
    private void fetchVerifiedObjectId(final LikeActionController$RequestCompletionCallback likeActionController$RequestCompletionCallback) {
        if (!Utility.isNullOrEmpty(this.verifiedObjectId)) {
            if (likeActionController$RequestCompletionCallback != null) {
                likeActionController$RequestCompletionCallback.onComplete();
            }
            return;
        }
        final LikeActionController$GetOGObjectIdRequestWrapper likeActionController$GetOGObjectIdRequestWrapper = new LikeActionController$GetOGObjectIdRequestWrapper(this, this.objectId);
        final LikeActionController$GetPageIdRequestWrapper likeActionController$GetPageIdRequestWrapper = new LikeActionController$GetPageIdRequestWrapper(this, this.objectId);
        final RequestBatch requestBatch = new RequestBatch();
        likeActionController$GetOGObjectIdRequestWrapper.addToBatch(requestBatch);
        likeActionController$GetPageIdRequestWrapper.addToBatch(requestBatch);
        requestBatch.addCallback(new LikeActionController$10(this, likeActionController$GetOGObjectIdRequestWrapper, likeActionController$GetPageIdRequestWrapper, likeActionController$RequestCompletionCallback));
        requestBatch.executeAsync();
    }
    
    private static String getCacheKeyForObjectId(final String s) {
        final String s2 = null;
        final Session activeSession = Session.getActiveSession();
        String accessToken = s2;
        if (activeSession != null) {
            accessToken = s2;
            if (activeSession.isOpened()) {
                accessToken = activeSession.getAccessToken();
            }
        }
        String md5hash;
        if ((md5hash = accessToken) != null) {
            md5hash = Utility.md5hash(accessToken);
        }
        return String.format("%s|%s|com.fb.sdk.like|%d", s, Utility.coerceValueIfNullOrEmpty(md5hash, ""), LikeActionController.objectSuffix);
    }
    
    public static void getControllerForObjectId(final Context context, final String s, final LikeActionController$CreationCallback likeActionController$CreationCallback) {
        if (!LikeActionController.isInitialized) {
            performFirstInitialize(context);
        }
        final LikeActionController controllerFromInMemoryCache = getControllerFromInMemoryCache(s);
        if (controllerFromInMemoryCache != null) {
            invokeCallbackWithController(likeActionController$CreationCallback, controllerFromInMemoryCache);
            return;
        }
        LikeActionController.diskIOWorkQueue.addActiveWorkItem(new LikeActionController$CreateLikeActionControllerWorkItem(context, s, likeActionController$CreationCallback));
    }
    
    private static LikeActionController getControllerFromInMemoryCache(String cacheKeyForObjectId) {
        cacheKeyForObjectId = getCacheKeyForObjectId(cacheKeyForObjectId);
        final LikeActionController likeActionController = LikeActionController.cache.get(cacheKeyForObjectId);
        if (likeActionController != null) {
            LikeActionController.mruCacheWorkQueue.addActiveWorkItem(new LikeActionController$MRUCacheWorkItem(cacheKeyForObjectId, false));
        }
        return likeActionController;
    }
    
    private FacebookDialog$Callback getFacebookDialogCallback(final Bundle bundle) {
        return new LikeActionController$5(this, bundle);
    }
    
    public static boolean handleOnActivityResult(final Context context, final int n, final int n2, final Intent intent) {
        final UUID callIdFromIntent = NativeProtocol.getCallIdFromIntent(intent);
        if (callIdFromIntent != null) {
            if (Utility.isNullOrEmpty(LikeActionController.objectIdForPendingController)) {
                LikeActionController.objectIdForPendingController = context.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getString("PENDING_CONTROLLER_KEY", (String)null);
            }
            if (!Utility.isNullOrEmpty(LikeActionController.objectIdForPendingController)) {
                getControllerForObjectId(context, LikeActionController.objectIdForPendingController, new LikeActionController$1(n, n2, intent, callIdFromIntent));
                return true;
            }
        }
        return false;
    }
    
    private static void invokeCallbackWithController(final LikeActionController$CreationCallback likeActionController$CreationCallback, final LikeActionController likeActionController) {
        if (likeActionController$CreationCallback == null) {
            return;
        }
        LikeActionController.handler.post((Runnable)new LikeActionController$3(likeActionController$CreationCallback, likeActionController));
    }
    
    private void logAppEventForError(final String s, Bundle bundle) {
        bundle = new Bundle(bundle);
        bundle.putString("object_id", this.objectId);
        bundle.putString("current_action", s);
        this.appEventsLogger.logSdkEvent("fb_like_control_error", null, bundle);
    }
    
    private void logAppEventForError(final String s, final FacebookRequestError facebookRequestError) {
        final Bundle bundle = new Bundle();
        if (facebookRequestError != null) {
            final JSONObject requestResult = facebookRequestError.getRequestResult();
            if (requestResult != null) {
                bundle.putString("error", requestResult.toString());
            }
        }
        this.logAppEventForError(s, bundle);
    }
    
    private boolean onActivityResult(final int n, final int n2, final Intent intent, final UUID uuid) {
        if (this.pendingCallId != null && this.pendingCallId.equals(uuid)) {
            final FacebookDialog$PendingCall pendingCallById = PendingCallStore.getInstance().getPendingCallById(this.pendingCallId);
            if (pendingCallById != null) {
                FacebookDialog.handleActivityResult(this.context, pendingCallById, n, intent, this.getFacebookDialogCallback(this.pendingCallAnalyticsBundle));
                this.stopTrackingPendingCall();
                return true;
            }
        }
        return false;
    }
    
    private static void performFirstInitialize(final Context context) {
        synchronized (LikeActionController.class) {
            if (!LikeActionController.isInitialized) {
                LikeActionController.handler = new Handler(Looper.getMainLooper());
                LikeActionController.objectSuffix = context.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).getInt("OBJECT_SUFFIX", 1);
                LikeActionController.controllerDiskCache = new FileLruCache(context, LikeActionController.TAG, new FileLruCache$Limits());
                registerSessionBroadcastReceivers(context);
                LikeActionController.isInitialized = true;
            }
        }
    }
    
    private void performLikeOrUnlike(final Activity activity, final boolean b, final Bundle bundle) {
        if (!this.canUseOGPublish(b)) {
            this.presentLikeDialog(activity, bundle);
            return;
        }
        if (b) {
            this.publishLikeAsync(activity, bundle);
            return;
        }
        this.publishUnlikeAsync(activity, bundle);
    }
    
    private void presentLikeDialog(final Activity activity, final Bundle bundle) {
        final LikeActionController$LikeDialogBuilder likeActionController$LikeDialogBuilder = new LikeActionController$LikeDialogBuilder(activity, this.objectId);
        if (likeActionController$LikeDialogBuilder.canPresent()) {
            this.trackPendingCall(likeActionController$LikeDialogBuilder.build().present(), bundle);
            this.appEventsLogger.logSdkEvent("fb_like_control_did_present_dialog", null, bundle);
        }
        else {
            final String webFallbackUrl = likeActionController$LikeDialogBuilder.getWebFallbackUrl();
            if (!Utility.isNullOrEmpty(webFallbackUrl) && FacebookWebFallbackDialog.presentWebFallback((Context)activity, webFallbackUrl, likeActionController$LikeDialogBuilder.getApplicationId(), likeActionController$LikeDialogBuilder.getAppCall(), this.getFacebookDialogCallback(bundle))) {
                this.appEventsLogger.logSdkEvent("fb_like_control_did_present_fallback_dialog", null, bundle);
            }
        }
    }
    
    private void publishLikeAsync(final Activity activity, final Bundle bundle) {
        this.isPendingLikeOrUnlike = true;
        this.fetchVerifiedObjectId(new LikeActionController$6(this, activity, bundle));
    }
    
    private void publishUnlikeAsync(final Activity activity, final Bundle bundle) {
        this.isPendingLikeOrUnlike = true;
        final RequestBatch requestBatch = new RequestBatch();
        final LikeActionController$PublishUnlikeRequestWrapper likeActionController$PublishUnlikeRequestWrapper = new LikeActionController$PublishUnlikeRequestWrapper(this, this.unlikeToken);
        likeActionController$PublishUnlikeRequestWrapper.addToBatch(requestBatch);
        requestBatch.addCallback(new LikeActionController$7(this, likeActionController$PublishUnlikeRequestWrapper, activity, bundle));
        requestBatch.executeAsync();
    }
    
    private static void putControllerInMemoryCache(String cacheKeyForObjectId, final LikeActionController likeActionController) {
        cacheKeyForObjectId = getCacheKeyForObjectId(cacheKeyForObjectId);
        LikeActionController.mruCacheWorkQueue.addActiveWorkItem(new LikeActionController$MRUCacheWorkItem(cacheKeyForObjectId, true));
        LikeActionController.cache.put(cacheKeyForObjectId, likeActionController);
    }
    
    private void refreshStatusAsync() {
        if (this.session == null || this.session.isClosed() || SessionState.CREATED.equals(this.session.getState())) {
            this.refreshStatusViaService();
            return;
        }
        this.fetchVerifiedObjectId(new LikeActionController$8(this));
    }
    
    private void refreshStatusViaService() {
        final LikeStatusClient likeStatusClient = new LikeStatusClient(this.context, Settings.getApplicationId(), this.objectId);
        if (!likeStatusClient.start()) {
            return;
        }
        likeStatusClient.setCompletedListener(new LikeActionController$9(this));
    }
    
    private static void registerSessionBroadcastReceivers(final Context context) {
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(context);
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_UNSET");
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_CLOSED");
        intentFilter.addAction("com.facebook.sdk.ACTIVE_SESSION_OPENED");
        instance.registerReceiver(new LikeActionController$4(), intentFilter);
    }
    
    private static void serializeToDiskAsync(final LikeActionController likeActionController) {
        final String serializeToJson = serializeToJson(likeActionController);
        final String cacheKeyForObjectId = getCacheKeyForObjectId(likeActionController.objectId);
        if (!Utility.isNullOrEmpty(serializeToJson) && !Utility.isNullOrEmpty(cacheKeyForObjectId)) {
            LikeActionController.diskIOWorkQueue.addActiveWorkItem(new LikeActionController$SerializeToDiskWorkItem(cacheKeyForObjectId, serializeToJson));
        }
    }
    
    private static void serializeToDiskSynchronously(final String s, final String s2) {
        Closeable closeable = null;
        Closeable openPutStream = null;
        try {
            ((OutputStream)(closeable = (openPutStream = LikeActionController.controllerDiskCache.openPutStream(s)))).write(s2.getBytes());
        }
        catch (IOException ex) {
            closeable = openPutStream;
            Log.e(LikeActionController.TAG, "Unable to serialize controller to disk", (Throwable)ex);
        }
        finally {
            if (closeable != null) {
                Utility.closeQuietly(closeable);
            }
        }
    }
    
    private static String serializeToJson(final LikeActionController likeActionController) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("com.facebook.internal.LikeActionController.version", 2);
            jsonObject.put("object_id", (Object)likeActionController.objectId);
            jsonObject.put("like_count_string_with_like", (Object)likeActionController.likeCountStringWithLike);
            jsonObject.put("like_count_string_without_like", (Object)likeActionController.likeCountStringWithoutLike);
            jsonObject.put("social_sentence_with_like", (Object)likeActionController.socialSentenceWithLike);
            jsonObject.put("social_sentence_without_like", (Object)likeActionController.socialSentenceWithoutLike);
            jsonObject.put("is_object_liked", likeActionController.isObjectLiked);
            jsonObject.put("unlike_token", (Object)likeActionController.unlikeToken);
            if (likeActionController.pendingCallId != null) {
                jsonObject.put("pending_call_id", (Object)likeActionController.pendingCallId.toString());
            }
            if (likeActionController.pendingCallAnalyticsBundle != null) {
                final JSONObject convertToJSON = BundleJSONConverter.convertToJSON(likeActionController.pendingCallAnalyticsBundle);
                if (convertToJSON != null) {
                    jsonObject.put("pending_call_analytics_bundle", (Object)convertToJSON);
                }
            }
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e(LikeActionController.TAG, "Unable to serialize controller to JSON", (Throwable)ex);
            return null;
        }
    }
    
    private void stopTrackingPendingCall() {
        PendingCallStore.getInstance().stopTrackingPendingCall(this.pendingCallId);
        this.pendingCallId = null;
        this.pendingCallAnalyticsBundle = null;
        this.storeObjectIdForPendingController(null);
    }
    
    private void storeObjectIdForPendingController(final String objectIdForPendingController) {
        LikeActionController.objectIdForPendingController = objectIdForPendingController;
        this.context.getSharedPreferences("com.facebook.LikeActionController.CONTROLLER_STORE_KEY", 0).edit().putString("PENDING_CONTROLLER_KEY", LikeActionController.objectIdForPendingController).apply();
    }
    
    private void toggleAgainIfNeeded(final Activity activity, final Bundle bundle) {
        if (this.isObjectLiked != this.isObjectLikedOnServer) {
            this.performLikeOrUnlike(activity, this.isObjectLiked, bundle);
        }
    }
    
    private void trackPendingCall(final FacebookDialog$PendingCall facebookDialog$PendingCall, final Bundle pendingCallAnalyticsBundle) {
        PendingCallStore.getInstance().trackPendingCall(facebookDialog$PendingCall);
        this.pendingCallId = facebookDialog$PendingCall.getCallId();
        this.storeObjectIdForPendingController(this.objectId);
        this.pendingCallAnalyticsBundle = pendingCallAnalyticsBundle;
        serializeToDiskAsync(this);
    }
    
    private void updateState(final boolean isObjectLiked, String coerceValueIfNullOrEmpty, String coerceValueIfNullOrEmpty2, String coerceValueIfNullOrEmpty3, String coerceValueIfNullOrEmpty4, String coerceValueIfNullOrEmpty5) {
        coerceValueIfNullOrEmpty = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty, null);
        coerceValueIfNullOrEmpty2 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty2, null);
        coerceValueIfNullOrEmpty3 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty3, null);
        coerceValueIfNullOrEmpty4 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty4, null);
        coerceValueIfNullOrEmpty5 = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty5, null);
        int n;
        if (isObjectLiked != this.isObjectLiked || !Utility.areObjectsEqual(coerceValueIfNullOrEmpty, this.likeCountStringWithLike) || !Utility.areObjectsEqual(coerceValueIfNullOrEmpty2, this.likeCountStringWithoutLike) || !Utility.areObjectsEqual(coerceValueIfNullOrEmpty3, this.socialSentenceWithLike) || !Utility.areObjectsEqual(coerceValueIfNullOrEmpty4, this.socialSentenceWithoutLike) || !Utility.areObjectsEqual(coerceValueIfNullOrEmpty5, this.unlikeToken)) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n == 0) {
            return;
        }
        this.isObjectLiked = isObjectLiked;
        this.likeCountStringWithLike = coerceValueIfNullOrEmpty;
        this.likeCountStringWithoutLike = coerceValueIfNullOrEmpty2;
        this.socialSentenceWithLike = coerceValueIfNullOrEmpty3;
        this.socialSentenceWithoutLike = coerceValueIfNullOrEmpty4;
        this.unlikeToken = coerceValueIfNullOrEmpty5;
        serializeToDiskAsync(this);
        broadcastAction(this.context, this, "com.facebook.sdk.LikeActionController.UPDATED");
    }
    
    public String getLikeCountString() {
        if (this.isObjectLiked) {
            return this.likeCountStringWithLike;
        }
        return this.likeCountStringWithoutLike;
    }
    
    public String getObjectId() {
        return this.objectId;
    }
    
    public String getSocialSentence() {
        if (this.isObjectLiked) {
            return this.socialSentenceWithLike;
        }
        return this.socialSentenceWithoutLike;
    }
    
    public boolean isObjectLiked() {
        return this.isObjectLiked;
    }
    
    public void toggleLike(final Activity activity, final Bundle bundle) {
        this.appEventsLogger.logSdkEvent("fb_like_control_did_tap", null, bundle);
        final boolean b = !this.isObjectLiked;
        if (this.canUseOGPublish(b)) {
            this.updateState(b, this.likeCountStringWithLike, this.likeCountStringWithoutLike, this.socialSentenceWithLike, this.socialSentenceWithoutLike, this.unlikeToken);
            if (this.isPendingLikeOrUnlike) {
                this.appEventsLogger.logSdkEvent("fb_like_control_did_undo_quickly", null, bundle);
                return;
            }
        }
        this.performLikeOrUnlike(activity, b, bundle);
    }
}
