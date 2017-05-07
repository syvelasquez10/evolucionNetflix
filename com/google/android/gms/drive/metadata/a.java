// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import android.os.Bundle;
import java.util.Iterator;
import com.google.android.gms.common.data.DataHolder;
import java.util.HashSet;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.internal.fq;
import java.util.Set;

public abstract class a<T> implements MetadataField<T>
{
    private final String FM;
    private final Set<String> FN;
    private final int FO;
    
    protected a(final String s, final int fo) {
        this.FM = fq.b(s, (Object)"fieldName");
        this.FN = Collections.singleton(s);
        this.FO = fo;
    }
    
    protected a(final String s, final Collection<String> collection, final int fo) {
        this.FM = fq.b(s, (Object)"fieldName");
        this.FN = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(collection));
        this.FO = fo;
    }
    
    @Override
    public final T a(final DataHolder dataHolder, final int n, final int n2) {
        final Iterator<String> iterator = this.FN.iterator();
        while (iterator.hasNext()) {
            if (dataHolder.hasNull(iterator.next(), n, n2)) {
                return null;
            }
        }
        return this.b(dataHolder, n, n2);
    }
    
    protected abstract void a(final Bundle p0, final T p1);
    
    @Override
    public final void a(final DataHolder dataHolder, final MetadataBundle metadataBundle, final int n, final int n2) {
        fq.b(dataHolder, "dataHolder");
        fq.b(metadataBundle, "bundle");
        metadataBundle.b((MetadataField<Object>)this, this.a(dataHolder, n, n2));
    }
    
    @Override
    public final void a(final T t, final Bundle bundle) {
        fq.b(bundle, "bundle");
        if (t == null) {
            bundle.putString(this.getName(), (String)null);
            return;
        }
        this.a(bundle, t);
    }
    
    protected abstract T b(final DataHolder p0, final int p1, final int p2);
    
    @Override
    public final T d(final Bundle bundle) {
        fq.b(bundle, "bundle");
        if (bundle.get(this.getName()) != null) {
            return this.e(bundle);
        }
        return null;
    }
    
    protected abstract T e(final Bundle p0);
    
    @Override
    public final Collection<String> fR() {
        return this.FN;
    }
    
    @Override
    public final String getName() {
        return this.FM;
    }
    
    @Override
    public String toString() {
        return this.FM;
    }
}
