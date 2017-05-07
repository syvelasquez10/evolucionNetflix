// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import bolts.Task$TaskCompletionSource;
import java.util.Iterator;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Map;
import bolts.Continuation;
import java.util.List;
import java.util.ArrayList;
import bolts.Task;
import org.json.JSONException;
import bolts.AppLink$Target;
import org.json.JSONObject;
import bolts.AppLink;
import android.net.Uri;
import java.util.HashMap;
import bolts.AppLinkResolver;

public class FacebookAppLinkResolver implements AppLinkResolver
{
    private static final String APP_LINK_ANDROID_TARGET_KEY = "android";
    private static final String APP_LINK_KEY = "app_links";
    private static final String APP_LINK_TARGET_APP_NAME_KEY = "app_name";
    private static final String APP_LINK_TARGET_CLASS_KEY = "class";
    private static final String APP_LINK_TARGET_PACKAGE_KEY = "package";
    private static final String APP_LINK_TARGET_SHOULD_FALLBACK_KEY = "should_fallback";
    private static final String APP_LINK_TARGET_URL_KEY = "url";
    private static final String APP_LINK_WEB_TARGET_KEY = "web";
    private final HashMap<Uri, AppLink> cachedAppLinks;
    
    public FacebookAppLinkResolver() {
        this.cachedAppLinks = new HashMap<Uri, AppLink>();
    }
    
    private static AppLink$Target getAndroidTargetFromJson(final JSONObject jsonObject) {
        final Uri uri = null;
        final String tryGetStringFromJson = tryGetStringFromJson(jsonObject, "package", null);
        if (tryGetStringFromJson == null) {
            return null;
        }
        final String tryGetStringFromJson2 = tryGetStringFromJson(jsonObject, "class", null);
        final String tryGetStringFromJson3 = tryGetStringFromJson(jsonObject, "app_name", null);
        final String tryGetStringFromJson4 = tryGetStringFromJson(jsonObject, "url", null);
        Uri parse = uri;
        if (tryGetStringFromJson4 != null) {
            parse = Uri.parse(tryGetStringFromJson4);
        }
        return new AppLink$Target(tryGetStringFromJson, tryGetStringFromJson2, parse, tryGetStringFromJson3);
    }
    
    private static Uri getWebFallbackUriFromJson(final Uri uri, JSONObject jsonObject) {
        final Uri uri2 = null;
        try {
            jsonObject = jsonObject.getJSONObject("web");
            if (!tryGetBooleanFromJson(jsonObject, "should_fallback", true)) {
                return null;
            }
            final String tryGetStringFromJson = tryGetStringFromJson(jsonObject, "url", null);
            Uri parse = uri2;
            if (tryGetStringFromJson != null) {
                parse = Uri.parse(tryGetStringFromJson);
            }
            if (parse != null) {
                return parse;
            }
        }
        catch (JSONException ex) {}
        return uri;
    }
    
    private static boolean tryGetBooleanFromJson(final JSONObject jsonObject, final String s, final boolean b) {
        try {
            return jsonObject.getBoolean(s);
        }
        catch (JSONException ex) {
            return b;
        }
    }
    
    private static String tryGetStringFromJson(final JSONObject jsonObject, final String s, final String s2) {
        try {
            return jsonObject.getString(s);
        }
        catch (JSONException ex) {
            return s2;
        }
    }
    
    @Override
    public Task<AppLink> getAppLinkFromUrlInBackground(final Uri uri) {
        final ArrayList<Uri> list = new ArrayList<Uri>();
        list.add(uri);
        return this.getAppLinkFromUrlsInBackground(list).onSuccess((Continuation<Map<Uri, AppLink>, AppLink>)new FacebookAppLinkResolver$1(this, uri));
    }
    
    public Task<Map<Uri, AppLink>> getAppLinkFromUrlsInBackground(final List<Uri> list) {
        final HashMap<Uri, AppLink> hashMap = new HashMap<Uri, AppLink>();
        final HashSet<Uri> set = new HashSet<Uri>();
        final StringBuilder sb = new StringBuilder();
        for (final Uri uri : list) {
            synchronized (this.cachedAppLinks) {
                final AppLink appLink = this.cachedAppLinks.get(uri);
                // monitorexit(this.cachedAppLinks)
                if (appLink != null) {
                    hashMap.put(uri, appLink);
                    continue;
                }
            }
            if (!set.isEmpty()) {
                sb.append(',');
            }
            sb.append(uri.toString());
            set.add(uri);
        }
        if (set.isEmpty()) {
            return (Task<Map<Uri, AppLink>>)Task.forResult(hashMap);
        }
        final Task$TaskCompletionSource create = Task.create();
        final Bundle bundle = new Bundle();
        bundle.putString("ids", sb.toString());
        bundle.putString("fields", String.format("%s.fields(%s,%s)", "app_links", "android", "web"));
        new Request(null, "", bundle, null, new FacebookAppLinkResolver$2(this, create, hashMap, set)).executeAsync();
        return (Task<Map<Uri, AppLink>>)create.getTask();
    }
}
