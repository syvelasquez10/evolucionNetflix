// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.drive.metadata.internal;

import java.util.ArrayList;
import java.util.Collection;
import android.os.Bundle;
import com.google.android.gms.drive.metadata.CollectionMetadataField;
import android.os.Parcelable;

public class e<T extends Parcelable> extends CollectionMetadataField<T>
{
    public e(final String s) {
        super(s);
    }
    
    @Override
    protected void a(final Bundle bundle, final Collection<T> collection) {
        bundle.putParcelableArrayList(this.getName(), new ArrayList((Collection<? extends E>)collection));
    }
    
    protected Collection<T> i(final Bundle bundle) {
        return (Collection<T>)bundle.getParcelableArrayList(this.getName());
    }
}
