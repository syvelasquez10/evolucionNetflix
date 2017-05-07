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
    private final Map<String, DiskBasedCache$CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
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
        //     9: ifne            44
        //    12: aload_0        
        //    13: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    16: invokevirtual   java/io/File.mkdirs:()Z
        //    19: ifne            41
        //    22: ldc             "Unable to create cache dir %s"
        //    24: iconst_1       
        //    25: anewarray       Ljava/lang/Object;
        //    28: dup            
        //    29: iconst_0       
        //    30: aload_0        
        //    31: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    34: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    37: aastore        
        //    38: invokestatic    com/android/volley/VolleyLog.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    41: aload_0        
        //    42: monitorexit    
        //    43: return         
        //    44: aload_0        
        //    45: getfield        com/android/volley/toolbox/DiskBasedCache.mRootDirectory:Ljava/io/File;
        //    48: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    51: astore          5
        //    53: aload           5
        //    55: ifnull          41
        //    58: aload           5
        //    60: arraylength    
        //    61: istore_2       
        //    62: iconst_0       
        //    63: istore_1       
        //    64: iload_1        
        //    65: iload_2        
        //    66: if_icmpge       41
        //    69: aload           5
        //    71: iload_1        
        //    72: aaload         
        //    73: astore          6
        //    75: aconst_null    
        //    76: astore_3       
        //    77: new             Ljava/io/FileInputStream;
        //    80: dup            
        //    81: aload           6
        //    83: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    86: astore          4
        //    88: aload           4
        //    90: astore_3       
        //    91: aload           4
        //    93: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    96: astore          7
        //    98: aload           4
        //   100: astore_3       
        //   101: aload           7
        //   103: aload           6
        //   105: invokevirtual   java/io/File.length:()J
        //   108: putfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.size:J
        //   111: aload           4
        //   113: astore_3       
        //   114: aload_0        
        //   115: aload           7
        //   117: getfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.key:Ljava/lang/String;
        //   120: aload           7
        //   122: invokespecial   com/android/volley/toolbox/DiskBasedCache.putEntry:(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
        //   125: aload           4
        //   127: ifnull          135
        //   130: aload           4
        //   132: invokevirtual   java/io/FileInputStream.close:()V
        //   135: iload_1        
        //   136: iconst_1       
        //   137: iadd           
        //   138: istore_1       
        //   139: goto            64
        //   142: astore_3       
        //   143: aconst_null    
        //   144: astore          4
        //   146: aload           6
        //   148: ifnull          160
        //   151: aload           4
        //   153: astore_3       
        //   154: aload           6
        //   156: invokevirtual   java/io/File.delete:()Z
        //   159: pop            
        //   160: aload           4
        //   162: ifnull          135
        //   165: aload           4
        //   167: invokevirtual   java/io/FileInputStream.close:()V
        //   170: goto            135
        //   173: astore_3       
        //   174: goto            135
        //   177: astore          5
        //   179: aload_3        
        //   180: astore          4
        //   182: aload           5
        //   184: astore_3       
        //   185: aload           4
        //   187: ifnull          195
        //   190: aload           4
        //   192: invokevirtual   java/io/FileInputStream.close:()V
        //   195: aload_3        
        //   196: athrow         
        //   197: astore_3       
        //   198: aload_0        
        //   199: monitorexit    
        //   200: aload_3        
        //   201: athrow         
        //   202: astore_3       
        //   203: goto            135
        //   206: astore          4
        //   208: goto            195
        //   211: astore          5
        //   213: aload_3        
        //   214: astore          4
        //   216: aload           5
        //   218: astore_3       
        //   219: goto            185
        //   222: astore_3       
        //   223: goto            146
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      41     197    202    Any
        //  44     53     197    202    Any
        //  58     62     197    202    Any
        //  77     88     142    146    Ljava/io/IOException;
        //  77     88     177    185    Any
        //  91     98     222    226    Ljava/io/IOException;
        //  91     98     211    222    Any
        //  101    111    222    226    Ljava/io/IOException;
        //  101    111    211    222    Any
        //  114    125    222    226    Ljava/io/IOException;
        //  114    125    211    222    Any
        //  130    135    202    206    Ljava/io/IOException;
        //  130    135    197    202    Any
        //  154    160    211    222    Any
        //  165    170    173    177    Ljava/io/IOException;
        //  165    170    197    202    Any
        //  190    195    206    211    Ljava/io/IOException;
        //  190    195    197    202    Any
        //  195    197    197    202    Any
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
