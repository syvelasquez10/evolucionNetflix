// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.concurrent.CountedCompleter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Spliterator;
import java.util.NoSuchElementException;
import java.util.concurrent.locks.LockSupport;
import sun.misc.Contended;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.ToLongBiFunction;
import java.util.function.DoubleBinaryOperator;
import java.util.function.ToDoubleBiFunction;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Enumeration;
import java.util.function.Function;
import java.util.function.BiFunction;
import java.util.function.BiConsumer;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import sun.misc.Unsafe;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.concurrent.ConcurrentMap;
import java.util.AbstractMap;
import android.os.Message;
import android.os.Looper;
import io.realm.internal.async.QueryUpdateTask$Builder$RealmResultsQueryStep;
import io.realm.internal.async.QueryUpdateTask$Builder$UpdateQueryStep;
import io.realm.internal.Row;
import java.util.Collection;
import java.lang.ref.Reference;
import java.util.Set;
import io.realm.internal.async.QueryUpdateTask$NotifyEvent;
import io.realm.internal.async.QueryUpdateTask;
import io.realm.internal.async.BadVersionException;
import io.realm.log.RealmLog;
import io.realm.internal.async.QueryUpdateTask$Result;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.concurrent.Future;
import io.realm.internal.IdentitySet;
import java.lang.ref.ReferenceQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import io.realm.internal.RealmObjectProxy;
import java.util.concurrent.CopyOnWriteArrayList;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.os.Handler$Callback;

final class HandlerController implements Handler$Callback
{
    private static final Boolean NO_REALM_QUERY;
    final Map<WeakReference<RealmResults<? extends RealmModel>>, RealmQuery<? extends RealmModel>> asyncRealmResults;
    private boolean autoRefresh;
    final CopyOnWriteArrayList<RealmChangeListener<? extends BaseRealm>> changeListeners;
    final Map<WeakReference<RealmObjectProxy>, RealmQuery<? extends RealmModel>> emptyAsyncRealmObject;
    private final List<Runnable> pendingOnSuccessAsyncTransactionCallbacks;
    final BaseRealm realm;
    final ConcurrentHashMap<WeakReference<RealmObjectProxy>, Object> realmObjects;
    private final ReferenceQueue<RealmResults<? extends RealmModel>> referenceQueueAsyncRealmResults;
    final ReferenceQueue<RealmModel> referenceQueueRealmObject;
    private final ReferenceQueue<RealmResults<? extends RealmModel>> referenceQueueSyncRealmResults;
    final IdentitySet<WeakReference<RealmResults<? extends RealmModel>>> syncRealmResults;
    private Future updateAsyncQueriesTask;
    final List<WeakReference<RealmChangeListener<? extends BaseRealm>>> weakChangeListeners;
    
    static {
        NO_REALM_QUERY = Boolean.TRUE;
    }
    
    public HandlerController(final BaseRealm realm) {
        this.changeListeners = new CopyOnWriteArrayList<RealmChangeListener<? extends BaseRealm>>();
        this.weakChangeListeners = new CopyOnWriteArrayList<WeakReference<RealmChangeListener<? extends BaseRealm>>>();
        this.referenceQueueAsyncRealmResults = new ReferenceQueue<RealmResults<? extends RealmModel>>();
        this.referenceQueueSyncRealmResults = new ReferenceQueue<RealmResults<? extends RealmModel>>();
        this.referenceQueueRealmObject = new ReferenceQueue<RealmModel>();
        this.asyncRealmResults = new IdentityHashMap<WeakReference<RealmResults<? extends RealmModel>>, RealmQuery<? extends RealmModel>>();
        this.emptyAsyncRealmObject = new ConcurrentHashMap<WeakReference<RealmObjectProxy>, RealmQuery<? extends RealmModel>>();
        this.syncRealmResults = (IdentitySet<WeakReference<RealmResults<? extends RealmModel>>>)new IdentitySet();
        this.realmObjects = new ConcurrentHashMap<WeakReference<RealmObjectProxy>, Object>();
        this.pendingOnSuccessAsyncTransactionCallbacks = new ArrayList<Runnable>();
        this.realm = realm;
    }
    
