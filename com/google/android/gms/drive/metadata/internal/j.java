// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.drive.metadata.a;
import android.os.Parcelable;

public abstract class j<T extends Parcelable> extends a<T>
{
    public j(final String s, final Collection<String> collection, final Collection<String> collection2, final int n) {
        super(s, collection, collection2, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final T t) {
        bundle.putParcelable(this.getName(), (Parcelable)t);
    }
    
    protected T m(final Bundle bundle) {
        return (T)bundle.getParcelable(this.getName());
    }
}
