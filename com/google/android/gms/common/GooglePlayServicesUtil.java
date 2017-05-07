// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.app.NotificationManager;
import android.app.Notification;
import android.app.Notification$Style;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import com.google.android.gms.common.internal.n;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;
import android.net.Uri;
import android.net.Uri$Builder;
import android.app.PendingIntent;
import android.os.Build;
import android.content.res.Configuration;
import com.google.android.gms.common.internal.g;
import java.util.Arrays;
import java.util.Set;
import android.util.Base64;
import android.content.res.Resources;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.R;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.common.internal.b;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import com.google.android.gms.internal.kc;
import com.google.android.gms.internal.jt;
import android.app.Dialog;
import android.content.DialogInterface$OnCancelListener;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.content.Context;

public final class GooglePlayServicesUtil
{
    public static final String GMS_ERROR_DIALOG = "GooglePlayServicesErrorDialog";
    public static final String GOOGLE_PLAY_SERVICES_PACKAGE = "com.google.android.gms";
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE = 6111000;
    public static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    public static boolean Id;
    public static boolean Ie;
    private static int If;
    private static final Object Ig;
    
    static {
        GooglePlayServicesUtil.Id = false;
        GooglePlayServicesUtil.Ie = false;
        GooglePlayServicesUtil.If = -1;
        Ig = new Object();
    }
    
