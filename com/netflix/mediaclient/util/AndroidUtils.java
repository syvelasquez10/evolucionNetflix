// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import android.content.pm.ResolveInfo;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import android.os.Bundle;
import java.util.Iterator;
import android.content.Intent;
import android.util.DisplayMetrics;
import java.util.regex.Matcher;
import com.netflix.mediaclient.javabridge.transport.NativeTransport;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.media.PlayerType;
import com.netflix.mediaclient.service.configuration.PlayerTypeFactory;
import android.text.format.Formatter;
import android.os.StatFs;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.security.NoSuchAlgorithmException;
import android.content.pm.PackageManager$NameNotFoundException;
import android.util.Base64;
import java.security.MessageDigest;
import android.os.Build$VERSION;
import android.os.StrictMode$VmPolicy$Builder;
import android.os.StrictMode;
import android.os.StrictMode$ThreadPolicy$Builder;
import android.os.Debug;
import android.os.Environment;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.io.File;
import android.content.Context;
import java.util.regex.Pattern;

public final class AndroidUtils
{
    private static final String EXIT = "exit\n";
    public static final String FILENAME = "FILENAME";
    private static final String NOT_AVAILABLE = "n/a";
    public static final String OUTRES = "OUTRES";
    private static final String SHELL = "/system/bin/sh";
    private static final String[] SU_COMMANDS;
    private static final String TAG = "nf_utils";
    private static final String[] TEST_COMMANDS;
    private static final Pattern UID_PATTERN;
    public static final boolean debug = false;
    public static final boolean enableTestServer = false;
    public static final boolean release = true;
    private static String rootShell;
    
    static {
        UID_PATTERN = Pattern.compile("^uid=(\\d+).*?");
        SU_COMMANDS = new String[] { "su", "/system/xbin/su", "/system/bin/su" };
        TEST_COMMANDS = new String[] { "id", "/system/xbin/id", "/system/bin/id" };
    }
    
    private static boolean checkSu() {
        final String[] su_COMMANDS = AndroidUtils.SU_COMMANDS;
        for (int length = su_COMMANDS.length, i = 0; i < length; ++i) {
            AndroidUtils.rootShell = su_COMMANDS[i];
            if (isRootUid()) {
                return true;
            }
        }
        AndroidUtils.rootShell = null;
        return false;
    }
    
    public static void clearApplicationData(final Context context) {
        final File file = new File(context.getCacheDir().getParent());
        if (file.exists()) {
            final String[] list = file.list();
            for (int length = list.length, i = 0; i < length; ++i) {
                final String s = list[i];
                if (!s.equals("lib")) {
                    deleteDir(new File(file, s));
                    if (Log.isLoggable("nf_utils", 3)) {
                        Log.i("TAG", "File /data/data/com.netflix.mediaclient/" + s + " DELETED");
                    }
                }
            }
        }
    }
    
    public static boolean deleteDir(final File file) {
        if (file != null && file.isDirectory()) {
            final String[] list = file.list();
            for (int i = 0; i < list.length; ++i) {
                if (!deleteDir(new File(file, list[i]))) {
                    return false;
                }
            }
        }
        return file.delete();
    }
    
    public static int dipToPixels(final Context context, final int n) {
        return (int)(n * context.getResources().getDisplayMetrics().density + 0.5f);
    }
    
