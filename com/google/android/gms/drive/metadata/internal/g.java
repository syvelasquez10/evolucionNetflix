// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.ArrayList;
import java.util.Collection;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.b;
import android.os.Parcelable;

public class g<T extends Parcelable> extends b<T>
{
    public g(final String s, final int n) {
        super(s, n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Collection<T> collection) {
        bundle.putParcelableArrayList(this.getName(), new ArrayList((Collection<? extends E>)collection));
    }
    
    protected Collection<T> j(final Bundle bundle) {
        return (Collection<T>)bundle.getParcelableArrayList(this.getName());
    }
}
