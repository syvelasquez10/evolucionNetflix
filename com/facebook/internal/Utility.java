// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Collections;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.InputStream;
import android.os.Parcelable;
import android.util.Log;
import java.util.HashSet;
import java.util.Collection;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import com.facebook.FacebookException;
import org.json.JSONTokener;
import java.lang.reflect.Method;
import com.facebook.Settings;
import android.provider.Settings$Secure;
import com.facebook.Request$Callback;
import com.facebook.Session;
import com.facebook.Request;
import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.io.IOException;
import java.io.Closeable;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.content.Context;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONObject;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.model.GraphObject;
import android.os.AsyncTask;
import java.util.Map;

public final class Utility
{
    private static final String APPLICATION_FIELDS = "fields";
    private static final String APP_SETTINGS_PREFS_KEY_FORMAT = "com.facebook.internal.APP_SETTINGS.%s";
    private static final String APP_SETTINGS_PREFS_STORE = "com.facebook.internal.preferences.APP_SETTINGS";
    private static final String APP_SETTING_DIALOG_CONFIGS = "android_dialog_configs";
    private static final String[] APP_SETTING_FIELDS;
    private static final String APP_SETTING_NUX_CONTENT = "gdpv4_nux_content";
    private static final String APP_SETTING_NUX_ENABLED = "gdpv4_nux_enabled";
    private static final String APP_SETTING_SUPPORTS_ATTRIBUTION = "supports_attribution";
    private static final String APP_SETTING_SUPPORTS_IMPLICIT_SDK_LOGGING = "supports_implicit_sdk_logging";
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    private static final String DIALOG_CONFIG_DIALOG_NAME_FEATURE_NAME_SEPARATOR = "\\|";
    private static final String DIALOG_CONFIG_NAME_KEY = "name";
    private static final String DIALOG_CONFIG_URL_KEY = "url";
    private static final String DIALOG_CONFIG_VERSIONS_KEY = "versions";
    private static final String EXTRA_APP_EVENTS_INFO_FORMAT_VERSION = "a1";
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final String HASH_ALGORITHM_SHA1 = "SHA-1";
    static final String LOG_TAG = "FacebookSDK";
    private static final String URL_SCHEME = "https";
    private static final String UTF8 = "UTF-8";
    private static Map<String, Utility$FetchedAppSettings> fetchedAppSettings;
    private static AsyncTask<Void, Void, GraphObject> initialAppSettingsLoadTask;
    
    static {
        APP_SETTING_FIELDS = new String[] { "supports_attribution", "supports_implicit_sdk_logging", "gdpv4_nux_content", "gdpv4_nux_enabled", "android_dialog_configs" };
        Utility.fetchedAppSettings = new ConcurrentHashMap<String, Utility$FetchedAppSettings>();
    }
    
    public static <T> boolean areObjectsEqual(final T t, final T t2) {
        if (t == null) {
            return t2 == null;
        }
        return t.equals(t2);
    }
    
