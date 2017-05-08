// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import java.util.Hashtable;
import android.content.pm.ResolveInfo;
import android.widget.Toast;
import android.net.Uri;
import java.util.Locale;
import android.os.Build;
import android.os.Build$VERSION;
import android.content.Intent;
import java.io.FileOutputStream;
import android.app.Activity;
import java.util.Map;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Iterator;
import java.io.FilenameFilter;
import java.util.List;
import java.util.ArrayList;
import com.netflix.mediaclient.util.AndroidUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import com.netflix.mediaclient.Log;
import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import java.util.zip.ZipOutputStream;
import android.content.Context;

class ExportDebugData
{
    private static final String AUTHORITY = "com.netflix.mediaclient.debugdata.fileprovider";
    private static final String PATH = "debug_data";
    private static final long SIZE_LIMIT = 5242880L;
    private static final String TAG = "ExportDebugData";
    
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
        file.list(new ExportDebugData$1(list));
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
        //    44: ldc             "Wrote "
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: iload_2        
        //    50: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    53: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    56: invokestatic    com/netflix/mediaclient/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    59: pop            
        //    60: aload_0        
        //    61: ifnull          68
        //    64: aload_0        
        //    65: invokevirtual   java/io/InputStream.close:()V
        //    68: return         
        //    69: astore_1       
        //    70: aload_0        
        //    71: ifnull          78
        //    74: aload_0        
        //    75: invokevirtual   java/io/InputStream.close:()V
        //    78: aload_1        
        //    79: athrow         
        //    80: astore_0       
        //    81: return         
        //    82: astore_0       
        //    83: goto            78
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      9      69     80     Any
        //  9      16     69     80     Any
        //  20     28     69     80     Any
        //  35     60     69     80     Any
        //  64     68     80     82     Ljava/io/IOException;
        //  74     78     82     86     Ljava/io/IOException;
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
    
    public static void export(final Activity activity) {
        File file2;
        try {
            final File file = new File(activity.getFilesDir(), "debug_data");
            if (!file.exists() && (file.exists() || !file.mkdirs())) {
                throw new IOException("Unable to create directories: " + file.getAbsolutePath());
            }
            Log.i("ExportDebugData", "Preparing " + "debug_data.zip" + " in " + file.getAbsolutePath());
            file2 = new File(file, "debug_data.zip");
            if (!file2.exists() || file2.delete()) {
                final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(file2));
                zipOutputStream.setLevel(9);
                addOfflineRealm((Context)activity, zipOutputStream);
                addOfflineRegistry((Context)activity, zipOutputStream);
                addPreferences((Context)activity, zipOutputStream, "nfxpref");
                zipOutputStream.close();
                if (file2.exists()) {
                    final Intent intent = new Intent("android.intent.action.SEND_MULTIPLE");
                    intent.setType("text/plain");
                    intent.putExtra("android.intent.extra.SUBJECT", "Netflix Android Bug Report : com.netflix.mediaclient 4.12.1 build 14299 14299");
                    intent.putExtra("android.intent.extra.TEXT", "\n\n\n[" + Build$VERSION.SDK_INT + " " + Build.BRAND + " " + Build.MANUFACTURER + " " + Build.MODEL + " " + Build.DEVICE + " " + Locale.getDefault().getCountry() + " " + Locale.getDefault().getLanguage() + "]");
                    final ArrayList<Uri> list = new ArrayList<Uri>();
                    final Uri parse = Uri.parse("content://com.netflix.mediaclient.debugdata.fileprovider/debug_data/" + "debug_data.zip");
                    list.add(parse);
                    grantRead((Context)activity, intent, parse);
                    intent.putParcelableArrayListExtra("android.intent.extra.STREAM", (ArrayList)list);
                    intent.addFlags(1);
                    activity.startActivity(Intent.createChooser(intent, (CharSequence)"Send email..."));
                    return;
                }
                throw new IOException("Unable to create: " + file2.getAbsolutePath());
            }
        }
        catch (Throwable t) {
            Log.e("ExportDebugData", "Oops, cannot export data", t);
            Toast.makeText((Context)activity, (CharSequence)("Oops, cannot export data, see error in logs (" + t.toString() + ")"), 1).show();
            return;
        }
        throw new IOException("Unable to write: " + file2.getAbsolutePath());
    }
    
    private static void grantRead(final Context context, final Intent intent, final Uri uri) {
        final Iterator<ResolveInfo> iterator = context.getPackageManager().queryIntentActivities(intent, 65536).iterator();
        while (iterator.hasNext()) {
            context.grantUriPermission(iterator.next().activityInfo.packageName, uri, 1);
        }
    }
}
