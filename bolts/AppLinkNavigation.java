// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import android.content.pm.PackageManager;
import java.util.Map;
import java.util.HashMap;
import org.json.JSONException;
import android.content.Intent;
import java.net.URL;
import android.net.Uri;
import android.util.SparseArray;
import org.json.JSONArray;
import java.util.List;
import java.util.Iterator;
import org.json.JSONObject;
import android.content.pm.ApplicationInfo;
import android.content.Context;
import android.os.Bundle;

public class AppLinkNavigation
{
    private static final String KEY_NAME_REFERER_APP_LINK = "referer_app_link";
    private static final String KEY_NAME_REFERER_APP_LINK_APP_NAME = "app_name";
    private static final String KEY_NAME_REFERER_APP_LINK_PACKAGE = "package";
    private static final String KEY_NAME_USER_AGENT = "user_agent";
    private static final String KEY_NAME_VERSION = "version";
    private static final String VERSION = "1.0";
    private static AppLinkResolver defaultResolver;
    private final AppLink appLink;
    private final Bundle appLinkData;
    private final Bundle extras;
    
    public AppLinkNavigation(final AppLink appLink, Bundle appLinkData, final Bundle bundle) {
        if (appLink == null) {
            throw new IllegalArgumentException("appLink must not be null.");
        }
        Object extras;
        if ((extras = appLinkData) == null) {
            extras = new Bundle();
        }
        if ((appLinkData = bundle) == null) {
            appLinkData = new Bundle();
        }
        this.appLink = appLink;
        this.extras = (Bundle)extras;
        this.appLinkData = (Bundle)appLinkData;
    }
    
    private Bundle buildAppLinkDataForNavigation(final Context context) {
        final Bundle bundle = new Bundle();
        final Bundle bundle2 = new Bundle();
        if (context != null) {
            final String packageName = context.getPackageName();
            if (packageName != null) {
                bundle2.putString("package", packageName);
            }
            final ApplicationInfo applicationInfo = context.getApplicationInfo();
            if (applicationInfo != null) {
                final String string = context.getString(applicationInfo.labelRes);
                if (string != null) {
                    bundle2.putString("app_name", string);
                }
            }
        }
        bundle.putAll(this.getAppLinkData());
        bundle.putString("target_url", this.getAppLink().getSourceUrl().toString());
        bundle.putString("version", "1.0");
        bundle.putString("user_agent", "Bolts Android 1.1.2");
        bundle.putBundle("referer_app_link", bundle2);
        bundle.putBundle("extras", this.getExtras());
        return bundle;
    }
    
    public static AppLinkResolver getDefaultResolver() {
        return AppLinkNavigation.defaultResolver;
    }
    
    private JSONObject getJSONForBundle(final Bundle bundle) {
        final JSONObject jsonObject = new JSONObject();
        for (final String s : bundle.keySet()) {
            jsonObject.put(s, this.getJSONValue(bundle.get(s)));
        }
        return jsonObject;
    }
    
