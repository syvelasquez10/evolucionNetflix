// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.concurrent.ThreadPoolExecutor;
import io.realm.internal.async.RealmAsyncTaskImpl;
import java.util.List;
import java.util.Collections;
import io.realm.internal.ColumnInfo;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import java.util.ArrayList;
import io.realm.log.Logger;
import io.realm.log.AndroidLogger;
import io.realm.internal.RealmCore;
import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import io.realm.exceptions.RealmException;
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.RealmObjectProxy;
import java.util.Arrays;
import io.realm.log.RealmLog;
import io.realm.internal.ObjectServerFacade;
import io.realm.internal.Table;
import io.realm.internal.SharedRealm;
import java.util.HashMap;
import io.realm.internal.ColumnIndices;
import java.util.EnumMap;
import java.util.Map;

final class RealmCache
{
    private static Map<String, RealmCache> cachesMap;
    private final RealmConfiguration configuration;
    private final EnumMap<RealmCache$RealmCacheType, RealmCache$RefAndCount> refAndCountMap;
    private final ColumnIndices[] typedColumnIndicesArray;
    
    static {
        RealmCache.cachesMap = new HashMap<String, RealmCache>();
    }
    
    private RealmCache(final RealmConfiguration configuration) {
        this.typedColumnIndicesArray = new ColumnIndices[4];
        this.configuration = configuration;
        this.refAndCountMap = new EnumMap<RealmCache$RealmCacheType, RealmCache$RefAndCount>(RealmCache$RealmCacheType.class);
        final RealmCache$RealmCacheType[] values = RealmCache$RealmCacheType.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            this.refAndCountMap.put(values[i], new RealmCache$RefAndCount(null));
        }
    }
    
    private static void copyAssetFileIfNeeded(final RealmConfiguration p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_0        
        //     4: invokevirtual   io/realm/RealmConfiguration.hasAssetFile:()Z
        //     7: ifeq            33
        //    10: new             Ljava/io/File;
        //    13: dup            
        //    14: aload_0        
        //    15: invokevirtual   io/realm/RealmConfiguration.getRealmDirectory:()Ljava/io/File;
        //    18: aload_0        
        //    19: invokevirtual   io/realm/RealmConfiguration.getRealmFileName:()Ljava/lang/String;
        //    22: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    25: astore_2       
        //    26: aload_2        
        //    27: invokevirtual   java/io/File.exists:()Z
        //    30: ifeq            34
        //    33: return         
        //    34: aload_0        
        //    35: invokevirtual   io/realm/RealmConfiguration.getAssetFile:()Ljava/io/InputStream;
        //    38: astore_0       
        //    39: aload_0        
        //    40: ifnonnull       106
        //    43: new             Lio/realm/exceptions/RealmFileException;
        //    46: dup            
        //    47: getstatic       io/realm/exceptions/RealmFileException$Kind.ACCESS_ERROR:Lio/realm/exceptions/RealmFileException$Kind;
        //    50: ldc             "Invalid input stream to asset file."
        //    52: invokespecial   io/realm/exceptions/RealmFileException.<init>:(Lio/realm/exceptions/RealmFileException$Kind;Ljava/lang/String;)V
        //    55: athrow         
        //    56: astore_3       
        //    57: aconst_null    
        //    58: astore_2       
        //    59: new             Lio/realm/exceptions/RealmFileException;
        //    62: dup            
        //    63: getstatic       io/realm/exceptions/RealmFileException$Kind.ACCESS_ERROR:Lio/realm/exceptions/RealmFileException$Kind;
        //    66: ldc             "Could not resolve the path to the Realm asset file."
        //    68: aload_3        
        //    69: invokespecial   io/realm/exceptions/RealmFileException.<init>:(Lio/realm/exceptions/RealmFileException$Kind;Ljava/lang/String;Ljava/lang/Throwable;)V
        //    72: athrow         
        //    73: astore          4
        //    75: aload_0        
        //    76: astore_3       
        //    77: aload           4
        //    79: astore_0       
        //    80: aload           5
        //    82: astore          4
        //    84: aload_3        
        //    85: ifnull          96
        //    88: aload_3        
        //    89: invokevirtual   java/io/InputStream.close:()V
        //    92: aload           5
        //    94: astore          4
        //    96: aload_2        
        //    97: ifnull          104
        //   100: aload_2        
        //   101: invokevirtual   java/io/FileOutputStream.close:()V
        //   104: aload_0        
        //   105: athrow         
        //   106: new             Ljava/io/FileOutputStream;
        //   109: dup            
        //   110: aload_2        
        //   111: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   114: astore_2       
        //   115: sipush          4096
        //   118: newarray        B
        //   120: astore_3       
        //   121: aload_0        
        //   122: aload_3        
        //   123: invokevirtual   java/io/InputStream.read:([B)I
        //   126: istore_1       
        //   127: iload_1        
        //   128: iconst_m1      
        //   129: if_icmple       146
        //   132: aload_2        
        //   133: aload_3        
        //   134: iconst_0       
        //   135: iload_1        
        //   136: invokevirtual   java/io/FileOutputStream.write:([BII)V
        //   139: goto            121
        //   142: astore_3       
        //   143: goto            59
        //   146: aload_0        
        //   147: ifnull          257
        //   150: aload_0        
        //   151: invokevirtual   java/io/InputStream.close:()V
        //   154: aconst_null    
        //   155: astore_0       
        //   156: aload_0        
        //   157: astore_3       
        //   158: aload_2        
        //   159: ifnull          168
        //   162: aload_2        
        //   163: invokevirtual   java/io/FileOutputStream.close:()V
        //   166: aload_0        
        //   167: astore_3       
        //   168: aload_3        
        //   169: ifnull          33
        //   172: new             Lio/realm/exceptions/RealmFileException;
        //   175: dup            
        //   176: getstatic       io/realm/exceptions/RealmFileException$Kind.ACCESS_ERROR:Lio/realm/exceptions/RealmFileException$Kind;
        //   179: aload_3        
        //   180: invokespecial   io/realm/exceptions/RealmFileException.<init>:(Lio/realm/exceptions/RealmFileException$Kind;Ljava/lang/Throwable;)V
        //   183: athrow         
        //   184: astore_0       
        //   185: goto            156
        //   188: astore_2       
        //   189: aload_0        
        //   190: ifnonnull       254
        //   193: aload_2        
        //   194: astore_0       
        //   195: aload_0        
        //   196: astore_3       
        //   197: goto            168
        //   200: astore_2       
        //   201: aload           4
        //   203: ifnonnull       104
        //   206: goto            104
        //   209: astore          4
        //   211: goto            96
        //   214: astore_0       
        //   215: aconst_null    
        //   216: astore_2       
        //   217: aconst_null    
        //   218: astore_3       
        //   219: goto            80
        //   222: astore_2       
        //   223: aload_0        
        //   224: astore_3       
        //   225: aconst_null    
        //   226: astore          4
        //   228: aload_2        
        //   229: astore_0       
        //   230: aload           4
        //   232: astore_2       
        //   233: goto            80
        //   236: astore          4
        //   238: aload_0        
        //   239: astore_3       
        //   240: aload           4
        //   242: astore_0       
        //   243: goto            80
        //   246: astore_3       
        //   247: aconst_null    
        //   248: astore_2       
        //   249: aconst_null    
        //   250: astore_0       
        //   251: goto            59
        //   254: goto            195
        //   257: aconst_null    
        //   258: astore_0       
        //   259: goto            156
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  34     39     246    254    Ljava/io/IOException;
        //  34     39     214    222    Any
        //  43     56     56     59     Ljava/io/IOException;
        //  43     56     222    236    Any
        //  59     73     73     80     Any
        //  88     92     209    214    Ljava/io/IOException;
        //  100    104    200    209    Ljava/io/IOException;
        //  106    115    56     59     Ljava/io/IOException;
        //  106    115    222    236    Any
        //  115    121    142    146    Ljava/io/IOException;
        //  115    121    236    246    Any
        //  121    127    142    146    Ljava/io/IOException;
        //  121    127    236    246    Any
        //  132    139    142    146    Ljava/io/IOException;
        //  132    139    236    246    Any
        //  150    154    184    188    Ljava/io/IOException;
        //  162    166    188    200    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
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
    
    static <E extends BaseRealm> E createRealmOrGetFromCache(final RealmConfiguration realmConfiguration, Class<E> baseRealm) {
        while (true) {
            while (true) {
                Label_0316: {
                    synchronized (RealmCache.class) {
                        RealmCache realmCache = RealmCache.cachesMap.get(realmConfiguration.getPath());
                        int n;
                        if (realmCache == null) {
                            realmCache = new RealmCache(realmConfiguration);
                            copyAssetFileIfNeeded(realmConfiguration);
                            n = 0;
                        }
                        else {
                            realmCache.validateConfiguration(realmConfiguration);
                            n = 1;
                        }
                        final RealmCache$RefAndCount realmCache$RefAndCount = realmCache.refAndCountMap.get(RealmCache$RealmCacheType.valueOf((Class<? extends BaseRealm>)baseRealm));
                        if (realmCache$RefAndCount.globalCount == 0) {
                            final SharedRealm instance = SharedRealm.getInstance(realmConfiguration);
                            if (Table.primaryKeyTableNeedsMigration(instance)) {
                                instance.beginTransaction();
                                if (Table.migratePrimaryKeyTableIfNeeded(instance)) {
                                    instance.commitTransaction();
                                }
                                else {
                                    instance.cancelTransaction();
                                }
                            }
                            instance.close();
                        }
                        if (realmCache$RefAndCount.localRealm.get() == null) {
                            if (baseRealm != Realm.class) {
                                break Label_0316;
                            }
                            final BaseRealm baseRealm2 = Realm.createInstance(realmConfiguration, realmCache.typedColumnIndicesArray);
                            if (n == 0) {
                                RealmCache.cachesMap.put(realmConfiguration.getPath(), realmCache);
                            }
                            realmCache$RefAndCount.localRealm.set(baseRealm2);
                            realmCache$RefAndCount.localCount.set(0);
                        }
                        final Integer n2 = realmCache$RefAndCount.localCount.get();
                        if (n2 == 0) {
                            if (baseRealm == Realm.class && realmCache$RefAndCount.globalCount == 0) {
                                baseRealm = (BaseRealm)realmCache$RefAndCount.localRealm.get();
                                storeColumnIndices(realmCache.typedColumnIndicesArray, baseRealm.schema.columnIndices.clone());
                            }
                            realmCache$RefAndCount.globalCount++;
                        }
                        realmCache$RefAndCount.localCount.set(n2 + 1);
                        baseRealm = (E)realmCache$RefAndCount.localRealm.get();
                        if (realmCache$RefAndCount.globalCount == 1) {
                            ObjectServerFacade.getFacade(realmConfiguration.isSyncConfiguration()).realmOpened(realmConfiguration);
                        }
                        return (E)baseRealm;
                    }
                }
                if (baseRealm == DynamicRealm.class) {
                    final BaseRealm baseRealm2 = DynamicRealm.createInstance(realmConfiguration);
                    continue;
                }
                break;
            }
            break;
        }
        throw new IllegalArgumentException("The type of Realm class must be Realm or DynamicRealm.");
    }
    
    public static ColumnIndices findColumnIndices(final ColumnIndices[] array, final long n) {
        for (int i = array.length - 1; i >= 0; --i) {
            final ColumnIndices columnIndices = array[i];
            if (columnIndices != null && columnIndices.getSchemaVersion() == n) {
                return columnIndices;
            }
        }
        return null;
    }
    
    static void invokeWithGlobalRefCount(final RealmConfiguration realmConfiguration, final RealmCache$Callback realmCache$Callback) {
        synchronized (RealmCache.class) {
            final RealmCache realmCache = RealmCache.cachesMap.get(realmConfiguration.getPath());
            if (realmCache == null) {
                realmCache$Callback.onResult(0);
            }
            else {
                final RealmCache$RealmCacheType[] values = RealmCache$RealmCacheType.values();
                final int length = values.length;
                int i = 0;
                int n = 0;
                while (i < length) {
                    n += realmCache.refAndCountMap.get(values[i]).globalCount;
                    ++i;
                }
                realmCache$Callback.onResult(n);
            }
        }
    }
    
    static void release(final BaseRealm baseRealm) {
        while (true) {
            int n = 0;
            Object o = null;
            while (true) {
                Label_0326: {
                    RealmCache$RefAndCount realmCache$RefAndCount = null;
                    Label_0313: {
                        final String path;
                        final RealmCache realmCache;
                        synchronized (RealmCache.class) {
                            path = baseRealm.getPath();
                            realmCache = RealmCache.cachesMap.get(path);
                            if (realmCache == null) {
                                break Label_0326;
                            }
                            realmCache$RefAndCount = realmCache.refAndCountMap.get(RealmCache$RealmCacheType.valueOf(baseRealm.getClass()));
                            o = realmCache$RefAndCount.localCount.get();
                            Object value = o;
                            if (o == null) {
                                value = 0;
                            }
                            if ((int)value <= 0) {
                                RealmLog.warn("%s has been closed already.", new Object[] { path });
                                return;
                            }
                            o = (int)value - 1;
                            if ((int)o != 0) {
                                break Label_0313;
                            }
                            realmCache$RefAndCount.localCount.set(null);
                            realmCache$RefAndCount.localRealm.set(null);
                            realmCache$RefAndCount.globalCount--;
                            if (realmCache$RefAndCount.globalCount < 0) {
                                throw new IllegalStateException("Global reference counter of Realm" + path + " got corrupted.");
                            }
                        }
                        final BaseRealm baseRealm2;
                        if (baseRealm2 instanceof Realm && realmCache$RefAndCount.globalCount == 0) {
                            Arrays.fill(realmCache.typedColumnIndicesArray, null);
                        }
                        o = RealmCache$RealmCacheType.values();
                        for (int length = ((Integer)o).length, i = 0; i < length; ++i) {
                            realmCache$RefAndCount = o[i];
                            n += realmCache.refAndCountMap.get(realmCache$RefAndCount).globalCount;
                        }
                        baseRealm2.doClose();
                        if (n == 0) {
                            RealmCache.cachesMap.remove(path);
                            ObjectServerFacade.getFacade(baseRealm2.getConfiguration().isSyncConfiguration()).realmClosed(baseRealm2.getConfiguration());
                            return;
                        }
                        return;
                    }
                    realmCache$RefAndCount.localCount.set(o);
                    return;
                }
                RealmCache$RefAndCount realmCache$RefAndCount = null;
                continue;
            }
        }
    }
    
    private static int storeColumnIndices(final ColumnIndices[] array, final ColumnIndices columnIndices) {
        long n = Long.MAX_VALUE;
        int n2 = -1;
        long schemaVersion;
        for (int i = array.length - 1; i >= 0; --i, n = schemaVersion) {
            if (array[i] == null) {
                array[i] = columnIndices;
                return i;
            }
            final ColumnIndices columnIndices2 = array[i];
            schemaVersion = n;
            if (columnIndices2.getSchemaVersion() <= n) {
                schemaVersion = columnIndices2.getSchemaVersion();
                n2 = i;
            }
        }
        array[n2] = columnIndices;
        return n2;
    }
    
    static void updateSchemaCache(final Realm realm) {
        synchronized (RealmCache.class) {
            final RealmCache realmCache = RealmCache.cachesMap.get(realm.getPath());
            if (realmCache != null && realmCache.refAndCountMap.get(RealmCache$RealmCacheType.TYPED_REALM).localRealm.get() != null) {
                final ColumnIndices[] typedColumnIndicesArray = realmCache.typedColumnIndicesArray;
                final ColumnIndices updateSchemaCache = realm.updateSchemaCache(typedColumnIndicesArray);
                if (updateSchemaCache != null) {
                    storeColumnIndices(typedColumnIndicesArray, updateSchemaCache);
                }
            }
        }
    }
    
    private void validateConfiguration(final RealmConfiguration realmConfiguration) {
        if (this.configuration.equals(realmConfiguration)) {
            return;
        }
        if (!Arrays.equals(this.configuration.getEncryptionKey(), realmConfiguration.getEncryptionKey())) {
            throw new IllegalArgumentException("Wrong key used to decrypt Realm.");
        }
        final RealmMigration migration = realmConfiguration.getMigration();
        final RealmMigration migration2 = this.configuration.getMigration();
        if (migration2 != null && migration != null && migration2.getClass().equals(migration.getClass()) && !migration.equals(migration2)) {
            throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. The most likely cause is that equals() and hashCode() are not overridden in the migration class: " + realmConfiguration.getMigration().getClass().getCanonicalName());
        }
        throw new IllegalArgumentException("Configurations cannot be different if used to open the same file. \nCached configuration: \n" + this.configuration + "\n\nNew configuration: \n" + realmConfiguration);
    }
}