    public static <T> ArrayList<T> arrayList(final T... array) {
        final ArrayList<T> list = new ArrayList<T>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
        }
        return list;
    }
    
    public static <T> List<T> asListNoNulls(final T... array) {
        final ArrayList<T> list = new ArrayList<T>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final T t = array[i];
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }
    
    public static Uri buildUri(final String s, String s2, final Bundle bundle) {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("https");
        uri$Builder.authority(s);
        uri$Builder.path(s2);
        final Iterator<String> iterator = bundle.keySet().iterator();
        while (iterator.hasNext()) {
            s2 = iterator.next();
            final Object value = bundle.get(s2);
            if (value instanceof String) {
                uri$Builder.appendQueryParameter(s2, (String)value);
            }
        }
        return uri$Builder.build();
    }
    
    public static void clearCaches(final Context context) {
        ImageDownloader.clearCache(context);
    }
    
    private static void clearCookiesForDomain(final Context context, final String s) {
        CookieSyncManager.createInstance(context).sync();
        final CookieManager instance = CookieManager.getInstance();
        final String cookie = instance.getCookie(s);
        if (cookie == null) {
            return;
        }
        final String[] split = cookie.split(";");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String[] split2 = split[i].split("=");
            if (split2.length > 0) {
                instance.setCookie(s, split2[0].trim() + "=;expires=Sat, 1 Jan 2000 00:00:01 UTC;");
            }
        }
        instance.removeExpiredCookie();
    }
    
    public static void clearFacebookCookies(final Context context) {
        clearCookiesForDomain(context, "facebook.com");
        clearCookiesForDomain(context, ".facebook.com");
        clearCookiesForDomain(context, "https://facebook.com");
        clearCookiesForDomain(context, "https://.facebook.com");
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static String coerceValueIfNullOrEmpty(final String s, final String s2) {
        if (isNullOrEmpty(s)) {
            return s2;
        }
        return s;
    }
    
    static Map<String, Object> convertJSONObjectToHashMap(final JSONObject jsonObject) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final JSONArray names = jsonObject.names();
        int n = 0;
    Label_0067_Outer:
        while (true) {
            if (n >= names.length()) {
                return hashMap;
            }
            while (true) {
                try {
                    final String string = names.getString(n);
                    Object o2;
                    final Object o = o2 = jsonObject.get(string);
                    if (o instanceof JSONObject) {
                        o2 = convertJSONObjectToHashMap((JSONObject)o);
                    }
                    hashMap.put(string, o2);
                    ++n;
                    continue Label_0067_Outer;
                }
                catch (JSONException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static void deleteDirectory(final File file) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                deleteDirectory(listFiles[i]);
            }
        }
        file.delete();
    }
    
    public static void disconnectQuietly(final URLConnection urlConnection) {
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).disconnect();
        }
    }
    
    public static String getActivityName(final Context context) {
        if (context == null) {
            return "null";
        }
        if (context == context.getApplicationContext()) {
            return "unknown";
        }
        return context.getClass().getSimpleName();
    }
    
    private static GraphObject getAppSettingsQueryResponse(final String s) {
        final Bundle parameters = new Bundle();
        parameters.putString("fields", TextUtils.join((CharSequence)",", (Object[])Utility.APP_SETTING_FIELDS));
        final Request graphPathRequest = Request.newGraphPathRequest(null, s, null);
        graphPathRequest.setSkipClientToken(true);
        graphPathRequest.setParameters(parameters);
        return graphPathRequest.executeAndWait().getGraphObject();
    }
    
    public static Utility$DialogFeatureConfig getDialogFeatureConfig(final String s, final String s2, final String s3) {
        if (isNullOrEmpty(s2) || isNullOrEmpty(s3)) {
            return null;
        }
        final Utility$FetchedAppSettings utility$FetchedAppSettings = Utility.fetchedAppSettings.get(s);
        if (utility$FetchedAppSettings != null) {
            final Map<String, Utility$DialogFeatureConfig> map = utility$FetchedAppSettings.getDialogConfigurations().get(s2);
            if (map != null) {
                return map.get(s3);
            }
        }
        return null;
    }
    
    public static String getHashedDeviceAndAppID(final Context context, final String s) {
        final String string = Settings$Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            return null;
        }
        return sha1hash(string + s);
    }
    
    public static String getMetadataApplicationId(final Context context) {
        Validate.notNull(context, "context");
        Settings.loadDefaultsFromMetadata(context);
        return Settings.getApplicationId();
    }
    
    public static Method getMethodQuietly(final Class<?> clazz, final String s, final Class<?>... array) {
        try {
            return clazz.getMethod(s, array);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    public static Method getMethodQuietly(final String s, final String s2, final Class<?>... array) {
        try {
            return getMethodQuietly(Class.forName(s), s2, array);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static Object getStringPropertyAsJSON(final JSONObject jsonObject, final String s, final String s2) {
        Object o = jsonObject.opt(s);
        if (o != null && o instanceof String) {
            o = new JSONTokener((String)o).nextValue();
        }
        if (o == null || o instanceof JSONObject || o instanceof JSONArray) {
            return o;
        }
        if (s2 != null) {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.putOpt(s2, o);
            return jsonObject2;
        }
        throw new FacebookException("Got an unexpected non-JSON object.");
    }
    
    private static String hashBytes(final MessageDigest messageDigest, final byte[] array) {
        messageDigest.update(array);
        final byte[] digest = messageDigest.digest();
        final StringBuilder sb = new StringBuilder();
        for (int length = digest.length, i = 0; i < length; ++i) {
            final byte b = digest[i];
            sb.append(Integer.toHexString(b >> 4 & 0xF));
            sb.append(Integer.toHexString(b >> 0 & 0xF));
        }
        return sb.toString();
    }
    
    private static String hashWithAlgorithm(final String s, final String s2) {
        return hashWithAlgorithm(s, s2.getBytes());
    }
    
    private static String hashWithAlgorithm(final String s, final byte[] array) {
        try {
            return hashBytes(MessageDigest.getInstance(s), array);
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }
    
    public static int[] intersectRanges(final int[] array, final int[] array2) {
        int n = 0;
        if (array == null) {
            return array2;
        }
        if (array2 == null) {
            return array;
        }
        final int[] array3 = new int[array.length + array2.length];
        int n2 = 0;
        int n3 = 0;
        int n4;
        while (true) {
            n4 = n3;
            if (n2 >= array.length) {
                break;
            }
            n4 = n3;
            if (n >= array2.length) {
                break;
            }
            int n5 = array[n2];
            final int n6 = array2[n];
            int n7;
            if (n2 < array.length - 1) {
                n7 = array[n2 + 1];
            }
            else {
                n7 = Integer.MAX_VALUE;
            }
            int n8;
            if (n < array2.length - 1) {
                n8 = array2[n + 1];
            }
            else {
                n8 = Integer.MAX_VALUE;
            }
            int n9;
            int n10;
            if (n5 < n6) {
                if (n7 > n6) {
                    if (n7 > n8) {
                        n9 = n + 2;
                        n5 = n6;
                        n7 = n8;
                        n10 = n2;
                    }
                    else {
                        n10 = n2 + 2;
                        n9 = n;
                        n5 = n6;
                    }
                }
                else {
                    n10 = n2 + 2;
                    n7 = Integer.MAX_VALUE;
                    n5 = Integer.MIN_VALUE;
                    n9 = n;
                }
            }
            else if (n8 > n5) {
                if (n8 > n7) {
                    n10 = n2 + 2;
                    n9 = n;
                }
                else {
                    n9 = n + 2;
                    n10 = n2;
                    n7 = n8;
                }
            }
            else {
                n9 = n + 2;
                n7 = Integer.MAX_VALUE;
                n5 = Integer.MIN_VALUE;
                n10 = n2;
            }
            n = n9;
            n2 = n10;
            if (n5 == Integer.MIN_VALUE) {
                continue;
            }
            final int n11 = n3 + 1;
            array3[n3] = n5;
            if (n7 == Integer.MAX_VALUE) {
                n4 = n11;
                break;
            }
            n3 = n11 + 1;
            array3[n11] = n7;
            n = n9;
            n2 = n10;
        }
        return Arrays.copyOf(array3, n4);
    }
    
    public static Object invokeMethodQuietly(Object invoke, final Method method, final Object... array) {
        try {
            invoke = method.invoke(invoke, array);
            return invoke;
        }
        catch (IllegalAccessException ex) {
            return null;
        }
        catch (InvocationTargetException ex2) {
            return null;
        }
    }
    
    public static boolean isNullOrEmpty(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static <T> boolean isNullOrEmpty(final Collection<T> collection) {
        return collection == null || collection.size() == 0;
    }
    
    public static <T> boolean isSubset(final Collection<T> collection, final Collection<T> collection2) {
        boolean b = false;
        if (collection2 == null || collection2.size() == 0) {
            if (collection == null || collection.size() == 0) {
                b = true;
            }
            return b;
        }
        final HashSet set = new HashSet((Collection<? extends E>)collection2);
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }
    
    public static void loadAppSettingsAsync(final Context context, final String s) {
        final JSONObject jsonObject = null;
        if (!isNullOrEmpty(s) && !Utility.fetchedAppSettings.containsKey(s) && Utility.initialAppSettingsLoadTask == null) {
            final String format = String.format("com.facebook.internal.APP_SETTINGS.%s", s);
            (Utility.initialAppSettingsLoadTask = new Utility$1(s, context, format)).execute((Object[])null);
            final String string = context.getSharedPreferences("com.facebook.internal.preferences.APP_SETTINGS", 0).getString(format, (String)null);
            if (!isNullOrEmpty(string)) {
                while (true) {
                    try {
                        final JSONObject jsonObject2 = new JSONObject(string);
                        if (jsonObject2 != null) {
                            parseAppSettingsFromJSON(s, jsonObject2);
                        }
                    }
                    catch (JSONException ex) {
                        logd("FacebookSDK", (Exception)ex);
                        final JSONObject jsonObject2 = jsonObject;
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public static void logd(final String s, final Exception ex) {
        if (Settings.isDebugEnabled() && s != null && ex != null) {
            Log.d(s, ex.getClass().getSimpleName() + ": " + ex.getMessage());
        }
    }
    
    public static void logd(final String s, final String s2) {
        if (Settings.isDebugEnabled() && s != null && s2 != null) {
            Log.d(s, s2);
        }
    }
    
    public static void logd(final String s, final String s2, final Throwable t) {
        if (Settings.isDebugEnabled() && !isNullOrEmpty(s)) {
            Log.d(s, s2, t);
        }
    }
    
    static String md5hash(final String s) {
        return hashWithAlgorithm("MD5", s);
    }
    
    private static Utility$FetchedAppSettings parseAppSettingsFromJSON(final String s, final JSONObject jsonObject) {
        final Utility$FetchedAppSettings utility$FetchedAppSettings = new Utility$FetchedAppSettings(jsonObject.optBoolean("supports_attribution", false), jsonObject.optBoolean("supports_implicit_sdk_logging", false), jsonObject.optString("gdpv4_nux_content", ""), jsonObject.optBoolean("gdpv4_nux_enabled", false), parseDialogConfigurations(jsonObject.optJSONObject("android_dialog_configs")), null);
        Utility.fetchedAppSettings.put(s, utility$FetchedAppSettings);
        return utility$FetchedAppSettings;
    }
    
    private static Map<String, Map<String, Utility$DialogFeatureConfig>> parseDialogConfigurations(final JSONObject jsonObject) {
        final HashMap<Object, Map<String, Utility$DialogFeatureConfig>> hashMap = new HashMap<Object, Map<String, Utility$DialogFeatureConfig>>();
        if (jsonObject != null) {
            final JSONArray optJSONArray = jsonObject.optJSONArray("data");
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); ++i) {
                    final Utility$DialogFeatureConfig access$400 = parseDialogConfig(optJSONArray.optJSONObject(i));
                    if (access$400 != null) {
                        final String dialogName = access$400.getDialogName();
                        Map<String, Utility$DialogFeatureConfig> map;
                        if ((map = hashMap.get(dialogName)) == null) {
                            map = new HashMap<String, Utility$DialogFeatureConfig>();
                            hashMap.put(dialogName, map);
                        }
                        map.put(access$400.getFeatureName(), access$400);
                    }
                }
            }
        }
        return (Map<String, Map<String, Utility$DialogFeatureConfig>>)hashMap;
    }
    
    public static Bundle parseUrlQueryString(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Landroid/os/Bundle;
        //     3: dup            
        //     4: invokespecial   android/os/Bundle.<init>:()V
        //     7: astore_3       
        //     8: aload_0        
        //     9: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //    12: ifne            113
        //    15: aload_0        
        //    16: ldc_w           "&"
        //    19: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    22: astore_0       
        //    23: aload_0        
        //    24: arraylength    
        //    25: istore_2       
        //    26: iconst_0       
        //    27: istore_1       
        //    28: iload_1        
        //    29: iload_2        
        //    30: if_icmpge       113
        //    33: aload_0        
        //    34: iload_1        
        //    35: aaload         
        //    36: ldc             "="
        //    38: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    41: astore          4
        //    43: aload           4
        //    45: arraylength    
        //    46: iconst_2       
        //    47: if_icmpne       75
        //    50: aload_3        
        //    51: aload           4
        //    53: iconst_0       
        //    54: aaload         
        //    55: ldc             "UTF-8"
        //    57: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    60: aload           4
        //    62: iconst_1       
        //    63: aaload         
        //    64: ldc             "UTF-8"
        //    66: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    69: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    72: goto            115
        //    75: aload           4
        //    77: arraylength    
        //    78: iconst_1       
        //    79: if_icmpne       115
        //    82: aload_3        
        //    83: aload           4
        //    85: iconst_0       
        //    86: aaload         
        //    87: ldc             "UTF-8"
        //    89: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    92: ldc_w           ""
        //    95: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    98: goto            115
        //   101: astore          4
        //   103: ldc             "FacebookSDK"
        //   105: aload           4
        //   107: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   110: goto            115
        //   113: aload_3        
        //   114: areturn        
        //   115: iload_1        
        //   116: iconst_1       
        //   117: iadd           
        //   118: istore_1       
        //   119: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  43     72     101    113    Ljava/io/UnsupportedEncodingException;
        //  75     98     101    113    Ljava/io/UnsupportedEncodingException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    public static void putObjectInBundle(final Bundle bundle, final String s, final Object o) {
        if (o instanceof String) {
            bundle.putString(s, (String)o);
            return;
        }
        if (o instanceof Parcelable) {
            bundle.putParcelable(s, (Parcelable)o);
            return;
        }
        if (o instanceof byte[]) {
            bundle.putByteArray(s, (byte[])o);
            return;
        }
        throw new FacebookException("attempted to add unsupported type to Bundle");
    }
    
    public static Utility$FetchedAppSettings queryAppSettings(final String s, final boolean b) {
        if (!b && Utility.fetchedAppSettings.containsKey(s)) {
            return Utility.fetchedAppSettings.get(s);
        }
        final GraphObject appSettingsQueryResponse = getAppSettingsQueryResponse(s);
        if (appSettingsQueryResponse == null) {
            return null;
        }
        return parseAppSettingsFromJSON(s, appSettingsQueryResponse.getInnerJSONObject());
    }
    
    public static String readStreamToString(final InputStream p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: new             Ljava/io/BufferedInputStream;
        //     5: dup            
        //     6: aload_0        
        //     7: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    10: astore_0       
        //    11: new             Ljava/io/InputStreamReader;
        //    14: dup            
        //    15: aload_0        
        //    16: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    19: astore_2       
        //    20: new             Ljava/lang/StringBuilder;
        //    23: dup            
        //    24: invokespecial   java/lang/StringBuilder.<init>:()V
        //    27: astore_3       
        //    28: sipush          2048
        //    31: newarray        C
        //    33: astore          4
        //    35: aload_2        
        //    36: aload           4
        //    38: invokevirtual   java/io/InputStreamReader.read:([C)I
        //    41: istore_1       
        //    42: iload_1        
        //    43: iconst_m1      
        //    44: if_icmpeq       76
        //    47: aload_3        
        //    48: aload           4
        //    50: iconst_0       
        //    51: iload_1        
        //    52: invokevirtual   java/lang/StringBuilder.append:([CII)Ljava/lang/StringBuilder;
        //    55: pop            
        //    56: goto            35
        //    59: astore          4
        //    61: aload_0        
        //    62: astore_3       
        //    63: aload           4
        //    65: astore_0       
        //    66: aload_3        
        //    67: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    70: aload_2        
        //    71: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    74: aload_0        
        //    75: athrow         
        //    76: aload_3        
        //    77: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    80: astore_3       
        //    81: aload_0        
        //    82: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    85: aload_2        
        //    86: invokestatic    com/facebook/internal/Utility.closeQuietly:(Ljava/io/Closeable;)V
        //    89: aload_3        
        //    90: areturn        
        //    91: astore_0       
        //    92: aconst_null    
        //    93: astore_2       
        //    94: goto            66
        //    97: astore          4
        //    99: aconst_null    
        //   100: astore_2       
        //   101: aload_0        
        //   102: astore_3       
        //   103: aload           4
        //   105: astore_0       
        //   106: goto            66
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     91     97     Any
        //  11     20     97     109    Any
        //  20     35     59     66     Any
        //  35     42     59     66     Any
        //  47     56     59     66     Any
        //  76     81     59     66     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    
    public static boolean safeGetBooleanFromResponse(final GraphObject graphObject, final String s) {
        Object o = false;
        if (graphObject != null) {
            o = graphObject.getProperty(s);
        }
        Object value = o;
        if (!(o instanceof Boolean)) {
            value = false;
        }
        return (boolean)value;
    }
    
    public static String safeGetStringFromResponse(final GraphObject graphObject, final String s) {
        Object property = "";
        if (graphObject != null) {
            property = graphObject.getProperty(s);
        }
        Object o = property;
        if (!(property instanceof String)) {
            o = "";
        }
        return (String)o;
    }
    
    public static void setAppEventAttributionParameters(final GraphObject graphObject, final AttributionIdentifiers attributionIdentifiers, final String s, final boolean b) {
        final boolean b2 = true;
        if (attributionIdentifiers != null && attributionIdentifiers.getAttributionId() != null) {
            graphObject.setProperty("attribution", attributionIdentifiers.getAttributionId());
        }
        if (attributionIdentifiers != null && attributionIdentifiers.getAndroidAdvertiserId() != null) {
            graphObject.setProperty("advertiser_id", attributionIdentifiers.getAndroidAdvertiserId());
            graphObject.setProperty("advertiser_tracking_enabled", !attributionIdentifiers.isTrackingLimited());
        }
        else if (s != null) {
            graphObject.setProperty("advertiser_id", s);
        }
        graphObject.setProperty("application_tracking_enabled", !b && b2);
    }
    
    public static void setAppEventExtendedDeviceInfoParameters(final GraphObject graphObject, final Context context) {
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put((Object)"a1");
        final String packageName = context.getPackageName();
        final int n = -1;
        final String s = "";
        int versionCode = n;
        while (true) {
            try {
                final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                versionCode = n;
                versionCode = packageInfo.versionCode;
                final String versionName = packageInfo.versionName;
                versionCode = versionCode;
                jsonArray.put((Object)packageName);
                jsonArray.put(versionCode);
                jsonArray.put((Object)versionName);
                graphObject.setProperty("extinfo", jsonArray.toString());
            }
            catch (PackageManager$NameNotFoundException ex) {
                final String versionName = s;
                continue;
            }
            break;
        }
    }
    
    static String sha1hash(final String s) {
        return hashWithAlgorithm("SHA-1", s);
    }
    
    static String sha1hash(final byte[] array) {
        return hashWithAlgorithm("SHA-1", array);
    }
    
    public static boolean stringsEqualOrEmpty(final String s, final String s2) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        final boolean empty2 = TextUtils.isEmpty((CharSequence)s2);
        return (empty && empty2) || (!empty && !empty2 && s.equals(s2));
    }
    
    public static JSONArray tryGetJSONArrayFromResponse(final GraphObject graphObject, final String s) {
        if (graphObject == null) {
            return null;
        }
        final Object property = graphObject.getProperty(s);
        if (!(property instanceof JSONArray)) {
            return null;
        }
        return (JSONArray)property;
    }
    
    public static JSONObject tryGetJSONObjectFromResponse(final GraphObject graphObject, final String s) {
        if (graphObject == null) {
            return null;
        }
        final Object property = graphObject.getProperty(s);
        if (!(property instanceof JSONObject)) {
            return null;
        }
        return (JSONObject)property;
    }
    
    public static <T> Collection<T> unmodifiableCollection(final T... array) {
        return Collections.unmodifiableCollection((Collection<? extends T>)Arrays.asList(array));
    }
}
