// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Build;
import java.util.Iterator;
import android.content.pm.PackageInstaller$SessionInfo;
import android.content.pm.PackageManager;
import android.app.AppOpsManager;
import android.os.UserManager;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import android.app.NotificationManager;
import android.util.Log;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzg;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import com.google.android.gms.internal.zzmx;
import com.google.android.gms.internal.zzml;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.content.DialogInterface$OnCancelListener;
import android.app.Activity;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public final class GooglePlayServicesUtil
{
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static boolean zzaal;
    public static boolean zzaam;
    private static int zzaan;
    private static String zzaao;
    private static Integer zzaap;
    static final AtomicBoolean zzaaq;
    private static final AtomicBoolean zzaar;
    private static final Object zzpy;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zzns();
        GooglePlayServicesUtil.zzaal = false;
        GooglePlayServicesUtil.zzaam = false;
        GooglePlayServicesUtil.zzaan = -1;
        zzpy = new Object();
        GooglePlayServicesUtil.zzaao = null;
        GooglePlayServicesUtil.zzaap = null;
        zzaaq = new AtomicBoolean();
        zzaar = new AtomicBoolean();
    }
    
    @Deprecated
    public static int isGooglePlayServicesAvailable(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/common/internal/zzd.zzaeK:Z
        //     3: ifeq            8
        //     6: iconst_0       
        //     7: ireturn        
        //     8: aload_0        
        //     9: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    12: astore_3       
        //    13: aload_0        
        //    14: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    17: getstatic       com/google/android/gms/R$string.common_google_play_services_unknown_issue:I
        //    20: invokevirtual   android/content/res/Resources.getString:(I)Ljava/lang/String;
        //    23: pop            
        //    24: ldc             "com.google.android.gms"
        //    26: aload_0        
        //    27: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    30: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    33: ifne            40
        //    36: aload_0        
        //    37: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.zzad:(Landroid/content/Context;)V
        //    40: aload_3        
        //    41: ldc             "com.google.android.gms"
        //    43: bipush          64
        //    45: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    48: astore_2       
        //    49: invokestatic    com/google/android/gms/common/zzd.zznu:()Lcom/google/android/gms/common/zzd;
        //    52: astore          4
        //    54: aload_2        
        //    55: getfield        android/content/pm/PackageInfo.versionCode:I
        //    58: invokestatic    com/google/android/gms/internal/zzml.zzcb:(I)Z
        //    61: ifne            71
        //    64: aload_0        
        //    65: invokestatic    com/google/android/gms/internal/zzml.zzan:(Landroid/content/Context;)Z
        //    68: ifeq            117
        //    71: aload           4
        //    73: aload_2        
        //    74: getstatic       com/google/android/gms/common/zzc$zzbz.zzaak:[Lcom/google/android/gms/common/zzc$zza;
        //    77: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //    80: ifnonnull       190
        //    83: ldc             "GooglePlayServicesUtil"
        //    85: ldc             "Google Play services signature invalid."
        //    87: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    90: pop            
        //    91: bipush          9
        //    93: ireturn        
        //    94: astore_2       
        //    95: ldc             "GooglePlayServicesUtil"
        //    97: ldc             "The Google Play services resources were not found. Check your project configuration to ensure that the resources are included."
        //    99: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   102: pop            
        //   103: goto            24
        //   106: astore_0       
        //   107: ldc             "GooglePlayServicesUtil"
        //   109: ldc             "Google Play services is missing."
        //   111: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   114: pop            
        //   115: iconst_1       
        //   116: ireturn        
        //   117: aload           4
        //   119: aload_3        
        //   120: ldc             "com.android.vending"
        //   122: sipush          8256
        //   125: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   128: getstatic       com/google/android/gms/common/zzc$zzbz.zzaak:[Lcom/google/android/gms/common/zzc$zza;
        //   131: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //   134: astore_0       
        //   135: aload_0        
        //   136: ifnonnull       162
        //   139: ldc             "GooglePlayServicesUtil"
        //   141: ldc             "Google Play Store signature invalid."
        //   143: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   146: pop            
        //   147: bipush          9
        //   149: ireturn        
        //   150: astore_0       
        //   151: ldc             "GooglePlayServicesUtil"
        //   153: ldc             "Google Play Store is neither installed nor updating."
        //   155: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   158: pop            
        //   159: bipush          9
        //   161: ireturn        
        //   162: aload           4
        //   164: aload_2        
        //   165: iconst_1       
        //   166: anewarray       Lcom/google/android/gms/common/zzc$zza;
        //   169: dup            
        //   170: iconst_0       
        //   171: aload_0        
        //   172: aastore        
        //   173: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //   176: ifnonnull       190
        //   179: ldc             "GooglePlayServicesUtil"
        //   181: ldc             "Google Play services signature invalid."
        //   183: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   186: pop            
        //   187: bipush          9
        //   189: ireturn        
        //   190: getstatic       com/google/android/gms/common/GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   193: invokestatic    com/google/android/gms/internal/zzml.zzca:(I)I
        //   196: istore_1       
        //   197: aload_2        
        //   198: getfield        android/content/pm/PackageInfo.versionCode:I
        //   201: invokestatic    com/google/android/gms/internal/zzml.zzca:(I)I
        //   204: iload_1        
        //   205: if_icmpge       249
        //   208: ldc             "GooglePlayServicesUtil"
        //   210: new             Ljava/lang/StringBuilder;
        //   213: dup            
        //   214: invokespecial   java/lang/StringBuilder.<init>:()V
        //   217: ldc             "Google Play services out of date.  Requires "
        //   219: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   222: getstatic       com/google/android/gms/common/GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   225: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   228: ldc             " but found "
        //   230: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   233: aload_2        
        //   234: getfield        android/content/pm/PackageInfo.versionCode:I
        //   237: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   240: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   243: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   246: pop            
        //   247: iconst_2       
        //   248: ireturn        
        //   249: aload_2        
        //   250: getfield        android/content/pm/PackageInfo.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //   253: astore_2       
        //   254: aload_2        
        //   255: astore_0       
        //   256: aload_2        
        //   257: ifnonnull       268
        //   260: aload_3        
        //   261: ldc             "com.google.android.gms"
        //   263: iconst_0       
        //   264: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   267: astore_0       
        //   268: aload_0        
        //   269: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   272: ifne            289
        //   275: iconst_3       
        //   276: ireturn        
        //   277: astore_0       
        //   278: ldc             "GooglePlayServicesUtil"
        //   280: ldc             "Google Play services missing when getting application info."
        //   282: aload_0        
        //   283: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   286: pop            
        //   287: iconst_1       
        //   288: ireturn        
        //   289: iconst_0       
        //   290: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  13     24     94     106    Ljava/lang/Throwable;
        //  40     49     106    117    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  117    135    150    162    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  139    147    150    162    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  162    187    150    162    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  260    268    277    289    Landroid/content/pm/PackageManager$NameNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    @Deprecated
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
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2) {
        return showErrorDialogFragment(n, activity, n2, null);
    }
    
    @Deprecated
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        return showErrorDialogFragment(n, activity, null, n2, dialogInterface$OnCancelListener);
    }
    
    public static boolean showErrorDialogFragment(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        final Dialog zza = zza(n, activity, fragment, n2, dialogInterface$OnCancelListener);
        if (zza == null) {
            return false;
        }
        zza(activity, dialogInterface$OnCancelListener, "GooglePlayServicesErrorDialog", zza);
        return true;
    }
    
    private static Dialog zza(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener onCancelListener) {
        final AlertDialog$Builder alertDialog$Builder = null;
        if (n == 0) {
            return null;
        }
        int n3 = n;
        if (zzml.zzan((Context)activity) && (n3 = n) == 2) {
            n3 = 42;
        }
        AlertDialog$Builder alertDialog$Builder2 = alertDialog$Builder;
        if (zzmx.zzqx()) {
            final TypedValue typedValue = new TypedValue();
            activity.getTheme().resolveAttribute(16843529, typedValue, true);
            alertDialog$Builder2 = alertDialog$Builder;
            if ("Theme.Dialog.Alert".equals(activity.getResources().getResourceEntryName(typedValue.resourceId))) {
                alertDialog$Builder2 = new AlertDialog$Builder((Context)activity, 5);
            }
        }
        AlertDialog$Builder alertDialog$Builder3;
        if ((alertDialog$Builder3 = alertDialog$Builder2) == null) {
            alertDialog$Builder3 = new AlertDialog$Builder((Context)activity);
        }
        alertDialog$Builder3.setMessage((CharSequence)zzg.zzc((Context)activity, n3, zzaf((Context)activity)));
        if (onCancelListener != null) {
            alertDialog$Builder3.setOnCancelListener(onCancelListener);
        }
        final Intent zza = GoogleApiAvailability.getInstance().zza((Context)activity, n3, "d");
        zzh zzh;
        if (fragment == null) {
            zzh = new zzh(activity, zza, n2);
        }
        else {
            zzh = new zzh(fragment, zza, n2);
        }
        final String zzh2 = zzg.zzh((Context)activity, n3);
        if (zzh2 != null) {
            alertDialog$Builder3.setPositiveButton((CharSequence)zzh2, (DialogInterface$OnClickListener)zzh);
        }
        final String zzg = com.google.android.gms.common.internal.zzg.zzg((Context)activity, n3);
        if (zzg != null) {
            alertDialog$Builder3.setTitle((CharSequence)zzg);
        }
        return (Dialog)alertDialog$Builder3.create();
    }
    
    public static void zza(Activity supportFragmentManager, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener, final String s, final Dialog dialog) {
        while (true) {
            try {
                final int n = (supportFragmentManager instanceof FragmentActivity) ? 1 : 0;
                if (n != 0) {
                    supportFragmentManager = (Activity)((FragmentActivity)supportFragmentManager).getSupportFragmentManager();
                    SupportErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show((FragmentManager)supportFragmentManager, s);
                    return;
                }
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                final int n = 0;
                continue;
            }
            break;
        }
        if (zzmx.zzqu()) {
            ErrorDialogFragment.newInstance(dialog, dialogInterface$OnCancelListener).show(supportFragmentManager.getFragmentManager(), s);
            return;
        }
        throw new RuntimeException("This Activity does not support Fragments.");
    }
    
    @Deprecated
    public static void zzaa(final Context context) {
        final int googlePlayServicesAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent zza = GoogleApiAvailability.getInstance().zza(context, googlePlayServicesAvailable, "e");
        Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + googlePlayServicesAvailable);
        if (zza == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", zza);
    }
    
    @Deprecated
    public static void zzac(final Context context) {
        if (GooglePlayServicesUtil.zzaaq.getAndSet(true)) {
            return;
        }
        try {
            ((NotificationManager)context.getSystemService("notification")).cancel(10436);
        }
        catch (SecurityException ex) {}
    }
    
    private static void zzad(final Context context) {
        if (!GooglePlayServicesUtil.zzaar.get()) {
            while (true) {
                final Context context2;
            Label_0173:
                while (true) {
                    synchronized (GooglePlayServicesUtil.zzpy) {
                        if (GooglePlayServicesUtil.zzaao == null) {
                            GooglePlayServicesUtil.zzaao = context.getPackageName();
                            try {
                                final Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                                if (metaData != null) {
                                    GooglePlayServicesUtil.zzaap = metaData.getInt("com.google.android.gms.version");
                                }
                                else {
                                    GooglePlayServicesUtil.zzaap = null;
                                }
                                final Integer zzaap = GooglePlayServicesUtil.zzaap;
                                // monitorexit(GooglePlayServicesUtil.zzpy)
                                if (zzaap == null) {
                                    throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                                }
                                break Label_0173;
                            }
                            catch (PackageManager$NameNotFoundException ex) {
                                Log.wtf("GooglePlayServicesUtil", "This should never happen.", (Throwable)ex);
                                continue;
                            }
                            continue;
                        }
                    }
                    if (!GooglePlayServicesUtil.zzaao.equals(context2.getPackageName())) {
                        throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + GooglePlayServicesUtil.zzaao + "' and this call used package '" + context2.getPackageName() + "'.");
                    }
                    continue;
                }
                if ((int)context2 != GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                    throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE + " but" + " found " + context2 + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
                }
                break;
            }
        }
    }
    
    public static String zzaf(final Context context) {
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
    
    public static boolean zzah(final Context context) {
        if (zzmx.zzqA()) {
            final Bundle applicationRestrictions = ((UserManager)context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
            if (applicationRestrictions != null && "true".equals(applicationRestrictions.getString("restricted_profile"))) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean zzb(final Context context, int n, final String s) {
        final boolean b = false;
        Label_0030: {
            if (!zzmx.zzqB()) {
                break Label_0030;
            }
            final AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService("appops");
            try {
                appOpsManager.checkPackage(n, s);
                boolean b2 = true;
                Label_0028: {
                    return b2;
                }
                final String[] packagesForUid = context.getPackageManager().getPackagesForUid(n);
                b2 = b;
                // iftrue(Label_0028:, s == null)
                // iftrue(Label_0028:, n >= packagesForUid.length)
                // iftrue(Label_0028:, packagesForUid == null)
            Block_6:
                while (true) {
                    Block_4: {
                        break Block_4;
                        b2 = b;
                        break Block_6;
                    }
                    b2 = b;
                    n = 0;
                    continue;
                    Label_0076:
                    ++n;
                    continue;
                }
                // iftrue(Label_0076:, !s.equals((Object)packagesForUid[n]))
                return true;
            }
            catch (SecurityException ex) {
                return false;
            }
        }
    }
    
    public static boolean zzb(final PackageManager packageManager) {
        while (true) {
            boolean b = true;
            synchronized (GooglePlayServicesUtil.zzpy) {
            Label_0052:
                while (true) {
                    if (GooglePlayServicesUtil.zzaan != -1) {
                        break Label_0052;
                    }
                    while (true) {
                        try {
                            if (zzd.zznu().zza(packageManager.getPackageInfo("com.google.android.gms", 64), zzc.zzaad[1]) != null) {
                                GooglePlayServicesUtil.zzaan = 1;
                            }
                            else {
                                GooglePlayServicesUtil.zzaan = 0;
                            }
                            if (GooglePlayServicesUtil.zzaan != 0) {
                                return b;
                            }
                            break;
                        }
                        catch (PackageManager$NameNotFoundException ex) {
                            GooglePlayServicesUtil.zzaan = 0;
                            continue Label_0052;
                        }
                        continue Label_0052;
                    }
                    break;
                }
            }
            b = false;
            return b;
        }
    }
    
    @Deprecated
    public static boolean zzb(final PackageManager packageManager, final String s) {
        return zzd.zznu().zzb(packageManager, s);
    }
    
    public static boolean zzc(final PackageManager packageManager) {
        return zzb(packageManager) || !zznt();
    }
    
    @Deprecated
    public static boolean zzd(final Context context, final int n) {
        return n == 18 || (n == 1 && zzj(context, "com.google.android.gms"));
    }
    
    public static boolean zze(final Context context, final int n) {
        return zzb(context, n, "com.google.android.gms") && zzb(context.getPackageManager(), "com.google.android.gms");
    }
    
    static boolean zzj(final Context context, final String s) {
        if (zzmx.zzqD()) {
            final Iterator<PackageInstaller$SessionInfo> iterator = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (iterator.hasNext()) {
                if (s.equals(iterator.next().getAppPackageName())) {
                    return true;
                }
            }
        }
        if (zzah(context)) {
            return false;
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationInfo(s, 8192).enabled;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    private static int zzns() {
        return 8115000;
    }
    
    public static boolean zznt() {
        if (GooglePlayServicesUtil.zzaal) {
            return GooglePlayServicesUtil.zzaam;
        }
        return "user".equals(Build.TYPE);
    }
}
