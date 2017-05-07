// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;

final class zzmh$zza<T> implements Iterator<T>
{
    boolean mCanRemove;
    int mIndex;
    final int mOffset;
    int mSize;
    final /* synthetic */ zzmh zzagL;
    
    zzmh$zza(final zzmh zzagL, final int mOffset) {
        this.zzagL = zzagL;
        this.mCanRemove = false;
        this.mOffset = mOffset;
        this.mSize = zzagL.colGetSize();
    }
    
    @Override
    public boolean hasNext() {
        return this.mIndex < this.mSize;
    }
    
    @Override
    public T next() {
        final Object colGetEntry = this.zzagL.colGetEntry(this.mIndex, this.mOffset);
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
        this.zzagL.colRemoveAt(this.mIndex);
    }
}
