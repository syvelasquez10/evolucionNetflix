// 
// Decompiled by Procyon v0.5.30
// 

package io.realm.internal;

import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

public abstract class Collection$Iterator<T> implements Iterator<T>
{
    Collection iteratorCollection;
    protected int pos;
    
    public Collection$Iterator(final Collection iteratorCollection) {
        this.pos = -1;
        this.iteratorCollection = iteratorCollection;
        if (Collection.access$000(iteratorCollection)) {
            return;
        }
        if (Collection.access$100(iteratorCollection).isInTransaction()) {
            this.detach();
            return;
        }
        Collection.access$100(this.iteratorCollection).addIterator(this);
    }
    
    void checkValid() {
        if (this.iteratorCollection == null) {
            throw new ConcurrentModificationException("No outside changes to a Realm is allowed while iterating a living Realm collection.");
        }
    }
    
    protected abstract T convertRowToObject(final UncheckedRow p0);
    
    void detach() {
        this.iteratorCollection = this.iteratorCollection.createSnapshot();
    }
    
    T get(final int n) {
        return this.convertRowToObject(this.iteratorCollection.getUncheckedRow(n));
    }
    
    @Override
    public boolean hasNext() {
        this.checkValid();
        return this.pos + 1 < this.iteratorCollection.size();
    }
    
    void invalidate() {
        this.iteratorCollection = null;
    }
    
    @Override
    public T next() {
        this.checkValid();
        ++this.pos;
        if (this.pos >= this.iteratorCollection.size()) {
            throw new NoSuchElementException("Cannot access index " + this.pos + " when size is " + this.iteratorCollection.size() + ". Remember to check hasNext() before using next().");
        }
        return this.get(this.pos);
    }
    
    @Deprecated
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove() is not supported by RealmResults iterators.");
    }
}