    private Object getJSONValue(final Object o) {
        int i = 0;
        final int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = 0;
        final int n8 = 0;
        final int n9 = 0;
        Object jsonForBundle;
        if (o instanceof Bundle) {
            jsonForBundle = this.getJSONForBundle((Bundle)o);
        }
        else {
            if (o instanceof CharSequence) {
                return o.toString();
            }
            if (o instanceof List) {
                final JSONArray jsonArray = new JSONArray();
                final Iterator<Object> iterator = (Iterator<Object>)((List)o).iterator();
                while (iterator.hasNext()) {
                    jsonArray.put(this.getJSONValue(iterator.next()));
                }
                return jsonArray;
            }
            if (o instanceof SparseArray) {
                final JSONArray jsonArray2 = new JSONArray();
                final SparseArray sparseArray = (SparseArray)o;
                for (int j = n9; j < sparseArray.size(); ++j) {
                    jsonArray2.put(sparseArray.keyAt(j), this.getJSONValue(sparseArray.valueAt(j)));
                }
                return jsonArray2;
            }
            if (o instanceof Character) {
                return o.toString();
            }
            jsonForBundle = o;
            if (!(o instanceof Boolean)) {
                if (o instanceof Number) {
                    if (o instanceof Double || o instanceof Float) {
                        return ((Number)o).doubleValue();
                    }
                    return ((Number)o).longValue();
                }
                else {
                    if (o instanceof boolean[]) {
                        final JSONArray jsonArray3 = new JSONArray();
                        for (boolean[] array = (boolean[])o; i < array.length; ++i) {
                            jsonArray3.put(this.getJSONValue(array[i]));
                        }
                        return jsonArray3;
                    }
                    if (o instanceof char[]) {
                        final JSONArray jsonArray4 = new JSONArray();
                        final char[] array2 = (char[])o;
                        for (int length = array2.length, k = n; k < length; ++k) {
                            jsonArray4.put(this.getJSONValue(array2[k]));
                        }
                        return jsonArray4;
                    }
                    if (o instanceof CharSequence[]) {
                        final JSONArray jsonArray5 = new JSONArray();
                        final CharSequence[] array3 = (CharSequence[])o;
                        for (int length2 = array3.length, l = n2; l < length2; ++l) {
                            jsonArray5.put(this.getJSONValue(array3[l]));
                        }
                        return jsonArray5;
                    }
                    if (o instanceof double[]) {
                        final JSONArray jsonArray6 = new JSONArray();
                        final double[] array4 = (double[])o;
                        for (int length3 = array4.length, n10 = n3; n10 < length3; ++n10) {
                            jsonArray6.put(this.getJSONValue(array4[n10]));
                        }
                        return jsonArray6;
                    }
                    if (o instanceof float[]) {
                        final JSONArray jsonArray7 = new JSONArray();
                        final float[] array5 = (float[])o;
                        for (int length4 = array5.length, n11 = n4; n11 < length4; ++n11) {
                            jsonArray7.put(this.getJSONValue(array5[n11]));
                        }
                        return jsonArray7;
                    }
                    if (o instanceof int[]) {
                        final JSONArray jsonArray8 = new JSONArray();
                        final int[] array6 = (int[])o;
                        for (int length5 = array6.length, n12 = n5; n12 < length5; ++n12) {
                            jsonArray8.put(this.getJSONValue(array6[n12]));
                        }
                        return jsonArray8;
                    }
                    if (o instanceof long[]) {
                        final JSONArray jsonArray9 = new JSONArray();
                        final long[] array7 = (long[])o;
                        for (int length6 = array7.length, n13 = n6; n13 < length6; ++n13) {
                            jsonArray9.put(this.getJSONValue(array7[n13]));
                        }
                        return jsonArray9;
                    }
                    if (o instanceof short[]) {
                        final JSONArray jsonArray10 = new JSONArray();
                        final short[] array8 = (short[])o;
                        for (int length7 = array8.length, n14 = n7; n14 < length7; ++n14) {
                            jsonArray10.put(this.getJSONValue(array8[n14]));
                        }
                        return jsonArray10;
                    }
                    if (o instanceof String[]) {
                        final JSONArray jsonArray11 = new JSONArray();
                        final String[] array9 = (String[])o;
                        for (int length8 = array9.length, n15 = n8; n15 < length8; ++n15) {
                            jsonArray11.put(this.getJSONValue(array9[n15]));
                        }
                        return jsonArray11;
                    }
                    return null;
                }
            }
        }
        return jsonForBundle;
    }
    
    private static AppLinkResolver getResolver(final Context context) {
        if (getDefaultResolver() != null) {
            return getDefaultResolver();
        }
        return new WebViewAppLinkResolver(context);
    }
    
    public static AppLinkNavigation$NavigationResult navigate(final Context context, final AppLink appLink) {
        return new AppLinkNavigation(appLink, null, null).navigate(context);
    }
    
    public static Task<AppLinkNavigation$NavigationResult> navigateInBackground(final Context context, final Uri uri) {
        return navigateInBackground(context, uri, getResolver(context));
    }
    
    public static Task<AppLinkNavigation$NavigationResult> navigateInBackground(final Context context, final Uri uri, final AppLinkResolver appLinkResolver) {
        return appLinkResolver.getAppLinkFromUrlInBackground(uri).onSuccess((Continuation<AppLink, AppLinkNavigation$NavigationResult>)new AppLinkNavigation$1(context), Task.UI_THREAD_EXECUTOR);
    }
    
