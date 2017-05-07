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
                        if (Log.isLoggable()) {
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
        //    86: ldc             "Entry found!"
        //    88: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    91: pop            
        //    92: aload_0        
        //    93: aload_1        
        //    94: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.getFileForName:(Ljava/lang/String;)Ljava/io/File;
        //    97: astore          6
        //    99: new             Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;
        //   102: dup            
        //   103: new             Ljava/io/FileInputStream;
        //   106: dup            
        //   107: aload           6
        //   109: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   112: aconst_null    
        //   113: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$1;)V
        //   116: astore          4
        //   118: aload           4
        //   120: astore_3       
        //   121: aload           4
        //   123: aload           6
        //   125: invokevirtual   java/io/File.length:()J
        //   128: aload           4
        //   130: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.access$100:(Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;)I
        //   133: i2l            
        //   134: lsub           
        //   135: l2i            
        //   136: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.streamToBytes:(Ljava/io/InputStream;I)[B
        //   139: astore          5
        //   141: aload_2        
        //   142: ifnull          162
        //   145: aload           4
        //   147: astore_3       
        //   148: aload_2        
        //   149: aload_1        
        //   150: aload           5
        //   152: aload           6
        //   154: invokevirtual   java/io/File.lastModified:()J
        //   157: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   162: aload           4
        //   164: ifnull          81
        //   167: aload           4
        //   169: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   172: goto            81
        //   175: astore_1       
        //   176: goto            81
        //   179: astore          5
        //   181: aconst_null    
        //   182: astore          4
        //   184: aload           4
        //   186: astore_3       
        //   187: ldc             "nf_log"
        //   189: new             Ljava/lang/StringBuilder;
        //   192: dup            
        //   193: invokespecial   java/lang/StringBuilder.<init>:()V
        //   196: ldc             " Failed to load file "
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload           6
        //   203: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   206: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   209: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   212: aload           5
        //   214: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   217: pop            
        //   218: aload           4
        //   220: astore_3       
        //   221: aload_0        
        //   222: aload_1        
        //   223: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.remove:(Ljava/lang/String;)V
        //   226: aload           4
        //   228: ifnull          236
        //   231: aload           4
        //   233: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   236: aload_2        
        //   237: ifnull          81
        //   240: aload_2        
        //   241: aload_1        
        //   242: aconst_null    
        //   243: lconst_0       
        //   244: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   249: goto            81
        //   252: astore_1       
        //   253: aload_0        
        //   254: monitorexit    
        //   255: aload_1        
        //   256: athrow         
        //   257: astore_1       
        //   258: aconst_null    
        //   259: astore_3       
        //   260: aload_3        
        //   261: ifnull          268
        //   264: aload_3        
        //   265: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   268: aload_1        
        //   269: athrow         
        //   270: astore_3       
        //   271: goto            236
        //   274: astore_2       
        //   275: goto            268
        //   278: astore_1       
        //   279: goto            260
        //   282: astore          5
        //   284: goto            184
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      56     252    257    Any
        //  60     68     252    257    Any
        //  72     81     252    257    Any
        //  84     99     252    257    Any
        //  99     118    179    184    Ljava/io/IOException;
        //  99     118    257    260    Any
        //  121    141    282    287    Ljava/io/IOException;
        //  121    141    278    282    Any
        //  148    162    282    287    Ljava/io/IOException;
        //  148    162    278    282    Any
        //  167    172    175    179    Ljava/io/IOException;
        //  167    172    252    257    Any
        //  187    218    278    282    Any
        //  221    226    278    282    Any
        //  231    236    270    274    Ljava/io/IOException;
        //  231    236    252    257    Any
        //  240    249    252    257    Any
        //  264    268    274    278    Ljava/io/IOException;
        //  264    268    252    257    Any
        //  268    270    252    257    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0162:
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
