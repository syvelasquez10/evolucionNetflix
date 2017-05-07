// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.net.UrlQuerySanitizer$ParameterValuePair;
import android.net.UrlQuerySanitizer;
import java.util.HashMap;
import android.net.Uri;
import android.content.pm.PackageManager;
import java.util.Arrays;
import java.util.Map;
import android.os.Build$VERSION;
import android.webkit.WebView;
import java.net.HttpURLConnection;
import java.util.List;
import android.webkit.WebSettings;
import android.content.Context;
import org.json.JSONObject;
import android.os.Bundle;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import java.io.IOException;
import java.nio.CharBuffer;

public final class dq
{
    private static final Object px;
    private static boolean re;
    private static String rf;
    private static boolean rg;
    
    static {
        px = new Object();
        dq.re = true;
        dq.rg = false;
    }
    
    public static String a(final Readable readable) throws IOException {
        final StringBuilder sb = new StringBuilder();
        final CharBuffer allocate = CharBuffer.allocate(2048);
        while (true) {
            final int read = readable.read(allocate);
            if (read == -1) {
                break;
            }
            allocate.flip();
            sb.append(allocate, 0, read);
        }
        return sb.toString();
    }
    
    private static JSONArray a(final Collection<?> collection) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<?> iterator = collection.iterator();
        while (iterator.hasNext()) {
            a(jsonArray, iterator.next());
        }
        return jsonArray;
    }
    
    static JSONArray a(final Object[] array) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        for (int length = array.length, i = 0; i < length; ++i) {
            a(jsonArray, array[i]);
        }
        return jsonArray;
    }
    
    private static JSONObject a(final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            a(jsonObject, s, bundle.get(s));
        }
        return jsonObject;
    }
    
    public static void a(final Context context, final String s, final WebSettings webSettings) {
        webSettings.setUserAgentString(b(context, s));
    }
    
    public static void a(final Context context, final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            new du(context, s, iterator.next()).start();
        }
    }
    
    public static void a(final Context context, final String s, final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", b(context, s));
        httpURLConnection.setUseCaches(false);
    }
    
    public static void a(final WebView webView) {
        if (Build$VERSION.SDK_INT >= 11) {
            ds.a(webView);
        }
    }
    
    private static void a(final JSONArray jsonArray, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonArray.put((Object)a((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonArray.put((Object)p((Map<String, ?>)o));
            return;
        }
        if (o instanceof Collection) {
            jsonArray.put((Object)a((Collection<?>)o));
            return;
        }
        if (o instanceof Object[]) {
            jsonArray.put((Object)a((Object[])o));
            return;
        }
        jsonArray.put(o);
    }
    
    private static void a(final JSONObject jsonObject, String s, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonObject.put(s, (Object)a((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonObject.put(s, (Object)p((Map<String, ?>)o));
            return;
        }
        if (o instanceof Collection) {
            if (s == null) {
                s = "null";
            }
            jsonObject.put(s, (Object)a((Collection<?>)o));
            return;
        }
        if (o instanceof Object[]) {
            jsonObject.put(s, (Object)a(Arrays.asList((Object[])o)));
            return;
        }
        jsonObject.put(s, o);
    }
    
    public static boolean a(final PackageManager packageManager, final String s, final String s2) {
        return packageManager.checkPermission(s2, s) == 0;
    }
    
    public static boolean a(final ClassLoader classLoader, final Class<?> clazz, final String s) {
        try {
            return clazz.isAssignableFrom(Class.forName(s, false, classLoader));
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    private static String b(final Context context, final String s) {
    Label_0035_Outer:
        while (true) {
            while (true) {
                synchronized (dq.px) {
                    if (dq.rf != null) {
                        return dq.rf;
                    }
                    if (Build$VERSION.SDK_INT >= 17) {
                        dq.rf = dt.getDefaultUserAgent(context);
                        return dq.rf = dq.rf + " (Mobile; " + s + ")";
                    }
                }
                final Context context2;
                Label_0128: {
                    if (!dv.bD()) {
                        dv.rp.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                synchronized (dq.px) {
                                    dq.rf = j(context2);
                                    dq.px.notifyAll();
                                }
                            }
                        });
                        while (dq.rf == null) {
                            try {
                                dq.px.wait();
                                continue Label_0035_Outer;
                            }
                            catch (InterruptedException ex) {
                                // monitorexit(o)
                                return dq.rf;
                            }
                            break Label_0128;
                        }
                        continue;
                    }
                }
                dq.rf = j(context2);
                continue;
            }
        }
    }
    
    public static Map<String, String> b(final Uri uri) {
        if (uri == null) {
            return null;
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final UrlQuerySanitizer urlQuerySanitizer = new UrlQuerySanitizer();
        urlQuerySanitizer.setAllowUnregisteredParamaters(true);
        urlQuerySanitizer.setUnregisteredParameterValueSanitizer(UrlQuerySanitizer.getAllButNulLegal());
        urlQuerySanitizer.parseUrl(uri.toString());
        for (final UrlQuerySanitizer$ParameterValuePair urlQuerySanitizer$ParameterValuePair : urlQuerySanitizer.getParameterList()) {
            hashMap.put(urlQuerySanitizer$ParameterValuePair.mParameter, urlQuerySanitizer$ParameterValuePair.mValue);
        }
        return hashMap;
    }
    
    public static void b(final WebView webView) {
        if (Build$VERSION.SDK_INT >= 11) {
            ds.b(webView);
        }
    }
    
    public static int bA() {
        if (Build$VERSION.SDK_INT >= 9) {
            return 7;
        }
        return 1;
    }
    
    public static boolean by() {
        return dq.re;
    }
    
    public static int bz() {
        if (Build$VERSION.SDK_INT >= 9) {
            return 6;
        }
        return 0;
    }
    
    public static boolean h(final Context context) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            dw.z("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            return false;
        }
        boolean b;
        if ((resolveActivity.activityInfo.configChanges & 0x10) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboard"));
            b = false;
        }
        else {
            b = true;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x20) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboardHidden"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x80) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "orientation"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x100) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenLayout"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x200) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "uiMode"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x400) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenSize"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x800) == 0x0) {
            dw.z(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "smallestScreenSize"));
            return false;
        }
        return b;
    }
    
    public static void i(final Context context) {
        if (dq.rg) {
            return;
        }
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver((BroadcastReceiver)new a(), intentFilter);
        dq.rg = true;
    }
    
    private static String j(final Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }
    
    public static JSONObject p(final Map<String, ?> map) throws JSONException {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject();
            for (final String s : map.keySet()) {
                a(jsonObject, s, map.get(s));
            }
        }
        catch (ClassCastException ex) {
            throw new JSONException("Could not convert map to JSON: " + ex.getMessage());
        }
        return jsonObject;
    }
    
    public static String r(final String s) {
        return Uri.parse(s).buildUpon().query((String)null).build().toString();
    }
    
    private static final class a extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                dq.re = true;
            }
            else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                dq.re = false;
            }
        }
    }
}
