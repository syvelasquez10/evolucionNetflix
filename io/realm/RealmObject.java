// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import io.realm.internal.Row;
import io.realm.internal.InvalidRow;
import rx.Observable;
import java.util.List;
import io.realm.internal.RealmObjectProxy;

public abstract class RealmObject implements RealmModel
{
    public static <E extends RealmModel> void addChangeListener(final E e, final RealmChangeListener<E> realmChangeListener) {
        if (e == null) {
            throw new IllegalArgumentException("Object should not be null");
        }
        if (realmChangeListener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        }
        if (!(e instanceof RealmObjectProxy)) {
            throw new IllegalArgumentException("Cannot add listener from this unmanaged RealmObject (created outside of Realm)");
        }
        final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)e;
        final BaseRealm realm$realm = realmObjectProxy.realmGet$proxyState().getRealm$realm();
        realm$realm.checkIfValid();
        if (!realm$realm.handlerController.isAutoRefreshEnabled()) {
            throw new IllegalStateException("You can't register a listener from a non-Looper thread or IntentService thread.");
        }
        final List listeners$realm = realmObjectProxy.realmGet$proxyState().getListeners$realm();
        if (!listeners$realm.contains(realmChangeListener)) {
            listeners$realm.add(realmChangeListener);
        }
        if (isLoaded(realmObjectProxy)) {
            realm$realm.handlerController.addToRealmObjects(realmObjectProxy);
        }
    }
    
    public static <E extends RealmModel> Observable<E> asObservable(final E e) {
        if (!(e instanceof RealmObjectProxy)) {
            throw new IllegalArgumentException("Cannot create Observables from unmanaged RealmObjects");
        }
        final BaseRealm realm$realm = ((RealmObjectProxy)e).realmGet$proxyState().getRealm$realm();
        if (realm$realm instanceof Realm) {
            return (Observable<E>)realm$realm.configuration.getRxFactory().from((Realm)realm$realm, (RealmModel)e);
        }
        if (realm$realm instanceof DynamicRealm) {
            return (Observable<E>)realm$realm.configuration.getRxFactory().from((DynamicRealm)realm$realm, (DynamicRealmObject)e);
        }
        throw new UnsupportedOperationException(((DynamicRealm)realm$realm).getClass() + " does not support RxJava." + " See https://realm.io/docs/java/latest/#rxjava for more details.");
    }
    
    public static <E extends RealmModel> void deleteFromRealm(final E e) {
        if (!(e instanceof RealmObjectProxy)) {
            throw new IllegalArgumentException("Object not managed by Realm, so it cannot be removed.");
        }
        final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)e;
        if (realmObjectProxy.realmGet$proxyState().getRow$realm() == null) {
            throw new IllegalStateException("Object malformed: missing object in Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
        }
        if (realmObjectProxy.realmGet$proxyState().getRealm$realm() == null) {
            throw new IllegalStateException("Object malformed: missing Realm. Make sure to instantiate RealmObjects with Realm.createObject()");
        }
        realmObjectProxy.realmGet$proxyState().getRealm$realm().checkIfValid();
        final Row row$realm = realmObjectProxy.realmGet$proxyState().getRow$realm();
        row$realm.getTable().moveLastOver(row$realm.getIndex());
        realmObjectProxy.realmGet$proxyState().setRow$realm((Row)InvalidRow.INSTANCE);
    }
    
    public static <E extends RealmModel> boolean isLoaded(final E e) {
        if (e instanceof RealmObjectProxy) {
            final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)e;
            realmObjectProxy.realmGet$proxyState().getRealm$realm().checkIfValid();
            if (realmObjectProxy.realmGet$proxyState().getPendingQuery$realm() != null && !realmObjectProxy.realmGet$proxyState().isCompleted$realm()) {
                return false;
            }
        }
        return true;
    }
    
    public static <E extends RealmModel> boolean isManaged(final E e) {
        return e instanceof RealmObjectProxy;
    }
    
    public static <E extends RealmModel> boolean isValid(final E e) {
        if (e instanceof RealmObjectProxy) {
            final Row row$realm = ((RealmObjectProxy)e).realmGet$proxyState().getRow$realm();
            if (row$realm == null || !row$realm.isAttached()) {
                return false;
            }
        }
        return true;
    }
    
    public static <E extends RealmModel> boolean load(final E e) {
        return isLoaded((RealmModel)e) || (e instanceof RealmObjectProxy && ((RealmObjectProxy)e).realmGet$proxyState().onCompleted$realm());
    }
    
    public static <E extends RealmModel> void removeChangeListener(final E e, final RealmChangeListener realmChangeListener) {
        if (e == null) {
            throw new IllegalArgumentException("Object should not be null");
        }
        if (realmChangeListener == null) {
            throw new IllegalArgumentException("Listener should not be null");
        }
        if (e instanceof RealmObjectProxy) {
            final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)e;
            realmObjectProxy.realmGet$proxyState().getRealm$realm().checkIfValid();
            realmObjectProxy.realmGet$proxyState().getListeners$realm().remove(realmChangeListener);
            return;
        }
        throw new IllegalArgumentException("Cannot remove listener from this unmanaged RealmObject (created outside of Realm)");
    }
    
    public static <E extends RealmModel> void removeChangeListeners(final E e) {
        if (e instanceof RealmObjectProxy) {
            final RealmObjectProxy realmObjectProxy = (RealmObjectProxy)e;
            realmObjectProxy.realmGet$proxyState().getRealm$realm().checkIfValid();
            realmObjectProxy.realmGet$proxyState().getListeners$realm().clear();
            return;
        }
        throw new IllegalArgumentException("Cannot remove listeners from this unmanaged RealmObject (created outside of Realm)");
    }
    
    public final <E extends RealmModel> void addChangeListener(final RealmChangeListener<E> realmChangeListener) {
        addChangeListener(this, (RealmChangeListener<RealmModel>)realmChangeListener);
    }
    
    public final <E extends RealmObject> Observable<E> asObservable() {
        return asObservable(this);
    }
    
    public final void deleteFromRealm() {
        deleteFromRealm(this);
    }
    
    public final boolean isLoaded() {
        return isLoaded(this);
    }
    
    public boolean isManaged() {
        return isManaged(this);
    }
    
    public final boolean isValid() {
        return isValid(this);
    }
    
    public final boolean load() {
        return load(this);
    }
    
    public final void removeChangeListener(final RealmChangeListener realmChangeListener) {
        removeChangeListener(this, realmChangeListener);
    }
    
    public final void removeChangeListeners() {
        removeChangeListeners(this);
    }
}