    private void collectAsyncRealmResultsCallbacks(final List<RealmResults<? extends RealmModel>> list) {
        this.collectRealmResultsCallbacks(this.asyncRealmResults.keySet().iterator(), list);
    }
    
    private void collectRealmResultsCallbacks(final Iterator<WeakReference<RealmResults<? extends RealmModel>>> iterator, final List<RealmResults<? extends RealmModel>> list) {
        while (iterator.hasNext()) {
            final RealmResults<? extends RealmModel> realmResults = iterator.next().get();
            if (realmResults == null) {
                iterator.remove();
            }
            else {
                if (!realmResults.isLoaded()) {
                    continue;
                }
                realmResults.syncIfNeeded();
                list.add(realmResults);
            }
        }
    }
    
    private void collectSyncRealmResultsCallbacks(final List<RealmResults<? extends RealmModel>> list) {
        this.collectRealmResultsCallbacks(this.syncRealmResults.keySet().iterator(), list);
    }
    
    private void completedAsyncQueriesUpdate(final QueryUpdateTask$Result queryUpdateTask$Result) {
        final int compareTo = this.realm.sharedRealm.getVersionID().compareTo(queryUpdateTask$Result.versionID);
        if (compareTo > 0) {
            RealmLog.trace("COMPLETED_UPDATE_ASYNC_QUERIES %s caller is more advanced, Looper will updates queries", new Object[] { this });
            return;
        }
        while (true) {
            if (compareTo != 0) {
                ArrayList<RealmResults<? extends RealmModel>> list = null;
                while (true) {
                    RealmLog.trace("COMPLETED_UPDATE_ASYNC_QUERIES %s caller is behind advance_read", new Object[] { this });
                    while (true) {
                        Map.Entry<WeakReference, V> entry = null;
                        WeakReference<RealmResults> weakReference = null;
                        RealmResults<? extends RealmModel> realmResults = null;
                        Label_0169: {
                            try {
                                this.realm.sharedRealm.refresh(queryUpdateTask$Result.versionID);
                                list = new ArrayList<RealmResults<? extends RealmModel>>(queryUpdateTask$Result.updatedTableViews.size());
                                final Iterator<Map.Entry<WeakReference, V>> iterator = queryUpdateTask$Result.updatedTableViews.entrySet().iterator();
                                while (iterator.hasNext()) {
                                    entry = iterator.next();
                                    weakReference = entry.getKey();
                                    realmResults = weakReference.get();
                                    if (realmResults != null) {
                                        break Label_0169;
                                    }
                                    this.asyncRealmResults.remove(weakReference);
                                }
                                break;
                            }
                            catch (BadVersionException ex) {
                                throw new IllegalStateException("Failed to advance Caller Realm to Worker Realm version", (Throwable)ex);
                            }
                        }
                        realmResults.swapTableViewPointer((long)entry.getValue());
                        realmResults.syncIfNeeded();
                        list.add(realmResults);
                        RealmLog.trace("COMPLETED_UPDATE_ASYNC_QUERIES updating RealmResults %s", new Object[] { this, weakReference });
                        continue;
                    }
                }
                this.collectSyncRealmResultsCallbacks(list);
                this.notifyAllListeners(list);
                this.updateAsyncQueriesTask = null;
                return;
            }
            continue;
        }
    }
    
