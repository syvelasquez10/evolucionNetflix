// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import java.util.Iterator;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.common.internal.n;
import java.util.Set;

public abstract class a<T> implements MetadataField<T>
{
    private final String Pt;
    private final Set<String> Pu;
    private final Set<String> Pv;
    private final int Pw;
    
    protected a(final String s, final int pw) {
        this.Pt = n.b(s, (Object)"fieldName");
        this.Pu = Collections.singleton(s);
        this.Pv = Collections.emptySet();
        this.Pw = pw;
    }
    
    protected a(final String s, final Collection<String> collection, final Collection<String> collection2, final int pw) {
        this.Pt = n.b(s, (Object)"fieldName");
        this.Pu = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(collection));
        this.Pv = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(collection2));
        this.Pw = pw;
    }
    
    @Override
    public final T a(final DataHolder dataHolder, final int n, final int n2) {
        if (this.b(dataHolder, n, n2)) {
            return this.c(dataHolder, n, n2);
        }
        return null;
    }
    
    protected abstract void a(final Bundle p0, final T p1);
    
    @Override
    public final void a(final DataHolder dataHolder, final MetadataBundle metadataBundle, final int n, final int n2) {
        n.b(dataHolder, "dataHolder");
        n.b(metadataBundle, "bundle");
        metadataBundle.b((MetadataField<Object>)this, this.a(dataHolder, n, n2));
    }
    
    @Override
    public final void a(final T t, final Bundle bundle) {
        n.b(bundle, "bundle");
        if (t == null) {
            bundle.putString(this.getName(), (String)null);
            return;
        }
        this.a(bundle, t);
    }
    
    protected boolean b(final DataHolder dataHolder, final int n, final int n2) {
        final Iterator<String> iterator = this.Pu.iterator();
        while (iterator.hasNext()) {
            if (dataHolder.h(iterator.next(), n, n2)) {
                return false;
            }
        }
        return true;
    }
    
    protected abstract T c(final DataHolder p0, final int p1, final int p2);
    
    @Override
    public final T f(final Bundle bundle) {
        n.b(bundle, "bundle");
        if (bundle.get(this.getName()) != null) {
            return this.g(bundle);
        }
        return null;
    }
    
    protected abstract T g(final Bundle p0);
    
    @Override
    public final String getName() {
        return this.Pt;
    }
    
    @Override
    public String toString() {
        return this.Pt;
    }
}
