// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.data.DataHolder;
import java.util.Collection;
import com.google.android.gms.common.data.a;
import com.google.android.gms.drive.metadata.internal.j;

final class kd$1 extends j<com.google.android.gms.common.data.a>
{
    kd$1(final String s, final Collection collection, final Collection collection2, final int n) {
        super(s, collection, collection2, n);
    }
    
    protected com.google.android.gms.common.data.a k(final DataHolder dataHolder, final int n, final int n2) {
        throw new IllegalStateException("Thumbnail field is write only");
    }
}