    private static String doRunCommanByProcess(final String p0, final String p1, final OUTPUT p2) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          6
        //     3: aconst_null    
        //     4: astore          7
        //     6: aconst_null    
        //     7: astore_3       
        //     8: aconst_null    
        //     9: astore          5
        //    11: aload           6
        //    13: astore          4
        //    15: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
        //    18: aload_0        
        //    19: invokevirtual   java/lang/Runtime.exec:(Ljava/lang/String;)Ljava/lang/Process;
        //    22: astore_0       
        //    23: aload_0        
        //    24: astore          5
        //    26: aload           6
        //    28: astore          4
        //    30: aload_0        
        //    31: astore_3       
        //    32: new             Ljava/io/DataOutputStream;
        //    35: dup            
        //    36: aload_0        
        //    37: invokevirtual   java/lang/Process.getOutputStream:()Ljava/io/OutputStream;
        //    40: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    43: astore          6
        //    45: aload_0        
        //    46: aload_2        
        //    47: invokestatic    com/netflix/mediaclient/util/AndroidUtils.sinkProcessOutput:(Ljava/lang/Process;Lcom/netflix/mediaclient/util/AndroidUtils$OUTPUT;)Lcom/netflix/mediaclient/util/AndroidUtils$InputStreamHandler;
        //    50: astore_2       
        //    51: aload           6
        //    53: new             Ljava/lang/StringBuilder;
        //    56: dup            
        //    57: invokespecial   java/lang/StringBuilder.<init>:()V
        //    60: aload_1        
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    64: bipush          10
        //    66: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: invokevirtual   java/io/DataOutputStream.writeBytes:(Ljava/lang/String;)V
        //    75: aload           6
        //    77: invokevirtual   java/io/DataOutputStream.flush:()V
        //    80: aload           6
        //    82: ldc             "exit\n"
        //    84: invokevirtual   java/io/DataOutputStream.writeBytes:(Ljava/lang/String;)V
        //    87: aload           6
        //    89: invokevirtual   java/io/DataOutputStream.flush:()V
        //    92: aload_0        
        //    93: invokevirtual   java/lang/Process.waitFor:()I
        //    96: pop            
        //    97: aload_2        
        //    98: ifnull          130
        //   101: aload_2        
        //   102: invokevirtual   com/netflix/mediaclient/util/AndroidUtils$InputStreamHandler.getOutput:()Ljava/lang/String;
        //   105: astore_1       
        //   106: aload           6
        //   108: ifnull          116
        //   111: aload           6
        //   113: invokevirtual   java/io/DataOutputStream.close:()V
        //   116: aload_1        
        //   117: astore_2       
        //   118: aload_0        
        //   119: ifnull          128
        //   122: aload_0        
        //   123: invokevirtual   java/lang/Process.destroy:()V
        //   126: aload_1        
        //   127: astore_2       
        //   128: aload_2        
        //   129: areturn        
        //   130: aconst_null    
        //   131: astore_2       
        //   132: aload           6
        //   134: ifnull          142
        //   137: aload           6
        //   139: invokevirtual   java/io/DataOutputStream.close:()V
        //   142: aload_0        
        //   143: ifnull          128
        //   146: aload_0        
        //   147: invokevirtual   java/lang/Process.destroy:()V
        //   150: aconst_null    
        //   151: areturn        
        //   152: astore_0       
        //   153: aconst_null    
        //   154: areturn        
        //   155: astore_2       
        //   156: aload           5
        //   158: astore_0       
        //   159: aload           7
        //   161: astore_1       
        //   162: aload_1        
        //   163: astore          4
        //   165: aload_0        
        //   166: astore_3       
        //   167: aload_2        
        //   168: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   171: astore_2       
        //   172: aload_1        
        //   173: astore          4
        //   175: aload_0        
        //   176: astore_3       
        //   177: ldc             "nf_utils"
        //   179: new             Ljava/lang/StringBuilder;
        //   182: dup            
        //   183: invokespecial   java/lang/StringBuilder.<init>:()V
        //   186: ldc             "runCommand error: "
        //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   191: aload_2        
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   198: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   201: pop            
        //   202: aload_1        
        //   203: astore          4
        //   205: aload_0        
        //   206: astore_3       
        //   207: new             Ljava/io/IOException;
        //   210: dup            
        //   211: aload_2        
        //   212: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   215: athrow         
        //   216: astore_0       
        //   217: aload           4
        //   219: ifnull          227
        //   222: aload           4
        //   224: invokevirtual   java/io/DataOutputStream.close:()V
        //   227: aload_3        
        //   228: ifnull          235
        //   231: aload_3        
        //   232: invokevirtual   java/lang/Process.destroy:()V
        //   235: aload_0        
        //   236: athrow         
        //   237: astore_1       
        //   238: goto            235
        //   241: astore_1       
        //   242: aload           6
        //   244: astore          4
        //   246: aload_0        
        //   247: astore_3       
        //   248: aload_1        
        //   249: astore_0       
        //   250: goto            217
        //   253: astore_2       
        //   254: aload           6
        //   256: astore_1       
        //   257: goto            162
        //   260: astore_0       
        //   261: aload_1        
        //   262: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  15     23     155    162    Ljava/lang/Exception;
        //  15     23     216    217    Any
        //  32     45     155    162    Ljava/lang/Exception;
        //  32     45     216    217    Any
        //  45     97     253    260    Ljava/lang/Exception;
        //  45     97     241    253    Any
        //  101    106    253    260    Ljava/lang/Exception;
        //  101    106    241    253    Any
        //  111    116    260    263    Ljava/lang/Exception;
        //  122    126    260    263    Ljava/lang/Exception;
        //  137    142    152    155    Ljava/lang/Exception;
        //  146    150    152    155    Ljava/lang/Exception;
        //  167    172    216    217    Any
        //  177    202    216    217    Any
        //  207    216    216    217    Any
        //  222    227    237    241    Ljava/lang/Exception;
        //  231    235    237    241    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 148, Size: 148
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
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
    
