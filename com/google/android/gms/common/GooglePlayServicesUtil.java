// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.os.Build;
import android.content.res.Configuration;
import com.google.android.gms.common.internal.g;
import java.util.Arrays;
import java.util.Set;
import android.util.Base64;
import android.content.res.Resources;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.google.android.gms.R$string;
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
import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Intent;
import android.util.Log;
import android.content.Context;

public final class GooglePlayServicesUtil
{
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
    
    public static void D(final Context context) {
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
    
    private static Dialog a(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener onCancelListener) {
        int n3 = n;
        if (jt.K((Context)activity) && (n3 = n) == 2) {
            n3 = 42;
        }
        while (true) {
            Label_0490: {
                if (!kc.hE()) {
                    break Label_0490;
                }
                final TypedValue typedValue = new TypedValue();
                activity.getTheme().resolveAttribute(16843529, typedValue, true);
                if (!"Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                    break Label_0490;
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
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_google_play_services_install_title).create();
                    }
                    case 3: {
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_google_play_services_enable_title).create();
                    }
                    case 2: {
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_google_play_services_update_title).create();
                    }
                    case 42: {
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_android_wear_update_title).create();
                    }
                    case 9: {
                        Log.e("GooglePlayServicesUtil", "Google Play services is invalid. Cannot recover.");
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_google_play_services_unsupported_title).create();
                    }
                    case 7: {
                        Log.e("GooglePlayServicesUtil", "Network error occurred. Please retry request later.");
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_google_play_services_network_error_title).create();
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
                        return (Dialog)alertDialog$Builder2.setTitle(R$string.common_google_play_services_invalid_account_title).create();
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
        Label_0046:
            while (true) {
                if (GooglePlayServicesUtil.If != -1) {
                    break Label_0046;
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
                        continue Label_0046;
                    }
                    continue Label_0046;
                }
                break;
            }
        }
        return false;
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
                return resources.getString(R$string.common_google_play_services_unknown_issue);
            }
            case 1: {
                if (a(context.getResources())) {
                    return resources.getString(R$string.common_google_play_services_install_text_tablet);
                }
                return resources.getString(R$string.common_google_play_services_install_text_phone);
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_text);
            }
            case 2: {
                return resources.getString(R$string.common_google_play_services_update_text);
            }
            case 42: {
                return resources.getString(R$string.common_android_wear_update_text);
            }
            case 9: {
                return resources.getString(R$string.common_google_play_services_unsupported_text);
            }
            case 7: {
                return resources.getString(R$string.common_google_play_services_network_error_text);
            }
            case 5: {
                return resources.getString(R$string.common_google_play_services_invalid_account_text);
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
                return resources.getString(R$string.common_google_play_services_install_button);
            }
            case 3: {
                return resources.getString(R$string.common_google_play_services_enable_button);
            }
            case 2:
            case 42: {
                return resources.getString(R$string.common_google_play_services_update_button);
            }
        }
    }
    
    public static boolean gb() {
        if (GooglePlayServicesUtil.Id) {
            return GooglePlayServicesUtil.Ie;
        }
        return "user".equals(Build.TYPE);
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
        //    21: ldc_w           "com.google.android.gms"
        //    24: bipush          64
        //    26: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    29: astore_3       
        //    30: aload_3        
        //    31: getfield        android/content/pm/PackageInfo.versionCode:I
        //    34: invokestatic    com/google/android/gms/internal/jt.aP:(I)Z
        //    37: ifeq            209
        //    40: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.gb:()Z
        //    43: ifeq            112
        //    46: iconst_0       
        //    47: istore_1       
        //    48: aload_3        
        //    49: iconst_2       
        //    50: anewarray       [B
        //    53: dup            
        //    54: iconst_0       
        //    55: getstatic       com/google/android/gms/common/b.HH:[[B
        //    58: iload_1        
        //    59: aaload         
        //    60: aastore        
        //    61: dup            
        //    62: iconst_1       
        //    63: getstatic       com/google/android/gms/common/b.HM:[[B
        //    66: iload_1        
        //    67: aaload         
        //    68: aastore        
        //    69: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //    72: ifnonnull       117
        //    75: ldc             "GooglePlayServicesUtil"
        //    77: ldc_w           "Google Play Services signature invalid on Glass."
        //    80: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    83: pop            
        //    84: bipush          9
        //    86: ireturn        
        //    87: astore_3       
        //    88: ldc             "GooglePlayServicesUtil"
        //    90: ldc_w           "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
        //    93: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    96: pop            
        //    97: goto            16
        //   100: astore_0       
        //   101: ldc             "GooglePlayServicesUtil"
        //   103: ldc_w           "Google Play services is missing."
        //   106: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   109: pop            
        //   110: iconst_1       
        //   111: ireturn        
        //   112: iconst_1       
        //   113: istore_1       
        //   114: goto            48
        //   117: aload_0        
        //   118: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   121: astore_0       
        //   122: aload_2        
        //   123: aload_0        
        //   124: bipush          64
        //   126: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   129: astore          4
        //   131: aload_2        
        //   132: aload           4
        //   134: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)Z
        //   137: ifne            312
        //   140: ldc             "GooglePlayServicesUtil"
        //   142: new             Ljava/lang/StringBuilder;
        //   145: dup            
        //   146: invokespecial   java/lang/StringBuilder.<init>:()V
        //   149: ldc_w           "Calling package "
        //   152: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   155: aload           4
        //   157: getfield        android/content/pm/PackageInfo.packageName:Ljava/lang/String;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: ldc_w           " signature invalid on Glass."
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   172: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   175: pop            
        //   176: bipush          9
        //   178: ireturn        
        //   179: astore_2       
        //   180: ldc             "GooglePlayServicesUtil"
        //   182: new             Ljava/lang/StringBuilder;
        //   185: dup            
        //   186: invokespecial   java/lang/StringBuilder.<init>:()V
        //   189: ldc_w           "Could not get info for calling package: "
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: aload_0        
        //   196: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   199: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   202: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   205: pop            
        //   206: bipush          9
        //   208: ireturn        
        //   209: aload_0        
        //   210: invokestatic    com/google/android/gms/internal/jt.K:(Landroid/content/Context;)Z
        //   213: ifeq            238
        //   216: aload_3        
        //   217: getstatic       com/google/android/gms/common/b.HH:[[B
        //   220: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //   223: ifnonnull       312
        //   226: ldc             "GooglePlayServicesUtil"
        //   228: ldc_w           "Google Play services signature invalid."
        //   231: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   234: pop            
        //   235: bipush          9
        //   237: ireturn        
        //   238: aload_2        
        //   239: ldc_w           "com.android.vending"
        //   242: bipush          64
        //   244: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   247: astore_0       
        //   248: aload_0        
        //   249: getstatic       com/google/android/gms/common/b.HH:[[B
        //   252: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //   255: astore_0       
        //   256: aload_0        
        //   257: ifnonnull       285
        //   260: ldc             "GooglePlayServicesUtil"
        //   262: ldc_w           "Google Play Store signature invalid."
        //   265: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   268: pop            
        //   269: bipush          9
        //   271: ireturn        
        //   272: astore_0       
        //   273: ldc             "GooglePlayServicesUtil"
        //   275: ldc_w           "Google Play Store is missing."
        //   278: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   281: pop            
        //   282: bipush          9
        //   284: ireturn        
        //   285: aload_3        
        //   286: iconst_1       
        //   287: anewarray       [B
        //   290: dup            
        //   291: iconst_0       
        //   292: aload_0        
        //   293: aastore        
        //   294: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.a:(Landroid/content/pm/PackageInfo;[[B)[B
        //   297: ifnonnull       312
        //   300: ldc             "GooglePlayServicesUtil"
        //   302: ldc_w           "Google Play services signature invalid."
        //   305: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   308: pop            
        //   309: bipush          9
        //   311: ireturn        
        //   312: ldc             6111000
        //   314: invokestatic    com/google/android/gms/internal/jt.aN:(I)I
        //   317: istore_1       
        //   318: aload_3        
        //   319: getfield        android/content/pm/PackageInfo.versionCode:I
        //   322: invokestatic    com/google/android/gms/internal/jt.aN:(I)I
        //   325: iload_1        
        //   326: if_icmpge       360
        //   329: ldc             "GooglePlayServicesUtil"
        //   331: new             Ljava/lang/StringBuilder;
        //   334: dup            
        //   335: invokespecial   java/lang/StringBuilder.<init>:()V
        //   338: ldc_w           "Google Play services out of date.  Requires 6111000 but found "
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: aload_3        
        //   345: getfield        android/content/pm/PackageInfo.versionCode:I
        //   348: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   351: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   354: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   357: pop            
        //   358: iconst_2       
        //   359: ireturn        
        //   360: aload_2        
        //   361: ldc_w           "com.google.android.gms"
        //   364: iconst_0       
        //   365: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   368: astore_0       
        //   369: aload_0        
        //   370: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   373: ifne            394
        //   376: iconst_3       
        //   377: ireturn        
        //   378: astore_0       
        //   379: ldc             "GooglePlayServicesUtil"
        //   381: ldc_w           "Google Play services missing when getting application info."
        //   384: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;)I
        //   387: pop            
        //   388: aload_0        
        //   389: invokevirtual   android/content/pm/PackageManager$NameNotFoundException.printStackTrace:()V
        //   392: iconst_1       
        //   393: ireturn        
        //   394: iconst_0       
        //   395: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  5      16     87     100    Ljava/lang/Throwable;
        //  20     30     100    112    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  122    176    179    209    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  238    248    272    285    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  360    369    378    394    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
}
