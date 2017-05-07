// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.ArrayList;
import android.os.Bundle;
import java.util.Collection;
import java.util.Collections;
import com.google.android.gms.drive.metadata.b;
import android.os.Parcelable;

public class i<T extends Parcelable> extends b<T>
{
    public i(final String s, final int n) {
        super(s, Collections.emptySet(), Collections.singleton(s), n);
    }
    
    @Override
    protected void a(final Bundle bundle, final Collection<T> collection) {
        bundle.putParcelableArrayList(this.getName(), new ArrayList((Collection<? extends E>)collection));
    }
    
    protected Collection<T> l(final Bundle bundle) {
        return (Collection<T>)bundle.getParcelableArrayList(this.getName());
    }
}