    private void completedAsyncRealmObject(final QueryUpdateTask$Result queryUpdateTask$Result) {
        final Set<WeakReference<RealmObjectProxy>> keySet = (Set<WeakReference<RealmObjectProxy>>)queryUpdateTask$Result.updatedRow.keySet();
        if (keySet.size() > 0) {
            final WeakReference<RealmObjectProxy> weakReference = keySet.iterator().next();
            final RealmObjectProxy realmObjectProxy = weakReference.get();
            if (realmObjectProxy != null) {
                final int compareTo = this.realm.sharedRealm.getVersionID().compareTo(queryUpdateTask$Result.versionID);
                if (compareTo == 0) {
                    final long longValue = queryUpdateTask$Result.updatedRow.get(weakReference);
                    if (longValue != 0L && this.emptyAsyncRealmObject.containsKey(weakReference)) {
                        this.emptyAsyncRealmObject.remove(weakReference);
                        this.realmObjects.put(weakReference, HandlerController.NO_REALM_QUERY);
                    }
                    realmObjectProxy.realmGet$proxyState().onCompleted$realm(longValue);
                    realmObjectProxy.realmGet$proxyState().notifyChangeListeners$realm();
                }
                else {
                    if (compareTo <= 0) {
                        throw new IllegalStateException("Caller thread behind the Worker thread");
                    }
                    if (RealmObject.isValid(realmObjectProxy)) {
                        RealmLog.trace("[COMPLETED_ASYNC_REALM_OBJECT %s], realm: %s. RealmObject is already loaded, just notify it", new Object[] { this.realm, this });
                        realmObjectProxy.realmGet$proxyState().notifyChangeListeners$realm();
                        return;
                    }
                    RealmLog.trace("[COMPLETED_ASYNC_REALM_OBJECT %s, realm: %s. RealmObject is not loaded yet. Rerun the query.", new Object[] { realmObjectProxy, this });
                    final Object value = this.realmObjects.get(weakReference);
                    RealmQuery<? extends RealmModel> realmQuery;
                    if (value == null || value == HandlerController.NO_REALM_QUERY) {
                        realmQuery = this.emptyAsyncRealmObject.get(weakReference);
                    }
                    else {
                        realmQuery = (RealmQuery<? extends RealmModel>)value;
                    }
                    Realm.asyncTaskExecutor.submitQueryUpdate((Runnable)QueryUpdateTask.newBuilder().realmConfiguration(this.realm.getConfiguration()).addObject((WeakReference)weakReference, realmQuery.handoverQueryPointer(), realmQuery.getArgument()).sendToNotifier(this.realm.sharedRealm.realmNotifier, QueryUpdateTask$NotifyEvent.COMPLETE_ASYNC_OBJECT).build());
                }
            }
        }
    }
    
    private void completedAsyncRealmResults(final QueryUpdateTask$Result queryUpdateTask$Result) {
        final Set<WeakReference<RealmResults>> keySet = (Set<WeakReference<RealmResults>>)queryUpdateTask$Result.updatedTableViews.keySet();
        if (keySet.size() > 0) {
            final WeakReference<RealmResults> weakReference = keySet.iterator().next();
            final RealmResults realmResults = weakReference.get();
            if (realmResults == null) {
                this.asyncRealmResults.remove(weakReference);
                RealmLog.trace("[COMPLETED_ASYNC_REALM_RESULTS %s] realm: %s RealmResults GC'd ignore results", new Object[] { weakReference, this });
            }
            else {
                final int compareTo = this.realm.sharedRealm.getVersionID().compareTo(queryUpdateTask$Result.versionID);
                if (compareTo == 0) {
                    if (!realmResults.isLoaded()) {
                        RealmLog.trace("[COMPLETED_ASYNC_REALM_RESULTS %s] , realm: %s same versions, using results (RealmResults is not loaded)", new Object[] { weakReference, this });
                        realmResults.swapTableViewPointer(queryUpdateTask$Result.updatedTableViews.get(weakReference));
                        realmResults.syncIfNeeded();
                        realmResults.notifyChangeListeners(false);
                        return;
                    }
                    RealmLog.trace("[COMPLETED_ASYNC_REALM_RESULTS %s] , realm: %s ignoring result the RealmResults (is already loaded)", new Object[] { weakReference, this });
                }
                else {
                    if (compareTo <= 0) {
                        RealmLog.trace("[COMPLETED_ASYNC_REALM_RESULTS %s] , %s caller thread behind worker thread, ignore results (a batch update will update everything including this query)", new Object[] { weakReference, this });
                        return;
                    }
                    if (!realmResults.isLoaded()) {
                        RealmLog.trace("[COMPLETED_ASYNC_REALM_RESULTS %s ] , %s caller is more advanced & RealmResults is not loaded, rerunning the query against the latest version", new Object[] { weakReference, this });
                        final RealmQuery<? extends RealmModel> realmQuery = this.asyncRealmResults.get(weakReference);
                        Realm.asyncTaskExecutor.submitQueryUpdate((Runnable)QueryUpdateTask.newBuilder().realmConfiguration(this.realm.getConfiguration()).add((WeakReference)weakReference, realmQuery.handoverQueryPointer(), realmQuery.getArgument()).sendToNotifier(this.realm.sharedRealm.realmNotifier, QueryUpdateTask$NotifyEvent.COMPLETE_ASYNC_RESULTS).build());
                        return;
                    }
                    RealmLog.trace("[COMPLETED_ASYNC_REALM_RESULTS %s] , %s caller is more advanced & RealmResults is loaded ignore the outdated result", new Object[] { weakReference, this });
                }
            }
        }
    }
    
