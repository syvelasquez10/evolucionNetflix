// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.Window;
import android.graphics.Rect;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;
import java.util.UUID;
import android.os.Build;
import java.util.Locale;
import java.util.ArrayList;
import android.net.UrlQuerySanitizer$ParameterValuePair;
import android.net.UrlQuerySanitizer;
import java.util.HashMap;
import android.content.pm.PackageManager;
import java.util.Arrays;
import org.json.JSONObject;
import java.util.Map;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.webkit.WebView;
import java.net.HttpURLConnection;
import java.util.List;
import android.webkit.WebSettings;
import android.content.Context;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.Collection;
import java.io.IOException;
import java.nio.CharBuffer;
import java.text.ParseException;
import java.util.TimeZone;
import android.text.TextUtils;
import android.net.Uri;
import java.text.SimpleDateFormat;

@ez
public final class gj
{
    private static final Object uf;
    private static final SimpleDateFormat[] wm;
    private static boolean wn;
    private static String wo;
    private static boolean wp;
    
    static {
        uf = new Object();
        wm = new SimpleDateFormat[] { new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"), new SimpleDateFormat("yyyyMMdd") };
        gj.wn = true;
        gj.wp = false;
    }
    
    public static String L(final String s) {
        return Uri.parse(s).buildUpon().query((String)null).build().toString();
    }
    
    public static int M(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            gs.W("Could not parse value:" + ex);
            return 0;
        }
    }
    
    public static boolean N(final String s) {
        return !TextUtils.isEmpty((CharSequence)s) && s.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }
    
    public static long O(final String s) {
        int i = 0;
        if (TextUtils.isEmpty((CharSequence)s)) {
            return -1L;
        }
        final SimpleDateFormat[] wm = gj.wm;
        while (i < wm.length) {
            final SimpleDateFormat simpleDateFormat = wm[i];
            try {
                simpleDateFormat.setLenient(false);
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                return simpleDateFormat.parse(s).getTime();
            }
            catch (ParseException ex) {
                ++i;
                continue;
            }
            break;
        }
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex2) {
            return -1L;
        }
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
    
    public static void a(final Context context, final String s, final WebSettings webSettings) {
        webSettings.setUserAgentString(c(context, s));
    }
    
