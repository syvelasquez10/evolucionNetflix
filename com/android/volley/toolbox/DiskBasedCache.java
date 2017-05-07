// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.FilterInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Collections;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
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
    private final Map<String, CacheHeader> mEntries;
    private final int mMaxCacheSizeInBytes;
    private final File mRootDirectory;
    private long mTotalSize;
    
    public DiskBasedCache(final File file) {
        this(file, 5242880);
    }
    
    public DiskBasedCache(final File mRootDirectory, final int mMaxCacheSizeInBytes) {
        this.mEntries = new LinkedHashMap<String, CacheHeader>(16, 0.75f, true);
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
            int n2 = 0;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            final Iterator<Map.Entry<String, CacheHeader>> iterator = this.mEntries.entrySet().iterator();
            int n3;
            do {
                n3 = n2;
                if (!iterator.hasNext()) {
                    break;
                }
                final CacheHeader cacheHeader = iterator.next().getValue();
                if (this.getFileForKey(cacheHeader.key).delete()) {
                    this.mTotalSize -= cacheHeader.size;
                }
                else {
                    VolleyLog.d("Could not delete cache entry for key=%s, filename=%s", cacheHeader.key, this.getFilenameForKey(cacheHeader.key));
                }
                iterator.remove();
                n3 = ++n2;
            } while (this.mTotalSize + n >= this.mMaxCacheSizeInBytes * 0.9f);
            if (VolleyLog.DEBUG) {
                VolleyLog.v("pruned %d files, %d bytes, %d ms", n3, this.mTotalSize - mTotalSize, SystemClock.elapsedRealtime() - elapsedRealtime);
            }
        }
    }
    
    private void putEntry(final String s, final CacheHeader cacheHeader) {
        if (!this.mEntries.containsKey(s)) {
            this.mTotalSize += cacheHeader.size;
        }
        else {
            this.mTotalSize += cacheHeader.size - this.mEntries.get(s).size;
        }
        this.mEntries.put(s, cacheHeader);
    }
    
    private void removeEntry(final String s) {
        final CacheHeader cacheHeader = this.mEntries.get(s);
        if (cacheHeader != null) {
            this.mTotalSize -= cacheHeader.size;
            this.mEntries.remove(s);
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
        synchronized (this) {
            final File[] listFiles = this.mRootDirectory.listFiles();
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    listFiles[i].delete();
                }
            }
            this.mEntries.clear();
            this.mTotalSize = 0L;
            VolleyLog.d("Cache cleared.", new Object[0]);
        }
    }
    
    @Override
    public Entry get(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_0        
        //     4: monitorenter   
        //     5: aload_0        
        //     6: getfield        com/android/volley/toolbox/DiskBasedCache.mEntries:Ljava/util/Map;
        //     9: aload_1        
        //    10: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    15: checkcast       Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    18: astore          4
        //    20: aload           4
        //    22: ifnonnull       32
        //    25: aload           5
        //    27: astore_1       
        //    28: aload_0        
        //    29: monitorexit    
        //    30: aload_1        
        //    31: areturn        
        //    32: aload_0        
        //    33: aload_1        
        //    34: invokevirtual   com/android/volley/toolbox/DiskBasedCache.getFileForKey:(Ljava/lang/String;)Ljava/io/File;
        //    37: astore          7
        //    39: aconst_null    
        //    40: astore_2       
        //    41: aconst_null    
        //    42: astore          6
        //    44: new             Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;
        //    47: dup            
        //    48: new             Ljava/io/FileInputStream;
        //    51: dup            
        //    52: aload           7
        //    54: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    57: aconst_null    
        //    58: invokespecial   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.<init>:(Ljava/io/InputStream;Lcom/android/volley/toolbox/DiskBasedCache$1;)V
        //    61: astore_3       
        //    62: aload_3        
        //    63: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    66: pop            
        //    67: aload           4
        //    69: aload_3        
        //    70: aload           7
        //    72: invokevirtual   java/io/File.length:()J
        //    75: aload_3        
        //    76: invokestatic    com/android/volley/toolbox/DiskBasedCache$CountingInputStream.access$100:(Lcom/android/volley/toolbox/DiskBasedCache$CountingInputStream;)I
        //    79: i2l            
        //    80: lsub           
        //    81: l2i            
        //    82: invokestatic    com/android/volley/toolbox/DiskBasedCache.streamToBytes:(Ljava/io/InputStream;I)[B
        //    85: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CacheHeader.toCacheEntry:([B)Lcom/android/volley/Cache$Entry;
        //    88: astore_2       
        //    89: aload_3        
        //    90: ifnull          97
        //    93: aload_3        
        //    94: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //    97: aload_2        
        //    98: astore_1       
        //    99: goto            28
        //   102: astore_1       
        //   103: aload           5
        //   105: astore_1       
        //   106: goto            28
        //   109: astore          4
        //   111: aload           6
        //   113: astore_3       
        //   114: aload_3        
        //   115: astore_2       
        //   116: ldc             "%s: %s"
        //   118: iconst_2       
        //   119: anewarray       Ljava/lang/Object;
        //   122: dup            
        //   123: iconst_0       
        //   124: aload           7
        //   126: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   129: aastore        
        //   130: dup            
        //   131: iconst_1       
        //   132: aload           4
        //   134: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
        //   137: aastore        
        //   138: invokestatic    com/android/volley/VolleyLog.d:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   141: aload_3        
        //   142: astore_2       
        //   143: aload_0        
        //   144: aload_1        
        //   145: invokevirtual   com/android/volley/toolbox/DiskBasedCache.remove:(Ljava/lang/String;)V
        //   148: aload           5
        //   150: astore_1       
        //   151: aload_3        
        //   152: ifnull          28
        //   155: aload_3        
        //   156: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   159: aload           5
        //   161: astore_1       
        //   162: goto            28
        //   165: astore_1       
        //   166: aload           5
        //   168: astore_1       
        //   169: goto            28
        //   172: astore_1       
        //   173: aload_2        
        //   174: ifnull          181
        //   177: aload_2        
        //   178: invokevirtual   com/android/volley/toolbox/DiskBasedCache$CountingInputStream.close:()V
        //   181: aload_1        
        //   182: athrow         
        //   183: astore_1       
        //   184: aload_0        
        //   185: monitorexit    
        //   186: aload_1        
        //   187: athrow         
        //   188: astore_1       
        //   189: aload           5
        //   191: astore_1       
        //   192: goto            28
        //   195: astore_1       
        //   196: aload_3        
        //   197: astore_2       
        //   198: goto            173
        //   201: astore          4
        //   203: goto            114
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      20     183    188    Any
        //  32     39     183    188    Any
        //  44     62     109    114    Ljava/io/IOException;
        //  44     62     172    173    Any
        //  62     89     201    206    Ljava/io/IOException;
        //  62     89     195    201    Any
        //  93     97     102    109    Ljava/io/IOException;
        //  93     97     183    188    Any
        //  116    141    172    173    Any
        //  143    148    172    173    Any
        //  155    159    165    172    Ljava/io/IOException;
        //  155    159    183    188    Any
        //  177    181    188    195    Ljava/io/IOException;
        //  177    181    183    188    Any
        //  181    183    183    188    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
        //    52: astore          6
        //    54: aload           6
        //    56: ifnull          42
        //    59: aload           6
        //    61: arraylength    
        //    62: istore_2       
        //    63: iconst_0       
        //    64: istore_1       
        //    65: iload_1        
        //    66: iload_2        
        //    67: if_icmpge       42
        //    70: aload           6
        //    72: iload_1        
        //    73: aaload         
        //    74: astore          7
        //    76: aconst_null    
        //    77: astore_3       
        //    78: aconst_null    
        //    79: astore          5
        //    81: new             Ljava/io/FileInputStream;
        //    84: dup            
        //    85: aload           7
        //    87: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    90: astore          4
        //    92: aload           4
        //    94: invokestatic    com/android/volley/toolbox/DiskBasedCache$CacheHeader.readHeader:(Ljava/io/InputStream;)Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;
        //    97: astore_3       
        //    98: aload_3        
        //    99: aload           7
        //   101: invokevirtual   java/io/File.length:()J
        //   104: putfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.size:J
        //   107: aload_0        
        //   108: aload_3        
        //   109: getfield        com/android/volley/toolbox/DiskBasedCache$CacheHeader.key:Ljava/lang/String;
        //   112: aload_3        
        //   113: invokespecial   com/android/volley/toolbox/DiskBasedCache.putEntry:(Ljava/lang/String;Lcom/android/volley/toolbox/DiskBasedCache$CacheHeader;)V
        //   116: aload           4
        //   118: ifnull          126
        //   121: aload           4
        //   123: invokevirtual   java/io/FileInputStream.close:()V
        //   126: iload_1        
        //   127: iconst_1       
        //   128: iadd           
        //   129: istore_1       
        //   130: goto            65
        //   133: astore_3       
        //   134: goto            126
        //   137: astore_3       
        //   138: aload           5
        //   140: astore          4
        //   142: aload           7
        //   144: ifnull          156
        //   147: aload           4
        //   149: astore_3       
        //   150: aload           7
        //   152: invokevirtual   java/io/File.delete:()Z
        //   155: pop            
        //   156: aload           4
        //   158: ifnull          126
        //   161: aload           4
        //   163: invokevirtual   java/io/FileInputStream.close:()V
        //   166: goto            126
        //   169: astore_3       
        //   170: goto            126
        //   173: astore          4
        //   175: aload_3        
        //   176: ifnull          183
        //   179: aload_3        
        //   180: invokevirtual   java/io/FileInputStream.close:()V
        //   183: aload           4
        //   185: athrow         
        //   186: astore_3       
        //   187: aload_0        
        //   188: monitorexit    
        //   189: aload_3        
        //   190: athrow         
        //   191: astore_3       
        //   192: goto            183
        //   195: astore          5
        //   197: aload           4
        //   199: astore_3       
        //   200: aload           5
        //   202: astore          4
        //   204: goto            175
        //   207: astore_3       
        //   208: goto            142
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      42     186    191    Any
        //  45     54     186    191    Any
        //  59     63     186    191    Any
        //  81     92     137    142    Ljava/io/IOException;
        //  81     92     173    175    Any
        //  92     116    207    211    Ljava/io/IOException;
        //  92     116    195    207    Any
        //  121    126    133    137    Ljava/io/IOException;
        //  121    126    186    191    Any
        //  150    156    173    175    Any
        //  161    166    169    173    Ljava/io/IOException;
        //  161    166    186    191    Any
        //  179    183    191    195    Ljava/io/IOException;
        //  179    183    186    191    Any
        //  183    186    186    191    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    public void invalidate(final String s, final boolean b) {
        synchronized (this) {
            final Entry value = this.get(s);
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
    public void put(final String s, final Entry entry) {
        synchronized (this) {
            this.pruneIfNeeded(entry.data.length);
            final File fileForKey = this.getFileForKey(s);
            try {
                final FileOutputStream fileOutputStream = new FileOutputStream(fileForKey);
                final CacheHeader cacheHeader = new CacheHeader(s, entry);
                cacheHeader.writeHeader(fileOutputStream);
                fileOutputStream.write(entry.data);
                fileOutputStream.close();
                this.putEntry(s, cacheHeader);
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
    
    private static class CacheHeader
    {
        public String etag;
        public String key;
        public Map<String, String> responseHeaders;
        public long serverDate;
        public long size;
        public long softTtl;
        public long ttl;
        
        private CacheHeader() {
        }
        
        public CacheHeader(final String key, final Entry entry) {
            this.key = key;
            this.size = entry.data.length;
            this.etag = entry.etag;
            this.serverDate = entry.serverDate;
            this.ttl = entry.ttl;
            this.softTtl = entry.softTtl;
            this.responseHeaders = entry.responseHeaders;
        }
        
        public static CacheHeader readHeader(final InputStream inputStream) throws IOException {
            final CacheHeader cacheHeader = new CacheHeader();
            final ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            if (objectInputStream.readByte() != 2) {
                throw new IOException();
            }
            cacheHeader.key = objectInputStream.readUTF();
            cacheHeader.etag = objectInputStream.readUTF();
            if (cacheHeader.etag.equals("")) {
                cacheHeader.etag = null;
            }
            cacheHeader.serverDate = objectInputStream.readLong();
            cacheHeader.ttl = objectInputStream.readLong();
            cacheHeader.softTtl = objectInputStream.readLong();
            cacheHeader.responseHeaders = readStringStringMap(objectInputStream);
            return cacheHeader;
        }
        
        private static Map<String, String> readStringStringMap(final ObjectInputStream objectInputStream) throws IOException {
            final int int1 = objectInputStream.readInt();
            Map<String, String> emptyMap;
            if (int1 == 0) {
                emptyMap = Collections.emptyMap();
            }
            else {
                emptyMap = new HashMap<String, String>(int1);
            }
            for (int i = 0; i < int1; ++i) {
                emptyMap.put(objectInputStream.readUTF().intern(), objectInputStream.readUTF().intern());
            }
            return emptyMap;
        }
        
        private static void writeStringStringMap(final Map<String, String> map, final ObjectOutputStream objectOutputStream) throws IOException {
            if (map != null) {
                objectOutputStream.writeInt(map.size());
                for (final Map.Entry<String, String> entry : map.entrySet()) {
                    objectOutputStream.writeUTF(entry.getKey());
                    objectOutputStream.writeUTF(entry.getValue());
                }
            }
            else {
                objectOutputStream.writeInt(0);
            }
        }
        
        public Entry toCacheEntry(final byte[] data) {
            final Entry entry = new Entry();
            entry.data = data;
            entry.etag = this.etag;
            entry.serverDate = this.serverDate;
            entry.ttl = this.ttl;
            entry.softTtl = this.softTtl;
            entry.responseHeaders = this.responseHeaders;
            return entry;
        }
        
        public boolean writeHeader(final OutputStream outputStream) {
            try {
                final ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeByte(2);
                objectOutputStream.writeUTF(this.key);
                String etag;
                if (this.etag == null) {
                    etag = "";
                }
                else {
                    etag = this.etag;
                }
                objectOutputStream.writeUTF(etag);
                objectOutputStream.writeLong(this.serverDate);
                objectOutputStream.writeLong(this.ttl);
                objectOutputStream.writeLong(this.softTtl);
                writeStringStringMap(this.responseHeaders, objectOutputStream);
                objectOutputStream.flush();
                return true;
            }
            catch (IOException ex) {
                VolleyLog.d("%s", ex.toString());
                return false;
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
