// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.net.URLConnection;
import java.io.FileWriter;
import java.io.ByteArrayOutputStream;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;
import android.os.Environment;
import java.util.LinkedList;
import java.io.FileOutputStream;
import java.net.URL;
import java.io.File;
import android.content.Context;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.Closeable;

public final class FileUtils
{
    public static final int BYTES_PER_KB = 1024;
    public static final int BYTES_PER_MB = 1048576;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final int EOF = -1;
    public static final String FILE_COLON_TWO_SLASH = "file://";
    private static final String TAG = "FileUtils";
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static long copy(final InputStream inputStream, final OutputStream outputStream) {
        long n = 0L;
        final byte[] array = new byte[4096];
        while (true) {
            final int read = inputStream.read(array);
            if (-1 == read) {
                break;
            }
            outputStream.write(array, 0, read);
            n += read;
        }
        return n;
    }
    
    public static boolean copyFileFromAssetToFS(final Context p0, final String p1, final String p2, final boolean p3) {
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
        //     7: astore          5
        //     9: aload_0        
        //    10: aload_2        
        //    11: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    14: astore          8
        //    16: aload           8
        //    18: ifnull          258
        //    21: iconst_1       
        //    22: istore          4
        //    24: aload           8
        //    26: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //    29: iload           4
        //    31: ifeq            59
        //    34: iload_3        
        //    35: ifne            59
        //    38: iconst_0       
        //    39: ireturn        
        //    40: astore          8
        //    42: aconst_null    
        //    43: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //    46: iconst_0       
        //    47: istore          4
        //    49: goto            29
        //    52: astore_0       
        //    53: aconst_null    
        //    54: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //    57: aload_0        
        //    58: athrow         
        //    59: iload           4
        //    61: ifeq            84
        //    64: iload_3        
        //    65: ifeq            84
        //    68: aload_0        
        //    69: aload_2        
        //    70: invokevirtual   android/content/Context.deleteFile:(Ljava/lang/String;)Z
        //    73: ifne            84
        //    76: ldc             "FileUtils"
        //    78: ldc             "Failed to delete file"
        //    80: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    83: pop            
        //    84: aload_0        
        //    85: aload_2        
        //    86: iconst_0       
        //    87: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //    90: astore_2       
        //    91: aload           7
        //    93: astore          5
        //    95: aload_0        
        //    96: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    99: aload_1        
        //   100: invokevirtual   android/content/res/AssetManager.open:(Ljava/lang/String;)Ljava/io/InputStream;
        //   103: astore_0       
        //   104: aload_0        
        //   105: ifnonnull       129
        //   108: aload_0        
        //   109: astore          5
        //   111: ldc             "FileUtils"
        //   113: ldc             "IS is null"
        //   115: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   118: pop            
        //   119: aload_2        
        //   120: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   123: aload_0        
        //   124: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   127: iconst_0       
        //   128: ireturn        
        //   129: aload_0        
        //   130: astore          5
        //   132: sipush          1024
        //   135: newarray        B
        //   137: astore_1       
        //   138: aload_0        
        //   139: astore          5
        //   141: aload_0        
        //   142: aload_1        
        //   143: invokevirtual   java/io/InputStream.read:([B)I
        //   146: istore          4
        //   148: iload           4
        //   150: iconst_m1      
        //   151: if_icmpeq       194
        //   154: aload_0        
        //   155: astore          5
        //   157: aload_2        
        //   158: aload_1        
        //   159: iconst_0       
        //   160: iload           4
        //   162: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //   165: goto            138
        //   168: astore          5
        //   170: aload_2        
        //   171: astore_1       
        //   172: aload           5
        //   174: astore_2       
        //   175: ldc             "FileUtils"
        //   177: ldc             "Failed to extract CA"
        //   179: aload_2        
        //   180: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   183: pop            
        //   184: aload_1        
        //   185: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   188: aload_0        
        //   189: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   192: iconst_0       
        //   193: ireturn        
        //   194: aload_2        
        //   195: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   198: aload_0        
        //   199: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   202: iconst_1       
        //   203: ireturn        
        //   204: astore_0       
        //   205: aconst_null    
        //   206: astore_2       
        //   207: aload           6
        //   209: astore          5
        //   211: aload_2        
        //   212: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   215: aload           5
        //   217: invokestatic    com/netflix/mediaclient/util/IoUtil.safeClose:(Ljava/io/Closeable;)V
        //   220: aload_0        
        //   221: athrow         
        //   222: astore_0       
        //   223: goto            211
        //   226: astore_2       
        //   227: aload_0        
        //   228: astore          5
        //   230: aload_2        
        //   231: astore_0       
        //   232: aload_1        
        //   233: astore_2       
        //   234: goto            211
        //   237: astore_2       
        //   238: aconst_null    
        //   239: astore_0       
        //   240: aload           5
        //   242: astore_1       
        //   243: goto            175
        //   246: astore          5
        //   248: aconst_null    
        //   249: astore_0       
        //   250: aload_2        
        //   251: astore_1       
        //   252: aload           5
        //   254: astore_2       
        //   255: goto            175
        //   258: iconst_0       
        //   259: istore          4
        //   261: goto            24
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  9      16     40     52     Ljava/io/FileNotFoundException;
        //  9      16     52     59     Any
        //  84     91     237    246    Ljava/lang/Exception;
        //  84     91     204    211    Any
        //  95     104    246    258    Ljava/lang/Exception;
        //  95     104    222    226    Any
        //  111    119    168    175    Ljava/lang/Exception;
        //  111    119    222    226    Any
        //  132    138    168    175    Ljava/lang/Exception;
        //  132    138    222    226    Any
        //  141    148    168    175    Ljava/lang/Exception;
        //  141    148    222    226    Any
        //  157    165    168    175    Ljava/lang/Exception;
        //  157    165    222    226    Any
        //  175    184    226    237    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0129:
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
    
    public static boolean createDirectoryIfRequired(final String s) {
        final File file = new File(s);
        return file.isDirectory() || file.mkdirs();
    }
    
    public static boolean deleteRecursive(final File file) {
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    deleteRecursive(listFiles[i]);
                }
            }
        }
        return file.delete();
    }
    
    public static String download(final Context context, String openConnection) {
        final Closeable closeable = null;
        final String substring = openConnection.substring(openConnection.lastIndexOf(47) + 1);
        final FileOutputStream outputStream = getOutputStream(context, substring, true);
        Object inputStream = closeable;
        try {
            openConnection = (String)new URL(openConnection).openConnection();
            inputStream = closeable;
            ((URLConnection)openConnection).setConnectTimeout(5000);
            inputStream = closeable;
            ((URLConnection)openConnection).setReadTimeout(5000);
            inputStream = closeable;
            ((URLConnection)openConnection).connect();
            inputStream = closeable;
            openConnection = (String)(inputStream = ((URLConnection)openConnection).getInputStream());
            final byte[] array = new byte[512];
            while (true) {
                inputStream = openConnection;
                final int read = ((InputStream)openConnection).read(array);
                if (read == -1) {
                    break;
                }
                inputStream = openConnection;
                outputStream.write(array, 0, read);
            }
        }
        finally {
            IoUtil.safeClose(outputStream);
            IoUtil.safeClose((Closeable)inputStream);
        }
        outputStream.flush();
        IoUtil.safeClose(outputStream);
        IoUtil.safeClose((Closeable)openConnection);
        final Context context2;
        return "file://" + context2.getFilesDir().getAbsolutePath() + "/" + substring;
    }
    
    public static String extractFileName(final String s) {
        if (StringUtils.isEmpty(s)) {
            return "";
        }
        return s.substring("file://".length());
    }
    
    public static long getDirectorySizeInBytes(File file) {
        long n;
        if (file == null || !file.exists()) {
            n = 0L;
        }
        else {
            if (!file.isDirectory()) {
                return file.length();
            }
            final LinkedList<File> list = new LinkedList<File>();
            list.add(file);
            long n2 = 0L;
            while (true) {
                n = n2;
                if (list.isEmpty()) {
                    break;
                }
                file = (File)list.remove();
                if (!file.exists()) {
                    continue;
                }
                final File[] listFiles = file.listFiles();
                if (listFiles == null || listFiles.length == 0) {
                    continue;
                }
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    final File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        list.add(file2);
                    }
                    else {
                        n2 += file2.length();
                    }
                }
            }
        }
        return n;
    }
    
    @SuppressLint({ "WorldReadableFiles" })
    public static FileOutputStream getOutputStream(final Context context, final String s, final boolean b) {
        if (b) {
            return context.openFileOutput(s, 0);
        }
        final File file = new File(Environment.getExternalStorageDirectory(), s);
        Log.d("FileUtils", "File " + file.getAbsolutePath());
        if (!file.exists()) {
            file.createNewFile();
        }
        return new FileOutputStream(file);
    }
    
    public static long getUsableSpace(final File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            Log.e("FileUtils", "Not directory or does not exists " + file.exists());
            return 0L;
        }
        return file.getUsableSpace();
    }
    
    public static boolean moveFile(final String s, final String s2) {
        return new File(s).renameTo(new File(s2));
    }
    
    public static FileInputStream openInputStream(final File file) {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        }
        if (!file.canRead()) {
            throw new IOException("File '" + file + "' cannot be read");
        }
        return new FileInputStream(file);
    }
    
    public static byte[] readBytesFromFile(final File p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_2        
        //     1: ifge            15
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc_w           "We can not read less than 1 byte!"
        //    11: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    14: athrow         
        //    15: iload_2        
        //    16: newarray        B
        //    18: astore          4
        //    20: new             Ljava/io/RandomAccessFile;
        //    23: dup            
        //    24: aload_0        
        //    25: ldc_w           "r"
        //    28: invokespecial   java/io/RandomAccessFile.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    31: astore_3       
        //    32: aload_3        
        //    33: iload_1        
        //    34: invokevirtual   java/io/RandomAccessFile.skipBytes:(I)I
        //    37: pop            
        //    38: aload_3        
        //    39: aload           4
        //    41: invokevirtual   java/io/RandomAccessFile.readFully:([B)V
        //    44: aload_3        
        //    45: ifnull          52
        //    48: aload_3        
        //    49: invokevirtual   java/io/RandomAccessFile.close:()V
        //    52: aload           4
        //    54: areturn        
        //    55: astore_0       
        //    56: aconst_null    
        //    57: astore_3       
        //    58: aload_3        
        //    59: ifnull          66
        //    62: aload_3        
        //    63: invokevirtual   java/io/RandomAccessFile.close:()V
        //    66: aload_0        
        //    67: athrow         
        //    68: astore_0       
        //    69: aload           4
        //    71: areturn        
        //    72: astore_3       
        //    73: goto            66
        //    76: astore_0       
        //    77: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  20     32     55     58     Any
        //  32     44     76     80     Any
        //  48     52     68     72     Ljava/lang/Throwable;
        //  62     66     72     76     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
    
    public static String readFile(final String s, final Charset charset) {
        final File file = new File(s);
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[(int)file.length()];
        fileInputStream.read(array);
        fileInputStream.close();
        return new String(array, charset);
    }
    
    public static byte[] readFile(final String s) {
        final File file = new File(s);
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[(int)file.length()];
        fileInputStream.read(array);
        fileInputStream.close();
        return array;
    }
    
    public static byte[] readFileToByteArray(final File file) {
        Closeable openInputStream = null;
        try {
            return toByteArray((InputStream)(openInputStream = openInputStream(file)));
        }
        finally {
            closeQuietly(openInputStream);
        }
    }
    
    public static String readFileWithUTF8Encoding(final String s) {
        return readFile(s, Charset.forName("UTF-8"));
    }
    
    public static String readRawString(final Context context, final int n) {
        final StringBuilder sb = new StringBuilder();
        final Scanner scanner = new Scanner(context.getResources().openRawResource(n));
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine() + "\n");
        }
        return sb.toString();
    }
    
    public static void removeFilesFromFS(final Context context, final String[] array) {
        for (int i = 0; i < array.length; ++i) {
            final File dir = context.getDir(array[i], 0);
            if (dir.exists()) {
                if (Log.isLoggable()) {
                    Log.d("FileUtils", "File for removal found: " + dir.getAbsolutePath());
                }
                final boolean deleteFile = context.deleteFile(array[i]);
                if (Log.isLoggable()) {
                    Log.d("FileUtils", "File " + array[i] + " is deleted " + deleteFile);
                }
            }
        }
    }
    
    public static byte[] toByteArray(final InputStream inputStream) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        copy(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    
    public static boolean writeBytesToFile(final String p0, final byte[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          4
        //     5: new             Ljava/io/FileOutputStream;
        //     8: dup            
        //     9: aload_0        
        //    10: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    13: astore_2       
        //    14: aload_2        
        //    15: astore_3       
        //    16: aload_2        
        //    17: aload_1        
        //    18: invokevirtual   java/io/FileOutputStream.write:([B)V
        //    21: aload_2        
        //    22: ifnull          196
        //    25: aload_2        
        //    26: invokevirtual   java/io/FileOutputStream.close:()V
        //    29: iconst_1       
        //    30: ireturn        
        //    31: astore_0       
        //    32: ldc             "FileUtils"
        //    34: ldc_w           "persistManifest close IO Exception "
        //    37: aload_0        
        //    38: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    41: pop            
        //    42: iconst_0       
        //    43: ireturn        
        //    44: astore_1       
        //    45: aconst_null    
        //    46: astore_2       
        //    47: aload_2        
        //    48: astore_3       
        //    49: ldc             "FileUtils"
        //    51: new             Ljava/lang/StringBuilder;
        //    54: dup            
        //    55: invokespecial   java/lang/StringBuilder.<init>:()V
        //    58: ldc_w           "writeBytesToFile file not found "
        //    61: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    64: aload_0        
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    71: aload_1        
        //    72: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    75: pop            
        //    76: aload_2        
        //    77: ifnull          196
        //    80: aload_2        
        //    81: invokevirtual   java/io/FileOutputStream.close:()V
        //    84: iconst_1       
        //    85: ireturn        
        //    86: astore_0       
        //    87: ldc             "FileUtils"
        //    89: ldc_w           "persistManifest close IO Exception "
        //    92: aload_0        
        //    93: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    96: pop            
        //    97: iconst_0       
        //    98: ireturn        
        //    99: astore_2       
        //   100: aload           4
        //   102: astore_1       
        //   103: aload_1        
        //   104: astore_3       
        //   105: ldc             "FileUtils"
        //   107: new             Ljava/lang/StringBuilder;
        //   110: dup            
        //   111: invokespecial   java/lang/StringBuilder.<init>:()V
        //   114: ldc_w           "writeBytesToFile IOException "
        //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   120: aload_0        
        //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   124: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   127: aload_2        
        //   128: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   131: pop            
        //   132: aload_1        
        //   133: ifnull          196
        //   136: aload_1        
        //   137: invokevirtual   java/io/FileOutputStream.close:()V
        //   140: iconst_1       
        //   141: ireturn        
        //   142: astore_0       
        //   143: ldc             "FileUtils"
        //   145: ldc_w           "persistManifest close IO Exception "
        //   148: aload_0        
        //   149: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   152: pop            
        //   153: iconst_0       
        //   154: ireturn        
        //   155: astore_0       
        //   156: aload_3        
        //   157: ifnull          164
        //   160: aload_3        
        //   161: invokevirtual   java/io/FileOutputStream.close:()V
        //   164: aload_0        
        //   165: athrow         
        //   166: astore_1       
        //   167: ldc             "FileUtils"
        //   169: ldc_w           "persistManifest close IO Exception "
        //   172: aload_1        
        //   173: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   176: pop            
        //   177: goto            164
        //   180: astore_0       
        //   181: goto            156
        //   184: astore_3       
        //   185: aload_2        
        //   186: astore_1       
        //   187: aload_3        
        //   188: astore_2       
        //   189: goto            103
        //   192: astore_1       
        //   193: goto            47
        //   196: iconst_0       
        //   197: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  5      14     44     47     Ljava/io/FileNotFoundException;
        //  5      14     99     103    Ljava/io/IOException;
        //  5      14     155    156    Any
        //  16     21     192    196    Ljava/io/FileNotFoundException;
        //  16     21     184    192    Ljava/io/IOException;
        //  16     21     180    184    Any
        //  25     29     31     44     Ljava/io/IOException;
        //  49     76     180    184    Any
        //  80     84     86     99     Ljava/io/IOException;
        //  105    132    155    156    Any
        //  136    140    142    155    Ljava/io/IOException;
        //  160    164    166    180    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0103:
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
    
    public static boolean writeStringToFile(final String s, final String s2, final String s3) {
        try {
            final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + s3);
            file.getParentFile().mkdirs();
            file.createNewFile();
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s2);
            fileWriter.close();
            Log.v(s, "*****************************************************************");
            Log.v(s, "Wrote string to file: " + file);
            Log.v(s, "Get file with command: adb pull /sdcard/" + s3);
            Log.v(s, "*****************************************************************");
            return true;
        }
        catch (IOException ex) {
            Log.handleException(s, ex);
            return false;
        }
    }
}
