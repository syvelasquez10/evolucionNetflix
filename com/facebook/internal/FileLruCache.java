// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;
import org.json.JSONObject;
import java.io.IOException;
import com.facebook.LoggingBehavior;
import java.util.Date;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import com.facebook.Settings;
import android.content.Context;
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;

public final class FileLruCache
{
    static final String TAG;
    private static final AtomicLong bufferIndex;
    private final File directory;
    private boolean isTrimInProgress;
    private boolean isTrimPending;
    private AtomicLong lastClearCacheTime;
    private final FileLruCache$Limits limits;
    private final Object lock;
    private final String tag;
    
    static {
        TAG = FileLruCache.class.getSimpleName();
        bufferIndex = new AtomicLong();
    }
    
    public FileLruCache(final Context context, final String tag, final FileLruCache$Limits limits) {
        this.lastClearCacheTime = new AtomicLong(0L);
        this.tag = tag;
        this.limits = limits;
        this.directory = new File(context.getCacheDir(), tag);
        this.lock = new Object();
        if (this.directory.mkdirs() || this.directory.isDirectory()) {
            FileLruCache$BufferFile.deleteAll(this.directory);
        }
    }
    
    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                Settings.getExecutor().execute(new FileLruCache$3(this));
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
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //     4: astore          11
        //     6: aload           11
        //     8: monitorenter   
        //     9: aload_0        
        //    10: iconst_0       
        //    11: putfield        com/facebook/internal/FileLruCache.isTrimPending:Z
        //    14: aload_0        
        //    15: iconst_1       
        //    16: putfield        com/facebook/internal/FileLruCache.isTrimInProgress:Z
        //    19: aload           11
        //    21: monitorexit    
        //    22: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //    25: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //    28: ldc             "trim started"
        //    30: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //    33: new             Ljava/util/PriorityQueue;
        //    36: dup            
        //    37: invokespecial   java/util/PriorityQueue.<init>:()V
        //    40: astore          11
        //    42: lconst_0       
        //    43: lstore_3       
        //    44: lconst_0       
        //    45: lstore          7
        //    47: aload_0        
        //    48: getfield        com/facebook/internal/FileLruCache.directory:Ljava/io/File;
        //    51: invokestatic    com/facebook/internal/FileLruCache$BufferFile.excludeBufferFiles:()Ljava/io/FilenameFilter;
        //    54: invokevirtual   java/io/File.listFiles:(Ljava/io/FilenameFilter;)[Ljava/io/File;
        //    57: astore          12
        //    59: lload           7
        //    61: lstore          5
        //    63: lload_3        
        //    64: lstore          9
        //    66: aload           12
        //    68: ifnull          363
        //    71: aload           12
        //    73: arraylength    
        //    74: istore_2       
        //    75: iconst_0       
        //    76: istore_1       
        //    77: lload           7
        //    79: lstore          5
        //    81: lload_3        
        //    82: lstore          9
        //    84: iload_1        
        //    85: iload_2        
        //    86: if_icmpge       363
        //    89: aload           12
        //    91: iload_1        
        //    92: aaload         
        //    93: astore          13
        //    95: new             Lcom/facebook/internal/FileLruCache$ModifiedFile;
        //    98: dup            
        //    99: aload           13
        //   101: invokespecial   com/facebook/internal/FileLruCache$ModifiedFile.<init>:(Ljava/io/File;)V
        //   104: astore          14
        //   106: aload           11
        //   108: aload           14
        //   110: invokevirtual   java/util/PriorityQueue.add:(Ljava/lang/Object;)Z
        //   113: pop            
        //   114: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   117: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   120: new             Ljava/lang/StringBuilder;
        //   123: dup            
        //   124: invokespecial   java/lang/StringBuilder.<init>:()V
        //   127: ldc             "  trim considering time="
        //   129: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   132: aload           14
        //   134: invokevirtual   com/facebook/internal/FileLruCache$ModifiedFile.getModified:()J
        //   137: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   143: ldc             " name="
        //   145: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   148: aload           14
        //   150: invokevirtual   com/facebook/internal/FileLruCache$ModifiedFile.getFile:()Ljava/io/File;
        //   153: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   162: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   165: aload           13
        //   167: invokevirtual   java/io/File.length:()J
        //   170: lstore          5
        //   172: iload_1        
        //   173: iconst_1       
        //   174: iadd           
        //   175: istore_1       
        //   176: lconst_1       
        //   177: lload           7
        //   179: ladd           
        //   180: lstore          7
        //   182: lload           5
        //   184: lload_3        
        //   185: ladd           
        //   186: lstore_3       
        //   187: goto            77
        //   190: astore          12
        //   192: aload           11
        //   194: monitorexit    
        //   195: aload           12
        //   197: athrow         
        //   198: lload           9
        //   200: aload_0        
        //   201: getfield        com/facebook/internal/FileLruCache.limits:Lcom/facebook/internal/FileLruCache$Limits;
        //   204: invokevirtual   com/facebook/internal/FileLruCache$Limits.getByteCount:()I
        //   207: i2l            
        //   208: lcmp           
        //   209: ifgt            225
        //   212: lload_3        
        //   213: aload_0        
        //   214: getfield        com/facebook/internal/FileLruCache.limits:Lcom/facebook/internal/FileLruCache$Limits;
        //   217: invokevirtual   com/facebook/internal/FileLruCache$Limits.getFileCount:()I
        //   220: i2l            
        //   221: lcmp           
        //   222: ifle            293
        //   225: aload           11
        //   227: invokevirtual   java/util/PriorityQueue.remove:()Ljava/lang/Object;
        //   230: checkcast       Lcom/facebook/internal/FileLruCache$ModifiedFile;
        //   233: invokevirtual   com/facebook/internal/FileLruCache$ModifiedFile.getFile:()Ljava/io/File;
        //   236: astore          12
        //   238: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   241: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   244: new             Ljava/lang/StringBuilder;
        //   247: dup            
        //   248: invokespecial   java/lang/StringBuilder.<init>:()V
        //   251: ldc             "  trim removing "
        //   253: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   256: aload           12
        //   258: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   261: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   264: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   267: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;Ljava/lang/String;Ljava/lang/String;)V
        //   270: lload           9
        //   272: aload           12
        //   274: invokevirtual   java/io/File.length:()J
        //   277: lsub           
        //   278: lstore          9
        //   280: aload           12
        //   282: invokevirtual   java/io/File.delete:()Z
        //   285: pop            
        //   286: lload_3        
        //   287: lconst_1       
        //   288: lsub           
        //   289: lstore_3       
        //   290: goto            198
        //   293: aload_0        
        //   294: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   297: astore          11
        //   299: aload           11
        //   301: monitorenter   
        //   302: aload_0        
        //   303: iconst_0       
        //   304: putfield        com/facebook/internal/FileLruCache.isTrimInProgress:Z
        //   307: aload_0        
        //   308: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   311: invokevirtual   java/lang/Object.notifyAll:()V
        //   314: aload           11
        //   316: monitorexit    
        //   317: return         
        //   318: astore          12
        //   320: aload           11
        //   322: monitorexit    
        //   323: aload           12
        //   325: athrow         
        //   326: astore          12
        //   328: aload_0        
        //   329: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   332: astore          11
        //   334: aload           11
        //   336: monitorenter   
        //   337: aload_0        
        //   338: iconst_0       
        //   339: putfield        com/facebook/internal/FileLruCache.isTrimInProgress:Z
        //   342: aload_0        
        //   343: getfield        com/facebook/internal/FileLruCache.lock:Ljava/lang/Object;
        //   346: invokevirtual   java/lang/Object.notifyAll:()V
        //   349: aload           11
        //   351: monitorexit    
        //   352: aload           12
        //   354: athrow         
        //   355: astore          12
        //   357: aload           11
        //   359: monitorexit    
        //   360: aload           12
        //   362: athrow         
        //   363: lload           5
        //   365: lstore_3       
        //   366: goto            198
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  9      22     190    198    Any
        //  22     42     326    363    Any
        //  47     59     326    363    Any
        //  71     75     326    363    Any
        //  95     172    326    363    Any
        //  192    195    190    198    Any
        //  198    225    326    363    Any
        //  225    286    326    363    Any
        //  302    317    318    326    Any
        //  320    323    318    326    Any
        //  337    352    355    363    Any
        //  357    360    355    363    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0077:
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
    
