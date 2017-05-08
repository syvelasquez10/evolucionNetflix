// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.references.OOMSoftReference;
import java.util.LinkedList;

class OOMSoftReferenceBucket<V> extends Bucket<V>
{
    private LinkedList<OOMSoftReference<V>> mSpareReferences;
    
    public OOMSoftReferenceBucket(final int n, final int n2, final int n3) {
        super(n, n2, n3);
        this.mSpareReferences = new LinkedList<OOMSoftReference<V>>();
    }
    
    @Override
    void addToFreeList(final V v) {
        OOMSoftReference<V> oomSoftReference;
        if ((oomSoftReference = this.mSpareReferences.poll()) == null) {
            oomSoftReference = new OOMSoftReference<V>();
        }
        oomSoftReference.set(v);
        this.mFreeList.add(oomSoftReference);
    }
    
    @Override
    public V pop() {
        final OOMSoftReference<Object> oomSoftReference = this.mFreeList.poll();
        final V value = oomSoftReference.get();
        oomSoftReference.clear();
        this.mSpareReferences.add((OOMSoftReference<V>)oomSoftReference);
        return value;
    }
}