    private static String doRunCommand(final String s, final OUTPUT output) throws IOException {
        return doRunCommanByProcess("/system/bin/sh", s, output);
    }
    
    public static void dumpHprofToDisk() {
        final File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        externalStoragePublicDirectory.mkdirs();
        final String absolutePath = new File(externalStoragePublicDirectory, "netflix.prof").getAbsolutePath();
        try {
            Log.v("nf_utils", "************************************************************");
            Log.v("nf_utils", "Dumping HPROF profile to file...");
            Log.v("nf_utils", "************************************************************");
            Debug.dumpHprofData(absolutePath);
            Log.v("nf_utils", "************************************************************");
            Log.v("nf_utils", "Dump complete.  File: " + absolutePath);
            Log.v("nf_utils", "************************************************************");
        }
        catch (IOException ex) {
            Log.handleException("nf_utils", ex);
        }
    }
    
    public static String dumpMemInfo(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("Process can not be null!");
        }
        return "SKIP DUMP_INFO";
    }
    
    public static void enableStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode$ThreadPolicy$Builder().detectAll().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode$VmPolicy$Builder().detectAll().penaltyLog().build());
    }
    
    public static int getAndroidVersion() {
        return Build$VERSION.SDK_INT;
    }
    
    public static String[] getAppSignatures(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        String[] array;
        if (packageManager == null) {
            Log.e("nf_utils", "Package manager not found, this should NOT happen");
            array = null;
        }
        else {
            final String[] array2 = null;
            final String[] array3 = null;
            String[] array5;
            final String[] array4 = array5 = null;
            String[] array6 = array2;
            String[] array7 = array3;
            try {
                final PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 64);
                array5 = array4;
                array6 = array2;
                array7 = array3;
                if (Log.isLoggable("nf_utils", 3)) {
                    array5 = array4;
                    array6 = array2;
                    array7 = array3;
                    Log.d("nf_utils", "Found # signatures: " + packageInfo.signatures.length);
                }
                array5 = array4;
                array6 = array2;
                array7 = array3;
                final String[] array8 = new String[packageInfo.signatures.length];
                int n = 0;
                while (true) {
                    array = array8;
                    array5 = array8;
                    array6 = array8;
                    array7 = array8;
                    if (n >= packageInfo.signatures.length) {
                        break;
                    }
                    array5 = array8;
                    array6 = array8;
                    array7 = array8;
                    final MessageDigest instance = MessageDigest.getInstance("SHA");
                    array5 = array8;
                    array6 = array8;
                    array7 = array8;
                    instance.update(packageInfo.signatures[n].toByteArray());
                    array5 = array8;
                    array6 = array8;
                    array7 = array8;
                    array8[n] = new String(Base64.encode(instance.digest(), 0));
                    array5 = array8;
                    array6 = array8;
                    array7 = array8;
                    if (Log.isLoggable("nf_utils", 3)) {
                        array5 = array8;
                        array6 = array8;
                        array7 = array8;
                        Log.d("nf_utils", "hash key[" + n + "]:" + array8[n]);
                    }
                    ++n;
                }
            }
            catch (PackageManager$NameNotFoundException ex) {
                Log.e("nf_utils", "Name not found", (Throwable)ex);
                return array5;
            }
            catch (NoSuchAlgorithmException ex2) {
                Log.e("nf_utils", "No such an algorithm", ex2);
                return array6;
            }
            catch (Exception ex3) {
                Log.e("nf_utils", "Error while getting signature", ex3);
                return array7;
            }
        }
        return array;
    }
    
    public static long getAvailableInternalMemory() {
        final StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocks() * statFs.getBlockSize();
    }
    
    public static String getAvailableInternalMemoryAsString(final Context context) {
        return Formatter.formatFileSize(context, getAvailableInternalMemory());
    }
    
    public static int getDimensionInDip(final Context context, final int n) {
        return (int)(context.getResources().getDimension(n) / context.getResources().getDisplayMetrics().density);
    }
    
    public static String getHeapSizeString(final Context context) {
        return Formatter.formatShortFileSize(context, Runtime.getRuntime().maxMemory());
    }
    
    public static PackageInfo getPackageInfo(final Context context, final String s) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (s == null) {
            throw new IllegalArgumentException("App name cannot be null!");
        }
        try {
            PackageInfo packageInfo;
            if ((packageInfo = context.getPackageManager().getPackageInfo(s, 0)) == null) {
                packageInfo = null;
            }
            return packageInfo;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    public static String getUserAgent(final Context context) {
        final String version = AndroidManifestUtils.getVersion(context);
        final int versionCode = AndroidManifestUtils.getVersionCode(context);
        PlayerType playerType;
        if ((playerType = PlayerTypeFactory.getCurrentType(context)) == null) {
            Log.e("nf_utils", "This should not happen, player type was null at this point! Use default.");
            playerType = PlayerType.device6;
        }
        final String mapPlayerType = mapPlayerType(playerType);
        final StringBuilder sb = new StringBuilder();
        sb.append("Netflix/").append(SecurityRepository.getNrdLibVersion());
        sb.append(' ').append("NCCP/2.15");
        sb.append(" (DEVTYPE=").append(version).append("-").append(versionCode);
        sb.append(' ').append("R").append(' ').append(SecurityRepository.getNrdLibVersion());
        sb.append(" android-").append(getAndroidVersion()).append('-');
        sb.append(mapPlayerType).append(" ; CERTVER=0)");
        return sb.toString();
    }
    
    public static boolean hasHoneycombAtLeast() {
        return getAndroidVersion() >= 11;
    }
    
    @SuppressLint({ "NewApi" })
    public static boolean isActivityFinishedOrDestroyed(final Activity activity) {
        boolean b = false;
        if (activity instanceof NetflixActivity) {
            b = ((NetflixActivity)activity).destroyed();
        }
        else if (getAndroidVersion() >= 17) {
            b = activity.isDestroyed();
        }
        return b || activity.isFinishing();
    }
    
    public static boolean isAppInstalled(final Context context, final String s) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            packageManager.getPackageInfo(s, 1);
            return true;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean isDrmPlayDrmPlayPost3() {
        return getAndroidVersion() >= 11 && isDrmPlaySupported();
    }
    
    public static final boolean isDrmPlaySupported() {
        if (!NativeTransport.isDrmPlayPresent()) {
            Log.d("nf_utils", "drm.play component is not found");
            return false;
        }
        Log.d("nf_utils", "drm.play component found.");
        return true;
    }
    
    public static boolean isHd() {
        return NativeTransport.isHdCapable();
    }
    
    public static boolean isNetflixPreloaded(final Context context) {
        boolean b = false;
        final PackageManager packageManager = context.getPackageManager();
        try {
            final PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if ((packageInfo.applicationInfo.flags & 0x1) != 0x0 || (packageInfo.applicationInfo.flags & 0x80) != 0x0) {
                b = true;
            }
            if (Log.isLoggable("nf_utils", 3)) {
                Log.d("nf_utils", "Netflix Stub/App present in the system folder ?=" + b + " ApplicationInfo.flags=" + packageInfo.applicationInfo.flags);
            }
            return b;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    public static boolean isOpenMaxALSupportMainprofile() {
        return NativeTransport.isOMXALmpCapable();
    }
    
    public static boolean isOpenMaxALSupported() {
        return getAndroidVersion() > 13;
    }
    
    public static boolean isPreHoneycombDevice() {
        return getAndroidVersion() < 11;
    }
    
    public static boolean isPropertyStreamingVideoDrs() {
        return NativeTransport.isPropertyStreamingVideoDrs();
    }
    
    private static boolean isRootUid() {
        boolean b = true;
        String runCommand = null;
        final String[] test_COMMANDS = AndroidUtils.TEST_COMMANDS;
        for (int length = test_COMMANDS.length, i = 0; i < length; ++i) {
            runCommand = runCommand(test_COMMANDS[i]);
            if (runCommand != null && runCommand.length() > 0) {
                break;
            }
        }
        if (runCommand == null || runCommand.length() == 0) {
            b = false;
        }
        else {
            final Matcher matcher = AndroidUtils.UID_PATTERN.matcher(runCommand);
            if (!matcher.matches() || !"0".equals(matcher.group(1))) {
                return false;
            }
        }
        return b;
    }
    
    public static boolean isScreenResolutionSameOrMore(final Activity activity, final int n, final int n2) {
        if (activity == null || activity.getWindowManager() == null) {
            Log.e("nf_utils", "Unable to get window manager! It should not happen!");
        }
        else {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            if (Log.isLoggable("nf_utils", 3)) {
                Log.d("nf_utils", "Given size (w,h): " + n + ", " + n2);
                Log.d("nf_utils", "Screen size (w,h): " + displayMetrics.widthPixels + ", " + displayMetrics.heightPixels);
            }
            if (displayMetrics.widthPixels < n || displayMetrics.heightPixels < n2) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isSuAvailable() {
        synchronized (AndroidUtils.class) {
            if (AndroidUtils.rootShell == null) {
                checkSu();
            }
            return AndroidUtils.rootShell != null;
        }
    }
    
    public static void logDeviceDensity(final Activity activity) {
        if (!Log.isLoggable("nf_utils", 3) || activity == null) {
            return;
        }
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.d("nf_utils", "Logical density: " + displayMetrics.density);
        Log.d("nf_utils", "DPI density: " + displayMetrics.densityDpi);
        switch (displayMetrics.densityDpi) {
            default: {
                Log.d("nf_utils", "Uknown screen density!");
            }
            case 120: {
                Log.d("nf_utils", "ldpi - 120 pixels/inch; dpi scale = .75 (4 dpi = 3 pixels)");
            }
            case 160: {
                Log.d("nf_utils", "mdpi - 160 pixels/inch; dpi scale = 1 (1 dpi = 1 pixel)");
            }
            case 240: {
                Log.d("nf_utils", "hdpi - 240 pixels/inch; dpi scale = 1.5 (2 dpi = 3 pixels)");
            }
            case 213: {
                Log.d("nf_utils", "tvhdpi - 213 pixels/inch; dpi scale = 1.33 (1 dpi = 3 pixels)");
            }
            case 320: {
                Log.d("nf_utils", "xhdpi - 320 pixels/inch; dpi scale = 2 (1 dpi = 2 pixels)");
            }
            case 480: {
                Log.d("nf_utils", "xxhdpi - 480 pixels/inch; dpi scale = 3 (1 dpi = 3 pixels)");
            }
        }
    }
    
    public static void logIntent(final String s, final Intent intent) {
        if (Log.isLoggable(s, 3)) {
            Log.d(s, "intent.getAction(): " + intent.getAction());
            if (intent.getCategories() == null) {
                Log.d(s, "intent.getCategories(): null");
            }
            else {
                final Iterator<String> iterator = intent.getCategories().iterator();
                while (iterator.hasNext()) {
                    Log.d(s, "intent.category: " + iterator.next());
                }
            }
            Log.d(s, "intent.getData(): " + intent.getData());
            Log.d(s, "intent.getDataString(): " + intent.getDataString());
            Log.d(s, "intent.getScheme(): " + intent.getScheme());
            Log.d(s, "intent.getType(): " + intent.getType());
            final Bundle extras = intent.getExtras();
            if (extras != null && !extras.isEmpty()) {
                for (final String s2 : extras.keySet()) {
                    Log.d(s, "EXTRA: {" + s2 + ": " + extras.get(s2) + "}");
                }
            }
            else {
                Log.d(s, "NO EXTRAS");
            }
        }
    }
    
    public static void logVerboseIntentInfo(final String s, final Intent intent) {
        if (!Log.isLoggable(s, 2)) {
            return;
        }
        String decode;
        String string;
        String string2;
        Label_0031_Outer:Label_0042_Outer:
        while (true) {
            decode = "n/a";
            while (true) {
            Label_0144:
                while (true) {
                Label_0133:
                    while (true) {
                        try {
                            if (intent.getDataString() == null) {
                                decode = "n/a";
                            }
                            else {
                                decode = URLDecoder.decode(intent.getDataString(), "utf-8");
                            }
                            if (intent.getCategories() != null) {
                                break Label_0133;
                            }
                            string = "n/a";
                            if (intent.getExtras() == null) {
                                string2 = "n/a";
                                Log.v(s, String.format("Handling intent\n   action: %s\n   uri: %s\n   decodedUri: %s\n   categories: %s\n   extras: %s", intent.getAction(), intent.getDataString(), decode, string, string2));
                                return;
                            }
                            break Label_0144;
                        }
                        catch (UnsupportedEncodingException ex) {
                            Log.w(s, "Couldn't decode url: " + intent.getDataString());
                            continue Label_0031_Outer;
                        }
                        continue Label_0031_Outer;
                    }
                    string = intent.getCategories().toString();
                    continue Label_0042_Outer;
                }
                string2 = intent.getExtras().toString();
                continue;
            }
        }
    }
    
    public static String mapPlayerType(final PlayerType playerType) {
        if (playerType == PlayerType.device2 || playerType == PlayerType.device3 || playerType == PlayerType.device5) {
            return "drmplay";
        }
        if (playerType == PlayerType.device1 || playerType == PlayerType.device4) {
            return "omx";
        }
        if (playerType == PlayerType.device6) {
            return "visualon";
        }
        if (playerType == PlayerType.device7) {
            return "XAL";
        }
        if (playerType == PlayerType.device8) {
            return "XALMP";
        }
        if (playerType == PlayerType.device9) {
            return "NFAMP";
        }
        if (playerType == PlayerType.device10) {
            return "JPLAYER";
        }
        if (playerType == PlayerType.device11) {
            return "JPLAYERBASE";
        }
        if (playerType == PlayerType.device12) {
            return "JPLAYER2";
        }
        return "N/A";
    }
    
    public static ResolveInfo queryIntentActivities(final Context context, final Intent intent) {
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null!");
        }
        if (intent == null) {
            throw new IllegalArgumentException("Intent cannot be null!");
        }
        try {
            return context.getPackageManager().resolveActivity(intent, 0);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static String runCommand(String doRunCommand) {
        if (doRunCommand == null) {
            throw new IllegalArgumentException("Cmd can not be null!");
        }
        try {
            doRunCommand = doRunCommand(doRunCommand, OUTPUT.STDERR);
            return doRunCommand;
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public static InputStreamHandler sinkProcessOutput(final Process process, final OUTPUT output) {
        switch (output) {
            default: {
                return null;
            }
            case STDOUT: {
                final InputStreamHandler inputStreamHandler = new InputStreamHandler(process.getErrorStream(), false);
                new InputStreamHandler(process.getInputStream(), true);
                return inputStreamHandler;
            }
            case STDERR: {
                final InputStreamHandler inputStreamHandler2 = new InputStreamHandler(process.getInputStream(), false);
                new InputStreamHandler(process.getErrorStream(), true);
                return inputStreamHandler2;
            }
            case BOTH: {
                new InputStreamHandler(process.getInputStream(), true);
                new InputStreamHandler(process.getErrorStream(), true);
                return null;
            }
        }
    }
    
    private static class InputStreamHandler extends Thread
    {
        StringBuffer output;
        private final boolean sink;
        private final InputStream stream;
        
        InputStreamHandler(final InputStream stream, final boolean sink) {
            this.sink = sink;
            this.stream = stream;
            this.start();
        }
        
        public String getOutput() {
            return this.output.toString();
        }
        
        @Override
        public void run() {
            try {
                if (this.sink) {
                    while (this.stream.read() != -1) {}
                    return;
                }
                this.output = new StringBuffer();
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.stream));
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    this.output.append(line);
                }
            }
            catch (IOException ex) {}
        }
    }
    
    enum OUTPUT
    {
        BOTH, 
        STDERR, 
        STDOUT;
    }
}
