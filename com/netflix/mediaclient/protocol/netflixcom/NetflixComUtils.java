// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.pm.ActivityInfo;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.content.ComponentName;
import com.netflix.mediaclient.Log;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.app.Activity;
import com.netflix.mediaclient.util.NumberUtils;
import java.util.Iterator;
import java.util.Set;
import com.netflix.mediaclient.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import android.net.Uri;

public class NetflixComUtils
{
    private static final String TAG = "NetflixComUtils";
    
    public static Map<String, String> getParameters(final Uri uri) {
        final Set queryParameterNames = uri.getQueryParameterNames();
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final String s : queryParameterNames) {
            if (StringUtils.isNotEmpty(s)) {
                final String queryParameter = uri.getQueryParameter(s);
                if (!StringUtils.isNotEmpty(queryParameter)) {
                    continue;
                }
                hashMap.put(s, queryParameter);
            }
        }
        return hashMap;
    }
    
    public static int getStartTimeFromParams(final Map<String, String> map) {
        final String s = map.get("t");
        if (NumberUtils.isPositiveWholeNumber(s)) {
            return NumberUtils.toIntegerSafely(s, -1);
        }
        return -1;
    }
    
    public static String getTrackId(final Uri uri) {
        return uri.getQueryParameter("trkid");
    }
    
    public static void launchBrowser(final Activity activity, final Uri uri) {
        final ActivityInfo activityInfo = null;
        final Intent intent = new Intent("android.intent.action.VIEW", uri);
        final ResolveInfo resolveActivity = activity.getPackageManager().resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://google.com")), 65536);
        final Iterator iterator = activity.getPackageManager().queryIntentActivities(intent, 64).iterator();
        ActivityInfo activityInfo2 = null;
        ActivityInfo activityInfo3 = null;
        ActivityInfo activityInfo4;
        while (true) {
            activityInfo4 = activityInfo;
            if (!iterator.hasNext()) {
                break;
            }
            final ResolveInfo resolveInfo = iterator.next();
            if (resolveInfo == null || resolveInfo.activityInfo == null || resolveInfo.activityInfo.packageName == null || resolveInfo.activityInfo.name == null) {
                Log.w("NetflixComUtils", "Found a weird null activityInfo. Skipping...");
            }
            else {
                if (resolveActivity != null && resolveActivity.activityInfo != null && resolveInfo.activityInfo.name.equals(resolveActivity.activityInfo.name) && resolveInfo.activityInfo.packageName.equals(resolveActivity.activityInfo.packageName)) {
                    activityInfo4 = resolveInfo.activityInfo;
                    break;
                }
                ActivityInfo activityInfo6;
                ActivityInfo activityInfo7;
                if (resolveInfo.activityInfo.packageName.contains("chrome")) {
                    final ActivityInfo activityInfo5 = resolveInfo.activityInfo;
                    activityInfo6 = activityInfo3;
                    activityInfo7 = activityInfo5;
                }
                else if (activityInfo3 == null && !resolveInfo.activityInfo.packageName.contains("com.netflix")) {
                    final ActivityInfo activityInfo8 = resolveInfo.activityInfo;
                    activityInfo7 = activityInfo2;
                    activityInfo6 = activityInfo8;
                }
                else {
                    final ActivityInfo activityInfo9 = activityInfo3;
                    activityInfo7 = activityInfo2;
                    activityInfo6 = activityInfo9;
                }
                final ActivityInfo activityInfo10 = activityInfo6;
                activityInfo2 = activityInfo7;
                activityInfo3 = activityInfo10;
            }
        }
        if (activityInfo4 != null) {
            intent.setComponent(new ComponentName(activityInfo4.packageName, activityInfo4.name));
        }
        else if (activityInfo2 != null) {
            intent.setComponent(new ComponentName(activityInfo2.packageName, activityInfo2.name));
        }
        else if (activityInfo3 != null) {
            intent.setComponent(new ComponentName(activityInfo3.packageName, activityInfo3.name));
        }
        else {
            ErrorLoggingManager.logHandledException("Didn't find right activity to handle: " + uri);
        }
        Log.d("NetflixComUtils", intent);
        intent.addFlags(268435456);
        activity.startActivity(intent);
    }
    
    public static void startHomeActivity(final NetflixActivity netflixActivity) {
        netflixActivity.startActivity(HomeActivity.createStartIntent(netflixActivity));
        netflixActivity.overridePendingTransition(0, 0);
    }
}
