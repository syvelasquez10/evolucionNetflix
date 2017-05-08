// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.cache.common.WriterCallback;
import com.facebook.common.statfs.StatFsHelper$StorageType;
import com.facebook.common.util.SecureHashUtil;
import java.util.Comparator;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;
import com.facebook.cache.common.MultiCacheKey;
import java.util.Collection;
import com.facebook.cache.common.CacheEvent;
import com.facebook.cache.common.CacheEventListener$EvictionReason;
import com.facebook.binaryresource.BinaryResource;
import java.util.Iterator;
import java.io.IOException;
import com.facebook.cache.common.CacheErrorLogger$CacheErrorCategory;
import java.util.HashMap;
import com.facebook.common.time.SystemClock;
import com.facebook.common.disk.DiskTrimmableRegistry;
import java.util.concurrent.TimeUnit;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.cache.common.CacheKey;
import java.util.Map;
import com.facebook.common.time.Clock;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.common.disk.DiskTrimmable;

public class DiskStorageCache implements FileCache, DiskTrimmable
{
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS;
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS;
    private static final Class<?> TAG;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private long mCacheSizeLastUpdateTime;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final DiskStorageCache$CacheStats mCacheStats;
    private final Clock mClock;
    private final long mDefaultCacheSizeLimit;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    final Map<CacheKey, String> mIndex;
    private final Object mLock;
    private final long mLowDiskSpaceCacheSizeLimit;
    private final StatFsHelper mStatFsHelper;
    private final DiskStorage mStorage;
    
