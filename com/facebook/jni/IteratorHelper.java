// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.jni;

import java.util.Iterator;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class IteratorHelper
{
    @DoNotStrip
    private Object mElement;
    private final Iterator mIterator;
    
    public IteratorHelper(final Iterable iterable) {
        this.mIterator = iterable.iterator();
    }
    
    public IteratorHelper(final Iterator mIterator) {
        this.mIterator = mIterator;
    }
    
    @DoNotStrip
    boolean hasNext() {
        if (this.mIterator.hasNext()) {
            this.mElement = this.mIterator.next();
            return true;
        }
        this.mElement = null;
        return false;
    }
}
