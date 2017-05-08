// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.references;

import java.lang.ref.SoftReference;

public class OOMSoftReference<T>
{
    SoftReference<T> softRef1;
    SoftReference<T> softRef2;
    SoftReference<T> softRef3;
    
    public OOMSoftReference() {
        this.softRef1 = null;
        this.softRef2 = null;
        this.softRef3 = null;
    }
    
    public void clear() {
        if (this.softRef1 != null) {
            this.softRef1.clear();
            this.softRef1 = null;
        }
        if (this.softRef2 != null) {
            this.softRef2.clear();
            this.softRef2 = null;
        }
        if (this.softRef3 != null) {
            this.softRef3.clear();
            this.softRef3 = null;
        }
    }
    
    public T get() {
        if (this.softRef1 == null) {
            return null;
        }
        return this.softRef1.get();
    }
    
    public void set(final T t) {
        this.softRef1 = new SoftReference<T>(t);
        this.softRef2 = new SoftReference<T>(t);
        this.softRef3 = new SoftReference<T>(t);
    }
}
