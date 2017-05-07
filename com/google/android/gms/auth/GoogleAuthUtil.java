// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.o;
import android.content.ServiceConnection;
import com.google.android.gms.common.a;
import com.google.android.gms.internal.eg;
import java.net.URISyntaxException;
import java.io.IOException;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.app.PendingIntent;
import android.content.pm.PackageManager$NameNotFoundException;
import android.app.NotificationManager;
import android.text.TextUtils;
import android.app.Notification;
import com.google.android.gms.R;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Bundle;
import android.content.Context;
import android.os.Build$VERSION;
import android.content.Intent;
import android.content.ComponentName;

public final class GoogleAuthUtil
{
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID;
    public static final String KEY_CLIENT_PACKAGE_NAME = "clientPackageName";
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    @Deprecated
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String OEM_ONLY_KEY_TARGET_ANDROID_ID = "oauth2_target_device_id";
    public static final String OEM_ONLY_KEY_VERIFIER = "oauth2_authcode_verifier";
    public static final String OEM_ONLY_SCOPE_ACCOUNT_BOOTSTRAP = "_account_setup";
    private static final ComponentName kb;
    private static final ComponentName kc;
    private static final Intent kd;
    private static final Intent ke;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {}
        KEY_CALLER_UID = "callerUid";
        if (Build$VERSION.SDK_INT >= 14) {}
        KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
        kb = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        kc = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
        kd = new Intent().setPackage("com.google.android.gms").setComponent(GoogleAuthUtil.kb);
        ke = new Intent().setPackage("com.google.android.gms").setComponent(GoogleAuthUtil.kc);
    }
    
    private static String a(final Context context, String s, String s2, final Bundle ex) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        Object o = ex;
        if (ex == null) {
            o = new Bundle();
        }
        try {
            s = getToken(context, s, s2, (Bundle)o);
            return s;
        }
        catch (GooglePlayServicesAvailabilityException ex) {
            final PendingIntent errorPendingIntent = GooglePlayServicesUtil.getErrorPendingIntent(ex.getConnectionStatusCode(), context, 0);
            final Resources resources = context.getResources();
            final Notification notification = new Notification(17301642, (CharSequence)resources.getString(R.string.auth_client_play_services_err_notification_msg), System.currentTimeMillis());
            notification.flags |= 0x10;
            s2 = (s = context.getApplicationInfo().name);
            Label_0136: {
                if (!TextUtils.isEmpty((CharSequence)s2)) {
                    break Label_0136;
                }
            Label_0189_Outer:
                while (true) {
                    s = context.getPackageName();
                    final PackageManager packageManager = context.getApplicationContext().getPackageManager();
                    while (true) {
                        Label_0253: {
                            Label_0245: {
                                while (true) {
                                    try {
                                        final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 0);
                                        if (applicationInfo != null) {
                                            s = packageManager.getApplicationLabel(applicationInfo).toString();
                                        }
                                        s = resources.getString(R.string.auth_client_requested_by_msg, new Object[] { s });
                                        switch (ex.getConnectionStatusCode()) {
                                            default: {
                                                final int n = R.string.auth_client_using_bad_version_title;
                                                notification.setLatestEventInfo(context, (CharSequence)resources.getString(n), (CharSequence)s, errorPendingIntent);
                                                ((NotificationManager)context.getSystemService("notification")).notify(39789, notification);
                                                throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
                                            }
                                            case 1: {
                                                break;
                                            }
                                            case 2: {
                                                break Label_0245;
                                            }
                                            case 3: {
                                                break Label_0253;
                                            }
                                        }
                                    }
                                    catch (PackageManager$NameNotFoundException ex2) {
                                        final ApplicationInfo applicationInfo = null;
                                        continue Label_0189_Outer;
                                    }
                                    break;
                                }
                                final int n = R.string.auth_client_needs_installation_title;
                                continue;
                            }
                            final int n = R.string.auth_client_needs_update_title;
                            continue;
                        }
                        final int n = R.string.auth_client_needs_enabling_title;
                        continue;
                    }
                }
            }
        }
        catch (UserRecoverableAuthException ex3) {
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
    }
    
    private static void b(final Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callack cannot be null.");
        }
        final String uri = intent.toUri(1);
        try {
            Intent.parseUri(uri, 1);
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }
    
    public static String getToken(final Context context, final String s, final String s2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, s, s2, new Bundle());
    }
    
    public static String getToken(Context context, final String s, String s2, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        final Context applicationContext = context.getApplicationContext();
        eg.O("Calling this from your main thread can lead to deadlock");
        m(applicationContext);
        Label_0125: {
            if (bundle != null) {
                break Label_0125;
            }
            bundle = new Bundle();
            while (true) {
                final String packageName = context.getApplicationInfo().packageName;
                bundle.putString("clientPackageName", packageName);
                if (!bundle.containsKey(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME)) {
                    bundle.putString(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME, packageName);
                }
                context = (Context)new a();
                if (!applicationContext.bindService(GoogleAuthUtil.kd, (ServiceConnection)context, 1)) {
                    throw new IOException("Could not bind to service with the given context.");
                }
                try {
                    final Bundle a = o.a.a(((a)context).bg()).a(s, s2, bundle);
                    s2 = a.getString("authtoken");
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        return s2;
                    }
                    s2 = a.getString("Error");
                    final Intent intent = (Intent)a.getParcelable("userRecoveryIntent");
                    if (x(s2)) {
                        throw new UserRecoverableAuthException(s2, intent);
                    }
                    goto Label_0205;
                    bundle = new Bundle(bundle);
                    continue;
                }
                catch (RemoteException ex) {
                    try {
                        Log.i("GoogleAuthUtil", "GMS remote exception ", (Throwable)ex);
                        throw new IOException("remote exception");
                    }
                    finally {
                        applicationContext.unbindService((ServiceConnection)context);
                    }
                }
                catch (InterruptedException ex2) {
                    throw new GoogleAuthException("Interrupted");
                }
                break;
            }
        }
        throw new GoogleAuthException(s2);
    }
    
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putBoolean("handle_notification", true);
        return a(context, s, s2, bundle2);
    }
    
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle, final Intent intent) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        b(intent);
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putParcelable("callback_intent", (Parcelable)intent);
        bundle2.putBoolean("handle_notification", true);
        return a(context, s, s2, bundle2);
    }
    
    public static String getTokenWithNotification(final Context context, final String s, final String s2, Bundle o, final String s3, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        if (TextUtils.isEmpty((CharSequence)s3)) {
            throw new IllegalArgumentException("Authority cannot be empty or null.");
        }
        Object o2;
        if ((o2 = o) == null) {
            o2 = new Bundle();
        }
        if ((o = bundle) == null) {
            o = new Bundle();
        }
        ContentResolver.validateSyncExtrasBundle((Bundle)o);
        ((Bundle)o2).putString("authority", s3);
        ((Bundle)o2).putBundle("sync_extras", (Bundle)o);
        ((Bundle)o2).putBoolean("handle_notification", true);
        return a(context, s, s2, (Bundle)o2);
    }
    
    public static void invalidateToken(final Context context, final String s) {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }
    
    private static void m(final Context context) throws GooglePlayServicesAvailabilityException, GoogleAuthException {
        try {
            GooglePlayServicesUtil.m(context);
        }
        catch (GooglePlayServicesRepairableException ex) {
            throw new GooglePlayServicesAvailabilityException(ex.getConnectionStatusCode(), ex.getMessage(), ex.getIntent());
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            throw new GoogleAuthException(ex2.getMessage());
        }
    }
    
    private static boolean w(final String s) {
        return "NetworkError".equals(s) || "ServiceUnavailable".equals(s) || "Timeout".equals(s);
    }
    
    private static boolean x(final String s) {
        return "BadAuthentication".equals(s) || "CaptchaRequired".equals(s) || "DeviceManagementRequiredOrSyncDisabled".equals(s) || "NeedPermission".equals(s) || "NeedsBrowser".equals(s) || "UserCancel".equals(s) || "AppDownloadRequired".equals(s);
    }
}
