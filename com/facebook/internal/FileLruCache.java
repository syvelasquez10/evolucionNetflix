// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.AbstractQueue;
import java.util.PriorityQueue;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import android.content.Context;
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public final class FileLruCache
{
    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG;
    private static final AtomicLong bufferIndex;
    private final File directory;
    private boolean isTrimPending;
    private final FileLruCache$Limits limits;
    private final Object lock;
    private final String tag;
    
    static {
        TAG = FileLruCache.class.getSimpleName();
        bufferIndex = new AtomicLong();
    }
    
    public FileLruCache(final Context context, final String tag, final FileLruCache$Limits limits) {
        this.tag = tag;
        this.limits = limits;
        this.directory = new File(context.getCacheDir(), tag);
        this.lock = new Object();
        this.directory.mkdirs();
        FileLruCache$BufferFile.deleteAll(this.directory);
    }
    
    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                Settings.getExecutor().execute(new FileLruCache$2(this));
            }
        }
    }
    
    private void renameToTargetAndTrim(final String s, final File file) {
        if (!file.renameTo(new File(this.directory, Utility.md5hash(s)))) {
            file.delete();
        }
        this.postTrim();
    }
    
    private void trim() {
    Label_0149_Outer:
        while (true) {
            while (true) {
                Label_0314: {
                    try {
                        Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "trim started");
                        final PriorityQueue<FileLruCache$ModifiedFile> priorityQueue = new PriorityQueue<FileLruCache$ModifiedFile>();
                        final File[] listFiles = this.directory.listFiles(FileLruCache$BufferFile.excludeBufferFiles());
                        final int length = listFiles.length;
                        long n = 0L;
                        long n2 = 0L;
                        long length2;
                        for (int i = 0; i < length; ++i, ++n, n2 += length2) {
                            final File file = listFiles[i];
                            final FileLruCache$ModifiedFile fileLruCache$ModifiedFile = new FileLruCache$ModifiedFile(file);
                            priorityQueue.add(fileLruCache$ModifiedFile);
                            Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "  trim considering time=" + (Object)fileLruCache$ModifiedFile.getModified() + " name=" + fileLruCache$ModifiedFile.getFile().getName());
                            length2 = file.length();
                        }
                        break Label_0314;
                        Label_0244: {
                            synchronized (this.lock) {
                                this.isTrimPending = false;
                                this.lock.notifyAll();
                                return;
                            }
                        }
                        while (true) {
                            final AbstractQueue<FileLruCache$ModifiedFile> abstractQueue;
                            final File file2 = abstractQueue.remove().getFile();
                            Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "  trim removing " + file2.getName());
                            n2 -= file2.length();
                            file2.delete();
                            --n;
                            continue Label_0149_Outer;
                        }
                    }
                    // iftrue(Label_0244:, n2 <= (long)this.limits.getByteCount() && n <= (long)this.limits.getFileCount())
                    finally {
                        synchronized (this.lock) {
                            this.isTrimPending = false;
                            this.lock.notifyAll();
                        }
                        // monitorexit(this.lock)
                    }
                }
                continue;
            }
        }
    }
    
    public void clearForTest() {
        final File[] listFiles = this.directory.listFiles();
        for (int length = listFiles.length, i = 0; i < length; ++i) {
            listFiles[i].delete();
        }
    }
    
    public InputStream get(final String s) {
        return this.get(s, null);
    }
    
    public InputStream get(String optString, final String s) {
        final File file = new File(this.directory, Utility.md5hash(optString));
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
            try {
                final JSONObject header = FileLruCache$StreamHeader.readHeader(bufferedInputStream);
                if (header == null) {
                    return null;
                }
                final String optString2 = header.optString("key");
                if (optString2 == null || !optString2.equals(optString)) {
                    return null;
                }
                optString = header.optString("tag", (String)null);
                if ((s == null && optString != null) || (s != null && !s.equals(optString))) {
                    return null;
                }
                final long time = new Date().getTime();
                Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "Setting lastModified to " + (Object)time + " for " + file.getName());
                file.setLastModified(time);
                return bufferedInputStream;
            }
            finally {
                bufferedInputStream.close();
            }
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    public InputStream interceptAndPut(final String s, final InputStream inputStream) {
        return new FileLruCache$CopyingInputStream(inputStream, this.openPutStream(s));
    }
    
    OutputStream openPutStream(final String s) {
        return this.openPutStream(s, null);
    }
    
    public OutputStream openPutStream(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/internal/FileLruCache.directory:Ljava/io/File;
        //     4: invokestatic    com/facebook/internal/FileLruCache$BufferFile.newFile:(Ljava/io/File;)Ljava/io/File;
        //     7: astore_3       
        //     8: aload_3        
        //     9: invokevirtual   java/io/File.delete:()Z
        //    12: pop            
        //    13: aload_3        
        //    14: invokevirtual   java/io/File.createNewFile:()Z
        //    17: ifne            51
        //    20: new             Ljava/io/IOException;
        //    23: dup            
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc_w           "Could not create file at "
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: aload_3        
        //    38: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    41: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    44: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    47: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    50: athrow         
        //    51: new             Ljava/io/FileOutputStream;
        //    54: dup            
        //    55: aload_3        
        //    56: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    59: astore          4
        //    61: new             Ljava/io/BufferedOutputStream;
        //    64: dup            
        //    65: new             Lcom/facebook/internal/FileLruCache$CloseCallbackOutputStream;
        //    68: dup            
        //    69: aload           4
        //    71: new             Lcom/facebook/internal/FileLruCache$1;
        //    74: dup            
        //    75: aload_0        
        //    76: aload_1        
        //    77: aload_3        
        //    78: invokespecial   com/facebook/internal/FileLruCache$1.<init>:(Lcom/facebook/internal/FileLruCache;Ljava/lang/String;Ljava/io/File;)V
        //    81: invokespecial   com/facebook/internal/FileLruCache$CloseCallbackOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
        //    84: sipush          8192
        //    87: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    90: astore_3       
        //    91: new             Lorg/json/JSONObject;
        //    94: dup            
        //    95: invokespecial   org/json/JSONObject.<init>:()V
        //    98: astore          4
        //   100: aload           4
        //   102: ldc             "key"
        //   104: aload_1        
        //   105: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   108: pop            
        //   109: aload_2        
        //   110: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   113: ifne            125
        //   116: aload           4
        //   118: ldc             "tag"
        //   120: aload_2        
        //   121: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   124: pop            
        //   125: aload_3        
        //   126: aload           4
        //   128: invokestatic    com/facebook/internal/FileLruCache$StreamHeader.writeHeader:(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
        //   131: aload_3        
        //   132: areturn        
        //   133: astore_1       
        //   134: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   137: iconst_5       
        //   138: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   141: new             Ljava/lang/StringBuilder;
        //   144: dup            
        //   145: invokespecial   java/lang/StringBuilder.<init>:()V
        //   148: ldc_w           "Error creating buffer output stream: "
        //   151: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   154: aload_1        
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   158: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   161: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   164: new             Ljava/io/IOException;
        //   167: dup            
        //   168: aload_1        
        //   169: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   172: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   175: athrow         
        //   176: astore_1       
        //   177: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   180: iconst_5       
        //   181: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   184: new             Ljava/lang/StringBuilder;
        //   187: dup            
        //   188: invokespecial   java/lang/StringBuilder.<init>:()V
        //   191: ldc_w           "Error creating JSON header for cache file: "
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: aload_1        
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   201: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   204: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   207: new             Ljava/io/IOException;
        //   210: dup            
        //   211: aload_1        
        //   212: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   215: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   218: athrow         
        //   219: astore_1       
        //   220: aload_3        
        //   221: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   224: aload_1        
        //   225: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  51     61     133    176    Ljava/io/FileNotFoundException;
        //  91     125    176    219    Lorg/json/JSONException;
        //  91     125    219    226    Any
        //  125    131    176    219    Lorg/json/JSONException;
        //  125    131    219    226    Any
        //  177    219    219    226    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0125:
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
    
    long sizeInBytesForTest() {
        Object o = this.lock;
        long n;
        synchronized (o) {
            while (this.isTrimPending) {
                try {
                    this.lock.wait();
                }
                catch (InterruptedException ex) {}
            }
            // monitorexit(o)
            o = this.directory.listFiles();
            n = 0L;
            for (int length = o.length, i = 0; i < length; ++i) {
                n += o[i].length();
            }
        }
        return n;
    }
    
    @Override
    public String toString() {
        return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
    }
}
