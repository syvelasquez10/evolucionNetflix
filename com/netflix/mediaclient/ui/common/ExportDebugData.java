// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import java.util.Hashtable;
import android.view.View;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import java.io.Closeable;
import com.netflix.mediaclient.util.IoUtil;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.os.Process;
import android.content.pm.ResolveInfo;
import java.util.concurrent.TimeUnit;
import android.os.SystemClock;
import android.text.TextUtils;
import android.widget.Toast;
import java.util.Locale;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.Intent;
import android.app.Activity;
import android.net.Uri;
import com.netflix.falkor.cache.FalkorCache;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import io.realm.RealmConfiguration;
import java.util.Map;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Iterator;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.io.File;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.zip.ZipOutputStream;
import android.content.Context;

public class ExportDebugData
{
    private static final String AUTHORITY = "com.netflix.mediaclient.debugdata.fileprovider";
    private static final String PATH = "debug_data";
    private static final String PREFS_NAMES = "ExportDebugData.PREFS_NAMES";
    private static final String PREF_CRASHED_PID = "ExportDebugData.PREF_CRASHED_PID";
    private static final long SIZE_LIMIT = 5242880L;
    private static final String TAG = "ExportDebugData";
    private static final String THREAD_DUMP_FILENAME = "thread_dump.txt";
    private static long lastReportTime;
    
    static {
        ExportDebugData.lastReportTime = 0L;
    }
    
