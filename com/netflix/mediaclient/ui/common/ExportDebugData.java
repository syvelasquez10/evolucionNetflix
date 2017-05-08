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
import android.widget.Toast;
import java.util.Locale;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.Intent;
import android.net.Uri;
import java.io.FileOutputStream;
import java.util.Map;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Iterator;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import com.netflix.mediaclient.Log;
import java.io.File;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import java.util.zip.ZipOutputStream;
import android.content.Context;
import android.app.Activity;

public class ExportDebugData
{
    private static final String AUTHORITY = "com.netflix.mediaclient.debugdata.fileprovider";
    private static final String PATH = "debug_data";
    private static final String PREFS_NAMES = "ExportDebugData.PREFS_NAMES";
    private static final String PREF_CRASHED_PID = "ExportDebugData.PREF_CRASHED_PID";
    private static final long SIZE_LIMIT = 5242880L;
    private static final String TAG = "ExportDebugData";
    private static long lastReportTime;
    
    static {
        ExportDebugData.lastReportTime = 0L;
    }
    
    private static void addFalkorMemCache(final Context context, final ZipOutputStream zipOutputStream) {
        final NetflixActivity netflixActivity = AndroidUtils.getContextAs(context, NetflixActivity.class);
        if (netflixActivity != null) {
            final ServiceManager serviceManager = netflixActivity.getServiceManager();
            if (serviceManager != null) {
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
    }
    
    private static void addOfflineRealm(final Context context, final ZipOutputStream zipOutputStream) {
        final File file = new File(context.getFilesDir(), RealmUtils.sCurrentConfig.getRealmFileName());
        if (file.exists()) {
            Log.i("ExportDebugData", "Adding " + file.getAbsolutePath());
            zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
            copyFile(new FileInputStream(file), zipOutputStream);
            zipOutputStream.closeEntry();
            return;
        }
        throw new IOException("Unable to find " + file.getAbsolutePath());
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
    
    private static void createBugReport(final Activity activity) {
        while (true) {
            ArrayList<Uri> list = null;
            Intent intent = null;
        Label_0631:
            while (true) {
                Label_0751: {
                    try {
                        final File file = new File(activity.getFilesDir(), "debug_data");
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
                        addFalkorMemCache((Context)activity, zipOutputStream);
                        addOfflineRealm((Context)activity, zipOutputStream);
                        addOfflineRegistry((Context)activity, zipOutputStream);
                        addPreferences((Context)activity, zipOutputStream, "nfxpref");
                        zipOutputStream.close();
                        list = new ArrayList<Uri>();
                        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)activity)) {
                            final File screenshot = screenshot(activity, file);
                            if (screenshot != null) {
                                list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + screenshot.getName()));
                            }
                        }
                        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)activity)) {
                            final File logcat = logcat((Context)activity, file);
                            if (logcat != null) {
                                list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + logcat.getName()));
                            }
                        }
                        if (!file2.exists()) {
                            throw new IOException("Unable to create: " + file2.getAbsolutePath());
                        }
                        list.add(Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + "debug_data.zip"));
                        final NetflixActivity netflixActivity = AndroidUtils.getContextAs((Context)activity, NetflixActivity.class);
                        if (netflixActivity == null || netflixActivity.getServiceManager() == null || netflixActivity.getServiceManager().getCurrentProfile() == null) {
                            break Label_0751;
                        }
                        final String string = "user=" + netflixActivity.getServiceManager().getCurrentProfile().getEmail() + " (" + netflixActivity.getServiceManager().getCurrentProfile().getLanguagesInCsv() + ")\n";
                        intent = new Intent("android.intent.action.SEND_MULTIPLE");
                        intent.setType("text/plain");
                        intent.putExtra("android.intent.extra.EMAIL", new String[] { "spy-issues@netflix.com" });
                        intent.putExtra("android.intent.extra.SUBJECT", "Enter_JIRA_summary_here");
                        intent.putExtra("android.intent.extra.TEXT", "\n\nEnter_JIRA_description_here\n\n\n\n\npackage=com.netflix.mediaclient\nversion=4.13.0 build 14540\ncode=14540\nandroid=" + Build$VERSION.SDK_INT + "\nbrand=" + Build.BRAND + "\nmanufacturer=" + Build.MANUFACTURER + "\nmodel=" + Build.MODEL + "\ndevice=" + Build.DEVICE + "\ndevice.locale=" + Locale.getDefault().getCountry() + "_" + Locale.getDefault().getLanguage() + "\n" + string);
                        final Iterator<Uri> iterator = list.iterator();
                        while (iterator.hasNext()) {
                            grantRead((Context)activity, intent, iterator.next());
                        }
                    }
                    catch (Throwable t) {
                        Log.e("ExportDebugData", "Oops, cannot createBugReport data", t);
                        Toast.makeText((Context)activity, (CharSequence)("Oops, cannot createBugReport data, see error in logs (" + t.toString() + ")"), 1).show();
                        return;
                    }
                    break Label_0631;
                }
                final String string = "";
                continue;
            }
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)list);
            intent.addFlags(1);
            activity.startActivity(Intent.createChooser(intent, (CharSequence)"Send email..."));
        }
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
            IoUtil.safeClose(bufferedReader);
            IoUtil.safeClose(fileWriter);
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
                IoUtil.safeClose(fileOutputStream);
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
}
