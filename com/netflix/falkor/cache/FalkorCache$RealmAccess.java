// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import java.io.IOException;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.NetflixApplication;
import io.realm.RealmQuery;
import java.util.concurrent.ThreadPoolExecutor;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.Realm$1;
import io.realm.Realm$Transaction$OnError;
import io.realm.Realm$Transaction$OnSuccess;
import io.realm.RealmAsyncTask;
import io.realm.log.RealmLog;
import io.realm.internal.Table;
import java.util.List;
import java.util.Collections;
import io.realm.BaseRealm$MigrationCallback;
import io.realm.RealmMigration;
import io.realm.Realm$2;
import java.util.Iterator;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import io.realm.internal.ColumnInfo;
import java.util.HashMap;
import io.realm.RealmSchema;
import io.realm.RealmObjectSchema;
import java.util.ArrayList;
import io.realm.internal.SharedRealm;
import java.io.File;
import io.realm.internal.ObjectServerFacade;
import io.realm.RealmConfiguration$Builder;
import io.realm.internal.RealmCore;
import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import io.realm.exceptions.RealmException;
import java.io.FileNotFoundException;
import io.realm.exceptions.RealmFileException;
import io.realm.exceptions.RealmFileException$Kind;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.RealmCache;
import io.realm.internal.ColumnIndices;
import io.realm.internal.RealmObjectProxy;
import java.util.Map;
import io.realm.RealmModel;
import io.realm.RealmConfiguration;
import io.realm.BaseRealm;
import io.realm.Realm$Transaction;
import com.netflix.mediaclient.Log;
import io.realm.Realm;
import java.util.concurrent.atomic.AtomicInteger;

public class FalkorCache$RealmAccess
{
    private static final String TAG = "FalkorCache.RealmAccess";
    private static final AtomicInteger sAccessCount;
    private static final ThreadLocal<Boolean> sThreadInTransaction;
    
    static {
        sAccessCount = new AtomicInteger(0);
        sThreadInTransaction = new ThreadLocal<Boolean>();
    }
    
    public static void beginTransaction(final Realm realm) {
        realm.beginTransaction();
        FalkorCache$RealmAccess.sThreadInTransaction.set(true);
    }
    
    public static void cancelTransaction(final Realm realm) {
        Log.i("FalkorCache.RealmAccess", "cancelled a transaction");
        realm.cancelTransaction();
        FalkorCache$RealmAccess.sThreadInTransaction.set(false);
    }
    
    public static void close(final Realm realm) {
        realm.close();
    }
    
    public static void commitTransaction(final Realm realm) {
        realm.commitTransaction();
        FalkorCache$RealmAccess.sThreadInTransaction.set(false);
    }
    
    private static void doExecuteTransaction(final Realm realm, final Realm$Transaction realm$Transaction, final boolean b) {
        FalkorCache$RealmAccess.sAccessCount.incrementAndGet();
        Label_0024: {
            if (b) {
                break Label_0024;
            }
            try {
                realm.executeTransaction(realm$Transaction);
                return;
                realm$Transaction.execute(realm);
            }
            finally {
                FalkorCache$RealmAccess.sAccessCount.decrementAndGet();
            }
        }
    }
    
    public static void executeTransaction(final Realm realm, final Realm$Transaction realm$Transaction) {
        doExecuteTransaction(realm, realm$Transaction, isThreadInTransaction());
    }
    
    public static Realm getInstance() {
        return Realm.getInstance(FalkorCache.sRealmConfiguration);
    }
    
    private static boolean isThreadInTransaction() {
        return FalkorCache$RealmAccess.sThreadInTransaction.get() != null && FalkorCache$RealmAccess.sThreadInTransaction.get();
    }
    
    public static void purge() {
        Log.i("FalkorCache.RealmAccess", "purging cache");
        while (FalkorCache$RealmAccess.sAccessCount.get() > 0) {
            try {
                Log.i("FalkorCache.RealmAccess", "Purge waiting due to accessCount=%d", FalkorCache$RealmAccess.sAccessCount.get());
                Thread.sleep(5L);
            }
            catch (InterruptedException ex) {
                Log.i("FalkorCache.RealmAccess", "Purge wait interrupted");
            }
        }
        final Realm instance = Realm.getInstance(FalkorCache.sRealmConfiguration);
        final Throwable t = null;
        while (true) {
            try {
                instance.executeTransaction((Realm$Transaction)new FalkorCache$RealmAccess$1());
                Log.i("FalkorCache.RealmAccess", "purged cache empty=%b", instance.isEmpty());
                while (true) {
                    if (instance == null) {
                        break Label_0116;
                    }
                    Label_0132: {
                        if (!false) {
                            break Label_0132;
                        }
                        try {
                            instance.close();
                            FalkorCache.sMonitor.resetAll();
                            return;
                        }
                        catch (Throwable t3) {
                            throw new NullPointerException();
                        }
                    }
                    instance.close();
                    continue;
                }
            }
            catch (Throwable t) {
                try {
                    throw t;
                }
                finally {}
                while (true) {
                    if (instance == null) {
                        break Label_0155;
                    }
                    Label_0166: {
                        if (t == null) {
                            break Label_0166;
                        }
                        try {
                            instance.close();
                            throw;
                        }
                        catch (Throwable instance) {
                            t.addSuppressed((Throwable)instance);
                            throw;
                        }
                    }
                    instance.close();
                    continue;
                }
            }
            finally {
                continue;
            }
            break;
        }
    }
}
