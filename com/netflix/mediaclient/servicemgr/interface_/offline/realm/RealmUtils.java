// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline.realm;

import io.realm.RealmQuery;
import java.util.concurrent.ThreadPoolExecutor;
import io.realm.internal.async.RealmAsyncTaskImpl;
import io.realm.Realm$2;
import io.realm.Realm$Transaction$OnError;
import io.realm.Realm$Transaction$OnSuccess;
import io.realm.RealmAsyncTask;
import io.realm.internal.Table;
import java.util.Collections;
import io.realm.BaseRealm$MigrationCallback;
import io.realm.Realm$3;
import io.realm.internal.ColumnInfo;
import java.util.Set;
import io.realm.internal.RealmProxyMediator;
import io.realm.Realm$1;
import io.realm.RealmSchema;
import io.realm.RealmObjectSchema;
import java.util.ArrayList;
import io.realm.internal.ObjectServerFacade;
import io.realm.log.Logger;
import io.realm.log.RealmLog;
import io.realm.log.AndroidLogger;
import io.realm.internal.RealmCore;
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
import io.realm.BaseRealm;
import android.content.Context;
import io.realm.RealmModel;
import java.util.Iterator;
import io.realm.RealmResults;
import java.util.List;
import io.realm.Realm$Transaction;
import io.realm.Realm;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import java.util.Arrays;
import io.realm.RealmMigration;
import io.realm.RealmConfiguration$Builder;
import java.util.HashMap;
import io.realm.RealmConfiguration;

public class RealmUtils
{
    public static final String TAG = "RealmUtils";
    public static RealmConfiguration sCurrentConfig;
    private static HashMap<Long, RealmUtils$DbState> sCurrentStatesMap;
    
    static {
        RealmUtils.sCurrentConfig = new RealmConfiguration$Builder().name("offline.realm").modules((Object)new RealmOfflineModule(), new Object[0]).migration((RealmMigration)new RealmOfflineMigration()).schemaVersion(2L).build();
    }
    
    private static void checkAndUpdateCurrentState(final RealmUtils$DbState realmUtils$DbState, final RealmUtils$DbState realmUtils$DbState2) {
        checkAndUpdateCurrentState(new RealmUtils$DbState[] { realmUtils$DbState }, realmUtils$DbState2);
    }
    
    private static void checkAndUpdateCurrentState(final RealmUtils$DbState[] array, final RealmUtils$DbState realmUtils$DbState) {
        if (RealmUtils.sCurrentStatesMap == null) {
            RealmUtils.sCurrentStatesMap = new HashMap<Long, RealmUtils$DbState>();
        }
        final long id = Thread.currentThread().getId();
        RealmUtils$DbState closed;
        if (RealmUtils.sCurrentStatesMap.containsKey(id)) {
            closed = RealmUtils.sCurrentStatesMap.get(id);
        }
        else {
            closed = RealmUtils$DbState.CLOSED;
        }
        if (!Arrays.asList(array).contains(closed)) {
            final String string = "SPY-10228 - Realm Db is in a wrong state: " + RealmUtils.sCurrentStatesMap + "; but should be in " + array + " for thread: " + id;
            if (Log.isLoggable()) {
                Log.w("RealmUtils", string);
            }
            else {
                ErrorLoggingManager.logHandledException(string);
            }
        }
        RealmUtils.sCurrentStatesMap.put(id, realmUtils$DbState);
    }
    
    public static void close(final Realm realm) {
        checkAndUpdateCurrentState(RealmUtils$DbState.INSTANCE_OBTAINED, RealmUtils$DbState.CLOSED);
        realm.close();
    }
    
    public static void executeTransaction(final Realm realm, final Realm$Transaction realm$Transaction) {
        checkAndUpdateCurrentState(RealmUtils$DbState.INSTANCE_OBTAINED, RealmUtils$DbState.INSTANCE_OBTAINED);
        realm.executeTransaction(realm$Transaction);
    }
    
    public static void executeTransactionAsync(final Realm realm, final Realm$Transaction realm$Transaction) {
        checkAndUpdateCurrentState(RealmUtils$DbState.INSTANCE_OBTAINED, RealmUtils$DbState.INSTANCE_OBTAINED);
        realm.executeTransactionAsync(realm$Transaction);
    }
    
    public static List<RealmIncompleteVideoDetails> getIncompleteVideoDetails(final Realm realm) {
        return (List<RealmIncompleteVideoDetails>)realm.where(RealmIncompleteVideoDetails.class).findAll();
    }
    
    public static RealmVideoDetails getOfflineVideoDetails(final String s) {
        final Realm realmInstance = getRealmInstance();
        try {
            final RealmResults all = realmInstance.where(RealmVideoDetails.class).equalTo("id", s).findAll();
            final int size = all.size();
            if (size != 1) {
                ErrorLoggingManager.logHandledException(String.format("SPY-10228 - Found %d records for the following video id: %s", size, s));
            }
            for (final RealmVideoDetails realmVideoDetails : all) {
                if (realmVideoDetails.getPlayable() != null) {
                    return realmVideoDetails;
                }
            }
            return null;
        }
        finally {
            close(realmInstance);
        }
    }
    
    public static RealmProfile getProfile(final String s) {
        final Realm realmInstance = getRealmInstance();
        try {
            return (RealmProfile)realmInstance.where(RealmProfile.class).equalTo("id", s).findFirst();
        }
        finally {
            close(realmInstance);
        }
    }
    
    public static Realm getRealmInstance() {
        checkAndUpdateCurrentState(new RealmUtils$DbState[] { RealmUtils$DbState.CLOSED, RealmUtils$DbState.INSTANCE_OBTAINED }, RealmUtils$DbState.INSTANCE_OBTAINED);
        try {
            return Realm.getInstance(RealmUtils.sCurrentConfig);
        }
        catch (IllegalArgumentException ex) {
            Log.w("RealmUtils", "WARNING: If you downgraded the app please clear all app data");
            throw ex;
        }
    }
    
    public static boolean idNotExists(final Realm realm, final Class clazz, final String s) {
        return realm.where((Class<RealmModel>)clazz).equalTo("id", s).count() == 0L;
    }
    
    public static void removeRecordsForPlayable(final Context context, final Realm realm, final String s) {
        executeTransaction(realm, (Realm$Transaction)new RealmUtils$1(s, context));
    }
}