    public static void D(final Context context) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        final int googlePlayServicesAvailable = isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent c = c(context, googlePlayServicesAvailable);
        Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + googlePlayServicesAvailable);
        if (c == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", c);
    }
    
    private static void E(final Context context) {
        final ApplicationInfo applicationInfo = null;
        int int1;
        while (true) {
            try {
                final ApplicationInfo applicationInfo2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                final Bundle metaData = applicationInfo2.metaData;
                if (metaData == null) {
                    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                }
                int1 = metaData.getInt("com.google.android.gms.version");
                if (int1 == 6111000) {
                    return;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.wtf("GooglePlayServicesUtil", "This should never happen.", (Throwable)ex);
                final ApplicationInfo applicationInfo2 = applicationInfo;
                continue;
            }
            break;
        }
        throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected 6111000 but found " + int1 + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
    }
    
    private static String F(final Context context) {
        String s;
        Object packageManager = s = context.getApplicationInfo().name;
        if (!TextUtils.isEmpty((CharSequence)packageManager)) {
            return s;
        }
        s = context.getPackageName();
        packageManager = context.getApplicationContext().getPackageManager();
        while (true) {
            try {
                final ApplicationInfo applicationInfo = ((PackageManager)packageManager).getApplicationInfo(context.getPackageName(), 0);
                if (applicationInfo != null) {
                    s = ((PackageManager)packageManager).getApplicationLabel(applicationInfo).toString();
                }
                return s;
            }
            catch (PackageManager$NameNotFoundException ex) {
                final ApplicationInfo applicationInfo = null;
                continue;
            }
            break;
        }
    }
    
    private static Dialog a(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener onCancelListener) {
        int n3 = n;
        if (jt.K((Context)activity) && (n3 = n) == 2) {
            n3 = 42;
        }
        while (true) {
            Label_0495: {
                if (!kc.hE()) {
                    break Label_0495;
                }
                final TypedValue typedValue = new TypedValue();
                activity.getTheme().resolveAttribute(16843529, typedValue, true);
                if (!"Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                    break Label_0495;
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)activity, 5);
                AlertDialog$Builder alertDialog$Builder2 = alertDialog$Builder;
                if (alertDialog$Builder == null) {
                    alertDialog$Builder2 = new AlertDialog$Builder((Context)activity);
                }
                alertDialog$Builder2.setMessage((CharSequence)d((Context)activity, n3));
                if (onCancelListener != null) {
                    alertDialog$Builder2.setOnCancelListener(onCancelListener);
                }
                final Intent c = c((Context)activity, n3);
                b b;
                if (fragment == null) {
                    b = new b(activity, c, n2);
                }
                else {
                    b = new b(fragment, c, n2);
                }
                final String e = e((Context)activity, n3);
                if (e != null) {
                    alertDialog$Builder2.setPositiveButton((CharSequence)e, (DialogInterface$OnClickListener)b);
                }
                switch (n3) {
                    default: {
                        Log.e("GooglePlayServicesUtil", "Unexpected error code " + n3);
                        return (Dialog)alertDialog$Builder2.create();
                    }
                    case 0: {
                        return null;
                    }
                    case 4:
                    case 6: {
                        return (Dialog)alertDialog$Builder2.create();
                    }
                    case 1: {
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_google_play_services_install_title).create();
                    }
                    case 3: {
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_google_play_services_enable_title).create();
                    }
                    case 2: {
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_google_play_services_update_title).create();
                    }
                    case 42: {
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_android_wear_update_title).create();
                    }
                    case 9: {
                        Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_google_play_services_unsupported_title).create();
                    }
                    case 7: {
                        Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_google_play_services_network_error_title).create();
                    }
                    case 8: {
                        Log.e("GooglePlayServicesUtil", "Internal error occurred. Please see logs for detailed information");
                        return (Dialog)alertDialog$Builder2.create();
                    }
                    case 10: {
                        Log.e("GooglePlayServicesUtil", "Developer error occurred. Please see logs for detailed information");
                        return (Dialog)alertDialog$Builder2.create();
                    }
                    case 5: {
                        Log.e("GooglePlayServicesUtil", "An invalid account was specified when connecting. Please provide a valid account.");
                        return (Dialog)alertDialog$Builder2.setTitle(R.string.common_google_play_services_invalid_account_title).create();
                    }
                    case 11: {
                        Log.e("GooglePlayServicesUtil", "The application is not licensed to the user.");
                        return (Dialog)alertDialog$Builder2.create();
                    }
                }
            }
            final AlertDialog$Builder alertDialog$Builder = null;
            continue;
        }
    }
    
    public static boolean a(final PackageManager packageManager, final PackageInfo packageInfo) {
        boolean b = true;
        final boolean b2 = false;
        boolean b3 = false;
        if (packageInfo != null) {
            if (c(packageManager)) {
                if (a(packageInfo, true) == null) {
                    b = false;
                }
                return b;
            }
            boolean b4 = b2;
            if (a(packageInfo, false) != null) {
                b4 = true;
            }
            b3 = b4;
            if (!b4) {
                b3 = b4;
                if (a(packageInfo, true) != null) {
                    Log.w("GooglePlayServicesUtil", "Test-keys aren't accepted on this build.");
                    return b4;
                }
            }
        }
        return b3;
    }
    
    public static boolean a(final Resources resources) {
        if (resources != null) {
            boolean b;
            if ((resources.getConfiguration().screenLayout & 0xF) > 3) {
                b = true;
            }
            else {
                b = false;
            }
            if ((kc.hB() && b) || b(resources)) {
                return true;
            }
        }
        return false;
    }
    
    private static byte[] a(final PackageInfo packageInfo, final boolean b) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        final byte[] byteArray = packageInfo.signatures[0].toByteArray();
        Set<byte[]> set;
        if (b) {
            set = b.fZ();
        }
        else {
            set = b.ga();
        }
        if (set.contains(byteArray)) {
            return byteArray;
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(byteArray, 0));
        }
        return null;
    }
    
    private static byte[] a(final PackageInfo packageInfo, final byte[]... array) {
        if (packageInfo.signatures.length != 1) {
            Log.w("GooglePlayServicesUtil", "Package has more than one signature.");
            return null;
        }
        final byte[] byteArray = packageInfo.signatures[0].toByteArray();
        for (int i = 0; i < array.length; ++i) {
            final byte[] array2 = array[i];
            if (Arrays.equals(array2, byteArray)) {
                return array2;
            }
        }
        if (Log.isLoggable("GooglePlayServicesUtil", 2)) {
            Log.v("GooglePlayServicesUtil", "Signature not valid.  Found: \n" + Base64.encodeToString(byteArray, 0));
        }
        return null;
    }
    
    public static Intent ai(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1:
            case 2: {
                return g.aY("com.google.android.gms");
            }
            case 42: {
                return g.gZ();
            }
            case 3: {
                return g.aW("com.google.android.gms");
            }
        }
    }
    
    public static boolean b(final PackageManager packageManager) {
        synchronized (GooglePlayServicesUtil.Ig) {
        Label_0045:
            while (true) {
                if (GooglePlayServicesUtil.If != -1) {
                    break Label_0045;
                }
                while (true) {
                    try {
                        if (a(packageManager.getPackageInfo("com.google.android.gms", 64), new byte[][] { com.google.android.gms.common.b.HZ[1] }) != null) {
                            GooglePlayServicesUtil.If = 1;
                        }
                        else {
                            GooglePlayServicesUtil.If = 0;
                        }
                        // monitorexit(GooglePlayServicesUtil.Ig)
                        if (GooglePlayServicesUtil.If != 0) {
                            return true;
                        }
                        break;
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        GooglePlayServicesUtil.If = 0;
                        continue Label_0045;
                    }
                    continue Label_0045;
                }
                break;
            }
        }
        return false;
    }
    
    public static boolean b(final PackageManager packageManager, final String s) {
        try {
            return a(packageManager, packageManager.getPackageInfo(s, 64));
        }
        catch (PackageManager$NameNotFoundException ex) {
            if (Log.isLoggable("GooglePlayServicesUtil", 3)) {
                Log.d("GooglePlayServicesUtil", "Package manager can't find package " + s + ", defaulting to false");
            }
            return false;
        }
    }
    
    private static boolean b(final Resources resources) {
        final boolean b = false;
        final Configuration configuration = resources.getConfiguration();
        boolean b2 = b;
        if (kc.hD()) {
            b2 = b;
            if ((configuration.screenLayout & 0xF) <= 3) {
                b2 = b;
                if (configuration.smallestScreenWidthDp >= 600) {
                    b2 = true;
                }
            }
        }
        return b2;
    }
    
    @Deprecated
    public static Intent c(final Context context, final int n) {
        return ai(n);
    }
    
    public static boolean c(final PackageManager packageManager) {
        return b(packageManager) || !gb();
    }
    
    public static String d(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(R.string.common_google_play_services_unknown_issue);
            }
            case 1: {
                if (a(context.getResources())) {
                    return resources.getString(R.string.common_google_play_services_install_text_tablet);
                }
                return resources.getString(R.string.common_google_play_services_install_text_phone);
            }
            case 3: {
                return resources.getString(R.string.common_google_play_services_enable_text);
            }
            case 2: {
                return resources.getString(R.string.common_google_play_services_update_text);
            }
            case 42: {
                return resources.getString(R.string.common_android_wear_update_text);
            }
            case 9: {
                return resources.getString(R.string.common_google_play_services_unsupported_text);
            }
            case 7: {
                return resources.getString(R.string.common_google_play_services_network_error_text);
            }
            case 5: {
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            }
        }
    }
    
    public static String e(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(17039370);
            }
            case 1: {
                return resources.getString(R.string.common_google_play_services_install_button);
            }
            case 3: {
                return resources.getString(R.string.common_google_play_services_enable_button);
            }
            case 2:
            case 42: {
                return resources.getString(R.string.common_google_play_services_update_button);
            }
        }
    }
    
    public static String f(final Context context, final int n) {
        final Resources resources = context.getResources();
        switch (n) {
            default: {
                return resources.getString(R.string.common_google_play_services_unknown_issue);
            }
            case 1: {
                return resources.getString(R.string.common_google_play_services_notification_needs_installation_title);
            }
            case 2: {
                return resources.getString(R.string.common_google_play_services_notification_needs_update_title);
            }
            case 42: {
                return resources.getString(R.string.common_android_wear_notification_needs_update_text);
            }
            case 3: {
                return resources.getString(R.string.common_google_play_services_needs_enabling_title);
            }
            case 9: {
                return resources.getString(R.string.common_google_play_services_unsupported_text);
            }
            case 7: {
                return resources.getString(R.string.common_google_play_services_network_error_text);
            }
            case 5: {
                return resources.getString(R.string.common_google_play_services_invalid_account_text);
            }
        }
    }
    
    public static boolean gb() {
        if (GooglePlayServicesUtil.Id) {
            return GooglePlayServicesUtil.Ie;
        }
        return "user".equals(Build.TYPE);
    }
    
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2) {
        return getErrorDialog(n, activity, n2, null);
    }
    
    public static Dialog getErrorDialog(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return a(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    public static PendingIntent getErrorPendingIntent(final int n, final Context context, final int n2) {
        final Intent c = c(context, n);
        if (c == null) {
            return null;
        }
        return PendingIntent.getActivity(context, n2, c, 268435456);
    }
    
    public static String getErrorString(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN_ERROR_CODE";
            }
            case 0: {
                return "SUCCESS";
            }
            case 1: {
                return "SERVICE_MISSING";
            }
            case 2: {
                return "SERVICE_VERSION_UPDATE_REQUIRED";
            }
            case 3: {
                return "SERVICE_DISABLED";
            }
            case 4: {
                return "SIGN_IN_REQUIRED";
            }
            case 5: {
                return "INVALID_ACCOUNT";
            }
            case 6: {
                return "RESOLUTION_REQUIRED";
            }
            case 7: {
                return "NETWORK_ERROR";
            }
            case 8: {
                return "INTERNAL_ERROR";
            }
            case 9: {
                return "SERVICE_INVALID";
            }
            case 10: {
                return "DEVELOPER_ERROR";
            }
            case 11: {
                return "LICENSE_CHECK_FAILED";
            }
        }
    }
    
    public static String getOpenSourceSoftwareLicenseInfo(final Context context) {
        Object o = new Uri$Builder().scheme("android.resource").authority("com.google.android.gms").appendPath("raw").appendPath("oss_notice").build();
        try {
            final InputStream openInputStream = context.getContentResolver().openInputStream((Uri)o);
            try {
                return (String)(o = new Scanner(openInputStream).useDelimiter("\\A").next());
            }
            catch (NoSuchElementException ex) {}
            finally {
                if (openInputStream != null) {
                    openInputStream.close();
                }
            }
        }
        catch (Exception ex2) {
            o = null;
        }
        return (String)o;
    }
    
    public static Context getRemoteContext(Context packageContext) {
        try {
            packageContext = packageContext.createPackageContext("com.google.android.gms", 3);
            return packageContext;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static Resources getRemoteResource(final Context context) {
        try {
            return context.getPackageManager().getResourcesForApplication("com.google.android.gms");
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static int isGooglePlayServicesAvailable(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //     4: astore_2       
        //     5: aload_0        
        //     6: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //     9: getstatic       com/google/android/gms/R$string.common_google_play_services_unknown_issue:I
        //    12: invokevirtual   android/content/res/Resources.getString:(I)Ljava/lang/String;
        //    15: pop            
        //    16: aload_0        
        //    17: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.E:(Landroid/content/Context;)V
        //    20: aload_2        
        //    21: ldc             "com.google.android.gms"
        //    23: bipush          64
        //    25: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    28: astore_3       
        //    29: aload_3        
        //    30: getfield        android/content/pm/PackageInfo.versionCode:I
        //    33: invokestatic    com/google/android/gms/internal/jt.aP:(I)Z
        //    36: ifeq            208
        //    39: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.gb:()Z
        //    42: ifeq            111
        //    45: iconst_0       
        //    46: istore_1       
        //    47: aload_3        
        //    48: iconst_2       
        //    49: anewarray       [B
        //    52: dup            
        //    53: iconst_0       
        //    54: getstatic       com/google/android/gms/common/b.HH:[[B
        //    57: iload_1        
        //    58: aaload         
        //    59: aastore        
        //    60: dup            
        //    61: iconst_1       
        //    62: getstatic       com/google/android/gms/common/b.HM:[[B
        //    65: iload_1        
        //    66: aaload         
        //    67: aastore        
        //    68: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //    71: ifnonnull       116
        //    74: ldc             "GooglePlayServicesUtil"
        //    76: ldc_w           "Google Play Services signature invalid on Glass."
        //    79: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    82: pop            
        //    83: bipush          9
        //    85: ireturn        
        //    86: astore_3       
        //    87: ldc             "GooglePlayServicesUtil"
        //    89: ldc_w           "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
        //    92: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    95: pop            
        //    96: goto            16
        //    99: astore_0       
        //   100: ldc             "GooglePlayServicesUtil"
        //   102: ldc_w           "Google Play services is missing."
        //   105: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   108: pop            
        //   109: iconst_1       
        //   110: ireturn        
        //   111: iconst_1       
        //   112: istore_1       
        //   113: goto            47
        //   116: aload_0        
        //   117: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   120: astore_0       
        //   121: aload_2        
        //   122: aload_0        
        //   123: bipush          64
        //   125: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   128: astore          4
        //   130: aload_2        
        //   131: aload           4
        //   133: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)Z
        //   136: ifne            310
        //   139: ldc             "GooglePlayServicesUtil"
        //   141: new             Ljava/lang/StringBuilder;
        //   144: dup            
        //   145: invokespecial   java/lang/StringBuilder.<init>:()V
        //   148: ldc_w           "Calling package "
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: aload           4
        //   156: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   162: ldc_w           " signature invalid on Glass."
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   171: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   174: pop            
        //   175: bipush          9
        //   177: ireturn        
        //   178: astore_2       
        //   179: ldc             "GooglePlayServicesUtil"
        //   181: new             Ljava/lang/StringBuilder;
        //   184: dup            
        //   185: invokespecial   java/lang/StringBuilder.<init>:()V
        //   188: ldc_w           "Could not get info for calling package: "
        //   191: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   194: aload_0        
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   201: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   204: pop            
        //   205: bipush          9
        //   207: ireturn        
        //   208: aload_0        
        //   209: invokestatic    com/google/android/gms/internal/jt.K:(Landroid/content/Context;)Z
        //   212: ifeq            237
        //   215: aload_3        
        //   216: getstatic       com/google/android/gms/common/b.HH:[[B
        //   219: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //   222: ifnonnull       310
        //   225: ldc             "GooglePlayServicesUtil"
        //   227: ldc_w           "Google Play services signature invalid."
        //   230: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   233: pop            
        //   234: bipush          9
        //   236: ireturn        
        //   237: aload_2        
        //   238: ldc             "com.android.vending"
        //   240: bipush          64
        //   242: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   245: astore_0       
        //   246: aload_0        
        //   247: getstatic       com/google/android/gms/common/b.HH:[[B
        //   250: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //   253: astore_0       
        //   254: aload_0        
        //   255: ifnonnull       283
        //   258: ldc             "GooglePlayServicesUtil"
        //   260: ldc_w           "Google Play Store signature invalid."
        //   263: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   266: pop            
        //   267: bipush          9
        //   269: ireturn        
        //   270: astore_0       
        //   271: ldc             "GooglePlayServicesUtil"
        //   273: ldc_w           "Google Play Store is missing."
        //   276: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   279: pop            
        //   280: bipush          9
        //   282: ireturn        
        //   283: aload_3        
        //   284: iconst_1       
        //   285: anewarray       [B
        //   288: dup            
        //   289: iconst_0       
        //   290: aload_0        
        //   291: aastore        
        //   292: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //   295: ifnonnull       310
        //   298: ldc             "GooglePlayServicesUtil"
        //   300: ldc_w           "Google Play services signature invalid."
        //   303: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   306: pop            
        //   307: bipush          9
        //   309: ireturn        
        //   310: ldc             6111000
        //   312: invokestatic    com/google/android/gms/internal/jt.aN:(I)I
        //   315: istore_1       
        //   316: aload_3        
        //   317: getfield        android/content/pm/PackageInfo.versionCode:I
        //   320: invokestatic    com/google/android/gms/internal/jt.aN:(I)I
        //   323: iload_1        
        //   324: if_icmpge       358
        //   327: ldc             "GooglePlayServicesUtil"
        //   329: new             Ljava/lang/StringBuilder;
        //   332: dup            
        //   333: invokespecial   java/lang/StringBuilder.<init>:()V
        //   336: ldc_w           "Google Play services out of date.  Requires 6111000 but found "
        //   339: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   342: aload_3        
        //   343: getfield        android/content/pm/PackageInfo.versionCode:I
        //   346: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   349: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   352: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   355: pop            
        //   356: iconst_2       
        //   357: ireturn        
        //   358: aload_2        
        //   359: ldc             "com.google.android.gms"
        //   361: iconst_0       
        //   362: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   365: astore_0       
        //   366: aload_0        
        //   367: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   370: ifne            391
        //   373: iconst_3       
        //   374: ireturn        
        //   375: astore_0       
        //   376: ldc             "GooglePlayServicesUtil"
        //   378: ldc_w           "Google Play services missing when getting application info."
        //   381: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;)I
        //   384: pop            
        //   385: aload_0        
        //   386: invokevirtual   android/content/pm/PackageManager$NameNotFoundException.printStackTrace:()V
        //   389: iconst_1       
        //   390: ireturn        
        //   391: iconst_0       
        //   392: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  5      16     86     99     Ljava/lang/Throwable;
        //  20     29     99     111    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  121    175    178    208    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  237    246    270    283    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  358    366    375    391    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static boolean isGoogleSignedUid(final PackageManager packageManager, final int n) {
        if (packageManager == null) {
            throw new SecurityException("Unknown error: invalid Package Manager");
        }
        final String[] packagesForUid = packageManager.getPackagesForUid(n);
        if (packagesForUid.length == 0 || !b(packageManager, packagesForUid[0])) {
            throw new SecurityException("Uid is not Google Signed");
        }
        return true;
    }
    
    public static boolean isUserRecoverableError(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1:
            case 2:
            case 3:
            case 9: {
                return true;
            }
        }
    }
    
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2) {
        return showErrorDialogFragment(n, activity, n2, null);
    }
    
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return showErrorDialogFragment(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    public static boolean showErrorDialogFragment(final int n, Activity activity, Fragment a, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        int n3 = 0;
        a = (Fragment)a(n, activity, a, n2, dialogInterface$OnCancelListener);
        if (a == null) {
            return false;
        }
        while (true) {
            try {
                n3 = ((activity instanceof FragmentActivity) ? 1 : 0);
                if (n3 != 0) {
                    activity = (Activity)((FragmentActivity)activity).getSupportFragmentManager();
                    SupportErrorDialogFragment.newInstance((Dialog)a, dialogInterface$OnCancelListener).show((FragmentManager)activity, "GooglePlayServicesErrorDialog");
                }
                else {
                    if (!kc.hB()) {
                        throw new RuntimeException("This Activity does not support Fragments.");
                    }
                    activity = (Activity)activity.getFragmentManager();
                    ErrorDialogFragment.newInstance((Dialog)a, dialogInterface$OnCancelListener).show((android.app.FragmentManager)activity, "GooglePlayServicesErrorDialog");
                }
                return true;
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                continue;
            }
            break;
        }
    }
    
    public static void showErrorNotification(final int n, final Context context) {
        final boolean k = jt.K(context);
        int n2 = n;
        if (k && (n2 = n) == 2) {
            n2 = 42;
        }
        final Resources resources = context.getResources();
        final String f = f(context, n2);
        final String string = resources.getString(R.string.common_google_play_services_error_notification_requested_by_msg, new Object[] { F(context) });
        final PendingIntent errorPendingIntent = getErrorPendingIntent(n2, context, 0);
        Notification build;
        if (k) {
            n.I(kc.hF());
            build = new Notification$Builder(context).setSmallIcon(R.drawable.common_ic_googleplayservices).setPriority(2).setAutoCancel(true).setStyle((Notification$Style)new Notification$BigTextStyle().bigText((CharSequence)(f + " " + string))).addAction(R.drawable.common_full_open_on_phone, (CharSequence)resources.getString(R.string.common_open_on_phone), errorPendingIntent).build();
        }
        else {
            build = new Notification(17301642, (CharSequence)resources.getString(R.string.common_google_play_services_notification_ticker), System.currentTimeMillis());
            build.flags |= 0x10;
            build.setLatestEventInfo(context, (CharSequence)f, (CharSequence)string, errorPendingIntent);
        }
        ((NotificationManager)context.getSystemService("notification")).notify(39789, build);
    }
}
