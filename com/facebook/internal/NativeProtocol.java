// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.database.Cursor;
import android.content.pm.ResolveInfo;
import android.content.ContentResolver;
import com.facebook.Settings;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.SessionDefaultAudience;
import android.content.Intent;
import android.content.Context;
import java.util.Iterator;
import java.util.TreeSet;
import android.net.Uri;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
import java.util.List;

public final class NativeProtocol
{
    private static final NativeProtocol$NativeAppInfo FACEBOOK_APP_INFO;
    private static final List<Integer> KNOWN_PROTOCOL_VERSIONS;
    private static Map<String, List<NativeProtocol$NativeAppInfo>> actionToAppInfoMap;
    private static List<NativeProtocol$NativeAppInfo> facebookAppInfoList;
    
    static {
        FACEBOOK_APP_INFO = new NativeProtocol$KatanaAppInfo(null);
        NativeProtocol.facebookAppInfoList = buildFacebookAppList();
        NativeProtocol.actionToAppInfoMap = buildActionToAppInfoMap();
        KNOWN_PROTOCOL_VERSIONS = Arrays.asList(20141107, 20141028, 20141001, 20140701, 20140324, 20140204, 20131107, 20130618, 20130502, 20121101);
    }
    
    private static Map<String, List<NativeProtocol$NativeAppInfo>> buildActionToAppInfoMap() {
        final HashMap<String, ArrayList<NativeProtocol$MessengerAppInfo>> hashMap = (HashMap<String, ArrayList<NativeProtocol$MessengerAppInfo>>)new HashMap<String, List<NativeProtocol$MessengerAppInfo>>();
        final ArrayList<NativeProtocol$MessengerAppInfo> list = new ArrayList<NativeProtocol$MessengerAppInfo>();
        list.add(new NativeProtocol$MessengerAppInfo(null));
        hashMap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", (List<NativeProtocol$MessengerAppInfo>)NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.FEED_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.LIKE_DIALOG", NativeProtocol.facebookAppInfoList);
        hashMap.put("com.facebook.platform.action.request.MESSAGE_DIALOG", list);
        hashMap.put("com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG", list);
        return (Map<String, List<NativeProtocol$NativeAppInfo>>)hashMap;
    }
    
    private static List<NativeProtocol$NativeAppInfo> buildFacebookAppList() {
        final ArrayList<NativeProtocol$WakizashiAppInfo> list = (ArrayList<NativeProtocol$WakizashiAppInfo>)new ArrayList<NativeProtocol$NativeAppInfo>();
        list.add(NativeProtocol.FACEBOOK_APP_INFO);
        list.add(new NativeProtocol$WakizashiAppInfo(null));
        return (List<NativeProtocol$NativeAppInfo>)list;
    }
    
    private static Uri buildPlatformProviderVersionURI(final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo) {
        return Uri.parse("content://" + nativeProtocol$NativeAppInfo.getPackage() + ".provider.PlatformProvider/versions");
    }
    
    public static int computeLatestAvailableVersionFromVersionSpec(final TreeSet<Integer> set, final int n, final int[] array) {
        final int length = array.length;
        final Iterator<Integer> descendingIterator = set.descendingIterator();
        int max = -1;
        int n2 = length - 1;
        while (descendingIterator.hasNext()) {
            final int intValue = descendingIterator.next();
            max = Math.max(max, intValue);
            while (n2 >= 0 && array[n2] > intValue) {
                --n2;
            }
            if (n2 < 0) {
                break;
            }
            if (array[n2] != intValue) {
                continue;
            }
            if (n2 % 2 == 0) {
                return Math.min(max, n);
            }
            break;
        }
        return -1;
    }
    
    public static Intent createPlatformServiceIntent(final Context context) {
        for (final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateServiceIntent = validateServiceIntent(context, new Intent("com.facebook.platform.PLATFORM_SERVICE").setPackage(nativeProtocol$NativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeProtocol$NativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }
    
    public static Intent createProxyAuthIntent(final Context context, final String s, final List<String> list, final String s2, final boolean b, final SessionDefaultAudience sessionDefaultAudience) {
        for (final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent putExtra = new Intent().setClassName(nativeProtocol$NativeAppInfo.getPackage(), "com.facebook.katana.ProxyAuth").putExtra("client_id", s);
            if (!Utility.isNullOrEmpty((Collection<Object>)list)) {
                putExtra.putExtra("scope", TextUtils.join((CharSequence)",", (Iterable)list));
            }
            if (!Utility.isNullOrEmpty(s2)) {
                putExtra.putExtra("e2e", s2);
            }
            putExtra.putExtra("response_type", "token");
            putExtra.putExtra("return_scopes", "true");
            putExtra.putExtra("default_audience", sessionDefaultAudience.getNativeProtocolAudience());
            if (!Settings.getPlatformCompatibilityEnabled()) {
                putExtra.putExtra("legacy_override", "v2.2");
                if (b) {
                    putExtra.putExtra("auth_type", "rerequest");
                }
            }
            final Intent validateActivityIntent = validateActivityIntent(context, putExtra, nativeProtocol$NativeAppInfo);
            if (validateActivityIntent != null) {
                return validateActivityIntent;
            }
        }
        return null;
    }
    
    public static Intent createTokenRefreshIntent(final Context context) {
        for (final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo : NativeProtocol.facebookAppInfoList) {
            final Intent validateServiceIntent = validateServiceIntent(context, new Intent().setClassName(nativeProtocol$NativeAppInfo.getPackage(), "com.facebook.katana.platform.TokenRefreshService"), nativeProtocol$NativeAppInfo);
            if (validateServiceIntent != null) {
                return validateServiceIntent;
            }
        }
        return null;
    }
    
    private static TreeSet<Integer> getAllAvailableProtocolVersionsForAppInfo(Context query, final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo) {
        final TreeSet<Integer> set = new TreeSet<Integer>();
        final ContentResolver contentResolver = query.getContentResolver();
        final Uri buildPlatformProviderVersionURI = buildPlatformProviderVersionURI(nativeProtocol$NativeAppInfo);
        while (true) {
            try {
                query = (Context)contentResolver.query(buildPlatformProviderVersionURI, new String[] { "version" }, (String)null, (String[])null, (String)null);
                Label_0093: {
                    if (query != null) {
                        Object o;
                        try {
                            while (((Cursor)query).moveToNext()) {
                                set.add(((Cursor)query).getInt(((Cursor)query).getColumnIndex("version")));
                            }
                            break Label_0093;
                        }
                        finally {
                            o = query;
                            query = (Context)set;
                        }
                        if (o != null) {
                            ((Cursor)o).close();
                        }
                        throw query;
                    }
                }
                if (query != null) {
                    ((Cursor)query).close();
                }
                return set;
            }
            finally {
                final Object o = null;
                continue;
            }
            break;
        }
    }
    
    private static int getLatestAvailableProtocolVersionForAppInfo(final Context context, final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo, final int[] array) {
        return computeLatestAvailableVersionFromVersionSpec(getAllAvailableProtocolVersionsForAppInfo(context, nativeProtocol$NativeAppInfo), getLatestKnownVersion(), array);
    }
    
    private static int getLatestAvailableProtocolVersionForAppInfoList(final Context context, final List<NativeProtocol$NativeAppInfo> list, final int[] array) {
        if (list == null) {
            return -1;
        }
        final Iterator<NativeProtocol$NativeAppInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            final int latestAvailableProtocolVersionForAppInfo = getLatestAvailableProtocolVersionForAppInfo(context, iterator.next(), array);
            if (latestAvailableProtocolVersionForAppInfo != -1) {
                return latestAvailableProtocolVersionForAppInfo;
            }
        }
        return -1;
    }
    
    public static int getLatestAvailableProtocolVersionForService(final Context context, final int n) {
        return getLatestAvailableProtocolVersionForAppInfoList(context, NativeProtocol.facebookAppInfoList, new int[] { n });
    }
    
    public static final int getLatestKnownVersion() {
        return NativeProtocol.KNOWN_PROTOCOL_VERSIONS.get(0);
    }
    
    static Intent validateActivityIntent(final Context context, Intent intent, final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return null;
            }
            if (!nativeProtocol$NativeAppInfo.validateSignature(context, resolveActivity.activityInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    static Intent validateServiceIntent(final Context context, Intent intent, final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null) {
                return null;
            }
            if (!nativeProtocol$NativeAppInfo.validateSignature(context, resolveService.serviceInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
}
