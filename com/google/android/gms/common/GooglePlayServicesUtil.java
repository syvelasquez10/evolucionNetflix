// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import android.os.Build;
import java.util.Iterator;
import android.content.pm.PackageInstaller$SessionInfo;
import com.google.android.gms.common.internal.zzn;
import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.os.Bundle;
import android.app.NotificationManager;
import android.util.Log;
import android.app.AppOpsManager;
import android.content.Intent;
import android.content.DialogInterface$OnClickListener;
import com.google.android.gms.common.internal.zzh;
import com.google.android.gms.common.internal.zzg;
import android.app.AlertDialog$Builder;
import android.util.TypedValue;
import com.google.android.gms.internal.zzlk;
import com.google.android.gms.internal.zzlv;
import android.support.v4.app.FragmentManager;
import android.app.Dialog;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.content.DialogInterface$OnCancelListener;
import android.app.Activity;
import android.content.res.Resources;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;

public final class GooglePlayServicesUtil
{
    @Deprecated
    public static final int GOOGLE_PLAY_SERVICES_VERSION_CODE;
    public static boolean zzYu;
    public static boolean zzYv;
    private static int zzYw;
    private static String zzYx;
    private static Integer zzYy;
    static final AtomicBoolean zzYz;
    private static final Object zzpm;
    
