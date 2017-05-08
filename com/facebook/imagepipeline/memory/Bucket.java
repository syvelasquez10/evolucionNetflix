// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import java.util.LinkedList;
import com.facebook.common.internal.Preconditions;
import java.util.Queue;

class Bucket<V>
{
    final Queue mFreeList;
    private int mInUseLength;
    public final int mItemSize;
    public final int mMaxLength;
    
    public Bucket(final int mItemSize, final int mMaxLength, final int mInUseLength) {
        final boolean b = true;
        Preconditions.checkState(mItemSize > 0);
        Preconditions.checkState(mMaxLength >= 0);
        Preconditions.checkState(mInUseLength >= 0 && b);
        this.mItemSize = mItemSize;
        this.mMaxLength = mMaxLength;
        this.mFreeList = new LinkedList();
        this.mInUseLength = mInUseLength;
    }
    
    void addToFreeList(final V v) {
        this.mFreeList.add(v);
    }
    
    public void decrementInUseCount() {
        Preconditions.checkState(this.mInUseLength > 0);
        --this.mInUseLength;
    }
    
    public V get() {
        final V pop = this.pop();
        if (pop != null) {
            ++this.mInUseLength;
        }
        return pop;
    }
    
    int getFreeListSize() {
        return this.mFreeList.size();
    }
    
    public void incrementInUseCount() {
        ++this.mInUseLength;
    }
    
    public boolean isMaxLengthExceeded() {
        return this.mInUseLength + this.getFreeListSize() > this.mMaxLength;
    }
    
    public V pop() {
        return this.mFreeList.poll();
    }
    
    public void release(final V v) {
        Preconditions.checkNotNull(v);
        Preconditions.checkState(this.mInUseLength > 0);
        --this.mInUseLength;
        this.addToFreeList(v);
    }
}
