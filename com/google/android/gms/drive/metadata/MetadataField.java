// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import java.util.Iterator;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.internal.eg;
import java.util.Set;

public abstract class MetadataField<T>
{
    private final String rC;
    private final Set<String> rD;
    
    protected MetadataField(final String s) {
        this.rC = eg.b(s, "fieldName");
        this.rD = Collections.singleton(s);
    }
    
    protected MetadataField(final String s, final Collection<String> collection) {
        this.rC = eg.b(s, "fieldName");
        this.rD = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(collection));
    }
    
    protected abstract void a(final Bundle p0, final T p1);
    
    public final void a(final DataHolder dataHolder, final MetadataBundle metadataBundle, final int n, final int n2) {
        eg.b(dataHolder, "dataHolder");
        eg.b(metadataBundle, "bundle");
        metadataBundle.b((MetadataField<Object>)this, this.c(dataHolder, n, n2));
    }
    
    public final void a(final T t, final Bundle bundle) {
        eg.b(bundle, "bundle");
        if (t == null) {
            bundle.putString(this.getName(), (String)null);
            return;
        }
        this.a(bundle, t);
    }
    
    protected abstract T b(final DataHolder p0, final int p1, final int p2);
    
    public final T c(final DataHolder dataHolder, final int n, final int n2) {
        final Iterator<String> iterator = this.rD.iterator();
        while (iterator.hasNext()) {
            if (dataHolder.hasNull(iterator.next(), n, n2)) {
                return null;
            }
        }
        return this.b(dataHolder, n, n2);
    }
    
    public final Collection<String> cV() {
        return this.rD;
    }
    
    public final T d(final Bundle bundle) {
        eg.b(bundle, "bundle");
        if (bundle.get(this.getName()) != null) {
            return this.e(bundle);
        }
        return null;
    }
    
    protected abstract T e(final Bundle p0);
    
    public final String getName() {
        return this.rC;
    }
    
    @Override
    public String toString() {
        return this.rC;
    }
}
