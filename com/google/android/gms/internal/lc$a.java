// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.SessionReadResult;
import com.google.android.gms.common.api.BaseImplementation$b;

class lc$a extends kq$a
{
    private final BaseImplementation$b<SessionReadResult> De;
    
    private lc$a(final BaseImplementation$b<SessionReadResult> de) {
        this.De = de;
    }
    
    public void a(final SessionReadResult sessionReadResult) {
        this.De.b(sessionReadResult);
    }
}
