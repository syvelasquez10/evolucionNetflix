// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.drive.metadata.MetadataField;
import android.os.Parcelable;

public abstract class f<T extends Parcelable> extends MetadataField<T>
{
    public f(final String s, final Collection<String> collection) {
        super(s, collection);
    }
    
    @Override
    protected void a(final Bundle bundle, final T t) {
        bundle.putParcelable(this.getName(), (Parcelable)t);
    }
    
    protected T j(final Bundle bundle) {
        return (T)bundle.getParcelable(this.getName());
    }
}
