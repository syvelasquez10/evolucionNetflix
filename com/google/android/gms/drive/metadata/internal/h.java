// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.drive.metadata.a;
import android.os.Parcelable;

public abstract class h<T extends Parcelable> extends a<T>
{
    public h(final String s, final int n) {
        super(s, n);
    }
    
    public h(final String s, final Collection<String> collection, final int n) {
        super(s, collection, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final T t) {
        bundle.putParcelable(this.getName(), (Parcelable)t);
    }
    
    protected T k(final Bundle bundle) {
        return (T)bundle.getParcelable(this.getName());
    }
}
