// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.images;

import com.google.android.gms.common.internal.m;
import android.net.Uri;

final class a$a
{
    public final Uri uri;
    
    public a$a(final Uri uri) {
        this.uri = uri;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof a$a && (this == o || m.equal(((a$a)o).uri, this.uri));
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.uri);
    }
}
