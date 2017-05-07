// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.ij;
import com.google.android.gms.common.api.BaseImplementation$a;
import com.google.android.gms.common.api.Result;

public abstract class Cast$a<R extends Result> extends BaseImplementation$a<R, ij>
{
    public Cast$a() {
        super(Cast.CU);
    }
    
    public void V(final int n) {
        this.b(this.c(new Status(n)));
    }
}
