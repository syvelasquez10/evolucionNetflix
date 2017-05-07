// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import java.util.Iterator;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.internal.Utility;
import java.util.List;
import android.content.Intent;
import java.util.ArrayList;
import android.content.Context;

final class NativeProtocol
{
    static final String ACTION_LOGIN_DIALOG = "com.facebook.platform.action.request.LOGIN_DIALOG";
    static final String ACTION_LOGIN_DIALOG_REPLY = "com.facebook.platform.action.reply.LOGIN_DIALOG";
    static final String AUDIENCE_EVERYONE = "EVERYONE";
    static final String AUDIENCE_FRIENDS = "ALL_FRIENDS";
    static final String AUDIENCE_ME = "SELF";
    private static final String BASIC_INFO = "basic_info";
    static final String ERROR_APPLICATION_ERROR = "ApplicationError";
    static final String ERROR_NETWORK_ERROR = "NetworkError";
    static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
    static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
    static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
    static final String ERROR_UNKNOWN_ERROR = "UnknownError";
    static final String ERROR_USER_CANCELED = "UserCanceled";
    static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
    static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
    static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
    static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
    static final String EXTRA_WRITE_PRIVACY = "com.facebook.platform.extra.WRITE_PRIVACY";
    static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
    static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    static final String KATANA_PACKAGE = "com.facebook.katana";
    static final String KATANA_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
    public static final String KATANA_PROXY_AUTH_APP_ID_KEY = "client_id";
    public static final String KATANA_PROXY_AUTH_PERMISSIONS_KEY = "scope";
    static final String KATANA_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
    static final String KATANA_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
    static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 65537;
    static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 65536;
    static final int PROTOCOL_VERSION_20121101 = 20121101;
    static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
    static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
    static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
    static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
    static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
    
    static Intent createLoginDialog20121101Intent(final Context context, final String s, final ArrayList<String> list, final String s2) {
        return validateKatanaActivityIntent(context, new Intent().setAction("com.facebook.platform.PLATFORM_ACTIVITY").addCategory("android.intent.category.DEFAULT").putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 20121101).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", "com.facebook.platform.action.request.LOGIN_DIALOG").putExtra("com.facebook.platform.extra.APPLICATION_ID", s).putStringArrayListExtra("com.facebook.platform.extra.PERMISSIONS", (ArrayList)ensureDefaultPermissions(list)).putExtra("com.facebook.platform.extra.WRITE_PRIVACY", ensureDefaultAudience(s2)));
    }
    
    static Intent createProxyAuthIntent(final Context context, final String s, final List<String> list) {
        final Intent putExtra = new Intent().setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth").putExtra("client_id", s);
        if (!Utility.isNullOrEmpty((Collection<Object>)list)) {
            putExtra.putExtra("scope", TextUtils.join((CharSequence)",", (Iterable)list));
        }
        return validateKatanaActivityIntent(context, putExtra);
    }
    
    static Intent createTokenRefreshIntent(final Context context) {
        final Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.platform.TokenRefreshService");
        return validateKatanaServiceIntent(context, intent);
    }
    
    private static String ensureDefaultAudience(final String s) {
        String s2 = s;
        if (Utility.isNullOrEmpty(s)) {
            s2 = "SELF";
        }
        return s2;
    }
    
    private static ArrayList<String> ensureDefaultPermissions(final ArrayList<String> list) {
        ArrayList<String> list2;
        if (Utility.isNullOrEmpty((Collection<Object>)list)) {
            list2 = new ArrayList<String>();
        }
        else {
            for (final String s : list) {
                final ArrayList<String> list3 = list;
                if (Session.isPublishPermission(s)) {
                    return list3;
                }
                if ("basic_info".equals(s)) {
                    return list;
                }
            }
            list2 = new ArrayList<String>(list);
        }
        list2.add("basic_info");
        return list2;
    }
    
    static AccessTokenSource getAccessTokenSourceFromNative(final Bundle bundle) {
        if (20121101L == bundle.getInt("com.facebook.platform.protocol.PROTOCOL_VERSION", 0)) {
            return AccessTokenSource.FACEBOOK_APPLICATION_NATIVE;
        }
        return AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    }
    
    static boolean isServiceDisabledResult20121101(final Intent intent) {
        final boolean b = false;
        final int intExtra = intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
        final String stringExtra = intent.getStringExtra("com.facebook.platform.status.ERROR_TYPE");
        boolean b2 = b;
        if (20121101 == intExtra) {
            b2 = b;
            if ("ServiceDisabled".equals(stringExtra)) {
                b2 = true;
            }
        }
        return b2;
    }
    
    static Intent validateKatanaActivityIntent(final Context context, Intent intent) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return null;
            }
            if (!validateSignature(context, resolveActivity.activityInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    static Intent validateKatanaServiceIntent(final Context context, Intent intent) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveService = context.getPackageManager().resolveService(intent, 0);
            if (resolveService == null) {
                return null;
            }
            if (!validateSignature(context, resolveService.serviceInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    static final boolean validateSignature(final Context context, final String s) {
        while (true) {
            final boolean b = false;
            while (true) {
                int n = 0;
                Label_0056: {
                    try {
                        final Signature[] signatures = context.getPackageManager().getPackageInfo(s, 64).signatures;
                        final int length = signatures.length;
                        n = 0;
                        boolean b2 = b;
                        if (n < length) {
                            if (!signatures[n].toCharsString().equals("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2")) {
                                break Label_0056;
                            }
                            b2 = true;
                        }
                        return b2;
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        return false;
                    }
                }
                ++n;
                continue;
            }
        }
    }
}
