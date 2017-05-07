// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.data;

import java.io.FilterInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
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
    private final Map<String, Entry> mEntries;
    private final int mMaxSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
    public FileSystemDataRepositoryImpl(final File file) {
        this(file, 5242880);
    }
    
    public FileSystemDataRepositoryImpl(final File mRootDirectory, final int mMaxSizeInBytes) {
        this.mEntries = new HashMap<String, Entry>();
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
            int n2 = 0;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final ArrayList<Object> list = new ArrayList<Object>(this.mEntries.values());
            Collections.sort(list, (Comparator<? super Object>)new Comparator<Entry>() {
                @Override
                public int compare(final Entry entry, final Entry entry2) {
                    if (entry.getTs() == entry2.getTs()) {
                        return 0;
                    }
                    if (entry.getTs() < entry2.getTs()) {
                        return -1;
                    }
                    return 1;
                }
            });
            final Iterator<T> iterator = list.iterator();
            int n3;
            do {
                n3 = n2;
                if (!iterator.hasNext()) {
                    break;
                }
                final Entry entry = (Entry)iterator.next();
                final File fileForName = this.getFileForName(entry.getKey());
                final long length = fileForName.length();
                if (fileForName.delete()) {
                    this.mTotalSize -= length;
                }
                else {
                    Log.e("nf_log", "Could not delete entry " + fileForName.getName());
                }
                this.mEntries.remove(entry.getKey());
                n3 = ++n2;
            } while (this.mTotalSize + n >= this.mMaxSizeInBytes);
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "Pruned " + n3 + " in " + (SystemClock.elapsedRealtime() - elapsedRealtime) + " [ms]. Still available [B]: " + (this.mTotalSize - mTotalSize));
            }
        }
    }
    
    private static byte[] streamToBytes(final InputStream inputStream, final int n) throws IOException {
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
    public Entry[] getEntries() {
        synchronized (this) {
            return this.mEntries.values().toArray(new Entry[this.mEntries.size()]);
        }
    }
    
    @Override
    public void load(final String p0, final DataLoadedCallback p1) {
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
        //   100: astore          7
        //   102: aconst_null    
        //   103: astore_3       
        //   104: aconst_null    
        //   105: astore          6
        //   107: new             Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;
        //   110: dup            
        //   111: new             Ljava/io/FileInputStream;
        //   114: dup            
        //   115: aload           7
        //   117: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //   120: aconst_null    
        //   121: invokespecial   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$1;)V
        //   124: astore          4
        //   126: aload           4
        //   128: aload           7
        //   130: invokevirtual   java/io/File.length:()J
        //   133: aload           4
        //   135: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.access$100:(Lcom/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream;)I
        //   138: i2l            
        //   139: lsub           
        //   140: l2i            
        //   141: invokestatic    com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.streamToBytes:(Ljava/io/InputStream;I)[B
        //   144: astore_3       
        //   145: aload_2        
        //   146: ifnull          162
        //   149: aload_2        
        //   150: aload_1        
        //   151: aload_3        
        //   152: aload           7
        //   154: invokevirtual   java/io/File.lastModified:()J
        //   157: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   162: aload           4
        //   164: ifnull          84
        //   167: aload           4
        //   169: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   172: goto            84
        //   175: astore_1       
        //   176: goto            84
        //   179: astore          5
        //   181: aload           6
        //   183: astore          4
        //   185: aload           4
        //   187: astore_3       
        //   188: ldc             "nf_log"
        //   190: new             Ljava/lang/StringBuilder;
        //   193: dup            
        //   194: invokespecial   java/lang/StringBuilder.<init>:()V
        //   197: ldc             " Failed to load file "
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: aload           7
        //   204: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   207: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   210: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   213: aload           5
        //   215: invokestatic    com/netflix/mediaclient/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   218: pop            
        //   219: aload           4
        //   221: astore_3       
        //   222: aload_0        
        //   223: aload_1        
        //   224: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.remove:(Ljava/lang/String;)V
        //   227: aload           4
        //   229: ifnull          237
        //   232: aload           4
        //   234: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   237: aload_2        
        //   238: ifnull          84
        //   241: aload_2        
        //   242: aload_1        
        //   243: aconst_null    
        //   244: lconst_0       
        //   245: invokeinterface com/netflix/mediaclient/util/data/DataRepository$DataLoadedCallback.onDataLoaded:(Ljava/lang/String;[BJ)V
        //   250: goto            84
        //   253: astore_1       
        //   254: aload_0        
        //   255: monitorexit    
        //   256: aload_1        
        //   257: athrow         
        //   258: astore_1       
        //   259: aload_3        
        //   260: ifnull          267
        //   263: aload_3        
        //   264: invokevirtual   com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl$CountingInputStream.close:()V
        //   267: aload_1        
        //   268: athrow         
        //   269: astore_3       
        //   270: goto            237
        //   273: astore_2       
        //   274: goto            267
        //   277: astore_1       
        //   278: aload           4
        //   280: astore_3       
        //   281: goto            259
        //   284: astore          5
        //   286: goto            185
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      59     253    258    Any
        //  63     71     253    258    Any
        //  75     84     253    258    Any
        //  87     102    253    258    Any
        //  107    126    179    185    Ljava/io/IOException;
        //  107    126    258    259    Any
        //  126    145    284    289    Ljava/io/IOException;
        //  126    145    277    284    Any
        //  149    162    284    289    Ljava/io/IOException;
        //  149    162    277    284    Any
        //  167    172    175    179    Ljava/io/IOException;
        //  167    172    253    258    Any
        //  188    219    258    259    Any
        //  222    227    258    259    Any
        //  232    237    269    273    Ljava/io/IOException;
        //  232    237    253    258    Any
        //  241    250    253    258    Any
        //  263    267    273    277    Ljava/io/IOException;
        //  263    267    253    258    Any
        //  267    269    253    258    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 146, Size: 146
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
    
    @Override
    public void loadAll(final LoadedCallback p0) {
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
        //     9: ifne            57
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
        //    54: aload_0        
        //    55: monitorexit    
        //    56: return         
        //    57: aload_0        
        //    58: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mRootDirectory:Ljava/io/File;
        //    61: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    64: astore          4
        //    66: aload           4
        //    68: ifnull          54
        //    71: aload           4
        //    73: arraylength    
        //    74: istore_3       
        //    75: iconst_0       
        //    76: istore_2       
        //    77: iload_2        
        //    78: iload_3        
        //    79: if_icmpge       218
        //    82: aload           4
        //    84: iload_2        
        //    85: aaload         
        //    86: astore          5
        //    88: aload           5
        //    90: ifnull          310
        //    93: aload           5
        //    95: invokevirtual   java/io/File.exists:()Z
        //    98: ifeq            310
        //   101: ldc             "nf_log"
        //   103: iconst_3       
        //   104: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   107: ifeq            191
        //   110: new             Ljava/text/SimpleDateFormat;
        //   113: dup            
        //   114: ldc_w           "dd.MM.yyyy HH:mm:ss"
        //   117: invokespecial   java/text/SimpleDateFormat.<init>:(Ljava/lang/String;)V
        //   120: new             Ljava/util/Date;
        //   123: dup            
        //   124: aload           5
        //   126: invokevirtual   java/io/File.lastModified:()J
        //   129: invokespecial   java/util/Date.<init>:(J)V
        //   132: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   135: astore          6
        //   137: ldc             "nf_log"
        //   139: new             Ljava/lang/StringBuilder;
        //   142: dup            
        //   143: invokespecial   java/lang/StringBuilder.<init>:()V
        //   146: ldc             "File found "
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: aload           5
        //   153: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: ldc_w           " created "
        //   162: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   165: aload           6
        //   167: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   170: ldc_w           ". Size [B]: "
        //   173: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   176: aload           5
        //   178: invokevirtual   java/io/File.length:()J
        //   181: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   187: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   190: pop            
        //   191: aload_0        
        //   192: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   195: aload           5
        //   197: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   200: new             Lcom/netflix/mediaclient/util/data/FileSystemEntryImpl;
        //   203: dup            
        //   204: aload           5
        //   206: invokespecial   com/netflix/mediaclient/util/data/FileSystemEntryImpl.<init>:(Ljava/io/File;)V
        //   209: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   214: pop            
        //   215: goto            310
        //   218: ldc             "nf_log"
        //   220: iconst_3       
        //   221: invokestatic    com/netflix/mediaclient/Log.isLoggable:(Ljava/lang/String;I)Z
        //   224: ifeq            261
        //   227: ldc             "nf_log"
        //   229: new             Ljava/lang/StringBuilder;
        //   232: dup            
        //   233: invokespecial   java/lang/StringBuilder.<init>:()V
        //   236: aload_0        
        //   237: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   240: invokeinterface java/util/Map.size:()I
        //   245: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   248: ldc_w           " entries found!"
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   257: invokestatic    com/netflix/mediaclient/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   260: pop            
        //   261: aload_1        
        //   262: ifnull          54
        //   265: aload_0        
        //   266: getfield        com/netflix/mediaclient/util/data/FileSystemDataRepositoryImpl.mEntries:Ljava/util/Map;
        //   269: invokeinterface java/util/Map.values:()Ljava/util/Collection;
        //   274: astore          4
        //   276: aload_1        
        //   277: aload           4
        //   279: aload           4
        //   281: invokeinterface java/util/Collection.size:()I
        //   286: anewarray       Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //   289: invokeinterface java/util/Collection.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   294: checkcast       [Lcom/netflix/mediaclient/util/data/DataRepository$Entry;
        //   297: invokeinterface com/netflix/mediaclient/util/data/DataRepository$LoadedCallback.onLoaded:([Lcom/netflix/mediaclient/util/data/DataRepository$Entry;)V
        //   302: goto            54
        //   305: astore_1       
        //   306: aload_0        
        //   307: monitorexit    
        //   308: aload_1        
        //   309: athrow         
        //   310: iload_2        
        //   311: iconst_1       
        //   312: iadd           
        //   313: istore_2       
        //   314: goto            77
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      54     305    310    Any
        //  57     66     305    310    Any
        //  71     75     305    310    Any
        //  93     191    305    310    Any
        //  191    215    305    310    Any
        //  218    261    305    310    Any
        //  265    302    305    310    Any
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
                        Log.w("nf_log", "Entry found, removing " + s);
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
    public String save(String key, final byte[] array) {
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
                key = ((Entry)fileSystemEntryImpl).getKey();
                return key;
            }
            catch (IOException ex) {
                Log.e("nf_log", "Failed to save data to file system!", ex);
                if (!fileForName.delete()) {
                    Log.e("nf_log", "Failed to save data. Could not clean up file " + fileForName.getAbsolutePath());
                }
                key = null;
            }
        }
    }
    
    private static class CountingInputStream extends FilterInputStream
    {
        private int bytesRead;
        
        private CountingInputStream(final InputStream inputStream) {
            super(inputStream);
            this.bytesRead = 0;
        }
        
        @Override
        public int read() throws IOException {
            final int read = super.read();
            if (read != -1) {
                ++this.bytesRead;
            }
            return read;
        }
        
        @Override
        public int read(final byte[] array, int read, final int n) throws IOException {
            read = super.read(array, read, n);
            if (read != -1) {
                this.bytesRead += read;
            }
            return read;
        }
    }
}
