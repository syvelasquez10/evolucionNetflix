// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

import java.util.Iterator;

final class MapCollections$ArrayIterator<T> implements Iterator<T>
{
    boolean mCanRemove;
    int mIndex;
    final int mOffset;
    int mSize;
    final /* synthetic */ MapCollections this$0;
    
    MapCollections$ArrayIterator(final MapCollections this$0, final int mOffset) {
        this.this$0 = this$0;
        this.mCanRemove = false;
        this.mOffset = mOffset;
        this.mSize = this$0.colGetSize();
    }
    
    @Override
    public boolean hasNext() {
        return this.mIndex < this.mSize;
    }
    
    @Override
    public T next() {
        final Object colGetEntry = this.this$0.colGetEntry(this.mIndex, this.mOffset);
        ++this.mIndex;
        this.mCanRemove = true;
        return (T)colGetEntry;
    }
    
    @Override
    public void remove() {
        if (!this.mCanRemove) {
            throw new IllegalStateException();
        }
        --this.mIndex;
        --this.mSize;
        this.mCanRemove = false;
        this.this$0.colRemoveAt(this.mIndex);
    }
}