    static {
        TAG = DiskStorageCache.class;
        FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2L);
        FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30L);
    }
    
    public DiskStorageCache(final DiskStorage mStorage, final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier, final DiskStorageCache$Params diskStorageCache$Params, final CacheEventListener mCacheEventListener, final CacheErrorLogger mCacheErrorLogger, final DiskTrimmableRegistry diskTrimmableRegistry) {
        this.mLock = new Object();
        this.mLowDiskSpaceCacheSizeLimit = diskStorageCache$Params.mLowDiskSpaceCacheSizeLimit;
        this.mDefaultCacheSizeLimit = diskStorageCache$Params.mDefaultCacheSizeLimit;
        this.mCacheSizeLimit = diskStorageCache$Params.mDefaultCacheSizeLimit;
        this.mStatFsHelper = StatFsHelper.getInstance();
        this.mStorage = mStorage;
        this.mEntryEvictionComparatorSupplier = mEntryEvictionComparatorSupplier;
        this.mCacheSizeLastUpdateTime = -1L;
        this.mCacheEventListener = mCacheEventListener;
        this.mCacheSizeLimitMinimum = diskStorageCache$Params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = mCacheErrorLogger;
        this.mCacheStats = new DiskStorageCache$CacheStats();
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        this.mClock = SystemClock.get();
        this.mIndex = new HashMap<CacheKey, String>();
    }
    
    private void calcFileCacheSize() {
        while (true) {
            int n = 0;
            int n2 = 0;
            int n3 = 0;
            long max = -1L;
            final long now = this.mClock.now();
            final long future_TIMESTAMP_THRESHOLD_MS = DiskStorageCache.FUTURE_TIMESTAMP_THRESHOLD_MS;
            while (true) {
                int n5 = 0;
                int n8 = 0;
                int n9 = 0;
                int n10 = 0;
                Label_0272: {
                    try {
                        final Iterator<DiskStorage$Entry> iterator = this.mStorage.getEntries().iterator();
                        long n4 = 0L;
                        n5 = 0;
                        if (!iterator.hasNext()) {
                            if (n != 0) {
                                this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.READ_INVALID_ENTRY, DiskStorageCache.TAG, "Future timestamp found in " + n2 + " files , with a total size of " + n3 + " bytes, and a maximum time delta of " + max + "ms", null);
                            }
                            this.mCacheStats.set(n4, n5);
                            return;
                        }
                        final DiskStorage$Entry diskStorage$Entry = iterator.next();
                        n4 += diskStorage$Entry.getSize();
                        if (diskStorage$Entry.getTimestamp() > now + future_TIMESTAMP_THRESHOLD_MS) {
                            final int n6 = (int)(n3 + diskStorage$Entry.getSize());
                            max = Math.max(diskStorage$Entry.getTimestamp() - now, max);
                            final int n7 = n2 + 1;
                            n8 = 1;
                            n9 = n6;
                            n10 = n7;
                            break Label_0272;
                        }
                    }
                    catch (IOException ex) {
                        this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.GENERIC_IO, DiskStorageCache.TAG, "calcFileCacheSize: " + ex.getMessage(), ex);
                        return;
                    }
                    final int n11 = n3;
                    n8 = n;
                    n10 = n2;
                    n9 = n11;
                }
                final int n12 = n8;
                ++n5;
                n3 = n9;
                n2 = n10;
                n = n12;
                continue;
            }
        }
    }
    
    private BinaryResource endInsert(final DiskStorage$Inserter diskStorage$Inserter, final CacheKey cacheKey, final String s) {
        synchronized (this.mLock) {
            final BinaryResource commit = diskStorage$Inserter.commit(cacheKey);
            this.mCacheStats.increment(commit.size(), 1L);
            this.mIndex.put(cacheKey, s);
            return commit;
        }
    }
    
    private void evictAboveSize(final long cacheLimit, final CacheEventListener$EvictionReason evictionReason) {
        while (true) {
            while (true) {
                long size = 0L;
                int n = 0;
                long n2 = 0L;
                DiskStorage$Entry diskStorage$Entry = null;
                Label_0137: {
                    try {
                        final Collection<DiskStorage$Entry> sortedEntries = this.getSortedEntries(this.mStorage.getEntries());
                        size = this.mCacheStats.getSize();
                        final Iterator<DiskStorage$Entry> iterator = sortedEntries.iterator();
                        n = 0;
                        n2 = 0L;
                        if (iterator.hasNext()) {
                            diskStorage$Entry = iterator.next();
                            if (n2 <= size - cacheLimit) {
                                break Label_0137;
                            }
                        }
                        this.mCacheStats.increment(-n2, -n);
                        this.mStorage.purgeUnexpectedResources();
                        return;
                    }
                    catch (IOException ex) {
                        this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.EVICTION, DiskStorageCache.TAG, "evictAboveSize: " + ex.getMessage(), ex);
                        throw ex;
                    }
                }
                final long remove = this.mStorage.remove(diskStorage$Entry);
                this.mIndex.values().remove(diskStorage$Entry.getId());
                int n3 = n;
                long n4 = n2;
                if (remove > 0L) {
                    n3 = n + 1;
                    n4 = n2 + remove;
                    this.mCacheEventListener.onEviction(new SettableCacheEvent().setResourceId(diskStorage$Entry.getId()).setEvictionReason(evictionReason).setItemSize(remove).setCacheSize(size - n4).setCacheLimit(cacheLimit));
                }
                n = n3;
                n2 = n4;
                continue;
            }
        }
    }
    
    static String getFirstResourceId(final CacheKey cacheKey) {
        try {
            if (cacheKey instanceof MultiCacheKey) {
                return secureHashKey(((MultiCacheKey)cacheKey).getCacheKeys().get(0));
            }
            return secureHashKey(cacheKey);
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    private static List<String> getResourceIds(final CacheKey cacheKey) {
        ArrayList list2;
        try {
            if (!(cacheKey instanceof MultiCacheKey)) {
                final ArrayList<String> list = new ArrayList<String>(1);
                list.add(secureHashKey(cacheKey));
                return list;
            }
            final List<CacheKey> cacheKeys = ((MultiCacheKey)cacheKey).getCacheKeys();
            list2 = new ArrayList<String>(cacheKeys.size());
            for (int i = 0; i < cacheKeys.size(); ++i) {
                list2.add(secureHashKey(cacheKeys.get(i)));
            }
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
        return (List<String>)list2;
    }
    
    private Collection<DiskStorage$Entry> getSortedEntries(final Collection<DiskStorage$Entry> collection) {
        final long now = this.mClock.now();
        final long future_TIMESTAMP_THRESHOLD_MS = DiskStorageCache.FUTURE_TIMESTAMP_THRESHOLD_MS;
        final ArrayList<DiskStorage$Entry> list = new ArrayList<DiskStorage$Entry>(collection.size());
        final ArrayList<DiskStorage$Entry> list2 = new ArrayList<DiskStorage$Entry>(collection.size());
        for (final DiskStorage$Entry diskStorage$Entry : collection) {
            if (diskStorage$Entry.getTimestamp() > future_TIMESTAMP_THRESHOLD_MS + now) {
                list.add(diskStorage$Entry);
            }
            else {
                list2.add(diskStorage$Entry);
            }
        }
        Collections.sort((List<Object>)list2, (Comparator<? super Object>)this.mEntryEvictionComparatorSupplier.get());
        list.addAll(list2);
        return list;
    }
    
    private void maybeEvictFilesInCacheDir() {
        synchronized (this.mLock) {
            final boolean maybeUpdateFileCacheSize = this.maybeUpdateFileCacheSize();
            this.updateFileCacheSizeLimit();
            final long size = this.mCacheStats.getSize();
            if (size > this.mCacheSizeLimit && !maybeUpdateFileCacheSize) {
                this.mCacheStats.reset();
                this.maybeUpdateFileCacheSize();
            }
            if (size > this.mCacheSizeLimit) {
                this.evictAboveSize(this.mCacheSizeLimit * 9L / 10L, CacheEventListener$EvictionReason.CACHE_FULL);
            }
        }
    }
    
    private boolean maybeUpdateFileCacheSize() {
        boolean b = false;
        final long now = this.mClock.now();
        if (!this.mCacheStats.isInitialized() || this.mCacheSizeLastUpdateTime == -1L || now - this.mCacheSizeLastUpdateTime > DiskStorageCache.FILECACHE_SIZE_UPDATE_PERIOD_MS) {
            this.calcFileCacheSize();
            this.mCacheSizeLastUpdateTime = now;
            b = true;
        }
        return b;
    }
    
    private static String secureHashKey(final CacheKey cacheKey) {
        return SecureHashUtil.makeSHA1HashBase64(cacheKey.toString().getBytes("UTF-8"));
    }
    
    private DiskStorage$Inserter startInsert(final String s, final CacheKey cacheKey) {
        this.maybeEvictFilesInCacheDir();
        return this.mStorage.insert(s, cacheKey);
    }
    
    private void updateFileCacheSizeLimit() {
        if (this.mStatFsHelper.testLowDiskSpace(StatFsHelper$StorageType.INTERNAL, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize())) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
            return;
        }
        this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
    }
    
    @Override
    public void clearAll() {
        synchronized (this.mLock) {
            while (true) {
                try {
                    this.mStorage.clearAll();
                    this.mIndex.clear();
                    this.mCacheStats.reset();
                }
                catch (IOException ex) {
                    this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.EVICTION, DiskStorageCache.TAG, "clearAll: " + ex.getMessage(), ex);
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public BinaryResource getResource(final CacheKey cacheKey) {
        while (true) {
            final SettableCacheEvent setCacheKey = new SettableCacheEvent().setCacheKey(cacheKey);
            while (true) {
                int n = 0;
                Label_0240: {
                    try {
                        synchronized (this.mLock) {
                            String s;
                            BinaryResource binaryResource;
                            if (this.mIndex.containsKey(cacheKey)) {
                                s = this.mIndex.get(cacheKey);
                                setCacheKey.setResourceId(s);
                                binaryResource = this.mStorage.getResource(s, cacheKey);
                            }
                            else {
                                final List<String> resourceIds = getResourceIds(cacheKey);
                                n = 0;
                                s = null;
                                binaryResource = null;
                                if (n < resourceIds.size()) {
                                    s = resourceIds.get(n);
                                    setCacheKey.setResourceId(s);
                                    binaryResource = this.mStorage.getResource(s, cacheKey);
                                    if (binaryResource == null) {
                                        break Label_0240;
                                    }
                                }
                            }
                            if (binaryResource == null) {
                                this.mCacheEventListener.onMiss(setCacheKey);
                                this.mIndex.remove(cacheKey);
                            }
                            else {
                                this.mCacheEventListener.onHit(setCacheKey);
                                this.mIndex.put(cacheKey, s);
                            }
                            return binaryResource;
                        }
                    }
                    catch (IOException exception) {
                        this.mCacheErrorLogger.logError(CacheErrorLogger$CacheErrorCategory.GENERIC_IO, DiskStorageCache.TAG, "getResource", exception);
                        setCacheKey.setException(exception);
                        this.mCacheEventListener.onReadException(setCacheKey);
                        return null;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    @Override
    public boolean hasKey(final CacheKey p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/facebook/cache/disk/DiskStorageCache.mLock:Ljava/lang/Object;
        //     4: astore          5
        //     6: aload           5
        //     8: monitorenter   
        //     9: aload_0        
        //    10: aload_1        
        //    11: invokevirtual   com/facebook/cache/disk/DiskStorageCache.hasKeySync:(Lcom/facebook/cache/common/CacheKey;)Z
        //    14: ifeq            22
        //    17: aload           5
        //    19: monitorexit    
        //    20: iconst_1       
        //    21: ireturn        
        //    22: aconst_null    
        //    23: astore          4
        //    25: aload_0        
        //    26: getfield        com/facebook/cache/disk/DiskStorageCache.mIndex:Ljava/util/Map;
        //    29: aload_1        
        //    30: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    35: ifeq            94
        //    38: aload_0        
        //    39: getfield        com/facebook/cache/disk/DiskStorageCache.mIndex:Ljava/util/Map;
        //    42: aload_1        
        //    43: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    48: checkcast       Ljava/lang/String;
        //    51: astore          4
        //    53: aload_0        
        //    54: getfield        com/facebook/cache/disk/DiskStorageCache.mStorage:Lcom/facebook/cache/disk/DiskStorage;
        //    57: aload           4
        //    59: aload_1        
        //    60: invokeinterface com/facebook/cache/disk/DiskStorage.contains:(Ljava/lang/String;Ljava/lang/Object;)Z
        //    65: istore_3       
        //    66: iload_3        
        //    67: ifeq            148
        //    70: aload_0        
        //    71: getfield        com/facebook/cache/disk/DiskStorageCache.mIndex:Ljava/util/Map;
        //    74: aload_1        
        //    75: aload           4
        //    77: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    82: pop            
        //    83: aload           5
        //    85: monitorexit    
        //    86: iload_3        
        //    87: ireturn        
        //    88: astore_1       
        //    89: aload           5
        //    91: monitorexit    
        //    92: aload_1        
        //    93: athrow         
        //    94: aload_1        
        //    95: invokestatic    com/facebook/cache/disk/DiskStorageCache.getResourceIds:(Lcom/facebook/cache/common/CacheKey;)Ljava/util/List;
        //    98: astore          6
        //   100: iconst_0       
        //   101: istore_2       
        //   102: iconst_0       
        //   103: istore_3       
        //   104: iload_2        
        //   105: aload           6
        //   107: invokeinterface java/util/List.size:()I
        //   112: if_icmpge       66
        //   115: aload           6
        //   117: iload_2        
        //   118: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   123: checkcast       Ljava/lang/String;
        //   126: astore          4
        //   128: aload_0        
        //   129: getfield        com/facebook/cache/disk/DiskStorageCache.mStorage:Lcom/facebook/cache/disk/DiskStorage;
        //   132: aload           4
        //   134: aload_1        
        //   135: invokeinterface com/facebook/cache/disk/DiskStorage.contains:(Ljava/lang/String;Ljava/lang/Object;)Z
        //   140: istore_3       
        //   141: iload_3        
        //   142: ifeq            168
        //   145: goto            66
        //   148: aload_0        
        //   149: getfield        com/facebook/cache/disk/DiskStorageCache.mIndex:Ljava/util/Map;
        //   152: aload_1        
        //   153: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   158: pop            
        //   159: goto            83
        //   162: astore_1       
        //   163: aload           5
        //   165: monitorexit    
        //   166: iconst_0       
        //   167: ireturn        
        //   168: iload_2        
        //   169: iconst_1       
        //   170: iadd           
        //   171: istore_2       
        //   172: goto            104
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      20     88     94     Any
        //  25     66     162    168    Ljava/io/IOException;
        //  25     66     88     94     Any
        //  70     83     162    168    Ljava/io/IOException;
        //  70     83     88     94     Any
        //  83     86     88     94     Any
        //  89     92     88     94     Any
        //  94     100    162    168    Ljava/io/IOException;
        //  94     100    88     94     Any
        //  104    141    162    168    Ljava/io/IOException;
        //  104    141    88     94     Any
        //  148    159    162    168    Ljava/io/IOException;
        //  148    159    88     94     Any
        //  163    166    88     94     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0066:
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
    public boolean hasKeySync(final CacheKey cacheKey) {
        synchronized (this.mLock) {
            return this.mIndex.containsKey(cacheKey);
        }
    }
    
    @Override
    public BinaryResource insert(final CacheKey p0, final WriterCallback p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/facebook/cache/disk/SettableCacheEvent;
        //     3: dup            
        //     4: invokespecial   com/facebook/cache/disk/SettableCacheEvent.<init>:()V
        //     7: aload_1        
        //     8: invokevirtual   com/facebook/cache/disk/SettableCacheEvent.setCacheKey:(Lcom/facebook/cache/common/CacheKey;)Lcom/facebook/cache/disk/SettableCacheEvent;
        //    11: astore          4
        //    13: aload_0        
        //    14: getfield        com/facebook/cache/disk/DiskStorageCache.mCacheEventListener:Lcom/facebook/cache/common/CacheEventListener;
        //    17: aload           4
        //    19: invokeinterface com/facebook/cache/common/CacheEventListener.onWriteAttempt:(Lcom/facebook/cache/common/CacheEvent;)V
        //    24: aload_0        
        //    25: getfield        com/facebook/cache/disk/DiskStorageCache.mLock:Ljava/lang/Object;
        //    28: astore          5
        //    30: aload           5
        //    32: monitorenter   
        //    33: aload_0        
        //    34: getfield        com/facebook/cache/disk/DiskStorageCache.mIndex:Ljava/util/Map;
        //    37: aload_1        
        //    38: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //    43: ifeq            150
        //    46: aload_0        
        //    47: getfield        com/facebook/cache/disk/DiskStorageCache.mIndex:Ljava/util/Map;
        //    50: aload_1        
        //    51: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    56: checkcast       Ljava/lang/String;
        //    59: astore_3       
        //    60: aload           5
        //    62: monitorexit    
        //    63: aload           4
        //    65: aload_3        
        //    66: invokevirtual   com/facebook/cache/disk/SettableCacheEvent.setResourceId:(Ljava/lang/String;)Lcom/facebook/cache/disk/SettableCacheEvent;
        //    69: pop            
        //    70: aload_0        
        //    71: aload_3        
        //    72: aload_1        
        //    73: invokespecial   com/facebook/cache/disk/DiskStorageCache.startInsert:(Ljava/lang/String;Lcom/facebook/cache/common/CacheKey;)Lcom/facebook/cache/disk/DiskStorage$Inserter;
        //    76: astore          5
        //    78: aload           5
        //    80: aload_2        
        //    81: aload_1        
        //    82: invokeinterface com/facebook/cache/disk/DiskStorage$Inserter.writeData:(Lcom/facebook/cache/common/WriterCallback;Ljava/lang/Object;)V
        //    87: aload_0        
        //    88: aload           5
        //    90: aload_1        
        //    91: aload_3        
        //    92: invokespecial   com/facebook/cache/disk/DiskStorageCache.endInsert:(Lcom/facebook/cache/disk/DiskStorage$Inserter;Lcom/facebook/cache/common/CacheKey;Ljava/lang/String;)Lcom/facebook/binaryresource/BinaryResource;
        //    95: astore_1       
        //    96: aload           4
        //    98: aload_1        
        //    99: invokeinterface com/facebook/binaryresource/BinaryResource.size:()J
        //   104: invokevirtual   com/facebook/cache/disk/SettableCacheEvent.setItemSize:(J)Lcom/facebook/cache/disk/SettableCacheEvent;
        //   107: aload_0        
        //   108: getfield        com/facebook/cache/disk/DiskStorageCache.mCacheStats:Lcom/facebook/cache/disk/DiskStorageCache$CacheStats;
        //   111: invokevirtual   com/facebook/cache/disk/DiskStorageCache$CacheStats.getSize:()J
        //   114: invokevirtual   com/facebook/cache/disk/SettableCacheEvent.setCacheSize:(J)Lcom/facebook/cache/disk/SettableCacheEvent;
        //   117: pop            
        //   118: aload_0        
        //   119: getfield        com/facebook/cache/disk/DiskStorageCache.mCacheEventListener:Lcom/facebook/cache/common/CacheEventListener;
        //   122: aload           4
        //   124: invokeinterface com/facebook/cache/common/CacheEventListener.onWriteSuccess:(Lcom/facebook/cache/common/CacheEvent;)V
        //   129: aload           5
        //   131: invokeinterface com/facebook/cache/disk/DiskStorage$Inserter.cleanUp:()Z
        //   136: ifne            148
        //   139: getstatic       com/facebook/cache/disk/DiskStorageCache.TAG:Ljava/lang/Class;
        //   142: ldc_w           "Failed to delete temp file"
        //   145: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/Class;Ljava/lang/String;)V
        //   148: aload_1        
        //   149: areturn        
        //   150: aload_1        
        //   151: invokestatic    com/facebook/cache/disk/DiskStorageCache.getFirstResourceId:(Lcom/facebook/cache/common/CacheKey;)Ljava/lang/String;
        //   154: astore_3       
        //   155: goto            60
        //   158: astore_1       
        //   159: aload           5
        //   161: monitorexit    
        //   162: aload_1        
        //   163: athrow         
        //   164: astore_1       
        //   165: aload           5
        //   167: invokeinterface com/facebook/cache/disk/DiskStorage$Inserter.cleanUp:()Z
        //   172: ifne            184
        //   175: getstatic       com/facebook/cache/disk/DiskStorageCache.TAG:Ljava/lang/Class;
        //   178: ldc_w           "Failed to delete temp file"
        //   181: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/Class;Ljava/lang/String;)V
        //   184: aload_1        
        //   185: athrow         
        //   186: astore_1       
        //   187: aload           4
        //   189: aload_1        
        //   190: invokevirtual   com/facebook/cache/disk/SettableCacheEvent.setException:(Ljava/io/IOException;)Lcom/facebook/cache/disk/SettableCacheEvent;
        //   193: pop            
        //   194: aload_0        
        //   195: getfield        com/facebook/cache/disk/DiskStorageCache.mCacheEventListener:Lcom/facebook/cache/common/CacheEventListener;
        //   198: aload           4
        //   200: invokeinterface com/facebook/cache/common/CacheEventListener.onWriteException:(Lcom/facebook/cache/common/CacheEvent;)V
        //   205: getstatic       com/facebook/cache/disk/DiskStorageCache.TAG:Ljava/lang/Class;
        //   208: ldc_w           "Failed inserting a file into the cache"
        //   211: aload_1        
        //   212: invokestatic    com/facebook/common/logging/FLog.e:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   215: aload_1        
        //   216: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  33     60     158    164    Any
        //  60     63     158    164    Any
        //  70     78     186    217    Ljava/io/IOException;
        //  78     129    164    186    Any
        //  129    148    186    217    Ljava/io/IOException;
        //  150    155    158    164    Any
        //  159    162    158    164    Any
        //  165    184    186    217    Ljava/io/IOException;
        //  184    186    186    217    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
}
