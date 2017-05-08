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
import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.io.File;
import java.util.Map;

public class FileSystemDataRepositoryImpl implements DataRepository
{
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final int DEFAULT_MAX_SIZE_OF_ENTRY_IN_BYTES = 1024;
    private static final String TAG = "nf_log";
    private final Map<String, DataRepository$Entry> mEntries;
    private int mMaxEntrySizeInBytes;
    private final int mMaxSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
    public FileSystemDataRepositoryImpl(final File file) {
        this(file, 5242880, 1024);
    }
    
    public FileSystemDataRepositoryImpl(final File mRootDirectory, final int mMaxSizeInBytes, final int n) {
        this.mEntries = new HashMap<String, DataRepository$Entry>();
        this.mTotalSize = 0L;
        this.mRootDirectory = mRootDirectory;
        this.mMaxSizeInBytes = mMaxSizeInBytes;
    }
    
    private File getFileForName(final String s) {
        return new File(this.mRootDirectory, s);
    }
    
    private boolean isHigherThanMaximumAllocatedSize(final int n) {
        final boolean b = this.mTotalSize + n > this.mMaxSizeInBytes;
        Log.d("nf_log", "Need to prune based on using too much space: %b", b);
        return b;
    }
    
    private boolean isNotEnoughSpaceAvailable(final int n) {
        final long usableSpace = FileUtils.getUsableSpace(this.mRootDirectory);
        final boolean b = n > usableSpace;
        Log.d("nf_log", "Used space %d in bytes. Need to prune based on not having enough space (%d in bytes) on device: %b", this.mTotalSize, usableSpace, b);
        return b;
    }
    
