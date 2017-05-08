// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.concurrent.ThreadPoolExecutor;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.log.RealmLog;
import io.realm.internal.Table;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.ColumnInfo;
import java.util.HashMap;
import java.util.ArrayList;
import io.realm.internal.SharedRealm;
import java.io.File;
import io.realm.internal.ObjectServerFacade;
import io.realm.internal.RealmCore;
import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import io.realm.exceptions.RealmException;
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnIndices;
import io.realm.internal.RealmObjectProxy;
import java.util.Map;

public class Realm extends BaseRealm
{
    private static RealmConfiguration defaultConfiguration;
    
    Realm(final RealmConfiguration realmConfiguration) {
        super(realmConfiguration);
    }
    
    private void checkHasPrimaryKey(final Class<? extends RealmModel> clazz) {
        if (!this.schema.getTable((Class)clazz).hasPrimaryKey()) {
            throw new IllegalArgumentException("A RealmObject with no @PrimaryKey cannot be updated: " + clazz.toString());
        }
    }
    
    private <E extends RealmModel> void checkNotNullObject(final E e) {
        if (e == null) {
            throw new IllegalArgumentException("Null objects cannot be copied into Realm.");
        }
    }
    
    private <E extends RealmModel> E copyOrUpdate(final E e, final boolean b, final Map<RealmModel, RealmObjectProxy> map) {
        this.checkIfValid();
        return (E)this.configuration.getSchemaMediator().copyOrUpdate(this, (RealmModel)e, b, (Map)map);
    }
    
    static Realm createAndValidate(final RealmConfiguration realmConfiguration, final ColumnIndices[] array) {
        final Realm realm = new Realm(realmConfiguration);
        final long version = realm.getVersion();
        final long schemaVersion = realmConfiguration.getSchemaVersion();
        final ColumnIndices columnIndices = RealmCache.findColumnIndices(array, schemaVersion);
        if (columnIndices != null) {
            realm.schema.columnIndices = columnIndices.clone();
            return realm;
        }
        final boolean syncConfiguration = realmConfiguration.isSyncConfiguration();
        if (!syncConfiguration && version != -1L) {
            if (version < schemaVersion) {
                realm.doClose();
                throw new RealmMigrationNeededException(realmConfiguration.getPath(), String.format("Realm on disk need to migrate from v%s to v%s", version, schemaVersion));
            }
            if (schemaVersion < version) {
                realm.doClose();
                throw new IllegalArgumentException(String.format("Realm on disk is newer than the one specified: v%s vs. v%s", version, schemaVersion));
            }
        }
        if (!syncConfiguration) {
            try {
                initializeRealm(realm);
                return realm;
            }
            catch (RuntimeException ex) {
                realm.doClose();
                throw ex;
            }
        }
        initializeSyncedRealm(realm);
        return realm;
    }
    
    static Realm createInstance(final RealmConfiguration realmConfiguration, final ColumnIndices[] array) {
        try {
            return createAndValidate(realmConfiguration, array);
        }
        catch (RealmMigrationNeededException ex) {
            if (realmConfiguration.shouldDeleteRealmIfMigrationNeeded()) {
                deleteRealm(realmConfiguration);
            }
            else {
                try {
                    migrateRealm(realmConfiguration, ex);
                }
                catch (FileNotFoundException ex2) {
                    throw new RealmFileException(RealmFileException$Kind.NOT_FOUND, (Throwable)ex2);
                }
            }
            return createAndValidate(realmConfiguration, array);
        }
    }
    
    public static boolean deleteRealm(final RealmConfiguration realmConfiguration) {
        return BaseRealm.deleteRealm(realmConfiguration);
    }
    
