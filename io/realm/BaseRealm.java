// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.RealmProxyMediator;
import io.realm.internal.Table;
import java.util.Collections;
import io.realm.internal.InvalidRow;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import java.util.List;
import io.realm.log.RealmLog;
import io.realm.internal.ObjectServerFacade;
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmMigrationNeededException;
import java.util.concurrent.atomic.AtomicBoolean;
import io.realm.internal.SharedRealm$SchemaVersionListener;
import io.realm.internal.RealmNotifier;
import io.realm.internal.SharedRealm;
import io.realm.internal.async.RealmThreadPoolExecutor;
import android.content.Context;
import java.io.Closeable;

abstract class BaseRealm implements Closeable
{
    static volatile Context applicationContext;
    static final RealmThreadPoolExecutor asyncTaskExecutor;
    public static final BaseRealm$ThreadLocalRealmObjectContext objectContext;
    protected RealmConfiguration configuration;
    HandlerController handlerController;
    RealmSchema schema;
    protected SharedRealm sharedRealm;
    final long threadId;
    
    static {
        asyncTaskExecutor = RealmThreadPoolExecutor.newDefaultExecutor();
        objectContext = new BaseRealm$ThreadLocalRealmObjectContext();
    }
    
    protected BaseRealm(final RealmConfiguration configuration) {
        this.threadId = Thread.currentThread().getId();
        this.configuration = configuration;
        this.handlerController = new HandlerController(this);
        final AndroidNotifier androidNotifier = new AndroidNotifier(this.handlerController);
        Object o;
        if (!(this instanceof Realm)) {
            o = null;
        }
        else {
            o = new BaseRealm$1(this);
        }
        this.sharedRealm = SharedRealm.getInstance(configuration, (RealmNotifier)androidNotifier, (SharedRealm$SchemaVersionListener)o, true);
        this.schema = new RealmSchema(this);
        if (this.handlerController.isAutoRefreshAvailable()) {
            this.setAutoRefresh(true);
        }
    }
    
