// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.common.api.BaseImplementation$a;
import com.google.android.gms.common.api.Result;

abstract class hz$c<T extends Result> extends BaseImplementation$a<T, hy>
{
    public hz$c() {
        super(hd.BN);
    }
    
    protected abstract void a(final hv p0);
    
    @Override
    protected final void a(final hy hy) {
        this.a(hy.fo());
    }
}