    public static void a(final Context context, final String s, final List<String> list) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            new gq(context, s, iterator.next()).start();
        }
    }
    
    public static void a(final Context context, final String s, final List<String> list, final String s2) {
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            new gq(context, s, iterator.next(), s2).start();
        }
    }
    
    public static void a(final Context context, final String s, final boolean b, final HttpURLConnection httpURLConnection) {
        a(context, s, b, httpURLConnection, false);
    }
    
    public static void a(final Context context, final String s, final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection, final String s2) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", s2);
        httpURLConnection.setUseCaches(false);
    }
    
    public static void a(final Context context, final String s, final boolean instanceFollowRedirects, final HttpURLConnection httpURLConnection, final boolean useCaches) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(instanceFollowRedirects);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", c(context, s));
        httpURLConnection.setUseCaches(useCaches);
    }
    
    public static void a(final WebView webView) {
        if (Build$VERSION.SDK_INT >= 11) {
            gn.a(webView);
        }
    }
    
    private static void a(final JSONArray jsonArray, final Object o) throws JSONException {
        if (o instanceof Bundle) {
            jsonArray.put((Object)c((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonArray.put((Object)t((Map<String, ?>)o));
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
            jsonObject.put(s, (Object)c((Bundle)o));
            return;
        }
        if (o instanceof Map) {
            jsonObject.put(s, (Object)t((Map<String, ?>)o));
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
    
    public static void b(final WebView webView) {
        if (Build$VERSION.SDK_INT >= 11) {
            gn.b(webView);
        }
    }
    
    public static String c(Context context, final String s) {
        final Object uf = gj.uf;
        // monitorenter(uf)
        Label_0089: {
            try {
                if (gj.wo != null) {
                    return gj.wo;
                }
                if (Build$VERSION.SDK_INT >= 17) {
                    final Context context2 = context;
                    gj.wo = gp.getDefaultUserAgent(context2);
                    final StringBuilder sb = new StringBuilder();
                    final String s2 = gj.wo;
                    final StringBuilder sb2 = sb.append(s2);
                    final String s3 = " (Mobile; ";
                    final StringBuilder sb3 = sb2.append(s3);
                    final String s4 = s;
                    final StringBuilder sb4 = sb3.append(s4);
                    final String s5 = ")";
                    final StringBuilder sb5 = sb4.append(s5);
                    gj.wo = sb5.toString();
                    final String wo = gj.wo;
                    return wo;
                }
                break Label_0089;
            }
            finally {
                final Context context3;
                context = context3;
            }
            // monitorexit(uf)
            try {
                final Context context2 = context;
                gj.wo = gp.getDefaultUserAgent(context2);
                final StringBuilder sb = new StringBuilder();
                final String s2 = gj.wo;
                final StringBuilder sb2 = sb.append(s2);
                final String s3 = " (Mobile; ";
                final StringBuilder sb3 = sb2.append(s3);
                final String s4 = s;
                final StringBuilder sb4 = sb3.append(s4);
                final String s5 = ")";
                final StringBuilder sb5 = sb4.append(s5);
                gj.wo = sb5.toString();
                final String wo2;
                final String wo = wo2 = gj.wo;
                return wo2;
            }
            catch (Exception ex) {}
        }
        while (true) {
            if (!gr.dt()) {
                gr.wC.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        synchronized (gj.uf) {
                            gj.wo = r(context);
                            gj.uf.notifyAll();
                        }
                    }
                });
                while (gj.wo == null) {
                    try {
                        gj.uf.wait();
                    }
                    catch (InterruptedException ex2) {
                        gj.wo = do();
                        gs.W("Interrupted, use default user agent: " + gj.wo);
                    }
                }
                break Label_0169;
            }
            try {
                gj.wo = r(context);
                gj.wo = gj.wo + " (Mobile; " + s + ")";
                // monitorexit(uf)
                return gj.wo;
            }
            catch (Exception ex3) {
                gj.wo = do();
                continue;
            }
            break;
        }
    }
    
    public static Map<String, String> c(final Uri uri) {
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
    
    private static JSONObject c(final Bundle bundle) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            a(jsonObject, s, bundle.get(s));
        }
        return jsonObject;
    }
    
    public static void c(final Context context, final String s, final String s2) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s2);
        a(context, s, list);
    }
    
    public static boolean dl() {
        return gj.wn;
    }
    
    public static int dm() {
        if (Build$VERSION.SDK_INT >= 9) {
            return 6;
        }
        return 0;
    }
    
    public static int dn() {
        if (Build$VERSION.SDK_INT >= 9) {
            return 7;
        }
        return 1;
    }
    
    static String do() {
        final StringBuffer sb = new StringBuffer(256);
        sb.append("Mozilla/5.0 (Linux; U; Android");
        if (Build$VERSION.RELEASE != null) {
            sb.append(" ").append(Build$VERSION.RELEASE);
        }
        sb.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            sb.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                sb.append(" Build/").append(Build.DISPLAY);
            }
        }
        sb.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return sb.toString();
    }
    
    public static String dp() {
        final UUID randomUUID = UUID.randomUUID();
        final byte[] byteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        final byte[] byteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String s = new BigInteger(1, byteArray).toString();
        int n = 0;
    Label_0100_Outer:
        while (true) {
            if (n >= 2) {
                return s;
            }
            while (true) {
                try {
                    final MessageDigest instance = MessageDigest.getInstance("MD5");
                    instance.update(byteArray);
                    instance.update(byteArray2);
                    final byte[] array = new byte[8];
                    System.arraycopy(instance.digest(), 0, array, 0, 8);
                    s = new BigInteger(1, array).toString();
                    ++n;
                    continue Label_0100_Outer;
                }
                catch (NoSuchAlgorithmException ex) {
                    continue;
                }
                break;
            }
        }
    }
    
    public static boolean p(final Context context) {
        final Intent intent = new Intent();
        intent.setClassName(context, "com.google.android.gms.ads.AdActivity");
        final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            gs.W("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            return false;
        }
        boolean b;
        if ((resolveActivity.activityInfo.configChanges & 0x10) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboard"));
            b = false;
        }
        else {
            b = true;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x20) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "keyboardHidden"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x80) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "orientation"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x100) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenLayout"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x200) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "uiMode"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x400) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "screenSize"));
            b = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 0x800) == 0x0) {
            gs.W(String.format("com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".", "smallestScreenSize"));
            return false;
        }
        return b;
    }
    
    public static void q(final Context context) {
        if (gj.wp) {
            return;
        }
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver((BroadcastReceiver)new a(), intentFilter);
        gj.wp = true;
    }
    
    private static String r(final Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }
    
    public static int s(final Context context) {
        int n = 0;
        int top;
        if (context instanceof Activity) {
            final Window window = ((Activity)context).getWindow();
            final Rect rect = new Rect();
            window.getDecorView().getWindowVisibleDisplayFrame(rect);
            top = rect.top;
            n = window.findViewById(16908290).getTop() - top;
        }
        else {
            top = 0;
        }
        return n + top;
    }
    
    public static JSONObject t(final Map<String, ?> map) throws JSONException {
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
    
    public static int[] t(final Context context) {
        final WindowManager windowManager = (WindowManager)context.getSystemService("window");
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        final float n = 160.0f / displayMetrics.densityDpi;
        return new int[] { (int)(displayMetrics.widthPixels * n), (int)(n * displayMetrics.heightPixels) };
    }
    
    private static final class a extends BroadcastReceiver
    {
        public void onReceive(final Context context, final Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                gj.wn = true;
            }
            else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                gj.wn = false;
            }
        }
    }
}
