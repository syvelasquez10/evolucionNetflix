// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.net.URLConnection;
import java.io.FileWriter;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.Log;
import android.os.Environment;
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
    
    public static boolean deleteRecursive(final File file) {
        if (file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                deleteRecursive(listFiles[i]);
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
    
    public static String readFile(final String s, final Charset charset) {
        final File file = new File(s);
        final FileInputStream fileInputStream = new FileInputStream(file);
        final byte[] array = new byte[(int)file.length()];
        fileInputStream.read(array);
        fileInputStream.close();
        return new String(array, charset);
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
    
    public static void writeStringToFile(final String s, final String s2, final String s3) {
        try {
            final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + s3);
            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(s2);
            fileWriter.close();
            Log.v(s, "*****************************************************************");
            Log.v(s, "Wrote string to file: " + file);
            Log.v(s, "Get file with command: adb pull /sdcard/" + s3);
            Log.v(s, "*****************************************************************");
        }
        catch (IOException ex) {
            Log.handleException(s, ex);
        }
    }
}
