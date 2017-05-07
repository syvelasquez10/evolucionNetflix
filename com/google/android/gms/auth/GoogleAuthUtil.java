// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.os.Parcelable;
import android.text.TextUtils;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.internal.el;
import com.google.android.gms.internal.s;
import android.content.ServiceConnection;
import com.google.android.gms.common.a;
import com.google.android.gms.internal.fq;
import java.net.URISyntaxException;
import java.io.IOException;
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
    public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
    @Deprecated
    public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    private static final ComponentName wM;
    private static final ComponentName wN;
    private static final Intent wO;
    private static final Intent wP;
    
    static {
        if (Build$VERSION.SDK_INT >= 11) {}
        KEY_CALLER_UID = "callerUid";
        if (Build$VERSION.SDK_INT >= 14) {}
        KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
        wM = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        wN = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
        wO = new Intent().setPackage("com.google.android.gms").setComponent(GoogleAuthUtil.wM);
        wP = new Intent().setPackage("com.google.android.gms").setComponent(GoogleAuthUtil.wN);
    }
    
    private static boolean P(final String s) {
        return "NetworkError".equals(s) || "ServiceUnavailable".equals(s) || "Timeout".equals(s);
    }
    
    private static boolean Q(final String s) {
        return "BadAuthentication".equals(s) || "CaptchaRequired".equals(s) || "DeviceManagementRequiredOrSyncDisabled".equals(s) || "NeedPermission".equals(s) || "NeedsBrowser".equals(s) || "UserCancel".equals(s) || "AppDownloadRequired".equals(s);
    }
    
    private static String a(final Context context, String token, final String s, final Bundle bundle) throws IOException, UserRecoverableNotifiedException, GoogleAuthException {
        Bundle bundle2 = bundle;
        if (bundle == null) {
            bundle2 = new Bundle();
        }
        try {
            token = getToken(context, token, s, bundle2);
            return token;
        }
        catch (GooglePlayServicesAvailabilityException ex) {
            GooglePlayServicesUtil.showErrorNotification(ex.getConnectionStatusCode(), context);
            throw new UserRecoverableNotifiedException("User intervention required. Notification has been pushed.");
        }
        catch (UserRecoverableAuthException ex2) {
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
    
    public static void clearToken(Context context, final String s) throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException {
        final Context applicationContext = context.getApplicationContext();
        fq.ak("Calling this from your main thread can lead to deadlock");
        s(applicationContext);
        final Bundle bundle = new Bundle();
        final String packageName = context.getApplicationInfo().packageName;
        bundle.putString("clientPackageName", packageName);
        if (!bundle.containsKey(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME)) {
            bundle.putString(GoogleAuthUtil.KEY_ANDROID_PACKAGE_NAME, packageName);
        }
        context = (Context)new a();
        if (applicationContext.bindService(GoogleAuthUtil.wO, (ServiceConnection)context, 1)) {
            try {
                final Bundle a = com.google.android.gms.internal.s.a.a(((a)context).dV()).a(s, bundle);
                final String string = a.getString(el.xD);
                if (!a.getBoolean("booleanResult")) {
                    throw new GoogleAuthException(string);
                }
                goto Label_0144;
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
    
    public static String getToken(final Context context, final String s, final String s2) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return getToken(context, s, s2, new Bundle());
    }
    
    public static String getToken(Context context, final String s, String s2, Bundle bundle) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        final Context applicationContext = context.getApplicationContext();
        fq.ak("Calling this from your main thread can lead to deadlock");
        s(applicationContext);
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
                if (!applicationContext.bindService(GoogleAuthUtil.wO, (ServiceConnection)context, 1)) {
                    throw new IOException("Could not bind to service with the given context.");
                }
                try {
                    final Bundle a = com.google.android.gms.internal.s.a.a(((a)context).dV()).a(s, s2, bundle);
                    s2 = a.getString("authtoken");
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        return s2;
                    }
                    s2 = a.getString("Error");
                    final Intent intent = (Intent)a.getParcelable("userRecoveryIntent");
                    if (Q(s2)) {
                        throw new UserRecoverableAuthException(s2, intent);
                    }
                    goto Label_0202;
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
    
    @Deprecated
    public static void invalidateToken(final Context context, final String s) {
        AccountManager.get(context).invalidateAuthToken("com.google", s);
    }
    
    private static void s(final Context context) throws GooglePlayServicesAvailabilityException, GoogleAuthException {
        try {
            GooglePlayServicesUtil.s(context);
        }
        catch (GooglePlayServicesRepairableException ex) {
            throw new GooglePlayServicesAvailabilityException(ex.getConnectionStatusCode(), ex.getMessage(), ex.getIntent());
        }
        catch (GooglePlayServicesNotAvailableException ex2) {
            throw new GoogleAuthException(ex2.getMessage());
        }
    }
}
