// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.data;

import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import android.os.SystemClock;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.io.File;
import java.util.Map;

public class FileSystemDataRepositoryImpl implements DataRepository
{
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final String TAG = "nf_log";
    private final Map<String, DataRepository$Entry> mEntries;
    private final int mMaxSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
    public FileSystemDataRepositoryImpl(final File file) {
        this(file, 5242880);
    }
    
    public FileSystemDataRepositoryImpl(final File mRootDirectory, final int mMaxSizeInBytes) {
        this.mEntries = new HashMap<String, DataRepository$Entry>();
        this.mTotalSize = 0L;
        this.mRootDirectory = mRootDirectory;
        this.mMaxSizeInBytes = mMaxSizeInBytes;
    }
    
    private File getFileForName(final String s) {
        return new File(this.mRootDirectory, s);
    }
    
    private void pruneIfNeeded(final int n) {
        if (this.mTotalSize + n >= this.mMaxSizeInBytes) {
            Log.d("nf_log", "Pruning oldest entries.");
            final long mTotalSize = this.mTotalSize;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final ArrayList<Object> list = new ArrayList<Object>(this.mEntries.values());
            Collections.sort(list, (Comparator<? super Object>)new FileSystemDataRepositoryImpl$1(this));
            final Iterator<T> iterator = list.iterator();
            int n2 = 0;
            while (true) {
                while (iterator.hasNext()) {
                    final DataRepository$Entry dataRepository$Entry = (DataRepository$Entry)iterator.next();
                    final File fileForName = this.getFileForName(dataRepository$Entry.getKey());
                    final long length = fileForName.length();
                    if (fileForName.delete()) {
                        this.mTotalSize -= length;
                    }
                    else {
                        Log.e("nf_log", "Could not delete entry " + fileForName.getName());
                    }
                    this.mEntries.remove(dataRepository$Entry.getKey());
                    ++n2;
                    if (this.mTotalSize + n < this.mMaxSizeInBytes) {
                        if (Log.isLoggable("nf_log", 3)) {
                            Log.d("nf_log", "Pruned " + n2 + " in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " [ms]. Still available [B]: " + (this.mTotalSize - mTotalSize));
                        }
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    private static byte[] streamToBytes(final InputStream inputStream, final int n) {
        final byte[] array = new byte[n];
        int i;
        int read;
        for (i = 0; i < n; i += read) {
            read = inputStream.read(array, i, n - i);
            if (read == -1) {
                break;
            }
        }
        if (i != n) {
            throw new IOException("Expected " + n + " bytes, read " + i + " bytes");
        }
        return array;
    }
    
    @Override
    public void clear() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mRootDirectory:Ljava/io/File;
        //     6: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //     9: astore          4
        //    11: aload           4
        //    13: ifnull          89
        //    16: aload           4
        //    18: arraylength    
        //    19: istore_2       
        //    20: iconst_0       
        //    21: istore_1       
        //    22: iload_1        
        //    23: iload_2        
        //    24: if_icmpge       89
        //    27: aload           4
        //    29: iload_1        
        //    30: aaload         
        //    31: astore          5
        //    33: aload           5
        //    35: invokevirtual   java/io/File.delete:()Z
        //    38: istore_3       
        //    39: ldc             "nf_log"
        //    41: iconst_3       
        //    42: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    45: ifeq            121
        //    48: ldc             "nf_log"
        //    50: new             Ljava/lang/StringBuilder;
        //    53: dup            
        //    54: invokespecial   java/lang/StringBuilder.<init>:()V
        //    57: ldc             "File found "
        //    59: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    62: aload           5
        //    64: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    67: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    70: ldc             " and deleted "
        //    72: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    75: iload_3        
        //    76: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //    79: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    82: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    85: pop            
        //    86: goto            121
        //    89: aload_0        
        //    90: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //    93: invokeinterface java/util/Map.clear:()V
        //    98: aload_0        
        //    99: lconst_0       
        //   100: putfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mTotalSize:J
        //   103: ldc             "nf_log"
        //   105: ldc             "Cache cleared."
        //   107: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   110: pop            
        //   111: aload_0        
        //   112: monitorexit    
        //   113: return         
        //   114: astore          4
        //   116: aload_0        
        //   117: monitorexit    
        //   118: aload           4
        //   120: athrow         
        //   121: iload_1        
        //   122: iconst_1       
        //   123: iadd           
        //   124: istore_1       
        //   125: goto            22
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     114    121    Any
        //  16     20     114    121    Any
        //  33     86     114    121    Any
        //  89     111    114    121    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    @Override
    public DataRepository$Entry[] getEntries() {
        synchronized (this) {
            return this.mEntries.values().toArray(new DataRepository$Entry[this.mEntries.size()]);
        }
    }
    
    @Override
    public void load(final String p0, final DataRepository$DataLoadedCallback p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //     6: aload_1        
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: checkcast       Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //    15: astore_3       
        //    16: ldc             "nf_log"
        //    18: iconst_3       
        //    19: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //    22: ifeq            59
        //    25: ldc             "nf_log"
        //    27: new             Ljava/lang/StringBuilder;
        //    30: dup            
        //    31: invokespecial   java/lang/StringBuilder.<init>:()V
        //    34: ldc             "Find entry for key "
        //    36: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    39: aload_1        
        //    40: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    43: ldc             ", with file name "
        //    45: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    48: aload_1        
        //    49: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    52: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    55: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    58: pop            
        //    59: aload_3        
        //    60: ifnonnull       87
        //    63: ldc             "nf_log"
        //    65: ldc             "Entry not found!"
        //    67: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    70: pop            
        //    71: aload_2        
        //    72: ifnull          84
        //    75: aload_2        
        //    76: aload_1        
        //    77: aconst_null    
        //    78: lconst_0       
        //    79: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //    84: aload_0        
        //    85: monitorexit    
        //    86: return         
        //    87: ldc             "nf_log"
        //    89: ldc             "Entry found!"
        //    91: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    94: pop            
        //    95: aload_0        
        //    96: aload_1        
        //    97: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.getFileForName:(Ljava/lang/String;)Ljava/io/File;
        //   100: astore          6
        //   102: new             Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;
        //   105: dup            
        //   106: new             Ljava/io/FileInputStream;
        //   109: dup            
        //   110: aload           6
        //   112: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   115: aconst_null    
        //   116: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$1;)V
        //   119: astore          4
        //   121: aload           4
        //   123: astore_3       
        //   124: aload           4
        //   126: aload           6
        //   128: invokevirtual   java/io/File.length:()J
        //   131: aload           4
        //   133: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.access$100:(Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;)I
        //   136: i2l            
        //   137: lsub           
        //   138: l2i            
        //   139: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.streamToBytes:(Ljava/io/InputStream;I)[B
        //   142: astore          5
        //   144: aload_2        
        //   145: ifnull          165
        //   148: aload           4
        //   150: astore_3       
        //   151: aload_2        
        //   152: aload_1        
        //   153: aload           5
        //   155: aload           6
        //   157: invokevirtual   java/io/File.lastModified:()J
        //   160: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   165: aload           4
        //   167: ifnull          84
        //   170: aload           4
        //   172: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   175: goto            84
        //   178: astore_1       
        //   179: goto            84
        //   182: astore          5
        //   184: aconst_null    
        //   185: astore          4
        //   187: aload           4
        //   189: astore_3       
        //   190: ldc             "nf_log"
        //   192: new             Ljava/lang/StringBuilder;
        //   195: dup            
        //   196: invokespecial   java/lang/StringBuilder.<init>:()V
        //   199: ldc             " Failed to load file "
        //   201: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   204: aload           6
        //   206: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   209: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   212: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   215: aload           5
        //   217: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   220: pop            
        //   221: aload           4
        //   223: astore_3       
        //   224: aload_0        
        //   225: aload_1        
        //   226: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.remove:(Ljava/lang/String;)V
        //   229: aload           4
        //   231: ifnull          239
        //   234: aload           4
        //   236: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   239: aload_2        
        //   240: ifnull          84
        //   243: aload_2        
        //   244: aload_1        
        //   245: aconst_null    
        //   246: lconst_0       
        //   247: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   252: goto            84
        //   255: astore_1       
        //   256: aload_0        
        //   257: monitorexit    
        //   258: aload_1        
        //   259: athrow         
        //   260: astore_1       
        //   261: aconst_null    
        //   262: astore_3       
        //   263: aload_3        
        //   264: ifnull          271
        //   267: aload_3        
        //   268: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   271: aload_1        
        //   272: athrow         
        //   273: astore_3       
        //   274: goto            239
        //   277: astore_2       
        //   278: goto            271
        //   281: astore_1       
        //   282: goto            263
        //   285: astore          5
        //   287: goto            187
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      59     255    260    Any
        //  63     71     255    260    Any
        //  75     84     255    260    Any
        //  87     102    255    260    Any
        //  102    121    182    187    Ljava/io/IOException;
        //  102    121    260    263    Any
        //  124    144    285    290    Ljava/io/IOException;
        //  124    144    281    285    Any
        //  151    165    285    290    Ljava/io/IOException;
        //  151    165    281    285    Any
        //  170    175    178    182    Ljava/io/IOException;
        //  170    175    255    260    Any
        //  190    221    281    285    Any
        //  224    229    281    285    Any
        //  234    239    273    277    Ljava/io/IOException;
        //  234    239    255    260    Any
        //  243    252    255    260    Any
        //  267    271    277    281    Ljava/io/IOException;
        //  267    271    255    260    Any
        //  271    273    255    260    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0165:
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
    public void loadAll(final DataRepository$LoadedCallback p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mRootDirectory:Ljava/io/File;
        //     6: invokevirtual   java/io/File.exists:()Z
        //     9: ifne            68
        //    12: aload_0        
        //    13: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mRootDirectory:Ljava/io/File;
        //    16: invokevirtual   java/io/File.mkdirs:()Z
        //    19: ifne            54
        //    22: ldc             "nf_log"
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc_w           "Unable to create cache dir "
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: aload_0        
        //    38: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mRootDirectory:Ljava/io/File;
        //    41: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    44: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    47: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    50: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    53: pop            
        //    54: aload_1        
        //    55: ifnull          65
        //    58: aload_1        
        //    59: aconst_null    
        //    60: invokeinterface com/netflix/mediaclient/util/data/DataRepository$LoadedCallback.onLoaded:([Lcom/netflix/mediaclient/util/data/DataRepository$Entry;)V
        //    65: aload_0        
        //    66: monitorexit    
        //    67: return         
        //    68: aload_0        
        //    69: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mRootDirectory:Ljava/io/File;
        //    72: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    75: astore          4
        //    77: aload           4
        //    79: ifnonnull       101
        //    82: aload_1        
        //    83: ifnull          65
        //    86: aload_1        
        //    87: aconst_null    
        //    88: invokeinterface com/netflix/mediaclient/util/data/DataRepository$LoadedCallback.onLoaded:([Lcom/netflix/mediaclient/util/data/DataRepository$Entry;)V
        //    93: goto            65
        //    96: astore_1       
        //    97: aload_0        
        //    98: monitorexit    
        //    99: aload_1        
        //   100: athrow         
        //   101: aload           4
        //   103: arraylength    
        //   104: istore_3       
        //   105: iconst_0       
        //   106: istore_2       
        //   107: iload_2        
        //   108: iload_3        
        //   109: if_icmpge       248
        //   112: aload           4
        //   114: iload_2        
        //   115: aaload         
        //   116: astore          5
        //   118: aload           5
        //   120: ifnull          335
        //   123: aload           5
        //   125: invokevirtual   java/io/File.exists:()Z
        //   128: ifeq            335
        //   131: ldc             "nf_log"
        //   133: iconst_3       
        //   134: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   137: ifeq            221
        //   140: new             Ljava/text/SimpleDateFormat;
        //   143: dup            
        //   144: ldc_w           "dd.MM.yyyy HH:mm:ss"
        //   147: invokespecial   java/text/SimpleDateFormat.<init>:(Ljava/lang/String;)V
        //   150: new             Ljava/util/Date;
        //   153: dup            
        //   154: aload           5
        //   156: invokevirtual   java/io/File.lastModified:()J
        //   159: invokespecial   java/util/Date.<init>:(J)V
        //   162: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   165: astore          6
        //   167: ldc             "nf_log"
        //   169: new             Ljava/lang/StringBuilder;
        //   172: dup            
        //   173: invokespecial   java/lang/StringBuilder.<init>:()V
        //   176: ldc             "File found "
        //   178: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: aload           5
        //   183: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   186: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   189: ldc_w           " created "
        //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   195: aload           6
        //   197: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   200: ldc_w           ". Size [B]: "
        //   203: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   206: aload           5
        //   208: invokevirtual   java/io/File.length:()J
        //   211: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   217: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   220: pop            
        //   221: aload_0        
        //   222: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   225: aload           5
        //   227: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   230: new             Lcom/netflix/mediaclient/util/data/FileSystemEntryImpl;
        //   233: dup            
        //   234: aload           5
        //   236: invokespecial   com/netflix/mediaclient/util/data/FileSystemEntryImpl.<init>:(Ljava/io/File;)V
        //   239: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   244: pop            
        //   245: goto            335
        //   248: ldc             "nf_log"
        //   250: iconst_3       
        //   251: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   254: ifeq            291
        //   257: ldc             "nf_log"
        //   259: new             Ljava/lang/StringBuilder;
        //   262: dup            
        //   263: invokespecial   java/lang/StringBuilder.<init>:()V
        //   266: aload_0        
        //   267: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   270: invokeinterface java/util/Map.size:()I
        //   275: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   278: ldc_w           " entries found!"
        //   281: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   284: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   287: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   290: pop            
        //   291: aload_1        
        //   292: ifnull          65
        //   295: aload_0        
        //   296: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   299: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //   304: astore          4
        //   306: aload_1        
        //   307: aload           4
        //   309: aload           4
        //   311: invokeinterface java/util/Collection.size:()I
        //   316: anewarray       Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //   319: invokeinterface java/util/Collection.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   324: checkcast       [Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //   327: invokeinterface com/netflix/mediaclient/util/data/DataRepository$LoadedCallback.onLoaded:([Lcom/netflix/mediaclient/util/data/DataRepository$Entry;)V
        //   332: goto            65
        //   335: iload_2        
        //   336: iconst_1       
        //   337: iadd           
        //   338: istore_2       
        //   339: goto            107
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      54     96     101    Any
        //  58     65     96     101    Any
        //  68     77     96     101    Any
        //  86     93     96     101    Any
        //  101    105    96     101    Any
        //  123    221    96     101    Any
        //  221    245    96     101    Any
        //  248    291    96     101    Any
        //  295    332    96     101    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    @Override
    public void remove(final String s) {
        while (true) {
            synchronized (this) {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Removing entry for key " + s + ", with file name " + s);
                }
                if (this.mEntries.remove(s) != null) {
                    if (Log.isLoggable("nf_log", 3)) {
                        Log.d("nf_log", "Entry found, removing " + s);
                    }
                    final File fileForName = this.getFileForName(s);
                    if (fileForName != null) {
                        final boolean delete = fileForName.delete();
                        if (Log.isLoggable("nf_log", 3)) {
                            Log.d("nf_log", "File found " + fileForName.getName() + " and deleted " + delete + " for key " + s);
                        }
                    }
                    else if (Log.isLoggable("nf_log", 6)) {
                        Log.e("nf_log", "File is null for key" + s);
                    }
                    return;
                }
            }
            if (Log.isLoggable("nf_log", 3)) {
                final String s2;
                Log.w("nf_log", "Entry not found, can not remove " + s2);
            }
        }
    }
    
    @Override
    public String save(String save, final byte[] array) {
        synchronized (this) {
            save = this.save(save, array, null);
            return save;
        }
    }
    
    @Override
    public String save(String key, final byte[] array, final DataRepository$DataSavedCallback dataRepository$DataSavedCallback) {
        final String s = null;
        synchronized (this) {
            this.pruneIfNeeded(array.length);
            final File fileForName = this.getFileForName(FileSystemEntryImpl.getFilenameForKey(key));
            try {
                final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileForName));
                bufferedOutputStream.write(array);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                final FileSystemEntryImpl fileSystemEntryImpl = new FileSystemEntryImpl(fileForName);
                this.mEntries.put(fileForName.getName(), fileSystemEntryImpl);
                if (dataRepository$DataSavedCallback != null) {
                    dataRepository$DataSavedCallback.onDataSaved(fileSystemEntryImpl.getKey());
                }
                key = fileSystemEntryImpl.getKey();
                return key;
            }
            catch (IOException ex) {
                Log.e("nf_log", "Failed to save data to file system!", ex);
                if (!fileForName.delete()) {
                    Log.e("nf_log", "Failed to save data. Could not clean up file " + fileForName.getAbsolutePath());
                }
                key = s;
                if (dataRepository$DataSavedCallback != null) {
                    dataRepository$DataSavedCallback.onDataSaved(null);
                    key = s;
                    return key;
                }
                return key;
            }
        }
    }
}
