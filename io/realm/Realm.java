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
import io.realm.internal.ColumnInfo;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import java.util.ArrayList;
import java.util.HashMap;
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
        if (version != -1L && version < schemaVersion && columnIndices == null) {
            realm.doClose();
            throw new RealmMigrationNeededException(realmConfiguration.getPath(), String.format("Realm on disk need to migrate from v%s to v%s", version, schemaVersion));
        }
        if (version != -1L && schemaVersion < version && columnIndices == null) {
            realm.doClose();
            throw new IllegalArgumentException(String.format("Realm on disk is newer than the one specified: v%s vs. v%s", version, schemaVersion));
        }
        if (columnIndices == null) {
            try {
                initializeRealm(realm);
                return realm;
            }
            catch (RuntimeException ex) {
                realm.doClose();
                throw ex;
            }
        }
        realm.schema.columnIndices = columnIndices.clone();
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
    
    private static void initializeRealm(final Realm realm) {
        final long version = realm.getVersion();
        int n = 0;
        final boolean b = false;
        final boolean syncConfiguration = realm.configuration.isSyncConfiguration();
        boolean b2 = b;
        while (true) {
            if (!syncConfiguration) {
                RealmProxyMediator schemaMediator = null;
                Set modelClasses = null;
                HashMap hashMap;
                ArrayList<RealmObjectSchema> list = null;
                RealmSchema realmSchema;
                Iterator<Class> iterator;
                Class clazz;
                final Map<Class, ColumnInfo> map;
                Label_0252:Label_0222_Outer:
                while (true) {
                    while (true) {
                    Label_0484:
                        while (true) {
                            try {
                                realm.beginTransaction();
                                b2 = b;
                                if (version == -1L) {
                                    n = 1;
                                    b2 = true;
                                    realm.setVersion(realm.configuration.getSchemaVersion());
                                }
                                n = (b2 ? 1 : 0);
                                schemaMediator = realm.configuration.getSchemaMediator();
                                n = (b2 ? 1 : 0);
                                modelClasses = schemaMediator.getModelClasses();
                                n = (b2 ? 1 : 0);
                                hashMap = new HashMap(modelClasses.size());
                                n = (b2 ? 1 : 0);
                                list = new ArrayList<RealmObjectSchema>();
                                n = (b2 ? 1 : 0);
                                realmSchema = new RealmSchema();
                                n = (b2 ? 1 : 0);
                                iterator = modelClasses.iterator();
                                while (true) {
                                    n = (b2 ? 1 : 0);
                                    if (!iterator.hasNext()) {
                                        break Label_0252;
                                    }
                                    n = (b2 ? 1 : 0);
                                    clazz = iterator.next();
                                    if (version == -1L && !syncConfiguration) {
                                        n = (b2 ? 1 : 0);
                                        schemaMediator.createTable((Class)clazz, realm.sharedRealm);
                                    }
                                    if (!syncConfiguration) {
                                        break;
                                    }
                                    n = (b2 ? 1 : 0);
                                    list.add(schemaMediator.createRealmObjectSchema((Class)clazz, realmSchema));
                                }
                            }
                            finally {
                                if (!syncConfiguration) {
                                    if (n == 0) {
                                        break Label_0484;
                                    }
                                    realm.commitTransaction(false);
                                }
                            }
                            n = (b2 ? 1 : 0);
                            map.put(clazz, schemaMediator.validateTable((Class)clazz, realm.sharedRealm, false));
                            continue Label_0222_Outer;
                        }
                        realm.cancelTransaction();
                        continue;
                    }
                }
                if (syncConfiguration) {
                    realm.sharedRealm.updateSchema(new RealmSchema((ArrayList)list), version);
                    for (final Class clazz2 : modelClasses) {
                        map.put(clazz2, schemaMediator.validateTable((Class)clazz2, realm.sharedRealm, false));
                    }
                }
                final RealmSchema schema = realm.schema;
                long schemaVersion;
                if (version == -1L) {
                    schemaVersion = realm.configuration.getSchemaVersion();
                }
                else {
                    schemaVersion = version;
                }
                schema.columnIndices = new ColumnIndices(schemaVersion, (Map)map);
                if (version == -1L) {
                    final Realm$Transaction initialDataTransaction = realm.getConfiguration().getInitialDataTransaction();
                    if (initialDataTransaction != null) {
                        if (syncConfiguration) {
                            realm.executeTransaction(initialDataTransaction);
                            realm.executeTransaction((Realm$Transaction)new Realm$1());
                        }
                        else {
                            initialDataTransaction.execute(realm);
                        }
                    }
                }
                if (!syncConfiguration) {
                    if (!b2) {
                        realm.cancelTransaction();
                        return;
                    }
                    realm.commitTransaction(false);
                }
                return;
            }
            continue;
        }
    }
    
    private static void migrateRealm(final RealmConfiguration realmConfiguration, final RealmMigrationNeededException ex) {
        BaseRealm.migrateRealm(realmConfiguration, null, (BaseRealm$MigrationCallback)new Realm$3(), ex);
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
        if ((realm$Transaction$OnSuccess != null || realm$Transaction$OnError != null) && !this.hasValidNotifier()) {
            throw new IllegalStateException("Your Realm is opened from a thread without a Looper and you provided a callback, we need a Handler to invoke your callback");
        }
        return (RealmAsyncTask)new RealmAsyncTaskImpl(Realm.asyncTaskExecutor.submitTransaction((Runnable)new Realm$2(this, this.getConfiguration(), realm$Transaction, realm$Transaction$OnSuccess, realm$Transaction$OnError)), (ThreadPoolExecutor)Realm.asyncTaskExecutor);
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