    public void clearCache() {
        final File[] listFiles = this.directory.listFiles(FileLruCache$BufferFile.excludeBufferFiles());
        this.lastClearCacheTime.set(System.currentTimeMillis());
        if (listFiles != null) {
            Settings.getExecutor().execute(new FileLruCache$2(this, listFiles));
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
        //    76: invokestatic    java/lang/System.currentTimeMillis:()J
        //    79: aload_3        
        //    80: aload_1        
        //    81: invokespecial   com/facebook/internal/FileLruCache$1.<init>:(Lcom/facebook/internal/FileLruCache;JLjava/io/File;Ljava/lang/String;)V
        //    84: invokespecial   com/facebook/internal/FileLruCache$CloseCallbackOutputStream.<init>:(Ljava/io/OutputStream;Lcom/facebook/internal/FileLruCache$StreamCloseCallback;)V
        //    87: sipush          8192
        //    90: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    93: astore_3       
        //    94: new             Lorg/json/JSONObject;
        //    97: dup            
        //    98: invokespecial   org/json/JSONObject.<init>:()V
        //   101: astore          4
        //   103: aload           4
        //   105: ldc             "key"
        //   107: aload_1        
        //   108: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   111: pop            
        //   112: aload_2        
        //   113: invokestatic    com/facebook/internal/Utility.isNullOrEmpty:(Ljava/lang/String;)Z
        //   116: ifne            129
        //   119: aload           4
        //   121: ldc_w           "tag"
        //   124: aload_2        
        //   125: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   128: pop            
        //   129: aload_3        
        //   130: aload           4
        //   132: invokestatic    com/facebook/internal/FileLruCache$StreamHeader.writeHeader:(Ljava/io/OutputStream;Lorg/json/JSONObject;)V
        //   135: aload_3        
        //   136: areturn        
        //   137: astore_1       
        //   138: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   141: iconst_5       
        //   142: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   145: new             Ljava/lang/StringBuilder;
        //   148: dup            
        //   149: invokespecial   java/lang/StringBuilder.<init>:()V
        //   152: ldc_w           "Error creating buffer output stream: "
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: aload_1        
        //   159: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   162: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   165: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   168: new             Ljava/io/IOException;
        //   171: dup            
        //   172: aload_1        
        //   173: invokevirtual   java/io/FileNotFoundException.getMessage:()Ljava/lang/String;
        //   176: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   179: athrow         
        //   180: astore_1       
        //   181: getstatic       com/facebook/LoggingBehavior.CACHE:Lcom/facebook/LoggingBehavior;
        //   184: iconst_5       
        //   185: getstatic       com/facebook/internal/FileLruCache.TAG:Ljava/lang/String;
        //   188: new             Ljava/lang/StringBuilder;
        //   191: dup            
        //   192: invokespecial   java/lang/StringBuilder.<init>:()V
        //   195: ldc_w           "Error creating JSON header for cache file: "
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload_1        
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   205: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   208: invokestatic    com/facebook/internal/Logger.log:(Lcom/facebook/LoggingBehavior;ILjava/lang/String;Ljava/lang/String;)V
        //   211: new             Ljava/io/IOException;
        //   214: dup            
        //   215: aload_1        
        //   216: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   219: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   222: athrow         
        //   223: astore_1       
        //   224: aload_3        
        //   225: invokevirtual   java/io/BufferedOutputStream.close:()V
        //   228: aload_1        
        //   229: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  51     61     137    180    Ljava/io/FileNotFoundException;
        //  94     129    180    223    Lorg/json/JSONException;
        //  94     129    223    230    Any
        //  129    135    180    223    Lorg/json/JSONException;
        //  129    135    223    230    Any
        //  181    223    223    230    Any
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
    
    @Override
    public String toString() {
        return "{FileLruCache: tag:" + this.tag + " file:" + this.directory.getName() + "}";
    }
}