    static {
        GOOGLE_PLAY_SERVICES_VERSION_CODE = zzmW();
        GooglePlayServicesUtil.zzYu = false;
        GooglePlayServicesUtil.zzYv = false;
        GooglePlayServicesUtil.zzYw = -1;
        zzpm = new Object();
        GooglePlayServicesUtil.zzYx = null;
        GooglePlayServicesUtil.zzYy = null;
        zzYz = new AtomicBoolean();
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
    
    @Deprecated
    public static int isGooglePlayServicesAvailable(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: getstatic       com/google/android/gms/common/internal/zzd.zzacF:Z
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
        //    49: invokestatic    com/google/android/gms/common/zzd.zzmY:()Lcom/google/android/gms/common/zzd;
        //    52: astore          4
        //    54: aload_2        
        //    55: getfield        android/content/pm/PackageInfo.versionCode:I
        //    58: invokestatic    com/google/android/gms/internal/zzlk.zzbX:(I)Z
        //    61: ifne            71
        //    64: aload_0        
        //    65: invokestatic    com/google/android/gms/internal/zzlk.zzao:(Landroid/content/Context;)Z
        //    68: ifeq            117
        //    71: aload           4
        //    73: aload_2        
        //    74: getstatic       com/google/android/gms/common/zzc$zzbu.zzYt:[Lcom/google/android/gms/common/zzc$zza;
        //    77: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //    80: ifnonnull       233
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
        //   122: bipush          64
        //   124: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   127: getstatic       com/google/android/gms/common/zzc$zzbu.zzYt:[Lcom/google/android/gms/common/zzc$zza;
        //   130: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //   133: astore          5
        //   135: aload           5
        //   137: ifnonnull       193
        //   140: ldc             "GooglePlayServicesUtil"
        //   142: ldc             "Google Play Store signature invalid."
        //   144: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   147: pop            
        //   148: bipush          9
        //   150: ireturn        
        //   151: astore          5
        //   153: aload_0        
        //   154: ldc             "com.android.vending"
        //   156: invokestatic    com/google/android/gms/common/GooglePlayServicesUtil.zzh:(Landroid/content/Context;Ljava/lang/String;)Z
        //   159: ifeq            222
        //   162: ldc             "GooglePlayServicesUtil"
        //   164: ldc             "Google Play Store is updating."
        //   166: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   169: pop            
        //   170: aload           4
        //   172: aload_2        
        //   173: getstatic       com/google/android/gms/common/zzc$zzbu.zzYt:[Lcom/google/android/gms/common/zzc$zza;
        //   176: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //   179: ifnonnull       233
        //   182: ldc             "GooglePlayServicesUtil"
        //   184: ldc             "Google Play services signature invalid."
        //   186: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   189: pop            
        //   190: bipush          9
        //   192: ireturn        
        //   193: aload           4
        //   195: aload_2        
        //   196: iconst_1       
        //   197: anewarray       Lcom/google/android/gms/common/zzc$zza;
        //   200: dup            
        //   201: iconst_0       
        //   202: aload           5
        //   204: aastore        
        //   205: invokevirtual   com/google/android/gms/common/zzd.zza:(Landroid/content/pm/PackageInfo;[Lcom/google/android/gms/common/zzc$zza;)Lcom/google/android/gms/common/zzc$zza;
        //   208: ifnonnull       233
        //   211: ldc             "GooglePlayServicesUtil"
        //   213: ldc             "Google Play services signature invalid."
        //   215: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   218: pop            
        //   219: bipush          9
        //   221: ireturn        
        //   222: ldc             "GooglePlayServicesUtil"
        //   224: ldc             "Google Play Store is neither installed nor updating."
        //   226: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   229: pop            
        //   230: bipush          9
        //   232: ireturn        
        //   233: getstatic       com/google/android/gms/common/GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   236: invokestatic    com/google/android/gms/internal/zzlk.zzbV:(I)I
        //   239: istore_1       
        //   240: aload_2        
        //   241: getfield        android/content/pm/PackageInfo.versionCode:I
        //   244: invokestatic    com/google/android/gms/internal/zzlk.zzbV:(I)I
        //   247: iload_1        
        //   248: if_icmpge       292
        //   251: ldc             "GooglePlayServicesUtil"
        //   253: new             Ljava/lang/StringBuilder;
        //   256: dup            
        //   257: invokespecial   java/lang/StringBuilder.<init>:()V
        //   260: ldc             "Google Play services out of date.  Requires "
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: getstatic       com/google/android/gms/common/GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE:I
        //   268: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   271: ldc             " but found "
        //   273: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   276: aload_2        
        //   277: getfield        android/content/pm/PackageInfo.versionCode:I
        //   280: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   283: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   286: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   289: pop            
        //   290: iconst_2       
        //   291: ireturn        
        //   292: aload_2        
        //   293: getfield        android/content/pm/PackageInfo.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //   296: astore_2       
        //   297: aload_2        
        //   298: astore_0       
        //   299: aload_2        
        //   300: ifnonnull       311
        //   303: aload_3        
        //   304: ldc             "com.google.android.gms"
        //   306: iconst_0       
        //   307: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //   310: astore_0       
        //   311: aload_0        
        //   312: getfield        android/content/pm/ApplicationInfo.enabled:Z
        //   315: ifne            335
        //   318: iconst_3       
        //   319: ireturn        
        //   320: astore_0       
        //   321: ldc             "GooglePlayServicesUtil"
        //   323: ldc             "Google Play services missing when getting application info."
        //   325: invokestatic    android/util/Log.wtf:(Ljava/lang/String;Ljava/lang/String;)I
        //   328: pop            
        //   329: aload_0        
        //   330: invokevirtual   android/content/pm/PackageManager$NameNotFoundException.printStackTrace:()V
        //   333: iconst_1       
        //   334: ireturn        
        //   335: iconst_0       
        //   336: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  13     24     94     106    Ljava/lang/Throwable;
        //  40     49     106    117    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  117    135    151    193    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  140    148    151    193    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  193    219    151    193    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  303    311    320    335    Landroid/content/pm/PackageManager$NameNotFoundException;
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
    
    public static boolean showErrorDialogFragment(final int n, Activity activity, Fragment zza, final int n2, final DialogInterface$OnCancelListener dialogInterface$OnCancelListener) {
        int n3 = 0;
        zza = (Fragment)zza(n, activity, zza, n2, dialogInterface$OnCancelListener);
        if (zza == null) {
            return false;
        }
        while (true) {
            try {
                n3 = ((activity instanceof FragmentActivity) ? 1 : 0);
                if (n3 != 0) {
                    activity = (Activity)((FragmentActivity)activity).getSupportFragmentManager();
                    SupportErrorDialogFragment.newInstance((Dialog)zza, dialogInterface$OnCancelListener).show((FragmentManager)activity, "GooglePlayServicesErrorDialog");
                }
                else {
                    if (!zzlv.zzpO()) {
                        throw new RuntimeException("This Activity does not support Fragments.");
                    }
                    activity = (Activity)activity.getFragmentManager();
                    ErrorDialogFragment.newInstance((Dialog)zza, dialogInterface$OnCancelListener).show((android.app.FragmentManager)activity, "GooglePlayServicesErrorDialog");
                }
                return true;
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                continue;
            }
            break;
        }
    }
    
    private static Dialog zza(final int n, final Activity activity, final Fragment fragment, final int n2, final DialogInterface$OnCancelListener onCancelListener) {
        final AlertDialog$Builder alertDialog$Builder = null;
        if (n == 0) {
            return null;
        }
        int n3 = n;
        if (zzlk.zzao((Context)activity) && (n3 = n) == 2) {
            n3 = 42;
        }
        AlertDialog$Builder alertDialog$Builder2 = alertDialog$Builder;
        if (zzlv.zzpR()) {
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
        alertDialog$Builder3.setMessage((CharSequence)zzg.zzb((Context)activity, n3, zzaf((Context)activity)));
        if (onCancelListener != null) {
            alertDialog$Builder3.setOnCancelListener(onCancelListener);
        }
        final Intent zzbc = zzbc(n3);
        zzh zzh;
        if (fragment == null) {
            zzh = new zzh(activity, zzbc, n2);
        }
        else {
            zzh = new zzh(fragment, zzbc, n2);
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
    
    public static boolean zza(final Context context, int n, final String s) {
        final boolean b = false;
        Label_0030: {
            if (!zzlv.zzpV()) {
                break Label_0030;
            }
            final AppOpsManager appOpsManager = (AppOpsManager)context.getSystemService("appops");
            try {
                appOpsManager.checkPackage(n, s);
                boolean b2 = true;
                Label_0028: {
                    return b2;
                }
                // iftrue(Label_0028:, packagesForUid == null)
                // iftrue(Label_0028:, s == null)
            Block_5:
                while (true) {
                    b2 = b;
                    break Block_5;
                    final String[] packagesForUid = context.getPackageManager().getPackagesForUid(n);
                    b2 = b;
                    continue;
                }
                n = 0;
                // iftrue(Label_0028:, n >= packagesForUid.length)
            Block_6:
                while (true) {
                    b2 = b;
                    break Block_6;
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
    
    @Deprecated
    public static void zzaa(final Context context) {
        final int googlePlayServicesAvailable = isGooglePlayServicesAvailable(context);
        if (googlePlayServicesAvailable == 0) {
            return;
        }
        final Intent zzbc = zzbc(googlePlayServicesAvailable);
        Log.e("GooglePlayServicesUtil", "GooglePlayServices not available due to error " + googlePlayServicesAvailable);
        if (zzbc == null) {
            throw new GooglePlayServicesNotAvailableException(googlePlayServicesAvailable);
        }
        throw new GooglePlayServicesRepairableException(googlePlayServicesAvailable, "Google Play Services not available", zzbc);
    }
    
    @Deprecated
    public static void zzac(final Context context) {
        if (GooglePlayServicesUtil.zzYz.getAndSet(true)) {
            return;
        }
        try {
            ((NotificationManager)context.getSystemService("notification")).cancel(10436);
        }
        catch (SecurityException ex) {}
    }
    
    private static void zzad(final Context context) {
        while (true) {
            final Context context2;
        Label_0163:
            while (true) {
                synchronized (GooglePlayServicesUtil.zzpm) {
                    if (GooglePlayServicesUtil.zzYx == null) {
                        GooglePlayServicesUtil.zzYx = context.getPackageName();
                        try {
                            final Bundle metaData = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
                            if (metaData != null) {
                                GooglePlayServicesUtil.zzYy = metaData.getInt("com.google.android.gms.version");
                            }
                            else {
                                GooglePlayServicesUtil.zzYy = null;
                            }
                            final Integer zzYy = GooglePlayServicesUtil.zzYy;
                            // monitorexit(GooglePlayServicesUtil.zzpm)
                            if (zzYy == null) {
                                throw new IllegalStateException("A required meta-data tag in your app's AndroidManifest.xml does not exist.  You must have the following declaration within the <application> element:     <meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />");
                            }
                            break Label_0163;
                        }
                        catch (PackageManager$NameNotFoundException ex) {
                            Log.wtf("GooglePlayServicesUtil", "This should never happen.", (Throwable)ex);
                            continue;
                        }
                        continue;
                    }
                }
                if (!GooglePlayServicesUtil.zzYx.equals(context2.getPackageName())) {
                    throw new IllegalArgumentException("isGooglePlayServicesAvailable should only be called with Context from your application's package. A previous call used package '" + GooglePlayServicesUtil.zzYx + "' and this call used package '" + context2.getPackageName() + "'.");
                }
                continue;
            }
            if ((int)context2 != GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE) {
                throw new IllegalStateException("The meta-data tag in your app's AndroidManifest.xml does not have the right value.  Expected " + GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE + " but" + " found " + context2 + ".  You must have the" + " following declaration within the <application> element: " + "    <meta-data android:name=\"" + "com.google.android.gms.version" + "\" android:value=\"@integer/google_play_services_version\" />");
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
    
    public static boolean zzb(final PackageManager packageManager) {
        synchronized (GooglePlayServicesUtil.zzpm) {
        Label_0050:
            while (true) {
                if (GooglePlayServicesUtil.zzYw != -1) {
                    break Label_0050;
                }
                while (true) {
                    try {
                        if (zzd.zzmY().zza(packageManager.getPackageInfo("com.google.android.gms", 64), zzc.zzYm[1]) != null) {
                            GooglePlayServicesUtil.zzYw = 1;
                        }
                        else {
                            GooglePlayServicesUtil.zzYw = 0;
                        }
                        // monitorexit(GooglePlayServicesUtil.zzpm)
                        if (GooglePlayServicesUtil.zzYw != 0) {
                            return true;
                        }
                        break;
                    }
                    catch (PackageManager$NameNotFoundException ex) {
                        GooglePlayServicesUtil.zzYw = 0;
                        continue Label_0050;
                    }
                    continue Label_0050;
                }
                break;
            }
        }
        return false;
    }
    
    @Deprecated
    public static boolean zzb(final PackageManager packageManager, final String s) {
        return zzd.zzmY().zzb(packageManager, s);
    }
    
    @Deprecated
    public static Intent zzbc(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 1:
            case 2: {
                return zzn.zzcp("com.google.android.gms");
            }
            case 42: {
                return zzn.zzoM();
            }
            case 3: {
                return zzn.zzcn("com.google.android.gms");
            }
        }
    }
    
    public static boolean zzc(final PackageManager packageManager) {
        return zzb(packageManager) || !zzmX();
    }
    
    @Deprecated
    public static boolean zzd(final Context context, final int n) {
        return n == 18 || (n == 1 && zzh(context, "com.google.android.gms"));
    }
    
    public static boolean zze(final Context context, final int n) {
        return zza(context, n, "com.google.android.gms") && zzb(context.getPackageManager(), "com.google.android.gms");
    }
    
    public static boolean zzh(final Context context, final String s) {
        if (zzlv.zzpX()) {
            final Iterator<PackageInstaller$SessionInfo> iterator = context.getPackageManager().getPackageInstaller().getAllSessions().iterator();
            while (iterator.hasNext()) {
                if (s.equals(iterator.next().getAppPackageName())) {
                    return true;
                }
            }
        }
        else {
            final PackageManager packageManager = context.getPackageManager();
            try {
                if (packageManager.getApplicationInfo(s, 8192).enabled) {
                    return true;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
        return false;
    }
    
    private static int zzmW() {
        return 7895000;
    }
    
    public static boolean zzmX() {
        if (GooglePlayServicesUtil.zzYu) {
            return GooglePlayServicesUtil.zzYv;
        }
        return "user".equals(Build.TYPE);
    }
}