    public static Task<AppLinkNavigation$NavigationResult> navigateInBackground(final Context context, final String s) {
        return navigateInBackground(context, s, getResolver(context));
    }
    
    public static Task<AppLinkNavigation$NavigationResult> navigateInBackground(final Context context, final String s, final AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(s), appLinkResolver);
    }
    
    public static Task<AppLinkNavigation$NavigationResult> navigateInBackground(final Context context, final URL url) {
        return navigateInBackground(context, url, getResolver(context));
    }
    
    public static Task<AppLinkNavigation$NavigationResult> navigateInBackground(final Context context, final URL url, final AppLinkResolver appLinkResolver) {
        return navigateInBackground(context, Uri.parse(url.toString()), appLinkResolver);
    }
    
    private void sendAppLinkNavigateEventBroadcast(final Context context, final Intent intent, final AppLinkNavigation$NavigationResult appLinkNavigation$NavigationResult, final JSONException ex) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (ex != null) {
            hashMap.put("error", ex.getLocalizedMessage());
        }
        String s;
        if (appLinkNavigation$NavigationResult.isSucceeded()) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("success", s);
        hashMap.put("type", appLinkNavigation$NavigationResult.getCode());
        MeasurementEvent.sendBroadcastEvent(context, "al_nav_out", intent, hashMap);
    }
    
    public static void setDefaultResolver(final AppLinkResolver defaultResolver) {
        AppLinkNavigation.defaultResolver = defaultResolver;
    }
    
    public AppLink getAppLink() {
        return this.appLink;
    }
    
    public Bundle getAppLinkData() {
        return this.appLinkData;
    }
    
    public Bundle getExtras() {
        return this.extras;
    }
    
    public AppLinkNavigation$NavigationResult navigate(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        final Bundle buildAppLinkDataForNavigation = this.buildAppLinkDataForNavigation(context);
        while (true) {
            for (final AppLink$Target appLink$Target : this.getAppLink().getTargets()) {
                Object web = new Intent("android.intent.action.VIEW");
                if (appLink$Target.getUrl() != null) {
                    ((Intent)web).setData(appLink$Target.getUrl());
                }
                else {
                    ((Intent)web).setData(this.appLink.getSourceUrl());
                }
                ((Intent)web).setPackage(appLink$Target.getPackageName());
                if (appLink$Target.getClassName() != null) {
                    ((Intent)web).setClassName(appLink$Target.getPackageName(), appLink$Target.getClassName());
                }
                ((Intent)web).putExtra("al_applink_data", buildAppLinkDataForNavigation);
                if (packageManager.resolveActivity((Intent)web, 65536) != null) {
                    final AppLinkNavigation$NavigationResult failed = AppLinkNavigation$NavigationResult.FAILED;
                    Intent intent = null;
                    Label_0149: {
                        if (web != null) {
                            final AppLinkNavigation$NavigationResult app = AppLinkNavigation$NavigationResult.APP;
                            intent = (Intent)web;
                            web = app;
                        }
                        else {
                            final Uri webUrl = this.getAppLink().getWebUrl();
                            if (webUrl != null) {
                                try {
                                    intent = new Intent("android.intent.action.VIEW", webUrl.buildUpon().appendQueryParameter("al_applink_data", this.getJSONForBundle(buildAppLinkDataForNavigation).toString()).build());
                                    web = AppLinkNavigation$NavigationResult.WEB;
                                    break Label_0149;
                                }
                                catch (JSONException ex) {
                                    this.sendAppLinkNavigateEventBroadcast(context, (Intent)web, AppLinkNavigation$NavigationResult.FAILED, ex);
                                    throw new RuntimeException((Throwable)ex);
                                }
                            }
                            web = failed;
                            intent = null;
                        }
                    }
                    this.sendAppLinkNavigateEventBroadcast(context, intent, (AppLinkNavigation$NavigationResult)web, null);
                    if (intent != null) {
                        context.startActivity(intent);
                    }
                    return (AppLinkNavigation$NavigationResult)web;
                }
            }
            Object web = null;
            continue;
        }
    }
}