    public static Object getDefaultModule() {
        try {
            final Constructor<?> constructor = Class.forName("io.realm.DefaultRealmModule").getDeclaredConstructors()[0];
            constructor.setAccessible(true);
            return constructor.newInstance(new Object[0]);
        }
        catch (ClassNotFoundException ex4) {
            return null;
        }
        catch (InvocationTargetException ex) {
            throw new RealmException("Could not create an instance of " + "io.realm.DefaultRealmModule", (Throwable)ex);
        }
        catch (InstantiationException ex2) {
            throw new RealmException("Could not create an instance of " + "io.realm.DefaultRealmModule", (Throwable)ex2);
        }
        catch (IllegalAccessException ex3) {
            throw new RealmException("Could not create an instance of " + "io.realm.DefaultRealmModule", (Throwable)ex3);
        }
    }
    
    public static Realm getInstance(final RealmConfiguration realmConfiguration) {
        if (realmConfiguration == null) {
            throw new IllegalArgumentException("A non-null RealmConfiguration must be provided");
        }
        return (Realm)RealmCache.createRealmOrGetFromCache(realmConfiguration, (Class)Realm.class);
    }
    
    public static void init(final Context context) {
        Label_0078: {
            synchronized (Realm.class) {
                if (BaseRealm.applicationContext != null) {
                    break Label_0078;
                }
                if (context == null) {
                    throw new IllegalArgumentException("Non-null context required.");
                }
            }
            final Context context2;
            RealmCore.loadLibrary(context2);
            Realm.defaultConfiguration = new RealmConfiguration$Builder(context2).build();
            ObjectServerFacade.getSyncFacadeIfPossible().init(context2);
            BaseRealm.applicationContext = context2.getApplicationContext();
            SharedRealm.initialize(new File(context2.getFilesDir(), ".realm.temp"));
        }
    }
    // monitorexit(Realm.class)
    
