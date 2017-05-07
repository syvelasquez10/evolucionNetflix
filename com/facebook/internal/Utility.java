// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.util.Collections;
import java.util.Arrays;
import android.text.TextUtils;
import java.io.InputStream;
import com.facebook.model.GraphObject;
import com.facebook.Request$Callback;
import com.facebook.Session;
import com.facebook.Request;
import android.os.Parcelable;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Collection;
import com.facebook.FacebookException;
import org.json.JSONTokener;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
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

public final class Utility
{
    private static final String APPLICATION_FIELDS = "fields";
    public static final int DEFAULT_STREAM_BUFFER_SIZE = 8192;
    private static final String HASH_ALGORITHM_MD5 = "MD5";
    private static final Object LOCK;
    static final String LOG_TAG = "FacebookSDK";
    private static final String SUPPORTS_ATTRIBUTION = "supports_attribution";
    private static final String URL_SCHEME = "https";
    private static volatile boolean attributionAllowedForLastAppChecked;
    private static volatile String lastAppCheckedForAttributionStatus;
    
    static {
        LOCK = new Object();
        Utility.attributionAllowedForLastAppChecked = false;
        Utility.lastAppCheckedForAttributionStatus = "";
    }
    
    public static <T> ArrayList<T> arrayList(final T... array) {
        final ArrayList<T> list = new ArrayList<T>(array.length);
        for (int length = array.length, i = 0; i < length; ++i) {
            list.add(array[i]);
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
    
    public static void disconnectQuietly(final URLConnection urlConnection) {
        if (urlConnection instanceof HttpURLConnection) {
            ((HttpURLConnection)urlConnection).disconnect();
        }
    }
    
    public static String getMetadataApplicationId(final Context context) {
        try {
            final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                return applicationInfo.metaData.getString("com.facebook.sdk.ApplicationId");
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return null;
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
    }
    
    public static void logd(final String s, final String s2) {
    }
    
    static String md5hash(final String s) {
        StringBuilder sb;
        try {
            final MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(s.getBytes());
            final byte[] digest = instance.digest();
            sb = new StringBuilder();
            for (int length = digest.length, i = 0; i < length; ++i) {
                final byte b = digest[i];
                sb.append(Integer.toHexString(b >> 4 & 0xF));
                sb.append(Integer.toHexString(b >> 0 & 0xF));
            }
        }
        catch (NoSuchAlgorithmException ex) {
            return null;
        }
        return sb.toString();
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
    
    public static boolean queryAppAttributionSupportAndWait(final String lastAppCheckedForAttributionStatus) {
        while (true) {
            while (true) {
                synchronized (Utility.LOCK) {
                    if (lastAppCheckedForAttributionStatus.equals(Utility.lastAppCheckedForAttributionStatus)) {
                        return Utility.attributionAllowedForLastAppChecked;
                    }
                    final Bundle parameters = new Bundle();
                    parameters.putString("fields", "supports_attribution");
                    final Request graphPathRequest = Request.newGraphPathRequest(null, lastAppCheckedForAttributionStatus, null);
                    graphPathRequest.setParameters(parameters);
                    final GraphObject graphObject = graphPathRequest.executeAndWait().getGraphObject();
                    Object o = false;
                    if (graphObject != null) {
                        o = graphObject.getProperty("supports_attribution");
                    }
                    Object value = o;
                    if (!(o instanceof Boolean)) {
                        value = false;
                    }
                    Utility.lastAppCheckedForAttributionStatus = lastAppCheckedForAttributionStatus;
                    if (value) {
                        final boolean attributionAllowedForLastAppChecked = true;
                        return Utility.attributionAllowedForLastAppChecked = attributionAllowedForLastAppChecked;
                    }
                }
                final boolean attributionAllowedForLastAppChecked = false;
                continue;
            }
        }
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
    
    public static boolean stringsEqualOrEmpty(final String s, final String s2) {
        final boolean empty = TextUtils.isEmpty((CharSequence)s);
        final boolean empty2 = TextUtils.isEmpty((CharSequence)s2);
        return (empty && empty2) || (!empty && !empty2 && s.equals(s2));
    }
    
    public static <T> Collection<T> unmodifiableCollection(final T... array) {
        return Collections.unmodifiableCollection((Collection<? extends T>)Arrays.asList(array));
    }
}
