// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.database.Cursor;
import android.content.pm.ResolveInfo;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import java.util.UUID;
import android.content.ContentResolver;
import com.facebook.Settings;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.SessionDefaultAudience;
import android.content.Intent;
import android.os.Bundle;
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
    public static final String ACTION_FEED_DIALOG = "com.facebook.platform.action.request.FEED_DIALOG";
    public static final String ACTION_FEED_DIALOG_REPLY = "com.facebook.platform.action.reply.FEED_DIALOG";
    public static final String ACTION_LIKE_DIALOG = "com.facebook.platform.action.request.LIKE_DIALOG";
    public static final String ACTION_LIKE_DIALOG_REPLY = "com.facebook.platform.action.reply.LIKE_DIALOG";
    public static final String ACTION_MESSAGE_DIALOG = "com.facebook.platform.action.request.MESSAGE_DIALOG";
    public static final String ACTION_MESSAGE_DIALOG_REPLY = "com.facebook.platform.action.reply.MESSAGE_DIALOG";
    public static final String ACTION_OGACTIONPUBLISH_DIALOG = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    public static final String ACTION_OGACTIONPUBLISH_DIALOG_REPLY = "com.facebook.platform.action.reply.OGACTIONPUBLISH_DIALOG";
    public static final String ACTION_OGMESSAGEPUBLISH_DIALOG = "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    public static final String ACTION_OGMESSAGEPUBLISH_DIALOG_REPLY = "com.facebook.platform.action.reply.OGMESSAGEPUBLISH_DIALOG";
    public static final String AUDIENCE_EVERYONE = "everyone";
    public static final String AUDIENCE_FRIENDS = "friends";
    public static final String AUDIENCE_ME = "only_me";
    public static final String BRIDGE_ARG_ACTION_ID_STRING = "action_id";
    public static final String BRIDGE_ARG_APP_NAME_STRING = "app_name";
    public static final String BRIDGE_ARG_ERROR_BUNDLE = "error";
    public static final String BRIDGE_ARG_ERROR_CODE = "error_code";
    public static final String BRIDGE_ARG_ERROR_DESCRIPTION = "error_description";
    public static final String BRIDGE_ARG_ERROR_JSON = "error_json";
    public static final String BRIDGE_ARG_ERROR_SUBCODE = "error_subcode";
    public static final String BRIDGE_ARG_ERROR_TYPE = "error_type";
    private static final String CONTENT_SCHEME = "content://";
    public static final int DIALOG_REQUEST_CODE = 64207;
    public static final String ERROR_APPLICATION_ERROR = "ApplicationError";
    public static final String ERROR_NETWORK_ERROR = "NetworkError";
    public static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
    public static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
    public static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
    public static final String ERROR_UNKNOWN_ERROR = "UnknownError";
    public static final String ERROR_USER_CANCELED = "UserCanceled";
    public static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
    public static final String EXTRA_ACTION = "com.facebook.platform.extra.ACTION";
    public static final String EXTRA_ACTION_TYPE = "com.facebook.platform.extra.ACTION_TYPE";
    public static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    public static final String EXTRA_APPLICATION_NAME = "com.facebook.platform.extra.APPLICATION_NAME";
    public static final String EXTRA_DATA_FAILURES_FATAL = "com.facebook.platform.extra.DATA_FAILURES_FATAL";
    public static final String EXTRA_DESCRIPTION = "com.facebook.platform.extra.DESCRIPTION";
    public static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    public static final String EXTRA_FRIEND_TAGS = "com.facebook.platform.extra.FRIENDS";
    public static final String EXTRA_GET_INSTALL_DATA_PACKAGE = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
    public static final String EXTRA_IMAGE = "com.facebook.platform.extra.IMAGE";
    public static final String EXTRA_LIKE_COUNT_STRING_WITHOUT_LIKE = "com.facebook.platform.extra.LIKE_COUNT_STRING_WITHOUT_LIKE";
    public static final String EXTRA_LIKE_COUNT_STRING_WITH_LIKE = "com.facebook.platform.extra.LIKE_COUNT_STRING_WITH_LIKE";
    public static final String EXTRA_LINK = "com.facebook.platform.extra.LINK";
    public static final String EXTRA_OBJECT_ID = "com.facebook.platform.extra.OBJECT_ID";
    public static final String EXTRA_OBJECT_IS_LIKED = "com.facebook.platform.extra.OBJECT_IS_LIKED";
    public static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
    public static final String EXTRA_PHOTOS = "com.facebook.platform.extra.PHOTOS";
    public static final String EXTRA_PLACE_TAG = "com.facebook.platform.extra.PLACE";
    public static final String EXTRA_PREVIEW_PROPERTY_NAME = "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME";
    public static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
    public static final String EXTRA_PROTOCOL_BRIDGE_ARGS = "com.facebook.platform.protocol.BRIDGE_ARGS";
    public static final String EXTRA_PROTOCOL_CALL_ID = "com.facebook.platform.protocol.CALL_ID";
    public static final String EXTRA_PROTOCOL_METHOD_ARGS = "com.facebook.platform.protocol.METHOD_ARGS";
    public static final String EXTRA_PROTOCOL_METHOD_RESULTS = "com.facebook.platform.protocol.RESULT_ARGS";
    public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
    static final String EXTRA_PROTOCOL_VERSIONS = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
    public static final String EXTRA_REF = "com.facebook.platform.extra.REF";
    public static final String EXTRA_SOCIAL_SENTENCE_WITHOUT_LIKE = "com.facebook.platform.extra.SOCIAL_SENTENCE_WITHOUT_LIKE";
    public static final String EXTRA_SOCIAL_SENTENCE_WITH_LIKE = "com.facebook.platform.extra.SOCIAL_SENTENCE_WITH_LIKE";
    public static final String EXTRA_SUBTITLE = "com.facebook.platform.extra.SUBTITLE";
    public static final String EXTRA_TITLE = "com.facebook.platform.extra.TITLE";
    public static final String EXTRA_UNLIKE_TOKEN = "com.facebook.platform.extra.UNLIKE_TOKEN";
    private static final NativeProtocol$NativeAppInfo FACEBOOK_APP_INFO;
    private static final String FACEBOOK_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
    public static final String FACEBOOK_PROXY_AUTH_APP_ID_KEY = "client_id";
    public static final String FACEBOOK_PROXY_AUTH_E2E_KEY = "e2e";
    public static final String FACEBOOK_PROXY_AUTH_PERMISSIONS_KEY = "scope";
    private static final String FACEBOOK_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
    public static final String IMAGE_URL_KEY = "url";
    public static final String IMAGE_USER_GENERATED_KEY = "user_generated";
    static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
    static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    private static final List<Integer> KNOWN_PROTOCOL_VERSIONS;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 65537;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 65536;
    public static final int MESSAGE_GET_INSTALL_DATA_REPLY = 65541;
    public static final int MESSAGE_GET_INSTALL_DATA_REQUEST = 65540;
    public static final int MESSAGE_GET_LIKE_STATUS_REPLY = 65543;
    public static final int MESSAGE_GET_LIKE_STATUS_REQUEST = 65542;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REPLY = 65539;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REQUEST = 65538;
    public static final String METHOD_ARGS_ACTION = "ACTION";
    public static final String METHOD_ARGS_ACTION_TYPE = "ACTION_TYPE";
    public static final String METHOD_ARGS_DATA_FAILURES_FATAL = "DATA_FAILURES_FATAL";
    public static final String METHOD_ARGS_DESCRIPTION = "DESCRIPTION";
    public static final String METHOD_ARGS_FRIEND_TAGS = "FRIENDS";
    public static final String METHOD_ARGS_IMAGE = "IMAGE";
    public static final String METHOD_ARGS_LINK = "LINK";
    public static final String METHOD_ARGS_OBJECT_ID = "object_id";
    public static final String METHOD_ARGS_PHOTOS = "PHOTOS";
    public static final String METHOD_ARGS_PLACE_TAG = "PLACE";
    public static final String METHOD_ARGS_PREVIEW_PROPERTY_NAME = "PREVIEW_PROPERTY_NAME";
    public static final String METHOD_ARGS_REF = "REF";
    public static final String METHOD_ARGS_SUBTITLE = "SUBTITLE";
    public static final String METHOD_ARGS_TITLE = "TITLE";
    public static final String METHOD_ARGS_VIDEO = "VIDEO";
    public static final int NO_PROTOCOL_AVAILABLE = -1;
    public static final String OPEN_GRAPH_CREATE_OBJECT_KEY = "fbsdk:create_object";
    private static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
    private static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
    public static final int PROTOCOL_VERSION_20121101 = 20121101;
    public static final int PROTOCOL_VERSION_20130502 = 20130502;
    public static final int PROTOCOL_VERSION_20130618 = 20130618;
    public static final int PROTOCOL_VERSION_20131107 = 20131107;
    public static final int PROTOCOL_VERSION_20140204 = 20140204;
    public static final int PROTOCOL_VERSION_20140324 = 20140324;
    public static final int PROTOCOL_VERSION_20140701 = 20140701;
    public static final int PROTOCOL_VERSION_20141001 = 20141001;
    public static final int PROTOCOL_VERSION_20141028 = 20141028;
    public static final int PROTOCOL_VERSION_20141107 = 20141107;
    public static final String RESULT_ARGS_ACCESS_TOKEN = "access_token";
    public static final String RESULT_ARGS_EXPIRES_SECONDS_SINCE_EPOCH = "expires_seconds_since_epoch";
    public static final String RESULT_ARGS_PERMISSIONS = "permissions";
    public static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
    public static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
    public static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
    public static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
    public static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
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
    
    public static Intent createPlatformActivityIntent(final Context context, final String s, final String s2, final int n, final String s3, final Bundle bundle) {
        final Intent activityIntent = findActivityIntent(context, "com.facebook.platform.PLATFORM_ACTIVITY", s2);
        if (activityIntent == null) {
            return null;
        }
        activityIntent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", n).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", s2).putExtra("com.facebook.platform.extra.APPLICATION_ID", Utility.getMetadataApplicationId(context));
        if (isVersionCompatibleWithBucketedIntent(n)) {
            final Bundle bundle2 = new Bundle();
            bundle2.putString("action_id", s);
            bundle2.putString("app_name", s3);
            activityIntent.putExtra("com.facebook.platform.protocol.BRIDGE_ARGS", bundle2);
            Bundle bundle3;
            if ((bundle3 = bundle) == null) {
                bundle3 = new Bundle();
            }
            activityIntent.putExtra("com.facebook.platform.protocol.METHOD_ARGS", bundle3);
            return activityIntent;
        }
        activityIntent.putExtra("com.facebook.platform.protocol.CALL_ID", s);
        activityIntent.putExtra("com.facebook.platform.extra.APPLICATION_NAME", s3);
        activityIntent.putExtras(bundle);
        return activityIntent;
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
    
    private static Intent findActivityIntent(final Context context, final String action, final String s) {
        final List<NativeProtocol$NativeAppInfo> list = NativeProtocol.actionToAppInfoMap.get(s);
        Intent intent;
        if (list == null) {
            intent = null;
        }
        else {
            final Iterator<NativeProtocol$NativeAppInfo> iterator = list.iterator();
            intent = null;
            while (iterator.hasNext()) {
                final NativeProtocol$NativeAppInfo nativeProtocol$NativeAppInfo = iterator.next();
                final Intent validateActivityIntent = validateActivityIntent(context, new Intent().setAction(action).setPackage(nativeProtocol$NativeAppInfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeProtocol$NativeAppInfo);
                if ((intent = validateActivityIntent) != null) {
                    return validateActivityIntent;
                }
            }
        }
        return intent;
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
    
    public static Bundle getBridgeArgumentsFromIntent(final Intent intent) {
        if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
            return null;
        }
        return intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
    }
    
    public static UUID getCallIdFromIntent(final Intent intent) {
        if (intent != null) {
            while (true) {
                Label_0045: {
                    if (!isVersionCompatibleWithBucketedIntent(getProtocolVersionFromIntent(intent))) {
                        break Label_0045;
                    }
                    final Bundle bundleExtra = intent.getBundleExtra("com.facebook.platform.protocol.BRIDGE_ARGS");
                    if (bundleExtra == null) {
                        break Label_0045;
                    }
                    String s = bundleExtra.getString("action_id");
                    if (s == null) {
                        return null;
                    }
                    try {
                        return UUID.fromString(s);
                        s = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        return null;
                    }
                }
                String s = null;
                continue;
            }
        }
        return null;
    }
    
    public static Bundle getErrorDataFromResultIntent(final Intent intent) {
        if (!isErrorResult(intent)) {
            return null;
        }
        final Bundle bridgeArgumentsFromIntent = getBridgeArgumentsFromIntent(intent);
        if (bridgeArgumentsFromIntent != null) {
            return bridgeArgumentsFromIntent.getBundle("error");
        }
        return intent.getExtras();
    }
    
    public static Exception getExceptionFromErrorData(final Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        String s;
        if ((s = bundle.getString("error_type")) == null) {
            s = bundle.getString("com.facebook.platform.status.ERROR_TYPE");
        }
        String s2;
        if ((s2 = bundle.getString("error_description")) == null) {
            s2 = bundle.getString("com.facebook.platform.status.ERROR_DESCRIPTION");
        }
        if (s != null && s.equalsIgnoreCase("UserCanceled")) {
            return new FacebookOperationCanceledException(s2);
        }
        return new FacebookException(s2);
    }
    
    public static int getLatestAvailableProtocolVersionForAction(final Context context, final String s, final int[] array) {
        return getLatestAvailableProtocolVersionForAppInfoList(context, NativeProtocol.actionToAppInfoMap.get(s), array);
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
    
    public static int getProtocolVersionFromIntent(final Intent intent) {
        return intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
    }
    
    public static Bundle getSuccessResultsFromIntent(final Intent intent) {
        final int protocolVersionFromIntent = getProtocolVersionFromIntent(intent);
        final Bundle extras = intent.getExtras();
        if (!isVersionCompatibleWithBucketedIntent(protocolVersionFromIntent) || extras == null) {
            return extras;
        }
        return extras.getBundle("com.facebook.platform.protocol.RESULT_ARGS");
    }
    
    public static boolean isErrorResult(final Intent intent) {
        final Bundle bridgeArgumentsFromIntent = getBridgeArgumentsFromIntent(intent);
        if (bridgeArgumentsFromIntent != null) {
            return bridgeArgumentsFromIntent.containsKey("error");
        }
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }
    
    public static boolean isVersionCompatibleWithBucketedIntent(final int n) {
        return NativeProtocol.KNOWN_PROTOCOL_VERSIONS.contains(n) && n >= 20140701;
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
