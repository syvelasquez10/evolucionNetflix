// 
// Decompiled by Procyon v0.5.30
// 

package io.realm;

import java.util.ListIterator;
import java.util.Iterator;
import io.realm.internal.InvalidRow;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Collection;
import java.util.AbstractList;

abstract class OrderedRealmCollectionImpl<E extends RealmModel> extends AbstractList<E> implements OrderedRealmCollection<E>
{
    String className;
    Class<E> classSpec;
    final io.realm.internal.Collection collection;
    final BaseRealm realm;
    
    OrderedRealmCollectionImpl(final BaseRealm realm, final io.realm.internal.Collection collection, final Class<E> classSpec) {
        this.realm = realm;
        this.classSpec = classSpec;
        this.collection = collection;
    }
    
    OrderedRealmCollectionImpl(final BaseRealm realm, final io.realm.internal.Collection collection, final String className) {
        this.realm = realm;
        this.className = className;
        this.collection = collection;
    }
    
    @Deprecated
    @Override
    public void add(final int n, final E e) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    @Override
    public boolean add(final E e) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    @Override
    public boolean addAll(final int n, final Collection<? extends E> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    public boolean addAll(final Collection<? extends E> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    @Override
    public void clear() {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    public boolean contains(final Object o) {
        if (this.isLoaded()) {
            if (o instanceof RealmObjectProxy && ((RealmObjectProxy)o).realmGet$proxyState().getRow$realm() == InvalidRow.INSTANCE) {
                return false;
            }
            final Iterator<RealmModel> iterator = this.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(o)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean deleteAllFromRealm() {
        this.realm.checkIfValid();
        if (this.size() > 0) {
            this.collection.clear();
            return true;
        }
        return false;
    }
    
    @Override
    public E get(final int n) {
        this.realm.checkIfValid();
        return this.realm.get(this.classSpec, this.className, this.collection.getUncheckedRow(n));
    }
    
    @Override
    public Iterator<E> iterator() {
        return (Iterator<E>)new OrderedRealmCollectionImpl$RealmCollectionIterator(this);
    }
    
    @Override
    public ListIterator<E> listIterator() {
        return (ListIterator<E>)new OrderedRealmCollectionImpl$RealmCollectionListIterator(this, 0);
    }
    
    @Override
    public ListIterator<E> listIterator(final int n) {
        return (ListIterator<E>)new OrderedRealmCollectionImpl$RealmCollectionListIterator(this, n);
    }
    
    @Deprecated
    @Override
    public E remove(final int n) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    public boolean remove(final Object o) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    public boolean removeAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    public boolean retainAll(final Collection<?> collection) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    @Deprecated
    @Override
    public E set(final int n, final E e) {
        throw new UnsupportedOperationException("This method is not supported by 'RealmResults' or 'OrderedRealmCollectionSnapshot'.");
    }
    
    public int size() {
        if (!this.isLoaded()) {
            return 0;
        }
        final long size = this.collection.size();
        if (size > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int)size;
    }
}
