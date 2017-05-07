// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Collections;
import java.util.Arrays;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import android.util.Log;
import java.util.HashSet;
import java.util.Collection;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import com.facebook.FacebookException;
import org.json.JSONArray;
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
import java.io.IOException;
import java.io.Closeable;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.content.Context;
import java.util.Iterator;
import android.net.Uri$Builder;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import java.util.concurrent.ConcurrentHashMap;
import com.facebook.model.GraphObject;
import android.os.AsyncTask;
import java.util.Map;

public final class Utility
{
    private static final String[] APP_SETTING_FIELDS;
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
        //    12: ifne            117
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
        //    30: if_icmpge       117
        //    33: aload_0        
        //    34: iload_1        
        //    35: aaload         
        //    36: ldc             "="
        //    38: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    41: astore          4
        //    43: aload           4
        //    45: arraylength    
        //    46: iconst_2       
        //    47: if_icmpne       77
        //    50: aload_3        
        //    51: aload           4
        //    53: iconst_0       
        //    54: aaload         
        //    55: ldc_w           "UTF-8"
        //    58: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    61: aload           4
        //    63: iconst_1       
        //    64: aaload         
        //    65: ldc_w           "UTF-8"
        //    68: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    71: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    74: goto            119
        //    77: aload           4
        //    79: arraylength    
        //    80: iconst_1       
        //    81: if_icmpne       119
        //    84: aload_3        
        //    85: aload           4
        //    87: iconst_0       
        //    88: aaload         
        //    89: ldc_w           "UTF-8"
        //    92: invokestatic    java/net/URLDecoder.decode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //    95: ldc_w           ""
        //    98: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   101: goto            119
        //   104: astore          4
        //   106: ldc_w           "FacebookSDK"
        //   109: aload           4
        //   111: invokestatic    com/facebook/internal/Utility.logd:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   114: goto            119
        //   117: aload_3        
        //   118: areturn        
        //   119: iload_1        
        //   120: iconst_1       
        //   121: iadd           
        //   122: istore_1       
        //   123: goto            28
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  43     74     104    117    Ljava/io/UnsupportedEncodingException;
        //  77     101    104    117    Ljava/io/UnsupportedEncodingException;
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
    
    public static <T> Collection<T> unmodifiableCollection(final T... array) {
        return Collections.unmodifiableCollection((Collection<? extends T>)Arrays.asList(array));
    }
}
