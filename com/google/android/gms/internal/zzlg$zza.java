// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Iterator;

final class zzlg$zza<T> implements Iterator<T>
{
    boolean mCanRemove;
    int mIndex;
    final int mOffset;
    int mSize;
    final /* synthetic */ zzlg zzaeH;
    
    zzlg$zza(final zzlg zzaeH, final int mOffset) {
        this.zzaeH = zzaeH;
        this.mCanRemove = false;
        this.mOffset = mOffset;
        this.mSize = zzaeH.colGetSize();
    }
    
    @Override
    public boolean hasNext() {
        return this.mIndex < this.mSize;
    }
    
    @Override
    public T next() {
        final Object colGetEntry = this.zzaeH.colGetEntry(this.mIndex, this.mOffset);
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
        this.zzaeH.colRemoveAt(this.mIndex);
    }
}
