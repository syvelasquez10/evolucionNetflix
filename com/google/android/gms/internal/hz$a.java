// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;

abstract class hz$a<T> implements Result
{
    private final Status CM;
    protected final T CN;
    
    public hz$a(final Status cm, final T cn) {
        this.CM = cm;
        this.CN = cn;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