    private void pruneIfNeeded(final int mMaxEntrySizeInBytes) {
        if (mMaxEntrySizeInBytes > this.mMaxEntrySizeInBytes) {
            this.mMaxEntrySizeInBytes = mMaxEntrySizeInBytes;
        }
        if (this.isHigherThanMaximumAllocatedSize(mMaxEntrySizeInBytes) || this.isNotEnoughSpaceAvailable(mMaxEntrySizeInBytes)) {
            Log.d("nf_log", "Pruning oldest entries.");
            final long mTotalSize = this.mTotalSize;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final ArrayList<Object> list = new ArrayList<Object>(this.mEntries.values());
            Collections.sort(list, (Comparator<? super Object>)new FileSystemDataRepositoryImpl$1(this));
            final Iterator<T> iterator = list.iterator();
            int n = 0;
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
                    ++n;
                    if (this.mTotalSize + mMaxEntrySizeInBytes < this.mMaxSizeInBytes) {
                        if (Log.isLoggable()) {
                            Log.d("nf_log", "Pruned " + n + " in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " [ms]. Still available [B]: " + (this.mTotalSize - mTotalSize) + ". Usable space now: " + FileUtils.getUsableSpace(this.mRootDirectory));
                        }
                        return;
                    }
                }
                continue;
            }
        }
        Log.d("nf_log", "No need to prune oldest entries.");
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
        //    13: ifnull          86
        //    16: aload           4
        //    18: arraylength    
        //    19: istore_2       
        //    20: iconst_0       
        //    21: istore_1       
        //    22: iload_1        
        //    23: iload_2        
        //    24: if_icmpge       86
        //    27: aload           4
        //    29: iload_1        
        //    30: aaload         
        //    31: astore          5
        //    33: aload           5
        //    35: invokevirtual   java/io/File.delete:()Z
        //    38: istore_3       
        //    39: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    42: ifeq            118
        //    45: ldc             "nf_log"
        //    47: new             Ljava/lang/StringBuilder;
        //    50: dup            
        //    51: invokespecial   java/lang/StringBuilder.<init>:()V
        //    54: ldc             "File found "
        //    56: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    59: aload           5
        //    61: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    64: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    67: ldc             " and deleted "
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: iload_3        
        //    73: invokevirtual   java/lang/StringBuilder.append:(Z)Ljava/lang/StringBuilder;
        //    76: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    79: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    82: pop            
        //    83: goto            118
        //    86: aload_0        
        //    87: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //    90: invokeinterface java/util/Map.clear:()V
        //    95: aload_0        
        //    96: lconst_0       
        //    97: putfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mTotalSize:J
        //   100: ldc             "nf_log"
        //   102: ldc             "Cache cleared."
        //   104: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   107: pop            
        //   108: aload_0        
        //   109: monitorexit    
        //   110: return         
        //   111: astore          4
        //   113: aload_0        
        //   114: monitorexit    
        //   115: aload           4
        //   117: athrow         
        //   118: iload_1        
        //   119: iconst_1       
        //   120: iadd           
        //   121: istore_1       
        //   122: goto            22
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     111    118    Any
        //  16     20     111    118    Any
        //  33     83     111    118    Any
        //  86     108    111    118    Any
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
        //    16: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //    19: ifeq            56
        //    22: ldc             "nf_log"
        //    24: new             Ljava/lang/StringBuilder;
        //    27: dup            
        //    28: invokespecial   java/lang/StringBuilder.<init>:()V
        //    31: ldc             "Find entry for key "
        //    33: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    36: aload_1        
        //    37: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    40: ldc             ", with file name "
        //    42: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    45: aload_1        
        //    46: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    49: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    52: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    55: pop            
        //    56: aload_3        
        //    57: ifnonnull       84
        //    60: ldc             "nf_log"
        //    62: ldc             "Entry not found!"
        //    64: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    67: pop            
        //    68: aload_2        
        //    69: ifnull          81
        //    72: aload_2        
        //    73: aload_1        
        //    74: aconst_null    
        //    75: lconst_0       
        //    76: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //    81: aload_0        
        //    82: monitorexit    
        //    83: return         
        //    84: ldc             "nf_log"
        //    86: ldc_w           "Entry found!"
        //    89: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    92: pop            
        //    93: aload_0        
        //    94: aload_1        
        //    95: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.getFileForName:(Ljava/lang/String;)Ljava/io/File;
        //    98: astore          6
        //   100: new             Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;
        //   103: dup            
        //   104: new             Ljava/io/FileInputStream;
        //   107: dup            
        //   108: aload           6
        //   110: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   113: aconst_null    
        //   114: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$1;)V
        //   117: astore          4
        //   119: aload           4
        //   121: astore_3       
        //   122: aload           4
        //   124: aload           6
        //   126: invokevirtual   java/io/File.length:()J
        //   129: aload           4
        //   131: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.access$100:(Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;)I
        //   134: i2l            
        //   135: lsub           
        //   136: l2i            
        //   137: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.streamToBytes:(Ljava/io/InputStream;I)[B
        //   140: astore          5
        //   142: aload_2        
        //   143: ifnull          163
        //   146: aload           4
        //   148: astore_3       
        //   149: aload_2        
        //   150: aload_1        
        //   151: aload           5
        //   153: aload           6
        //   155: invokevirtual   java/io/File.lastModified:()J
        //   158: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   163: aload           4
        //   165: ifnull          81
        //   168: aload           4
        //   170: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   173: goto            81
        //   176: astore_1       
        //   177: goto            81
        //   180: astore          5
        //   182: aconst_null    
        //   183: astore          4
        //   185: aload           4
        //   187: astore_3       
        //   188: ldc             "nf_log"
        //   190: new             Ljava/lang/StringBuilder;
        //   193: dup            
        //   194: invokespecial   java/lang/StringBuilder.<init>:()V
        //   197: ldc_w           " Failed to load file "
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: aload           6
        //   205: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: aload           5
        //   216: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   219: pop            
        //   220: aload           4
        //   222: astore_3       
        //   223: aload_0        
        //   224: aload_1        
        //   225: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.remove:(Ljava/lang/String;)V
        //   228: aload           4
        //   230: ifnull          238
        //   233: aload           4
        //   235: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   238: aload_2        
        //   239: ifnull          81
        //   242: aload_2        
        //   243: aload_1        
        //   244: aconst_null    
        //   245: lconst_0       
        //   246: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   251: goto            81
        //   254: astore_1       
        //   255: aload_0        
        //   256: monitorexit    
        //   257: aload_1        
        //   258: athrow         
        //   259: astore_1       
        //   260: aconst_null    
        //   261: astore_3       
        //   262: aload_3        
        //   263: ifnull          270
        //   266: aload_3        
        //   267: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   270: aload_1        
        //   271: athrow         
        //   272: astore_3       
        //   273: goto            238
        //   276: astore_2       
        //   277: goto            270
        //   280: astore_1       
        //   281: goto            262
        //   284: astore          5
        //   286: goto            185
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      56     254    259    Any
        //  60     68     254    259    Any
        //  72     81     254    259    Any
        //  84     100    254    259    Any
        //  100    119    180    185    Ljava/io/IOException;
        //  100    119    259    262    Any
        //  122    142    284    289    Ljava/io/IOException;
        //  122    142    280    284    Any
        //  149    163    284    289    Ljava/io/IOException;
        //  149    163    280    284    Any
        //  168    173    176    180    Ljava/io/IOException;
        //  168    173    254    259    Any
        //  188    220    280    284    Any
        //  223    228    280    284    Any
        //  233    238    272    276    Ljava/io/IOException;
        //  233    238    254    259    Any
        //  242    251    254    259    Any
        //  266    270    276    280    Ljava/io/IOException;
        //  266    270    254    259    Any
        //  270    272    254    259    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
        //   109: if_icmpge       245
        //   112: aload           4
        //   114: iload_2        
        //   115: aaload         
        //   116: astore          5
        //   118: aload           5
        //   120: ifnull          329
        //   123: aload           5
        //   125: invokevirtual   java/io/File.exists:()Z
        //   128: ifeq            329
        //   131: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   134: ifeq            218
        //   137: new             Ljava/text/SimpleDateFormat;
        //   140: dup            
        //   141: ldc_w           "dd.MM.yyyy HH:mm:ss"
        //   144: invokespecial   java/text/SimpleDateFormat.<init>:(Ljava/lang/String;)V
        //   147: new             Ljava/util/Date;
        //   150: dup            
        //   151: aload           5
        //   153: invokevirtual   java/io/File.lastModified:()J
        //   156: invokespecial   java/util/Date.<init>:(J)V
        //   159: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   162: astore          6
        //   164: ldc             "nf_log"
        //   166: new             Ljava/lang/StringBuilder;
        //   169: dup            
        //   170: invokespecial   java/lang/StringBuilder.<init>:()V
        //   173: ldc             "File found "
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: aload           5
        //   180: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   183: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   186: ldc_w           " created "
        //   189: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   192: aload           6
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: ldc_w           ". Size [B]: "
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: aload           5
        //   205: invokevirtual   java/io/File.length:()J
        //   208: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   211: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   214: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   217: pop            
        //   218: aload_0        
        //   219: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   222: aload           5
        //   224: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   227: new             Lcom/netflix/mediaclient/util/data/FileSystemEntryImpl;
        //   230: dup            
        //   231: aload           5
        //   233: invokespecial   com/netflix/mediaclient/util/data/FileSystemEntryImpl.<init>:(Ljava/io/File;)V
        //   236: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   241: pop            
        //   242: goto            329
        //   245: invokestatic    com/netflix/mediaclient/Log.isLoggable:()Z
        //   248: ifeq            285
        //   251: ldc             "nf_log"
        //   253: new             Ljava/lang/StringBuilder;
        //   256: dup            
        //   257: invokespecial   java/lang/StringBuilder.<init>:()V
        //   260: aload_0        
        //   261: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   264: invokeinterface java/util/Map.size:()I
        //   269: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   272: ldc_w           " entries found!"
        //   275: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   278: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   281: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   284: pop            
        //   285: aload_1        
        //   286: ifnull          65
        //   289: aload_0        
        //   290: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   293: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //   298: astore          4
        //   300: aload_1        
        //   301: aload           4
        //   303: aload           4
        //   305: invokeinterface java/util/Collection.size:()I
        //   310: anewarray       Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //   313: invokeinterface java/util/Collection.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   318: checkcast       [Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //   321: invokeinterface com/netflix/mediaclient/util/data/DataRepository$LoadedCallback.onLoaded:([Lcom/netflix/mediaclient/util/data/DataRepository$Entry;)V
        //   326: goto            65
        //   329: iload_2        
        //   330: iconst_1       
        //   331: iadd           
        //   332: istore_2       
        //   333: goto            107
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      54     96     101    Any
        //  58     65     96     101    Any
        //  68     77     96     101    Any
        //  86     93     96     101    Any
        //  101    105    96     101    Any
        //  123    218    96     101    Any
        //  218    242    96     101    Any
        //  245    285    96     101    Any
        //  289    326    96     101    Any
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
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Removing entry for key " + s + ", with file name " + s);
                }
                if (this.mEntries.remove(s) != null) {
                    if (Log.isLoggable()) {
                        Log.d("nf_log", "Entry found, removing " + s);
                    }
                    final File fileForName = this.getFileForName(s);
                    if (fileForName != null) {
                        final boolean delete = fileForName.delete();
                        if (Log.isLoggable()) {
                            Log.d("nf_log", "File found " + fileForName.getName() + " and deleted " + delete + " for key " + s);
                        }
                    }
                    else if (Log.isLoggable()) {
                        Log.e("nf_log", "File is null for key" + s);
                    }
                    return;
                }
            }
            if (Log.isLoggable()) {
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
            final long usableSpace = FileUtils.getUsableSpace(this.mRootDirectory);
            if (usableSpace < array.length) {
                Log.w("nf_log", "Even after pruning, we may NOT have enough space avilable. Free space: %d vs entry space %d", usableSpace, array.length);
            }
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
