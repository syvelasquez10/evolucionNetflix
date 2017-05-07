// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

final class ib$g extends ia
{
    private final BaseImplementation$b<Status> De;
    
    public ib$g(final BaseImplementation$b<Status> baseImplementation$b) {
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void fq() {
        this.De.b(new Status(0));
    }
}
