// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.Iterator;
import android.os.Parcelable;
import org.json.JSONArray;
import com.facebook.internal.Utility;
import org.json.JSONException;
import android.util.Log;
import android.content.Intent;
import com.facebook.internal.Validate;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import org.json.JSONObject;
import android.os.Bundle;

public class AppLinkData
{
    private static final String APPLINK_BRIDGE_ARGS_KEY = "bridge_args";
    private static final String APPLINK_METHOD_ARGS_KEY = "method_args";
    private static final String APPLINK_VERSION_KEY = "version";
    public static final String ARGUMENTS_NATIVE_CLASS_KEY = "com.facebook.platform.APPLINK_NATIVE_CLASS";
    public static final String ARGUMENTS_NATIVE_URL = "com.facebook.platform.APPLINK_NATIVE_URL";
    public static final String ARGUMENTS_REFERER_DATA_KEY = "referer_data";
    public static final String ARGUMENTS_TAPTIME_KEY = "com.facebook.platform.APPLINK_TAP_TIME_UTC";
    private static final String BRIDGE_ARGS_METHOD_KEY = "method";
    private static final String BUNDLE_AL_APPLINK_DATA_KEY = "al_applink_data";
    static final String BUNDLE_APPLINK_ARGS_KEY = "com.facebook.platform.APPLINK_ARGS";
    private static final String DEFERRED_APP_LINK_ARGS_FIELD = "applink_args";
    private static final String DEFERRED_APP_LINK_CLASS_FIELD = "applink_class";
    private static final String DEFERRED_APP_LINK_CLICK_TIME_FIELD = "click_time";
    private static final String DEFERRED_APP_LINK_EVENT = "DEFERRED_APP_LINK";
    private static final String DEFERRED_APP_LINK_PATH = "%s/activities";
    private static final String DEFERRED_APP_LINK_URL_FIELD = "applink_url";
    private static final String METHOD_ARGS_REF_KEY = "ref";
    private static final String METHOD_ARGS_TARGET_URL_KEY = "target_url";
    private static final String REFERER_DATA_REF_KEY = "fb_ref";
    private static final String TAG;
    private Bundle argumentBundle;
    private JSONObject arguments;
    private String ref;
    private Uri targetUri;
    
    static {
        TAG = AppLinkData.class.getCanonicalName();
    }
    
    public static AppLinkData createFromActivity(final Activity activity) {
        Validate.notNull(activity, "activity");
        final Intent intent = activity.getIntent();
        AppLinkData appLinkData;
        if (intent == null) {
            appLinkData = null;
        }
        else {
            AppLinkData appLinkData2;
            if ((appLinkData2 = createFromAlApplinkData(intent)) == null) {
                appLinkData2 = createFromJson(intent.getStringExtra("com.facebook.platform.APPLINK_ARGS"));
            }
            if ((appLinkData = appLinkData2) == null) {
                return createFromUri(intent.getData());
            }
        }
        return appLinkData;
    }
    
    private static AppLinkData createFromAlApplinkData(final Intent intent) {
        final Bundle bundleExtra = intent.getBundleExtra("al_applink_data");
        if (bundleExtra == null) {
            return null;
        }
        final AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = intent.getData();
        if (appLinkData.targetUri == null) {
            final String string = bundleExtra.getString("target_url");
            if (string != null) {
                appLinkData.targetUri = Uri.parse(string);
            }
        }
        appLinkData.argumentBundle = bundleExtra;
        appLinkData.arguments = null;
        final Bundle bundle = bundleExtra.getBundle("referer_data");
        if (bundle != null) {
            appLinkData.ref = bundle.getString("fb_ref");
        }
        return appLinkData;
    }
    
    private static AppLinkData createFromJson(final String s) {
        if (s != null) {
            try {
                final JSONObject jsonObject = new JSONObject(s);
                final String string = jsonObject.getString("version");
                if (jsonObject.getJSONObject("bridge_args").getString("method").equals("applink") && string.equals("2")) {
                    final AppLinkData appLinkData = new AppLinkData();
                    appLinkData.arguments = jsonObject.getJSONObject("method_args");
                    if (appLinkData.arguments.has("ref")) {
                        appLinkData.ref = appLinkData.arguments.getString("ref");
                    }
                    else if (appLinkData.arguments.has("referer_data")) {
                        final JSONObject jsonObject2 = appLinkData.arguments.getJSONObject("referer_data");
                        if (jsonObject2.has("fb_ref")) {
                            appLinkData.ref = jsonObject2.getString("fb_ref");
                        }
                    }
                    if (appLinkData.arguments.has("target_url")) {
                        appLinkData.targetUri = Uri.parse(appLinkData.arguments.getString("target_url"));
                    }
                    appLinkData.argumentBundle = toBundle(appLinkData.arguments);
                    return appLinkData;
                }
            }
            catch (JSONException ex) {
                Log.d(AppLinkData.TAG, "Unable to parse AppLink JSON", (Throwable)ex);
                return null;
            }
            catch (FacebookException ex2) {
                Log.d(AppLinkData.TAG, "Unable to parse AppLink JSON", (Throwable)ex2);
                return null;
            }
        }
        return null;
    }
    
