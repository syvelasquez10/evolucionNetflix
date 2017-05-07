// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import android.accounts.AccountManager;
import java.net.URISyntaxException;
import android.content.ContentResolver;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.List;
import android.os.RemoteException;
import java.io.IOException;
import android.util.Log;
import com.google.android.gms.internal.r$a;
import android.content.ServiceConnection;
import com.google.android.gms.common.a;
import com.google.android.gms.common.internal.n;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import com.google.android.gms.internal.if;
import android.os.Bundle;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.content.Context;
import android.os.Build$VERSION;
import android.content.Intent;
import android.content.ComponentName;

public final class GoogleAuthUtil
{
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    private static final ComponentName Dn;
    private static final ComponentName Do;
    private static final Intent Dp;
    private static final Intent Dq;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_ANDROID_PACKAGE_NAME;
    public static final String KEY_CALLER_UID;
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    @Deprecated
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {}
        KEY_CALLER_UID = "callerUid";
        if (Build$VERSION.SDK_INT >= 14) {}
        KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
        Dn = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        Do = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
        Dp = new Intent().setPackage("com.google.android.gms").setComponent(GoogleAuthUtil.Dn);
        Dq = new Intent().setPackage("com.google.android.gms").setComponent(GoogleAuthUtil.Do);
    }
    
    private static void D(final Context context) {
        try {
            GooglePlayServicesUtil.D(context);
        }
        catch (GooglePlayServicesRepairableException ex) {
            throw new GooglePlayServicesAvailabilityException(ex.getConnectionStatusCode(), ex.getMessage(), ex.getIntent());
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            throw new GoogleAuthException(ex2.getMessage());
        }
    }
    
    private static String a(final Context context, String token, final String s, final Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        try {
            token = getToken(context, token, s, bundle2);
            return token;
        }
        catch (GooglePlayServicesAvailabilityException ex) {
            final int connectionStatusCode = ex.getConnectionStatusCode();
            if (b(context, connectionStatusCode)) {
                final GoogleAuthUtil$a googleAuthUtil$a = new GoogleAuthUtil$a(context.getApplicationContext());
                googleAuthUtil$a.sendMessageDelayed(googleAuthUtil$a.obtainMessage(1), 30000L);
            }
            else {
                GooglePlayServicesUtil.showErrorNotification(connectionStatusCode, context);
            }
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        catch (UserRecoverableAuthException ex2) {
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
    }
    
    private static boolean aw(final String s) {
        return "NetworkError".equals(s) || "ServiceUnavailable".equals(s) || "Timeout".equals(s);
    }
    
    private static boolean ax(final String s) {
        return "BadAuthentication".equals(s) || "CaptchaRequired".equals(s) || "DeviceManagementRequiredOrSyncDisabled".equals(s) || "NeedPermission".equals(s) || "NeedsBrowser".equals(s) || "UserCancel".equals(s) || "AppDownloadRequired".equals(s) || if.DT.fu().equals(s) || if.DU.fu().equals(s) || if.DV.fu().equals(s) || if.DW.fu().equals(s) || if.DX.fu().equals(s) || if.DY.fu().equals(s);
    }
    
    private static boolean b(final Context context, final int n) {
        if (n == 1) {
            final PackageManager packageManager = context.getPackageManager();
            try {
                if (packageManager.getApplicationInfo("com.google.android.gms", 8192).enabled) {
                    return true;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
        return false;
    }
    
    public static void clearToken(Context context, final String s) {
        final Context applicationContext = context.getApplicationContext();
        n.aU("Calling this from your main thread can lead to deadlock");
        D(applicationContext);
        final Bundle bundle = new Bundle();
        final String packageName = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", packageName);
        if (!bundle.containsKey(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME)) {
            bundle.putString(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME, packageName);
        }
        context = (Context)new a();
        if (applicationContext.bindService(GoogleAuthUtil.Dp, (ServiceConnection)context, 1)) {
            try {
                final Bundle a = r$a.a(((a)context).fX()).a(s, bundle);
                final String string = a.getString(if.Ev);
                if (!a.getBoolean("booleanResult")) {
                    throw new GoogleAuthException(string);
                }
                goto Label_0148;
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
        }
        throw new IOException("Could not bind to service with the given context.");
    }
    
    public static List<AccountChangeEvent> getAccountChangeEvents(Context applicationContext, final int eventIndex, final String accountName) {
        n.b(accountName, (Object)"accountName must be provided");
        n.aU("Calling this from your main thread can lead to deadlock");
        applicationContext = applicationContext.getApplicationContext();
        D(applicationContext);
        final a a = new a();
        if (applicationContext.bindService(GoogleAuthUtil.Dp, (ServiceConnection)a, 1)) {
            try {
                return r$a.a(a.fX()).a(new AccountChangeEventsRequest().setAccountName(accountName).setEventIndex(eventIndex)).getEvents();
            }
            catch (RemoteException ex) {
                Log.i("GoogleAuthUtil", "GMS remote exception ", (Throwable)ex);
                throw new IOException("remote exception");
            }
            catch (InterruptedException ex2) {
                throw new GoogleAuthException("Interrupted");
            }
            finally {
                applicationContext.unbindService((ServiceConnection)a);
            }
        }
        throw new IOException("Could not bind to service with the given context.");
    }
    
    public static String getAccountId(final Context context, final String s) {
        n.b(s, (Object)"accountName must be provided");
        n.aU("Calling this from your main thread can lead to deadlock");
        D(context.getApplicationContext());
        return getToken(context, s, "^^_account_id_^^", new Bundle());
    }
    
    public static String getAppCert(final Context context, final String s) {
        return "spatula";
    }
    
    public static String getToken(final Context context, final String s, final String s2) {
        return getToken(context, s, s2, new Bundle());
    }
    
    public static String getToken(Context context, final String s, String s2, Bundle bundle) {
        final Context applicationContext = context.getApplicationContext();
        n.aU("Calling this from your main thread can lead to deadlock");
        D(applicationContext);
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
                if (!applicationContext.bindService(GoogleAuthUtil.Dp, (ServiceConnection)context, 1)) {
                    throw new IOException("Could not bind to service with the given context.");
                }
                try {
                    final Bundle a = r$a.a(((a)context).fX()).a(s, s2, bundle);
                    s2 = a.getString("authtoken");
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        return s2;
                    }
                    s2 = a.getString("Error");
                    final Intent intent = (Intent)a.getParcelable("userRecoveryIntent");
                    if (ax(s2)) {
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
    
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle) {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putBoolean("handle_notification", true);
        return a(context, s, s2, bundle2);
    }
    
    public static String getTokenWithNotification(final Context context, final String s, final String s2, final Bundle bundle, final Intent intent) {
        h(intent);
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        bundle2.putParcelable("callback_intent", (Parcelable)intent);
        bundle2.putBoolean("handle_notification", true);
        return a(context, s, s2, bundle2);
    }
    
    public static String getTokenWithNotification(final Context context, final String s, final String s2, Bundle o, final String s3, final Bundle bundle) {
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
    
    private static void h(final Intent intent) {
        if (intent == null) {
            throw new IllegalArgumentException("Callback cannot be null.");
        }
        final String uri = intent.toUri(1);
        try {
            Intent.parseUri(uri, 1);
        }
        catch (URISyntaxException ex) {
            throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
        }
    }
    
    @Deprecated
    public static void invalidateToken(final Context context, final String s) {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }
}