    private void deleteWeakReferences() {
        while (true) {
            final Reference<? extends RealmResults<? extends RealmModel>> poll = this.referenceQueueAsyncRealmResults.poll();
            if (poll == null) {
                break;
            }
            this.asyncRealmResults.remove(poll);
        }
        while (true) {
            final Reference<? extends RealmResults<? extends RealmModel>> poll2 = this.referenceQueueSyncRealmResults.poll();
            if (poll2 == null) {
                break;
            }
            this.syncRealmResults.remove((Object)poll2);
        }
        while (true) {
            final Reference<? extends RealmModel> poll3 = this.referenceQueueRealmObject.poll();
            if (poll3 == null) {
                break;
            }
            this.realmObjects.remove(poll3);
        }
    }
    
    private static boolean isIntentServiceThread() {
        final String name = Thread.currentThread().getName();
        return name != null && name.startsWith("IntentService[");
    }
    
    private void notifyAsyncTransactionCallbacks() {
        if (!this.pendingOnSuccessAsyncTransactionCallbacks.isEmpty()) {
            final Iterator<Runnable> iterator = this.pendingOnSuccessAsyncTransactionCallbacks.iterator();
            while (iterator.hasNext()) {
                iterator.next().run();
            }
            this.pendingOnSuccessAsyncTransactionCallbacks.clear();
        }
    }
    
    private void notifyGlobalListeners() {
        final Iterator<RealmChangeListener<BaseRealm>> iterator = (Iterator<RealmChangeListener<BaseRealm>>)this.changeListeners.iterator();
        while (!this.realm.isClosed() && iterator.hasNext()) {
            iterator.next().onChange(this.realm);
        }
        final Iterator<WeakReference<RealmChangeListener<? extends BaseRealm>>> iterator2 = this.weakChangeListeners.iterator();
        List<WeakReference<RealmChangeListener>> list = null;
        while (!this.realm.isClosed() && iterator2.hasNext()) {
            final WeakReference<RealmChangeListener<? extends BaseRealm>> weakReference = iterator2.next();
            final RealmChangeListener<BaseRealm> realmChangeListener = weakReference.get();
            if (realmChangeListener == null) {
                if (list == null) {
                    list = new ArrayList<WeakReference<RealmChangeListener>>(this.weakChangeListeners.size());
                }
                list.add((WeakReference<RealmChangeListener>)weakReference);
            }
            else {
                realmChangeListener.onChange(this.realm);
            }
        }
        if (list != null) {
            this.weakChangeListeners.removeAll(list);
        }
    }
    
    private void notifyRealmObjectCallbacks() {
        final ArrayList<RealmObjectProxy> list = new ArrayList<RealmObjectProxy>();
        final Iterator<WeakReference<RealmObjectProxy>> iterator = (Iterator<WeakReference<RealmObjectProxy>>)this.realmObjects.keySet().iterator();
        while (iterator.hasNext()) {
            final RealmObjectProxy realmObjectProxy = iterator.next().get();
            if (realmObjectProxy == null) {
                iterator.remove();
            }
            else if (realmObjectProxy.realmGet$proxyState().getRow$realm().isAttached()) {
                list.add(realmObjectProxy);
            }
            else {
                if (realmObjectProxy.realmGet$proxyState().getRow$realm() == Row.EMPTY_ROW) {
                    continue;
                }
                iterator.remove();
            }
        }
        final Iterator<Object> iterator2 = list.iterator();
        while (!this.realm.isClosed() && iterator2.hasNext()) {
            iterator2.next().realmGet$proxyState().notifyChangeListeners$realm();
        }
    }
    