    private static void addFalkorMemCache(final Context context, final ZipOutputStream zipOutputStream) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(context, NetflixActivity.class);
        if (netflixActivity != null) {
            final ServiceManager serviceManager = netflixActivity.getServiceManager();
            if (serviceManager == null || !serviceManager.isReady()) {
                Log.i("ExportDebugData", "Unable to export falkor mem cache, ServiceManager not available");
                return;
            }
            final File file = new File(netflixActivity.getFilesDir() + "/cache.txt");
            serviceManager.getBrowse().dumpCacheToDisk(file);
            if (!file.exists()) {
                Log.i("ExportDebugData", "Expected file not found " + file.getAbsolutePath());
                return;
            }
            Log.i("ExportDebugData", "Adding " + file.getAbsolutePath());
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            copyFile(new FileInputStream(file), zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }
    
    private static void addOfflineRegistry(final Context context, final ZipOutputStream zipOutputStream) {
        final File file = new File(AndroidUtils.getExternalDownloadDirIfAvailable(context).getAbsolutePath() + "/.of");
        final ArrayList<Object> list = new ArrayList<Object>();
        file.list(new ExportDebugData$2(list));
        for (final File file2 : list) {
            Log.i("ExportDebugData", "Adding " + file2.getAbsolutePath());
            zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
            copyFile(new FileInputStream(file2), zipOutputStream);
            zipOutputStream.closeEntry();
        }
    }
    
    private static void addPreferences(final Context context, final ZipOutputStream zipOutputStream, final String s) {
        final Map all = context.getSharedPreferences(s, 0).getAll();
        final Properties properties = new Properties();
        for (final String s2 : all.keySet()) {
            if (all.get(s2) != null) {
                ((Hashtable<String, String>)properties).put("." + s2, String.valueOf(all.get(s2)));
            }
            else {
                ((Hashtable<String, String>)properties).put("Default." + s2, "<null value>");
            }
        }
        zipOutputStream.putNextEntry(new ZipEntry("preferences.xml"));
        properties.storeToXML(new PrintStream(zipOutputStream), "Export of " + s);
        zipOutputStream.closeEntry();
    }
    
    private static void addRealmFile(final Context context, final RealmConfiguration realmConfiguration, final ZipOutputStream zipOutputStream) {
        final File file = new File(context.getFilesDir(), realmConfiguration.getRealmFileName());
        if (file.exists()) {
            Log.i("ExportDebugData", "Adding " + file.getAbsolutePath());
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            copyFile(new FileInputStream(file), zipOutputStream);
            zipOutputStream.closeEntry();
            final File file2 = new File(context.getFilesDir(), realmConfiguration.getRealmFileName() + ".lru_backup/journal");
            if (file2.exists()) {
                Log.i("ExportDebugData", "Adding " + file2.getAbsolutePath());
                zipOutputStream.putNextEntry(new ZipEntry(file.getName() + ".lru_backup/journal.txt"));
                copyFile(new FileInputStream(file2), zipOutputStream);
                zipOutputStream.closeEntry();
            }
            return;
        }
        throw new IOException("Unable to find " + file.getAbsolutePath());
    }
    
    private static void copyFile(final InputStream p0, final OutputStream p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_2       
        //     2: sipush          1024
        //     5: newarray        B
        //     7: astore          4
        //     9: aload_0        
        //    10: aload           4
        //    12: invokevirtual   java/io/InputStream.read:([B)I
        //    15: istore_3       
        //    16: iload_3        
        //    17: ifle            35
        //    20: aload_1        
        //    21: aload           4
        //    23: iconst_0       
        //    24: iload_3        
        //    25: invokevirtual   java/io/OutputStream.write:([BII)V
        //    28: iload_2        
        //    29: iload_3        
        //    30: iadd           
        //    31: istore_2       
        //    32: goto            9
        //    35: ldc             "ExportDebugData"
        //    37: new             Ljava/lang/StringBuilder;
        //    40: dup            
        //    41: invokespecial   java/lang/StringBuilder.<init>:()V
        //    44: ldc_w           "Wrote "
        //    47: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    50: iload_2        
        //    51: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    54: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    57: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    60: pop            
        //    61: aload_0        
        //    62: ifnull          69
        //    65: aload_0        
        //    66: invokevirtual   java/io/InputStream.close:()V
        //    69: return         
        //    70: astore_1       
        //    71: aload_0        
        //    72: ifnull          79
        //    75: aload_0        
        //    76: invokevirtual   java/io/InputStream.close:()V
        //    79: aload_1        
        //    80: athrow         
        //    81: astore_0       
        //    82: return         
        //    83: astore_0       
        //    84: goto            79
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      9      70     81     Any
        //  9      16     70     81     Any
        //  20     28     70     81     Any
        //  35     61     70     81     Any
        //  65     69     81     83     Ljava/io/IOException;
        //  75     79     83     87     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 48, Size: 48
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
    
    public static void createBugReport(final Context context, final String s, final String s2) {
        createBugReport(context, s, s2, null);
    }
    
    public static void createBugReport(final Context context, String string, final String s, String string2) {
        while (true) {
            ArrayList<Uri> list = null;
            Intent intent = null;
        Label_0792:
            while (true) {
                Label_0929: {
                    while (true) {
                        try {
                            final File file = new File(context.getFilesDir(), "debug_data");
                            if (!file.exists() && (file.exists() || !file.mkdirs())) {
                                throw new IOException("Unable to create directories: " + file.getAbsolutePath());
                            }
                            Log.i("ExportDebugData", "Preparing " + "debug_data.zip" + " in " + file.getAbsolutePath());
                            final File file2 = new File(file, "debug_data.zip");
                            if (file2.exists() && !file2.delete()) {
                                throw new IOException("Unable to write: " + file2.getAbsolutePath());
                            }
                            final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
                            zipOutputStream.setLevel(9);
                            addFalkorMemCache(context, zipOutputStream);
                            addRealmFile(context, RealmUtils.sCurrentConfig, zipOutputStream);
                            addRealmFile(context, FalkorCache.sRealmConfiguration, zipOutputStream);
                            addOfflineRegistry(context, zipOutputStream);
                            addPreferences(context, zipOutputStream, "nfxpref");
                            zipOutputStream.close();
                            list = new ArrayList<Uri>();
                            final Activity activity = AndroidUtils.getContextAs(context, Activity.class);
                            if (!AndroidUtils.isActivityFinishedOrDestroyed(context)) {
                                final File screenshot = screenshot(activity, file);
                                if (screenshot != null) {
                                    list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + screenshot.getName()));
                                }
                            }
                            final File logcat = logcat(context, file);
                            if (logcat != null) {
                                list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + logcat.getName()));
                            }
                            final File threadDump = threadDump(context, file, string2);
                            if (threadDump != null) {
                                list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + threadDump.getName()));
                            }
                            if (!file2.exists()) {
                                throw new IOException("Unable to create: " + file2.getAbsolutePath());
                            }
                            list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + "debug_data.zip"));
                            final NetflixActivity netflixActivity = AndroidUtils.getContextAs(context, NetflixActivity.class);
                            if (netflixActivity == null || netflixActivity.getServiceManager() == null || !netflixActivity.getServiceManager().isReady() || netflixActivity.getServiceManager().getCurrentProfile() == null) {
                                break Label_0929;
                            }
                            string2 = "user=" + netflixActivity.getServiceManager().getCurrentProfile().getEmail() + " (" + netflixActivity.getServiceManager().getCurrentProfile().getLanguagesInCsv() + ")\n";
                            intent = new Intent("android.intent.action.SEND_MULTIPLE");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.EMAIL", new String[] { "spy-issues@netflix.com" });
                            String s2 = string;
                            if (string == null) {
                                s2 = "Enter_JIRA_summary_here";
                            }
                            intent.putExtra("android.intent.extra.SUBJECT", s2);
                            final StringBuilder append = new StringBuilder().append("\n\nEnter_JIRA_description_here\n\n\n\n\npackage=com.netflix.mediaclient\nversion=4.16.1 build 15145\ncode=15145\nandroid=").append(Build$VERSION.SDK_INT).append("\nbrand=").append(Build.BRAND).append("\nmanufacturer=").append(Build.MANUFACTURER).append("\nmodel=").append(Build.MODEL).append("\ndevice=").append(Build.DEVICE).append("\ndevice.locale=").append(Locale.getDefault().getCountry()).append("_").append(Locale.getDefault().getLanguage()).append("\n").append(string2).append("\n");
                            if (s == null) {
                                string = "";
                                intent.putExtra("android.intent.extra.TEXT", append.append(string).toString());
                                final Iterator<Uri> iterator = list.iterator();
                                while (iterator.hasNext()) {
                                    grantRead(context, intent, iterator.next());
                                }
                                break Label_0792;
                            }
                        }
                        catch (Throwable t) {
                            Log.e("ExportDebugData", "Oops, cannot createBugReport data", t);
                            Toast.makeText(context, (CharSequence)("Oops, cannot createBugReport data, see error in logs (" + t.toString() + ")"), 1).show();
                            return;
                        }
                        string = "\n" + s;
                        continue;
                    }
                }
                string2 = "";
                continue;
            }
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)list);
            intent.addFlags(1);
            final Intent chooser = Intent.createChooser(intent, (CharSequence)"Send email...");
            chooser.addFlags(268435456);
            context.startActivity(chooser);
        }
    }
    
    public static void createBugReportFromCrash(final Context context, final Throwable t, final String s) {
        String string = null;
        String string3;
        if (t != null) {
            final String string2 = t.toString();
            final String s2 = string = "{code}\n" + Log.getStackTraceString(t) + "{code}";
            string3 = string2;
            if (t.getStackTrace() != null) {
                string = s2;
                string3 = string2;
                if (t.getStackTrace()[0] != null) {
                    final String fileName = t.getStackTrace()[0].getFileName();
                    final int lineNumber = t.getStackTrace()[0].getLineNumber();
                    string = s2;
                    string3 = string2;
                    if (!TextUtils.isEmpty((CharSequence)fileName)) {
                        string = s2;
                        string3 = string2;
                        if (lineNumber > 0) {
                            string3 = string2 + " at " + fileName + ":" + lineNumber;
                            string = s2;
                        }
                    }
                }
            }
        }
        else {
            string3 = null;
        }
        createBugReport(context, string3, string, s);
    }
    
    public static void createBugReportWithFeedback(final NetflixActivity netflixActivity) {
        final long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis > ExportDebugData.lastReportTime + TimeUnit.SECONDS.toMillis(10L)) {
            final Toast text = Toast.makeText((Context)netflixActivity, (CharSequence)"Taking screenshot...", 0);
            text.show();
            netflixActivity.getHandler().postDelayed((Runnable)new ExportDebugData$1(text, netflixActivity), 200L);
            ExportDebugData.lastReportTime = uptimeMillis;
        }
    }
    
    private static void grantRead(final Context context, final Intent intent, final Uri uri) {
        final Iterator<ResolveInfo> iterator = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (iterator.hasNext()) {
            context.grantUriPermission(iterator.next().activityInfo.packageName, uri, 1);
        }
    }
    
    private static File logcat(final Context context, File file) {
        Label_0196: {
            BufferedReader bufferedReader;
            FileWriter fileWriter;
            try {
                file = new File(file, "logcat.txt");
                if (file.exists() && !file.delete()) {
                    break Label_0196;
                }
                final int int1 = context.getSharedPreferences("ExportDebugData.PREFS_NAMES", 0).getInt("ExportDebugData.PREF_CRASHED_PID", 0);
                final StringBuilder append = new StringBuilder("logcat -d | grep \"").append(Process.myPid());
                if (int1 != 0) {
                    append.append("\\|").append(int1);
                }
                append.append("\"");
                Log.e("ExportDebugData", "Capture logcat using $" + (Object)append);
                bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(append.toString()).getInputStream()));
                fileWriter = new FileWriter(file);
                while (true) {
                    final String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    fileWriter.append((CharSequence)line).append((CharSequence)"\n");
                }
            }
            catch (Throwable t) {
                Log.e("ExportDebugData", "Unable to capture logcat", t);
                return null;
            }
            IoUtil.safeClose((Closeable)bufferedReader);
            IoUtil.safeClose((Closeable)fileWriter);
            return file;
        }
        Log.e("ExportDebugData", "Unable to capture logcat, " + file.getAbsolutePath() + " cannot be used");
        return null;
    }
    
    public static void markCrashed(final Context context) {
        Log.e("ExportDebugData", "Mark session as crashed");
        context.getSharedPreferences("ExportDebugData.PREFS_NAMES", 0).edit().putInt("ExportDebugData.PREF_CRASHED_PID", Process.myPid()).apply();
    }
    
    private static File screenshot(final Activity activity, File file) {
        try {
            file = new File(file, "screenshot.jpeg");
            if (!file.exists() || file.delete()) {
                final View rootView = activity.getWindow().getDecorView().getRootView();
                rootView.setDrawingCacheEnabled(true);
                final Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
                rootView.setDrawingCacheEnabled(false);
                final FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)fileOutputStream);
                IoUtil.safeClose((Closeable)fileOutputStream);
                return file;
            }
            Log.e("ExportDebugData", "Unable to capture screenshot, " + file.getAbsolutePath() + " cannot be used");
            return null;
        }
        catch (Throwable t) {
            Log.e("ExportDebugData", "Unable to capture screenshot", t);
            return null;
        }
    }
    
    private static File threadDump(final Context p0, final File p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: astore_0       
        //     2: aload_2        
        //     3: ifnonnull       10
        //     6: invokestatic    com/netflix/mediaclient/util/ThreadUtils.dumpThreads:()Ljava/lang/String;
        //     9: astore_0       
        //    10: new             Ljava/io/File;
        //    13: dup            
        //    14: aload_1        
        //    15: ldc             "thread_dump.txt"
        //    17: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    20: astore_1       
        //    21: aload_1        
        //    22: invokevirtual   java/io/File.exists:()Z
        //    25: ifeq            35
        //    28: aload_1        
        //    29: invokevirtual   java/io/File.delete:()Z
        //    32: ifeq            126
        //    35: new             Ljava/io/FileWriter;
        //    38: dup            
        //    39: aload_1        
        //    40: invokespecial   java/io/FileWriter.<init>:(Ljava/io/File;)V
        //    43: astore_2       
        //    44: aload_2        
        //    45: aload_0        
        //    46: invokevirtual   java/io/FileWriter.write:(Ljava/lang/String;)V
        //    49: aload_2        
        //    50: ifnull          61
        //    53: iconst_0       
        //    54: ifeq            85
        //    57: aload_2        
        //    58: invokevirtual   java/io/FileWriter.close:()V
        //    61: aload_1        
        //    62: areturn        
        //    63: astore_0       
        //    64: new             Ljava/lang/NullPointerException;
        //    67: dup            
        //    68: invokespecial   java/lang/NullPointerException.<init>:()V
        //    71: athrow         
        //    72: astore_0       
        //    73: ldc             "ExportDebugData"
        //    75: ldc_w           "Unable to get threadDump"
        //    78: aload_0        
        //    79: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    82: pop            
        //    83: aconst_null    
        //    84: areturn        
        //    85: aload_2        
        //    86: invokevirtual   java/io/FileWriter.close:()V
        //    89: goto            61
        //    92: astore_0       
        //    93: aload_0        
        //    94: athrow         
        //    95: astore_1       
        //    96: aload_2        
        //    97: ifnull          108
        //   100: aload_0        
        //   101: ifnull          119
        //   104: aload_2        
        //   105: invokevirtual   java/io/FileWriter.close:()V
        //   108: aload_1        
        //   109: athrow         
        //   110: astore_2       
        //   111: aload_0        
        //   112: aload_2        
        //   113: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   116: goto            108
        //   119: aload_2        
        //   120: invokevirtual   java/io/FileWriter.close:()V
        //   123: goto            108
        //   126: ldc             "ExportDebugData"
        //   128: new             Ljava/lang/StringBuilder;
        //   131: dup            
        //   132: invokespecial   java/lang/StringBuilder.<init>:()V
        //   135: ldc_w           "Unable to get threadDump, "
        //   138: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: aload_1        
        //   142: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: ldc_w           " cannot be used"
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   157: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   160: pop            
        //   161: aconst_null    
        //   162: areturn        
        //   163: astore_1       
        //   164: aconst_null    
        //   165: astore_0       
        //   166: goto            96
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      10     72     85     Ljava/lang/Throwable;
        //  10     35     72     85     Ljava/lang/Throwable;
        //  35     44     72     85     Ljava/lang/Throwable;
        //  44     49     92     96     Ljava/lang/Throwable;
        //  44     49     163    169    Any
        //  57     61     63     72     Ljava/lang/Throwable;
        //  64     72     72     85     Ljava/lang/Throwable;
        //  85     89     72     85     Ljava/lang/Throwable;
        //  93     95     95     96     Any
        //  104    108    110    119    Ljava/lang/Throwable;
        //  108    110    72     85     Ljava/lang/Throwable;
        //  111    116    72     85     Ljava/lang/Throwable;
        //  119    123    72     85     Ljava/lang/Throwable;
        //  126    161    72     85     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 90, Size: 90
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
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
}
