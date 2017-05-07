// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gcm;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.sql.Timestamp;
import android.os.Parcelable;
import android.content.SharedPreferences$Editor;
import android.util.Log;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build$VERSION;
import android.content.Context;
import android.app.PendingIntent;

public final class GCMRegistrar
{
    private static PendingIntent sAppPendingIntent;
    private static GCMBroadcastReceiver sRetryReceiver;
    private static String sRetryReceiverClassName;
    private static Context sRetryReceiverContext;
    
    static void cancelAppPendingIntent() {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sAppPendingIntent != null) {
                GCMRegistrar.sAppPendingIntent.cancel();
                GCMRegistrar.sAppPendingIntent = null;
            }
        }
    }
    
    public static void checkDevice(final Context context) {
        final int sdk_INT = Build$VERSION.SDK_INT;
        if (sdk_INT < 8) {
            throw new UnsupportedOperationException("Device must be at least API Level 8 (instead of " + sdk_INT + ")");
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.getPackageInfo("com.google.android.gsf", 0);
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new UnsupportedOperationException("Device does not have package com.google.android.gsf");
        }
    }
    
    static String clearRegistrationId(final Context context) {
        setRegisteredOnServer(context, null, 0L);
        return setRegistrationId(context, "");
    }
    
    private static int getAppVersion(final Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            throw new RuntimeException("Coult not get package name: " + ex);
        }
    }
    
    static int getBackoff(final Context context) {
        return getGCMPreferences(context).getInt("backoff_ms", 3000);
    }
    
    static String getFlatSenderIds(final String... array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("No senderIds");
        }
        final StringBuilder sb = new StringBuilder(array[0]);
        for (int i = 1; i < array.length; ++i) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
    }
    
    private static SharedPreferences getGCMPreferences(final Context context) {
        return context.getSharedPreferences("com.google.android.gcm", 0);
    }
    
    public static String getRegistrationId(final Context context) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final String string = gcmPreferences.getString("regId", "");
        final int int1 = gcmPreferences.getInt("appVersion", Integer.MIN_VALUE);
        final int appVersion = getAppVersion(context);
        String s = string;
        if (int1 != Integer.MIN_VALUE) {
            s = string;
            if (int1 != appVersion) {
                log(context, 2, "App version changed from %d to %d;resetting registration id", int1, appVersion);
                clearRegistrationId(context);
                s = "";
            }
        }
        return s;
    }
    
    static void internalRegister(final Context context, final String... array) {
        final String flatSenderIds = getFlatSenderIds(array);
        log(context, 2, "Registering app for senders %s", flatSenderIds);
        final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
        intent.setPackage("com.google.android.gsf");
        setPackageNameExtra(context, intent);
        intent.putExtra("sender", flatSenderIds);
        context.startService(intent);
    }
    
    static void internalUnregister(final Context context) {
        log(context, 2, "Unregistering app", new Object[0]);
        final Intent intent = new Intent("com.google.android.c2dm.intent.UNREGISTER");
        intent.setPackage("com.google.android.gsf");
        setPackageNameExtra(context, intent);
        context.startService(intent);
    }
    
    public static boolean isRegistered(final Context context) {
        return getRegistrationId(context).length() > 0;
    }
    
    private static void log(final Context context, final int n, String format, final Object... array) {
        if (Log.isLoggable("GCMRegistrar", n)) {
            format = String.format(format, array);
            Log.println(n, "GCMRegistrar", "[" + context.getPackageName() + "]: " + format);
        }
    }
    
    public static void register(final Context context, final String... array) {
        resetBackoff(context);
        internalRegister(context, array);
    }
    
    static void resetBackoff(final Context context) {
        log(context, 2, "Resetting backoff", new Object[0]);
        setBackoff(context, 3000);
    }
    
    static void setBackoff(final Context context, final int n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        edit.putInt("backoff_ms", n);
        edit.commit();
    }
    
    private static void setPackageNameExtra(final Context context, final Intent intent) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sAppPendingIntent == null) {
                log(context, 2, "Creating pending intent to get package name", new Object[0]);
                GCMRegistrar.sAppPendingIntent = PendingIntent.getBroadcast(context, 0, new Intent(), 0);
            }
            intent.putExtra("app", (Parcelable)GCMRegistrar.sAppPendingIntent);
        }
    }
    
    private static void setRegisteredOnServer(final Context context, final Boolean b, final long n) {
        final SharedPreferences$Editor edit = getGCMPreferences(context).edit();
        if (b != null) {
            edit.putBoolean("onServer", (boolean)b);
            log(context, 2, "Setting registeredOnServer flag as %b until %s", b, new Timestamp(n));
        }
        else {
            log(context, 2, "Setting registeredOnServer expiration to %s", new Timestamp(n));
        }
        edit.putLong("onServerExpirationTime", n);
        edit.commit();
    }
    
    static String setRegistrationId(final Context context, final String s) {
        final SharedPreferences gcmPreferences = getGCMPreferences(context);
        final String string = gcmPreferences.getString("regId", "");
        final int appVersion = getAppVersion(context);
        log(context, 2, "Saving regId on app version %d", appVersion);
        final SharedPreferences$Editor edit = gcmPreferences.edit();
        edit.putString("regId", s);
        edit.putInt("appVersion", appVersion);
        edit.commit();
        return string;
    }
    
    static void setRetryBroadcastReceiver(final Context sRetryReceiverContext) {
        synchronized (GCMRegistrar.class) {
            if (GCMRegistrar.sRetryReceiver == null) {
                if (GCMRegistrar.sRetryReceiverClassName == null) {
                    log(sRetryReceiverContext, 6, "internal error: retry receiver class not set yet", new Object[0]);
                    GCMRegistrar.sRetryReceiver = new GCMBroadcastReceiver();
                }
                else {
                    try {
                        GCMRegistrar.sRetryReceiver = (GCMBroadcastReceiver)Class.forName(GCMRegistrar.sRetryReceiverClassName).newInstance();
                    }
                    catch (Exception ex) {
                        log(sRetryReceiverContext, 6, "Could not create instance of %s. Using %s directly.", GCMRegistrar.sRetryReceiverClassName, GCMBroadcastReceiver.class.getName());
                        GCMRegistrar.sRetryReceiver = new GCMBroadcastReceiver();
                    }
                }
                final String packageName = sRetryReceiverContext.getPackageName();
                final IntentFilter intentFilter = new IntentFilter("com.google.android.gcm.intent.RETRY");
                intentFilter.addCategory(packageName);
                log(sRetryReceiverContext, 2, "Registering retry receiver", new Object[0]);
                (GCMRegistrar.sRetryReceiverContext = sRetryReceiverContext).registerReceiver((BroadcastReceiver)GCMRegistrar.sRetryReceiver, intentFilter);
            }
        }
    }
    
    static void setRetryReceiverClassName(final Context context, final String sRetryReceiverClassName) {
        synchronized (GCMRegistrar.class) {
            log(context, 2, "Setting the name of retry receiver class to %s", sRetryReceiverClassName);
            GCMRegistrar.sRetryReceiverClassName = sRetryReceiverClassName;
        }
    }
}
