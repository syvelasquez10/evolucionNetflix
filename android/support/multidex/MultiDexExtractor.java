// 
// Decompiled by Procyon v0.5.30
// 

package android.support.multidex;

import java.util.zip.ZipException;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import android.content.pm.ApplicationInfo;
import android.os.Build$VERSION;
import android.content.SharedPreferences;
import android.content.Context;
import java.io.File;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.IOException;
import android.util.Log;
import java.io.Closeable;
import java.lang.reflect.InvocationTargetException;
import android.content.SharedPreferences$Editor;
import java.lang.reflect.Method;

final class MultiDexExtractor
{
    private static Method sApplyMethod;
    
    static {
        try {
            MultiDexExtractor.sApplyMethod = SharedPreferences$Editor.class.getMethod("apply", (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex) {
            MultiDexExtractor.sApplyMethod = null;
        }
    }
    
    private static void apply(final SharedPreferences$Editor sharedPreferences$Editor) {
        if (MultiDexExtractor.sApplyMethod == null) {
            goto Label_0020;
        }
        try {
            MultiDexExtractor.sApplyMethod.invoke(sharedPreferences$Editor, new Object[0]);
        }
        catch (IllegalAccessException ex) {}
        catch (InvocationTargetException ex2) {
            goto Label_0020;
        }
    }
    
    private static void closeQuietly(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.w("MultiDex", "Failed to close resource", (Throwable)ex);
        }
    }
    
    private static void extract(final ZipFile p0, final ZipEntry p1, final File p2, final String p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   java/util/zip/ZipFile.getInputStream:(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
        //     5: astore_0       
        //     6: aload_3        
        //     7: ldc             ".zip"
        //     9: aload_2        
        //    10: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //    13: invokestatic    java/io/File.createTempFile:(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;
        //    16: astore_3       
        //    17: ldc             "MultiDex"
        //    19: new             Ljava/lang/StringBuilder;
        //    22: dup            
        //    23: invokespecial   java/lang/StringBuilder.<init>:()V
        //    26: ldc             "Extracting "
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: aload_3        
        //    32: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    38: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    41: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //    44: pop            
        //    45: new             Ljava/util/zip/ZipOutputStream;
        //    48: dup            
        //    49: new             Ljava/io/BufferedOutputStream;
        //    52: dup            
        //    53: new             Ljava/io/FileOutputStream;
        //    56: dup            
        //    57: aload_3        
        //    58: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    61: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    64: invokespecial   java/util/zip/ZipOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    67: astore          5
        //    69: new             Ljava/util/zip/ZipEntry;
        //    72: dup            
        //    73: ldc             "classes.dex"
        //    75: invokespecial   java/util/zip/ZipEntry.<init>:(Ljava/lang/String;)V
        //    78: astore          6
        //    80: aload           6
        //    82: aload_1        
        //    83: invokevirtual   java/util/zip/ZipEntry.getTime:()J
        //    86: invokevirtual   java/util/zip/ZipEntry.setTime:(J)V
        //    89: aload           5
        //    91: aload           6
        //    93: invokevirtual   java/util/zip/ZipOutputStream.putNextEntry:(Ljava/util/zip/ZipEntry;)V
        //    96: sipush          16384
        //    99: newarray        B
        //   101: astore_1       
        //   102: aload_0        
        //   103: aload_1        
        //   104: invokevirtual   java/io/InputStream.read:([B)I
        //   107: istore          4
        //   109: iload           4
        //   111: iconst_m1      
        //   112: if_icmpeq       134
        //   115: aload           5
        //   117: aload_1        
        //   118: iconst_0       
        //   119: iload           4
        //   121: invokevirtual   java/util/zip/ZipOutputStream.write:([BII)V
        //   124: aload_0        
        //   125: aload_1        
        //   126: invokevirtual   java/io/InputStream.read:([B)I
        //   129: istore          4
        //   131: goto            109
        //   134: aload           5
        //   136: invokevirtual   java/util/zip/ZipOutputStream.closeEntry:()V
        //   139: aload           5
        //   141: invokevirtual   java/util/zip/ZipOutputStream.close:()V
        //   144: ldc             "MultiDex"
        //   146: new             Ljava/lang/StringBuilder;
        //   149: dup            
        //   150: invokespecial   java/lang/StringBuilder.<init>:()V
        //   153: ldc             "Renaming to "
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: aload_2        
        //   159: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   168: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   171: pop            
        //   172: aload_3        
        //   173: aload_2        
        //   174: invokevirtual   java/io/File.renameTo:(Ljava/io/File;)Z
        //   177: ifne            247
        //   180: new             Ljava/io/IOException;
        //   183: dup            
        //   184: new             Ljava/lang/StringBuilder;
        //   187: dup            
        //   188: invokespecial   java/lang/StringBuilder.<init>:()V
        //   191: ldc             "Failed to rename \""
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: aload_3        
        //   197: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: ldc             "\" to \""
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: aload_2        
        //   209: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: ldc             "\""
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   223: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   226: athrow         
        //   227: astore_1       
        //   228: aload_0        
        //   229: invokestatic    android/support/multidex/MultiDexExtractor.closeQuietly:(Ljava/io/Closeable;)V
        //   232: aload_3        
        //   233: invokevirtual   java/io/File.delete:()Z
        //   236: pop            
        //   237: aload_1        
        //   238: athrow         
        //   239: astore_1       
        //   240: aload           5
        //   242: invokevirtual   java/util/zip/ZipOutputStream.close:()V
        //   245: aload_1        
        //   246: athrow         
        //   247: aload_0        
        //   248: invokestatic    android/support/multidex/MultiDexExtractor.closeQuietly:(Ljava/io/Closeable;)V
        //   251: aload_3        
        //   252: invokevirtual   java/io/File.delete:()Z
        //   255: pop            
        //   256: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  45     69     227    239    Any
        //  69     109    239    247    Any
        //  115    131    239    247    Any
        //  134    139    239    247    Any
        //  139    227    227    239    Any
        //  240    247    227    239    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    private static SharedPreferences getMultiDexPreferences(final Context context) {
        int n;
        if (Build$VERSION.SDK_INT < 11) {
            n = 0;
        }
        else {
            n = 4;
        }
        return context.getSharedPreferences("multidex.version", n);
    }
    
    private static long getTimeStamp(final File file) {
        long lastModified;
        final long n = lastModified = file.lastModified();
        if (n == -1L) {
            lastModified = n - 1L;
        }
        return lastModified;
    }
    
    private static long getZipCrc(final File file) {
        long zipCrc;
        final long n = zipCrc = ZipUtil.getZipCrc(file);
        if (n == -1L) {
            zipCrc = n - 1L;
        }
        return zipCrc;
    }
    
    private static boolean isModified(final Context context, final File file, final long n) {
        final SharedPreferences multiDexPreferences = getMultiDexPreferences(context);
        return multiDexPreferences.getLong("timestamp", -1L) != getTimeStamp(file) || multiDexPreferences.getLong("crc", -1L) != n;
    }
    
    static List<File> load(Context loadExistingExtractions, final ApplicationInfo applicationInfo, final File file, final boolean b) {
        Log.i("MultiDex", "MultiDexExtractor.load(" + applicationInfo.sourceDir + ", " + b + ")");
        final File file2 = new File(applicationInfo.sourceDir);
        final long zipCrc = getZipCrc(file2);
        while (true) {
            Label_0165: {
                if (b || isModified((Context)loadExistingExtractions, file2, zipCrc)) {
                    break Label_0165;
                }
                try {
                    loadExistingExtractions = loadExistingExtractions((Context)loadExistingExtractions, file2, file);
                    Log.i("MultiDex", "load found " + ((List)loadExistingExtractions).size() + " secondary dex files");
                    return (List<File>)loadExistingExtractions;
                }
                catch (IOException ex) {
                    Log.w("MultiDex", "Failed to reload existing extracted secondary dex files, falling back to fresh extraction", (Throwable)ex);
                    final List<File> performExtractions = performExtractions(file2, file);
                    putStoredApkInfo((Context)loadExistingExtractions, getTimeStamp(file2), zipCrc, performExtractions.size() + 1);
                    loadExistingExtractions = performExtractions;
                    continue;
                }
            }
            Log.i("MultiDex", "Detected that extraction must be performed.");
            final List<File> performExtractions2 = performExtractions(file2, file);
            putStoredApkInfo((Context)loadExistingExtractions, getTimeStamp(file2), zipCrc, performExtractions2.size() + 1);
            loadExistingExtractions = performExtractions2;
            continue;
        }
    }
    
    private static List<File> loadExistingExtractions(final Context context, final File file, final File file2) {
        Log.i("MultiDex", "loading existing secondary dex files");
        final String string = file.getName() + ".classes";
        final int int1 = getMultiDexPreferences(context).getInt("dex.number", 1);
        final ArrayList list = new ArrayList<File>(int1);
        for (int i = 2; i <= int1; ++i) {
            final File file3 = new File(file2, string + i + ".zip");
            if (!file3.isFile()) {
                throw new IOException("Missing extracted secondary dex file '" + file3.getPath() + "'");
            }
            list.add(file3);
            if (!verifyZipFile(file3)) {
                Log.i("MultiDex", "Invalid zip file: " + file3);
                throw new IOException("Invalid ZIP file.");
            }
        }
        return (List<File>)list;
    }
    
    private static void mkdirChecked(final File file) {
        file.mkdir();
        if (!file.isDirectory()) {
            final File parentFile = file.getParentFile();
            if (parentFile == null) {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". Parent file is null.");
            }
            else {
                Log.e("MultiDex", "Failed to create dir " + file.getPath() + ". parent file is a dir " + parentFile.isDirectory() + ", a file " + parentFile.isFile() + ", exists " + parentFile.exists() + ", readable " + parentFile.canRead() + ", writable " + parentFile.canWrite());
            }
            throw new IOException("Failed to create cache directory " + file.getPath());
        }
    }
    
    private static List<File> performExtractions(final File p0, final File p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/lang/StringBuilder;
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: aload_0        
        //     8: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    11: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    14: ldc_w           ".classes"
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    23: astore          7
        //    25: aload_1        
        //    26: aload           7
        //    28: invokestatic    android/support/multidex/MultiDexExtractor.prepareDexDir:(Ljava/io/File;Ljava/lang/String;)V
        //    31: new             Ljava/util/ArrayList;
        //    34: dup            
        //    35: invokespecial   java/util/ArrayList.<init>:()V
        //    38: astore          6
        //    40: new             Ljava/util/zip/ZipFile;
        //    43: dup            
        //    44: aload_0        
        //    45: invokespecial   java/util/zip/ZipFile.<init>:(Ljava/io/File;)V
        //    48: astore          8
        //    50: aload           8
        //    52: new             Ljava/lang/StringBuilder;
        //    55: dup            
        //    56: invokespecial   java/lang/StringBuilder.<init>:()V
        //    59: ldc_w           "classes"
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: iconst_2       
        //    66: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    69: ldc_w           ".dex"
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    78: invokevirtual   java/util/zip/ZipFile.getEntry:(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
        //    81: astore_0       
        //    82: iconst_2       
        //    83: istore_3       
        //    84: aload_0        
        //    85: ifnull          421
        //    88: new             Ljava/io/File;
        //    91: dup            
        //    92: aload_1        
        //    93: new             Ljava/lang/StringBuilder;
        //    96: dup            
        //    97: invokespecial   java/lang/StringBuilder.<init>:()V
        //   100: aload           7
        //   102: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   105: iload_3        
        //   106: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   109: ldc             ".zip"
        //   111: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   114: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   117: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   120: astore          9
        //   122: aload           6
        //   124: aload           9
        //   126: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   131: pop            
        //   132: ldc             "MultiDex"
        //   134: new             Ljava/lang/StringBuilder;
        //   137: dup            
        //   138: invokespecial   java/lang/StringBuilder.<init>:()V
        //   141: ldc_w           "Extraction is needed for file "
        //   144: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   147: aload           9
        //   149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   152: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   155: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   158: pop            
        //   159: iconst_0       
        //   160: istore          4
        //   162: iconst_0       
        //   163: istore_2       
        //   164: iload_2        
        //   165: iconst_3       
        //   166: if_icmpge       322
        //   169: iload           4
        //   171: ifne            322
        //   174: iload_2        
        //   175: iconst_1       
        //   176: iadd           
        //   177: istore_2       
        //   178: aload           8
        //   180: aload_0        
        //   181: aload           9
        //   183: aload           7
        //   185: invokestatic    android/support/multidex/MultiDexExtractor.extract:(Ljava/util/zip/ZipFile;Ljava/util/zip/ZipEntry;Ljava/io/File;Ljava/lang/String;)V
        //   188: aload           9
        //   190: invokestatic    android/support/multidex/MultiDexExtractor.verifyZipFile:(Ljava/io/File;)Z
        //   193: istore          4
        //   195: new             Ljava/lang/StringBuilder;
        //   198: dup            
        //   199: invokespecial   java/lang/StringBuilder.<init>:()V
        //   202: ldc_w           "Extraction "
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: astore          10
        //   210: iload           4
        //   212: ifeq            458
        //   215: ldc_w           "success"
        //   218: astore          5
        //   220: ldc             "MultiDex"
        //   222: aload           10
        //   224: aload           5
        //   226: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   229: ldc_w           " - length "
        //   232: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   235: aload           9
        //   237: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   243: ldc_w           ": "
        //   246: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   249: aload           9
        //   251: invokevirtual   java/io/File.length:()J
        //   254: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   257: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   260: invokestatic    android/util/Log.i:(Ljava/lang/String;Ljava/lang/String;)I
        //   263: pop            
        //   264: iload           4
        //   266: ifne            455
        //   269: aload           9
        //   271: invokevirtual   java/io/File.delete:()Z
        //   274: pop            
        //   275: aload           9
        //   277: invokevirtual   java/io/File.exists:()Z
        //   280: ifeq            455
        //   283: ldc             "MultiDex"
        //   285: new             Ljava/lang/StringBuilder;
        //   288: dup            
        //   289: invokespecial   java/lang/StringBuilder.<init>:()V
        //   292: ldc_w           "Failed to delete corrupted secondary dex '"
        //   295: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   298: aload           9
        //   300: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   306: ldc_w           "'"
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   312: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   315: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   318: pop            
        //   319: goto            164
        //   322: iload           4
        //   324: ifne            382
        //   327: new             Ljava/io/IOException;
        //   330: dup            
        //   331: new             Ljava/lang/StringBuilder;
        //   334: dup            
        //   335: invokespecial   java/lang/StringBuilder.<init>:()V
        //   338: ldc_w           "Could not create zip file "
        //   341: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   344: aload           9
        //   346: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   349: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: ldc_w           " for secondary dex ("
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: iload_3        
        //   359: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   362: ldc             ")"
        //   364: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   367: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   370: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   373: athrow         
        //   374: astore_0       
        //   375: aload           8
        //   377: invokevirtual   java/util/zip/ZipFile.close:()V
        //   380: aload_0        
        //   381: athrow         
        //   382: iload_3        
        //   383: iconst_1       
        //   384: iadd           
        //   385: istore_3       
        //   386: aload           8
        //   388: new             Ljava/lang/StringBuilder;
        //   391: dup            
        //   392: invokespecial   java/lang/StringBuilder.<init>:()V
        //   395: ldc_w           "classes"
        //   398: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   401: iload_3        
        //   402: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   405: ldc_w           ".dex"
        //   408: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   411: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   414: invokevirtual   java/util/zip/ZipFile.getEntry:(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
        //   417: astore_0       
        //   418: goto            84
        //   421: aload           8
        //   423: invokevirtual   java/util/zip/ZipFile.close:()V
        //   426: aload           6
        //   428: areturn        
        //   429: astore_0       
        //   430: ldc             "MultiDex"
        //   432: ldc             "Failed to close resource"
        //   434: aload_0        
        //   435: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   438: pop            
        //   439: aload           6
        //   441: areturn        
        //   442: astore_1       
        //   443: ldc             "MultiDex"
        //   445: ldc             "Failed to close resource"
        //   447: aload_1        
        //   448: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   451: pop            
        //   452: goto            380
        //   455: goto            164
        //   458: ldc_w           "failed"
        //   461: astore          5
        //   463: goto            220
        //    Signature:
        //  (Ljava/io/File;Ljava/io/File;)Ljava/util/List<Ljava/io/File;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  50     82     374    382    Any
        //  88     159    374    382    Any
        //  178    210    374    382    Any
        //  220    264    374    382    Any
        //  269    319    374    382    Any
        //  327    374    374    382    Any
        //  375    380    442    455    Ljava/io/IOException;
        //  386    418    374    382    Any
        //  421    426    429    442    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0380:
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
    
    private static void prepareDexDir(File file, final String s) {
        mkdirChecked(file.getParentFile());
        mkdirChecked(file);
        final File[] listFiles = file.listFiles(new MultiDexExtractor$1(s));
        if (listFiles == null) {
            Log.w("MultiDex", "Failed to list secondary dex dir content (" + file.getPath() + ").");
        }
        else {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                file = listFiles[i];
                Log.i("MultiDex", "Trying to delete old file " + file.getPath() + " of size " + file.length());
                if (!file.delete()) {
                    Log.w("MultiDex", "Failed to delete old file " + file.getPath());
                }
                else {
                    Log.i("MultiDex", "Deleted old file " + file.getPath());
                }
            }
        }
    }
    
    private static void putStoredApkInfo(final Context context, final long n, final long n2, final int n3) {
        final SharedPreferences$Editor edit = getMultiDexPreferences(context).edit();
        edit.putLong("timestamp", n);
        edit.putLong("crc", n2);
        edit.putInt("dex.number", n3);
        apply(edit);
    }
    
    static boolean verifyZipFile(final File file) {
        try {
            final ZipFile zipFile = new ZipFile(file);
            try {
                zipFile.close();
                return true;
            }
            catch (IOException ex3) {
                Log.w("MultiDex", "Failed to close zip file: " + file.getAbsolutePath());
            }
            return false;
        }
        catch (ZipException ex) {
            Log.w("MultiDex", "File " + file.getAbsolutePath() + " is not a valid zip file.", (Throwable)ex);
            return false;
        }
        catch (IOException ex2) {
            Log.w("MultiDex", "Got an IOException trying to open zip file: " + file.getAbsolutePath(), (Throwable)ex2);
            return false;
        }
    }
}
