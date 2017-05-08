// 
// Decompiled by Procyon v0.5.30
// 

package io.realm.internal;

import java.util.NoSuchElementException;
import java.util.ListIterator;

public abstract class Collection$ListIterator<T> extends Collection$Iterator<T> implements ListIterator<T>
{
    public Collection$ListIterator(final Collection collection, final int n) {
        super(collection);
        if (n >= 0 && n <= this.iteratorCollection.size()) {
            this.pos = n - 1;
            return;
        }
        throw new IndexOutOfBoundsException("Starting location must be a valid index: [0, " + (this.iteratorCollection.size() - 1L) + "]. Yours was " + n);
    }
    
    @Deprecated
    @Override
    public void add(final T t) {
        throw new UnsupportedOperationException("Adding an element is not supported. Use Realm.createObject() instead.");
    }
    
    @Override
    public boolean hasPrevious() {
        this.checkValid();
        return this.pos >= 0;
    }
    
    @Override
    public int nextIndex() {
        this.checkValid();
        return this.pos + 1;
    }
    
    @Override
    public T previous() {
        this.checkValid();
        try {
            final T value = this.get(this.pos);
            --this.pos;
            return value;
        }
        catch (IndexOutOfBoundsException ex) {
            throw new NoSuchElementException("Cannot access index less than zero. This was " + this.pos + ". Remember to check hasPrevious() before using previous().");
        }
    }
    
    @Override
    public int previousIndex() {
        this.checkValid();
        return this.pos;
    }
    
    @Deprecated
    @Override
    public void set(final T t) {
        throw new UnsupportedOperationException("Replacing and element is not supported.");
    }
}