    private static AppLinkData createFromUri(final Uri targetUri) {
        if (targetUri == null) {
            return null;
        }
        final AppLinkData appLinkData = new AppLinkData();
        appLinkData.targetUri = targetUri;
        return appLinkData;
    }
    
    public static void fetchDeferredAppLinkData(final Context context, final AppLinkData$CompletionHandler appLinkData$CompletionHandler) {
        fetchDeferredAppLinkData(context, null, appLinkData$CompletionHandler);
    }
    
    public static void fetchDeferredAppLinkData(Context applicationContext, final String s, final AppLinkData$CompletionHandler appLinkData$CompletionHandler) {
        Validate.notNull(applicationContext, "context");
        Validate.notNull(appLinkData$CompletionHandler, "completionHandler");
        String metadataApplicationId = s;
        if (s == null) {
            metadataApplicationId = Utility.getMetadataApplicationId(applicationContext);
        }
        Validate.notNull(metadataApplicationId, "applicationId");
        applicationContext = applicationContext.getApplicationContext();
        Settings.getExecutor().execute(new AppLinkData$1(applicationContext, metadataApplicationId, appLinkData$CompletionHandler));
    }
    
    private static void fetchDeferredAppLinkFromServer(final Context p0, final String p1, final AppLinkData$CompletionHandler p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          7
        //     3: aconst_null    
        //     4: astore          6
        //     6: invokestatic    com/facebook/model/GraphObject$Factory.create:()Lcom/facebook/model/GraphObject;
        //     9: astore          5
        //    11: aload           5
        //    13: ldc             "event"
        //    15: ldc             "DEFERRED_APP_LINK"
        //    17: invokeinterface com/facebook/model/GraphObject.setProperty:(Ljava/lang/String;Ljava/lang/Object;)V
        //    22: aload           5
        //    24: aload_0        
        //    25: invokestatic    com/facebook/internal/AttributionIdentifiers.getAttributionIdentifiers:(Landroid/content/Context;)Lcom/facebook/internal/AttributionIdentifiers;
        //    28: aload_0        
        //    29: aload_1        
        //    30: invokestatic    com/facebook/internal/Utility.getHashedDeviceAndAppID:(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
        //    33: aload_0        
        //    34: invokestatic    com/facebook/Settings.getLimitEventAndDataUsage:(Landroid/content/Context;)Z
        //    37: invokestatic    com/facebook/internal/Utility.setAppEventAttributionParameters:(Lcom/facebook/model/GraphObject;Lcom/facebook/internal/AttributionIdentifiers;Ljava/lang/String;Z)V
        //    40: aload           5
        //    42: ldc_w           "application_package_name"
        //    45: aload_0        
        //    46: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    49: invokeinterface com/facebook/model/GraphObject.setProperty:(Ljava/lang/String;Ljava/lang/Object;)V
        //    54: ldc             "%s/activities"
        //    56: iconst_1       
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: iconst_0       
        //    62: aload_1        
        //    63: aastore        
        //    64: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    67: astore_1       
        //    68: aload           7
        //    70: astore_0       
        //    71: aconst_null    
        //    72: aload_1        
        //    73: aload           5
        //    75: aconst_null    
        //    76: invokestatic    com/facebook/Request.newPostRequest:(Lcom/facebook/Session;Ljava/lang/String;Lcom/facebook/model/GraphObject;Lcom/facebook/Request$Callback;)Lcom/facebook/Request;
        //    79: invokevirtual   com/facebook/Request.executeAndWait:()Lcom/facebook/Response;
        //    82: invokevirtual   com/facebook/Response.getGraphObject:()Lcom/facebook/model/GraphObject;
        //    85: astore_1       
        //    86: aload_1        
        //    87: ifnull          378
        //    90: aload           7
        //    92: astore_0       
        //    93: aload_1        
        //    94: invokeinterface com/facebook/model/GraphObject.getInnerJSONObject:()Lorg/json/JSONObject;
        //    99: astore          5
        //   101: aload           6
        //   103: astore_1       
        //   104: aload           5
        //   106: ifnull          370
        //   109: aload           7
        //   111: astore_0       
        //   112: aload           5
        //   114: ldc             "applink_args"
        //   116: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   119: astore          10
        //   121: aload           7
        //   123: astore_0       
        //   124: aload           5
        //   126: ldc             "click_time"
        //   128: ldc2_w          -1
        //   131: invokevirtual   org/json/JSONObject.optLong:(Ljava/lang/String;J)J
        //   134: lstore_3       
        //   135: aload           7
        //   137: astore_0       
        //   138: aload           5
        //   140: ldc             "applink_class"
        //   142: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   145: astore          8
        //   147: aload           7
        //   149: astore_0       
        //   150: aload           5
        //   152: ldc             "applink_url"
        //   154: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   157: astore          9
        //   159: aload           6
        //   161: astore_1       
        //   162: aload           7
        //   164: astore_0       
        //   165: aload           10
        //   167: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   170: ifne            370
        //   173: aload           7
        //   175: astore_0       
        //   176: aload           10
        //   178: invokestatic    com/facebook/AppLinkData.createFromJson:(Ljava/lang/String;)Lcom/facebook/AppLinkData;
        //   181: astore          5
        //   183: lload_3        
        //   184: ldc2_w          -1
        //   187: lcmp           
        //   188: ifeq            245
        //   191: aload           5
        //   193: astore_0       
        //   194: aload           5
        //   196: getfield        com/facebook/AppLinkData.arguments:Lorg/json/JSONObject;
        //   199: ifnull          217
        //   202: aload           5
        //   204: astore_0       
        //   205: aload           5
        //   207: getfield        com/facebook/AppLinkData.arguments:Lorg/json/JSONObject;
        //   210: ldc             "com.facebook.platform.APPLINK_TAP_TIME_UTC"
        //   212: lload_3        
        //   213: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;J)Lorg/json/JSONObject;
        //   216: pop            
        //   217: aload           5
        //   219: astore_0       
        //   220: aload           5
        //   222: getfield        com/facebook/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   225: ifnull          245
        //   228: aload           5
        //   230: astore_0       
        //   231: aload           5
        //   233: getfield        com/facebook/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   236: ldc             "com.facebook.platform.APPLINK_TAP_TIME_UTC"
        //   238: lload_3        
        //   239: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //   242: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   245: aload           8
        //   247: ifnull          303
        //   250: aload           5
        //   252: astore_0       
        //   253: aload           5
        //   255: getfield        com/facebook/AppLinkData.arguments:Lorg/json/JSONObject;
        //   258: ifnull          277
        //   261: aload           5
        //   263: astore_0       
        //   264: aload           5
        //   266: getfield        com/facebook/AppLinkData.arguments:Lorg/json/JSONObject;
        //   269: ldc             "com.facebook.platform.APPLINK_NATIVE_CLASS"
        //   271: aload           8
        //   273: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   276: pop            
        //   277: aload           5
        //   279: astore_0       
        //   280: aload           5
        //   282: getfield        com/facebook/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   285: ifnull          303
        //   288: aload           5
        //   290: astore_0       
        //   291: aload           5
        //   293: getfield        com/facebook/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   296: ldc             "com.facebook.platform.APPLINK_NATIVE_CLASS"
        //   298: aload           8
        //   300: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   303: aload           5
        //   305: astore_1       
        //   306: aload           9
        //   308: ifnull          370
        //   311: aload           5
        //   313: astore_0       
        //   314: aload           5
        //   316: getfield        com/facebook/AppLinkData.arguments:Lorg/json/JSONObject;
        //   319: ifnull          338
        //   322: aload           5
        //   324: astore_0       
        //   325: aload           5
        //   327: getfield        com/facebook/AppLinkData.arguments:Lorg/json/JSONObject;
        //   330: ldc             "com.facebook.platform.APPLINK_NATIVE_URL"
        //   332: aload           9
        //   334: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   337: pop            
        //   338: aload           5
        //   340: astore_1       
        //   341: aload           5
        //   343: astore_0       
        //   344: aload           5
        //   346: getfield        com/facebook/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   349: ifnull          370
        //   352: aload           5
        //   354: astore_0       
        //   355: aload           5
        //   357: getfield        com/facebook/AppLinkData.argumentBundle:Landroid/os/Bundle;
        //   360: ldc             "com.facebook.platform.APPLINK_NATIVE_URL"
        //   362: aload           9
        //   364: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   367: aload           5
        //   369: astore_1       
        //   370: aload_2        
        //   371: aload_1        
        //   372: invokeinterface com/facebook/AppLinkData$CompletionHandler.onDeferredAppLinkDataFetched:(Lcom/facebook/AppLinkData;)V
        //   377: return         
        //   378: aconst_null    
        //   379: astore          5
        //   381: goto            101
        //   384: astore_0       
        //   385: aload           5
        //   387: astore_0       
        //   388: getstatic       com/facebook/AppLinkData.TAG:Ljava/lang/String;
        //   391: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   394: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   397: pop            
        //   398: goto            245
        //   401: astore_1       
        //   402: getstatic       com/facebook/AppLinkData.TAG:Ljava/lang/String;
        //   405: ldc_w           "Unable to fetch deferred applink from server"
        //   408: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/String;)V
        //   411: aload_0        
        //   412: astore_1       
        //   413: goto            370
        //   416: astore_0       
        //   417: aload           5
        //   419: astore_0       
        //   420: getstatic       com/facebook/AppLinkData.TAG:Ljava/lang/String;
        //   423: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   426: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   429: pop            
        //   430: goto            303
        //   433: astore_0       
        //   434: aload           5
        //   436: astore_0       
        //   437: getstatic       com/facebook/AppLinkData.TAG:Ljava/lang/String;
        //   440: ldc_w           "Unable to put tap time in AppLinkData.arguments"
        //   443: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   446: pop            
        //   447: aload           5
        //   449: astore_1       
        //   450: goto            370
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  71     86     401    416    Ljava/lang/Exception;
        //  93     101    401    416    Ljava/lang/Exception;
        //  112    121    401    416    Ljava/lang/Exception;
        //  124    135    401    416    Ljava/lang/Exception;
        //  138    147    401    416    Ljava/lang/Exception;
        //  150    159    401    416    Ljava/lang/Exception;
        //  165    173    401    416    Ljava/lang/Exception;
        //  176    183    401    416    Ljava/lang/Exception;
        //  194    202    384    401    Lorg/json/JSONException;
        //  194    202    401    416    Ljava/lang/Exception;
        //  205    217    384    401    Lorg/json/JSONException;
        //  205    217    401    416    Ljava/lang/Exception;
        //  220    228    384    401    Lorg/json/JSONException;
        //  220    228    401    416    Ljava/lang/Exception;
        //  231    245    384    401    Lorg/json/JSONException;
        //  231    245    401    416    Ljava/lang/Exception;
        //  253    261    416    433    Lorg/json/JSONException;
        //  253    261    401    416    Ljava/lang/Exception;
        //  264    277    416    433    Lorg/json/JSONException;
        //  264    277    401    416    Ljava/lang/Exception;
        //  280    288    416    433    Lorg/json/JSONException;
        //  280    288    401    416    Ljava/lang/Exception;
        //  291    303    416    433    Lorg/json/JSONException;
        //  291    303    401    416    Ljava/lang/Exception;
        //  314    322    433    453    Lorg/json/JSONException;
        //  314    322    401    416    Ljava/lang/Exception;
        //  325    338    433    453    Lorg/json/JSONException;
        //  325    338    401    416    Ljava/lang/Exception;
        //  344    352    433    453    Lorg/json/JSONException;
        //  344    352    401    416    Ljava/lang/Exception;
        //  355    367    433    453    Lorg/json/JSONException;
        //  355    367    401    416    Ljava/lang/Exception;
        //  388    398    401    416    Ljava/lang/Exception;
        //  420    430    401    416    Ljava/lang/Exception;
        //  437    447    401    416    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 221, Size: 221
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
    
    private static Bundle toBundle(final JSONObject jsonObject) {
        final Bundle bundle = new Bundle();
        final Iterator keys = jsonObject.keys();
        while (keys.hasNext()) {
            final String s = keys.next();
            final Object value = jsonObject.get(s);
            if (value instanceof JSONObject) {
                bundle.putBundle(s, toBundle((JSONObject)value));
            }
            else if (value instanceof JSONArray) {
                final JSONArray jsonArray = (JSONArray)value;
                if (jsonArray.length() == 0) {
                    bundle.putStringArray(s, new String[0]);
                }
                else {
                    final Object value2 = jsonArray.get(0);
                    if (value2 instanceof JSONObject) {
                        final Bundle[] array = new Bundle[jsonArray.length()];
                        for (int i = 0; i < jsonArray.length(); ++i) {
                            array[i] = toBundle(jsonArray.getJSONObject(i));
                        }
                        bundle.putParcelableArray(s, (Parcelable[])array);
                    }
                    else {
                        if (value2 instanceof JSONArray) {
                            throw new FacebookException("Nested arrays are not supported.");
                        }
                        final String[] array2 = new String[jsonArray.length()];
                        for (int j = 0; j < jsonArray.length(); ++j) {
                            array2[j] = jsonArray.get(j).toString();
                        }
                        bundle.putStringArray(s, array2);
                    }
                }
            }
            else {
                bundle.putString(s, value.toString());
            }
        }
        return bundle;
    }
    
    public Bundle getArgumentBundle() {
        return this.argumentBundle;
    }
    
    @Deprecated
    public JSONObject getArguments() {
        return this.arguments;
    }
    
    public String getRef() {
        return this.ref;
    }
    
    public Bundle getRefererData() {
        if (this.argumentBundle != null) {
            return this.argumentBundle.getBundle("referer_data");
        }
        return null;
    }
    
    public Uri getTargetUri() {
        return this.targetUri;
    }
}