    private void realmChanged(final boolean b) {
        String s;
        if (b) {
            s = "LOCAL_COMMIT";
        }
        else {
            s = "REALM_CHANGED";
        }
        RealmLog.debug("%s : %s", new Object[] { s, this });
        this.deleteWeakReferences();
        final boolean threadContainsAsyncQueries = this.threadContainsAsyncQueries();
        if (b && threadContainsAsyncQueries) {
            RealmLog.warn("Mixing asynchronous queries with local writes should be avoided. Realm will convert any async queries to synchronous in order to remain consistent. Use asynchronous writes instead. You can read more here: https://realm.io/docs/java/latest/#asynchronous-transactions", new Object[0]);
        }
        if (!b && threadContainsAsyncQueries) {
            this.updateAsyncQueries();
            return;
        }
        this.realm.sharedRealm.refresh();
        final ArrayList<RealmResults<? extends RealmModel>> list = new ArrayList<RealmResults<? extends RealmModel>>();
        this.collectAsyncRealmResultsCallbacks(list);
        this.collectSyncRealmResultsCallbacks(list);
        this.notifyAllListeners(list);
    }
    
    private boolean threadContainsAsyncQueries() {
        final Iterator<Map.Entry<WeakReference<RealmResults<? extends RealmModel>>, RealmQuery<? extends RealmModel>>> iterator = this.asyncRealmResults.entrySet().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            if (iterator.next().getKey().get() == null) {
                iterator.remove();
            }
            else {
                b = false;
            }
        }
        return !b;
    }
    
    private void updateAsyncEmptyRealmObject() {
        final Iterator<Map.Entry<WeakReference<RealmObjectProxy>, RealmQuery<? extends RealmModel>>> iterator = this.emptyAsyncRealmObject.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<WeakReference<RealmObjectProxy>, RealmQuery<? extends RealmModel>> entry = iterator.next();
            if (entry.getKey().get() != null) {
                Realm.asyncTaskExecutor.submitQueryUpdate((Runnable)QueryUpdateTask.newBuilder().realmConfiguration(this.realm.getConfiguration()).addObject((WeakReference)entry.getKey(), entry.getValue().handoverQueryPointer(), entry.getValue().getArgument()).sendToNotifier(this.realm.sharedRealm.realmNotifier, QueryUpdateTask$NotifyEvent.COMPLETE_ASYNC_OBJECT).build());
            }
            else {
                iterator.remove();
            }
        }
    }
    
    private void updateAsyncQueries() {
        if (this.updateAsyncQueriesTask != null && !this.updateAsyncQueriesTask.isDone()) {
            this.updateAsyncQueriesTask.cancel(true);
            Realm.asyncTaskExecutor.getQueue().remove(this.updateAsyncQueriesTask);
            RealmLog.trace("REALM_CHANGED realm: %s cancelling pending COMPLETED_UPDATE_ASYNC_QUERIES updates", new Object[] { this });
        }
        RealmLog.trace("REALM_CHANGED realm: %s updating async queries, total: %d", new Object[] { this, this.asyncRealmResults.size() });
        final QueryUpdateTask$Builder$UpdateQueryStep realmConfiguration = QueryUpdateTask.newBuilder().realmConfiguration(this.realm.getConfiguration());
        QueryUpdateTask$Builder$RealmResultsQueryStep add = null;
        final Iterator<Map.Entry<WeakReference<RealmResults<? extends RealmModel>>, RealmQuery<? extends RealmModel>>> iterator = this.asyncRealmResults.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<WeakReference<RealmResults<? extends RealmModel>>, RealmQuery<? extends RealmModel>> entry = iterator.next();
            final WeakReference<RealmResults<? extends RealmModel>> weakReference = entry.getKey();
            if (weakReference.get() == null) {
                iterator.remove();
            }
            else {
                add = realmConfiguration.add((WeakReference)weakReference, entry.getValue().handoverQueryPointer(), entry.getValue().getArgument());
            }
        }
        if (add != null) {
            this.updateAsyncQueriesTask = Realm.asyncTaskExecutor.submitQueryUpdate((Runnable)add.sendToNotifier(this.realm.sharedRealm.realmNotifier, QueryUpdateTask$NotifyEvent.COMPLETE_UPDATE_ASYNC_QUERIES).build());
        }
    }
    
     <E extends RealmObjectProxy> void addToRealmObjects(final E e) {
        final Iterator<WeakReference> iterator = this.realmObjects.keySet().iterator();
        while (iterator.hasNext()) {
            if (iterator.next().get() == e) {
                return;
            }
        }
        this.realmObjects.put(new WeakReference<RealmObjectProxy>(e, (ReferenceQueue<? super RealmObjectProxy>)this.referenceQueueRealmObject), HandlerController.NO_REALM_QUERY);
    }
    
    void addToRealmResults(final RealmResults<? extends RealmModel> realmResults) {
        this.syncRealmResults.add((Object)new WeakReference(realmResults, (ReferenceQueue<? super Object>)this.referenceQueueSyncRealmResults));
    }
    
    public void checkCanBeAutoRefreshed() {
        if (Looper.myLooper() == null) {
            throw new IllegalStateException("Cannot set auto-refresh in a Thread without a Looper");
        }
        if (isIntentServiceThread()) {
            throw new IllegalStateException("Cannot set auto-refresh in an IntentService thread.");
        }
    }
    
    public void handleAsyncTransactionCompleted(final Runnable runnable) {
        if (this.realm.sharedRealm != null) {
            if (runnable != null) {
                this.pendingOnSuccessAsyncTransactionCallbacks.add(runnable);
            }
            this.realmChanged(false);
        }
    }
    
    public boolean handleMessage(final Message message) {
        if (this.realm.sharedRealm != null) {
            switch (message.what) {
                default: {
                    throw new IllegalArgumentException("Unknown message: " + message.what);
                }
                case 14930352:
                case 165580141: {
                    this.realmChanged(message.what == 165580141);
                    break;
                }
                case 39088169: {
                    this.completedAsyncRealmResults((QueryUpdateTask$Result)message.obj);
                    return true;
                }
                case 63245986: {
                    this.completedAsyncRealmObject((QueryUpdateTask$Result)message.obj);
                    return true;
                }
                case 24157817: {
                    this.completedAsyncQueriesUpdate((QueryUpdateTask$Result)message.obj);
                    return true;
                }
                case 102334155: {
                    throw (Error)message.obj;
                }
            }
        }
        return true;
    }
    
    public boolean isAutoRefreshAvailable() {
        return Looper.myLooper() != null && !isIntentServiceThread();
    }
    
    public boolean isAutoRefreshEnabled() {
        return this.autoRefresh;
    }
    
    void notifyAllListeners(final List<RealmResults<? extends RealmModel>> list) {
        final Iterator<RealmResults<? extends RealmModel>> iterator = list.iterator();
        while (!this.realm.isClosed() && iterator.hasNext()) {
            iterator.next().notifyChangeListeners(false);
        }
        this.notifyRealmObjectCallbacks();
        if (!this.realm.isClosed() && this.threadContainsAsyncEmptyRealmObject()) {
            this.updateAsyncEmptyRealmObject();
        }
        this.notifyAsyncTransactionCallbacks();
        this.notifyGlobalListeners();
    }
    
    public void setAutoRefresh(final boolean autoRefresh) {
        this.checkCanBeAutoRefreshed();
        this.autoRefresh = autoRefresh;
    }
    
    boolean threadContainsAsyncEmptyRealmObject() {
        final Iterator<Map.Entry<WeakReference<RealmObjectProxy>, RealmQuery<? extends RealmModel>>> iterator = this.emptyAsyncRealmObject.entrySet().iterator();
        boolean b = true;
        while (iterator.hasNext()) {
            if (iterator.next().getKey().get() == null) {
                iterator.remove();
            }
            else {
                b = false;
            }
        }
        return !b;
    }
}