    private static void initializeRealm(final Realm p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   io/realm/Realm.beginTransaction:()V
        //     4: aload_0        
        //     5: invokevirtual   io/realm/Realm.getVersion:()J
        //     8: lstore_2       
        //     9: lload_2        
        //    10: ldc2_w          -1
        //    13: lcmp           
        //    14: ifne            158
        //    17: iconst_1       
        //    18: istore_1       
        //    19: iload_1        
        //    20: ifeq            34
        //    23: aload_0        
        //    24: aload_0        
        //    25: getfield        io/realm/Realm.configuration:Lio/realm/RealmConfiguration;
        //    28: invokevirtual   io/realm/RealmConfiguration.getSchemaVersion:()J
        //    31: invokevirtual   io/realm/Realm.setVersion:(J)V
        //    34: aload_0        
        //    35: getfield        io/realm/Realm.configuration:Lio/realm/RealmConfiguration;
        //    38: invokevirtual   io/realm/RealmConfiguration.getSchemaMediator:()Lio/realm/internal/RealmProxyMediator;
        //    41: astore          5
        //    43: aload           5
        //    45: invokevirtual   io/realm/internal/RealmProxyMediator.getModelClasses:()Ljava/util/Set;
        //    48: astore          6
        //    50: new             Ljava/util/HashMap;
        //    53: dup            
        //    54: aload           6
        //    56: invokeinterface java/util/Set.size:()I
        //    61: invokespecial   java/util/HashMap.<init>:(I)V
        //    64: astore          4
        //    66: aload           6
        //    68: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    73: astore          6
        //    75: aload           6
        //    77: invokeinterface java/util/Iterator.hasNext:()Z
        //    82: ifeq            163
        //    85: aload           6
        //    87: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    92: checkcast       Ljava/lang/Class;
        //    95: astore          7
        //    97: iload_1        
        //    98: ifeq            113
        //   101: aload           5
        //   103: aload           7
        //   105: aload_0        
        //   106: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   109: invokevirtual   io/realm/internal/RealmProxyMediator.createTable:(Ljava/lang/Class;Lio/realm/internal/SharedRealm;)Lio/realm/internal/Table;
        //   112: pop            
        //   113: aload           4
        //   115: aload           7
        //   117: aload           5
        //   119: aload           7
        //   121: aload_0        
        //   122: getfield        io/realm/Realm.sharedRealm:Lio/realm/internal/SharedRealm;
        //   125: iconst_0       
        //   126: invokevirtual   io/realm/internal/RealmProxyMediator.validateTable:(Ljava/lang/Class;Lio/realm/internal/SharedRealm;Z)Lio/realm/internal/ColumnInfo;
        //   129: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   134: pop            
        //   135: goto            75
        //   138: astore          4
        //   140: aload           4
        //   142: athrow         
        //   143: astore          4
        //   145: iconst_0       
        //   146: istore_1       
        //   147: iload_1        
        //   148: ifeq            236
        //   151: aload_0        
        //   152: invokevirtual   io/realm/Realm.commitTransaction:()V
        //   155: aload           4
        //   157: athrow         
        //   158: iconst_0       
        //   159: istore_1       
        //   160: goto            19
        //   163: aload_0        
        //   164: getfield        io/realm/Realm.schema:Lio/realm/RealmSchema;
        //   167: astore          5
        //   169: iload_1        
        //   170: ifeq            181
        //   173: aload_0        
        //   174: getfield        io/realm/Realm.configuration:Lio/realm/RealmConfiguration;
        //   177: invokevirtual   io/realm/RealmConfiguration.getSchemaVersion:()J
        //   180: lstore_2       
        //   181: aload           5
        //   183: new             Lio/realm/internal/ColumnIndices;
        //   186: dup            
        //   187: lload_2        
        //   188: aload           4
        //   190: invokespecial   io/realm/internal/ColumnIndices.<init>:(JLjava/util/Map;)V
        //   193: putfield        io/realm/RealmSchema.columnIndices:Lio/realm/internal/ColumnIndices;
        //   196: iload_1        
        //   197: ifeq            222
        //   200: aload_0        
        //   201: getfield        io/realm/Realm.configuration:Lio/realm/RealmConfiguration;
        //   204: invokevirtual   io/realm/RealmConfiguration.getInitialDataTransaction:()Lio/realm/Realm$Transaction;
        //   207: astore          4
        //   209: aload           4
        //   211: ifnull          222
        //   214: aload           4
        //   216: aload_0        
        //   217: invokeinterface io/realm/Realm$Transaction.execute:(Lio/realm/Realm;)V
        //   222: iload_1        
        //   223: ifeq            231
        //   226: aload_0        
        //   227: invokevirtual   io/realm/Realm.commitTransaction:()V
        //   230: return         
        //   231: aload_0        
        //   232: invokevirtual   io/realm/Realm.cancelTransaction:()V
        //   235: return         
        //   236: aload_0        
        //   237: invokevirtual   io/realm/Realm.cancelTransaction:()V
        //   240: goto            155
        //   243: astore          4
        //   245: goto            147
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      9      138    143    Ljava/lang/Exception;
        //  0      9      143    147    Any
        //  23     34     138    143    Ljava/lang/Exception;
        //  23     34     243    248    Any
        //  34     75     138    143    Ljava/lang/Exception;
        //  34     75     243    248    Any
        //  75     97     138    143    Ljava/lang/Exception;
        //  75     97     243    248    Any
        //  101    113    138    143    Ljava/lang/Exception;
        //  101    113    243    248    Any
        //  113    135    138    143    Ljava/lang/Exception;
        //  113    135    243    248    Any
        //  140    143    143    147    Any
        //  163    169    138    143    Ljava/lang/Exception;
        //  163    169    243    248    Any
        //  173    181    138    143    Ljava/lang/Exception;
        //  173    181    243    248    Any
        //  181    196    138    143    Ljava/lang/Exception;
        //  181    196    243    248    Any
        //  200    209    138    143    Ljava/lang/Exception;
        //  200    209    243    248    Any
        //  214    222    138    143    Ljava/lang/Exception;
        //  214    222    243    248    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
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
    
    private static void initializeSyncedRealm(final Realm realm) {
        while (true) {
            Label_0365: {
                long version = 0L;
                boolean b2 = false;
                Set modelClasses = null;
                ArrayList<RealmObjectSchema> list = null;
                int n = 0;
                final RealmProxyMediator realmProxyMediator;
                Label_0129: {
                    while (true) {
                        final boolean b = false;
                        while (true) {
                            Label_0124: {
                                try {
                                    realm.beginTransaction();
                                    version = realm.getVersion();
                                    if (version == -1L) {
                                        b2 = true;
                                        final RealmProxyMediator schemaMediator = realm.configuration.getSchemaMediator();
                                        modelClasses = schemaMediator.getModelClasses();
                                        list = new ArrayList<RealmObjectSchema>();
                                        final RealmSchema realmSchema = new RealmSchema();
                                        final Iterator<Class> iterator = modelClasses.iterator();
                                        while (iterator.hasNext()) {
                                            list.add(schemaMediator.createRealmObjectSchema((Class)iterator.next(), realmSchema));
                                        }
                                        break Label_0129;
                                    }
                                    break Label_0124;
                                }
                                catch (Exception ex) {
                                    throw ex;
                                }
                                finally {
                                    n = (b ? 1 : 0);
                                }
                                break;
                            }
                            b2 = false;
                            continue;
                        }
                    }
                    if (n == 0) {
                        break Label_0365;
                    }
                    realm.commitTransaction();
                    throw realmProxyMediator;
                }
                final RealmSchema realmSchema2 = new RealmSchema((ArrayList)list);
                final long schemaVersion = realm.configuration.getSchemaVersion();
                Label_0372: {
                    if (!realm.sharedRealm.requiresMigration(realmSchema2)) {
                        break Label_0372;
                    }
                    if (version >= schemaVersion) {
                        throw new IllegalArgumentException(String.format("The schema was changed but the schema version was not updated. The configured schema version (%d) must be higher than the one in the Realm file (%d) in order to update the schema.", schemaVersion, version));
                    }
                    realm.sharedRealm.updateSchema(realmSchema2, schemaVersion);
                    realm.setVersion(schemaVersion);
                    try {
                        while (true) {
                            final HashMap hashMap = new HashMap<Class, ColumnInfo>(modelClasses.size());
                            for (final Class clazz : modelClasses) {
                                hashMap.put(clazz, realmProxyMediator.validateTable((Class)clazz, realm.sharedRealm, false));
                            }
                            final RealmSchema schema = realm.schema;
                            if (b2) {
                                version = schemaVersion;
                            }
                            schema.columnIndices = new ColumnIndices(version, (Map)hashMap);
                            if (b2) {
                                final Realm$Transaction initialDataTransaction = realm.configuration.getInitialDataTransaction();
                                if (initialDataTransaction != null) {
                                    initialDataTransaction.execute(realm);
                                }
                            }
                            if (n != 0) {
                                realm.commitTransaction();
                                return;
                            }
                            realm.cancelTransaction();
                            return;
                            realm.cancelTransaction();
                            throw realmProxyMediator;
                            n = 0;
                            continue;
                        }
                    }
                    finally {
                        continue;
                    }
                }
            }
            break;
        }
    }
    
    private static void migrateRealm(final RealmConfiguration realmConfiguration, final RealmMigrationNeededException ex) {
        BaseRealm.migrateRealm(realmConfiguration, null, (BaseRealm$MigrationCallback)new Realm$2(), ex);
    }
    
    public <E extends RealmModel> E copyToRealm(final E e) {
        this.checkNotNullObject(e);
        return this.copyOrUpdate(e, false, new HashMap<RealmModel, RealmObjectProxy>());
    }
    
    public <E extends RealmModel> E copyToRealmOrUpdate(final E e) {
        this.checkNotNullObject(e);
        this.checkHasPrimaryKey(e.getClass());
        return this.copyOrUpdate(e, true, new HashMap<RealmModel, RealmObjectProxy>());
    }
    
    public <E extends RealmModel> E createObject(final Class<E> clazz) {
        this.checkIfValid();
        return this.createObjectInternal(clazz, true, Collections.emptyList());
    }
    
    public <E extends RealmModel> E createObject(final Class<E> clazz, final Object o) {
        this.checkIfValid();
        return this.createObjectInternal(clazz, o, true, Collections.emptyList());
    }
    
     <E extends RealmModel> E createObjectInternal(final Class<E> clazz, final Object o, final boolean b, final List<String> list) {
        return this.get(clazz, this.schema.getTable((Class)clazz).addEmptyRowWithPrimaryKey(o), b, list);
    }
    
     <E extends RealmModel> E createObjectInternal(final Class<E> clazz, final boolean b, final List<String> list) {
        final Table table = this.schema.getTable((Class)clazz);
        if (table.hasPrimaryKey()) {
            throw new RealmException(String.format("'%s' has a primary key, use 'createObject(Class<E>, Object)' instead.", Table.tableNameToClassName(table.getName())));
        }
        return this.get(clazz, table.addEmptyRow(), b, list);
    }
    
    public void executeTransaction(final Realm$Transaction realm$Transaction) {
        if (realm$Transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }
        this.beginTransaction();
        try {
            realm$Transaction.execute(this);
            this.commitTransaction();
        }
        catch (Throwable t) {
            if (this.isInTransaction()) {
                this.cancelTransaction();
            }
            else {
                RealmLog.warn("Could not cancel transaction, not currently in a transaction.", new Object[0]);
            }
            throw t;
        }
    }
    
    public RealmAsyncTask executeTransactionAsync(final Realm$Transaction realm$Transaction) {
        return this.executeTransactionAsync(realm$Transaction, null, null);
    }
    
    public RealmAsyncTask executeTransactionAsync(final Realm$Transaction realm$Transaction, final Realm$Transaction$OnSuccess realm$Transaction$OnSuccess, final Realm$Transaction$OnError realm$Transaction$OnError) {
        this.checkIfValid();
        if (realm$Transaction == null) {
            throw new IllegalArgumentException("Transaction should not be null");
        }
        final boolean canDeliverNotification = this.sharedRealm.capabilities.canDeliverNotification();
        if (realm$Transaction$OnSuccess != null || realm$Transaction$OnError != null) {
            this.sharedRealm.capabilities.checkCanDeliverNotification("Callback cannot be delivered on current thread.");
        }
        return (RealmAsyncTask)new RealmAsyncTaskImpl(Realm.asyncTaskExecutor.submitTransaction((Runnable)new Realm$1(this, this.getConfiguration(), realm$Transaction, canDeliverNotification, realm$Transaction$OnSuccess, this.sharedRealm.realmNotifier, realm$Transaction$OnError)), (ThreadPoolExecutor)Realm.asyncTaskExecutor);
    }
    
    Table getTable(final Class<? extends RealmModel> clazz) {
        return this.schema.getTable((Class)clazz);
    }
    
    ColumnIndices updateSchemaCache(final ColumnIndices[] array) {
        ColumnIndices columnIndices = null;
        final long schemaVersion = this.sharedRealm.getSchemaVersion();
        if (schemaVersion == this.schema.columnIndices.getSchemaVersion()) {
            return null;
        }
        final RealmProxyMediator schemaMediator = this.getConfiguration().getSchemaMediator();
        ColumnIndices columnIndices2;
        if ((columnIndices2 = RealmCache.findColumnIndices(array, schemaVersion)) == null) {
            final Set modelClasses = schemaMediator.getModelClasses();
            final HashMap hashMap = new HashMap<Class, ColumnInfo>(modelClasses.size());
            try {
                for (final Class clazz : modelClasses) {
                    hashMap.put(clazz, schemaMediator.validateTable((Class)clazz, this.sharedRealm, true));
                }
            }
            catch (RealmMigrationNeededException ex) {
                throw ex;
            }
            columnIndices2 = (columnIndices = new ColumnIndices(schemaVersion, (Map)hashMap));
        }
        this.schema.columnIndices.copyFrom(columnIndices2, schemaMediator);
        return columnIndices;
    }
    
    public <E extends RealmModel> RealmQuery<E> where(final Class<E> clazz) {
        this.checkIfValid();
        return (RealmQuery<E>)RealmQuery.createQuery(this, (Class)clazz);
    }
}
