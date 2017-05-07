// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.OutputStream;
import java.io.FileOutputStream;
import com.android.volley.Cache$Entry;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import android.os.SystemClock;
import com.android.volley.VolleyLog;
import java.util.LinkedHashMap;
import java.io.File;
import java.util.Map;
import com.android.volley.Cache;

public class DiskBasedCache implements Cache
{
    private static final int CACHE_VERSION = 2;
    private static final int DEFAULT_DISK_USAGE_BYTES = 5242880;
    private static final float HYSTERESIS_FACTOR = 0.9f;
    private final Map<String, DiskBasedCache$CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
    public DiskBasedCache(final File file) {
        this(file, 5242880);
    }
    
    public DiskBasedCache(final File mRootDirectory, final int mMaxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap<String, DiskBasedCache$CacheHeader>(16, 0.75f, true);
        this.mTotalSize = 0L;
        this.mRootDirectory = mRootDirectory;
        this.mMaxCacheSizeInBytes = mMaxCacheSizeInBytes;
    }
    
    private String getFilenameForKey(final String s) {
        final int n = s.length() / 2;
        return String.valueOf(s.substring(0, n).hashCode()) + String.valueOf(s.substring(n).hashCode());
    }
    
    private void pruneIfNeeded(final int n) {
        if (this.mTotalSize + n >= this.mMaxCacheSizeInBytes) {
            if (VolleyLog.DEBUG) {
                VolleyLog.v("Pruning old cache entries.", new Object[0]);
            }
            final long mTotalSize = this.mTotalSize;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Iterator<Map.Entry<String, DiskBasedCache$CacheHeader>> iterator = this.mEntries.entrySet().iterator();
            int n2 = 0;
            while (true) {
                while (iterator.hasNext()) {
                    final DiskBasedCache$CacheHeader diskBasedCache$CacheHeader = iterator.next().getValue();
                    if (this.getFileForKey(diskBasedCache$CacheHeader.key).delete()) {
                        this.mTotalSize -= diskBasedCache$CacheHeader.size;
                    }
                    else {
                        VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", diskBasedCache$CacheHeader.key, this.getFilenameForKey(diskBasedCache$CacheHeader.key));
                    }
                    iterator.remove();
                    ++n2;
                    if (this.mTotalSize + n < this.mMaxCacheSizeInBytes * 0.9f) {
                        if (VolleyLog.DEBUG) {
                            VolleyLog.v("pruned %d files, %d bytes, %d ms", n2, this.mTotalSize - mTotalSize, SystemClock.elapsedRealtime() - elapsedRealtime);
                        }
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    private void putEntry(final String s, final DiskBasedCache$CacheHeader diskBasedCache$CacheHeader) {
        if (!this.mEntries.containsKey(s)) {
            this.mTotalSize += diskBasedCache$CacheHeader.size;
        }
        else {
            this.mTotalSize += diskBasedCache$CacheHeader.size - this.mEntries.get(s).size;
        }
        this.mEntries.put(s, diskBasedCache$CacheHeader);
    }
    
    private void removeEntry(final String s) {
        final DiskBasedCache$CacheHeader diskBasedCache$CacheHeader = this.mEntries.get(s);
        if (diskBasedCache$CacheHeader != null) {
            this.mTotalSize -= diskBasedCache$CacheHeader.size;
            this.mEntries.remove(s);
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
        int i = 0;
        synchronized (this) {
            final File[] listFiles = this.mRootDirectory.listFiles();
            if (listFiles != null) {
                while (i < listFiles.length) {
                    listFiles[i].delete();
                    ++i;
                }
            }
            this.mEntries.clear();
            this.mTotalSize = 0L;
            VolleyLog.d("Cache cleared.", new Object[0]);
        }
    }
    
    @Override
    public Cache$Entry get(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/android/volley/toolbox/DiskBasedCache.mEntries:Ljava/util/Map;
        //     6: aload_1        
        //     7: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: checkcast       Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    15: astore          4
        //    17: aload           4
        //    19: ifnonnull       28
        //    22: aconst_null    
        //    23: astore_1       
        //    24: aload_0        
        //    25: monitorexit    
        //    26: aload_1        
        //    27: areturn        
        //    28: aload_0        
        //    29: aload_1        
        //    30: invokevirtual   com/android/volley/toolbox/DiskBasedCache.getFileForKey:(Ljava/lang/String;)Ljava/io/File;
        //    33: astore          5
        //    35: new             Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;
        //    38: dup            
        //    39: new             Ljava/io/FileInputStream;
        //    42: dup            
        //    43: aload           5
        //    45: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    48: aconst_null    
        //    49: invokespecial   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/android/volley/toolbox/DiskBasedCache$1;)V
        //    52: astore_3       
        //    53: aload_3        
        //    54: astore_2       
        //    55: aload_3        
        //    56: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    59: pop            
        //    60: aload_3        
        //    61: astore_2       
        //    62: aload           4
        //    64: aload_3        
        //    65: aload           5
        //    67: invokevirtual   java/io/File.length:()J
        //    70: aload_3        
        //    71: invokestatic    com/android/volley/toolbox/DiskBasedCache$CountingInputStream.access$100:(Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)I
        //    74: i2l            
        //    75: lsub           
        //    76: l2i            
        //    77: invokestatic    com/android/volley/toolbox/DiskBasedCache.streamToBytes:(Ljava/io/InputStream;I)[B
        //    80: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CacheHeader.toCacheEntry:([B)Lcom/android/volley/Cache$Entry;
        //    83: astore          4
        //    85: aload           4
        //    87: astore_2       
        //    88: aload_2        
        //    89: astore_1       
        //    90: aload_3        
        //    91: ifnull          24
        //    94: aload_3        
        //    95: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //    98: aload_2        
        //    99: astore_1       
        //   100: goto            24
        //   103: astore_1       
        //   104: aconst_null    
        //   105: astore_1       
        //   106: goto            24
        //   109: astore          4
        //   111: aconst_null    
        //   112: astore_3       
        //   113: aload_3        
        //   114: astore_2       
        //   115: ldc             "%s: %s"
        //   117: iconst_2       
        //   118: anewarray       Ljava/lang/Object;
        //   121: dup            
        //   122: iconst_0       
        //   123: aload           5
        //   125: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   128: aastore        
        //   129: dup            
        //   130: iconst_1       
        //   131: aload           4
        //   133: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   136: aastore        
        //   137: invokestatic    com/android/volley/VolleyLog.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   140: aload_3        
        //   141: astore_2       
        //   142: aload_0        
        //   143: aload_1        
        //   144: invokevirtual   com/android/volley/toolbox/DiskBasedCache.remove:(Ljava/lang/String;)V
        //   147: aload_3        
        //   148: ifnull          155
        //   151: aload_3        
        //   152: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   155: aconst_null    
        //   156: astore_1       
        //   157: goto            24
        //   160: astore_1       
        //   161: aconst_null    
        //   162: astore_1       
        //   163: goto            24
        //   166: astore_1       
        //   167: aconst_null    
        //   168: astore_2       
        //   169: aload_2        
        //   170: ifnull          177
        //   173: aload_2        
        //   174: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   177: aload_1        
        //   178: athrow         
        //   179: astore_1       
        //   180: aload_0        
        //   181: monitorexit    
        //   182: aload_1        
        //   183: athrow         
        //   184: astore_1       
        //   185: aconst_null    
        //   186: astore_1       
        //   187: goto            24
        //   190: astore_1       
        //   191: goto            169
        //   194: astore          4
        //   196: goto            113
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      17     179    184    Any
        //  28     35     179    184    Any
        //  35     53     109    113    Ljava/io/IOException;
        //  35     53     166    169    Any
        //  55     60     194    199    Ljava/io/IOException;
        //  55     60     190    194    Any
        //  62     85     194    199    Ljava/io/IOException;
        //  62     85     190    194    Any
        //  94     98     103    109    Ljava/io/IOException;
        //  94     98     179    184    Any
        //  115    140    190    194    Any
        //  142    147    190    194    Any
        //  151    155    160    166    Ljava/io/IOException;
        //  151    155    179    184    Any
        //  173    177    184    190    Ljava/io/IOException;
        //  173    177    179    184    Any
        //  177    179    179    184    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
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
    
    public File getFileForKey(final String s) {
        return new File(this.mRootDirectory, this.getFilenameForKey(s));
    }
    
    @Override
    public void initialize() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //     6: invokevirtual   java/io/File.exists:()Z
        //     9: ifne            45
        //    12: aload_0        
        //    13: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    16: invokevirtual   java/io/File.mkdirs:()Z
        //    19: ifne            42
        //    22: ldc_w           "Unable to create cache dir %s"
        //    25: iconst_1       
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: iconst_0       
        //    31: aload_0        
        //    32: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    35: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    38: aastore        
        //    39: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    42: aload_0        
        //    43: monitorexit    
        //    44: return         
        //    45: aload_0        
        //    46: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    49: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    52: astore          5
        //    54: aload           5
        //    56: ifnull          42
        //    59: aload           5
        //    61: arraylength    
        //    62: istore_2       
        //    63: iconst_0       
        //    64: istore_1       
        //    65: iload_1        
        //    66: iload_2        
        //    67: if_icmpge       42
        //    70: aload           5
        //    72: iload_1        
        //    73: aaload         
        //    74: astore          6
        //    76: aconst_null    
        //    77: astore_3       
        //    78: new             Ljava/io/FileInputStream;
        //    81: dup            
        //    82: aload           6
        //    84: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    87: astore          4
        //    89: aload           4
        //    91: astore_3       
        //    92: aload           4
        //    94: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    97: astore          7
        //    99: aload           4
        //   101: astore_3       
        //   102: aload           7
        //   104: aload           6
        //   106: invokevirtual   java/io/File.length:()J
        //   109: putfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.size:J
        //   112: aload           4
        //   114: astore_3       
        //   115: aload_0        
        //   116: aload           7
        //   118: getfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.key:Ljava/lang/String;
        //   121: aload           7
        //   123: invokespecial   com/android/volley/toolbox/DiskBasedCache.putEntry:(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
        //   126: aload           4
        //   128: ifnull          136
        //   131: aload           4
        //   133: invokevirtual   java/io/FileInputStream.close:()V
        //   136: iload_1        
        //   137: iconst_1       
        //   138: iadd           
        //   139: istore_1       
        //   140: goto            65
        //   143: astore_3       
        //   144: aconst_null    
        //   145: astore          4
        //   147: aload           6
        //   149: ifnull          161
        //   152: aload           4
        //   154: astore_3       
        //   155: aload           6
        //   157: invokevirtual   java/io/File.delete:()Z
        //   160: pop            
        //   161: aload           4
        //   163: ifnull          136
        //   166: aload           4
        //   168: invokevirtual   java/io/FileInputStream.close:()V
        //   171: goto            136
        //   174: astore_3       
        //   175: goto            136
        //   178: astore          5
        //   180: aload_3        
        //   181: astore          4
        //   183: aload           5
        //   185: astore_3       
        //   186: aload           4
        //   188: ifnull          196
        //   191: aload           4
        //   193: invokevirtual   java/io/FileInputStream.close:()V
        //   196: aload_3        
        //   197: athrow         
        //   198: astore_3       
        //   199: aload_0        
        //   200: monitorexit    
        //   201: aload_3        
        //   202: athrow         
        //   203: astore_3       
        //   204: goto            136
        //   207: astore          4
        //   209: goto            196
        //   212: astore          5
        //   214: aload_3        
        //   215: astore          4
        //   217: aload           5
        //   219: astore_3       
        //   220: goto            186
        //   223: astore_3       
        //   224: goto            147
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      42     198    203    Any
        //  45     54     198    203    Any
        //  59     63     198    203    Any
        //  78     89     143    147    Ljava/io/IOException;
        //  78     89     178    186    Any
        //  92     99     223    227    Ljava/io/IOException;
        //  92     99     212    223    Any
        //  102    112    223    227    Ljava/io/IOException;
        //  102    112    212    223    Any
        //  115    126    223    227    Ljava/io/IOException;
        //  115    126    212    223    Any
        //  131    136    203    207    Ljava/io/IOException;
        //  131    136    198    203    Any
        //  155    161    212    223    Any
        //  166    171    174    178    Ljava/io/IOException;
        //  166    171    198    203    Any
        //  191    196    207    212    Ljava/io/IOException;
        //  191    196    198    203    Any
        //  196    198    198    203    Any
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
    public void invalidate(final String s, final boolean b) {
        synchronized (this) {
            final Cache$Entry value = this.get(s);
            if (value != null) {
                value.softTtl = 0L;
                if (b) {
                    value.ttl = 0L;
                }
                this.put(s, value);
            }
        }
    }
    
    @Override
    public void put(final String s, final Cache$Entry cache$Entry) {
        synchronized (this) {
            this.pruneIfNeeded(cache$Entry.data.length);
            final File fileForKey = this.getFileForKey(s);
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(fileForKey);
                final DiskBasedCache$CacheHeader diskBasedCache$CacheHeader = new DiskBasedCache$CacheHeader(s, cache$Entry);
                diskBasedCache$CacheHeader.writeHeader(fileOutputStream);
                fileOutputStream.write(cache$Entry.data);
                fileOutputStream.close();
                this.putEntry(s, diskBasedCache$CacheHeader);
            }
            catch (IOException ex) {
                if (!fileForKey.delete()) {
                    VolleyLog.d("Could not clean up file %s", fileForKey.getAbsolutePath());
                }
            }
        }
    }
    
    @Override
    public void remove(final String s) {
        synchronized (this) {
            final boolean delete = this.getFileForKey(s).delete();
            this.removeEntry(s);
            if (!delete) {
                VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", s, this.getFilenameForKey(s));
            }
        }
    }
}