    static boolean deleteRealm(final RealmConfiguration realmConfiguration) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        RealmCache.invokeWithGlobalRefCount(realmConfiguration, (RealmCache$Callback)new BaseRealm$3(realmConfiguration, atomicBoolean));
        return atomicBoolean.get();
    }
    
    protected static void migrateRealm(final RealmConfiguration realmConfiguration, final RealmMigration realmMigration, final BaseRealm$MigrationCallback baseRealm$MigrationCallback, final RealmMigrationNeededException ex) {
        if (realmConfiguration == null) {
            throw new IllegalArgumentException("RealmConfiguration must be provided");
        }
        if (realmMigration == null && realmConfiguration.getMigration() == null) {
            throw new RealmMigrationNeededException(realmConfiguration.getPath(), "RealmMigration must be provided", (Throwable)ex);
        }
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        RealmCache.invokeWithGlobalRefCount(realmConfiguration, (RealmCache$Callback)new BaseRealm$4(realmConfiguration, atomicBoolean, realmMigration, baseRealm$MigrationCallback));
        if (atomicBoolean.get()) {
            throw new FileNotFoundException("Cannot migrate a Realm file which doesn't exist: " + realmConfiguration.getPath());
        }
    }
    
    public void beginTransaction() {
        this.checkIfValid();
        this.sharedRealm.beginTransaction();
    }
    
    public void cancelTransaction() {
        this.checkIfValid();
        this.sharedRealm.cancelTransaction();
    }
    
    protected void checkIfValid() {
        if (this.sharedRealm == null || this.sharedRealm.isClosed()) {
            throw new IllegalStateException("This Realm instance has already been closed, making it unusable.");
        }
        if (this.threadId != Thread.currentThread().getId()) {
            throw new IllegalStateException("Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.");
        }
    }
    
    void checkNotInSync() {
        if (this.configuration.isSyncConfiguration()) {
            throw new IllegalArgumentException("You cannot perform changes to a schema. Please update app and restart.");
        }
    }
    
    @Override
    public void close() {
        if (this.threadId != Thread.currentThread().getId()) {
            throw new IllegalStateException("Realm access from incorrect thread. Realm instance can only be closed on the thread it was created.");
        }
        RealmCache.release(this);
    }
    
    public void commitTransaction() {
        this.commitTransaction(true);
    }
    
    void commitTransaction(final boolean b) {
        this.checkIfValid();
        this.sharedRealm.commitTransaction();
        ObjectServerFacade.getFacade(this.configuration.isSyncConfiguration()).notifyCommit(this.configuration, this.sharedRealm.getLastSnapshotVersion());
        if (b) {
            this.sharedRealm.realmNotifier.notifyCommitByLocalThread();
        }
    }
    
    void doClose() {
        if (this.sharedRealm != null) {
            this.sharedRealm.close();
            this.sharedRealm = null;
        }
        if (this.schema != null) {
            this.schema.close();
        }
    }
    
    @Override
    protected void finalize() {
        if (this.sharedRealm != null && !this.sharedRealm.isClosed()) {
            RealmLog.warn("Remember to call close() on all Realm instances. Realm %s is being finalized without being closed, this can lead to running out of native memory.", new Object[] { this.configuration.getPath() });
        }
        super.finalize();
    }
    
     <E extends RealmModel> E get(final Class<E> clazz, final long n, final boolean b, final List<String> list) {
        final RealmModel instance = this.configuration.getSchemaMediator().newInstance((Class)clazz, (Object)this, (Row)this.schema.getTable((Class)clazz).getUncheckedRow(n), this.schema.getColumnInfo((Class)clazz), b, (List)list);
        ((RealmObjectProxy)instance).realmGet$proxyState().setTableVersion$realm();
        return (E)instance;
    }
    
     <E extends RealmModel> E get(final Class<E> clazz, final String s, final long n) {
        boolean b;
        if (s != null) {
            b = true;
        }
        else {
            b = false;
        }
        Table table;
        if (b) {
            table = this.schema.getTable(s);
        }
        else {
            table = this.schema.getTable((Class)clazz);
        }
        Object instance;
        if (b) {
            Object o;
            if (n != -1L) {
                o = table.getCheckedRow(n);
            }
            else {
                o = InvalidRow.INSTANCE;
            }
            instance = new DynamicRealmObject(this, (Row)o);
        }
        else {
            final RealmProxyMediator schemaMediator = this.configuration.getSchemaMediator();
            Object o2;
            if (n != -1L) {
                o2 = table.getUncheckedRow(n);
            }
            else {
                o2 = InvalidRow.INSTANCE;
            }
            instance = schemaMediator.newInstance((Class)clazz, (Object)this, (Row)o2, this.schema.getColumnInfo((Class)clazz), false, (List)Collections.emptyList());
        }
        final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)instance;
        if (n != -1L) {
            realmObjectProxy.realmGet$proxyState().setTableVersion$realm();
        }
        return (E)instance;
    }
    
    public RealmConfiguration getConfiguration() {
        return this.configuration;
    }
    
    public String getPath() {
        return this.configuration.getPath();
    }
    
    public RealmSchema getSchema() {
        return this.schema;
    }
    
    public long getVersion() {
        return this.sharedRealm.getSchemaVersion();
    }
    
    boolean hasValidNotifier() {
        return this.sharedRealm.realmNotifier != null && this.sharedRealm.realmNotifier.isValid();
    }
    
    public boolean isClosed() {
        if (this.threadId != Thread.currentThread().getId()) {
            throw new IllegalStateException("Realm access from incorrect thread. Realm objects can only be accessed on the thread they were created.");
        }
        return this.sharedRealm == null || this.sharedRealm.isClosed();
    }
    
    public boolean isInTransaction() {
        this.checkIfValid();
        return this.sharedRealm.isInTransaction();
    }
    
    public void setAutoRefresh(final boolean autoRefresh) {
        this.checkIfValid();
        this.handlerController.checkCanBeAutoRefreshed();
        this.handlerController.setAutoRefresh(autoRefresh);
    }
    
    void setVersion(final long schemaVersion) {
        this.sharedRealm.setSchemaVersion(schemaVersion);
    }
}
